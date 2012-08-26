import org.apache.commons.math.geometry.*;

/** basic ray class, currently allows an arbitrary double for origin, of which only
 * xyz will be used, enforce array size later
 */
public class Light{
	Vector3D origin;
	Vector3D colour;
		public Light(Vector3D origin, Vector3D colour){
		this.origin = origin;
		this.colour = colour;
	}
	public Vector3D getOrigin(){
		return origin;}
	public Vector3D getColour(){
		return colour;}
}
	
