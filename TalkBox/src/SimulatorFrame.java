import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class SimulatorFrame extends JFrame {
	/**
	 * A visual representation of simulation app
	 */

	// ------------ Fields ---------------------
	JButton[] pics; // An array of buttons. Size initialized by the desired amount of buttons
	JPanel panel;   // The used panel
	// -----------------------------------------

	public SimulatorFrame(ActionListener l, int n) {
		super("FrameDemo");
		
		initializePanel(l, n);
		//frame.getContentPane().setBackground(Color.cyan);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1100, 600));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * @param n
	 * 			The number of buttons to be declared
	 * @param l	
	 * 			The action listener
	 * 
	 * A method that initialized the panel and provide the first view for the client
	 * Associate the action listener l to the buttons --- GridLayout ? -----
	 */
	private void initializePanel(ActionListener l, int n) {
		pics = new JButton[n];
		panel = new JPanel(new GridLayout(1, n, 1, 1));
		
		for (int i = 0; i < pics.length; i ++) {
			pics[i] = new JButton("Something");
		//	pics[i].setBackground(Color.BLUE);
			pics[i].setVisible(true);
			pics[i].addActionListener(l);
			panel.add(pics[i]);
		}
		
		this.add(panel);
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

		new SimulatorFrame(null, 4);

	}

}
