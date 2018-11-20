package server;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import sun.tools.jar.Main;

public class AudioTest {
	public static void main(String[] args) throws JavaLayerException, FileNotFoundException, URISyntaxException {
		Player p = new Player(new FileInputStream(new File(AudioTest.class.getClassLoader().getResource("¸µµùµ¿.mp3").toURI())));  
		p.play();
	} 

}
