package game;
import graphics.frame;
import graphics.gfx;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.jail.ralphscode.AudioInput;
import projekt.convert;

public class figur extends Thread{
	
	 public Point p;
	 public float px_x;
	 public float px_y;		 
	 float richtung_x;
	 float richtung_y;
	 public int speed;
	 public int life;
	 public int strength;
	 public boolean agressive;
	 public float strecke =50;
	 public Point ziel = new Point(100,100);

	 public int damage = 0;
	 public BufferedImage image[] = new BufferedImage[6];
	 public BufferedImage look;
	 public int color;

	 AudioInput sound[] = new AudioInput[6]
;
	 public figur(String path,Point q) {
		load(path);
		 
		p = q;
        px_x= convert.px(p).x;
		px_y= convert.px(p).y;
		ziel.x = (int) px_x;
		ziel.y = (int) px_y;		
		
		start();
	 }


//Haupt-Methode---------------------	 


public void run() {
while (true) {
    
	//if (Math.abs(px_x - mouse.pos.x) < 10 && Math.abs(px_y - mouse.pos.y)  <10 ) color = 1;
	//else color = 0;
	
	
	// Spiel-Unterbrechung
    while (frame.content != "game")	try {Thread.sleep(10);} catch (InterruptedException e) {}

    if (damage > 0) defense(damage);
    if (this != screen.player)getZiel();
    
	richtung_x=0;
	richtung_y=0;
	
	//Entfernung zu Ziel
	strecke= (float) Math.hypot((ziel.x-px_x),(ziel.y-px_y));
	
	
	//x und y Strecke
	if (  !(  Math.abs(ziel.x-px_x) <2 & Math.abs(ziel.y-px_y) <2)  ) 
		{
		richtung_x = (ziel.x-px_x) / strecke ;
		richtung_y = (ziel.y-px_y) / strecke ;
		}

	if(richtung_x<0 && richtung_y<0)look=gfx.filter(image[0],color);
	if(richtung_x>0 && richtung_y<0)look=gfx.filter(image[2],color);
	if(richtung_x>0 && richtung_y>0)look=gfx.filter(image[3],color);
	if(richtung_x<0 && richtung_y>0)look=gfx.filter(image[5],color);
	if(richtung_y<-0.8)look=gfx.filter(image[1],color);
	if(richtung_y> 0.8)look=gfx.filter(image[4],color);

	//bewegen
	if (screen.karte.field[convert.map(new Point( (int) (px_x + richtung_x*speed/10),(int) (px_y + richtung_y*speed/10) )).x ][convert.map(new Point( (int) (px_x + richtung_x*speed/10),(int) (px_y + richtung_y*speed/10) )).y].passable) 
		{
		px_x = (float) (px_x + richtung_x*speed/10)  ;
		px_y = (float) (px_y + richtung_y*speed/10)  ;
		}	 


	
    //warten
    try {Thread.sleep(10);} catch (InterruptedException e) {}	
    if (life <= 0)
    {	
    	sound[3].play();
        try {Thread.sleep(2000);} catch (InterruptedException e) {}	
    	System.exit(0);
    }
    
}
}
//----------------------------------	 








private void getZiel() {
	if (Math.hypot((screen.player.px_x-px_x),(screen.player.px_y-px_y))<100){

		if(agressive){
			ziel.x =	(int) screen.player.px_x;
			ziel.y =	(int) screen.player.px_y;
		}

		else{
			ziel.x = (int) (px_x + (px_x-screen.player.px_x));
			ziel.y = (int) (px_y + (px_y-screen.player.px_y));
		}
	}

	
	else {
		if(Math.random()*1000 < .5)sound[0].play();

		if (Math.abs(ziel.x-px_x) <2 & Math.abs(ziel.y-px_y) <2){   
			ziel.x = (int) (px_x + (Math.random()*300)-150);
			ziel.y = (int) (px_y + (Math.random()*300)-150);

		}
	}

	
	if(agressive && Math.hypot((screen.player.px_x-px_x),(screen.player.px_y-px_y))<10 && screen.player.life>0){
		attack(screen.player);
	}
	
	
}
	


//angreifen
public void attack(figur bla) {
	bla.damage += (int) (Math.random() * strength);
	sound[2].play();
	try {Thread.sleep(500);} catch (InterruptedException e) {}	

}


//verteidigen
public void defense(int x) {
	life -= damage;
	damage = 0;
	try {Thread.sleep(200);} catch (InterruptedException e) {}
	sound[1].play();

}




private void load(String path) {
	 
	try {
	FileReader read = new FileReader(path);
	@SuppressWarnings("resource")
	BufferedReader br = new BufferedReader(read);

	life      = Integer.valueOf(br.readLine());
	strength  = Integer.valueOf(br.readLine());
	speed     = Integer.valueOf(br.readLine());
	agressive = Boolean.valueOf(br.readLine());
	
	image[0] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("monster/"+path+"_nw.png"));
	image[1] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("monster/"+path+"_n.png"));
	image[2] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("monster/"+path+"_no.png"));
	image[3] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("monster/"+path+"_so.png"));
	image[4] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("monster/"+path+"_s.png"));
	image[5] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("monster/"+path+"_sw.png"));
	look = image[0];
	}  catch (IOException e) {System.out.println("Character data not found '"+path+"'");}

	sound[0] = new AudioInput("monster/"+path+"_bored.wav"  );
	sound[1] = new AudioInput("monster/"+path+"_defense.wav");
	sound[2] = new AudioInput("monster/"+path+"_attack.wav" );
	sound[3] = new AudioInput("monster/"+path+"_die.wav"    );
	

}


}







