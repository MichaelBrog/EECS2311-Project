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
	LogFile log;
	
	/**
	 * Constructor. 
	 * Initialized the field of the class
	 * @throws IOException 
	 */
	public TalkBoxListener () throws IOException {
		tb = new TalkBoxFrame(this);
		tb.setVisible(true);
		tb.pack();
		log = new LogFile();
		
		try {
			log.writeToLog("Opened new TalkBox App");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	
		if (e.getActionCommand() == "Set Configuration") {
			
				try {
					log.writeToLog("Pressed: 'Set Configuration' in the 'Talk Box App'");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			
			
			thread = new Thread(new Runnable() {

				@Override
				public void run() {
					conf = new ConfigurationListener(log);
				}
			});
			thread.start();
			
			try {
				log.writeToLog("Opened new thread of 'Configuration app'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
		}
		else if (e.getActionCommand() == "Choose Configuration") {
			/*
			 * Go to additional page so the user could choose which 
			 * configuration he wants to use
			 * 
			 * */
			// In the meantime
			
			try {
				log.writeToLog("Pressed: 'Choose Configuration' in the 'Talk Box App'");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
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
			
			try {
				log.writeToLog("Pressed: 'Exit' in the 'Talk Box App'");
				log.writeToLog("TalkBox app is terminating");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			tb.setVisible(false);
			tb.dispose();
			
			try {
				log.writeToLog("TalkBoxApp has terminated");
				log.stopWriting();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			System.exit(0);
		}
	}
}
