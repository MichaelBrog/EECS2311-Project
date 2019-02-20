
/**
 * 
 * Text field - restrict to 100
 * progres bar
 * JFilerChooser
 * 
 * 
 * */

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


public class ConfigurationAppFrame extends JFrame implements Runnable{
	/**
	 * A visual representation of the ConfigurationApp
	 * */
	// ---------------------------------------------
	// ------------- Fields ------------------------
	public static int page = 0; 			// The 'page' the user is currently in.
	
	JPanel panel; 							// The first panel we will work with
	
	JLabel labelForTextField;					// The label for the check box
	
	JLabel title;		 				    // The title of the page
	JLabel labelForImage, labelForSound; 	// The labels for the sound and image buttons
	JLabel blankSpace = new JLabel(""); 	// Space holder
	
	JButton pickImage;						// A button for the user to pick from out image store / upload at check
	JButton pickSound;						// A button for the user to pick from our sound store / upload at check
	JButton startRecord;					// A button to start recording
	JButton stopRecord;					    // A button to stop recording
	boolean recordSelected = false;			// set to true if recording was selected. Adjust panel view accordingly
	
//	JCheckBox checkToSelfUploadSound;		// A check box for the user to check if wants to upload his own sound
//	JCheckBox checkToSelfUploadImage;		// A check box for the user to check if wants to upload his own image
	
	String[] dropDownImageStrings = {"", "Pick Image", "Upload Image"};
	JComboBox<String> dropDownImage = new JComboBox<String>(dropDownImageStrings);
	String[] dropDownSoundStrings = {"", "Pick Sound", "Upload Sound" , "Record Sound"};
	JComboBox<String> dropDownSound = new JComboBox<String>(dropDownSoundStrings);
	JButton previewImage;					// preview the image the user chose
	JButton previewSound;					// preview the sound the user chose
	
	
	JButton next;							// A button to go to the next page
	JButton previous;						// A button to go to the previous page
	JButton exit;							// A button to exit
	
	/*String[] numbers = {"1", "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"}; // numbers used in combo box
	JComboBox<String> comboBox = new JComboBox<String>(numbers);  // The user puts the number of buttons he wants. Must be a natural number
	*/
	GroupLayout layout;
	JTextField text;						// A text field for the user to input number of buttons
	
	//-- file fields --
	JFileChooser file; 			   			// The file system that the client will see in order to  choose sound / image
	FileNameExtensionFilter filterImage;	// A filter that the client will see so he chooses the correct file format
	FileNameExtensionFilter filterSound;	// A filter that the client will see so he chooses the correct file format
	
	//-- previous options saver --
	
	
	// ---------------------------------------------
	// ---------------------------------------------
	
	/**
	 * @param l
	 * 	     the action listener
	 * 
	 * @param i
	 * 		 the item listener for the check boxes
	 * 
	 * A constructor that initialize JFrame and it's own configuration
	 * */
	public ConfigurationAppFrame (ActionListener l, ItemListener i){
		super("Configuration App Wizard");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);							
		
