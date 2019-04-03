package main.java.TalkBox;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class PlaySound implements Runnable{
	Clip clip;
	AudioInputStream audioInput;
	
	public void run (File musicPath){
		
		try {
			audioInput = AudioSystem.getAudioInputStream(musicPath);
			clip = (Clip) AudioSystem.getClip();
			
			
			clip.addLineListener(new LineListener() {

				@Override
				public void update(LineEvent event) {
					if (event.getType() == LineEvent.Type.STOP) {
					    event.getLine().close();
					    clip.stop();
					    clip.drain();
					    clip.close();
					    
					    try {
							audioInput.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
			clip.open(audioInput);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		run();
		
	}
	
	@Override
	public void run() {
		
		clip.loop(0);
		
	}
	
	public void finish() {
		
		clip.stop();
		clip.flush();
		clip.close();
		try {
			audioInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}