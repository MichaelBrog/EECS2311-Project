import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SimulatorFrame extends JFrame{
/**
 * A visual representation of simulation app
 * */
	
	// ------------ Fields ---------------------
	JButton []pics;							// An array of buttons. Size initialized by the desired amount of buttons
	
	
	
	// -----------------------------------------
	
	public SimulatorFrame (ActionListener l, int n) {
		super("Simulator App");
		initializePanel(l);
	}
	
	/**
	 * A method that initialized the panel and provide the first view for the client
	 * Associate the action listener l to the buttons
	 * 						--- BorderLayout ? -----
	 * */
	private void initializePanel(ActionListener l) {
		
	}
	
	/**
	 * play the sound based on the pressed button
	 * */
	public void panelUpdate (JButton b) {
		
	}
	
	/**
	 * A main method to test the view of the simulator app
	 * */
	public static void main (String[] args) {
		
	}
	
	
}