		this.setResizable(false);
		initializeComponents(l, i);
        initializePanel();	
	}
	
	/**
	 * @param l
	 * 			the action listener
	 * 
	 * @param i
	 * 			the item listener for the check boxes
	 * 
	 * A method that initialized the components
	 * Associate the action listener l to the buttons and check boxes.
	 * 
	 * */
	private void initializeComponents (ActionListener l, ItemListener i) {
		title = new JLabel();
		labelForTextField = new JLabel("");
		previous = new JButton("Previous");
		previous.addActionListener(l);
		next = new JButton("Next");
		next.addActionListener(l);
		exit = new JButton("Exit");
		exit.addActionListener(l);
		text = new JTextField();
		
		// ------------ FILE CHANGE ----
		file = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		filterImage = new FileNameExtensionFilter("PNG and GIF images", "png", "gif");    /////////// CHECK PROPER IMAGE FORMAT
		filterSound = new FileNameExtensionFilter("mp4 and wav", "mp4", "wav");    		  /////////// CHECK PROPER SOUND FORMAT
		//------------------------------
		
		// Initialized for later use:
		pickImage = new JButton("Pick Image");
		pickImage.addActionListener(l);
		pickSound = new JButton("Pick Sound");
		pickSound.addActionListener(l);
/*		checkToSelfUploadSound = new JCheckBox("Upload sound yourself");
		checkToSelfUploadSound.addItemListener(i);*/
	/*	checkToSelfUploadImage = new JCheckBox("Upload image yourself");
		checkToSelfUploadImage.addItemListener(i);*/
		previewImage = new JButton("Preview Image");
		previewImage.addActionListener(l);
		previewSound = new JButton("Preview Sound");
		previewSound.addActionListener(l);
		startRecord = new JButton("Start Recording");
		startRecord.addActionListener(l);
		stopRecord = new JButton("Stop Recording");
		stopRecord.addActionListener(l);
		
		this.dropDownImage.addItemListener(i);
		this.dropDownSound.addItemListener(i);
		
		panel = new JPanel();
		layout = new GroupLayout(panel);
		panel.setLayout(layout);
	}
	
	/**
	 * 
	 * A method that initialized the panel and provide the first view for the client
	 * 
	 * */
	private void initializePanel() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setSize(455, 118);
		
		// Automatic gap insertion
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
	    
		
	    // Initialize components 
		title.setText("Welcome to the Configuration Wizard for your Talk Box");
	//	labelForTextField.setText("Please select the number of buttons you want to have: ");
		labelForTextField.setText("Please put the number of buttons  (1 to 100) default is 1: ");
		
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
	    
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(labelForTextField)
						.addComponent(exit))
				.addGroup(layout.createParallelGroup()
						.addComponent(blankSpace)
						.addComponent(text)
						.addComponent(next)
		));
		
		 
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(blankSpace))
				.addGroup(layout.createParallelGroup()
						.addComponent(labelForTextField)
						.addComponent(text))
				.addGroup(layout.createParallelGroup()
						.addComponent(exit)
						.addComponent(next)
		));
		
		this.add(panel);
	}
	
	/**
	 * @return the integer value which represents the number of buttons selected
	 * If the representation is not an integer - returns 0 for invalid input
	 * */
	public int getSizeButtons() {
		if (text.getText().matches("[\\d]+")) {
			if (Integer.parseInt(text.getText()) > 0 && Integer.parseInt(text.getText()) <= 100)
				return Integer.parseInt(text.getText());
		}
		return 0;
		//return Integer.parseInt((String) this.comboBox.getSelectedItem()); if combo box
	}
	
