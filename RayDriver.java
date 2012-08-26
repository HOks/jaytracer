/**very simple driver to test raytracer
*/
import org.apache.commons.math.geometry.Vector3D;
import java.io.*;
public class RayDriver{
	public static void main(String[] args){
		Scene scene = new Scene();
		RayTracer rayTracer = new RayTracer();
		String filename = "jaytr2.ppm";
		Vector3D[][] display;
		int width = 600;
		int height = 600;
		int colourDepth = 65535;
		Screen screen = new Screen(new Vector3D(-3,-3,1), new Vector3D(1,0,0), 6, new Vector3D(0,1,0),6);
		//scene.addShape(new Sphere(-3,0,3, 2,665355,0,0));
		scene.addShape(new Sphere(1.0,-0.8,3.0, 2.5,1,1,1,0.6,0.6));
		scene.addShape(new Sphere(-5.5,-0.5,7, 2,0.07,0.07,0.1,1,1));
		scene.addShape(new Plane(new Vector3D(-1000,10,-1000),new Vector3D(1,0,0), Integer.MAX_VALUE, new Vector3D(0,0,1), Integer.MAX_VALUE,0.4,0.3,0.3));
		scene.addLight(new Light(new Vector3D(0,-5,5), new Vector3D(0.6,0.6,0.6)));
		scene.addLight(new Light(new Vector3D(2,-5,1), new Vector3D(0.7,0.7,0.9)));
		Ray[][] rays = rayTracer.initializeRays(0,0,-5,screen,width,height);
		display = new Vector3D[rays.length][rays[0].length];
		for (int i =0; i < rays.length; i++){
			for(int j =0; j< rays[0].length; j++){
				//System.out.println(rays[i][j].getDirection().getX() + " " +rays[i][j].getDirection().getY() + " " + rays[i][j].getDirection().getZ());
				display[i][j] = rayTracer.Trace(rays[i][j], scene,0,5);
				//System.out.print(((int) display[i][j].getX()) + " ");
				//if(display[i][j].getX() > 0) System.out.print("1");
			}
			//System.out.println("");
		
		}
		print(display,filename, width, height, colourDepth);
	}
	public static void print(Vector3D[][] display, String filename, int width, int height, int colourDepth){
		//write to pnm, later change to pgm
		String header = "P3\n# CREATOR: GIMP PGM Filter Version 1.1\n" + width + " " + height + "\n" + colourDepth + "\n";
		try{
			FileWriter fstream = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(header);
			for (int j =0; j < display.length; j++){
				for(int i =0; i< display[0].length; i++){
					out.write((int) (display[i][j].getX()*colourDepth) + " " +(int) (display[i][j].getY()*colourDepth) + " " +(int) (display[i][j].getZ()*colourDepth));
					out.write("\n");
				}
			}
			out.close();
		}catch (Exception e){}
	}
}
		
