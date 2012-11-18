/**Unit Testing for RayTracer.java
 */
import org.apache.commons.math.geometry.Vector3D;
import org.junit.*;
import static org.junit.Assert.*;
public class RayTracerTester{
	RayTracer rayTracer = new RayTracer();
	Screen screen;
	//initialize with simple, well behaved parameters
	@Test public void wellBehavedInitialize(){
		screen = new Screen(new Vector3D(-3, -3, 1),new Vector3D(1,0,0) , 6 ,new Vector3D(0,1,0), 6);
		Ray[][] rays = rayTracer.initializeRays(0,0,0,screen,600,600);
		assertEquals(rays.length,600);
	}
	/*todo tests for initializeRays
	 * simple case where we can assertarrayequals for the well behaved case
	 * origin on screen
	 * origin parallel to screen
	 * null screen
	 * 0 resolution
	 * negative resolution
	 */
	/*todotests for trace
	* all
	*/
}
		
