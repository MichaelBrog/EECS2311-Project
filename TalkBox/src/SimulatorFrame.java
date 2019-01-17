import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SimulatorFrame extends JFrame{
/**
 * A visual representation of simulation app
 * */
	
	// ------------ Fields ---------------------
	JButton []pics;							// An array of buttons. Size initialized by the desired amount of buttons
	
	
	
	// -----------------------------------------
	
	public SimulatorFrame (ActionListener l, int n) {	
		JFrame frame = new JFrame("FrameDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel emptyLabel = new JLabel("Demo frame");
        emptyLabel.setPreferredSize(new Dimension(1650, 1000));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        
		
        frame.pack();
        frame.setVisible(true);
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
	public static void panelUpdate (JButton b, String buttonName) {
		
		b=new JButton(buttonName, new ImageIcon("play.png"));    
		b.setBounds(100,100,140, 40);
	}
	
	/**
	 * A main method to test the view of the simulator app
	 * */
	public static void main (String[] args) {
		JButton b1 = null,b2=null,b3=null;
		
		new SimulatorFrame(null, 1);
		panelUpdate(b1, "Happy");
		panelUpdate(b1, "Sad");
		panelUpdate(b1, "Angry");
	}
	
	
}
