/**homebrew 3d shape interface for ray tracing
*/
import org.apache.commons.math.geometry.Vector3D;
public interface Shape3d{

//	public Shape3d(double[] dims, double[] position);
	/**Returns the distance from the origin of a ray to it's intercept
	 * with the Shape3d, returns Double.MAX_VALUE if the Shape3d does not
	 * intercept the ray, behaviour is not defined for if the origin of the
	 * ray is located on the surface of the Shape3d
	 * @param a, the ray to be intercepted
	 */
	public double collision(Ray a);
	/**Returns the position of the shape3d as a Vector3D
	 */
	public Vector3D getPosition();
	/**Returns the colour of the shape3d as a Vector3D
	 * x, y,z hold respective r,g,b values
	 */
	public Vector3D getColour();
	/**Returns the normal vector to the Shape3d at a given point
	 * @param point, an arbitrary point in 3d space
	 * Note: this method does not check that the point is actually on
	 * the surface of the Shape3d, and inconsistant behaviour may result
	 * if it is not
	 */
	public Vector3D normal(Vector3D point);
	/**returns the Transparance coefficient of the Shape3d*/
	public double getTransparence();
	/**returns the TReflection coefficient of the Shape3d*/
	public double getReflect();
//	public double setTransparence();
//	public double setReflect();
	}
