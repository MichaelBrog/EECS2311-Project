package main.java.TalkBox;

import java.io.IOException;

public class TalkBoxMain {

	public static void main (String[] args) throws IOException {
		FileLoader.loadfiles();
		TalkBoxListener t = new TalkBoxListener();
	}
}
	