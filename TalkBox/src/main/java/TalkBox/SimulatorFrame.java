package main.java.TalkBox;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.GridLayout;

import java.awt.event.ActionListener;

import javax.swing.*;
import javax.imageio.ImageIO;

import java.io.BufferedReader;

//import junit.framework.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SimulatorFrame extends JFrame {

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
	JButton[] swap;
	final int MAX_NUM_B = 5;
	final int MAX_NUM_ROW = 3;
	int page = 0;
	JPanel panel; // The used panel
	int number_of_buttons = 0;	// the number of buttons the user picked
	String profile;
	File[] audio_array;
	
	// pagination
	JButton previous;
	JButton next;
	JButton exit;
	// -----------------------------------------

	public SimulatorFrame(ActionListener l, int n, String profile) throws IOException {
		super("FrameDemo");
		this.profile = profile;
		initializePanel(l, n);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(1100, 700));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		number_of_buttons = n;
		initializing();
	}

	public SimulatorFrame(ActionListener l, int n, String profile, int pageNumber) throws IOException {
		super("FrameDemo");
		this.setVisible(false);
		this.profile = profile;
		page = pageNumber;
		initializePanel(l, n);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(1100, 700));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		number_of_buttons = n;
		initializing();
	}	
	
	
	private void initializing () throws IOException {
		
		
		
		String saved_image_path = "";
		File current_image_file;
		File current_sound_file;
		//ImageIcon[] image_array = new ImageIcon[n];
	//	File[] audio_array = new File[number_of_buttons];
		audio_array = new File[number_of_buttons];
		ImageIcon image_file;
		File audio_file = null;

		//changes the location depending on the operating system
		if (System.getProperty("os.name").startsWith("Windows"))
			saved_image_path =   ".\\imageReasource\\TalkBoxData\\" + profile + "\\"; // mac/ linux/ unix
//			saved_image_path =   "..\\TalkBoxData\\" + profile + "\\"; // mac/ linux/ unix
		else
			saved_image_path = "./imageReasource/TalkBoxData/" + profile + "/"; // mac/ linux/ unix
//			saved_image_path = "../TalkBoxData/" + profile + "/"; // mac/ linux/ unix

		File[] files = new File(saved_image_path).listFiles();
		
		
		if (!new File(saved_image_path).exists()) {
			JOptionPane.showMessageDialog(null, "Talk Box data folder named \"imageRepository\" was not found, please reload configurator and ensure simulator is in same folder as \"imageRepository\"");
		}		
		
		
		File namesReader;
		
		if (System.getProperty("os.name").startsWith("Windows"))
			namesReader = new File (".\\imageReasource\\TalkBoxData\\" + profile + "\\names.txt");
//			namesReader = new File ("..\\TalkBoxData\\" + profile + "\\names.txt");
		else
			namesReader = new File ("./imageReasource/TalkBoxData/" + profile + "/names.txt");
//			namesReader = new File ("../TalkBoxData/" + profile + "/names.txt");
		
		
		if (!namesReader.exists()) {
			JOptionPane.showMessageDialog(null, "There isn't any available configuration yet");
			return;
		}
		
		FileReader fr = new FileReader(namesReader);
		BufferedReader br = new BufferedReader(fr);
		String[] name = new String [number_of_buttons];
		
		
		
		//For loop going until the # of buttons in order to match all the images and sounds to the buttons
		for (int k = 0; k < number_of_buttons; k++) {
			current_image_file = null;
			
			//Finds the correct file name and sets it to current_image_file
			for (File file : files) {
				if (file.getName().startsWith("Image_" + (k + 1) + ".ser")) {
					current_image_file = file;
				}
			}
			current_sound_file = null;
			//Finds the correct file name and sets it to current_sound_file
			for (File file : files) {
				if (file.getName().startsWith("Audio_" + (k + 1) + ".ser")) {
					current_sound_file = file;
				}
			}
			
			//deserializing the image file and turning the file to an image and sets it in image_file
			image_file = null;
			if (current_image_file != null) {
				try {
					FileInputStream fileIn = new FileInputStream(current_image_file.getPath());
					ObjectInputStream in = new ObjectInputStream(fileIn);
					image_file = new ImageIcon(ImageIO.read((File) in.readObject()));
					in.close();
					fileIn.close();
				} catch (IOException i) {
					i.printStackTrace();
					return;
				} catch (ClassNotFoundException c) {
					System.out.println("File class not found");
					c.printStackTrace();
					return;
				}
				//deserializing the audio file and sets it in audio_file
				audio_file = null;
				if (current_sound_file != null) {
					try {
						FileInputStream fileIn = new FileInputStream(current_sound_file.getPath());
						ObjectInputStream in = new ObjectInputStream(fileIn);
						audio_file = (File) in.readObject();
						in.close();
						fileIn.close();
					} catch (IOException i) {
						i.printStackTrace();
						return;
					} catch (ClassNotFoundException c) {
						System.out.println("File class not found");
						c.printStackTrace();
						return;
					}
				}

				//adding both the image and audio files to an array
				//The image array might not be needed 
				audio_array[k] = audio_file;
				//image_array[k] = image_file;
				
				name[k] = br.readLine();

				//this.SetButton(name, image_array[k], audio_array, k);
				//this.SetButton(name[k], image_file, audio_array, k);
				this.SetButton(name[k], image_file, k);
			}	
		}
		br.close();
		fr.close();
	}

	/**
	 * 
	 * 
	 * 
	 * @param n
	 * 
	 *          The number of buttons to be declared
	 * 
	 * @param l
	 * 
	 *          The action listener
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
	 * 
	 */

	private void initializePanel(ActionListener l, int n) {
/*		int row_num = 1;
		pics = new JButton[n];
		if(n > 5) {
			row_num = (int) ( Math.floor(n / 5));
		}
		
		
		panel = new JPanel(new GridLayout(row_num, n, 5, 5));
*/
		pics = new JButton[n];
		swap = new JButton[n];
		
		for (int i = 0; i < pics.length; i++) {
			pics[i] = new JButton("");
			pics[i].setVerticalTextPosition(SwingConstants.TOP);
			pics[i].setHorizontalTextPosition(SwingConstants.CENTER);
			pics[i].setVisible(true);
			pics[i].addActionListener(l);
			
			swap[i] = new JButton("Swap");
			swap[i].setVerticalTextPosition(SwingConstants.TOP);
			swap[i].setHorizontalTextPosition(SwingConstants.CENTER);
			swap[i].setVisible(true);
			swap[i].addActionListener(l);
		}

		//this.add(panel);
		
		// initialize buttons
		previous = new JButton("Previous");
		previous.addActionListener(l);
		next = new JButton("Next");
		next.addActionListener(l);
		exit = new JButton ("Exit");
		exit.addActionListener(l);
		
		
		JPanel helper = new JPanel(new GridLayout(1, 5, 5, 5));	// a helper panel, 1 row, 5 buttons
		JPanel helper1 = new JPanel(new GridLayout(1, 5, 5, 5));	// a helper panel, 1 row, 5 buttons
		
		panel = new JPanel (new GridLayout(3, 1, 5, 5));	// 3 rows, 1 column
	//	panel = new JPanel (new GridLayout(2, 1, 5, 5));	// 2 rows, 1 column USE THIS UNTIL MICHALS CODE
		
		
		int from = page*this.MAX_NUM_B;
		int to = page*this.MAX_NUM_B+5;
		
		for (int counter = from; counter <  to && counter < pics.length; counter ++) {
			
			helper.add(pics[counter]);
			helper1.add(swap[counter]);
		}
		
		panel.add(helper);
		panel.add(helper1);
		
		
		boolean addNext = false;
		if (Math.ceil(this.pics.length/((page+1)*this.MAX_NUM_B)) > 1)
			addNext = true;
		
		
		
		if (page == 0) {	// first page
			helper = new JPanel(new GridLayout(1, 2, 5, 5));	// a helper panel, 1 row, 5 buttons
			helper.add(exit);
			
			if (addNext)
				helper.add(next);
		}
		else if (page == n-1) { // last page
			helper = new JPanel(new GridLayout(1, 2, 5, 5));	// a helper panel, 1 row, 5 buttons
			helper.add(previous);
			helper.add(exit);
		}
		else {
			helper = new JPanel(new GridLayout(1, 3, 5, 5));	// a helper panel, 1 row, 5 buttons
			helper.add(previous);
			helper.add(next);
			helper.add(exit);
		}
		
		panel.add(helper);
		this.add(panel);
		
		
	}

	/**
	 * 
	 * @param buttonName
	 *                   the name of the desired button
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
	 *                                   A method that helps the user create desired
	 * 
	 *                                   button with complete freedom while also
	 * 
	 *                                   updating and changing the text associated
	 * 
	 *                                   with specific buttons.
	 * @throws IOException 
	 * 
	 */

	//public void SetButton(String buttonName, String image, int indexOfButton) throws IndexOutOfBoundsException {
	public void SetButton(String buttonName, ImageIcon icon, int indexOfButton) throws IndexOutOfBoundsException, IOException {
		try {
			//ImageIcon icon = new ImageIcon(image);
			pics[indexOfButton].setText(buttonName);
			pics[indexOfButton].setIcon(icon);
			pics[indexOfButton].setActionCommand(indexOfButton + "");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("The index you have entered is invalid");
		}
	}

	/**
	 * 
	 * play the sound based on the pressed button
	 * 
	 */

	private void panelUpdateNext() {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		this.setVisible(false);
		
		JPanel helper = new JPanel(new GridLayout(1, 5, 5, 5));	// a helper panel, 1 row, 5 buttons
		JPanel helper1 = new JPanel(new GridLayout(1, 5, 5, 5));	// a helper panel, 1 row, 5 buttons
		
		panel = new JPanel (new GridLayout(3, 1, 5, 5));	// 3 rows, 1 column
		//	panel = new JPanel (new GridLayout(2, 1, 5, 5));	// 2 rows, 1 column USE THIS UNTIL MICHALS CODE
		int from = page*this.MAX_NUM_B;
		int to = page*this.MAX_NUM_B+5;
		
		for (int counter = from; counter <  to && counter < pics.length; counter ++) {
			
			helper.add(pics[counter]);
			helper1.add(swap[counter]);
		}
		
		panel.add(helper);
		panel.add(helper1);
		
	//	helper = new JPanel(new GridLayout(1, 5, 5, 5));	// a helper panel, 1 row, 5 buttons
		/*
		 * Will be done by michael
		 */
		boolean addNext = false;
		if (Math.ceil(this.pics.length/((page+1)*this.MAX_NUM_B)) > 1)
			addNext = true;
		
		
		panel.revalidate();
		if (page == 0) {	// first page

			helper = new JPanel(new GridLayout(1, 2, 5, 5));	// a helper panel, 1 row, 5 buttons
			helper.add(exit);
			
			if (addNext)
				helper.add(next);
		}
		else if (page == Math.ceil(pics.length/5)) { // last page
			helper = new JPanel(new GridLayout(1, 2, 5, 5));	// a helper panel, 1 row, 5 buttons
			helper.add(previous);
			helper.add(exit);
		}
		else {
			helper = new JPanel(new GridLayout(1, 3, 5, 5));	// a helper panel, 1 row, 5 buttons
			helper.add(previous);
			helper.add(next);
			helper.add(exit);
		}
		
		panel.add(helper);
		panel.revalidate();
		this.add(panel);
		this.setVisible(true);
	}

	/**
	 * The method the listener calls when pressed next
	 */
	public void pushedNext () {
		page ++;
		panelUpdateNext();
	}
	
	/**
	 * The method the listener calls when pressed previous
	 */
	public void pushedPrevious () {
		page --;
		panelUpdateNext();
	}
	
	public void changeToGreen(JButton b) {
		b.setBackground(Color.GREEN);
		b.setOpaque(true);
		b.setBorderPainted(false);
	}
	
	public void resetToOrigin (JButton b) {
		b.setOpaque(false);
		b.setBorderPainted(true);
	}
	
	/**
	 * 
	 * @param b an instance of a button
	 * @return the index in which the button is located
	 */
	public int returnPic (JButton b) {
		
		for (int i = 0; i < pics.length; i ++) {
			if (b == swap[i])
				return i;
		}
		return -1;
	}
	
	
	
}