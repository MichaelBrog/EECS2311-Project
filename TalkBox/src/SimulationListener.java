import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationListener implements ActionListener{
	/**
	 * Implement the Action listener of the pressed buttons/ check box in the configuration app
	 * */
	//------------------ Fields --------------------------
	SimulatorFrame simFrame;
	
	//----------------------------------------------------
	
	/**
	 * @param n
	 * 			The number of buttons in the panel
	 * A constructor that calls and initialized the configuration app frame with the current
	 * action listener
	 * */
	public SimulationListener (int n) {
		simFrame = new SimulatorFrame(this, n);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
