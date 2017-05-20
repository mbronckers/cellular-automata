import java.util.ArrayList;
import java.util.Random;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

/**
 * @author maxbronckers
 * @author jerilynzheng
 * @date Wednesday 29 March 2017
 * @update
 */
public class World extends GraphicsProgram {

	// use arrayList to hold all organisms
	// run through arraylist, get position, draw on board
	// fill rest with 0
	// nextGen: update location of organism (instance var of org)
	// nextGen (for each type of organism): run through arrayList, check if xPos/yPos equals to a neighbor --> act accordingly
	
	public int[][] board;
	public ArrayList<Agents> list = new ArrayList<Agents>(); // list that holds all organisms
	public Random r = new Random();
	public Grid z = new Grid();
	
	public World(int width, int length) {
		board = new int[length][width];
	}
	
	public void clearWorld() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				board[r][c] = 0;
			}
		}

	}
	
	public void add(Agents x) {
		list.add(x);
		clearWorld();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).state == 0) { 				//state 0 means a dead organism 
				list.remove(list.get(i));				//remove the organism
				i--;
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
				board[list.get(i).yPos][list.get(i).xPos] = list.get(i).state;
		}
	}
	
	public void updateOrganisms() {
		clearWorld();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).state == 0) { 				//state 0 means a dead organism 
				list.remove(list.get(i));				//remove the organism
				i--;
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
				board[list.get(i).yPos][list.get(i).xPos] = list.get(i).state;
		}
		
	}
	
	public void showWorld(GCanvas canvas, int w, int l, int lP, int wP) {
		int[] statesSpies = {3,4};
		int[] statesWalls = {1,2};
		int[] statesEnemies = {5,6};
	
		canvas.removeAll();
		
		z.drawLines(canvas, w, l, lP, wP);
		
//		for (int r = 0; r < board.length; r++) {
//			for (int c = 0; c < board[0].length; c++) { //CONSOLE PRINTING
//				int value = board[r][c];
//				System.out.print(value);
//			}
//			System.out.println();
//		}
//		System.out.println();	
		
		for (Agents x : list) {
			int xPos = x.xPos;
			int yPos = x.yPos;
			
			for (int s : statesSpies) {
				if (x.state == s) {
					z.addSpy(canvas, xPos, yPos);
				}
			}
		
			for (int s : statesWalls) {
				if (x.state == s) {
					z.addWall(canvas, xPos, yPos);
				}
			}
			
			for (int s : statesEnemies) {
				if (x.state == s) {
					z.addEnemy(canvas, xPos, yPos);
				}
			}
		}
		try {								//let the thread sleep
		    Thread.sleep(100);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
	}
	
	public void nextGen() {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).nextGen(board, list);
		}
		
		updateOrganisms();
	}
}
