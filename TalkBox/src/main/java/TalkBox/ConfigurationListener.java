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
	String new_audio_path_M =  "./imageReasource/TalkBoxData/Audio_";	// mac/ linux/ unix
	String new_audio_path_W =   ".\\imageReasource\\TalkBoxData\\Audio_"; // windows
	String new_image_path_M =   "./imageReasource/TalkBoxData/Image_";	// mac/ linux/ unix
	String new_image_path_W =   ".\\imageReasource\\TalkBoxData\\Image_"; // windows
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
		
		try {
			log.writeToLog("Opened 'Configuration app'");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
			log.writeToLog("Opened 'Configuration app'");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
			try {
				log.writeToLog("Pressed: 'Exit' in the 'Configuration App'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			if (page_counter != 0) {
			
				File file;
				if (System.getProperty("os.name").startsWith("Windows"))
					file = new File(".\\imageReasource\\TalkBoxData\\numberOfButtons.txt");
				else
					file = new File("./imageReasource/TalkBoxData/numberOfButtons.txt");
				
				try {
					
					if (!file.exists()) {
						file.createNewFile();
						log.writeToLog("Creates a new file for the numberOfButtons.txt");
						
					}
					
					try {
						log.writeToLog("Write the number of buttons " + size + " to numberOfButtons.txt");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					PrintWriter pt = new PrintWriter(file);
					pt.println(size);
					pt.close();
					confFrame.printNamesToFile();
					
				}  catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			confFrame.setVisible(false);
			confFrame.dispose();
		
			try {
				log.writeToLog("Close 'Configuration app'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		else if (e.getActionCommand() == "Next") {
			try {
				log.writeToLog("Pressed: 'Next' in the 'Configuration App'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			if (first) {
				
				try {
					log.writeToLog("It is the first time next is pressed");
					log.writeToLog("Record the number of buttons the user wants");
					log.writeToLog("Gets the name of the profile");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				File dir;
				if (System.getProperty("os.name").startsWith("Windows"))
					dir = new File (".\\imageReasource\\TalkBoxData");
				else
					dir = new File ("./imageReasource/TalkBoxData");
				
				for(File file: dir.listFiles()) 
				    if (!file.isDirectory()) 
				        file.delete();
				
				
				
				size = confFrame.getSizeButtons();
				profileName = confFrame.getProfileName();
				
				
				
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
					
					try {
						log.writeToLog("Goes to the next page in the configuration app");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				complete[page_counter] = true;
				page_counter ++;
				pickedSound = false;
				pickedImage = false;
				
			}
			else {
				try {
					log.writeToLog("Not enough information is provided. Output an error message and doesn't go next");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				confFrame.popupError("You need to choose an audio, an image and name it");
			}
			pickedName = false;
			
		}
		else if (e.getActionCommand() == "Pick Sound") {
			try {
				log.writeToLog("Pressed: 'Pick Sound' in the 'Configuration App'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			String audio_path = confFrame.pressedPickSound();
			
			File audioFile = new File (audio_path);			
			FileOutputStream output;
			
			try {
				if (System.getProperty("os.name").startsWith("Windows")) 
					output = new FileOutputStream(new_audio_path_W + page_counter + ".ser");
				else 
					output = new FileOutputStream(new_audio_path_M + page_counter + ".ser");
				
				ObjectOutputStream objOutput = new ObjectOutputStream(output);
				objOutput.writeObject(audioFile);
				objOutput.close();
				
				pickedSound = true;
				log.writeToLog("It serializes the selected audio file and saves it in TalkBoxData");
				
				
			} catch (IOException e1) {
				try {
					log.writeToLog("Serialization of the file has FAILED");
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				e1.printStackTrace();
			}	
		}// pick sound 
		
		else if (e.getActionCommand() == "Pick Image") {
			try {
				log.writeToLog("Pressed: 'Pick Image' in the 'Configuration App'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			String image_path = confFrame.pressedPickImage();
			File imageFile = new File (image_path);			
			FileOutputStream output;
			
			try {
				
				
				if (System.getProperty("os.name").startsWith("Windows")) 
					output = new FileOutputStream(new_image_path_W + page_counter + ".ser");
				else 
					output = new FileOutputStream(new_image_path_M + page_counter + ".ser");
				
				ObjectOutputStream objOutput = new ObjectOutputStream(output);
				objOutput.writeObject(imageFile);
				objOutput.close();
				pickedImage = true;
				log.writeToLog("It serializes the selected image file and saves it in TalkBoxData");
				
				
			} catch (IOException e1) {
				
				try {
					log.writeToLog("Serialization of the file has FAILED");
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				e1.printStackTrace();
			}	
		}// pick Image
		
		else if (e.getActionCommand() == "Previous") {
			try {
				log.writeToLog("Pressed: 'Previous' in the 'Configuration App'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			page_counter --;
			if (confFrame.page == 1) {
				try {
					log.writeToLog("Got to the first page of the configuration app");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				first = true;
			}
			try {
				log.writeToLog("Gone to the previous page");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			confFrame.pressedPrevious(size);
			confFrame.dropDownImage.setSelectedIndex(0);
			confFrame.dropDownSound.setSelectedIndex(0);
			
			pickedSound = true;
			pickedImage = true;
			complete[page_counter] = true;
		}
		else if (e.getActionCommand() == "Upload Image") {
			try {
				log.writeToLog("Pressed: 'Upload Image' in the 'Configuration App'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			String image_path = confFrame.pressedUploadImage();
			
			File imageFile = new File (image_path);
			FileOutputStream output;
			
			try {
				if (System.getProperty("os.name").startsWith("Windows")) 
					output = new FileOutputStream(new_image_path_W + page_counter + ".ser");
				else 
					output = new FileOutputStream(new_image_path_M + page_counter + ".ser");
				
				ObjectOutputStream objOutput = new ObjectOutputStream(output);
				objOutput.writeObject(imageFile);
				objOutput.close();
				pickedImage = true;
				
				if (pickedSound && pickedImage)
					complete[page_counter] = true;
				
				
				log.writeToLog("Serializes the image file");
				
			} catch (IOException e1) {
				try {
					log.writeToLog("Serialization of the file has FAILED");
				} catch (IOException e2) {
					e1.printStackTrace();
				}
				
				e1.printStackTrace();
			}
			
		}
		else if (e.getActionCommand() == "Upload Sound") {
			try {
				log.writeToLog("Pressed: 'Upload Sound' in the 'Configuration App'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			String audio_path = confFrame.pressedUploadSound();
			
			File soundFile = new File (audio_path);			
			FileOutputStream output;
			
			try {
				if (System.getProperty("os.name").startsWith("Windows")) 
					output = new FileOutputStream(new_audio_path_W + page_counter + ".ser");
				else 
					output = new FileOutputStream(new_audio_path_M + page_counter + ".ser");
				
				ObjectOutputStream objOutput = new ObjectOutputStream(output);
				objOutput.writeObject(soundFile);
				objOutput.close();
				pickedSound = true;
				
				if (pickedSound && pickedImage)
					complete[page_counter] = true;
				
				log.writeToLog("Serializes the sound file");
				
			} catch (IOException e1) {
				try {
					log.writeToLog("Serialization of the file has FAILED");
				} catch (IOException e2) {
					e1.printStackTrace();
				}
				
				e1.printStackTrace();
			}
		}// upload sound
		
		else if (e.getActionCommand() == "Start Recording") {
			try {
				log.writeToLog("Pressed: 'Start Recording' in the 'Configuration App'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			thread = new Thread(new Runnable() {
            public void run() {
              record.run(page_counter);
              
            }
        });
		thread.start();
		
		try {
			log.writeToLog("Opened new thread of 'Record Audio'");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		}
		else if (e.getActionCommand() == "Stop Recording") {
			try {
				log.writeToLog("Pressed: 'Stop Recording' in the 'Configuration App'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			record.finish();
			thread.interrupt();
			
			try {
				log.writeToLog("Closes the thread of Record Audio");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			 
			String audio_path;
			
			if (System.getProperty("os.name").startsWith("Windows"))
				audio_path = ".\\imageReasource\\TalkBoxData\\RecordAudio" + page_counter + ".wav";
			else
				audio_path = "./imageReasource/TalkBoxData/RecordAudio_"+ page_counter + ".wav";
			
			File soundFile = new File (audio_path);			
			FileOutputStream output;
			
			try {
				if (System.getProperty("os.name").startsWith("Windows")) 
					output = new FileOutputStream(new_audio_path_W + page_counter + ".ser");
				else 
					output = new FileOutputStream(new_audio_path_M + page_counter + ".ser");
				
				ObjectOutputStream objOutput = new ObjectOutputStream(output);
				objOutput.writeObject(soundFile);
				objOutput.close();
				pickedSound = true;
				
				log.writeToLog("Serializes the record file");
			}
			catch (IOException e1) {
				try {
					log.writeToLog("Serialization of the file has FAILED");
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				
				e1.printStackTrace();
			}
		}// record sound
		
		else if (e.getActionCommand() == "Preview Sound") {
			try {
				log.writeToLog("Pressed: 'Preview Sound' in the 'Configuration App'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			if (this.pickedSound || this.complete[page_counter]) {
				
				FileInputStream fileIn;
				try {
					if (System.getProperty("os.name").startsWith("Windows"))
						fileIn = new FileInputStream(new_audio_path_W + page_counter + ".ser");
					else
						fileIn = new FileInputStream(new_audio_path_M + page_counter + ".ser");
					
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
				        
			        log.writeToLog("Play the selected sound");
				} catch (Exception e1) {
					try {
						log.writeToLog("Serialization of the audio file has FAILED");
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					
					e1.printStackTrace();
				}
		       
			}
		}// preview sound
		
		else if (e.getActionCommand() == "Preview Image") {
			try {
				log.writeToLog("Pressed: 'Preview Image' in the 'Configuration App'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			if (this.pickedImage || this.complete[page_counter]) {
				
				FileInputStream fileIn;
				try {
					if (System.getProperty("os.name").startsWith("Windows"))
						fileIn = new FileInputStream(new_image_path_W + page_counter + ".ser");
					else
						fileIn = new FileInputStream(new_image_path_M + page_counter + ".ser");
					
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
			        log.writeToLog("Opens the selected image in a new window");
				        
				} catch (Exception e1) {
					try {
						log.writeToLog("Serialization of the file has FAILED");
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					
					e1.printStackTrace();
				} 
		       
			}
		}// preview image
		else if (e.getActionCommand() == "Demo") {
			try {
				log.writeToLog("Pressed: 'Demo' in the 'Configuration App'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			File file;
			if (System.getProperty("os.name").startsWith("Windows"))
				file = new File(".\\imageReasource\\TalkBoxData\\numberOfButtons.txt");
			else
				file = new File("./imageReasource/TalkBoxData/numberOfButtons.txt");
			
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
			
			try {
				log.writeToLog("Opens a demo of the selected configuration in a new thread");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			this.thread = new Thread(new Runnable() {
				public void run() {
				try {
					SimulationListener sim = new SimulationListener(size, log);
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
		
		JComboBox combo = (JComboBox) e.getSource();
		
		if (combo.getSelectedItem() == "Pick Image" ) {
			try {
				log.writeToLog("Chose: 'Pick Image' in the 'Configuration App' -> set button to 'Pick Image'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			confFrame.uploadImageDropMenu("Pick Image");
		}
		else if (combo.getSelectedItem() == "Upload Image") {
			try {
				log.writeToLog("Chose: 'Upload Image' in the 'Configuration App' -> set button to 'Upload Image'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			confFrame.uploadImageDropMenu("Upload Image");
		}
		else if (combo.getSelectedItem() == "Upload Sound" ) {
			try {
				log.writeToLog("Chose: 'Upload Sound' in the 'Configuration App' -> set button to 'Upload Sound'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			confFrame.uploadSoundDropMenu("Upload Sound");
		}
		else if (combo.getSelectedItem() == "Pick Sound") {
			try {
				log.writeToLog("Chose: 'Pick Sound' in the 'Configuration App' -> set button to 'Pick Sound'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			confFrame.uploadSoundDropMenu("Pick Sound");
		}
		else if (combo.getSelectedItem() == "Record Sound") {
			try {
				log.writeToLog("Chose: 'Record Sound' in the 'Configuration App' -> set button to 'Record Sound'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			confFrame.uploadSoundDropMenu("Record Sound");
		}
		else if (combo.getSelectedItem() == "") {
			try {
				log.writeToLog("Reset the drop down menu in the 'Configuration App'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			confFrame.resetDropMenu();
		}
			
	}
}
