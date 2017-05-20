import java.awt.*;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/* This class implements all the graphics of the world onto a grid.
 * 
 */
public class Grid extends GraphicsProgram {
	public static int rowWidth;
	public static int rowHeight;
	
	public Grid() {
	}
	
	/* Pre: GCanvas, # of rows and columns, grid size
	 * Post: grid of specifications
	 */
	public void drawLines(GCanvas canvas, int rows, int columns, int height, int width) {
		rowHeight = height / rows;
		for (int i = 0; i <= rows; i++) {
			GLine rowsLine = new GLine(0, i * rowHeight, width, i * rowHeight);
			canvas.add(rowsLine);
		}
		rowWidth = width / columns;
		for (int j = 0; j <= columns; j++) {
			GLine columnsLine = new GLine(j * rowWidth, 0, j * rowWidth, height);
			canvas.add(columnsLine);
		}
	}
	/* Pre: GCanvas, x and y coordinates
	 * Post: spy in specified location
	 */
	public void addSpy(GCanvas canvas, int x, int y) {
		x *= rowWidth;
		y *= rowHeight;
		
		GImage spy = new GImage("spy.jpeg", rowWidth, rowHeight);
		spy.setBounds(x, y, rowWidth, rowHeight);
		canvas.add(spy);
	}
	
	/* Pre: GCanvas, x and y coordinates
	 * Post: wall in specified location
	 */
	public void addWall(GCanvas canvas, int x, int y) {
		x *= rowWidth;
		y *= rowHeight;
		
		GImage wall = new GImage("wall.jpeg", rowWidth, rowHeight);
		wall.setBounds(x, y, rowWidth, rowHeight);
		canvas.add(wall);
	}
	
	/* Pre: GCanvas, x and y coordinates
	 * Post: enemy in specified location
	 */
	public void addEnemy(GCanvas canvas, int x, int y) {
		x *= rowWidth;
		y *= rowHeight;

		GImage enemy = new GImage("enemy.jpeg", rowWidth, rowHeight);
		enemy.setBounds(x, y, rowWidth, rowHeight);
		canvas.add(enemy);
	}
}
