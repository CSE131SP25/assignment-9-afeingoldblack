package assignment9;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {

    private Snake snake;
    private Food food;

    public Game() {
        StdDraw.enableDoubleBuffering();
        
        // Construct new Snake and Food objects
        snake = new Snake();
        food = new Food();
    }

    public void play() {
        while (snake.isInbounds() && !snake.hasCollidedWithSelf()) 
        { // Check if the snake is in bounds
            int dir = getKeypress();

            // 1. Pass direction to your snake
            if (dir != -1) {
                snake.changeDirection(dir);
            }

            // 2. Move the snake
            snake.move();

            // 3. If the food has been eaten, make a new one
            if (snake.eat(food)) {
                food = new Food();
            }

            // 4. Update the drawing
            updateDrawing();

            // Add a brief pause for smoother movement
            StdDraw.pause(100); // Adjust as needed for game speed
        }
        
        // Set the background color
        StdDraw.clear(StdDraw.BLACK);

        // Set the drawing color to red
        StdDraw.setPenColor(StdDraw.RED);


        // Draw the skull base (a big circle)
        StdDraw.filledCircle(0.5, 0.6, 0.2);

        
        // Show the drawing
        StdDraw.show();

        System.out.println("Game Over!");
    }

    private int getKeypress() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            return 1; // Up
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            return 2; // Down
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            return 3; // Left
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            return 4; // Right
        } else {
            return -1; // No key pressed
        }
    }

    /**
     * Clears the screen, draws the snake and food, pauses, and shows the content
     */
    private void updateDrawing() {
        // 1. Clear the screen
        StdDraw.clear();

        // 2. Draw the snake and food
        snake.draw();
        food.draw();

        // 3. Pause for a short period
        StdDraw.pause(5); 

        // 4. Show the updated content
        StdDraw.show();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}
