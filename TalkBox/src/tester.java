



import org.junit.jupiter.api.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFrame;

	public class tester {

	    private ConfigurationAppFrame frame;
	    private ConfigurationListener config;
	    
	    @BeforeEach
	    public void setUp() throws Exception {
	        frame = new ConfigurationAppFrame(null, null);
	        config = new ConfigurationListener();
	        
	        config.confFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        config.confFrame.setTitle("TalkBox");
	        config.confFrame.pack();
	        config.confFrame.setVisible(true);
	    }
	    

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
	    
	    @Test
	    public void TalkBoxAppTest4Buttons() throws InterruptedException {
	        Thread.sleep(1500);
	        //assertEquals(frame.text,"");
	        config.confFrame.text.setText("4");
	        assertEquals(config.confFrame.text.getText(),"4");
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
	        
	        Thread.sleep(1500);
	        config.confFrame.dropDownImage.setSelectedItem("Pick Image");
	        Thread.sleep(1500);
	        assertEquals(config.confFrame.dropDownImage.getSelectedItem(),"Pick Image");
	        config.confFrame.pickImage.doClick();
	        Thread.sleep(1500);
	        pathImage = config.confFrame.file_Image.getSelectedFile();       
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
	        pathSound = config.confFrame.file_Audio.getSelectedFile();       
			assertEquals(config.confFrame.file_Audio.getSelectedFile(),pathSound);
			Thread.sleep(1500);
			config.confFrame.previewSound.doClick();
		    Thread.sleep(1500);
			
			Thread.sleep(1500);
	        config.confFrame.next.doClick();
	        
	        Thread.sleep(1500);
	        config.confFrame.dropDownImage.setSelectedItem("Pick Image");
	        Thread.sleep(1500);
	        assertEquals(config.confFrame.dropDownImage.getSelectedItem(),"Pick Image");
	        config.confFrame.pickImage.doClick();
	        Thread.sleep(1500);
	        pathImage = config.confFrame.file_Image.getSelectedFile();       
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
	        pathSound = config.confFrame.file_Audio.getSelectedFile();       
			assertEquals(config.confFrame.file_Audio.getSelectedFile(),pathSound);
			Thread.sleep(1500);
			config.confFrame.previewSound.doClick();
		    Thread.sleep(1500);
			
			Thread.sleep(1500);
	        config.confFrame.next.doClick();
	        
	        Thread.sleep(1500);
	        config.confFrame.dropDownImage.setSelectedItem("Pick Image");
	        Thread.sleep(1500);
	        assertEquals(config.confFrame.dropDownImage.getSelectedItem(),"Pick Image");
	        config.confFrame.pickImage.doClick();
	        Thread.sleep(1500);
	        pathImage = config.confFrame.file_Image.getSelectedFile();       
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
	        pathSound = config.confFrame.file_Audio.getSelectedFile();       
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


}
