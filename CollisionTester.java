import org.apache.commons.math.geometry.Vector3D;
public class CollisionTester{
	public static void main(String[] args){
		Sphere sphere = new Sphere(0,0,3, 1);
		Ray ray = new Ray(new Vector3D(0,1,0), new Vector3D(0,0,1));
		double dist = sphere.collision(ray);
		System.out.print(dist);
	}
}
