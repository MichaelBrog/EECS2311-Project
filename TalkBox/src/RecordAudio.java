import javax.sound.sampled.*;
import java.io.*;
 

public class RecordAudio implements Runnable{
    // record duration, in milliseconds
    static final long RECORD_TIME = 1000;  // 0.5 minute
 
    // path of the wav file
    String homeDirectory = System.getProperty("user.dir" );
    
    File wavFileM = new File( "./imageReasource/TalkBoxData/RecordAudio.wav");	// mac / linux
    File wavFileW = new File( ".\\imageReasource\\TalkBoxData\\RecordAudio.wav");	// windows
 
    // format of audio file
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
 
    // the line from which audio data is captured
    TargetDataLine line;
 
    /**
     * Defines an audio format
     */
    AudioFormat getAudioFormat() {
        float sampleRate = 44100;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                                             channels, signed, bigEndian);
        return format;
    }
 
    /**
     * Closes the target data line to finish capturing and recording
     */
    void finish() {
        line.stop();
        line.close();
        System.out.println("Finished");
    }
 
    /**
     * Captures the sound and record into a WAV file
     */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            info.getFormats();
            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing
 
            System.out.println("Start capturing...");
 
            AudioInputStream ais = new AudioInputStream(line);
 
            System.out.println("Start recording...");
 
            // start recording
     	      if (System.getProperty("os.name").startsWith("Windows"))
     	    	 AudioSystem.write(ais, fileType, wavFileW);
     	      else
     	    	  AudioSystem.write(ais, fileType, wavFileM);
 
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
	}
	
}
