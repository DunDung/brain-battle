package thread;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URISyntaxException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class BgmControlThread extends Thread{
	private Player intro;


	public BgmControlThread() throws FileNotFoundException, JavaLayerException, URISyntaxException {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("Intro.mp3");
		intro = new Player(is);
	}
	@Override
	public void run() {
		try {
			intro.play();
			while(true) {
				InputStream is = this.getClass().getClassLoader().getResourceAsStream("GameProgress.mp3");
				Player gameProgress = new Player(is);
				gameProgress.play();
			}
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}

	}
	public void closeIntro() {
		intro.close();
	}
	public static void playSoundEffect(String musicName) throws InterruptedException, JavaLayerException, FileNotFoundException, URISyntaxException {
		InputStream is = BgmControlThread.class.getClassLoader().getResourceAsStream(musicName);
		Player bgm = new Player(is);
		bgm.play();
	}
}