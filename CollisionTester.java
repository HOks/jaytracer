/** class for testing collision behaviour for Shape3d implementations
*/
import org.apache.commons.math.geometry.Vector3D;
import org.junit.*;
import static org.junit.Assert.*;
public class CollisionTester{
	Shape3d sphere;
	Shape3d plane;
	Ray ray;
	public final double EPSILON = 0.00000001;
	//tests for Sphere implementation
	@Test public void collideSphere(){
		sphere = new Sphere(0,0,3, 1);
		ray = new Ray(new Vector3D(0,0,0), new Vector3D(0,0,1));
		double dist = sphere.collision(ray);
		assertEquals(2.0,dist,EPSILON);
	}
	@Test public void tangentSphere(){
		sphere = new Sphere(0,0,3, 1);
		ray = new Ray(new Vector3D(0,1,0), new Vector3D(0,0,1));
		double dist = sphere.collision(ray);
		assertEquals(3.0,dist,EPSILON);
	}

	@Test public void insideSphere(){
		sphere = new Sphere(0,0,0, 1);
		ray = new Ray(new Vector3D(0,0,0), new Vector3D(0,0,1));
		double dist = sphere.collision(ray);
		assertEquals(1.0,dist,EPSILON);
	}
	@Test public void missSphere(){
		sphere = new Sphere(0,0,3, 1);
		ray = new Ray(new Vector3D(0,0,0), new Vector3D(0,1,0));
		double dist = sphere.collision(ray);
		assertEquals(Double.MAX_VALUE,dist,EPSILON);
	}
	@Test public void sourceOnSurfaceSphere(){
		sphere = new Sphere(0,0,3, 1);
		ray = new Ray(new Vector3D(0,0,4), new Vector3D(0,1,0));
		double dist = sphere.collision(ray);
		assertEquals(0,dist,EPSILON);
	}
	//tests for Plane implementation

	@Test public void collidePlane(){
		plane = new Plane(new Vector3D(0,0,0), new Vector3D(1,0,0),50,new Vector3D(0,1,0),50);
		ray = new Ray(new Vector3D(1,1,2), new Vector3D(0,0,-1));
		double dist = plane.collision(ray);
		assertEquals(2.0,dist,EPSILON);
	}
	
	@Test public void edgePerpendicular(){
		plane = new Plane(new Vector3D(0,0,0), new Vector3D(1,0,0),50,new Vector3D(0,1,0),50);
		ray = new Ray(new Vector3D(0,0,2), new Vector3D(0,0,-1));
		double dist = plane.collision(ray);
		assertEquals(2.0,dist,EPSILON);
	}
	//this should not detect a collision as planes are infinitely thin. This may cause problems detecting collisions at corners of boxes.
	@Test public void edgeParallel(){
		plane = new Plane(new Vector3D(0,0,0), new Vector3D(1,0,0),50,new Vector3D(0,1,0),50);
		ray = new Ray(new Vector3D(-2,0,0), new Vector3D(1,0,0));
		double dist = plane.collision(ray);
		assertEquals(Double.MAX_VALUE,dist,EPSILON);
	}
	//this currently fails as it collides as though the plane extends further
	@Test public void miss(){
		plane = new Plane(new Vector3D(0,0,0), new Vector3D(1,0,0),50,new Vector3D(0,1,0),50);
		ray = new Ray(new Vector3D(54,1,1), new Vector3D(0,0,-1));
		double dist = plane.collision(ray);
		assertEquals(Double.MAX_VALUE,dist,EPSILON);
	}
	//this results in MAX, inconsistant with behaviour for sphere, should be defined in interface
	//or designated as unknown behaviour.
	@Test public void sourceOnSurface(){
		plane = new Plane(new Vector3D(0,0,0), new Vector3D(1,0,0),50,new Vector3D(0,1,0),50);
		ray = new Ray(new Vector3D(0,0,0), new Vector3D(0,1,0));
		double dist = plane.collision(ray);
		assertEquals(0,dist,EPSILON);
	}
	
}
