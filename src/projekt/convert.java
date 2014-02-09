package projekt;

import java.awt.Point;

public class convert {
		
	/**
	 * Return the pixel coordinates from the Map Coordinates
	 * 
	 * @param k
	 * @return
	 */
	public static Point px(Point k){
	    double distance = Math.hypot(k.x, k.y);

	    double angleX = Math.acos(k.x / distance);
	    double angleNewX = angleX - Math.acos(0.8);
	    double pixelX = Math.cos(angleNewX) * distance;
	    int mapX = (int)(pixelX * 25);
	    
	    ////
	    double angleY = Math.acos(k.x / distance);
	    double angleNewY = angleY - Math.asin(0.8);
	    double pixelY = Math.sin(angleNewY) * distance;
	    int mapY = (int)(pixelY * 15) - 25;
	    
	    //System.out.println("Map-coordinate: k [" + k.x + "  " + k.y + "] results (angleX "+angleX+") in Pixel ["+ mapX + "  " + mapY + "]");

	    Point resultPoint = new Point(mapX, mapY);
	    return resultPoint;
	}
	
	
	/**
	 * Return the map coordinates from the Pixel Coordinates
	 * 
	 * @param Point k
	 * @return
	 */
	public static Point map(Point k){
		//int newX = (int) (((k.x*.75* Math.cos(36.9) - (k.y)* Math.sin(36.9))/40));
	    //int newY = (int) (((k.x*.75* Math.sin(36.9) + (k.y)* Math.cos(36.9))/40));
	    
	    double distance = Math.hypot(k.x, k.y);
	    

	    double angleX = Math.acos(k.x / distance);
	    double angleNewX = angleX + Math.acos(0.5);
	    double pixelX = Math.cos(angleNewX) * distance;
	    int mapX = (int)(pixelX / 25);
	    
	    ////
	    double angleY = Math.acos(k.y / distance);
	    double angleNewY = angleY - Math.asin(0.5);
	    double pixelY = Math.cos(angleNewY) * distance;
	    int mapY = (int)(pixelY / 15);
	    

//		System.out.println("Pixel: k [" + k.x + "  " + k.y + "] results in Map coordinate ["+ mapX + "  " + mapY + "]");
			    
	    Point resultPoint = new Point(mapX, mapY);
		return resultPoint;
	}

}
