/** creates a 3d box, implements shape3D.
 * a slight mess since vector3d doesn't support non-rotation matrices
 * or projections directly
*/
import java.lang.Math;
import org.apache.commons.math.geometry.Vector3D;
import org.apache.commons.math.linear.ArrayRealVector;
public class Plane implements Shape3d{
	Vector3D position;
	Vector3D v1;
	Vector3D v2;
	double l1;
	double l2;

	Vector3D colour = new Vector3D(255,255,255);
	double transparence = 0;
	double reflect = 0;
	/** creates a new sphere at co-ordinates x,y,z radius r*/ 
	public Plane(Vector3D origin, Vector3D v1, double l1, Vector3D v2, double l2){
		this(origin, v1, l1, v2, l2, 255, 255, 255);
	}
	//	public Shape3d(double[] dims, double[] position);
	/** returns distance along ray to point of collision (if one exists) as a double
	 * returns MAX_VALUE if no intersection
	 * */
	public Plane(Vector3D position, Vector3D v1, double l1, Vector3D v2, double l2, double r, double g, double b){
		this.position = position;
		this.v1 = v1.normalize();
		this.v2 = v2.normalize();
		this.l1 = l1;
		this.l2 = l2;
		colour = new Vector3D(r,g,b);
	}
	public double collision(Ray ray){
		//setting up values for quad formula
		double t0;
		double t1;
		double tr;
		/*
		ArrayRealVector rayDir = new ArrayRealVector(ray.getDirection().getX(),ray.getDirection().getY(),ray.getDirection().getZ());
		ArrayRealVector p1 = new ArrayRealVector(v1.getX(),v1.getY(),v1.getZ());
		ArrayRealVector p2 = new ArrayRealVector(v2.getX(),v2.getY(),v2.getZ());
		ArrayRealVector p0 = new ArrayRealVector(position.getX(),position.getY(),position.getZ());
		ArrayRealVector r1 = rayDir.projection(p1);
		ArrayRealVector r2 = rayDir.projection(p2);
		* */
		double t = Double.MAX_VALUE;
		double scalefactor = Vector3D.dotProduct(this.normal(position),ray.getDirection());
		if(scalefactor != 0){
			
		Vector3D oMinC = position.subtract(ray.getOrigin());
		t = Vector3D.dotProduct(this.normal(position),oMinC)/scalefactor;
		
		}
		if (t <0) t = Double.MAX_VALUE;
		return t;
	}
	public Vector3D getPosition(){
		return position;}
		/**returns normalized normal vector to surface at point, no checking that this is actually a collision point
		 */
	public Vector3D normal(Vector3D point){
		Vector3D norm = Vector3D.crossProduct(v1,v2);
		if(Vector3D.dotProduct(point,norm) >0) norm = norm.scalarMultiply(-1);
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
