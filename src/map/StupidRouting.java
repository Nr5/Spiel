package map;

import game.screen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


import java.util.HashMap;


public class StupidRouting{
	private HashMap<Point[], List<Point>> listOfBestRoutes = new HashMap<Point[], List<Point>>();
	
	public boolean extraChecks(Point possibleDirection) {
		return true;
	}
	
	public List<Point> recursiveSearchStart(Point currentPosition, Point target) {
		System.out.println("Recursive Search");
		System.out.print("Start: ");
		System.out.println(currentPosition);
		System.out.print("End: ");
		System.out.println(target);
		List<Point> alreadyVisitedPositions = new ArrayList<Point>();
		return recursiveSearch(currentPosition, target, alreadyVisitedPositions);
	}
	
	private List<Point> recursiveSearch(Point currentPosition, Point target, List<Point> alreadyVisitedPositions) {
		List<Point> solution = new ArrayList<Point>();
		//System.out.print("Already visited: ");
		//System.out.println(alreadyVisitedPositions);	

		System.out.print("Current Position: ");
		System.out.println(currentPosition);	
		// Try caching!
		Point[] currentRoute = {currentPosition,target};
		if (listOfBestRoutes.containsKey(currentRoute)) {
			solution = listOfBestRoutes.get(currentRoute);
		} else if (currentPosition.equals(target)) { 
			// If we are already at the target, just add it to the list and return.
 			solution.add(target);
		} else {
			// Ok, what are the possible directions???
			List<Point> sortedDirections = sortMapFieldsSoThatTheNearestFieldComesFirst(currentPosition, target);
			// Remove those directions, that I have already visited.
			List<Point> markForRemoval = new ArrayList<Point>();
			for (Point possibleDirection: sortedDirections) {
				// if the field is not walkable
				if (extraChecks(possibleDirection) == false) {
					markForRemoval.add(possibleDirection);
				} else {
					// if the routenplaner has already been there 
					for (Point visitedPoint : alreadyVisitedPositions) {
					
						if (possibleDirection.equals(visitedPoint)) {
							markForRemoval.add(possibleDirection);
						}
					}
				}
			}
			for (Point toRemovePoint : markForRemoval) {
				sortedDirections.remove(toRemovePoint);
			}	
			// if there is a direction, that has not been gone yet ... go it!
			for (Point possibleNextStep: sortedDirections) {
				//System.out.print("Next try: ");
				//System.out.println(possibleNextStep);		
				alreadyVisitedPositions.add(possibleNextStep);
				List<Point> nextStep = recursiveSearch(possibleNextStep, target, alreadyVisitedPositions);
				if (nextStep.size() > 0) {
					solution = nextStep;
					solution.add(0, possibleNextStep);
					listOfBestRoutes.put(currentRoute, solution);
					// Solution was found
					// don't try the other ways!
					// go forward to "return"
					break;
				}
			}
		}
		return solution;
	}
	
	/**
	 * Take the current position and the target and return a list of the neighbor-fields of the current position
	 * but sorted so that the first position is nearest to the target
	 * 
	 * @param currentPosition
	 * @param target
	 * @return
	 */
	public List<Point> sortMapFieldsSoThatTheNearestFieldComesFirst(Point currentPosition, Point target) {
		List<Point> sortedDirections = new ArrayList<Point>();
		
		if (target.x >= currentPosition.x && (target.y >= currentPosition.y)) {
			if (target.x - currentPosition.x > target.y - currentPosition.y) {
				sortedDirections.add(new Point(currentPosition.x + 1, currentPosition.y +  0));
				sortedDirections.add(new Point(currentPosition.x + 0, currentPosition.y +  1));
				sortedDirections.add(new Point(currentPosition.x + 0, currentPosition.y + -1));
				sortedDirections.add(new Point(currentPosition.x + -1,currentPosition.y +  0));
			} else {
				sortedDirections.add(new Point(currentPosition.x +0, currentPosition.y +  1));
				sortedDirections.add(new Point(currentPosition.x +1, currentPosition.y +  0));
				sortedDirections.add(new Point(currentPosition.x +-1,currentPosition.y +  0));
				sortedDirections.add(new Point(currentPosition.x +0, currentPosition.y + -1));
			}
		} else if (target.x >= currentPosition.x && target.y <= currentPosition.y) {
			if (target.x - currentPosition.x >= currentPosition.y - target.y ) {
				sortedDirections.add(new Point(currentPosition.x + 1, currentPosition.y +  0));
				sortedDirections.add(new Point(currentPosition.x + 0, currentPosition.y +  -1));
				sortedDirections.add(new Point(currentPosition.x + 0, currentPosition.y +  1));
				sortedDirections.add(new Point(currentPosition.x + -1, currentPosition.y +  0));
			} else {
				sortedDirections.add(new Point(currentPosition.x +  0,  currentPosition.y + -1));
				sortedDirections.add(new Point(currentPosition.x +  1, currentPosition.y +  0));
				sortedDirections.add(new Point(currentPosition.x + -1, currentPosition.y + 0));
				sortedDirections.add(new Point(currentPosition.x +  0, currentPosition.y + 1));
			}
		} else if (target.x <= currentPosition.x && (target.y >= currentPosition.y)) {
			if (currentPosition.x - target.x >= target.y - currentPosition.y) {
				sortedDirections.add(new Point(currentPosition.x + -1, currentPosition.y +  0));
				sortedDirections.add(new Point(currentPosition.x + 0, currentPosition.y +  1));
				sortedDirections.add(new Point(currentPosition.x + 0, currentPosition.y +  -1));
				sortedDirections.add(new Point(currentPosition.x + 1, currentPosition.y +  0));
			} else {
				sortedDirections.add(new Point(currentPosition.x +  0,  currentPosition.y + 1));
				sortedDirections.add(new Point(currentPosition.x + -1, currentPosition.y +  0));
				sortedDirections.add(new Point(currentPosition.x +  1, currentPosition.y + 0));
				sortedDirections.add(new Point(currentPosition.x +  0, currentPosition.y + -1));
			}
		} else {
			if (currentPosition.x - target.x >= currentPosition.y - target.y) {
				sortedDirections.add(new Point(currentPosition.x + -1, currentPosition.y +  0));
				sortedDirections.add(new Point(currentPosition.x + 0, currentPosition.y +  -1));
				sortedDirections.add(new Point(currentPosition.x + 0, currentPosition.y +  1));
				sortedDirections.add(new Point(currentPosition.x + 1, currentPosition.y +  0));
			} else {
				sortedDirections.add(new Point(currentPosition.x +  0, currentPosition.y + -1));
				sortedDirections.add(new Point(currentPosition.x + -1, currentPosition.y +  0));
				sortedDirections.add(new Point(currentPosition.x +  1, currentPosition.y + 0));
				sortedDirections.add(new Point(currentPosition.x +  0, currentPosition.y + 1));
			}
		}
		
		return sortedDirections;
	}	
	
}

