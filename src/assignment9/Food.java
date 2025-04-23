package assignment9;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class Food 
{

    public static final double FOOD_SIZE = 0.02; // Size of the food
    private double x, y; // Coordinates of the food

    /**
     * Creates a new Food at a random location
     */
    public Food() 
    {
        // Generate random coordinates within the bounds [0, 1)
        this.x = Math.random();
        this.y = Math.random();
    }

    /**
     * Draws the Food
     */
    public void draw() 
    {
        StdDraw.setPenColor(Color.RED); 
        StdDraw.filledCircle(x, y, FOOD_SIZE); 
    }

	public double getX() 
	{
		return x;
	}
	
	public double getY() 
	{
		return y;
	}

	public void addLast(BodySegment bodySegment) {
		
	}
}

