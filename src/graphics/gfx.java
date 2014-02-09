package graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class gfx {

	public static BufferedImage filter(BufferedImage image,int nr){
	int color;
	int red;
	int green;
	int blue;
	
	if (nr == 0)return image; 
	
	for (int i = 0;i<image.getWidth();i++){
		for (int j = 0;j<image.getHeight();j++){

		color = image.getRGB(i,j);	
		System.out.println(color);
			
		if (color<0) { 
		color = color + 16777216;
		
		blue = color % 256;	
		if (nr == 1) color = blue - 16777216; 
				
		
		//green = ((color - blue) /256)%256;
		//red = (color - green * 256 - blue)/65536;
		
		
		
		
		image.setRGB(i,j,color) ;
			
		}
		}	
	}	
	return image;




}
}
