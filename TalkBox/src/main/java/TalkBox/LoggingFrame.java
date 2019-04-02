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

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class LoggingFrame extends JFrame {

	JPanel panel;
	JLabel title;
	GroupLayout layout;
	
	
	public LoggingFrame(String windowname,String filename) {

		
		super(windowname);
		
		
		
		Scanner file = null;
		try {
			file = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel panel = new JPanel();
		JTextArea logs = new JTextArea();
		JTextArea logs2 = new JTextArea();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);							
		
		this.setSize(1000, 1000);
		
		this.setResizable(true);
		
		this.add(panel);

		
		panel.add(logs);
		panel.add(logs2);
		int i = 0;
		while(file.hasNextLine() && i<45) {
			String currentlog = file.nextLine();
			logs.append(currentlog);
			logs.append(System.lineSeparator());
		}
		while(file.hasNextLine()) {
			String currentlog = file.nextLine();
			logs2.append(currentlog);
			logs2.append(System.lineSeparator());
		}
		
		this.pack();
		setVisible(true);
		
	}

}
