import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurationListener implements ActionListener{
	/**
	 * Implement the Action listener of the pressed buttons/ check box in the configuration app
	 * */
	//------------------ Fields --------------------------
	ConfigurationAppFrame confFrame;					  // An instance of ConfigurationAppFrame.
	
	//----------------------------------------------------
	
	/**
	 * A constructor that calls and initialized the configuration app frame with the current
	 * action listener
	 * */
	public ConfigurationListener () {
		confFrame = new ConfigurationAppFrame(this);     //Creates the GUI and associated this listener with buttons
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
