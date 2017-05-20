import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.Scanner;

import acm.graphics.GCanvas;
import acm.program.GraphicsProgram;

/**
 * @author maxbronckers 
 * @author jerilynzheng
 * 
 * Project: Cellular Automaton
 * Description: The project exists out of 5 parts; the organisms, the board, the runner, the rules, and the graphics.
 * The organisms are spies, enemies, and walls. They are subclasses of the superclass Agents, which holds the common variables and methods.
 * The spies and enemies are moving organisms, while the walls are static. 
 * The board is managed by the class World, which has the main functions regarding communication between the board and organisms.
 * The World class also has an instantiated object of the Grid class, which drives the graphics of the project.
 * The project is controlled and driven by the class WorldController, which creates an object of the World and asks for the size of the board and graphics.
 * The controller class creates an initial state of organisms to display on the board, and then continues to call methods from the instantiated World object
 * for future generations of the organisms.
 * The graphics are driven by the Grid class, which inherits the class GraphicsProgram. The graphics display the board as a grid on the canvas, with an image for each organism. 
 * 
 * Notes:
 * Organisms are stored in an <Agents> ArrayList, which allows for expansion and minimal space usage. This allows for optimized performance to our knowledge.
 * Program is easily expandable and maintainable due to extensive project decomposition process at the start of the project: 
 * - Additional types organisms can be added as new classes and implemented in the rules
 * - Additional states of organisms can be added and implemented in the classes and implemented in the rules 
 * - 
 * 
 * Performance:
 * - Organisms are sustainable up to a 10,000 generations (screenshot in folder) on test machine (specifications below).
 * - Project works up with a board size up to 120 by 120 (screenshot in folder) on test machine. 
 * - Java's garbage collector (GC) removes all empty references to temporary objects of classes, which ensures that the heap size does not get too large.
 * 
 * Extensions:
 * Images for the organisms in graphics.
 * Efficient calculations of next generations of organisms.
 * 
 * Test machine:
 * Processor: 2.9 Intel i7 Quad-core
 * RAM: 16 GB 2133 MHz
 * Graphics Card: Radeon Pro 460 4096 MB
 * 
 * Date changed: April 16, 2017
 * Changes: Updated description + performance tests 
 */
public class WorldController extends GraphicsProgram {
	public static Random rand = new Random();
	public GCanvas canvas = new GCanvas();
	public UserInput ui = new UserInput();
	
	/**
	 * @param args
	 */
	public void run() {
		ui.run();
		int wP = ui.wP;
		int lP = ui.lP;
		int rows = ui.rows;
		int columns = ui.columns;

		World x = new World(rows, columns);
		canvas = this.getGCanvas();
		
		try {								//let the thread sleep to give the user time to open the applet completely.
		    Thread.sleep(1000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		initialState(x);
		x.showWorld(canvas, rows, columns, lP, wP);
		for (int k = 0; k < 1000; k++) {
			x.nextGen();
			x.showWorld(canvas, rows, columns, lP, wP);
		}
	}
	
	public static void initialState(World x) { // generates initial organisms in random locations on board
		int length = x.board.length;
		int width = x.board[0].length;

		
		for (int i = 0; i < (length/2); i++) {
			int xLocationSpy = rand.nextInt(width-1);
			int yLocationSpy = rand.nextInt(length-1);

			Spies random = new Spies(3, yLocationSpy, xLocationSpy);
			x.add(random);
			
		}
		
		for (int i = 0; i < (length/2); i++) {
			int xLocationEnemy = rand.nextInt(width-1);
			int yLocationEnemy = rand.nextInt(length-1);

			Enemies random = new Enemies(5, yLocationEnemy, xLocationEnemy);
			x.add(random);
			
		}
		
		for (int i = 0; i < (length/2); i++) {
			int xLocationWall = rand.nextInt(width-1);
			int yLocationWall = rand.nextInt(length-1);
			Walls random = new Walls(1, yLocationWall, xLocationWall);
			x.add(random);	
			

		}
	}
}
