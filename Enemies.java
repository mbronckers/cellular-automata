import java.util.ArrayList;

/**
 * 
 * @author maxbronckers
 * @author jerilynzheng
 *
 */

public class Enemies extends Agents {
	int[] states = {5,6};
	
	public Enemies() {
		super();
	}

	public Enemies(int state, int yPos, int xPos) {
		super(state, yPos, xPos);
	}

	@Override
	void nextGen(int[][] board, ArrayList<Agents> list) {
		String type1 = "", type2 = "", type3 = ""; 								// strings for each type of organism to hold neighbors

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
				
				Spies spies = new Spies();
				for (int x : spies.states) {
					if (value == x) {			//section for organism Walls
						type3 += value;
					}
				}
				spies = null;
			}
		}
		
		neighbors.add(type1);		// add strings of each type of organisms to the list of all neighbors
		neighbors.add(type2);
		neighbors.add(type3);
		
		rules(neighbors, list, board);
	}

	/* pre: current state
	 * post: new state (after application of rules; could be change in neighborhood (# of agents, location))
	 * 
	 */
	public void rules(ArrayList<String> n, ArrayList<Agents> list, int[][] board) {
		
		int countE = 0, countW = 0, countS = 0; //spies, walls, enemies
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
		
		countE = ar1.length;
		countW = ar2.length;
		countS = ar3.length;
		
		if (countE == 1 && countS > 3) { // pre: if 1 enemy in neighborhood and # spies > 3
			if (rand.nextBoolean()) { // post: 50% of time create an extra enemy
				Enemies x = new Enemies();		
				x.state = 5;
				x.xPos = xPos; //new enemy takes the exact same position
				x.yPos = yPos;
				move(list, x, board); //new enemy moves the spot
			}
		}
		
		if (countE < countS) { // pre: more spies than enemies
			state = 0; // post: kill enemy
		}
		
		else if (countE == countS) { // pre: # ememies = # spies
			move(list, board); // post: move enemy
		}
		
		else if (countE > countS) { // pre: more enemies than spies
			if (rand.nextBoolean()) { // post: 50% chance of moving
				move(list, board);
			}
									//50% of staying
		}
		
		
	}
}
