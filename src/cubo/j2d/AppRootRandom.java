/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cubo.j2d;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author mario
 */
public abstract class AppRootRandom implements GLEventListener{
    protected GL2 g;
    private static GLProfile profile;
    private static GLCapabilities capabilities;
    private static FPSAnimator fpsAnimator;
    private static GLCanvas glcanvas;
    private JFrame frame;
    private JPanel panel;
    private GLU glu = new GLU();
    
    private static ActionMap actionMap;
    private static InputMap inputMap;
    
    public abstract void registerAllKeyActions();
    public abstract void display(GLAutoDrawable glad);
   
    
    private void initGLObjects(){
    profile= GLProfile.get(GLProfile.GL2);
    capabilities=new GLCapabilities(profile);
    }
    
    private void createWindow(){
    glcanvas= new GLCanvas(capabilities);
    glcanvas.addGLEventListener(this);
    glcanvas.setSize(800, 600);
    
    frame=new JFrame("Cubo Multicolor");
    frame.getContentPane().add(glcanvas);
    frame.setSize(frame.getContentPane().getPreferredSize());
    
        Dimension d =Toolkit.getDefaultToolkit().getScreenSize();
        int x=(d.width-frame.getWidth())/2;
        int y=(d.height-frame.getHeight())/2;
        
        frame.setLocation(x, y);
        
        panel= new JPanel();
        panel.setPreferredSize(new Dimension(0, 0));
        frame.add(panel);
        actionMap=panel.getActionMap();
        inputMap=panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        
        frame.addWindowListener(new WindowAdapter() {
          public void WindowIsClosing(){
              if (fpsAnimator.isStarted()) {
                  fpsAnimator.stop();
              }
              System.exit(0);
          }
        
        });
        
    }
    public void registerKeyAction(Integer key,AbstractAction a){
    inputMap.put(KeyStroke.getKeyStroke(key,0), key.toString());
    actionMap.put(key.toString(), a);
    }
    public void start(){
    fpsAnimator.start();}
    
    public void clearCanvas(){
    g.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
    g.glLoadIdentity();
    }
    public void init(GLAutoDrawable d){
    g=d.getGL().getGL2();
    g.glShadeModel( GL2.GL_SMOOTH );
      g.glClearColor( 0, 0, 0, 0 );
      g.glClearDepth(1);
      g.glEnable( GL2.GL_DEPTH_TEST );
      g.glDepthFunc( GL2.GL_LEQUAL );
      g.glHint( GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST );
    }
      public void reshape( GLAutoDrawable d, int x, int y, int width, int height ) {
	
      g.glViewport( 0, 0, width, height );
      g.glMatrixMode( GL2.GL_PROJECTION );
      g.glLoadIdentity();
		
      glu.gluPerspective( 45f, (float) width/(float) height,1, 20);
      g.glMatrixMode( GL2.GL_MODELVIEW );
      g.glLoadIdentity();
   }
      public void dispose(GLAutoDrawable glad) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public AppRootRandom(){
    initGLObjects();
    registerAllKeyActions();
    createWindow();
    fpsAnimator= new FPSAnimator(glcanvas,200,true);
    frame.setVisible(true);
    }
}
