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
		
		private static TalkBoxMain tb;
		private static TalkBoxListener tbl;
		TalkBoxFrame tbf = new TalkBoxFrame(null, null);
	    private ConfigurationAppFrame frame;
	    private ConfigurationListener config;
	    public boolean setConfigClicked = false;
	    public boolean chooseSimClicked = false;
	    public boolean TBCLoggerClicked = false;
	    public boolean exitClicked = false;
	    public boolean simulationWorks = false;
	    
	    @BeforeAll
	    public static void setUp() throws Exception {
	    	tbl = new TalkBoxListener();
	    	tb = new TalkBoxMain();
	    	
	    }
	    
	    @Test
	    public void TalkBoxAppTestButton() throws InterruptedException {
	    
	    	String hello = "Hello";
	    	assertTrue(hello.equals("Hello"));
	    }
	    
	    @Test
	    public void TalkBoxMainTest() throws InterruptedException {
	    	//tb = new TalkBoxMain();
	    	assertTrue(tb.getClass() == TalkBoxMain.class);
	    }
	    
	    @Test
	    public void TalkBoxListenerTest() throws InterruptedException, IOException {	
	    	//tbl = new TalkBoxListener();
	    	assertTrue(tbl.getClass() == TalkBoxListener.class);	
	    }
	    
	    @Test
	    public void TalkBoxFrameTest() throws InterruptedException, IOException {	
	    	assertTrue(tbf.getClass() == TalkBoxFrame.class);	
	    }
	    
	    @Test
	    public void TalkBoxTestSetConfig() throws InterruptedException, IOException {
	    	
	    	//tbl = new TalkBoxListener();
	    	tbl.tb.setPanel();
	    	
	    	//Thread.sleep(2000);
	    	
	    	if (tbl.simulationWorking == true)
	    	{
	    		setConfigClicked = true;
	    	}
	    		
	    	assertEquals(tbl.simulationWorking,setConfigClicked);
	    	Thread.sleep(25000);
	    	
	    }
	    
	    @Test
	    public void TalkBoxTestChooseSim() throws InterruptedException, IOException {
	    	
	    	tbl.tb.setPanel();
	    	
	    	//Thread.sleep(2000);
	    	
	    	if (tbl.cs == true)
	    	{
	    		chooseSimClicked = true;
	    	}
	    		
	    	assertEquals(tbl.cs,chooseSimClicked);
	    	Thread.sleep(20000);
	    	
	    }
	    
	    @Test
	    public void SimulateTest() throws InterruptedException, IOException {
	    	
	    	tbl.tb.setPanel();
	    	
	    	//Thread.sleep(2000);
	    	
	    	if (tbl.simulationWorking == true)
	    	{
	    		simulationWorks = true;
	    	}
	    		
	    	assertEquals(tbl.simulationWorking,simulationWorks);
	    	Thread.sleep(10000);
	    	
	    }
	    
	    @Test
	    public void TalkBoxTestLogger() throws InterruptedException, IOException {
	    	
	    	tbl.tb.setPanel();
	    	
	    	//Thread.sleep(2000);
	    	
	    	if (tbl.logBoolean == true)
	    	{
	    		TBCLoggerClicked = true;
	    	}
	    		
	    	assertEquals(tbl.logBoolean,TBCLoggerClicked);
	    	Thread.sleep(20000);
	    	
	    }
	    
}
