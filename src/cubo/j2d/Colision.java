package cubo.j2d;


import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
//import CuboConntrucao.BaseCubo;

public class Colision extends Cube {

    private GL2 g;
    
    float cube1Mov = -8.0f;
    float cubo1Desl1 = -8.0f;
    float cube2Mov = 8.0f;
    float cubo2Desl2 = 8.0f;
    
    float cube1Inc =  0.09f;
    float cube2Inc =  0.09f;
    
    static boolean vai = true;
    static boolean vai2 = true;

    public Colision() {
        super();
        
    }

    public void display(GLAutoDrawable drawable) {
       g = drawable.getGL().getGL2();
        g.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        //cubo1(cubo1Desl);
        //cubo2(cubo2Desl);
        cube1(cube1Mov);
        cube2(cube2Mov);
        
        movimentCube1(vai);
        movimentCube2(vai2);
        
        if(cube1Mov >= 9.0f){
            vai = false;
        }
        if(cube2Mov <= -9.0f){
            vai2 = false;
        }
        if(cube1Mov <= -9.0f){
            vai = true;
        }
        if(cube2Mov >= 9.0f){
            vai2 = true;
        }
        //************************************
        if(cube1Mov >= -0.8f){
            vai = false;
        }
        if(cube2Mov <= 0.8f){
            vai2 = false;
        }
        
   
    }

    public void cube1(float moviment) {
        
        g.glLoadIdentity();                 
        g.glTranslatef(moviment, 0.0f, -15.0f);  

        g.glBegin(GL2.GL_QUADS);                
        
       g.glColor3f( 0f,0f,1f ); //blue color
      g.glVertex3f(-1, -1, 1); // Top Right Of The Quad (Top)
      g.glVertex3f( 1, -1, 1); // Top Left Of The Quad (Top)
      g.glVertex3f( 1, 1, 1 ); // Bottom Left Of The Quad (Top)
      g.glVertex3f( -1, 1, 1 ); // Bottom Right Of The Quad (Top)
		
      g.glColor3f( 0f,1f,0f ); //green color
      g.glVertex3f( -1.0f, 1.0f, 1.0f ); // Top Right Of The Quad
      g.glVertex3f( 1.0f, 1.0f, 1.0f ); // Top Left Of The Quad
      g.glVertex3f( 1.0f, 1.0f, -1.0f ); // Bottom Left Of The Quad
      g.glVertex3f( -1.0f, 1.0f, -1.0f ); // Bottom Right Of The Quad 

     
       g.glColor3f(1f,0f,0f); //red color
      g.glVertex3f( -1.0f, -1.0f, 1.0f ); // Top Right Of The Quad (Front)
      g.glVertex3f( -1.0f, 1.0f, 1.0f ); // Top Left Of The Quad (Front)
      g.glVertex3f( -1.0f, 1.0f, -1.0f ); // Bottom Left Of The Quad
      g.glVertex3f( -1.0f, -1.0f, -1.0f ); // Bottom Right Of The Quad 

      g.glColor3f( 1f,1f,0f ); //yellow (red + green)
      g.glVertex3f( 1.0f, 1.0f, -1.0f ); // Bottom Left Of The Quad
      g.glVertex3f( 1.0f, 1.0f, 1.0f ); // Bottom Right Of The Quad
      g.glVertex3f( 1.0f, -1.0f, 1.0f ); // Top Right Of The Quad (Back)
      g.glVertex3f( 1.0f, -1.0f, -1.0f ); // Top Left Of The Quad (Back)
//
      g.glColor3f( 1f,0f,1f ); //purple (red + green)
      g.glVertex3f( -1.0f, -1.0f, 1.0f ); // Top Right Of The Quad (Left)
      g.glVertex3f( -1.0f, -1.0f, -1.0f ); // Top Left Of The Quad (Left)
      g.glVertex3f( 1.0f, -1.0f, -1.0f ); // Bottom Left Of The Quad
      g.glVertex3f( 1.0f, -1.0f, 1.0f ); // Bottom Right Of The Quad 

      g.glColor3f( 0f,1f, 1f ); //sky blue (blue +green)
      g.glVertex3f( -1.0f, 1.0f, -1.0f ); // Top Right Of The Quad (Right)
      g.glVertex3f( 1.0f, 1.0f, -1.0f ); // Top Left Of The Quad
      g.glVertex3f( 1.0f, -1.0f, -1.0f ); // Bottom Left Of The Quad
      g.glVertex3f( -1.0f, -1.0f, -1.0f ); // Bottom Right Of The Quad
        
        g.glEnd();  
        
        //cubo1Desl += cubo1Inc;   
    }
    
    public void movimentCube1(boolean cube1){
        if(cube1){
            cube1Mov += cube1Inc;
        }else{
            cube1Mov -= cube1Inc;
        }
    }
    
    public void movimentCube2(boolean cube2){
        if(cube2){
            cube2Mov -= cube2Inc;
        }else{
            cube2Mov += cube2Inc;
        }
    }
    
    public void cube2(float moviment) {
        
        g.glLoadIdentity();                 
        g.glTranslatef(moviment, 0.0f, -15.0f);  

        g.glBegin(GL2.GL_QUADS);                
        
        g.glColor3f(0.0f, 1.0f, 0.0f);     
        g.glVertex3f(1.0f, 1.0f, -1.0f);
        g.glVertex3f(-1.0f, 1.0f, -1.0f);
        g.glVertex3f(-1.0f, 1.0f, 1.0f);
        g.glVertex3f(1.0f, 1.0f, 1.0f);

        // Bottom face 
        g.glColor3f(1.0f, 0.5f, 0.0f);     
        g.glVertex3f(1.0f, -1.0f, 1.0f);
        g.glVertex3f(-1.0f, -1.0f, 1.0f);
        g.glVertex3f(-1.0f, -1.0f, -1.0f);
        g.glVertex3f(1.0f, -1.0f, -1.0f);

        // Front face  
        g.glColor3f(1.0f, 0.0f, 0.0f);     
        g.glVertex3f(1.0f, 1.0f, 1.0f);
        g.glVertex3f(-1.0f, 1.0f, 1.0f);
        g.glVertex3f(-1.0f, -1.0f, 1.0f);
        g.glVertex3f(1.0f, -1.0f, 1.0f);

        // Back face 
        g.glColor3f(1.0f, 1.0f, 0.0f);     
        g.glVertex3f(1.0f, -1.0f, -1.0f);
        g.glVertex3f(-1.0f, -1.0f, -1.0f);
        g.glVertex3f(-1.0f, 1.0f, -1.0f);
        g.glVertex3f(1.0f, 1.0f, -1.0f);

        // Left face 
        g.glColor3f(0.0f, 0.0f, 1.0f);     
        g.glVertex3f(-1.0f, 1.0f, 1.0f);
        g.glVertex3f(-1.0f, 1.0f, -1.0f);
        g.glVertex3f(-1.0f, -1.0f, -1.0f);
        g.glVertex3f(-1.0f, -1.0f, 1.0f);

        // Right face 
        g.glColor3f(1.0f, 0.0f, 1.0f);     
        g.glVertex3f(1.0f, 1.0f, -1.0f);
        g.glVertex3f(1.0f, 1.0f, 1.0f);
        g.glVertex3f(1.0f, -1.0f, 1.0f);
        g.glVertex3f(1.0f, -1.0f, -1.0f);
        
        g.glEnd();  
        
        //cubo2Desl -= cubo1Inc;
    }

//    public static void main(String[] args) {
//        Colision c = new Colision();
//    }

    public void registerAllKeyActions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
