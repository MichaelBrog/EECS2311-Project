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
		this.add(panel);
		JTextArea logs = new JTextArea();
		
		
		logs.setLineWrap(true);
		logs.setWrapStyleWord(true);
		logs.setSize(400, 400);
		logs.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

		JScrollPane scroller = new JScrollPane(logs);
		scroller.setBounds(3, 3, 300, 200);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setPreferredSize(new Dimension(700,310));
		this.getContentPane().add(scroller);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);							
		
		
		
		this.setResizable(false);
		
		
		
		
		
		
	
		while(file.hasNextLine()) {
			String currentlog = file.nextLine();
			logs.append(currentlog);
			logs.append(System.lineSeparator());
		}
		
		
		
		logs.setEditable(false);
		this.pack();
		setVisible(true);
		
		
	}

}