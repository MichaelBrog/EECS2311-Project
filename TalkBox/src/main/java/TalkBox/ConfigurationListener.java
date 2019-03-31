package main.java.TalkBox;
/*
 * write object/ read object
 * */
 
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities; 

public class ConfigurationListener implements ActionListener, ItemListener{
	
	
	public static final Logger logger = Logger.getLogger("TalkBox");
	
	
	/**
	 * Implement the Action listener of the pressed buttons/ check box in the configuration app
	 * */
	//------------------ Fields --------------------------
	public ConfigurationAppFrame confFrame;					  // An instance of ConfigurationAppFrame.
	RecordAudio record;
	public static int size = 0;								// the number of buttons in the simulation app
	private boolean complete[];
	private int page_counter = 0;
	private boolean first = true;
	private boolean pickedImage = false;				// must pick an image before pressing next
	private boolean pickedSound = false;				// must pick a sound before going next
	private boolean pickedName = false;
	Thread thread;
	String homeDirectory = System.getProperty("user.dir" );
	String new_audio_path_M =  "./imageReasource/TalkBoxData/";	// mac/ linux/ unix
	String new_audio_path_W =   ".\\imageReasource\\TalkBoxData\\"; // windows
	String new_image_path_M =   "./imageReasource/TalkBoxData/";	// mac/ linux/ unix
	String new_image_path_W =   ".\\imageReasource\\TalkBoxData\\"; // windows
//	String new_audio_path_M =  "../TalkBoxData/";	// mac/ linux/ unix
//	String new_audio_path_W =   "..\\TalkBoxData\\"; // windows
//	String new_image_path_M =   "../TalkBoxData/";	// mac/ linux/ unix
//	String new_image_path_W =   "..\\TalkBoxData\\"; // windows
	String profileName;
	LogFile log;
	//----------------------------------------------------
	
	
	/*
	 * files should be accessible, not a hardcoded path 
	 * getresourcesstream
	 */
	
	
	
	
	/**
	 * A constructor that calls and initialized the configuration app frame with the current
	 * action listener
	 * */
	public ConfigurationListener (LogFile log) {
		confFrame = new ConfigurationAppFrame(this, this);     //Creates the GUI and associated this listeners with buttons and check boxes
		confFrame.setVisible(true);
		confFrame.pack();
		record = new RecordAudio();
		this.log = log;
		
		logger.info("Opened 'Configuration app'");
	}
	
