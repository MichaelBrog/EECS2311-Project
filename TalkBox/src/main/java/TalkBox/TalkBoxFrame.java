package main.java.TalkBox;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TalkBoxFrame extends JFrame {

	// ----- Fields -----
	JButton setConfiguration;
	JButton chooseConfiguration;
	JButton openLog; 
	JButton exit;
	JLabel title;
	GroupLayout layout;
	JPanel panel;
	
	// ---- choose simulation -----
	JButton simulate;	// A button that will be directed to the simulation app
	JButton previous;	// A button that will go back to the main Talk 	box 
	JComboBox<String> combo;	// A drop down menu that holds all the profiles
	JLabel titleInSim;	// The title label in the choose simulation page
	
	/**
	 * Constructor
	 * 
	 * @param l
	 * 		The action listener
	 */
	public TalkBoxFrame (ActionListener l, ItemListener i) {
		super("Talk Box App");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);							
	
		this.setResizable(false);
		initializeComponents(l, i);	
		setPanel();
		
	}
	
	/**
	 * Initialized the fields of this class and associated them with a listener
	 * 
	 * @param l
	 * 		The action listener
	 */
	private void initializeComponents(ActionListener l, ItemListener i) {
		setConfiguration = new JButton("Set Configuration");
		setConfiguration.addActionListener(l);
		setConfiguration.setPreferredSize(new Dimension(400, 100));
		setConfiguration.setFont(new Font("Arial", Font.PLAIN, 18));
		
		chooseConfiguration = new JButton("Choose Simulator");
		chooseConfiguration.addActionListener(l);
		chooseConfiguration.setPreferredSize(new Dimension(400, 100));
		chooseConfiguration.setFont(new Font("Arial", Font.PLAIN, 18));
		
		openLog = new JButton("TBCLog");
		openLog.addActionListener(l);
		openLog.setPreferredSize(new Dimension(400, 100));
		openLog.setFont(new Font("Arial", Font.PLAIN, 18));
		
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
		
		// ---- initialize fields for the choose profile page ---
		simulate = new JButton("Simulate");
		simulate.addActionListener(l);
		previous = new JButton("Previous");
		previous.addActionListener(l);
		titleInSim = new JLabel("Please choose a profile:");
		
		File files;
		if (System.getProperty("os.name").startsWith("Windows"))
			files = new File(".\\imageReasource\\TalkBoxData");
		else
			files = new File("./imageReasource/TalkBoxData");
		
		String[] dirs = files.list(new FilenameFilter() {
			  @Override
			  public boolean accept(File current, String name) {
			    return new File(current, name).isDirectory();
			  }
			});
		
		if (dirs != null)
			combo = new JComboBox<String>(dirs);
		else
			combo = new JComboBox<String>();

		combo.addItemListener(i);
	}
	
	/**
	 * Created and designs the panel
	 */
	public void setPanel() {
		
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		layout = new GroupLayout(panel);
		panel.setLayout(layout);
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		// Automatic gap insertion
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(setConfiguration)
						.addComponent(chooseConfiguration)
						.addComponent(openLog)
						.addComponent(exit))
		);
		
		layout.linkSize(SwingConstants.HORIZONTAL, setConfiguration, chooseConfiguration, exit, openLog);
		 
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(title))
				.addGroup(layout.createParallelGroup()
						.addComponent(setConfiguration))
				.addGroup(layout.createParallelGroup()
						.addComponent(chooseConfiguration))
				.addGroup(layout.createParallelGroup()
						.addComponent(openLog))
				.addGroup(layout.createParallelGroup()
						.addComponent(exit))
		);
		
		this.add(panel);
	}
	
	/**
	 * 
	 * Changes the page so the user can pick a profile and simulate it
	 * 
	 */
	public void chooseProfile (ItemListener i) {
		
		File files;
		if (System.getProperty("os.name").startsWith("Windows"))
			files = new File(".\\imageReasource\\TalkBoxData");
//			files = new File("..\\TalkBoxData");
		else
			files = new File("./imageReasource/TalkBoxData");
//			files = new File("../TalkBoxData");
		
		String[] dirs = files.list(new FilenameFilter() {
			  @Override
			  public boolean accept(File current, String name) {
			    return new File(current, name).isDirectory();
			  }
			});
		
		if (dirs != null)
			combo = new JComboBox<String>(dirs);
		else
			combo = new JComboBox<String>();
		combo.insertItemAt("", 0);
		combo.setSelectedIndex(0);
		combo.addItemListener(i);
		
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		layout = new GroupLayout(panel);
		panel.setLayout(layout);
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		// Automatic gap insertion
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(titleInSim)
						.addComponent(combo)
						.addGroup(layout.createSequentialGroup()
								.addComponent(previous)
								.addComponent(simulate)))
		);
		
		layout.linkSize(SwingConstants.HORIZONTAL, previous, simulate);
		 
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(titleInSim))
				.addGroup(layout.createParallelGroup()
						.addComponent(combo))
				.addGroup(layout.createParallelGroup()
					//	.addGroup(layout.createParallelGroup()
						.addComponent(previous)
						.addComponent(simulate))
		);
		
		this.add(panel);
	} // chooseProfile
	
	
	
	
	
}