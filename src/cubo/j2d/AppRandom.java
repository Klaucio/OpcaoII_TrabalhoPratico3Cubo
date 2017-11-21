/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cubo.j2d;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.sun.glass.events.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author mario
 */
public class AppRandom extends AppRootRandom{
//    private float anglePyramid = 0.0f;  // Rotational angle for pyramid [NEW]
private float angleCube = 0.0f;     // Rotational angle for cube [NEW]

 
    
//    public static void main( String[] args ) {
//    AppRandom app=new AppRandom();
//    app.start();
//    
//    }

   
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

      
      
      g.glBegin(GL2.GL_QUADS); // Start Drawing The Cube
      g.glColor3f(1f,0f,0f); //red color
      g.glVertex3f(-1, -1, 1); // Top Right Of The Quad (Top)
      g.glVertex3f( 1, -1, 1); // Top Left Of The Quad (Top)
      g.glVertex3f( 1, 1, 1 ); // Bottom Left Of The Quad (Top)
      g.glVertex3f( -1, 1, 1 ); // Bottom Right Of The Quad (Top)
		
      g.glColor3f( 0f,1f,0f ); //green color
      g.glVertex3f( -1.0f, 1.0f, 1.0f ); // Top Right Of The Quad
      g.glVertex3f( 1.0f, 1.0f, 1.0f ); // Top Left Of The Quad
      g.glVertex3f( 1.0f, 1.0f, -1.0f ); // Bottom Left Of The Quad
      g.glVertex3f( -1.0f, 1.0f, -1.0f ); // Bottom Right Of The Quad 

      g.glColor3f( 0f,0f,1f ); //blue color
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
   
      g.glEnd(); // Done Drawing The Quad
      g.glFlush();
//       anglePyramid += 0.2f;
   angleCube -= 0.15f;
//		
//      rquad -= 0.15f;
    }

  
    
}
