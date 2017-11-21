/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
class Object4D{
     public float [][] points;
    }
 class World4D{
     public List<Object4D> objects= new ArrayList<Object4D>();
    }

public abstract class AppRandom implements GLEventListener{
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
    
   protected void drawFigure3f(int mode,float[][] figure){
        if(null==figure || (figure[0].length !=3)) return;
        Random r= new Random(1);
        g.glBegin(mode); // Start Drawing The Cube
        for(int i=0;i<figure.length;i++){
        g.glColor3f(r.nextFloat(),r.nextFloat(),r.nextFloat()); //red color
      g.glVertex3f(figure[i][0], figure[i][1],figure[i][2]); // Top Right Of The Quad (Top)
        }
      
      g.glEnd(); // Done Drawing The Quad
    
    }
    protected void drawFigureQuads3f(float[][] figure){
       
        drawFigure3f(GL2.GL_QUADS,figure); // Start Drawing The Cube
     
    }
    protected void drawWorld4DQuads3f(World4D world4d){
        for(int i=0;i<world4d.objects.size();i++){
            drawFigure3f(GL2.GL_QUADS,world4d.objects.get(i).points);
        }
    }
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
    public AppRandom(){
    initGLObjects();
    registerAllKeyActions();
    createWindow();
    fpsAnimator= new FPSAnimator(glcanvas,200,true);
    frame.setVisible(true);
    }
}
