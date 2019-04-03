package test.java.TalkBox;




import org.junit.jupiter.api.*;
import main.java.TalkBox.*;
import main.java.TalkBox.ConfigurationAppFrame;
import main.java.TalkBox.ConfigurationListener;
import main.java.TalkBox.SimulatorFrame;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

	public class tester {
		
		private TalkBoxMain tb;
		private TalkBoxListener tbl;
		TalkBoxFrame tbf = new TalkBoxFrame(null, null);
	    private ConfigurationAppFrame frame;
	    private ConfigurationListener config;
	    
	    @Test
	    public void TalkBoxAppTestButton() throws InterruptedException {
	    
	    	String hello = "Hello";
	    	assertTrue(hello.equals("Hello"));
	    }
	    
	    @Test
	    public void TalkBoxMainTest() throws InterruptedException {
	    	tb = new TalkBoxMain();
	    	assertTrue(tb.getClass() == TalkBoxMain.class);
	    }
	    
	    @Test
	    public void TalkBoxListenerTest() throws InterruptedException, IOException {	
	    	tbl = new TalkBoxListener();
	    	assertTrue(tbl.getClass() == TalkBoxListener.class);	
	    }
	    
	    @Test
	    public void TalkBoxFrameTest() throws InterruptedException, IOException {	
	    	assertTrue(tbf.getClass() == TalkBoxFrame.class);	
	    }
	    
	    @Test
	    public void TalkBoxTest1Button() throws InterruptedException, IOException {	
	    	Thread.sleep(100000);
	    	tbf.setPanel();
	    	//tbf.chooseProfile(i)	
	    	assertTrue(tbf.getClass() == TalkBoxFrame.class);	
	    }
	    //CHECK LOGGERS TO SEE WHAT IS HAPPENING AND ASSERT ON THEM.
	    

	    /*
	    @BeforeEach
	    public void setUp() throws Exception {
	        frame = new ConfigurationAppFrame(null, null);
	        config = new ConfigurationListener();
	        
	        config.confFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        config.confFrame.setTitle("TalkBox");
	        config.confFrame.pack();
	        config.confFrame.setVisible(true);
	    }
	    
	    */
	    
/*
	    @Test
	    public void TalkBoxAppTest1Button() throws InterruptedException {
	        Thread.sleep(1500);
	        //assertEquals(frame.text,"");
	        config.confFrame.text.setText("1");
	        assertEquals(config.confFrame.text.getText(),"1");
	        config.size = Integer.parseInt(config.confFrame.text.getText());
	        assertEquals(config.getTotalNumberOfButtons(),config.size);
	        Thread.sleep(1500);
	        config.confFrame.next.doClick();
//	        ActionEvent e = new ActionEvent(config, 0, "pressedNext");
//	        System.out.println(e.getActionCommand());
//	        config.actionPerformed(e);
	        
	        Thread.sleep(1500);
	        config.confFrame.dropDownImage.setSelectedItem("Pick Image");
	        Thread.sleep(1500);
	        assertEquals(config.confFrame.dropDownImage.getSelectedItem(),"Pick Image");
	        config.confFrame.pickImage.doClick();
	        Thread.sleep(1500);
	        File pathImage = config.confFrame.file_Image.getSelectedFile();       
			assertEquals(config.confFrame.file_Image.getSelectedFile(),pathImage);
			Thread.sleep(1500);
			config.confFrame.previewImage.doClick();
		    Thread.sleep(1500);
			
			Thread.sleep(1500);
			config.confFrame.dropDownSound.setSelectedItem("Pick Sound");
		    Thread.sleep(1500);
		    assertEquals(config.confFrame.dropDownSound.getSelectedItem(),"Pick Sound");
		    config.confFrame.pickSound.doClick();
		    Thread.sleep(1500);
	        File pathSound = config.confFrame.file_Audio.getSelectedFile();       
			assertEquals(config.confFrame.file_Audio.getSelectedFile(),pathSound);
			Thread.sleep(1500);
			config.confFrame.previewSound.doClick();
		    Thread.sleep(1500);
			
			Thread.sleep(1500);
	        config.confFrame.next.doClick();
//	        Thread.sleep(2000);
//	        config.confFrame.lastPage();
//	        Thread.sleep(2000);
//	        Thread.sleep(1500);
//	        config.confFrame.exit.doClick();
//	        boolean isExit = true;
//	        assertTrue(isExit);
	        
	        Thread.sleep(1500);
	        SimulatorFrame sf = new SimulatorFrame(config, config.size);
	        Thread.sleep(40000);
	        //sf.sim.panel.setVisible(true);
			
			
	    }
	    
	    */
	    
	  

}
