package game;

import graphics.frame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;







import projekt.main;

public class key implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			frame.content = "menue";
		}
		
		if(e.getKeyCode() == KeyEvent.VK_I) {
		    frame.content = "inventar";
		}
	   	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}