/*	*//**
	 * The method unchecks the check boxes 
	 * *//*
	private void uncheck () 
	{
		if (checkToSelfUploadSound.isSelected())
			checkToSelfUploadSound.setSelected(false);
		
		if (checkToSelfUploadImage.isSelected())
			checkToSelfUploadImage.setSelected(false);
		
	//	uploadImageCheckBox();
	//	uploadSoundCheckBox();
	}
	*/
	/**
	 * @param size
	 * 			The number of buttons the user works with 
	 * 
	 * Uses the field 'page', then increment 'page' for the next future page update 
	 * The method updated the view to go to the next page 
	 * */
	public void pressedNext (int size) {

		// If next goes to the last page - special setting
		if (page == size) {
			lastPage();
			page ++;
		}
		// If next goes to the first initial setting - special setting
		else if (page == 0) {
			page ++;
			firstPage();
			
		}
		// Middle setting
		else {
			
			//uncheck();
			int helper = page + 1;
			if (this.recordSelected) {
				recordSelected = false;
				repaintForRecord();
			}
			title.setText("Please choose an image and sound for button " + helper);
			page ++;
		}
	}
	
	/**
	 *	@param size
	 *			The number of buttons the user works with
	 *
	 * Uses the field page to go back words, then decrement it
	 * The method updates the view to go to the previous page
	 * 
	 * */
	public void pressedPrevious (int size) {
		// If the current page is the last page, needs special setting
		if (page == size + 1) {
			firstPage();
			int helper = page - 1;
			title.setText("Please choose an image and sound for button " + helper);
		}
		// If the current page is the first initialized page
		else if (page == 1) {
			refresh();
			initializePanel();
		//	uncheck();
		}
		// Middle setting
		else {
		//	uncheck();
			int helper = page - 1;
			if (this.recordSelected) {
				recordSelected = false;
				repaintForRecord();
			}
			title.setText("Please choose an image and sound for button " + helper);
		}
		page --;
	}
	
	private void refresh () {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		layout = new GroupLayout(panel);
		panel.setLayout(layout);
	}
	
	/**
	 *  Sets the outline of the first page
	 * */
	private void firstPage () {
		title.setText("Please choose an image and sound for button " + (page));
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		layout = new GroupLayout(panel);
		panel.setLayout(layout);
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		this.setSize(520, 190);
		
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
	    
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(dropDownImage)
						.addComponent(pickImage)
						.addComponent(this.previewImage)
						.addComponent(exit))
				.addGroup(layout.createParallelGroup()
						.addComponent(blankSpace)
						.addComponent(dropDownSound)
						.addComponent(pickSound)
						.addComponent(this.previewSound)
						.addGroup(layout.createSequentialGroup()
							.addComponent(previous)
							.addComponent(next)))
		);
		
		layout.linkSize(SwingConstants.HORIZONTAL, previous, next, exit);
		layout.linkSize(SwingConstants.HORIZONTAL, previewImage, previewSound, pickImage, pickSound, dropDownImage, dropDownSound);
		 
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(blankSpace))
				.addGroup(layout.createParallelGroup()
						.addComponent(dropDownImage)
						.addComponent(dropDownSound))
				.addGroup(layout.createParallelGroup()
						.addComponent(pickImage)
						.addComponent(pickSound))
				.addGroup(layout.createParallelGroup()
						.addComponent(this.previewImage)
						.addComponent(this.previewSound))
				.addGroup(layout.createParallelGroup()
						.addComponent(exit)
						.addGroup(layout.createParallelGroup()
								.addComponent(previous)
								.addComponent(next)))
		);
	
	}
	
	/**
	 *  Set up of the last page of the configuration wizard
	 * */
	private void lastPage () {
		title.setText("Your configuration is complete.");
		
		this.setSize(410, 90);
		
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(blankSpace)
						.addComponent(blankSpace)
						.addComponent(previous))
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(blankSpace)
						.addComponent(blankSpace))
				.addGroup(layout.createParallelGroup()
						.addComponent(blankSpace)
						.addComponent(blankSpace)
						.addComponent(exit))
		);
		
		layout.linkSize(SwingConstants.HORIZONTAL, previous, exit);
		 
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(blankSpace)
						.addComponent(title)
						.addComponent(blankSpace))
				.addGroup(layout.createParallelGroup()
						.addComponent(blankSpace)
						.addComponent(blankSpace)
						.addComponent(blankSpace))
				.addGroup(layout.createParallelGroup()
						.addComponent(previous)
						.addComponent(blankSpace)
						.addComponent(exit))
		);
		
	}
