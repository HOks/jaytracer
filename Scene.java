/** encapsulates all geometric objects inthe scene to be traced,
 * takes input as transformgroups describing groups of positioned objects
 * currently only supports point light sources of uniform intensity
*/

import java.util.ArrayList;
import org.apache.commons.math.geometry.Vector3D;
public class Scene{
	ArrayList<Shape3d> scenery;
	ArrayList<Light> light;
	public Scene(){
		scenery = new ArrayList<Shape3d>();
		light = new ArrayList<Light>();
		}
	public void addShape(Shape3d a){
		scenery.add(a);
		}
	public void addLight(Light a){
		light.add(a);
	}
	public ArrayList<Shape3d> getScenery(){
		return scenery;
	}
	public ArrayList<Light> getLight(){
		return light;
	}
}