	/**
	 * A constructor that calls and initialized the configuration app frame with the current
	 * action listener
	 * @throws IOException 
	 * */
	public ConfigurationListener () throws IOException {
		confFrame = new ConfigurationAppFrame(this, this);     //Creates the GUI and associated this listeners with buttons and check boxes
		confFrame.setVisible(true);
		confFrame.pack();
		record = new RecordAudio();
		log = new LogFile();
		
		try {
			FileHandler fileh = new FileHandler("ConfigurationLog.log");
			logger.addHandler(fileh);
			SimpleFormatter formatter = new SimpleFormatter();
			fileh.setFormatter(formatter);
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		logger.info("the App Frame is being initialized ");
	}
	
	/**
	 * Please create action events for pressing next, previous and exit
	 * Please note that the methods pressedNext and pressedPrevious can be used to adjust the GUI
	 * */

	@SuppressWarnings("deprecation")
	//@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "Exit") {
			logger.info("Pressed: 'Exit' in the 'Configuration App'");

			if (page_counter != 0) {
			
				File file;
				if (System.getProperty("os.name").startsWith("Windows"))
					file = new File(".\\imageReasource\\TalkBoxData\\" + profileName + "\\numberOfButtons.txt");
//					file = new File(".\\TalkBoxData\\" + profileName + "\\numberOfButtons.txt");
				else
					file = new File("./imageReasource/TalkBoxData/" + profileName + "/numberOfButtons.txt");
//					file = new File("./TalkBoxData/" + profileName + "/numberOfButtons.txt");
				
				
				try {
					
					if (!file.exists()) {
						file.createNewFile();
						logger.info("Creates a new file for the numberOfButtons.txt");
						
					}
					
					logger.info("Write the number of buttons " + size + " to numberOfButtons.txt");
					
					PrintWriter pt = new PrintWriter(file);
					pt.println(size);
					pt.close();
					confFrame.printNamesToFile();
					
				}  catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			logger.info("Close 'Configuration app'");
			LoggingFrame logpop = new LoggingFrame("Configuration App Logs","ConfigurationLog.log");
			confFrame.setVisible(false);
			confFrame.dispose();
		
			
		}

		else if (e.getActionCommand() == "Next") {
			logger.info("Pressed: 'Next' in the 'Configuration App'");
			
			if (first) {
				
				logger.info("It is the first time next is pressed");
				logger.info("Record the number of buttons the user wants");
				logger.info("Gets the name of the profile");

				File dir;
				if (System.getProperty("os.name").startsWith("Windows"))
					dir = new File (".\\imageReasource\\TalkBoxData");
//					dir = new File ("..\\TalkBoxData");
				else
					dir = new File ("./imageReasource/TalkBoxData");
//					dir = new File ("../TalkBoxData");
//				
//				if (dir != null)
//				{
//					for(File file: dir.listFiles()) 
//					    if (!file.isDirectory()) 
//					        file.delete();
//				}
				
				
				
				
				size = confFrame.getSizeButtons();
				profileName = confFrame.getProfileName();
				makeDir(profileName);	// creates a directory for this profile
				
				
				this.complete = new boolean[size+1];
				this.complete[0] = false;
				
				if (size != 0 && !profileName.equals("")) {
					first = false;
					this.complete[0] = true;
				}
			}else {
				if (!confFrame.getText().equals(""))		
					pickedName = true;
			}
			
			if (pickedSound && pickedImage && pickedName)
				complete[page_counter] = true;
			
			if (this.complete[page_counter] || (pickedSound && pickedImage && pickedName)) {
				if (size != 0) {
					confFrame.pressedNext(size);
					confFrame.dropDownImage.setSelectedIndex(0);
					confFrame.dropDownSound.setSelectedIndex(0);
					
					logger.info("Goes to the next page in the configuration app");
				}
				complete[page_counter] = true;
				page_counter ++;
				pickedSound = false;
				pickedImage = false;
				
			}
			else {
				logger.info("Not enough information is provided. Output an error message and doesn't go next");
				
				confFrame.popupError("You need to choose an audio, an image and name it");
			}
			pickedName = false;
			
		} // next
		else if (e.getActionCommand() == "Pick Sound") {
			logger.info("Pressed: 'Pick Sound' in the 'Configuration App'");
			
			String audio_path = confFrame.pressedPickSound();
			
			File audioFile = new File (audio_path);			
			FileOutputStream output;
			
			try {
				if (System.getProperty("os.name").startsWith("Windows")) 
					output = new FileOutputStream(new_audio_path_W + this.profileName + "\\Audio_" +page_counter + ".ser");
				else 
					output = new FileOutputStream(new_audio_path_M + this.profileName + "/Audio_" + page_counter + ".ser");
				
				ObjectOutputStream objOutput = new ObjectOutputStream(output);
				objOutput.writeObject(audioFile);
				objOutput.close();
				
				pickedSound = true;
				logger.info("It serializes the selected audio file and saves it in TalkBoxData");
				
				
			} catch (IOException e1) {
				logger.info("Serialization of the file has FAILED");
				e1.printStackTrace();
			}	
		}// pick sound 
		
		else if (e.getActionCommand() == "Pick Image") {
			logger.info("Pressed: 'Pick Image' in the 'Configuration App'");
			
			String image_path = confFrame.pressedPickImage();
			File imageFile = new File (image_path);			
			FileOutputStream output;
			
			try {
				
				if (System.getProperty("os.name").startsWith("Windows")) 
					output = new FileOutputStream(new_image_path_W + this.profileName + "\\Image_" + page_counter + ".ser");
				else 
					output = new FileOutputStream(new_image_path_M + this.profileName + "/Image_" + page_counter + ".ser");
				
				ObjectOutputStream objOutput = new ObjectOutputStream(output);
				objOutput.writeObject(imageFile);
				objOutput.close();
				pickedImage = true;
				logger.info("It serializes the selected image file and saves it in TalkBoxData");
				
				
			} catch (IOException e1) {
				
				logger.info("Serialization of the file has FAILED");
				e1.printStackTrace();
			}	
		}// pick Image
		
		else if (e.getActionCommand() == "Previous") {
			logger.info("Pressed: 'Previous' in the 'Configuration App'");
			
			page_counter --;
			if (confFrame.page == 1) {
				logger.info("Got to the first page of the configuration app");
				
				first = true;
			}
			logger.info("Gone to the previous page");
			
			confFrame.pressedPrevious(size);
			confFrame.dropDownImage.setSelectedIndex(0);
			confFrame.dropDownSound.setSelectedIndex(0);
			
			pickedSound = true;
			pickedImage = true;
			complete[page_counter] = true;
		}// previous
		
		else if (e.getActionCommand() == "Upload Image") {
			logger.info("Pressed: 'Upload Image' in the 'Configuration App'");
			
			
			String image_path = confFrame.pressedUploadImage();
			
			File imageFile = new File (image_path);
			FileOutputStream output;
			
			try {
				if (System.getProperty("os.name").startsWith("Windows")) 
					output = new FileOutputStream(new_image_path_W + this.profileName + "\\Image_" + page_counter + ".ser");
				else 
					output = new FileOutputStream(new_image_path_M + this.profileName + "/Image_" + page_counter + ".ser");
				
				ObjectOutputStream objOutput = new ObjectOutputStream(output);
				objOutput.writeObject(imageFile);
				objOutput.close();
				pickedImage = true;
				
				if (pickedSound && pickedImage)
					complete[page_counter] = true;
				
				
				logger.info("Serializes the image file");
				
			} catch (IOException e1) {
				logger.info("Serialization of the file has FAILED");
				
				e1.printStackTrace();
			}
		} // upload image
		
		else if (e.getActionCommand() == "Upload Sound") {
			logger.info("Pressed: 'Upload Sound' in the 'Configuration App'");
			
			String audio_path = confFrame.pressedUploadSound();
			
			File soundFile = new File (audio_path);			
			FileOutputStream output;
			
			try {
				if (System.getProperty("os.name").startsWith("Windows")) 
					output = new FileOutputStream(new_audio_path_W + this.profileName + "\\Audio_" +page_counter + ".ser");
				else 
					output = new FileOutputStream(new_audio_path_M + this.profileName + "/Audio_" + page_counter + ".ser");
				
				ObjectOutputStream objOutput = new ObjectOutputStream(output);
				objOutput.writeObject(soundFile);
				objOutput.close();
				pickedSound = true;
				
				if (pickedSound && pickedImage)
					complete[page_counter] = true;
				
				logger.info("Serializes the sound file");
				
			} catch (IOException e1) {
				logger.info("Serialization of the file has FAILED");
				
				e1.printStackTrace();
			}
		}// upload sound
		
		else if (e.getActionCommand() == "Start Recording") {
			logger.info("Pressed: 'Start Recording' in the 'Configuration App'");
			
			
			thread = new Thread(new Runnable() {
            public void run() {
              record.run(page_counter);
              
            }
        });
		thread.start();
		
		logger.info("Opened new thread of 'Record Audio'");
		
		}
		else if (e.getActionCommand() == "Stop Recording") {
			logger.info("Pressed: 'Stop Recording' in the 'Configuration App'");
			
			record.finish();
			thread.interrupt();
			
			logger.info("Closes the thread of Record Audio");
			 
			String audio_path;
			
			if (System.getProperty("os.name").startsWith("Windows"))
				audio_path = ".\\imageReasource\\TalkBoxData\\RecordAudio" + page_counter + ".wav";
//				audio_path = "..\\TalkBoxData\\RecordAudio" + page_counter + ".wav";
			else
				audio_path = "./imageReasource/TalkBoxData/RecordAudio_"+ page_counter + ".wav";
//				audio_path = "../TalkBoxData/RecordAudio_"+ page_counter + ".wav";
			
			File soundFile = new File (audio_path);			
			FileOutputStream output;
			
			try {
				if (System.getProperty("os.name").startsWith("Windows")) 
					output = new FileOutputStream(new_audio_path_W + this.profileName + "\\Audio_" +page_counter + ".ser");
				else 
					output = new FileOutputStream(new_audio_path_M + this.profileName + "/Audio_" + page_counter + ".ser");
				
				ObjectOutputStream objOutput = new ObjectOutputStream(output);
				objOutput.writeObject(soundFile);
				objOutput.close();
				pickedSound = true;
				
				logger.info("Serializes the record file");
			}
			catch (IOException e1) {
				logger.info("Serialization of the file has FAILED");
				
				e1.printStackTrace();
			}
		}// record sound
		
		else if (e.getActionCommand() == "Preview Sound") {
			logger.info("Pressed: 'Preview Sound' in the 'Configuration App'");
			
			if (this.pickedSound || this.complete[page_counter]) {
				
				FileInputStream fileIn;
				try {
					if (System.getProperty("os.name").startsWith("Windows"))
						fileIn = new FileInputStream(new_audio_path_W + this.profileName + "\\Audio_" + page_counter + ".ser");
					else
						fileIn = new FileInputStream(new_audio_path_M + this.profileName + "/Audio_" + page_counter + ".ser");
					
					 ObjectInputStream in = new ObjectInputStream(fileIn);
				     File input = (File) in.readObject();
				     Clip clip = AudioSystem.getClip();
				     clip.open(AudioSystem.getAudioInputStream(input));
				     clip.start();
				         
			        while (!clip.isRunning())
			        	    Thread.sleep(10);
			        	while (clip.isRunning())
			        	    Thread.sleep(10);
			        	clip.close();
			      
			        in.close();
			        fileIn.close();
				        
			        logger.info("Play the selected sound");
				} catch (Exception e1) {
					logger.info("Serialization of the audio file has FAILED");
					
					e1.printStackTrace();
				}
		       
			}
		}// preview sound
		
		else if (e.getActionCommand() == "Preview Image") {
			logger.info("Pressed: 'Preview Image' in the 'Configuration App'");
			
			if (this.pickedImage || this.complete[page_counter]) {
				
				FileInputStream fileIn;
				try {
					if (System.getProperty("os.name").startsWith("Windows"))
						fileIn = new FileInputStream(new_image_path_W + this.profileName + "\\Image_" + page_counter + ".ser");
					else
						fileIn = new FileInputStream(new_image_path_M + this.profileName + "/Image_" + page_counter + ".ser");
					
					
					 ObjectInputStream in = new ObjectInputStream(fileIn);
				     File input = (File) in.readObject();
				     
				     SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							JFrame frame = new JFrame("Preview image");
							frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							 BufferedImage img = null;
							 
							 try {
								img = ImageIO.read(input);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							 
							 JLabel label = new JLabel();
							 label.setIcon(new ImageIcon(img));
							 frame.getContentPane().add(label, BorderLayout.CENTER);
							 frame.pack();
							 frame.setLocationRelativeTo(null);
							 frame.setVisible(true);
						}
				    	 
				     });
			        in.close();
			        fileIn.close();
			        logger.info("Opens the selected image in a new window");
				        
				} catch (Exception e1) {
					logger.info("Serialization of the file has FAILED");
					
					this.confFrame.popupError("Invalid input");
				} 
		       
			}
		}// preview image
		else if (e.getActionCommand() == "Demo") {
			logger.info("Pressed: 'Demo' in the 'Configuration App'");
			
			File file;
			if (System.getProperty("os.name").startsWith("Windows"))
				file = new File(".\\imageReasource\\TalkBoxData\\" + profileName + "\\numberOfButtons.txt");
//				file = new File("..\\TalkBoxData\\" + profileName + "\\numberOfButtons.txt");
			else
				file = new File("./imageReasource/TalkBoxData/" + profileName + "/numberOfButtons.txt");
//				file = new File("../TalkBoxData/" + profileName + "/numberOfButtons.txt");
			
			try {
				
				if (!file.exists())
					file.createNewFile();
				PrintWriter pt = new PrintWriter(file);
				pt.println(size);
				pt.close();
				confFrame.printNamesToFile();
				
			} catch (Exception e1) {
				
				e1.printStackTrace();
			} 
			
			logger.info("Opens a demo of the selected configuration in a new thread");
			
			this.thread = new Thread(new Runnable() {
				public void run() {
				try {
					SimulationListener sim = new SimulationListener(size, log, profileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}});
			thread.start();
		}
	}

	/**
	 * Adjusts the view based on the dropdown pick of the user
	 * */
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		JComboBox<String> combo = (JComboBox<String>) e.getSource();
		
		if (combo.getSelectedItem() == "Pick Image" ) {
			logger.info("Chose: 'Pick Image' in the 'Configuration App' -> set button to 'Pick Image'");
			
			confFrame.uploadImageDropMenu("Pick Image");
		}
		else if (combo.getSelectedItem() == "Upload Image") {
			logger.info("Chose: 'Upload Image' in the 'Configuration App' -> set button to 'Upload Image'");
			
			confFrame.uploadImageDropMenu("Upload Image");
		}
		else if (combo.getSelectedItem() == "Upload Sound" ) {
			logger.info("Chose: 'Upload Sound' in the 'Configuration App' -> set button to 'Upload Sound'");
			
			confFrame.uploadSoundDropMenu("Upload Sound");
		}
		else if (combo.getSelectedItem() == "Pick Sound") {
			logger.info("Chose: 'Pick Sound' in the 'Configuration App' -> set button to 'Pick Sound'");
			
			confFrame.uploadSoundDropMenu("Pick Sound");
		}
		else if (combo.getSelectedItem() == "Record Sound") {
			logger.info("Chose: 'Record Sound' in the 'Configuration App' -> set button to 'Record Sound'");
			
			confFrame.uploadSoundDropMenu("Record Sound");
		}
		else if (combo.getSelectedItem() == "") {
			logger.info("Reset the drop down menu in the 'Configuration App'");
			
			confFrame.resetDropMenu();
		}	
	}
	
	/**
	 * Creates  a directory for the new profile
	 * 
	 * @param profile
	 * 			The new directory name (the name of the profile)
	 */
	public void makeDir (String profile) {
		if (System.getProperty("os.name").startsWith("Windows")) 
			new File(".\\imageReasource\\TalkBoxData\\" + profile).mkdir();
//			new File("..\\TalkBoxData\\" + profile).mkdir();
		else
			new File("./imageReasource/TalkBoxData/" + profile).mkdir();
//			new File("../TalkBoxData/" + profile).mkdir();
	}
}
