package main.java.TalkBox;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.Comparator;
import java.util.stream.Collectors;

//import junit.framework.Test;

public class MainConfiguration {

	public static void main(String[] args) throws IOException {
		String homeDirectory = System.getProperty("user.dir");
		
		String decodedPath = "";
		String saved_image_path_W = "\\imageReasource\\imageRepository"; // windows
		String saved_image_path_M = "/imageReasource/imageRepository";	// mac/ linux/ unix
		String saved_audio_path_W = "\\imageReasource\\soundRepository"; // windows
		String saved_audio_path_M = "/imageReasource/soundRepository";	// mac/ linux/ unix
		String serialized_data_folder_W = "\\imageReasource\\TalkBoxData";
		String serialized_data_folder_M = "/imageReasource/TalkBoxData";
//		String saved_image_path_W = "..\\imageRepository"; // windows
//		String saved_image_path_M = "../imageRepository";	// mac/ linux/ unix
//		String saved_audio_path_W = "..\\soundRepository"; // windows
//		String saved_audio_path_M = "../soundRepository";	// mac/ linux/ unix
//		String serialized_data_folder_W = "..\\TalkBoxData";
//		String serialized_data_folder_M = "../TalkBoxData";
		CodeSource codeSource = MainConfiguration.class.getProtectionDomain().getCodeSource();
		File jarFile = null;
		Path index = null;
		
//		try {
//			jarFile = new File(codeSource.getLocation().toURI().getPath());
//		} catch (URISyntaxException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		String jarDir = jarFile.getParentFile().getPath();
//
//		
//		if (System.getProperty("os.name").startsWith("Windows")) 
//			index = Paths.get(jarDir + serialized_data_folder_W);
//		else
//			index = Paths.get(jarDir + serialized_data_folder_M);
//
//
//		    try {
//				Files.walk(index)
//				     .sorted(Comparator.reverseOrder())  // as the file tree is traversed depth-first and that deleted dirs have to be empty  
//				     .forEach(t -> {
//				         try {
//				             Files.delete(t);
//				         } catch (IOException e) {
//				         }
//				     });
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		    if (!Files.exists(index)) {
//		        try {
//					index = Files.createDirectories(index);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//		    }
		
		ConfigurationListener conf = new ConfigurationListener();
		conf.confFrame.setVisible(true);
		conf.confFrame.pack();
		
		
		//input stream example

		
		//new File("./names.txt");

		
		
		
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