package cubo.j2d;

/*
 * To change this template, choose Tools | Templates
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
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author KamyluKuna
 */
public abstract class Cube implements GLEventListener {

    //*******************
    private static GraphicsEnvironment graphicsEnvironment;
    private static boolean isFullScreen = false;
    private static DisplayMode dm, dm_old;
    private static Dimension xgraphic;
    private static Point point = new Point(0, 0);

    private GLU glu = new GLU();

    private float rotacao = 0;

    private static InputMap inputMap;
    private static ActionMap actionMap;
    //*******************

    private static void keyBindings(JPanel panel, final JFrame jFrame, Cube base) {
        ActionMap actionMap = panel.getActionMap();
        InputMap inputMap = panel.getInputMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "F1");
        actionMap.put("F1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fullScreen(jFrame);
            }
        });
    }

    public abstract void registerAllKeyActions();

    private static void fullScreen(JFrame f) {
        if (!isFullScreen) {
            f.dispose();
            f.setUndecorated(true);
            f.setVisible(true);
            xgraphic = f.getSize();
            point = f.getLocation();
            f.setLocation(0, 0);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            f.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
            isFullScreen = true;
        } else {
            f.dispose();
            f.setUndecorated(false);
            f.setResizable(true);
            f.setLocation(point);
            f.setSize(xgraphic);
            f.setVisible(true);
            isFullScreen = false;
        }
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();

        if (height <= 0) {
            height = 1;
        }

        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        //glu.gluPerspective(100.0f, h, 0.9, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void registerKeyAction(Integer key, AbstractAction a) {
        inputMap.put(KeyStroke.getKeyStroke(key, 0), key.toString());
        actionMap.put(key.toString(), a);
    }

    public Cube() {
        // versao do openGL
        final GLProfile gLProfile = GLProfile.get(GLProfile.GL2);
        GLCapabilities gLCapabilities = new GLCapabilities(gLProfile);

        // THE Canvas - local que contem os objectos
        final GLCanvas gLCanvas = new GLCanvas(gLCapabilities);

        //Render render = new Render();
        gLCanvas.addGLEventListener(this);
        gLCanvas.setSize(900, 550);

        final FPSAnimator animator = new FPSAnimator(gLCanvas, 300, true);

        final JFrame jFrame = new JFrame("JOpenGL");
          jFrame.setLocationRelativeTo(null);
       
      
        jFrame.getContentPane().add(gLCanvas);
        

        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (animator.isStarted()) {
                    animator.stop();
                    //System.exit(0);
                }
            }
        });

        // Pedindo o size o glCanvas, pa definir como size do jframe
        jFrame.setSize(jFrame.getContentPane().getPreferredSize());

        //**********************************
        graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] devices = graphicsEnvironment.getScreenDevices();

        dm_old = devices[0].getDisplayMode();
        dm = dm_old;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // encontrando as coordenadas do centro
        int windowX = Math.max(0, (screenSize.width - jFrame.getWidth()) / 2);
        int windowY = Math.max(0, (screenSize.height - jFrame.getHeight()) / 2);
        jFrame.setLocation(windowX, windowY);

        jFrame.setVisible(true);
        //**********************************

        // adicionando um painel no frame
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 0));
        jFrame.add(panel, BorderLayout.SOUTH);

        keyBindings(panel, jFrame, this);
        animator.start();
    }
}
