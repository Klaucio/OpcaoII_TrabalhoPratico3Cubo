package cubo.j2d;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/* 
 * Clock analog
 */

public class ClockGui extends JFrame implements MouseListener,KeyListener{
	
	private static JFrame frame;
	private int status=0;
	private static final int spacing = 35;
	private static final float radPerSecMin = (float)(Math.PI / 30.0);
	private static final float radPerNum = (float)(Math.PI/ -6);
	private int size;
	private int centerX;
	private int centerY;
        private int countDraws=0;
	
	SimpleDateFormat sf;
	
	Calendar cal;
	int hour;
	int minute;
	int second;
        int tam=1;
	Color colorSecond,colorMHour,colorNumber;
	
	Timer timer;
	TimeZone timeZone;
	
//	public static void main(String args[]){
//		frame = new ClockGui();
//		frame.setTitle("Relogio Analogico");
//		frame.setVisible(true);
//	}
	
	public ClockGui() {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setBounds(700, 100, 400, 430);
		getContentPane().setBackground(new Color(212, 234, 255));

		addMouseListener(this);
                addKeyListener(this);
		
		timer = new Timer();
		timeZone = TimeZone.getDefault();
		timer.schedule(new TickTimerTask(), 0, 1000); //after 1s once repaint
	}

  
	class TickTimerTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			cal = (Calendar) Calendar.getInstance(timeZone);
			repaint();
		}
		
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);	
		
		//border clock
		if(status == 0){
			g.setColor(new Color(124, 136, 162));
			g.fillOval(25, spacing, 350, 350);	
			g.setColor(Color.WHITE);
			g.fillOval(35, spacing+10, 330, 330);
		}else if(status == 1){
			g.setColor(Color.BLACK);
			g.fillOval(25, spacing, 350, 350);	
			g.setColor(Color.WHITE);
			g.fillOval(35, spacing+10, 330, 330);
		}

		size = 400 -spacing;		
		centerX = 400/2;
		centerY = 400/2+10;
					
		//clock face
		drawClockFace(g);
		
		//number clock face
		drawNumberClock(g);
										
		//get system time
		cal = Calendar.getInstance();
		hour = cal.get(Calendar.HOUR);
		minute = cal.get(Calendar.MINUTE);
		second = cal.get(Calendar.SECOND);	
		
		//draw digital clock
		if(status==1){
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(centerX-40, centerY-35, 90, 20);
			g.setColor(Color.BLACK);
			g.drawRect(centerX-40, centerY-35, 90, 20);
			sf = new SimpleDateFormat("hh:mm:ss a");
			g.setColor(Color.BLACK);
			g.setFont(new Font("Tahoma", Font.BOLD, 12));
			g.drawString(sf.format(cal.getTime()), centerX-35+3, centerY-35+15);
		}
		
		//draw date in clock 3
		if(status ==2){
			g.setColor(Color.YELLOW);
			g.drawRect(centerX+60, centerY-10, 80, 20);
			sf = new SimpleDateFormat("dd");
			g.drawString(sf.format(cal.getTime()), centerX+60+12, centerY+5);
		}
		
		//draw hands
		if(status==2){
//			drawHands(g,hour,minute,second,colorSecond.RED,colorMHour.YELLOW);
                        aumentaH(g,hour,minute,second,colorSecond.RED,colorMHour.YELLOW,tam);
		}else{
//			drawHands(g,hour,minute,second,colorSecond.RED,colorMHour.BLACK);
                      aumentaH(g,hour,minute,second,colorSecond.RED,colorMHour.BLACK,tam);
		}

		//draw point clock
		g.setColor(Color.BLACK);
		g.fillOval(centerX-5, centerY-5, 10, 10);
		g.setColor(Color.RED);
		g.fillOval(centerX-3, centerY-3, 6, 6);
			
	}

	/*-------------Clock Face----------------*/
	private void drawClockFace(Graphics g) {
		// TODO Auto-generated method stub
		
		// tick marks
		for (int sec = 0; sec<60; sec++) {
			int ticStart;
			if (sec%5 == 0) {
				ticStart = size/2-10;
			}else{
				ticStart = size/2-5;
			}
			
			if(status ==2){
				drawRadius(g, centerX, centerY, radPerSecMin*sec, ticStart-20, size/2-20,colorNumber.YELLOW);
			}else{
				drawRadius(g, centerX, centerY, radPerSecMin*sec, ticStart-20, size/2-20,colorNumber.BLACK);
			}
			
		}
	}
	
	private void drawRadius(Graphics g, int x, int y, double angle,
			int minRadius, int maxRadius, Color colorNumber) {
			float sine = (float)Math.sin(angle);
			float cosine = (float)Math.cos(angle);
			int dxmin = (int)(minRadius * sine);
			int dymin = (int)(minRadius * cosine);
			int dxmax = (int)(maxRadius * sine);
			int dymax = (int)(maxRadius * cosine);
			g.setColor(colorNumber);
			g.drawLine(x+dxmin, y+dymin, x+dxmax, y+dymax);
	}
	/*---------------------------------------------*/
	
	/*----------------Clock Number-----------------*/
	private void drawNumberClock(Graphics g) {
		// TODO Auto-generated method stub
		for(int num=12;num>0;num--){			
			drawnum(g,radPerNum*num,num);			
		}
	}

	private void drawnum(Graphics g, float angle,int n) {
		// TODO Auto-generated method stub
		float sine = (float)Math.sin(angle);
		float cosine = (float)Math.cos(angle);
		int dx = (int)((size/2-20-25) * -sine);
		int dy = (int)((size/2-20-25) * -cosine);		
		
		g.drawString(""+n,dx+centerX-5,dy+centerY+5);
	}
	/*-----------------------------------------------*/
	
	/*----------------Clock Hands--------------------*/
	private void drawHands(Graphics g, double hour, double minute, double second, Color colorSecond, Color colorMHour) {
		// TODO Auto-generated method stub
		
                double rsecond = (second*6)*(Math.PI)/180;
                double rminute = ((minute + (second / 60)) * 6) * (Math.PI) / 180;
                double rhours = ((hour + (minute / 60)) * 30) * (Math.PI) / 180;
            if(this.countDraws==0){
                 Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(tam));

                /*segundo*/ g.setColor(colorSecond);
                g.drawLine(centerX, centerY, centerX + (int) (150 * Math.cos(rsecond - (Math.PI / 2))), centerY + (int) (150 * Math.sin(rsecond - (Math.PI / 2))));
                g.setColor(colorMHour);
    /*minuto*/	g.drawLine(centerX, centerY, centerX + (int) (120 * Math.cos(rminute - (Math.PI / 2))), centerY + (int) (120 * Math.sin(rminute - (Math.PI / 2))));
                this.countDraws++;
            }
    /*hora*/	g.drawLine(centerX, centerY, centerX + (int) (90 * Math.cos(rhours - (Math.PI / 2))), centerY + (int) (90 * Math.sin(rhours - (Math.PI / 2))));
	}
        private void aumentaH(Graphics g,double hour,double minute,double second,Color colorSecond, Color colorMHour,int tam){
                double rsecond = (second*6)*(Math.PI)/180;
		double rminute = ((minute + (second / 60)) * 6) * (Math.PI) / 180;
		double rhours = ((hour + (minute / 60)) * 30) * (Math.PI) / 180;
		 Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(tam));
              		
		g.setColor(colorSecond);
                g.drawLine(centerX, centerY, centerX + (int) (150 * Math.cos(rsecond - (Math.PI / 2))), centerY + (int) (150 * Math.sin(rsecond - (Math.PI / 2))));
		g.setColor(colorMHour);
                g.drawLine(centerX, centerY, centerX + (int) (120 * Math.cos(rminute - (Math.PI / 2))), centerY + (int) (120 * Math.sin(rminute - (Math.PI / 2))));
                g2.drawLine(centerX, centerY, centerX + (int) (90 * Math.cos(rhours - (Math.PI / 2))), centerY + (int) (90 * Math.sin(rhours - (Math.PI / 2))));
    
        
        
        }
	/*-------------------------------------------------*/
	
	// event change interface clock
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(status==0){
			status =1;
			frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		}else if(status ==1){
			status=2;		
			frame.getContentPane().setBackground(Color.BLACK);
		}else{
			status =0;
			frame.getContentPane().setBackground(new Color(212, 234, 255));
		}
		repaint();
	}


    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_SPACE){ 
           dispose();
//            int selectedOption = JOptionPane.showConfirmDialog(this, "Deseja Sair Realmente?", "Atenção", JOptionPane.YES_NO_OPTION);
//            if(selectedOption == JOptionPane.YES_OPTION) {
//                dispose();
////                System.exit(0);
//            }
        } 
         }
          @Override
    public void keyTyped(KeyEvent e) {
//          System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
        Graphics g = null;
        if(e.getKeyChar() == 'h' || e.getKeyChar() == 'H'){ 
          
         tam+=1;
            aumentaH(g, hour, minute, second, colorSecond, colorMHour, tam);
            repaint();
        } 
         if(e.getKeyChar() == 'm' || e.getKeyChar() == 'M'){ 
          
           tam+=1;
            aumentaH(g, hour, minute, second, colorSecond, colorMHour, tam);
            repaint();
        } 
          if(e.getKeyChar() == 's' || e.getKeyChar() == 'S'){ 
          
           tam+=1;
            aumentaH(g, hour, minute, second, colorSecond, colorMHour, tam);
            repaint();
        } 
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
