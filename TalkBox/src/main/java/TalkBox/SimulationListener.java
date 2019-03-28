package main.java.TalkBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//import javax.sound.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

/*import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;*/

public class SimulationListener implements ActionListener{
	/**
	 * Implement the Action listener of the pressed buttons/ check box in the configuration app
	 * */
	//------------------ Fields --------------------------
	SimulatorFrame simFrame;
	static File[] audio_array;
	int number_of_buttons = 0;
	LogFile log;
	String profile;
	//----------------------------------------------------
	
	/**
	 * @param n
	 * 			The number of buttons in the panel
	 * A constructor that calls and initialized the configuration app frame with the current
	 * action listener
	 * @throws IOException 
	 * */
	public SimulationListener (File[] audio) throws IOException {
		log = new LogFile();
		audio_array = audio;
	}
	
	/**
	 * 
	 * @param numButtons 
	 * 				the number of buttons in the simulation app
	 * 
	 * @throws IOException
	 *//*
	public SimulationListener (int num, LogFile log, String profile) throws IOException {
		
		simFrame = new SimulatorFrame(this, num);
		this.log = log;
	}*/
	
	/**
	 * 
	 * @param numButtons 
	 * 				the number of buttons in the simulation app
	 * @param log
	 * 				the log it outputs messages to
	 * @param profile
	 * 				the profile the user chooses
	 * 
	 * @throws IOException
	 */
	public SimulationListener (int num, LogFile log, String profile) throws IOException {
		
		simFrame = new SimulatorFrame(this, num, profile);
		number_of_buttons = num;
		this.log = log;
		//this.profile = profile;
	}
	
	/**
	 * @param log
	 * 			The log instance to write to
	 * @throws IOException
	 */
	public SimulationListener (LogFile log, String profile) throws IOException {
		
		this.profile = profile;
		this.log = log;
		String saved_image_path = "";
		String homeDirectory = System.getProperty("user.dir");
		File current_file = null;
		Scanner scan;
		String protocol = SimulatorFrame.class.getResource("").getProtocol();
	
		if (System.getProperty("os.name").startsWith("Windows"))
			saved_image_path =   ".\\imageReasource\\TalkBoxData\\" + profile + "\\"; // mac/ linux/ unix
		else
			saved_image_path =   "./imageReasource/TalkBoxData/" + profile + "/"; // mac/ linux/ unix

		File[] files = new File(saved_image_path).listFiles();
		if (new File(saved_image_path).exists()) {

			current_file = null;
			
			//Finding a file with the number of buttons, ideally we don't need a for loop to find it, check for other method
		/*	for (File file : files) {
				if (file.getName().endsWith(saved_image_path + "numberOfButtons.txt")) {
					current_file = file;
				}
			}	*/	
			current_file = new File(saved_image_path + "numberOfButtons.txt");
			
			//scanner to scan the file and get an int value for the number of buttons
				scan = null;
				try {
					scan = new Scanner(current_file);
					log.writeToLog("Finds the number of buttons the simulation has");
					
				} catch (FileNotFoundException e) {
					try {
						log.writeToLog("Counld not find the number of buttons the simulation app has");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
				number_of_buttons = Integer.parseInt(scan.next());
		}// if
		
		simFrame = new SimulatorFrame(this, number_of_buttons, profile);
		
		try {
			log.writeToLog("Opened 'Simulation app'");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
/*	*//**
	 * No argument constructor
	 * @throws IOException
	 *//*
	public SimulationListener () throws IOException {
		this.log = new LogFile();
		String saved_image_path = "";
		String homeDirectory = System.getProperty("user.dir");
		File current_file = null;
		Scanner scan;
		String protocol = SimulatorFrame.class.getResource("").getProtocol();
	
		if (System.getProperty("os.name").startsWith("Windows"))
			saved_image_path =   ".\\imageReasource\\TalkBoxData\\"; // mac/ linux/ unix
		else
			saved_image_path =   "./imageReasource/TalkBoxData/"; // mac/ linux/ unix

		File[] files = new File(saved_image_path).listFiles();
		if (new File(saved_image_path).exists()) {

			current_file = null;
			
			//Finding a file with the number of buttons, ideally we don't need a for loop to find it, check for other method
			for (File file : files) {
				if (file.getName().endsWith("Buttons" + ".txt")) {
					current_file = file;
				}
			}		
			
			//scanner to scan the file and get an int value for the number of buttons
				scan = null;
				try {
					scan = new Scanner(current_file);
					
						log.writeToLog("Finds the number of buttons the simulation has");
					
				} catch (FileNotFoundException e) {
					try {
						log.writeToLog("Counld not find the number of buttons the simulation app has");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
				number_of_buttons = Integer.parseInt(scan.next());
		}// if
	
		simFrame = new SimulatorFrame(this, number_of_buttons, profile);
		
		try {
			log.writeToLog("Opened 'Simulation app'");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}*/
	
	//public static void playMusic(String musicLocation)
	public  void playMusic(File musicLocation)
	{
	
		try
		{
			//File musicPath = new File(musicLocation);
			File musicPath = musicLocation;
			if (musicPath.exists())
			{
				
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				
				//JOptionPane.showMessageDialog(null, "press okay to stop playing");
				try {
					log.writeToLog("Found the audio files");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else
			{
				//System.out.println("Can't find file at location: " + musicLocation);
				try {
					log.writeToLog("Didn't find the audio files");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		catch(Exception e)
		{
			try {
				log.writeToLog("Didn't find audio files");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == "Hi")
//
//		{
//
//			// TODO Auto-generated method stub
//
//			// open the sound file as a Java input stream
//
//		    String file = "Users\\ryann\\git\\EECS2311-Project\\EECS2311-Project\\TalkBox\\src\\hello.mp3";
//
//		    String music = "hello.mp3";
//
//		    Media hit = new Media(new File(music).toURI().toString());
//
//		    MediaPlayer mediaPlayer = new MediaPlayer(hit);
//
//		    mediaPlayer.play();
//
//		}
		
		//determines the number of the button by taking the actioncommand which is the image name
		//Then using regex to take the number out of it, ideally we find a better way to do this
		String command_num = e.getActionCommand();
		
		int numberOnly= Integer.parseInt(command_num);
		playMusic(audio_array[numberOnly]);		
		
		try {
			log.writeToLog("Playes the audio for specific button");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}