package game;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import projekt.convert;



public class bild extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public Point mappos = new Point((int)-screen.player.px_x+400,(int)-screen.player.px_y+300);
	static int feld_x = 40;
	static int feld_y = feld_x * 3/4;
	public boolean a;
	static BufferedImage click;
	Robot rob;
	public bild() throws InterruptedException{
		try {
			click = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/click.png"));
		} 
		catch (IOException e) {	}
		
 
		 addMouseListener(new mouse());
	     addMouseMotionListener(new mouse());

	     setLayout(null);
	     setBackground(Color.white);
	     setBounds(112, 84, 800, 600);
	     
	     
	     try {	rob = new Robot();	} 
	     catch (AWTException e) {}

	     
	     
	     //Toolkit toolkit = Toolkit.getDefaultToolkit();  
         //java.awt.Image image  = toolkit.getImage("click.png");
         //Point hotSpot = new Point(30, 30);
         //Cursor c = toolkit.createCustomCursor(image, hotSpot, "Changer");
        
         //setCursor(c);
	     
	}
				

	
	
	
	
	
	
	
	
	public void paint(Graphics g){
	     super.paint(g);
	
	     
	     double str_x= (400-mappos.x-screen.player.px_x);
	     double str_y= (300-mappos.y-screen.player.px_y);
	
	     double strecke1 =  Math.hypot(str_x*.75, str_y);
	     double strecke2 =  Math.hypot(str_x, str_y);
	
		if (strecke1 > 150) {
			
			mappos.y += (int) ( (str_y / strecke2)*(strecke1-150)   );
			mappos.x += (int) ( (str_x / strecke2)*(strecke1-150)   );
			
			if(mouse.getPos().x+mappos.x>114
				&&mouse.getPos().x+mappos.x<908
				&&mouse.getPos().y+mappos.y>86
				&&mouse.getPos().y+mappos.y<680) {	
				rob.mouseMove(
						mouse.getPos().x+mappos.x+  (int) ((str_x / strecke2)*(strecke1-150) ),	
						mouse.getPos().y+mappos.y+ (int) ( (str_y / strecke2)*(strecke1-150) )
				);
			}
	     }
	     
	 
	     //Karte
		 g.drawImage(screen.karte.image,mappos.x-2500,mappos.y, null);
	     
	     
	     
	     //---------------------------------------------
	     		 
	     
	     
	     // Person malen!
	     //Point personFeld = convert.mapKoordinate(new Point((int) screen.player.px_x,(int) screen.player.px_y));
		 //int x = convert.pxKoordinate(personFeld).x;
		 //int y = convert.pxKoordinate(personFeld).y;
		 //int size = 13;
		 //int [] eckenX = {x, x - size, x, x + size}; 
		 //int [] eckenY = {y - size, y, y + size, y}; 
		 //g.setColor(Color.BLUE); 
		 //g.fillPolygon(eckenX,	eckenY,	4);
		 //---------------------------------------------
				
		// Zielmarkierung: Draw a Circle: The name of the circle is "click"
	 	if (mouse.clicked == true) {
	 		g.drawImage(click,
	 				mouse.ms.x + mappos.x -20,
	 				mouse.ms.y + mappos.y -15,
	 				null
				);	
	 	}
	 	/*
	 	if (mouse.clicked == true) 	{
	 		int mousePixelX = mouse.ms.x;
	 		int mousePixelY = mouse.ms.y;
	 		
	 		int myX = mappos.x + convert.px(convert.map(new Point(mousePixelX,mousePixelY))).x;
	 		int myY = mappos.y + convert.px(convert.map(new Point(mousePixelX,mousePixelY))).y;
	 		Point p = new Point (myX, myY);
	 		
	 		//myX = myX - mappos.x;
	 		//myY = myY - mappos.y;
	 		
	
	
			g.setColor(Color.white);
			 		
	// 		g.drawString(""+mappos.x+" , "+mappos.y,10,10);
			
			g.drawLine(p.x+20,p.y   , p.x+40,p.y+15);
			g.drawLine(p.x+20,p.y   , p.x   ,p.y+15);
			g.drawLine(p.x+20,p.y+30, p.x+40,p.y+15);
			g.drawLine(p.x+20,p.y+30, p.x   ,p.y+15);
	
	 	}*/
				 
		// Spieler
	    g.drawImage(
	    		screen.player.look,
	    		(int) (screen.player.px_x + mappos.x) - screen.player.look.getWidth()/2,  
	    		(int) (screen.player.px_y + mappos.y) - screen.player.look.getWidth()+15 ,  
	    		screen.player.look.getWidth(),
	    		screen.player.look.getHeight(),
	    		null
    		);
	    		  
	    // Feinde
		for (int i = 0;i< screen.enemies.size();i++){
			 g.drawImage(screen.enemies.get(i).look,
					 (int) (screen.enemies.get(i).px_x + mappos.x)-screen.enemies.get(i).look.getWidth()/2,  
					 (int)(   screen.enemies.get(i).px_y   +  mappos.y)-screen.enemies.get(i).look.getWidth()+15, 
					screen.enemies.get(i).look.getWidth(),   
					screen.enemies.get(i).look.getHeight(), 
					null);
		
		}
	 	
	 	//---------------------------------------------
	    
	 
	 		
	
	
	    
	    
	}
}
