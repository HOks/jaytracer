/**very simple driver to test raytracer
*/
import org.apache.commons.math.geometry.Vector3D;
import java.io.*;
public class RayDriver2{
	public static void main(String[] args){
		Scene scene = new Scene();
		RayTracer rayTracer = new RayTracer();
		int width =1;
		int height =1;
		int colourDepth =1;
		Screen screen = null;
		String nextString;
		String fileWrite = "default.ppm";
		double cameraX =0;
		double cameraY =0;
		double cameraZ = 0;
		try { //possible FileNotFoundException or IOException or IllegalArgumentException
	Reader r = new FileReader(args[0]); 
	StreamTokenizer st = new StreamTokenizer(r);
	width = (int) nextNumber(st);
	height = (int) nextNumber(st);
	colourDepth = (int) nextNumber(st);
	cameraX =  nextNumber(st);
	cameraY =  nextNumber(st);
	cameraZ =  nextNumber(st);
	screen = new Screen(new Vector3D(nextNumber(st),nextNumber(st),nextNumber(st)),new Vector3D(nextNumber(st),nextNumber(st),nextNumber(st)),nextNumber(st),new Vector3D(nextNumber(st),nextNumber(st),nextNumber(st)),nextNumber(st));
	while(st.ttype != st.TT_EOF){
		nextString = nextWord(st);
		if(nextString.equals("Sphere"))
			scene.addShape(new Sphere(nextNumber(st),nextNumber(st),nextNumber(st),nextNumber(st),nextNumber(st),nextNumber(st),nextNumber(st),nextNumber(st),nextNumber(st)));
		else if(nextString.equals("Plane"))
			scene.addShape(new Plane(new Vector3D(nextNumber(st),nextNumber(st),nextNumber(st)),new Vector3D(nextNumber(st),nextNumber(st),nextNumber(st)),nextNumber(st),new Vector3D(nextNumber(st),nextNumber(st),nextNumber(st)),nextNumber(st), nextNumber(st), nextNumber(st), nextNumber(st)));
		else if(nextString.equals("Light"))
			scene.addLight(new Light(new Vector3D(nextNumber(st),nextNumber(st),nextNumber(st)),new Vector3D(nextNumber(st),nextNumber(st),nextNumber(st))));
		else fileWrite = nextString;
	}
}catch(Exception e){e.printStackTrace(); }//define better behaviour for separate exceptions later
		Vector3D[][] display;
/*		int width = 600;
		int height = 600;
		int colourDepth = 65535;
		Screen screen = new Screen(new Vector3D(-3,-3,1), new Vector3D(1,0,0), 6, new Vector3D(0,1,0),6);
		//scene.addShape(new Sphere(-3,0,3, 2,665355,0,0));
		scene.addShape(new Sphere(1.0,-0.8,3.0, 2.5,1,1,1,0.6,0.6));
		scene.addShape(new Sphere(-5.5,-0.5,7, 2,0.07,0.07,0.1,1,1));
		scene.addShape(new Sphere(-2,3,5,1.5,0.3,0.9,0.2,0.5,0.5));
		scene.addShape(new Plane(new Vector3D(-1000,10,-1000),new Vector3D(1,0,0), Integer.MAX_VALUE, new Vector3D(0,0,1), Integer.MAX_VALUE,0.4,0.3,0.3));
		scene.addLight(new Light(new Vector3D(0,-5,5), new Vector3D(0.6,0.6,0.6)));
		scene.addLight(new Light(new Vector3D(2,-5,1), new Vector3D(0.7,0.7,0.9)));
		*/
		Ray[][] rays = rayTracer.initializeRays(cameraX,cameraY,cameraZ,screen,width,height);
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
		print(display,fileWrite, width, height, colourDepth);
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

	static String nextWord(StreamTokenizer st)throws IllegalArgumentException, IOException{
	String word;
	while(st.ttype != st.TT_EOF){
	    if (st.ttype == st.TT_WORD || st.ttype =='"' || st.ttype =='\''){
		word = st.sval;
		st.nextToken();
		return word;}
	    st.nextToken();
	}throw new IllegalArgumentException();
    }
static double nextNumber(StreamTokenizer st)throws IllegalArgumentException, IOException{
	double number;
	while(st.ttype != st.TT_EOF){
	    if (st.ttype == st.TT_NUMBER){
		number = st.nval;
		st.nextToken();
		return number;}
	    st.nextToken();
	}throw new IllegalArgumentException();

    }
}
		
