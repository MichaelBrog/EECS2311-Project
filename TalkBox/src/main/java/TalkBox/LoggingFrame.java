package main.java.TalkBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class LoggingFrame extends JFrame {

	JPanel panel;
	JLabel title;
	GroupLayout layout;

	public LoggingFrame(String windowname, String filename) {

		super(windowname);

		Scanner file = null;
		try {
			file = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JPanel panel = new JPanel();
		this.add(panel);
		JTextArea logs = new JTextArea();

		logs.setLineWrap(true);
		logs.setWrapStyleWord(true);
		logs.setSize(400, 400);
		logs.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

		JScrollPane scroller = new JScrollPane(logs);
		scroller.setBounds(3, 3, 300, 200);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setPreferredSize(new Dimension(700, 310));
		this.getContentPane().add(scroller);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);

		this.setResizable(false);
		int openedconfig = 0;
		int exit = 0;
		int pickingSound = 0;
		int pickingImage = 0;
		int previous = 0;
		int previousT = 0;
		int personalImage = 0;
		int personalSound = 0;
		int recorded = 0;
		int recordedStoped = 0;
		int previewSound = 0;
		int previewImage = 0;
		int demo = 0;
		int simChoose = 0;
		int simOpen = 0;
		int musicPlay = 0;
		int musicNo = 0;
		while (file.hasNextLine()) {
			String currentlog = file.nextLine();
			if(filename == "ConfigurationLog.log") {
			if (currentlog.contains("Pressed: 'Set Configuration' in the 'Talk Box App'")) {
				openedconfig++;
			} else if (currentlog.contains("Pressed: 'Exit' in the 'Configuration App'")) {
				exit++;
			} else if (currentlog.contains("Pressed: 'Upload Sound' in the 'Configuration App'")) {
				personalSound++;
			} else if (currentlog.contains("Pressed: 'Pick Sound' in the 'Configuration App'")) {
				pickingSound++;
			} else if (currentlog.contains("Pressed: 'Start Recording' in the 'Configuration App'")) {
				recorded++;
			} else if (currentlog.contains("Pressed: 'Stop Recording' in the 'Configuration App'")) {
				recordedStoped++;
			} else if (currentlog.contains("Pressed: 'Upload Image' in the 'Configuration App'")) {
				personalImage++;
			} else if (currentlog.contains("Pressed: 'Pick Image' in the 'Configuration App'")) {
				pickingImage++;
			} else if (currentlog.contains("Pressed: 'Previous' in the 'Configuration App'")) {
				previous++;
			} else if (currentlog.contains("Pressed: 'Preview Sound' in the 'Configuration App'")) {
				previewSound++;
			} else if (currentlog.contains("Pressed: 'Preview Image' in the 'Configuration App'")) {
				previewImage++;
			} else if (currentlog.contains("Pressed: 'Demo' in the 'Configuration App'")) {
				demo++;
			}
			
			}
			if(filename == "SimulatorLog.log") {
			if (currentlog.contains("User is currently choosing which profile to simulate")) {
				simChoose++;
			} else if (currentlog.contains("Pressed: 'Previous' in the 'Talk Box App'")) {
				previousT++;
			} else if (currentlog.contains("Pressed: 'Simulate' in the 'Talk Box App'")) {
				simOpen++;
			} else if (currentlog.contains("Found the audio files")) {
				musicPlay++;
			} else if (currentlog.contains("Didn't find audio files")) {
				musicNo++;
			}
			}
		}
		if(filename == "ConfigurationLog.log") {
			logs.append("an instance of the 'Configuration App' was opened "+openedconfig+" times by the user."+System.lineSeparator());
			logs.append("the Exit button was pressed in the 'Configuration App' "+exit+" times."+System.lineSeparator());
			logs.append("the user Uploaded an Audio File of his choosing "+personalSound+" times."+System.lineSeparator());
			logs.append("the user Picked one of the preset Audio File " +pickingSound+ " times."+System.lineSeparator());
			logs.append("the user Started recording their unique Audio File "+recorded+ " times."+System.lineSeparator());
			logs.append("the user manually stopped the sound recording "+recordedStoped+" times."+System.lineSeparator());
			logs.append("the user Uploaded an Image of his choosing "+personalImage+" times."+System.lineSeparator());
			logs.append("the user Picked one of the preset Images " +pickingImage+ " times."+System.lineSeparator());
			logs.append("the user decided to append something by pressing 'Previous' in the 'Configuration App' "+previous+ " times"+System.lineSeparator());
			logs.append("the user decided to append something by pressing 'Previous' in the 'Talk Box App' "+previousT+ " times"+System.lineSeparator());
			logs.append("the user previewed the Sound "+previewSound+" times"+System.lineSeparator());
			logs.append("the user previewed the Image "+previewImage+" times"+System.lineSeparator());
			logs.append("the user previewed the simulator by pressing 'Demo' in the 'Configuration App' "+demo+" times"+System.lineSeparator());
		}else if(filename == "SimulatorLog.log") {
			logs.append("the user chose 'Choose Simulator' "+simChoose+" times"+System.lineSeparator());
			logs.append("The user simulated the TalkBox "+simOpen+ " times"+System.lineSeparator());
			logs.append("the Audio File was successfuly found and played "+musicPlay+" times"+System.lineSeparator());
			logs.append("the Audio File was not found "+musicNo+ " times"+System.lineSeparator());
		}
			
			logs.append(System.lineSeparator());

		//while (file.hasNextLine()) {
	//		String currentlog = file.nextLine();
		//	logs.append(currentlog);
		//	logs.append(System.lineSeparator());
		//}

		logs.setEditable(false);
		this.pack();
		setVisible(true);

	}

}