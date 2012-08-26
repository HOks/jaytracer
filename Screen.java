/** object representing the display for modelling raytracing
 * warning: there is nothing forcing orthogonality, so you may end up with a 1D screen
 * (or OD in the trivial case)
*/
import org.apache.commons.math.geometry.Vector3D;
public class Screen{
	Vector3D origin;
	Vector3D v1;
	Vector3D v2;
	double l1;
	double l2;
	public Screen(Vector3D origin, Vector3D v1, double l1, Vector3D v2, double l2){
		this.origin = origin;
		this.v1 = v1.normalize();
		this.v2 = v2.normalize();
		this.l1 = l1;
		this.l2 = l2;
	}
	public Vector3D getOrigin(){ return origin;}
	public Vector3D getV1(){ return v1;}
	public Vector3D getV2(){ return v2;}
	public double getL1(){ return l1;}
	public double getL2(){ return l2;}
}
