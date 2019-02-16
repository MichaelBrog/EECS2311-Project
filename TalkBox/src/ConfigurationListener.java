/*
 * write object/ read object
 * */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;
import java.nio.file.Path;

import javax.swing.JCheckBox; 

public class ConfigurationListener implements ActionListener, ItemListener, TalkBoxConfiguration{
	/**
	 * Implement the Action listener of the pressed buttons/ check box in the configuration app
	 * */
	//------------------ Fields --------------------------
	ConfigurationAppFrame confFrame;					  // An instance of ConfigurationAppFrame.
	public static int size = 0;
	private boolean first = true;
	//----------------------------------------------------
	
	/**
	 * A constructor that calls and initialized the configuration app frame with the current
	 * action listener
	 * */
	public ConfigurationListener () {
		confFrame = new ConfigurationAppFrame(this, this);     //Creates the GUI and associated this listeners with buttons and check boxes
	}
	
	/**
	 * Please create action events for pressing next, previous and exit
	 * Please note that the methods pressedNext and pressedPrevious can be used to adjust the GUI
	 * */

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
				first = false;
			}

			confFrame.pressedNext(size);
		}
		
		else if (e.getActionCommand() == "Previous") {
			if (ConfigurationAppFrame.page == 1) {
				first = true;
			}
			
			confFrame.pressedPrevious(size);
		}
		else if (e.getActionCommand() == "Upload Image") {
			System.out.println(confFrame.pressedUploadImage());
		}
		else if (e.getActionCommand() == "Upload Sound") {
			System.out.println(confFrame.pressedUploadSound());
		}
			
	}

	/**
	 * Please note: designed for the sound and image check box
	 * Create Item events when pressing a check box
	 * Please call methods uploadImageCheckBox and uploadSoundCheckBox when check box is pressed to update the GUI
	 * */
	@Override
	public void itemStateChanged(ItemEvent e) {

		JCheckBox source = (JCheckBox) e.getItemSelectable();

		if(source.getText() == confFrame.checkToSelfUploadImage.getText()) {
			confFrame.uploadImageCheckBox();
		}
		if(source.getText() == confFrame.checkToSelfUploadSound.getText()) {
			confFrame.uploadSoundCheckBox();
		}
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
		return 0;
	}

	@Override
	public int getTotalNumberOfButtons() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Path getRelativePathToAudioFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] getAudioFileNames() {
		// TODO Auto-generated method stub
		return null;
	}
}
