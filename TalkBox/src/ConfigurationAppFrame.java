import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConfigurationAppFrame extends JFrame{
	/**
	 * A visual representation of the ConfigurationApp
	 * */
	
	// ------------- Fields ------------------------
	public static int page = 0; 			// The 'page' the user is currently in. 
	JPanel panel; 							// The panel we will work with
	
	JLabel title;		 				    // The title of the page
	JLabel labelForImage, labelForSound; 	// The labels for the sound and image buttons
	JLabel labelForChekBox;					// The label for the check box
	
	JButton uploadImage;					// A button for the user to upload an image
	JButton uploadSound;	 				// A button for the user to upload a sound
	JButton pickImage;						// A button for the user to pick from out image store
	JButton pickSound;						// A button for the user to pick from our sound store
	JCheckBox checkToSelfUpload;			// A check box for the user to check if wants to upload his own files
	
	JButton next;							// A button to go to the next page
	JButton previous;						// A button to go to the previous page
	
	// ---------------------------------------------
	
	
	/**
	 * A constructor that initialize JFrame and it's own configuration
	 * */
	public ConfigurationAppFrame (ActionListener l){
		super("Configuration App Wizard");
		initializePanel(l);
	}
	
	/**
	 * A method that initialized the panel and provide the first view for the client
	 * Associate the action listener l to the buttons and check boxes.
	 * 						--- cardLayout ? -----
	 * */
	private void initializePanel(ActionListener l) {
		
	}
	
	/**
	 * Use's the field 'page', then increment 'page' for the next future page update 
	 * */
	public void panelUpdate () {
		
	}
	
	/**
	 * A main method to test the view of the configuration app
	 * */
	public static void main (String[] args) {
		
	}
	
}
