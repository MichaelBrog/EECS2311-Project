import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

public class ConfigurationListener implements ActionListener, ItemListener{
	/**
	 * Implement the Action listener of the pressed buttons/ check box in the configuration app
	 * */
	//------------------ Fields --------------------------
	ConfigurationAppFrame confFrame;					  // An instance of ConfigurationAppFrame.
	public static int size = 0;
	//----------------------------------------------------
	//	####################
	//	Please use method insideComboBox from ConfigurationAppFrame to get the number of buttons the user wants, 
	// 		then update field 'size' accordingly
	//	Should be done when the button 'next' is pressed for the first time (can be verified if page == 0 is true)
	//	####################
	
	
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
		if(confFrame.page == 0) {
			if (e.getActionCommand() == "next") {
				size = confFrame.comboBox.getSelectedIndex();
				confFrame.pressedNext(size);
				
			}
		}
		else if (confFrame.page == 1) {
			if (e.getActionCommand() == "next") {
				confFrame.pressedNext(size);
			}
			if (e.getActionCommand() == "previous") {
				size = 0;
				confFrame.pressedPrevious(size);
			}
		else if (confFrame.page == 2) {
			
		}

			
		}
		
		// TODO Auto-generated method stub
		
		
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
			System.out.println("image picked");
			confFrame.uploadImageCheckBox();
		}
		if(source.getText() == confFrame.checkToSelfUploadSound.getText()) {
			confFrame.uploadSoundCheckBox();
			System.out.println("sound picked");
			System.out.println("why can't I commit");


		}
	}
		
//hello
//		if (e.getStateChange() == ItemEvent.SELECTED) {
//		if(source == "pickImage") {
//			System.out.println("image picked");
//			confFrame.uploadImageCheckBox();
//		}
//		else if(source == "pickSound") {
//			confFrame.uploadSoundCheckBox();
//			System.out.println("sound picked");
//
//		}
//		}
//		if (e.getStateChange() == ItemEvent.DESELECTED) {
//			if(source == "pickImage") {
//				System.out.println("image picked");
//				confFrame.uploadImageCheckBox();
//			}
//			else if(source == "pickSound") {
//				confFrame.uploadSoundCheckBox();
//				System.out.println("sound picked");
//			}	
//		}	
	}

