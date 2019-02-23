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
		ConfigurationListener conf = new ConfigurationListener();
		conf.confFrame.setVisible(true);
		conf.confFrame.pack();
		
		
		//input stream example
		InputStream inputStream = MainConfiguration.class.getResourceAsStream("/hello.txt");
		String result = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
		System.out.println(result);
		
		//Files.createDirectories(Paths.get(homeDir + FilePathResource.REL_FILE_PATH));
		//create a class called filepathreasources
		
		try {
			Files.createDirectories(Paths.get("/path/to/directory"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//new File("./names.txt");

		
		
		
	}

}
