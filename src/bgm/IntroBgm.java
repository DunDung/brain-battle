package bgm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class IntroBgm extends Thread{
	private Player intro;

	public IntroBgm() throws FileNotFoundException, JavaLayerException, URISyntaxException {
		intro = new Player(new FileInputStream(new File(IntroBgm.class.getClassLoader().getResource("Intro.mp3").toURI())));
	}
	@Override
	public void run() {
				try {
					intro.play();
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
	}
	public Player getIntro() {
		return intro;
	}
}