import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class SimulatorFrame extends JFrame {
	/**
	 * A visual representation of simulation app
	 */

	// ------------ Fields ---------------------
	JButton[] pics; // An array of buttons. Size initialized by the desired amount of buttons

	// -----------------------------------------

	public SimulatorFrame(ActionListener l, int n) {
		JFrame frame = new JFrame("FrameDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setPreferredSize(new Dimension(1100, 600));
		
		frame.getContentPane().setBackground(Color.cyan);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * A method that initialized the panel and provide the first view for the client
	 * Associate the action listener l to the buttons --- BorderLayout ? -----
	 */
	private void initializePanel(ActionListener l) {

	}

	/**
	 * play the sound based on the pressed button
	 */
	public static void panelUpdate(JButton b, String buttonName) {

	}

	/**
	 * A main method to test the view of the simulator app
	 */
	public static void main(String[] args) {

		new SimulatorFrame(null, 1);

	}

}
