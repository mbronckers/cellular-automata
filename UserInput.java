import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * @author maxbronckers
 * @author jerilynzheng
 * @date Wednesday 29 March 2017
 * @update
 */
public class UserInput {
	public int wP;
	public int lP;
	public int rows;
	public int columns;
	
	// Initialize graphical user interface
	// io dialogue
	public void run() {
		String aStr = JOptionPane.showInputDialog(null, "Screen width of the world (pixels):"); 
		wP = Integer.parseInt(aStr); 
		String bStr = JOptionPane.showInputDialog(null, "Screen length of the world (pixels):"); 
		lP = Integer.parseInt(bStr); 
		String cStr = JOptionPane.showInputDialog(null, "# of Rows: "); 
		rows = Integer.parseInt(cStr); 
		String dStr = JOptionPane.showInputDialog(null, "# of Columns: "); 
		columns = Integer.parseInt(dStr); 
	
	}


	
	
}
