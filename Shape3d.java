/**homebrew 3d shape interface for ray tracing
*/
import org.apache.commons.math.geometry.Vector3D;
public interface Shape3d{

//	public Shape3d(double[] dims, double[] position);
	public double collision(Ray a);
	public Vector3D getPosition();
	public Vector3D getColour();
	public Vector3D normal(Vector3D point);
	public double getTransparence();
	public double getReflect();
//	public double setTransparence();
//	public double setReflect();
	}
