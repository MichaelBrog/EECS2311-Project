package main.java.TalkBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TalkBoxListener implements ActionListener {

	// --- Fields ---
	TalkBoxFrame tb;
	SimulationListener sim;
	Thread thread;
	ConfigurationListener conf;
	
	
	/**
	 * Constructor. 
	 * Initialized the field of the class
	 */
	public TalkBoxListener () {
		tb = new TalkBoxFrame(this);
		tb.setVisible(true);
		tb.pack();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand() == "Set Configuration") {
		
			thread = new Thread(new Runnable() {

				@Override
				public void run() {
					conf = new ConfigurationListener();
				}
			});
			thread.start();
			
			
		}
		else if (e.getActionCommand() == "Choose Configuration") {
			/*
			 * Go to additional page so the user could choose which 
			 * configuration he wants to use
			 * 
			 * */
			// In the meantime
			
			thread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						sim = new SimulationListener();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			thread.start();
			
			
		}
		else if (e.getActionCommand() == "Exit") {
			tb.setVisible(false);
			tb.dispose();
			System.exit(0);
		}
	}
}
