/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.sun.glass.events.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author mario
 */
public class AppRand extends AppRandom{
//    private float anglePyramid = 0.0f;  // Rotational angle for pyramid [NEW]
private float angleCube = 0.0f;     // Rotational angle for cube [NEW]

//private float color[][]={
//    {1f,0f,0f},
//    {0f,1f,0f},
//    {0f,0f,1f},
//    {1f,1f,0f},
//    {0f,1f,1f},
//    {1f,0f,1f}
//};
private float figure[][]={
    {-1,-1,1},
    {1,-1,1},
    {1,1,1},
    {-1,1,1},
    
  
    { -1.0f, 1.0f, 1.0f }, // Top Right Of The Quad
    { 1.0f, 1.0f, 1.0f }, // Top Left Of The Quad
    {1.0f, 1.0f, -1.0f }, // Bottom Left Of The Quad
    {-1.0f, 1.0f, -1.0f}, // Bottom Right Of The Quad 

 
    {-1.0f, -1.0f, 1.0f }, // Top Right Of The Quad (Front)
    {-1.0f, 1.0f, 1.0f }, // Top Left Of The Quad (Front)
    {-1.0f, 1.0f, -1.0f }, // Bottom Left Of The Quad
    {-1.0f, -1.0f, -1.0f }, // Bottom Right Of The Quad 


    { 1.0f, 1.0f, -1.0f }, // Bottom Left Of The Quad
    {1.0f, 1.0f, 1.0f }, // Bottom Right Of The Quad
    {1.0f, -1.0f, 1.0f }, // Top Right Of The Quad (Back)
    {1.0f, -1.0f, -1.0f }, // Top Left Of The Quad (Back)
////
   
        { -1.0f, -1.0f, 1.0f }, // Top Right Of The Quad (Left)
        { -1.0f, -1.0f, -1.0f }, // Top Left Of The Quad (Left)
        {1.0f, -1.0f, -1.0f }, // Bottom Left Of The Quad
        { 1.0f, -1.0f, 1.0f }, // Bottom Right Of The Quad 

    
        { -1.0f, 1.0f, -1.0f }, // Top Right Of The Quad (Right)
        {1.0f, 1.0f, -1.0f }, // Top Left Of The Quad
        {1.0f, -1.0f, -1.0f }, // Bottom Left Of The Quad
        {-1.0f, -1.0f, -1.0f }// Bottom Right Of The Quad
};
    
    public static void main( String[] args ) {
    AppRand app=new AppRand();
    app.start();
    
    }

   
    public void registerAllKeyActions() {
//      registerKeyAction(KeyEvent.VK_UP,new AbstractAction(){
//private static final long serialVersionUID = 1L;
//          public void actionPerformed(ActionEvent e) {
//             xrotation+=3;
//          }
//      
//      
//      });
    }

 
    public void display(GLAutoDrawable glad) {
        clearCanvas();
         g.glTranslatef(0.0f, 0.0f, -6.6f);  // Move right and into the screen
   g.glRotatef(angleCube, 1.0f, 1.0f, 1.0f);  // Rotate about (1,1,1)-axis [NEW]

//      drawFigureQuads3f(figure);
   World4D world4D= new World4D();
   Object4D object4d= new Object4D();
   object4d.points=figure;
   world4D.objects.add(object4d);
   
   object4d= new Object4D();
   object4d.points= new float[figure.length][figure[0].length];
   for(int i=0;i<figure.length;i++){
   for(int j=0;j<figure[i].length;j++){
   object4d.points[i][j]= figure[i][j]/2;
       if (j==2) {
           object4d.points[i][j]-=2;
       }
   }
   }
   world4D.objects.add(object4d);
   drawWorld4DQuads3f(world4D);
      
//      g.glBegin(GL2.GL_QUADS); // Start Drawing The Cube
//      g.glColor3f(1f,0f,0f); //red color
//      g.glVertex3f(-1, -1, 1); // Top Right Of The Quad (Top)
//      g.glVertex3f( 1, -1, 1); // Top Left Of The Quad (Top)
//      g.glVertex3f( 1, 1, 1 ); // Bottom Left Of The Quad (Top)
//      g.glVertex3f( -1, 1, 1 ); // Bottom Right Of The Quad (Top)
//		
//      g.glColor3f( 0f,1f,0f ); //green color
//      g.glVertex3f( -1.0f, 1.0f, 1.0f ); // Top Right Of The Quad
//      g.glVertex3f( 1.0f, 1.0f, 1.0f ); // Top Left Of The Quad
//      g.glVertex3f( 1.0f, 1.0f, -1.0f ); // Bottom Left Of The Quad
//      g.glVertex3f( -1.0f, 1.0f, -1.0f ); // Bottom Right Of The Quad 
//
//      g.glColor3f( 0f,0f,1f ); //blue color
//      g.glVertex3f( -1.0f, -1.0f, 1.0f ); // Top Right Of The Quad (Front)
//      g.glVertex3f( -1.0f, 1.0f, 1.0f ); // Top Left Of The Quad (Front)
//      g.glVertex3f( -1.0f, 1.0f, -1.0f ); // Bottom Left Of The Quad
//      g.glVertex3f( -1.0f, -1.0f, -1.0f ); // Bottom Right Of The Quad 
//
//      g.glColor3f( 1f,1f,0f ); //yellow (red + green)
//      g.glVertex3f( 1.0f, 1.0f, -1.0f ); // Bottom Left Of The Quad
//      g.glVertex3f( 1.0f, 1.0f, 1.0f ); // Bottom Right Of The Quad
//      g.glVertex3f( 1.0f, -1.0f, 1.0f ); // Top Right Of The Quad (Back)
//      g.glVertex3f( 1.0f, -1.0f, -1.0f ); // Top Left Of The Quad (Back)
////
//      g.glColor3f( 1f,0f,1f ); //purple (red + green)
//      g.glVertex3f( -1.0f, -1.0f, 1.0f ); // Top Right Of The Quad (Left)
//      g.glVertex3f( -1.0f, -1.0f, -1.0f ); // Top Left Of The Quad (Left)
//      g.glVertex3f( 1.0f, -1.0f, -1.0f ); // Bottom Left Of The Quad
//      g.glVertex3f( 1.0f, -1.0f, 1.0f ); // Bottom Right Of The Quad 
//
//      g.glColor3f( 0f,1f, 1f ); //sky blue (blue +green)
//      g.glVertex3f( -1.0f, 1.0f, -1.0f ); // Top Right Of The Quad (Right)
//      g.glVertex3f( 1.0f, 1.0f, -1.0f ); // Top Left Of The Quad
//      g.glVertex3f( 1.0f, -1.0f, -1.0f ); // Bottom Left Of The Quad
//      g.glVertex3f( -1.0f, -1.0f, -1.0f ); // Bottom Right Of The Quad
//   
//      g.glEnd(); // Done Drawing The Quad
      g.glFlush();
//       anglePyramid += 0.2f;
   angleCube -= 0.15f;
//		
//      rquad -= 0.15f;
    }

  
    
}
