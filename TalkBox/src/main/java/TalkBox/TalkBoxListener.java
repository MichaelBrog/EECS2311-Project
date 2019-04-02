package main.java.TalkBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JComboBox;

public class TalkBoxListener implements ActionListener, ItemListener {

	public static final Logger logger = Logger.getLogger("TalkBox");
	
	
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
		
		File file1 = new File("SimulatorLog.log");
		
		try {
			
			File file2 = new File("ConfigurationLog.log");
			FileHandler fileh = new FileHandler("ConfigurationLog.log");
			logger.addHandler(fileh);
			SimpleFormatter formatter = new SimpleFormatter();
			fileh.setFormatter(formatter);
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		logger.info("Opened new TalkBox App");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if (e.getActionCommand() == "Set Configuration") {
			
				logger.info("Pressed: 'Set Configuration' in the 'Talk Box App'");
			
			
			thread = new Thread(new Runnable() {

				@Override
				public void run() {
					conf = new ConfigurationListener(log);
				}
			});
			thread.start();
			
			logger.info("Opened new thread of 'Configuration app'");
		}
		else if (e.getActionCommand() == "Choose Simulator") {
			tb.chooseProfile(this);
			
		} // choose simulator
		
		else if (e.getActionCommand() == "TBCLog") {
			
			logger.info("Pressed: 'TBCLog' in the 'Talk Box App'");
			LoggingFrame logpop = new LoggingFrame("Configuration App Logs","ConfigurationLog.log");
			LoggingFrame logpop1 = new LoggingFrame("Simulator Logs","SimulatorLog.log");
			
		}// go to log
		
		else if (e.getActionCommand() == "Exit") {
			tb.setVisible(false);
			tb.dispose();
			log.stopWriting();
			System.exit(0);
		} // exit
		
		else if (e.getActionCommand() == "Previous") {
			tb.setPanel();	// Go to the menu	
		} // previous
		
		else if (e.getActionCommand() == "Simulate") {
			logger.info("Pressed: 'Choose Configuration' in the 'Talk Box App'");

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