package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;


import entities.Entity;
import entities.Tank;


@SuppressWarnings("serial")
public class Engine extends JPanel implements Runnable,ActionListener, KeyListener {
	
	//public static Entity[] entities = new Entity[30];
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	public static int showFps = 1;
	public static boolean isConnected = false;
	
	public static String serverIP = new String("127.0.0.1");
	public static String nick = new String("nafiz");

	public static boolean[] keys = new boolean[300];

	boolean gameLoop = true;
	Engine() {
		
		addKeyListener(this);
		
		entities.add(new Tank(200,100));
	}

	
	public void run() {
		int fps = 0;

		long time = System.currentTimeMillis();
		while(gameLoop) {
			
			updateEntities();
			repaint();

			if(System.currentTimeMillis() - time > 1000 ) {
				time = System.currentTimeMillis();
				
				showFps = fps;
				fps = 0;
			
			}
			fps++;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
		
	}
	public void updateEntities() {
		for(Entity e : entities) {
			if(e == null) continue;
			
			e.update();
		}
	}
	
	public void paint(Graphics g) 	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
	      RenderingHints rh =
	            new RenderingHints(RenderingHints.KEY_ANTIALIASING,
	                               RenderingHints.VALUE_ANTIALIAS_ON);

	      rh.put(RenderingHints.KEY_RENDERING,
	             RenderingHints.VALUE_RENDER_QUALITY);

	      g2.setRenderingHints(rh);

	      g2.setColor(new Color(0x007808));
	      g2.fillRect(0, 0, 800, 600);
	      
	      for(Entity en : entities) {
	    	  en.draw(g2);
	      }
	      
	      
	      g2.setColor(new Color(0xFFFFFF));
	      g2.drawString("FPS: "+ showFps, 740, 550);
	      	
    }
	
	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 87) {
			entities.get(0).yVec += 0.2f;
			entities.get(0).xVec += 0.2f;
		}
		if (e.getKeyCode() == 83) {
			entities.get(0).yVec -= 0.2f;
			entities.get(0).xVec -= 0.2f;
		}
		if (e.getKeyCode() == 68) {
			entities.get(0).angleVec += 0.15;
		}

		if (e.getKeyCode() == 65) {
			entities.get(0).angleVec -= 0.15;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

