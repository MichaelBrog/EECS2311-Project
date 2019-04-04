package main.java.TalkBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.swing.JButton;


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
	Clip clip;
	AudioInputStream audioInputStream;
	Thread thread = new Thread();
	PlaySound play = new PlaySound();
	boolean played = false;
	AudioInputStream audioInput;
	boolean swap = false; // when false, no button was picked. When true 1 button is picked
						  // If true and another button is picked -> swap and set to false
	JButton pointer1;	  // A reference to the button that needs to be swapped
	JButton pointer2;	  // A second pointer to the button that needs to be swapped
	
	JButton swapee1;		// get from pointer1
	JButton swapee2;		// get from pointer2
	
	int index1;
	int index2;
	
	public static final Logger logger1 = Logger.getLogger("TalkBoxSim");
	//----------------------------------------------------
	
	/**
	 * @param n
	 * 			The number of buttons in the panel
	 * A constructor that calls and initialized the configuration app frame with the current
	 * action listener
	 * @throws IOException 
	 * */
	public SimulationListener (File[] audio) throws IOException {
		try {	
			FileHandler fileh1 = new FileHandler("SimulatorLog.log");
			logger1.addHandler(fileh1);
			SimpleFormatter formatter1 = new SimpleFormatter();
			fileh1.setFormatter(formatter1);
			
		} catch (IOException e) {
			// TODO: handle exception
		}

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
		this.profile = profile;
		audio_array = simFrame.audio_array;
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
	
		if (System.getProperty("os.name").startsWith("Windows"))
			saved_image_path =   ".\\imageReasource\\TalkBoxData\\" + profile + "\\"; 
		else
			saved_image_path =   "./imageReasource/TalkBoxData/" + profile + "/"; // mac/ linux/ unix

		if (new File(saved_image_path).exists()) {

			current_file = null;

			current_file = new File(saved_image_path + "numberOfButtons.txt");
			
			//scanner to scan the file and get an int value for the number of buttons
				scan = null;
				try {
					scan = new Scanner(current_file);
					logger1.info("Finds the number of buttons the simulation has");
					
				} catch (FileNotFoundException e) {
					logger1.info("Counld not find the number of buttons the simulation app has");
					e.printStackTrace();
				}
				number_of_buttons = Integer.parseInt(scan.next());
		}// if
		
		simFrame = new SimulatorFrame(this, number_of_buttons, profile);
		audio_array = simFrame.audio_array;
		logger1.info("Opened 'Simulation app'");
	}
	
	
	//public static void playMusic(String musicLocation)
	public  void playMusic(File musicLocation)
	{
	
		try
		{
			File musicPath = musicLocation;
			if (musicPath.exists())
			{
			
				thread = new Thread(new Runnable() {

					@Override
					public void run() {
						play.run(musicPath);
						
				}});
				thread.start();
				played = true;
				logger1.info("Found the audio files");
			}
		}
		
		catch(Exception e)
		{
			logger1.info("Didn't find audio files");
			e.printStackTrace();
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		//determines the number of the button by taking the actioncommand which is the image name
		//Then using regex to take the number out of it, ideally we find a better way to do this
		if (e.getActionCommand() == "Next") {
			this.simFrame.pushedNext();
		}// next
		else if (e.getActionCommand() == "Previous") {
			this.simFrame.pushedPrevious();
		}// previous
		else if (e.getActionCommand() == "Exit") {
			if (played) {
				play.finish();
				thread.interrupt();
				played = false;
			}
			simFrame.setVisible(false);
			simFrame.dispose();
		}// exit
		else if (e.getActionCommand() == "Swap") {
			
			if (!swap) {
				pointer1 = (JButton)e.getSource();
				simFrame.changeToGreen(pointer1);
				index1 = simFrame.returnPic(pointer1);
				swap = true;
			}
			else if (e.getSource() == pointer1) {

				//pointer2 = (JButton)e.getSource();
				simFrame.resetToOrigin(pointer1);
				swap = false;
			}
			else {
				pointer2 = (JButton)e.getSource();
				simFrame.resetToOrigin(pointer1);
				swap = false;
				index2 = simFrame.returnPic(pointer2);
				try {
					swapFiles();
					fixNamesFile();
					int page = simFrame.page;
					simFrame.setVisible(false);
					simFrame.dispose();
					simFrame = new SimulatorFrame(this, this.number_of_buttons, profile, page);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
		else {
			if (played) {
				play.finish();
				thread.interrupt();
				thread.stop();
				played = false;
			}
			String command_num = e.getActionCommand();
			int numberOnly= Integer.parseInt(command_num);
			//index = numberOnly;
			playMusic(audio_array[numberOnly]);		
		
			logger1.info("Playes the audio for specific button");
		}
	}
	
	private void swapFiles() throws IOException, ClassNotFoundException {
		String saved_image_path;
		
		if (System.getProperty("os.name").startsWith("Windows"))
			saved_image_path =   ".\\imageReasource\\TalkBoxData\\" + profile + "\\"; 
		else
			saved_image_path =   "./imageReasource/TalkBoxData/" + profile + "/"; // mac/ linux/ unix

		// files already exist
		File swap_image_one = new File(saved_image_path + "Image_" + (index1+1) + ".ser");
		File swap_sound_one = new File(saved_image_path + "Audio_" + (index1+1) + ".ser");
		File swap_image_two = new File(saved_image_path + "Image_" + (index2+1) + ".ser");
		File swap_sound_two = new File(saved_image_path + "Audio_" + (index2+1) + ".ser");

		// create 2 temp files to save the sound and image of swapee1
		File temp_audio = new File(saved_image_path + "Audio_" + "temp" + ".ser");
		File temp_Image = new File(saved_image_path + "Image_" + "temp" + ".ser");
		Files.copy(Paths.get(swap_sound_one.getAbsolutePath()), Paths.get(saved_image_path + "Audio_" + "temp" + ".ser"), StandardCopyOption.REPLACE_EXISTING);
		Files.copy(Paths.get(swap_image_one.getAbsolutePath()), Paths.get(saved_image_path + "Image_" + "temp" + ".ser"), StandardCopyOption.REPLACE_EXISTING);
		
	/*	swap_image_one.renameTo(temp_Image);
		swap_sound_one.renameTo(temp_audio);
		
		swap_image_two.renameTo(swap_image_one);
		swap_sound_two.renameTo(swap_sound_one);
		
		temp_Image.renameTo(new File (saved_image_path + "Image_" + (index2+1) + ".ser"));
		temp_audio.renameTo(new File (saved_image_path + "Audio_" + (index2+1) + ".ser"));*/
		
		Files.move(Paths.get(swap_image_one.toURI()), Paths.get(temp_Image.toURI()), StandardCopyOption.REPLACE_EXISTING);
		Files.move(Paths.get(swap_sound_one.toURI()), Paths.get(temp_audio.toURI()), StandardCopyOption.REPLACE_EXISTING);
		
		Files.move(Paths.get(swap_image_two.toURI()), Paths.get(swap_image_one.toURI()), StandardCopyOption.REPLACE_EXISTING);
		Files.move(Paths.get(swap_sound_two.toURI()), Paths.get(swap_sound_one.toURI()), StandardCopyOption.REPLACE_EXISTING);
		
		Files.move(Paths.get(temp_Image.toURI()), Paths.get(saved_image_path + "Image_" + (index2+1) + ".ser"), StandardCopyOption.REPLACE_EXISTING);
		Files.move(Paths.get(temp_audio.toURI()), Paths.get(saved_image_path + "Audio_" + (index2+1) + ".ser"), StandardCopyOption.REPLACE_EXISTING);
		

	}
	
	private void fixNamesFile () throws IOException {
		int min, max;
		if (index1 < index2) {
			min = index1;
			max = index2;
		}
		else {
			min = index2;
			max = index1;
		}
		
		String saved_image_path;
		
		if (System.getProperty("os.name").startsWith("Windows"))
			saved_image_path =   ".\\imageReasource\\TalkBoxData\\" + profile + "\\"; 
		else
			saved_image_path =   "./imageReasource/TalkBoxData/" + profile + "/"; // mac/ linux/ unix
		
		List<String> strs = Files.readAllLines(Paths.get(saved_image_path + "names.txt"));
		
		String strMax = strs.get(max);
		String strMin = strs.get(min);
		String temp = strMin; // stores the min value
		
		strs.set(min, strMax); // put in the min index the max value
		strs.set(max, temp); // put in the max index the min value
		
		File names;
		if (System.getProperty("os.name").startsWith("Windows"))
			names = new File (saved_image_path + "names.txt");
		else
			names = new File (saved_image_path + "names.txt");
		
		if (!names.exists()) 
			names.createNewFile();
		
	/*	for(File file: names.listFiles()) 
		    if (!file.isDirectory()) 
		        file.delete();*/
		
		PrintWriter pt = new PrintWriter(names);

		for (int i = 0; i < strs.size(); i ++) 
			pt.println(strs.get(i));
		
		pt.close();
	}
	

}