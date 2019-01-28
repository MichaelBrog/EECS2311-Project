import java.awt.Dimension;

import java.awt.GridLayout;

import java.awt.event.ActionListener;

import javax.swing.*;

public class SimulatorFrame extends JFrame {

	/**
	
	 * 
	
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 
	 * 
	 * A visual representation of simulation app
	 * 
	 * 
	 * 
	 */

	// ------------ Fields ---------------------

	JButton[] pics; // An array of buttons. Size initialized by the desired amount of buttons

	JPanel panel; // The used panel

	// -----------------------------------------

	public SimulatorFrame(ActionListener l, int n) {

		super("FrameDemo");

		initializePanel(l, n);

		// frame.getContentPane().setBackground(Color.cyan);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setPreferredSize(new Dimension(1100, 300));

		pack();

		setLocationRelativeTo(null);

		setVisible(true);

	}

	/**
	 * 
	 * 
	 * 
	 * @param n
	 * 
	 * 
	 * 
	 *          The number of buttons to be declared
	 * 
	 * 
	 * 
	 * @param l
	 * 
	 * 
	 * 
	 *          The action listener
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 *          A method that initialized the panel and provide the first view for
	 * 
	 *          the client
	 * 
	 * 
	 * 
	 *          Associate the action listener l to the buttons --- GridLayout ?
	 * 
	 *          -----
	 * 
	 * 
	 * 
	 * @param n The number of buttons to be declared
	 * 
	 * @param l The action listener
	 * 
	 * 
	 * 
	 *          A method that initialized the panel and provide the first view for
	 * 
	 *          the client Associate the action listener l to the buttons ---
	 * 
	 *          GridLayout ? ----- >>>>>>> branch 'master' of
	 * 
	 *          https://github.com/ryang-123/EECS2311-Project
	 * 
	 */

	private void initializePanel(ActionListener l, int n) {

		pics = new JButton[n];

		panel = new JPanel(new GridLayout(1, n, 1, 1));

		for (int i = 0; i < pics.length; i++) {

			pics[i] = new JButton("Something");

//			pics[i].setBackground(Color.BLUE);

			pics[i].setVerticalTextPosition(SwingConstants.TOP);

			pics[i].setHorizontalTextPosition(SwingConstants.CENTER);

			pics[i].setVisible(true);

			pics[i].addActionListener(l);

			panel.add(pics[i]);

		}

		this.add(panel);

	}

	/**
	 * 
	 * 
	 * 
	 * @param buttonName
	 * 
	 * 
	 * 
	 *                   the name of the desired button
	 * 
	 * 
	 * 
	 * @param image    
	 * 
	 * 					 The URL or link to the image
	 * 
	 * @param indexOfButton
	 * 
	 * 					 The index of the intended button
	 * 
	 * 
	 * 
	 * @throws IndexOutOfBoundsException if {@code i < 0 || i >= n }.
	 * 
	 * 
	 * 
	 *                                   A method that helps the user create desired
	 * 
	 *                                   button with complete freedom while also
	 * 
	 *                                   updating and changing the text associated
	 * 
	 *                                   with specific buttons.
	 * 
	 */

	public void SetButton(String buttonName, ImageIcon image, int indexOfButton) throws IndexOutOfBoundsException {

		try {

			ImageIcon temp = new ImageIcon("Happy.png");
			
			pics[indexOfButton].setText(buttonName);

			pics[indexOfButton].setIcon(image);

		} catch (IndexOutOfBoundsException e) {

			System.out.println("The index you have entered is invalid");

		}

	}

	/**
	 * 
	 * 
	 * 
	 * play the sound based on the pressed button
	 * 
	 * 
	 * 
	 */

	public static void panelUpdate(JButton b, String buttonName) {

	}

	/**
	 * 
	 * 
	 * 
	 * A main method to test the view of the simulator app
	 * 
	 * 
	 * 
	 */

	public static void main(String[] args) {

		SimulatorFrame s = new SimulatorFrame(null, 4);

		s.SetButton("Happy", new ImageIcon("Happy.png"), 0);

		s.SetButton("Sad", new ImageIcon("https://thumbs.dreamstime.com/z/perplexed-expression-real-man-50490656.jpg"),

				1);

		s.SetButton("Angry",

				new ImageIcon("https://www.improvisedlife.com/cms/wp-content/uploads/2017/11/angry-emoji-1.jpg"), 2);

		s.SetButton("Perplexed", new ImageIcon("Perplexed.png"), 3);

		// SimulatorFrame s = new SimulatorFrame(null, 4);

		s.SetButton("Happy", new ImageIcon("Happy.png"), 0);

		s.SetButton("Sad", new ImageIcon("https://thumbs.dreamstime.com/z/perplexed-expression-real-man-50490656.jpg"),
				1);

		s.SetButton("Angry",
				new ImageIcon("https://www.improvisedlife.com/cms/wp-content/uploads/2017/11/angry-emoji-1.jpg"), 2);

		s.SetButton("Perplexed", new ImageIcon("Perplexed.png"), 3);

	}
}