import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author maxbronckers
 * @author jerilynzheng
 * @date Wednesday 29 March 2017
 * 
 * A parent class for different types of agents.
 */
public abstract class Agents {
	
	public int state;
	public int yPos;
	public int xPos;
	public ArrayList<String> neighbors = new ArrayList<String>(); 			// list to hold strings of all neighbors = current neighborhood
	public Random rand = new Random();
	
	abstract void nextGen(int[][] board, ArrayList<Agents> list);
	
	public Agents() {
	}
	
	public Agents(int state, int yPos, int xPos) {
		this.state = state;
		this.yPos = yPos;
		this.xPos = xPos;
	}

	/**
	 * 
	 * @param list
	 */
	
	public void move(ArrayList<Agents> list, int[][]board) {
		int count;
		int[] dir = direction();
		int originalX = xPos;
		int originalY = yPos;
		int numTry = 0;
		
		do {
			count = 0;
			/**
			 * Finding one out 8 moves randomly
			 */
			dir = direction();
			int xValue = dir[0];
			int yValue = dir[1];
			xPos = ( ((originalX + xValue) % board[0].length) + board[0].length) % board[0].length;
			yPos = ( ((originalY + yValue) % board.length) + board.length) % board.length;
						
			/** 
			 * Checking if move is possible
			 */
			 
			for (int i = 0; i < list.size(); i++) {
				if (xPos == list.get(i).xPos && yPos == list.get(i).yPos) {
					count++;
				}	
			}
			
			if (numTry > 50) { // if there is no move available after 50 tries, don't move.
				xPos = originalX;
				yPos = originalY;
				break; //no movement possible
			}
	
		
		} while (count >= 2); 		//try another move 
	
	}
	
	/**
	 * 
	 * @param list
	 * @param x the Agent that has to move
	 * 
	 * This function has an extra parameter to let a newly created Agent move 
	 * while called by a different object than the Agent's object.
	 * 
	 * Ex: Enemy 1 creates a new Enemy and wants to move it --> it calls this function.
	 */
	public void move(ArrayList<Agents> list, Agents x, int[][] board) {
		int count;
		int[] dir = direction();
		int originalX = x.xPos;
		int originalY = x.yPos;
		int numTry = 0;
		do {
			count = 0;
			numTry++;
			/**
			 * Finding one out 8 moves randomly
			 */
			dir = direction();
			int xValue = dir[0];
			int yValue = dir[1];
			
			x.xPos = ( ((originalX + xValue) % board[0].length) + board[0].length) % board[0].length;
			x.yPos = ( ((originalY + yValue) % board.length) + board.length) % board.length;
					
			/** 
			 * Checking if move is possible
			 */
			 
			for (int i = 0; i < list.size(); i++) {
				if (x.xPos == list.get(i).xPos && x.yPos == list.get(i).yPos) {
					count++;
				}	
			}
			
			if (numTry > 50) { // if there is no move available after 50 tries, don't move.
				x.xPos = originalX;
				x.yPos = originalY;
				break; //no movement possible
			}
	
		} while (count >= 2); 		//try another move 
		
		list.add(x);
	}
	
	/**
	 * Function randomly returns an array filled with an x-axis and y-axis movement.
	 * Minimal movement = -1
	 * Maximal movement = 1
	 * Possible movements per axis = 3
	 * Axes = 2
	 * Possible movements = 2^3 = 8
	 *
	 * Every movement has an equal chance of getting selected by using the random generator.
	 * @return
	 */
	
	/* Returns possible x and y movement in neighborhood.
	 * 
	 */
	public int[] direction() {
		int[] xy = new int[2];
		int x, y;
		if (rand.nextBoolean()) { 
			if (rand.nextBoolean()) { 
				if (rand.nextBoolean()) {
					x = -1;
					y = -1;
				}
				else {
					x = -1;
					y = 0;
				}
			}
			else { 
				if (rand.nextBoolean()) {
					x = 0;
					y = -1;
				}
				else {
					x = -1;
					y = 1;
				}
			}
		}
		
		else {
			if (rand.nextBoolean()) {
				if (rand.nextBoolean()) {
					x = 1;
					y = 1;
				}
				else {
					x = 1;
					y = 0;
				}
			}
			else {
				if (rand.nextBoolean()) {
					x = 0;
					y = 1;
				}
				else {
					x = 1;
					y = -1;
				}
			}
		}
		
		xy[0] = x;
		xy[1] = y;
		return xy;
	}
}

