import java.util.ArrayList;


/**
 * @author maxbronckers
 * @author jerilynzheng
 */
public class Walls extends Agents {

	int[] ruleset;
	int[] states = {1,2};
	
	public Walls(int s, int y, int x) {
		super(s, y, x);
	}
	
	public Walls(){
		super();
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
				
				Enemies enemy = new Enemies(); 		//create object to access possible states
				for (int x : enemy.states) {
					if (value == x) {			//section for organism Spies
						type2 += value;
					}
				}
				enemy = null;				//delete instance of Spies (aka object) from heap memory		
			
				Spies spies = new Spies();
				for (int x : spies.states) {
					if (value == x) {			//section for organism Walls
						type3 += value;
					}
				}
				spies = null;
				
			}	
		}
		
		neighbors.add(type1);		
		neighbors.add(type2);
		neighbors.add(type3);
	
		rules(neighbors, list, board);

	}
	
	/* pre: current state
	 * post: new state (after application of rules; could be change in neighborhood (# of agents, location))
	 * 
	 */
	public void rules(ArrayList<String> n, ArrayList<Agents> list, int[][] board) {
		
		int countW = 0, countE = 0, countS = 0; // walls, enemies, spies
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
		
		countW = ar1.length;
		countE = ar2.length;
		countS = ar3.length;

		
		if (countE == countS && countE != 0) { // pre: # enemies = # spies && at least one of each
			if (rand.nextBoolean()) { // post: 50% of time create new wall
				Walls x = new Walls(); 
				x.state = 1;
				x.xPos = xPos;
				x.yPos = yPos;
				move(list, x, board);
			}
		}
		
	}
}
