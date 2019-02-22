import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SimulationListener implements ActionListener{
	/**
	 * Implement the Action listener of the pressed buttons/ check box in the configuration app
	 * */
	//------------------ Fields --------------------------
	SimulatorFrame simFrame;
	static File[] audio_array;
	
	//----------------------------------------------------
	
	/**
	 * @param n
	 * 			The number of buttons in the panel
	 * A constructor that calls and initialized the configuration app frame with the current
	 * action listener
	 * */
	public SimulationListener (File[] audio) {
		
		audio_array = audio;
	}
	
	
	
	

	//public static void playMusic(String musicLocation)
	public static void playMusic(File musicLocation)
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
			}
			else
			{
				System.out.println("Can't find file at location: " + musicLocation);
			}
		}
		
		catch(Exception e)
		{
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
		
	}
}
