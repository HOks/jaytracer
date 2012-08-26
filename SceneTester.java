/** manually tests Scene by placing it's array of objects intoa  branchgroup and compiling
* for display through basic java3dtools
* doesn't work, stuffit, no testing, straight to tracing
*/

import java.util.ArrayList;
import javax.media.j3d.*;
public class SceneTester{
	private SceneTester(){}
	public static BranchGroup example(){
		ExampleScene example = new ExampleScene();
		BranchGroup bg = new BranchGroup();
		ArrayList<TransformGroup> scenery = example.getScenery();
		for(int i =0; i < scenery.size(); i++){
			bg.addChild(scenery.get(i));
			}
					bg.addChild(new AmbientLight());
		//bg.compile();
		return bg;
	}
}
		
