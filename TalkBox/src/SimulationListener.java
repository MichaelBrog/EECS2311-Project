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
	
	//----------------------------------------------------
	
	/**
	 * @param n
	 * 			The number of buttons in the panel
	 * A constructor that calls and initialized the configuration app frame with the current
	 * action listener
	 * */
	public SimulationListener (int n) {
		simFrame = new SimulatorFrame(this, n);
	}
	
	
	
	

	public static void playMusic(String musicLocation)
	{
	
		try
		{
			File musicPath = new File(musicLocation);
			if (musicPath.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				
				JOptionPane.showMessageDialog(null, "press okay to stop playing");
			}
			else
			{
				System.out.println("Can't find file");
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
		
		String filePath = "test.wav";
		playMusic(filePath);
		
		
	}
}
	
	

	
