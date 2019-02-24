import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import junit.framework.Test;

public class MainConfiguration {

	public static void main(String[] args) {
		String homeDirectory = System.getProperty("user.dir");
		String decodedPath = "";
		String saved_image_path_W = "\\Talk_Box_Data\\Images"; // windows
		String saved_image_path_M = "/Talk_Box_Data/Images";	// mac/ linux/ unix
		String saved_audio_path_W = "\\Talk_Box_Data\\Sound"; // windows
		String saved_audio_path_M = "/Talk_Box_Data/Sound";	// mac/ linux/ unix
		String serialized_data_folder_W = "\\Talk_Box_Data\\Serialized_Data";
		String serialized_data_folder_M = "/Talk_Box_Data/Serialized_Data";


		//input stream example
		
		String path = Test.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		try {
			decodedPath = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
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
				Files.createDirectories(Paths.get(jarDir + serialized_data_folder_W));
			}
			else {
				Files.createDirectories(Paths.get(jarDir + saved_image_path_M));
				Files.createDirectories(Paths.get(jarDir + saved_audio_path_M));
				Files.createDirectories(Paths.get(jarDir + serialized_data_folder_M));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ConfigurationListener conf = new ConfigurationListener();
		conf.confFrame.setVisible(true);
		conf.confFrame.pack();
		
		
		printfiles("bye.wav", jarDir + saved_image_path_W + "/bye.wav");
		
		
		
		
		
		
		
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
