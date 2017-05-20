import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author maxbronckers
 * @author jerilynzheng
 *
 */

public class Spies extends Agents {
	
	int[] states = {3,4}; // possible states for moving agents
	
	public Spies() {
		super();
	}
	public Spies(int s, int y, int x) {
		super(s, y, x);
	}
	
	@Override
	void nextGen(int[][] board, ArrayList<Agents> list) {
		//enemies, walls, spies
		String type1 = "", type2 = "", type3 = ""; 			// strings for each type of organism to hold neighbors

		int row = 0;
		int col = 0;
		for (int r = yPos-1; r < yPos + 2; r++) {
			for (int c = xPos-1; c < xPos + 2; c++) {
				
				int value = board[(( (r) % board.length) + board.length) % board.length][(( (c) % board.length) + board.length) % board.length];
				
				for (int x : states) { 		
					
					if (value == x) {			//add to string for type
						type1 += value;
					}
				}
				
				Walls wall = new Walls(); 		//create object to access possible states
				for (int x : wall.states) {
					if (value == x) {			//section for organism Walls
						type2 += value;
					}
				}
				wall = null;				//delete instance of Walls (aka object) from heap memory
				
				Enemies enemies = new Enemies();
				for (int x : enemies.states) {
					if (value == x) {			//section for organism Walls
						type3 += value;
					}
				}
				enemies = null;
				col++;
			}
			row++;
		}
		
		//add strings of each type of organisms to the list of all neighbors
		neighbors.add(type1);		//type1 = spies
		neighbors.add(type2);		//type2 = walls
		neighbors.add(type3);		//type3 = enemies

		rules(neighbors, list, board);

	}
	
	/* pre: current state
	 * post: new state (after application of rules; could be change in neighborhood (# of agents, location))
	 * 
	 */
	public void rules(ArrayList<String> n, ArrayList<Agents> list, int[][] board) {
	
		int countS = 0, countW = 0, countE = 0; //spies, walls, enemies
		int[] ar1 = new int[n.get(0).length()], ar2 = new int[n.get(1).length()], ar3 = new int[n.get(2).length()];
		
		for (int k = 0; k < 3; k++) {
			for (int i = 0; i < n.get(k).length(); i++) {
				if (k == 0) {
					ar1[i] = Character.getNumericValue(n.get(k).charAt(i)); //read char from each type into an array for each organism
				}
				else if (k == 1) {
					ar2[i] = Character.getNumericValue(n.get(k).charAt(i));
				}
				else if (k == 2) {
					ar3[i] = Character.getNumericValue(n.get(k).charAt(i));
				}
			}
		}
		
		countS = ar1.length;
		countW = ar2.length;
		countE = ar3.length;
		
		
		if (countS <= countE) { // pre: if # of spies less that # of enemies
			state = 0; // post: kill spy
		}
		
		if (countE == 0) { // pre: if # of spies = 0
			move(list, board); // post: move spy
		}
 				
		if (countS == 4 && countE == 0) { // if count of 4 spies in neighborhood and 0 enemies
			if (rand.nextBoolean()) {
				Spies x = new Spies(); // post: 50 % of time spies are safe and create another spy
				x.state = 3;
				x.xPos = xPos;
				x.yPos = yPos;
				move(list, x, board);
			}
		}

	}
	
}
