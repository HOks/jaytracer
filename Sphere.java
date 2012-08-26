/** schere class for basic raytracer testing
* default colour = white, default transparence and reflect = 0
*/
import java.lang.Math;
import org.apache.commons.math.geometry.Vector3D;
public class Sphere implements Shape3d{
	Vector3D position;
	double radius;
	Vector3D colour = new Vector3D(255,255,255);
	double transparence = 0;
	double reflect = 0;
	/** creates a new sphere at co-ordinates x,y,z radius r*/ 
	public Sphere(double x, double y, double z, double radius){
		this(x,y,z,radius, 255,255,255,0,0);
	}
	//	public Shape3d(double[] dims, double[] position);
	/** returns distance along ray to point of collision (if one exists) as a double
	 * returns MAX_VALUE if no intersection
	 * */
	public Sphere(double x, double y, double z, double radius, double r, double g, double b, double t, double ref){
		this.radius =  radius;
		position = new Vector3D(x,y,z);
		colour = new Vector3D(r,g,b);
		if(t >1) transparence = 1;
		else if (t < 0) transparence =0;
		else transparence = t;
		if(ref > 1) reflect = 1;
		else if(ref <0) reflect = 0;
		else reflect = ref;
	}
	public double collision(Ray ray){
		//setting up values for quad formula
		double t0;
		double t1;
		double t = Double.MAX_VALUE;
		Vector3D oMinC = (ray.getOrigin().subtract(position));
		double a = Vector3D.dotProduct(ray.getDirection(),ray.getDirection());
		double b = 2*Vector3D.dotProduct(oMinC,ray.getDirection());
		double c = Vector3D.dotProduct(oMinC,oMinC) - radius*radius;
		double discriminant = b*b -4*a*c;
		if (discriminant >= 0){//there is an intercept
			t0 = -b-Math.sqrt(discriminant);
			t1 = -b+Math.sqrt(discriminant);
			t0 = t0/(2*a);
			t1 = t1/(2*a);
			//take the smallest non-negative t
			t = t0;
			if(t <0) t=t1;
			if(t < 0){
				t = Double.MAX_VALUE;
			}
		}
		return t;
	}
	public Vector3D getPosition(){
		return position;}
		/**returns normalized normal vector to surface at point, no checking that this is actually a collision point
		 */
	public Vector3D normal(Vector3D point){
		Vector3D norm = point.subtract(position);
		norm = norm.normalize();
		return norm;
	}
	public Vector3D getColour(){
		return colour;
	}
	public double getTransparence(){
		return transparence;
	}
	public double getReflect(){
		return reflect;
	}
}
