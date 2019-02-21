/*
 * write object/ read object
 * */
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.swing.JCheckBox;
import javax.swing.JComboBox; 

public class ConfigurationListener implements ActionListener, ItemListener, TalkBoxConfiguration, Runnable{
	/**
	 * Implement the Action listener of the pressed buttons/ check box in the configuration app
	 * */
	//------------------ Fields --------------------------
	ConfigurationAppFrame confFrame;					  // An instance of ConfigurationAppFrame.
	RecordAudio record;
	public static int size = 0;
	private boolean first = true;
	Thread thread;
	//----------------------------------------------------
	
	/**
	 * A constructor that calls and initialized the configuration app frame with the current
	 * action listener
	 * */
	public ConfigurationListener () {
		confFrame = new ConfigurationAppFrame(this, this);     //Creates the GUI and associated this listeners with buttons and check boxes
		record = new RecordAudio();
		
	}
	
	/**
	 * Please create action events for pressing next, previous and exit
	 * Please note that the methods pressedNext and pressedPrevious can be used to adjust the GUI
	 * */

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand() == "Exit") {
			confFrame.setVisible(false);
			confFrame.dispose();
			System.exit(0);
		}

		else if (e.getActionCommand() == "Next") {
			if (first) {
				size = confFrame.getSizeButtons();
				if (size != 0)
					first = false;
			}
			if (size != 0) {
				confFrame.pressedNext(size);
				confFrame.dropDownImage.setSelectedIndex(0);
				confFrame.dropDownSound.setSelectedIndex(0);
			}
		}
		
		else if (e.getActionCommand() == "Previous") {
			if (ConfigurationAppFrame.page == 1) {
				first = true;
			}
			
			confFrame.pressedPrevious(size);
			confFrame.dropDownImage.setSelectedIndex(0);
			confFrame.dropDownSound.setSelectedIndex(0);
		}
//<<<<<<< HEAD
		else if (e.getActionCommand() == "pickImage") {
//=======
//		else if (e.getActionCommand() == "Upload Image") {
//			System.out.println(confFrame.pressedUploadImage());
//		}
//		else if (e.getActionCommand() == "Upload Sound") {
//			String path = confFrame.pressedUploadSound();
//			
//		}
//		else if (e.getActionCommand() == "Start Recording") {
//			
//			thread = new Thread(new Runnable() {
//            public void run() {
//              record.run();
//              
//            }
//        });
//		thread.start();
//		
//		}
//		else if (e.getActionCommand() == "Stop Recording") {
//			record.finish();
//			thread.stop();
//		}
//			
//	}

		}
		else if (e.getActionCommand() == "pickSound") {
			
			confFrame.pressedPrevious(size);
		}
		
		
		
		
		
		
	}
	
	
	/**
	 * Please note: designed for the sound and image check box
	 * Create Item events when pressing a check box
	 * Please call methods uploadImageCheckBox and uploadSoundCheckBox when check box is pressed to update the GUI
	 * */
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		JComboBox combo = (JComboBox) e.getSource();
		
		if (combo.getSelectedItem() == "Pick Image" ) {
			confFrame.uploadImageDropMenu("Pick Image");
		}
		else if (combo.getSelectedItem() == "Upload Image") {
			confFrame.uploadImageDropMenu("Upload Image");
		}
		else if (combo.getSelectedItem() == "Upload Sound" ) {
			confFrame.uploadSoundDropMenu("Upload Sound");
		}
		else if (combo.getSelectedItem() == "Pick Sound") {
			confFrame.uploadSoundDropMenu("Pick Sound");
		}
		else if (combo.getSelectedItem() == "Record Sound") {
			confFrame.uploadSoundDropMenu("Record Sound");
		}
		else if (combo.getSelectedItem() == "") {
			confFrame.resetDropMenu();
		}
			

		/*JCheckBox source = (JCheckBox) e.getItemSelectable();

		if(source.getText() == confFrame.checkToSelfUploadImage.getText()) {
			confFrame.uploadImageCheckBox();
		}
		if(source.getText() == confFrame.checkToSelfUploadSound.getText()) {
			confFrame.uploadSoundCheckBox();
		}*/
	}

	
	
	// -------------------------------------------------------------------------------
	// ------ Following methods implement the TalkBoxConfiguration interface ---------
	// -------------------------------------------------------------------------------
	
	@Override
	public int getNumberOfAudioButtons() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int getNumberOfAudioSets() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int getTotalNumberOfButtons() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Path getRelativePathToAudioFiles() {
		// TODO Auto-generated method stub
		Path path = FileSystems.getDefault().getPath("TalkBoxData", "Audio.txt");
		return path;
	}

	@Override
	public String[][] getAudioFileNames() {
		// TODO Auto-generated method stub
		return null;
	}

	
	// Thread interface
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}


//TO DO: Work on upload image/sound
