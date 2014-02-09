package map;
import game.screen;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import projekt.convert;
public class map {
	public mapField field[][] = new mapField[1000][1000];
	String s[][] = new String[1000][1000];
	public int height = 0;
	public int width = 0;
	public BufferedImage image = new BufferedImage(5000, 5000, 1);    
	public BufferedImage tile;    
	public BufferedImage gr;    
	public BufferedImage sa;    
	public BufferedImage st;    
	public BufferedImage X_wa;    

    
	public Graphics2D g2d = image.createGraphics();
    
	public map(String file) {
    	loadTiles();
    	try {
			FileReader read = new FileReader(file);
			BufferedReader br = new BufferedReader(read);
			
			int i = 0;
			int j = 0;
			
	        do {
	        	s[0][i] = br.readLine();
			
	
	        	if (s[0][i] != null) {
        	   		String spalte[] = s[0][i].split(",");
	        	
	        		for (j=0;j<spalte.length;j++){
	        			s[j][i] = spalte[j].replace(String.valueOf((char)34),"");  
	        			field [j][i] = new mapField(s[j][i]);
				  
	        			if (s[j][i].charAt(0)=='g')   tile =gr;
	        			if (s[j][i].charAt(1)=='a')   tile =sa;
	        			if (s[j][i].charAt(1)=='t')   tile =st;
	        			if (s[j][i].charAt(0)=='X')   tile =X_wa;
			   		
	        			g2d.drawImage(
			   				tile,
			   				convert.px(new Point(j,i)).x+2500, 
			   				convert.px(new Point(j,i)).y,
			   				null
		   				);
	    		  
	        		}
	        	}
        		i = i + 1;
	        } 
	        while (s[0][i-1] != null); 
        	
	        System.out.println(s[1][1]);
            
	        br.close();
			height = i;
			width  = j;
	       	
       	}
		catch (FileNotFoundException ex) {
			System.out.println("Error: map "+"'"+file+"'"+" not found!");  
		} 
		catch (IOException ex) {
			System.out.println("Error");
		}
    	

       	
      	
       	
       	
			 

       	
	}

    private void loadTiles() {
		try {
			gr   = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/gr.png"));
			sa   = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sa.png"));
			st   = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/st.png"));
			X_wa = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/X_wa.png"));
		} catch (IOException e) {e.printStackTrace();}
	}


}
