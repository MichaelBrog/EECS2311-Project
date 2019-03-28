package main.java.TalkBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.JComboBox;

public class TalkBoxListener implements ActionListener, ItemListener {

	// --- Fields ---
	TalkBoxFrame tb;
	SimulationListener sim;
	Thread thread;
	ConfigurationListener conf;
	LogFile log;
	String profile;	// the profile the user chooses 
	
	/**
	 * Constructor. 
	 * Initialized the field of the class
	 * @throws IOException 
	 */
	public TalkBoxListener () throws IOException {
		tb = new TalkBoxFrame(this, this);
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
		else if (e.getActionCommand() == "Choose Simulator") {
			tb.chooseProfile(this);
			
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
		} // exit
		
		else if (e.getActionCommand() == "Previous") {
			tb.setPanel();	// Go to the menu	
		} // previous
		
		else if (e.getActionCommand() == "Simulate") {
			try {
					log.writeToLog("Pressed: 'Choose Configuration' in the 'Talk Box App'");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			thread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							sim = new SimulationListener(log, profile);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			thread.start();
		
		
		}
		
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		
		JComboBox<String> combo = (JComboBox<String>) e.getSource();
		this.profile = (String) combo.getSelectedItem();
	}
}
