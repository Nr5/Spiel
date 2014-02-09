package map;

import java.awt.Point;

import game.screen;

public class SmartRouting extends StupidRouting {

	public boolean extraChecks(Point possibleDirection) {
		/**
		 * Check if the field "possibleDirection" is free to walk on
		 */
		if (possibleDirection.x >= 0 && possibleDirection.y >= 0) {
			if (!screen.karte.field[possibleDirection.x][possibleDirection.y].passable) {
				System.out.print("++++ You can not walk on ");
				System.out.println(possibleDirection);
			}
			//System.out.println(screen.karte.field[possibleDirection.x][possibleDirection.y].passable);
			//return false;
			return screen.karte.field[possibleDirection.x][possibleDirection.y].passable;
		}
		return true;
		
	}

}
