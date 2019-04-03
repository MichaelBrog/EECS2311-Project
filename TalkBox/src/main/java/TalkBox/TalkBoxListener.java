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
	public TalkBoxFrame tb;
	public SimulationListener sim;
	Thread thread;
	public ConfigurationListener conf;
	public LogFile log;
	String profile;	// the profile the user chooses 
	public boolean sc = false; //set configuration
	public boolean cs = false; //choose simulation
	public boolean logBoolean = false;
	public boolean exitBoolean = false;
	public boolean simulationWorking = false;
	
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
			File file1 = new File("SimulatorLog.log");
			if (!file1.exists())
				file1.createNewFile();
			
			File file2 = new File("ConfigurationLog.log");
			if (!file2.exists())
				file2.createNewFile();
			
			
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
				sc = true;
				System.out.println(sc);
			
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
			cs = true;
			System.out.println(cs);
			
		} // choose simulator
		
		else if (e.getActionCommand() == "TBCLog") {
			
			logBoolean = true;
			System.out.println(logBoolean);
			logger.info("Pressed: 'TBCLog' in the 'Talk Box App'");
			LoggingFrame logpop = new LoggingFrame("Configuration App Logs","ConfigurationLog.log");
			LoggingFrame logpop1 = new LoggingFrame("Simulator Logs","SimulatorLog.log");
			
		}// go to log
		
		else if (e.getActionCommand() == "Exit") {
			exitBoolean = true;
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
			simulationWorking = true;
			System.out.println(simulationWorking);

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