package map;

import game.screen;
import graphics.frame;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class mapField {
	public boolean passable = true;
	public String text; 
	
	mapField(String text){
	this.text = text;
	if (text.charAt(0) == 'X') passable = false;
	
	
	}


}
