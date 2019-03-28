package main.java.TalkBox;

/**
 * 
 * 
 * */

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

//import junit.framework.Test;


public class ConfigurationAppFrame extends JFrame implements Runnable{
	/**
	 * A visual representation of the ConfigurationApp
	 * */
	// ---------------------------------------------
	// ------------- Fields ------------------------
	public  int page = 0; 			// The 'page' the user is currently in.
	
	JPanel panel; 							// The first panel we will work with
	
	JLabel labelForTextField;					// The label for the check box
	
	JLabel title;		 				    // The title of the page
	JLabel labelForImage, labelForSound; 	// The labels for the sound and image buttons
	JLabel blankSpace = new JLabel(""); 	// Space holder
	
	public JButton pickImage;						// A button for the user to pick from out image store / upload at check
	public JButton pickSound;						// A button for the user to pick from our sound store / upload at check
	JButton startRecord;					// A button to start recording
	JButton stopRecord;					    // A button to stop recording
	boolean recordSelected = false;			// set to true if recording was selected. Adjust panel view accordingly
	
//	JCheckBox checkToSelfUploadSound;		// A check box for the user to check if wants to upload his own sound
//	JCheckBox checkToSelfUploadImage;		// A check box for the user to check if wants to upload his own image
	
	String[] dropDownImageStrings = {"", "Pick Image", "Upload Image"};
	public JComboBox<String> dropDownImage = new JComboBox<String>(dropDownImageStrings);
	String[] dropDownSoundStrings = {"", "Pick Sound", "Upload Sound" , "Record Sound"};
	public JComboBox<String> dropDownSound = new JComboBox<String>(dropDownSoundStrings);
	public JButton previewImage;					// preview the image the user chose
	public JButton previewSound;					// preview the sound the user chose
	
	
	public JButton next;							// A button to go to the next page
	JButton previous;						// A button to go to the previous page
	JButton exit;							// A button to exit
	
	/*String[] numbers = {"1", "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"}; // numbers used in combo box
	JComboBox<String> comboBox = new JComboBox<String>(numbers);  // The user puts the number of buttons he wants. Must be a natural number
	*/
	GroupLayout layout;
	public JTextField text;						// A text field for the user to input number of buttons
	
	//-- file fields --
	public JFileChooser file_Audio; 			   	// The file system that the client will see in order to  choose sound
	public JFileChooser file_Image; 			   	// The file system that the client will see in order to  choose image
	FileNameExtensionFilter filterImage;	// A filter that the client will see so he chooses the correct file format
	FileNameExtensionFilter filterSound;	// A filter that the client will see so he chooses the correct file format
	
	String homeDirectory = System.getProperty("user.dir");
	String saved_audio_path_M = "./imageReasource/soundRepository";	// mac/ linux/ unix
	String saved_audio_path_W = ".\\imageReasource\\soundRepository"; // windows
	String saved_image_path_M = "./imageReasource/imageRepository";	// mac/ linux/ unix
	String saved_image_path_W = ".\\imageReasource\\imageRepository"; // windows
	
	JTextField buttonName;		// The text field the user will use to write the name of the button
	String[] buttonNames;
	JLabel labelForButtonName;	// the label for the button
	JButton demo;	// a button the user will push and redirected to the simulation app
	
	JLabel configurationName;
	JTextField textConfName;
	
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
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
		demo = new JButton("Demo");
		demo.addActionListener(l);
		labelForTextField = new JLabel("");
		labelForButtonName = new JLabel("Give a name to the button: ");
		buttonName = new JTextField();
		previous = new JButton("Previous");
		previous.addActionListener(l);
		next = new JButton("Next");
		next.addActionListener(l);
		exit = new JButton("Exit");
		exit.addActionListener(l);
		text = new JTextField();
		configurationName = new JLabel("Name your profile:");
		textConfName = new JTextField();
		
		// ------------ FILE CHANGE ----
		this.file_Audio = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		this.file_Image = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		filterImage = new FileNameExtensionFilter("PNG, GIF and JPG images", "png", "gif", "jpg");    /////////// CHECK PROPER IMAGE FORMAT
		filterSound = new FileNameExtensionFilter("mp3 and wav", "mp3", "wav");    		  /////////// CHECK PROPER SOUND FORMAT
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
		this.setSize(455, 148);
		