/*	
	*//**
	 * Picked from the image drop down menu
	 * Changes the text on the button
	  */
	public void uploadImageDropMenu (String text) { 
		pickImage.setText(text);
	}
	
	/**
	 * Picked from the sound drop down menu
	 * Changes the text on the button
	  */
	public void uploadSoundDropMenu (String text) {
		if (!text.equals("Record Sound")) {
			pickSound.setText(text);
			if (this.recordSelected) {
				this.recordSelected = false;
				repaintForRecord();
			}
		}
		else {
			this.recordSelected = true;
			repaintForRecord();
		}
	}
	
	/**
	 * Resets the buttons of pickImage and pickSound for the next button update
	 * */
	public void resetDropMenu () {
		pickImage.setText("Pick Image");
		pickSound.setText("Pick Sound");
	}
	
	public void repaintForRecord() {
		if (recordSelected) {
			
			title.setText("Please choose an image and sound for button " + (page));
			panel.removeAll();
			panel.revalidate();
			panel.repaint();
			layout = new GroupLayout(panel);
			panel.setLayout(layout);
			JFrame.setDefaultLookAndFeelDecorated(true);
			
			this.setSize(610, 190);
			
			layout.setAutoCreateContainerGaps(true);
			layout.setAutoCreateGaps(true);
		    
			layout.setHorizontalGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
							.addComponent(title)
							.addComponent(dropDownImage)
							.addComponent(pickImage)
							.addComponent(this.previewImage)
							.addComponent(exit))
					.addGroup(layout.createParallelGroup()
							.addComponent(blankSpace)
							.addComponent(dropDownSound)
							.addGroup(layout.createSequentialGroup()
									.addComponent(this.startRecord)
									.addComponent(this.stopRecord))
							.addComponent(this.previewSound)
							.addGroup(layout.createSequentialGroup()
								.addComponent(previous)
								.addComponent(next)))
			);
			
			layout.linkSize(SwingConstants.HORIZONTAL, stopRecord, next, previous, startRecord, exit, previewImage , pickImage, dropDownImage, dropDownSound, previewSound);
			//layout.linkSize(SwingConstants.HORIZONTAL, previewImage , pickImage, dropDownImage, dropDownSound);
			 
			layout.setVerticalGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
							.addComponent(title)
							.addComponent(blankSpace))
					.addGroup(layout.createParallelGroup()
							.addComponent(dropDownImage)
							.addComponent(dropDownSound))
					.addGroup(layout.createParallelGroup()
							.addComponent(pickImage)
							.addGroup(layout.createParallelGroup()
									.addComponent(this.startRecord)
									.addComponent(this.stopRecord)))
					.addGroup(layout.createParallelGroup()
							.addComponent(this.previewImage)
							.addComponent(this.previewSound))
					.addGroup(layout.createParallelGroup()
							.addComponent(exit)
							.addGroup(layout.createParallelGroup()
									.addComponent(previous)
									.addComponent(next)))
			);
		}
		else
			firstPage();
			
	}
	
	/**
	 * The method opens up a 'file chooser' to select an image
	 * @return the path of the file chosen
	 * */
	public String pressedUploadImage () {
		file.setDialogTitle("Choose an image");
		file.setAcceptAllFileFilterUsed(false); // ?
		
		file.addChoosableFileFilter(filterImage);

		int retV = file.showOpenDialog(null);
		if (retV == JFileChooser.APPROVE_OPTION) {
			return file.getSelectedFile().getPath();
		}
		return "";
	}
	
	/**
	 * The method opens up a 'file chooser' to select a sound
	 * @return the path of the file chosen 
	 * */
	public String pressedUploadSound () {
		file.setDialogTitle("Choose a sound");
		file.setAcceptAllFileFilterUsed(false); // ?
		
		file.addChoosableFileFilter(filterSound);

		int retV = file.showOpenDialog(null);
		if (retV == JFileChooser.APPROVE_OPTION) {
			return file.getSelectedFile().getPath();
		}
		
		return "";
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
	
/*	*//**
	 * A main method to test the view of the configuration app
	 * *//*
	public static void main (String[] args) {
		
		Scanner scan = new Scanner(System.in);
		ConfigurationAppFrame conf = new ConfigurationAppFrame(null, null);
		conf.setVisible(true);
		conf.pack();
		int i = scan.nextInt();
		conf.pressedNext(4);
		i = scan.nextInt();
	//	conf.uploadImageCheckBox();
		i = scan.nextInt();
	//	conf.uploadSoundCheckBox();
		System.out.println(ConfigurationAppFrame.page);
		conf.pressedNext(4);
		i = scan.nextInt();
		System.out.println(ConfigurationAppFrame.page);
		conf.pressedNext(ConfigurationAppFrame.page);
		
		ConfigurationAppFrame.page = 3;
		
		System.out.println("page " + ConfigurationAppFrame.page);
		i = scan.nextInt();
		conf.pressedPrevious(2);
		
		ConfigurationAppFrame.page = 1;
		
		System.out.println("page " + ConfigurationAppFrame.page);
		i = scan.nextInt();
		conf.pressedPrevious(2);
		
		System.out.println("page " + ConfigurationAppFrame.page);
		i = scan.nextInt();
		//conf.pressedPrevious(2);
		
		scan.close();
	}*/
	
}

