
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class ConfigurationAppFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * A visual representation of the ConfigurationApp
	 * */
	
	// ------------- Fields ------------------------
	public static int page = 0; 			// The 'page' the user is currently in.
	
	JPanel panel; 							// The first panel we will work with
	
	JLabel labelForTextField;					// The label for the check box
	
	JLabel title;		 				    // The title of the page
	JLabel labelForImage, labelForSound; 	// The labels for the sound and image buttons
	JLabel blankSpace = new JLabel(""); 	// Space holder
	
	JButton uploadImage;					// A button for the user to upload an image
	JButton uploadSound;	 				// A button for the user to upload a sound
	JButton pickImage;						// A button for the user to pick from out image store
	JButton pickSound;						// A button for the user to pick from our sound store
	JCheckBox checkToSelfUpload;			// A check box for the user to check if wants to upload his own files
	
	JButton next;							// A button to go to the next page
	JButton previous;						// A button to go to the previous page
	JButton exit;							// A button to exit
	
	String[] numbers = {"1", "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"}; // numbers used in combo box
	JComboBox<String> comboBox = new JComboBox<String>(numbers);  // The user puts the number of buttons he wants. Must be a natural number
	
	GroupLayout layout;
	Container container;					// Container
	
	private static final int WIDTH  = 400;
	private static final int HEIGHT = 400;	
	// ---------------------------------------------
	
	
	/**
	 * @param l
	 * 	     the action listener
	 * 
	 * A constructor that initialize JFrame and it's own configuration
	 * */
	public ConfigurationAppFrame (ActionListener l){
		super("Configuration App Wizard");
		
		JFrame.setDefaultLookAndFeelDecorated(true);								// Check later
		this.setSize(ConfigurationAppFrame.WIDTH, ConfigurationAppFrame.HEIGHT);
		this.setSize(600, 150);
		this.setResizable(false);
        initializePanel(l);	
        
       
	}
	
	/**
	 * @param l
	 * 			the action listener
	 * 
	 * A method that initialized the panel and provide the first view for the client
	 * Associate the action listener l to the buttons and check boxes.
	 * 						--- cardLayout ? -----
	 * */
	private void initializePanel(ActionListener l) {
		// Associate layout with panel
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		// Automatic gap insertion
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
	    
		
	    // Initialize components
		title = new JLabel("Welcome to the Configuration Wizard for your Talk Box");
		labelForTextField = new JLabel("Please select the number of buttons you want to have: ");
		previous = new JButton("Previous");
		next = new JButton("next");
		exit = new JButton("Exit");
		
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
	    
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(labelForTextField)
						.addComponent(exit))
				.addGroup(layout.createParallelGroup()
						.addComponent(blankSpace)
						.addComponent(comboBox)
						.addGroup(layout.createSequentialGroup()
								.addComponent(previous)
								.addComponent(next))
		));
		
		layout.linkSize(SwingConstants.HORIZONTAL, previous, next);
		 
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(blankSpace))
				.addGroup(layout.createParallelGroup()
						.addComponent(labelForTextField)
						.addComponent(comboBox))
				.addGroup(layout.createParallelGroup()
						.addComponent(exit)
						.addGroup(layout.createParallelGroup()
								.addComponent(previous)
								.addComponent(next))
		));
	
		this.add(panel);
	}
	
	/**
	 * @param size
	 * 			The number of buttons the user works with 
	 * 
	 * Use's the field 'page', then increment 'page' for the next future page update 
	 * The method updated the view to go to the next page 
	 * */
	public void PressedNext (int size) {
		if (page == size)
			lastPage();
		
	}
	
	public void lastPage () {}
	
	/**
	 * A main method to test the view of the configuration app
	 * */
	public static void main (String[] args) {
		
		
		 EventQueue.invokeLater(() -> {

			    ConfigurationAppFrame conf = new ConfigurationAppFrame(null);
				conf.setDefaultCloseOperation(EXIT_ON_CLOSE);
				conf.setVisible(true);
				conf.pack();
	        });
	}
	
}
