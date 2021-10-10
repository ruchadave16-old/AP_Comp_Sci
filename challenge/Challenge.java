import javax.swing.*;
import java.awt.*;

public class Challenge {
	JFrame frame;
	JPanel panel;
	JLabel label;

	public Challenge() {
	//step 1: build the frame
		
		//create the frame object(swing container for all elements)
		frame = new JFrame("Challenge Problem 2.0 Advanced GUI Version");
		//set default size of frame
		frame.setSize(300, 300);
		//make exit button work
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	//step 2: create panels 
		
		//make a new JPanel object 
		//JPanel is a container that holds groups of componenets  
		panel = new JPanel();
		
		
	//Step 3: create components
		
		//make a jlabel objet to hold the text 
		label = new JLabel("This is a Challenge Problem!");
		
	//step 4: Add components to the panels 
		
		//add components to the panel (panel layout can also be done here) 
		panel.add(label);
		
	//step 5: add panels to the frame and set layout for panels 
	
		// using borderlayout to center the panel in the frame 
		frame.getContentPane().add(BorderLayout.CENTER, panel);
	
	//Step 6: set the frame to be visible
	
		//make the frame visible 
		frame.setVisible(true);

	}

	public String findIteration(int iterations) { //Iterations has to be 26 or less
		if (iterations < 27) {
			String ans = "";
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			for (int i = 0; i < iterations; i++) {
				String nextChar = Character.toString(alphabet.charAt(i));
				ans = ans + nextChar + ans;
			}
			JOptionPane.showMessageDialog(frame, ans);
			JOptionPane.showMessageDialog(frame, ans.length());
			return ans;
		}
		else {
			JOptionPane.showMessageDialog(frame, "This number is too high");
			return null;
		}
	}

	public static void main(String args[]) {
		Challenge c = new Challenge();

		String iter = JOptionPane.showInputDialog("Please enter which iteration you wish to see (Should be less than 27)");
		int i = Integer.parseInt(iter);
		c.findIteration(i);

	}
}