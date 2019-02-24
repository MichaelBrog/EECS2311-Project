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

public class ConfigurationListener implements ActionListener, ItemListener, TalkBoxConfiguration, Runnable{
	/**
	 * Implement the Action listener of the pressed buttons/ check box in the configuration app
	 * */
	//------------------ Fields --------------------------
	ConfigurationAppFrame confFrame;					  // An instance of ConfigurationAppFrame.
	RecordAudio record;
	public static int size = 0;
	private boolean complete[];
	private int page_counter = 0;
	private boolean first = true;
	private boolean pickedImage = false;				// must pick an image before pressing next
	private boolean pickedSound = false;				// must pick a sound before going next
	Thread thread;
	String homeDirectory = System.getProperty("user.dir" );
	String new_audio_path_M =  "./imageReasource/TalkBoxData/Audio_";	// mac/ linux/ unix
	String new_audio_path_W =   ".\\imageReasource\\TalkBoxData\\Audio_"; // windows
	String new_image_path_M =   "./imageReasource/TalkBoxData/Image_";	// mac/ linux/ unix
	String new_image_path_W =   ".\\imageReasource\\TalkBoxData\\Image_"; // windows
	//----------------------------------------------------
	
	
	/*
	 * files should be accessible, not a hardcoded path 
	 * getresourcesstream
	 */
	
	
	
	
	/**
	 * A constructor that calls and initialized the configuration app frame with the current
	 * action listener
	 * */
	public ConfigurationListener () {
		confFrame = new ConfigurationAppFrame(this, this);     //Creates the GUI and associated this listeners with buttons and check boxes
		record = new RecordAudio();
		
	}
	
	/**
	 * Please create action events for pressing next, previous and exit
	 * Please note that the methods pressedNext and pressedPrevious can be used to adjust the GUI
	 * */

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "Exit") {

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
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			confFrame.setVisible(false);
			confFrame.dispose();
			System.exit(0);
		}

		else if (e.getActionCommand() == "Next") {
			
			if (first) {

			/*	File dir;
				if (System.getProperty("os.name").startsWith("Windows"))
					dir = new File (".\\imageReasource\\TalkBoxData");
				else
					dir = new File ("./imageReasource/TalkBoxData");
				
				for(File file: dir.listFiles()) 
				    if (!file.isDirectory()) 
				        file.delete();
				*/
				size = confFrame.getSizeButtons();
				this.complete = new boolean[size+1];
				complete[0] = false;
				
				if (size != 0) {
					first = false;
					complete[0] = true;
				}
			}
			
			if (complete[page_counter] || (pickedSound && pickedImage)) {
				if (size != 0) {
					confFrame.pressedNext(size);
					confFrame.dropDownImage.setSelectedIndex(0);
					confFrame.dropDownSound.setSelectedIndex(0);
				}
				complete[page_counter] = true;
				page_counter ++;
				pickedSound = false;
				pickedImage = false;
		}
			
		}
		else if (e.getActionCommand() == "Pick Sound") {
			String audio_path = confFrame.pressedPickSound();
			
			File imageFile = new File (audio_path);			
			FileOutputStream output;
			
			try {
				if (System.getProperty("os.name").startsWith("Windows")) 
					output = new FileOutputStream(new_audio_path_W + page_counter + ".ser");
				else 
					output = new FileOutputStream(new_audio_path_M + page_counter + ".ser");
				
				ObjectOutputStream objOutput = new ObjectOutputStream(output);
				objOutput.writeObject(imageFile);
				objOutput.close();
				
				pickedSound = true;
				
				if (pickedSound && pickedImage)
					complete[page_counter] = true;
				
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}// pick sound 
		
		else if (e.getActionCommand() == "Pick Image") {
			
			String image_path = confFrame.pressedPickImage();
			File soundFile = new File (image_path);			
			FileOutputStream output;
			
			try {
				if (System.getProperty("os.name").startsWith("Windows")) 
					output = new FileOutputStream(new_image_path_W + page_counter + ".ser");
				else 
					output = new FileOutputStream(new_image_path_M + page_counter + ".ser");
				
				ObjectOutputStream objOutput = new ObjectOutputStream(output);
				objOutput.writeObject(soundFile);
				objOutput.close();
				pickedImage = true;
				
				if (pickedSound && pickedImage)
					complete[page_counter] = true;
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}// pick Image
		
		else if (e.getActionCommand() == "Previous") {
			page_counter --;
			if (ConfigurationAppFrame.page == 1) {
				first = true;
			}
			
			confFrame.pressedPrevious(size);
			confFrame.dropDownImage.setSelectedIndex(0);
			confFrame.dropDownSound.setSelectedIndex(0);
			
			pickedSound = true;
			pickedImage = true;
			complete[page_counter] = true;
		}
		else if (e.getActionCommand() == "Upload Image") {
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
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if (e.getActionCommand() == "Upload Sound") {
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
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}// upload sound
		
		else if (e.getActionCommand() == "Start Recording") {
			
			thread = new Thread(new Runnable() {
            public void run() {
              record.run();
              
            }
        });
		thread.start();
		
		}
		else if (e.getActionCommand() == "Stop Recording") {
			record.finish();
			thread.stop();
		
			String audio_path;
			
			if (System.getProperty("os.name").startsWith("Windows"))
				audio_path = ".\\imageReasource\\TalkBoxData\\RecordAudio.wav";
			else
				audio_path = "./imageReasource/TalkBoxData/RecordAudio.wav";
			
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
				
				
			}
			catch (IOException e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}// record sound
		
		else if (e.getActionCommand() == "Preview Sound") {
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
				        
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		       
			}
		}// preview sound
		
		else if (e.getActionCommand() == "Preview Image") {
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
				        
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
		       
			}
		}// preview image
	}

	/**
	 * Adjusts the view based on the dropdown pick of the user
	 * */
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		JComboBox combo = (JComboBox) e.getSource();
		
		if (combo.getSelectedItem() == "Pick Image" ) {
			confFrame.uploadImageDropMenu("Pick Image");
		}
		else if (combo.getSelectedItem() == "Upload Image") {
			confFrame.uploadImageDropMenu("Upload Image");
		}
		else if (combo.getSelectedItem() == "Upload Sound" ) {
			confFrame.uploadSoundDropMenu("Upload Sound");
		}
		else if (combo.getSelectedItem() == "Pick Sound") {
			confFrame.uploadSoundDropMenu("Pick Sound");
		}
		else if (combo.getSelectedItem() == "Record Sound") {
			confFrame.uploadSoundDropMenu("Record Sound");
		}
		else if (combo.getSelectedItem() == "") {
			confFrame.resetDropMenu();
		}
			

		/*JCheckBox source = (JCheckBox) e.getItemSelectable();
		if(source.getText() == confFrame.checkToSelfUploadImage.getText()) {
			confFrame.uploadImageCheckBox();
		}
		if(source.getText() == confFrame.checkToSelfUploadSound.getText()) {
			confFrame.uploadSoundCheckBox();
		}*/
	}

	
	
	// -------------------------------------------------------------------------------
	// ------ Following methods implement the TalkBoxConfiguration interface ---------
	// -------------------------------------------------------------------------------
	
	@Override
	public int getNumberOfAudioButtons() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int getNumberOfAudioSets() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int getTotalNumberOfButtons() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Path getRelativePathToAudioFiles() {
		// TODO Auto-generated method stub
		Path path = FileSystems.getDefault().getPath("TalkBoxData");
		
		return path;
	}

	@Override
	public String[][] getAudioFileNames() {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	// Thread interface
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
