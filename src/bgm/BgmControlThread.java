package bgm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class BgmControlThread extends Thread{
	private Player intro;
	private Player gameProgress;
	

	public BgmControlThread() throws FileNotFoundException, JavaLayerException, URISyntaxException {
		intro = new Player(new FileInputStream(new File(BgmControlThread.class.getClassLoader().getResource("Intro.mp3").toURI())));
	}
	@Override
	public void run() {
				try {
						intro.play();
						while(true) {
							Player gameProgress = new Player(new FileInputStream(new File(BgmControlThread.class.getClassLoader().getResource("GameProgress.mp3").toURI())));
							gameProgress.play();
						}
				} catch (URISyntaxException|FileNotFoundException|JavaLayerException e) {
					e.printStackTrace();
				}
				
	}
	public void closeIntro() {
		intro.close();
	}
	public void answer() throws InterruptedException, JavaLayerException, FileNotFoundException, URISyntaxException {
		Player answer = new Player(new FileInputStream(new File(BgmControlThread.class.getClassLoader().getResource("¸àºØ.mp3").toURI()))); 
		answer.play();
	}
	
}