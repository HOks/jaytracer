import org.apache.commons.math.geometry.*;

/** basic ray class, currently allows an arbitrary double for origin, of which only
 * xyz will be used, enforce array size later
 */
public class Ray{
	Vector3D origin;
	Vector3D direction;
		public Ray(Vector3D origin, Vector3D direction){
		this.origin = origin;
		this.direction = direction.normalize();
	}
	public Vector3D getOrigin(){
		return origin;}
	public Vector3D getDirection(){
		return direction;}
}
	
