package main.java.TalkBox;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class LogFile {

	// ------ Fields ------
	 File log;
	 PrintWriter pw;
	 Date date;
	
	// ------ Constructors --------
	public LogFile() throws IOException {
		
		File dir = new File("imageReasource");
		dir.mkdir();
		
		if (System.getProperty("os.name").startsWith("Windows"))
		{
			File dirData = new File("imageReasource\\TalkBoxData");
			dirData.mkdir();
		}
			
		else
		{
			File dirData = new File("imageReasource/TalkBoxData");
			dirData.mkdir();
		}
			
		if (System.getProperty("os.name").startsWith("Windows"))
			log =  new File (".\\imageReasource\\log.txt"); // mac/ linux/ unix
		else
			log = new File ("./imageReasource/log.txt"); // mac/ linux/ unix
		
		if (!log.exists())
			log.createNewFile();
		
		pw = new PrintWriter(log);
	}
	
	/**
	 * The method writes to the log of the software
	 * 
	 * @param str
	 * 			the string that needs to be added to the log
	 * @throws IOException 
	 */
	public  void writeToLog (String str) throws IOException {
	
		date = new Date();
		pw.write(date.toString() + ", " + str + "\n");
	}
	
	public void stopWriting () {
		pw.close();
	}
}
