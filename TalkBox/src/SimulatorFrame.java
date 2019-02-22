import java.awt.Dimension;

import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;

import javax.swing.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

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
	SimulatorFrame sim;


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
		
		
		
		
		int number_of_buttons = n;
		String saved_image_path = "";
		String homeDirectory = System.getProperty("user.dir");
		File current_image_file;
		File current_sound_file;
		ImageIcon[] image_array = new ImageIcon[n];
		Clip[] clip_array = new Clip[n];
		File[] audio_array = new File[n];
		ImageIcon image_file;
		Clip audio_file;
		File file1 = null;

		
		if (System.getProperty("os.name").startsWith("Windows"))
			saved_image_path = homeDirectory + "\\src\\TalkBoxData\\"; // mac/ linux/ unix
		else
			saved_image_path = homeDirectory + "/src/TalkBoxData/"; // mac/ linux/ unix

		File[] files = new File(saved_image_path).listFiles();

		for (int k = 0; k < number_of_buttons; k++) {
			current_image_file = null;
			for (File file : files) {
				if (file.getName().startsWith("Image_" + (k + 1) + ".ser")) {
					current_image_file = file;
					System.out.println(file.getName());
					System.out.println(file.getPath());

				}
			}
			current_sound_file = null;
			for (File file : files) {
				if (file.getName().startsWith("Audio_" + (k + 1) + ".ser")) {
					current_sound_file = file;
					System.out.println(file.getName());
					System.out.println(file.getPath());

				}
			}
			//deserializing the image file 
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
				//deserializing the audio file 
				audio_file = null;
				if (current_sound_file != null) {
					try {
						FileInputStream fileIn = new FileInputStream(current_sound_file.getPath());
						ObjectInputStream in = new ObjectInputStream(fileIn);
						file1 = (File) in.readObject();
//						AudioInputStream audioInput = AudioSystem.getAudioInputStream(file1);
//						audio_file = AudioSystem.getClip();
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
					System.out.println("Audio got to array: " + k);
				}
				audio_array[k] = file1;
					clip_array[k] = audio_file;
				image_array[k] = image_file;
				
			this.SetButton(current_image_file.getName(), image_array[k], audio_array, k);
			}	
		}

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

			pics[i] = new JButton("");

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

	//public void SetButton(String buttonName, String image, int indexOfButton) throws IndexOutOfBoundsException {
	public void SetButton(String buttonName, ImageIcon icon, File[] audio, int indexOfButton) throws IndexOutOfBoundsException {
		try {
			//ImageIcon icon = new ImageIcon(image);
			
			
			
			

			pics[indexOfButton].setText(buttonName);
			System.out.println("here");

			pics[indexOfButton].setIcon(icon);
			pics[indexOfButton].addActionListener(new SimulationListener(audio));
			
			
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
		int number_of_buttons = 0;
		String saved_image_path = "";
		String homeDirectory = System.getProperty("user.dir");
		File current_file = null;
	
		if (System.getProperty("os.name").startsWith("Windows"))
			saved_image_path = homeDirectory + "\\src\\TalkBoxData\\"; // mac/ linux/ unix
		else
			saved_image_path = homeDirectory + "/src/TalkBoxData/"; // mac/ linux/ unix

		File[] files = new File(saved_image_path).listFiles();

			current_file = null;
			for (File file : files) {
				if (file.getName().endsWith("Buttons" + ".txt")) {
					current_file = file;
					System.out.println(file.getName());
					System.out.println(file.getPath());

				}
			}		
		

		Scanner scan = null;
		
		try {
			scan = new Scanner(current_file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

		number_of_buttons = Integer.parseInt(scan.next());

		new SimulatorFrame(null, number_of_buttons);


	}
}