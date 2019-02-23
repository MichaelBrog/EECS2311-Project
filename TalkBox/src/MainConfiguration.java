import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class MainConfiguration {

	public static void main(String[] args) {
		String homeDirectory = System.getProperty("user.dir");
		//input stream example
		InputStream inputStream = MainConfiguration.class.getResourceAsStream("/hello.txt");
		String result = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
		System.out.println(result);
		
		try {
			Files.createDirectories(Paths.get(homeDirectory + FilePathResource.REL_FILE_PATH));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//create a class called filepathreasources
		try {
			Files.createDirectories(Paths.get("/path/to/directory"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		ConfigurationListener conf = new ConfigurationListener();
		conf.confFrame.setVisible(true);
		conf.confFrame.pack();
		
		
		//input stream example

		
		//new File("./names.txt");

		
		
		
	}

}