		// Automatic gap insertion
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
	    
		
	    // Initialize components 
		title.setText("Welcome to the Configuration Wizard for your Talk Box");
		labelForTextField.setText("Please put the number of buttons  (1 to 100) default is 1: ");
	    
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(labelForTextField)
						.addComponent(this.configurationName)
						.addComponent(exit))
				.addGroup(layout.createParallelGroup()
						.addComponent(blankSpace)
						.addComponent(text)
						.addComponent(this.textConfName)
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
						.addComponent(this.configurationName)
						.addComponent(this.textConfName))
				.addGroup(layout.createParallelGroup()
						.addComponent(exit)
						.addComponent(next)
		));
		
		this.add(panel);
	}
	
	/**
	 * A popup error for the user to see when input is invalid
	 */
	public void popupError (String err) {
		JOptionPane.showMessageDialog(null, err);
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
		this.popupError("Invalid input. The input for number of buttons should be a natural number and you need to name your profile");
		return 0;
		//return Integer.parseInt((String) this.comboBox.getSelectedItem()); if combo box
	}
	
	public String getProfileName() {
		if (this.textConfName.getText().equals(""))
			this.popupError("Invalid input. The input for number of buttons should be a natural number and you need to name your profile");
		
		return this.textConfName.getText();
	}
	
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
			buttonNames[page-1] = buttonName.getText();
			page ++;
		}
		// If next goes to the first initial setting - special setting
		else if (page == 0) {
			buttonNames = new String[size];
			// set initial names to null
			for (int i = 0; i < buttonNames.length; i ++)
				buttonNames[i] = "";
				
			page ++;
			buttonName.setText("");
			firstPage();
			
		}
		// Middle setting
		else {
			
			//uncheck();
			buttonNames[page-1] = buttonName.getText();
			
			buttonName.setText(buttonNames[page]);
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
		//	buttonName.setText(buttonNames[page-1]);
			int helper = page - 1;
			title.setText("Please choose an image and sound for button " + helper);
			buttonName.setText(buttonNames[page-2]);
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
			buttonName.setText(buttonNames[page-2]);
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
		
		this.setSize(520, 220);
		
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
	    
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(dropDownImage)
						.addComponent(pickImage)
						.addComponent(this.previewImage)
						.addComponent(this.labelForButtonName)
						.addComponent(exit))
				.addGroup(layout.createParallelGroup()
						.addComponent(blankSpace)
						.addComponent(dropDownSound)
						.addComponent(pickSound)
						.addComponent(this.previewSound)
						.addComponent(this.buttonName)
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
						.addComponent(this.labelForButtonName)
						.addComponent(this.buttonName))
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
						.addComponent(demo))
				.addGroup(layout.createParallelGroup()
						.addComponent(blankSpace)
						.addComponent(blankSpace)
						.addComponent(exit))
		);
		
		layout.linkSize(SwingConstants.HORIZONTAL, previous, exit, demo);
		 
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
						.addComponent(demo)
						.addComponent(exit))
		);
		
	}
	
	/**
	 * The method returns the text in the button
	 * @return
	 */
	public String getText () {
		return this.buttonName.getText();
	}
	
	/**
	 * The method prints the button names into a file called names
	 * @throws IOException 
	 */
	public void printNamesToFile () throws IOException {
		File names;
		if (System.getProperty("os.name").startsWith("Windows"))
			names = new File (".\\imageReasource\\TalkBoxData\\" + this.textConfName.getText() + "\\names.txt");
		else
			names = new File ("./imageReasource/TalkBoxData/" + this.textConfName.getText() + "/names.txt");
		
		if (!names.exists()) 
			names.createNewFile();
		
	/*	for(File file: names.listFiles()) 
		    if (!file.isDirectory()) 
		        file.delete();*/
		
		PrintWriter pt = new PrintWriter(names);

		for (int i = 0; i < this.buttonNames.length; i ++) 
			pt.println(buttonNames[i]);
		
		pt.close();
	}
	
	
	/**
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
			
			this.setSize(610, 220);
			
			layout.setAutoCreateContainerGaps(true);
			layout.setAutoCreateGaps(true);
		    
			layout.setHorizontalGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
							.addComponent(title)
							.addComponent(dropDownImage)
							.addComponent(pickImage)
							.addComponent(this.previewImage)
							.addComponent(this.labelForButtonName)
							.addComponent(exit))
					.addGroup(layout.createParallelGroup()
							.addComponent(blankSpace)
							.addComponent(dropDownSound)
							.addGroup(layout.createSequentialGroup()
									.addComponent(this.startRecord)
									.addComponent(this.stopRecord))
							.addComponent(this.previewSound)
							.addComponent(this.buttonName)
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
							.addComponent(this.labelForButtonName)
							.addComponent(this.buttonName))
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

		this.file_Image.setDialogTitle("Choose an image");
		file_Image.setAcceptAllFileFilterUsed(false); // ?
		file_Image.changeToParentDirectory();
		
		file_Image.addChoosableFileFilter(filterImage);
		file_Image.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());

		int retV = file_Image.showOpenDialog(null);
		if (retV == JFileChooser.APPROVE_OPTION) {
			return file_Image.getSelectedFile().getPath();
		}
		return "";
	}
	
	/**
	 * The method opens up a 'file chooser' to select a sound
	 * @return the path of the file chosen 
	 * */
	public String pressedUploadSound () {
		
		this.file_Audio.setDialogTitle("Choose a sound");
		file_Audio.setAcceptAllFileFilterUsed(false); // ?
		
		file_Audio.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());

		file_Audio.addChoosableFileFilter(filterSound);

		int retV = file_Audio.showOpenDialog(null);
		if (retV == JFileChooser.APPROVE_OPTION) {
			return file_Audio.getSelectedFile().getPath();
		}
		
		return "";
	}

	public String pressedPickSound () {

		file_Audio.setDialogTitle("Choose a sound");
		file_Audio.setAcceptAllFileFilterUsed(false); // ?
	
		file_Audio.addChoosableFileFilter(filterSound);
		
		if (System.getProperty("os.name").startsWith("Windows"))
			file_Audio.setCurrentDirectory(new File (this.saved_audio_path_W));
		else
			file_Audio.setCurrentDirectory(new File (this.saved_audio_path_M));
		
		//This should return the correct file from the input stream
		//File temp_image = getResourceAsFile(this.saved_audio_path_W);
		
		//file_Audio.setCurrentDirectory(temp_image);
		
		//InputStream inputStream = MainConfiguration.class.getResourceAsStream(this.saved_audio_path_M);

		int retV = file_Audio.showOpenDialog(null);
		if (retV == JFileChooser.APPROVE_OPTION) {
			return file_Audio.getSelectedFile().getPath();
		}
		
		return "";
	}
	
	public String pressedPickImage () {
		file_Image.setDialogTitle("Choose an Image");
		file_Image.setAcceptAllFileFilterUsed(false); // ?
	
		file_Image.addChoosableFileFilter(filterImage);
		
		if (System.getProperty("os.name").startsWith("Windows"))
			file_Image.setCurrentDirectory(new File (this.saved_image_path_W));
		else
			file_Image.setCurrentDirectory(new File (this.saved_image_path_M));
		
	//	File temp_image = getResourceAsFile(this.saved_image_path_M);
		
	//	file_Image.setCurrentDirectory(temp_image);

		int retV = file_Image.showOpenDialog(null);
		if (retV == JFileChooser.APPROVE_OPTION) {
			return file_Image.getSelectedFile().getPath();
		}
		
		return "";
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public static File getResourceAsFile(String resourcePath) {
	    try {
	        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
	        if (in == null) {
	            return null;
	        }

	        File tempFile = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
	        tempFile.deleteOnExit();

	        try (FileOutputStream out = new FileOutputStream(tempFile)) {
	            //copy stream
	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = in.read(buffer)) != -1) {
	                out.write(buffer, 0, bytesRead);
	            }
	        }
	        return tempFile;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
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
