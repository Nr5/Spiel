package game;

import graphics.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import map.map;

public class screen extends JPanel {
	public static final map    karte  = new map("neu");
	public static final figur player  = new figur("pigeon",new Point(20,20));
	public static final List<figur> enemies =new LinkedList<figur>();
	public key keys = new key();
	public bild spielfeld = new bild();

	
	
	public screen() throws InterruptedException {
	    setBounds(0, 0, 1024, 768);
	    setLayout(null);
	    add(spielfeld);
	    setBackground(Color.black);
	    enemies.add(new figur("pigeon",new Point(33,30) ));
	    enemies.add(new figur("player",new Point(23,30) ));
	    enemies.add(new figur("player",new Point(23,30) ));

	    enemies.add(new figur("pigeon",new Point(43,30) ));
	    enemies.add(new figur("pigeon",new Point(33,40) ));
	    enemies.add(new figur("pigeon",new Point(33,20) ));

	}

	
	
	@SuppressWarnings("deprecation")
	public static void game() throws InterruptedException{
		
		while(frame.content == "game"){   

	    	
	    	
	    	frame.panel.repaint();
			Thread.sleep(10);
		
		}

	}

	public void paint(Graphics g){
		super.paint(g);

g.setColor(Color.darkGray); g. drawRect(108,80,804,604);
g.setColor(Color.darkGray); g. drawRect(109,81,803,603);
g.setColor(Color.darkGray); g. drawRect(110,82,802,602);
g.setColor(Color.darkGray); g. drawRect(111,83,801,601);


g.setColor(Color.darkGray); g.drawRect(29,29,50,30);
g.setColor(Color.black);  g.fillRect(30,30,50,30);
g.setColor(Color.red); g.drawString(String.valueOf(player.life), 40, 50);
	  	
g.setColor(Color.black);   g.drawString(String.valueOf(mouse.pos.x), 30, 650);
g.setColor(Color.black);   g.drawString(String.valueOf(mouse.pos.y), 70, 650);
g.setColor(Color.black);   g.drawString(String.valueOf((int) screen.enemies.get(0).px_x), 30, 665);
g.setColor(Color.black);   g.drawString(String.valueOf((int) screen.enemies.get(0).px_y), 70, 665);
g.setColor(Color.black);   g.drawString(""+spielfeld.mappos.x, 30, 700);
g.setColor(Color.white);   g.drawString(""+spielfeld.a, 30, 720);
	  		

	     
	     
	 }


}
