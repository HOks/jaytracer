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
	 */
	/*
	@Test public void wellBehavedInitializeArray(){
		screen = new Screen(new Vector3D(-3, -3, 1),new Vector3D(1,0,0) , 6 ,new Vector3D(0,1,0), 6);
		Ray[][] rays = rayTracer.initializeRays(0,0,0,screen,600,600);
		assertArrayEquals(rays.length,600);
	}
	*/
	 /* ray origin on screen
	  */
	  @Test public void screenOrigin(){
		screen = new Screen(new Vector3D(-3, -3, 0),new Vector3D(1,0,0) , 6 ,new Vector3D(0,1,0), 6);
		Ray[][] rays = rayTracer.initializeRays(0,0,0,screen,600,600);
		assertEquals(rays.length,600);
	}
	
	 /* origin parallel to screen
	  */
	  @Test public void screenParallel(){
		screen = new Screen(new Vector3D(-3, -3, 0),new Vector3D(1,0,0) , 6 ,new Vector3D(0,1,0), 6);
		Ray[][] rays = rayTracer.initializeRays(0,-4,0,screen,600,600);
		assertEquals(rays.length,600);
	}
	 /* null screen
	  */
	  @Test public void nullScreen(){
		screen = null;
		Ray[][] rays = rayTracer.initializeRays(0,0,0,screen,600,600);
		assertEquals(rays.length,0);
	  }
	 /* 0 resolution
	  */
	  @Test public void zeroRes(){
		screen = new Screen(new Vector3D(-3, -3, 1),new Vector3D(1,0,0) , 6 ,new Vector3D(0,1,0), 6);
		Ray[][] rays = rayTracer.initializeRays(0,0,0,screen,50,0);
		assertEquals(rays.length,0); //awkward, this setup results in an array of 50 zero length arrays
	  }
	 /* negative resolution
	 */
	@Test public void negativeRes(){
		screen = new Screen(new Vector3D(-3, -3, 1),new Vector3D(1,0,0) , 6 ,new Vector3D(0,1,0), 6);
		Ray[][] rays = rayTracer.initializeRays(0,0,0,screen,-50,-1);
		assertEquals(rays.length,0);
	  }
	/*todotests for trace
	* all
	*/
}
		
