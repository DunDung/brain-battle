package bgm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class IntroBgm extends Thread{
	private static Player intro;

	@Override
	public void run() {
			try {
				intro = new Player(new FileInputStream(new File(IntroBgm.class.getClassLoader().getResource("Intro.mp3").toURI())));
				intro.play();
			} catch (FileNotFoundException | JavaLayerException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
}