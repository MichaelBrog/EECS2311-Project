package main.java.TalkBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		int number_of_buttons = 0;
		String saved_image_path = "";
		String homeDirectory = System.getProperty("user.dir");
		File current_file = null;
		Scanner scan;
		String protocol = SimulatorFrame.class.getResource("").getProtocol();
	
		if (System.getProperty("os.name").startsWith("Windows"))
			saved_image_path =   ".\\imageReasource\\TalkBoxData\\"; // mac/ linux/ unix
		else
			saved_image_path =   "./imageReasource/TalkBoxData/"; // mac/ linux/ unix

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
				scan = null;
				try {
					scan = new Scanner(current_file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				number_of_buttons = Integer.parseInt(scan.next());
		}// if
		
		SimulationListener sim;

		if(number_of_buttons != 0) {
		//	new SimulatorFrame(null, number_of_buttons);
			 sim = new SimulationListener(number_of_buttons);
		}
		else 
			sim = new SimulationListener();
	}
	
}


