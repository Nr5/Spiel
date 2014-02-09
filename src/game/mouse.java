package game;

import graphics.frame;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.event.MouseInputListener;
public class mouse implements MouseInputListener { 
static boolean clicked;
static Point ms = new Point();
static Point pos = new Point();
public static BufferedImage click; 


@Override
public void mouseClicked(MouseEvent m) {
	// TODO Auto-generated method stub


	}

@Override
public void mouseEntered(MouseEvent m) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent m) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent m) {
	// TODO Auto-generated method stub
   clicked = true;
	ms.x = m.getX() - frame.panel.spielfeld.mappos.x;
	ms.y = m.getY() - frame.panel.spielfeld.mappos.y;

}

@Override
public void mouseReleased(MouseEvent m) {
	// TODO Auto-generated method stub
	   clicked = false;
	   screen.player.ziel.x	= ms.x;
	   screen.player.ziel.y	= ms.y;
}

@Override
public void mouseMoved(MouseEvent m) {
	pos.x = m.getX() - frame.panel.spielfeld.mappos.x;
	pos.y = m.getY() - frame.panel.spielfeld.mappos.y;
}

@Override
public void mouseDragged(MouseEvent m) {
	frame.panel.spielfeld.rob.mouseMove(
			ms.x + frame.panel.spielfeld.mappos.x + 112, 
			ms.y + frame.panel.spielfeld.mappos.y + 84
	);
} 

public static Point getPos(){
	
	return new Point(MouseInfo.getPointerInfo().getLocation().x-frame.panel.spielfeld.mappos.x,MouseInfo.getPointerInfo().getLocation().y-frame.panel.spielfeld.mappos.y);	
	
}


}
