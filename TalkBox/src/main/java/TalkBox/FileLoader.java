package main.java.TalkBox;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;




public class FileLoader {
	
	

	public static void loadfiles() {

		String saved_image_path_W = "\\imageReasource\\imageRepository"; // windows
		String saved_image_path_M = "/imageReasource/imageRepository";	// mac/ linux/ unix
		String saved_audio_path_W = "\\imageReasource\\soundRepository"; // windows
		String saved_audio_path_M = "/imageReasource/soundRepository";	// mac/ linux/ unix
		CodeSource codeSource = MainConfiguration.class.getProtectionDomain().getCodeSource();
		File jarFile = null;
		
		try {
			jarFile = new File(codeSource.getLocation().toURI().getPath());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String jarDir = jarFile.getParentFile().getPath();
	
		try {
			if (System.getProperty("os.name").startsWith("Windows")) {
				Files.createDirectories(Paths.get(jarDir + saved_image_path_W));
				Files.createDirectories(Paths.get(jarDir + saved_audio_path_W));
				
				printfiles("thankyou1.wav", jarDir + saved_audio_path_W + "\\" + "thankyou1.wav");
				printfiles("thankyou2.wav", jarDir + saved_audio_path_W + "\\" + "thankyou2.wav");
				printfiles("thankyou3.wav", jarDir + saved_audio_path_W + "\\" + "thankyou3.wav");
				printfiles("thankyou4.wav", jarDir + saved_audio_path_W + "\\" + "thankyou4.wav");
				printfiles("thankyou5.wav", jarDir + saved_audio_path_W + "\\" + "thankyou5.wav");
				printfiles("thankyou6.wav", jarDir + saved_audio_path_W + "\\" + "thankyou6.wav");
				printfiles("thankyou7.wav", jarDir + saved_audio_path_W + "\\" + "thankyou7.wav");
				printfiles("bye.wav", jarDir + saved_audio_path_W + "\\" + "bye.wav");
				printfiles("happy.wav", jarDir + saved_audio_path_W + "\\" + "happy.wav");
				printfiles("hello.wav", jarDir + saved_audio_path_W + "\\" + "hello.wav");
				printfiles("no.wav", jarDir + saved_audio_path_W + "\\" + "no.wav");
				printfiles("yes.wav", jarDir + saved_audio_path_W + "\\" + "yes.wav");
				
				printfiles("Happy.jpg", jarDir + saved_image_path_W + "\\" + "Happy.jpg");
				printfiles("Sad.jpg", jarDir + saved_image_path_W + "\\" + "Sad.jpg");
				printfiles("Perplexed.jpg", jarDir + saved_image_path_W + "\\" + "Perplexed.jpg");
				printfiles("Angry.jpg", jarDir + saved_image_path_W + "\\" + "Angry.jpg");
			}
			else {
				Files.createDirectories(Paths.get(jarDir + saved_image_path_M));
				Files.createDirectories(Paths.get(jarDir + saved_audio_path_M));
			
				printfiles("thankyou1.wav", jarDir + saved_audio_path_M + "/" + "thankyou1.wav");
				printfiles("thankyou2.wav", jarDir + saved_audio_path_M + "/" + "thankyou2.wav");
				printfiles("thankyou3.wav", jarDir + saved_audio_path_M + "/" + "thankyou3.wav");
				printfiles("thankyou4.wav", jarDir + saved_audio_path_M + "/" + "thankyou4.wav");
				printfiles("thankyou5.wav", jarDir + saved_audio_path_M + "/" + "thankyou5.wav");
				printfiles("thankyou6.wav", jarDir + saved_audio_path_M + "/" + "thankyou6.wav");
				printfiles("thankyou7.wav", jarDir + saved_audio_path_M + "/" + "thankyou7.wav");
				printfiles("bye.wav", jarDir + saved_audio_path_M + "/" + "bye.wav");
				printfiles("happy.wav", jarDir + saved_audio_path_M + "/" + "happy.wav");
				printfiles("hello.wav", jarDir + saved_audio_path_M + "/" + "hello.wav");
				printfiles("no.wav", jarDir + saved_audio_path_M + "/" + "no.wav");
				printfiles("yes.wav", jarDir + saved_audio_path_M + "/" + "yes.wav");
				
				printfiles("Happy.jpg", jarDir + saved_image_path_M + "/" + "Happy.jpg");
				printfiles("Sad.jpg", jarDir + saved_image_path_M + "/" + "Sad.jpg");
				printfiles("Perplexed.jpg", jarDir + saved_image_path_M + "/" + "Perplexed.jpg");
				printfiles("Angry.jpg", jarDir + saved_image_path_M + "/" + "Angry.jpg");
				
				
				
				File[] directoryListing = jarFile.listFiles();
				if (directoryListing != null) {
				    for (File child : directoryListing) {
				    	if (child.toString().contains(".jpg"))
				    		printfiles(child.toString(), jarDir + saved_image_path_M + "/" + child);
				    	else if (child.toString().contains(".wav"))
				    		printfiles(child.toString(), jarDir + saved_audio_path_M + "/" + child);
						
				    	}
				    }
						
				
		}
	} catch (IOException e) {
		e.printStackTrace();
	}

	}
		
	
	public static void printfiles(String resourcePath, String file_location) {
	    try {
	        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
	        if (in == null) {
		        System.out.println("input is null");
	            return;
	        }
	        File tempFile = new File(file_location);
        	tempFile.createNewFile();

	        try (FileOutputStream out = new FileOutputStream(tempFile)) {
	            //copy stream
	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = in.read(buffer)) != -1) {
	                out.write(buffer, 0, bytesRead);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("exception");
	        return;
	    }
	}
	
	
}