/**raytracer 
 * could perhaps optimize by mapping camera to origin, to avoid having storing same origin point for <resolution> rays
*/
import org.apache.commons.math.geometry.Vector3D;
public class RayTracer{
	public final double EPSILON = 0.001;//need this so reflected rays don't get intercepted at starting point
	public final double SCALE =1;
	/** returns a 2d array of all rays to be traced for a given screen
	 * @param x, @param y, @param z, the co-ordinates of the origin of all rays
	 * @param screen, the screen object representing the display
	 * @param xres, @param yres, the x and y resolution of the screen. thus returned Ray[][] will be of
	 * size [xres][yres]
	 */
	public Ray[][] initializeRays(double x, double y, double z, Screen screen, int xres, int yres){
		Vector3D origin = new Vector3D(x,y,z);
		Ray[][] rays;
		//<= used as we want neat behaviour on bad exceptions rather than returning say a [50][0] array
		if (screen ==null || xres <=0 || yres <= 0){rays = new Ray[0][0];}
		else{
		rays = new Ray[xres][yres];
		for(int i =0; i < xres; i++){
			for(int j=0; j < yres; j++){
				Vector3D screenPoint = new Vector3D(SCALE,screen.getOrigin(),(((double)i)/xres)*screen.getL1(), screen.getV1(),(((double)j)/yres)*screen.getL2(),screen.getV2()); 
				//System.out.println(screenPoint.getX() + " " +screenPoint.getY() + " " +screenPoint.getZ());
				try{
				rays[i][j] = new Ray(origin, screenPoint.subtract(SCALE,origin));
				}catch (Exception e){System.out.println(e.toString());}
			}
		}
		}
		return rays;
	}
	/** returns pixel colour in vector3D format
	 * Should investigate refactoring to avoid creating an entire scene for every trace
	 * needs heavy optimisation to avoid redundant traces
	 * should refactor out use of Vector3D as colour, as it requires a new object whenever it's modified
	 * also to forbid negtive numbers
	 * @param ray, the ray being traced through the screen.
	 * @param scene, the scene the robot is being passed through
	 * @param cDepth, the current recursive depth of this trace
	 * @param maxDepth, the maximum depth to recursively trace to.
	 *  */
	public Vector3D Trace(Ray ray, Scene scene, int cDepth, int maxDepth){
		double dist = Double.MAX_VALUE;
		double tempDist;
		double intensity;
		double tempIntensity;
		int object = 0;
		double rCoeff =0;
		int currentDepth = cDepth+1;
		Vector3D colour = Vector3D.ZERO;
		Vector3D baseColour = Vector3D.ZERO;
		Vector3D reflectColour = Vector3D.ZERO;
		Vector3D light;
		Vector3D lightColour = Vector3D.ZERO;
		Vector3D intercept = null;
		Vector3D reflect;
		for (int i =0; i < scene.getScenery().size(); i++){
			tempDist = scene.getScenery().get(i).collision(ray);
			//System.out.print(tempDist);
			if(tempDist < dist){
				baseColour = scene.getScenery().get(i).getColour();
				dist = tempDist;
				object = i; //needed so we can get normal later
				//temp debugging
			}
		}
		//trace to light source
		if (dist < Double.MAX_VALUE){
			intensity = 0;
			lightColour = Vector3D.ZERO;
			for(int i = 0; i < scene.getLight().size(); i++){
				intercept = new Vector3D(1,ray.getOrigin(), dist, ray.getDirection());
				light = scene.getLight().get(i).getOrigin().subtract(intercept);
				light = light.normalize();
				tempIntensity = Vector3D.dotProduct(light, scene.getScenery().get(object).normal(intercept));
				//debugging
				//System.out.println(tempIntensity);
				
				
				if(tempIntensity > 0){
					 intensity = intensity + tempIntensity;
					 lightColour = lightColour.add(tempIntensity,scene.getLight().get(i).getColour());}
					 //adding minor ambient light 
			}
						
			colour = new Vector3D(lightColour.getX()*baseColour.getX(),lightColour.getY()*baseColour.getY(),lightColour.getZ()*baseColour.getZ());
//System.out.println(colour.getZ());
		//	colour = colour.scalarMultiply(intensity);
					//account for secondary reflected rays
		reflect = ray.getDirection().subtract(2*Vector3D.dotProduct(ray.getDirection(),scene.getScenery().get(object).normal(intercept)),scene.getScenery().get(object).normal(intercept));
		if(currentDepth < maxDepth){
			rCoeff = scene.getScenery().get(object).getReflect();
			if(rCoeff >0){
			reflectColour = Trace(new Ray(intercept.add(EPSILON,reflect), reflect), scene, currentDepth,maxDepth);
			//debugging since can't call print here
			//System.out.print("P3\n# CREATOR: GIMP PGM Filter Version 1.1\n" + 600 + " " + 600 + "\n" + 65535 + "\n");
			//System.out.println((int)(reflectColour.getX()*65535) + " " +(int)(65535*reflectColour.getY()) + " " + (int)(65535*reflectColour.getZ()));
			/*experimenting with colour mixing*/
			//colour = new Vector3D(colour.getX()+colour.getX()*rCoeff*reflectColour.getX(),colour.getY()+colour.getY()*rCoeff*reflectColour.getY(),colour.getZ()+colour.getZ()*rCoeff*reflectColour.getZ());
			reflectColour = new Vector3D(baseColour.getX()*reflectColour.getX(),baseColour.getY()*reflectColour.getY(),baseColour.getZ()*reflectColour.getZ());
			colour = new Vector3D(1,colour,rCoeff,reflectColour);
		}

		}
	}
		return colour;
	}
}
				
