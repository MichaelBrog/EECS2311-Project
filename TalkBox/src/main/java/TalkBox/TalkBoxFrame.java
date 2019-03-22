package main.java.TalkBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TalkBoxFrame extends JFrame {

	// ----- Fields -----
	JButton setConfiguration;
	JButton chooseConfiguration;
	JButton exit;
	JLabel title;
	GroupLayout layout;
	JPanel panel;
	
	/**
	 * Constructor
	 * 
	 * @param l
	 * 		The action listener
	 */
	public TalkBoxFrame (ActionListener l) {
		super("Talk Box App");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);							
	
		this.setResizable(false);
		initializeComponents(l);	
		setPanel();
		
	}
	
	/**
	 * Initialized the fields of this class and associated them with a listener
	 * 
	 * @param l
	 * 		The action listener
	 */
	private void initializeComponents(ActionListener l) {
		setConfiguration = new JButton("Set Configuration");
		setConfiguration.addActionListener(l);
		setConfiguration.setPreferredSize(new Dimension(400, 100));
		setConfiguration.setFont(new Font("Arial", Font.PLAIN, 18));
		
		chooseConfiguration = new JButton("Choose Configuration");
		chooseConfiguration.addActionListener(l);
		chooseConfiguration.setPreferredSize(new Dimension(400, 100));
		chooseConfiguration.setFont(new Font("Arial", Font.PLAIN, 18));
		
		exit = new JButton("Exit");
		exit.addActionListener(l);
		exit.setPreferredSize(new Dimension(400, 100));
		exit.setFont(new Font("Arial", Font.PLAIN, 18));
		
		title = new JLabel("Welcome to the Talk Box App");
		title.setFont(new Font("Arial", Font.BOLD, 18));
		title.setAlignmentX(100);
		
		panel = new JPanel();
		layout = new GroupLayout(panel);
		panel.setLayout(layout);
	}
	
	/**
	 * Created and designs the panel
	 */
	private void setPanel() {
		
		// Automatic gap insertion
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(setConfiguration)
						.addComponent(chooseConfiguration)
						.addComponent(exit))
		);
		
		layout.linkSize(SwingConstants.HORIZONTAL, setConfiguration, chooseConfiguration, exit);
		 
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(title))
				.addGroup(layout.createParallelGroup()
						.addComponent(setConfiguration))
				.addGroup(layout.createParallelGroup()
						.addComponent(chooseConfiguration))
				.addGroup(layout.createParallelGroup()
						.addComponent(exit))
		);
		
		this.add(panel);
		
		
	}
	
	
	
	
}
