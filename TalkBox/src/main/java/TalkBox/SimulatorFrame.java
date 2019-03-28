package main.java.TalkBox;
import java.awt.Dimension;

import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;

import javax.swing.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.BufferedReader;

//import junit.framework.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

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

	JPanel panel; // The used panel
	int number_of_buttons = 0;	// the number of buttons the user picked
	String profile;
	// -----------------------------------------

	public SimulatorFrame(ActionListener l, int n, String profile) throws IOException {
		super("FrameDemo");
		this.profile = profile;
		initializePanel(l, n);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(1100, 300));
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
		File[] audio_array = new File[number_of_buttons];
		ImageIcon image_file;
		File audio_file = null;

		//changes the location depending on the operating system
		if (System.getProperty("os.name").startsWith("Windows"))
			saved_image_path =   ".\\imageReasource\\TalkBoxData\\" + profile + "\\"; // mac/ linux/ unix
		else
			saved_image_path = "./imageReasource/TalkBoxData/" + profile + "/"; // mac/ linux/ unix

		File[] files = new File(saved_image_path).listFiles();
		
		
		if (!new File(saved_image_path).exists()) {
			JOptionPane.showMessageDialog(null, "Talk Box data folder named \"imageRepository\" was not found, please reload configurator and ensure simulator is in same folder as \"imageRepository\"");
		}		
		
		
		File namesReader;
		
		if (System.getProperty("os.name").startsWith("Windows"))
			namesReader = new File (".\\imageReasource\\TalkBoxData\\" + profile + "\\names.txt");
		else
			namesReader = new File ("./imageReasource/TalkBoxData/" + profile + "/names.txt");
		
		
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
				this.SetButton(name[k], image_file, audio_array, k);
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
		int row_num = 1;
		pics = new JButton[n];

		if(n > 5) {
			row_num = (int) ( Math.floor(n / 5));
		}
		
		
		panel = new JPanel(new GridLayout(row_num, n, 5, 5));

		for (int i = 0; i < pics.length; i++) {
			pics[i] = new JButton("");
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
	 * @throws IOException 
	 * 
	 */

	//public void SetButton(String buttonName, String image, int indexOfButton) throws IndexOutOfBoundsException {
	public void SetButton(String buttonName, ImageIcon icon, File[] audio, int indexOfButton) throws IndexOutOfBoundsException, IOException {
		try {
			//ImageIcon icon = new ImageIcon(image);
			pics[indexOfButton].setText(buttonName);
			pics[indexOfButton].setIcon(icon);
			pics[indexOfButton].setActionCommand(indexOfButton + "");
			pics[indexOfButton].addActionListener(new SimulationListener(audio));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("The index you have entered is invalid");
		}
	}

	/**
	 * 
	 * play the sound based on the pressed button
	 * 
	 */

	public static void panelUpdate(JButton b, String buttonName) {

	}

	
	
	
/*	/**
	 * 
	 * 
	 * 
	 * A main method to test the view of the simulator app
	 * @throws IOException 
	 * 
	 * 
	 * 
	 */
/*
	public static void main(String[] args) throws IOException {
		int number_of_buttons = 0;
		String saved_image_path = "";
		String homeDirectory = System.getProperty("user.dir");
		File current_file = null;
		Scanner scan;
		String protocol = SimulatorFrame.class.getResource("").getProtocol();

		
		String path = Test.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath = "";
		try {
			decodedPath = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	
		if (System.getProperty("os.name").startsWith("Windows"))
			//saved_image_path = "C:\\Users\\Michael\\Desktop\\talk box data\\"; // mac/ linux/ unix
			saved_image_path =   ".\\imageReasource\\TalkBoxData\\"; // mac/ linux/ unix
		else
			saved_image_path =   "./imageReasource/TalkBoxData/"; // mac/ linux/ unix
			//saved_image_path = homeDirectory + "C:\\Users\\Michael\\Desktop\\talk box data\\"; // mac/ linux/ unix

		File[] files = new File(saved_image_path).listFiles();
		if (new File(saved_image_path).exists()) {

			current_file = null;
			//Finding a file with the number of buttons, ideally we don't need a for loop to find it, check for other method
			for (File file : files) {
				if (file.getName().endsWith("Buttons" + ".txt")) {
					current_file = file;
				}
			}		
			//scanner to scan the file and get an int value for the number of buttons
			//if(current_file != null || !Objects.equals(protocol, "jar")) {
				scan = null;
				try {
					scan = new Scanner(current_file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				number_of_buttons = Integer.parseInt(scan.next());
			}
	//	}


		//	1);

		//s.SetButton("Angry","C:\\Users\\ryann\\git\\EECS2311-Project\\EECS2311-Project\\TalkBox\\src\\Angry.jpg", 2);


		//s.SetButton("Perplexed","C:\\Users\\ryann\\git\\EECS2311-Project\\EECS2311-Project\\TalkBox\\src\\Perplexed.jpg", 3);



		if(number_of_buttons != 0)
			new SimulatorFrame(null, number_of_buttons);
		else 
			new SimulatorFrame(null, 0);
	}*/
}