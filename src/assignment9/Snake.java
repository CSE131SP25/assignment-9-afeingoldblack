package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	private int direction;
	private boolean shouldGrow = false;
	
    public Snake() {
        segments = new LinkedList<>();
        segments.add(new BodySegment(0.5, 0.5, 0.02)); // starting at position (0.5, 0.5)
        direction = 4;  // Default direction is to the right
    }
	
	public void changeDirection(int direction) {
	    if ((this.direction == 1 && direction == 2) ||  // up → down
	            (this.direction == 2 && direction == 1) ||  // down → up
	            (this.direction == 3 && direction == 4) ||  // left → right
	            (this.direction == 4 && direction == 3)) {  // right → left
	            return;  // Do nothing — this is a reversal
	        }
	    this.direction = direction;
	    
		
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
	    BodySegment head = segments.getFirst();
	    double newX = head.getX() + deltaX;
	    double newY = head.getY() + deltaY;

	    // Add new head at new position
	    segments.addFirst(new BodySegment(newX, newY, SEGMENT_SIZE));

	    // Only remove tail if not growing
	    if (shouldGrow) {
	        shouldGrow = false; // Use it once
	    } else {
	        segments.removeLast();
	    }
	}


	
	/**
	 * Draws the snake by drawing each segment
	 */
    public void draw() {
        for (BodySegment segment : segments) {
            segment.draw();  // Each segment will draw itself
        }
    }
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
    public boolean eat(Food f) {
        BodySegment head = segments.getFirst();  // Get the head of the snake

        // Check if the snake's head is at the same location as the food
        if (Math.abs(head.getX() - f.getX()) < 0.02 && Math.abs(head.getY() - f.getY()) < 0.02) {
            // The snake ate the food, so grow the snake
            shouldGrow = true;
            return true;
        }
        return false;
    }

	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
    public boolean isInbounds() {
        BodySegment head = segments.getFirst();  // Get the head of the snake
        return head.getX() >= 0 && head.getX() <= 1 && head.getY() >= 0 && head.getY() <= 1;
            }

    
    public boolean hasCollidedWithSelf() {
        BodySegment head = segments.getFirst();
        double headX = head.getX();
        double headY = head.getY();

        for (int i = 1; i < segments.size(); i++) {
            BodySegment segment = segments.get(i);
            if (Math.abs(headX - segment.getX()) < SEGMENT_SIZE &&
                Math.abs(headY - segment.getY()) < SEGMENT_SIZE) {
                return true;
            }
        }
        return false;
    }

    }
