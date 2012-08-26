/** example well behaved scene for testing
 *
*/
import com.sun.j3d.utils.geometry.*;
import java.util.ArrayList;
import javax.media.j3d.*;
import javax.vecmath.Vector3d;
import javax.vecmath.Point3d;
public class ExampleScene extends Scene{
	ArrayList<TransformGroup> scenery = new ArrayList<TransformGroup>();
	/**creates a predetemined scene of 2 spheres
	 */
	public ExampleScene(){
		TransformGroup tg = new TransformGroup();
		Transform3D transform = new Transform3D();
		this.scenery = new ArrayList<TransformGroup>();
		Primitive p = new Sphere(6);
		p.setAppearance();
		Vector3d translate = new Vector3d(-1,0,0);
		transform.setTranslation(translate);
		tg.setTransform(transform);
		tg.addChild(p);
		scenery.add(tg);
		p = new Sphere(6);
		p.setAppearance();
		translate = new Vector3d(0,0,0);
		transform.setTranslation(translate);
		tg = new TransformGroup();
		tg.setTransform(transform);
		tg.addChild(p);
		scenery.add(tg);
		light = new Point3d[1];
		light[0] = new Point3d(0,20,0);

		//scenery.add(new Box(50,50,50, new Appearance()));
		}
	
	public void addPrimitive(Primitive a){}
		public ArrayList<TransformGroup> getScenery(){
		return scenery;
	}
}
