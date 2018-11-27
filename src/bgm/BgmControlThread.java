package bgm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class BgmControlThread extends Thread{
	private Player intro;


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
	public static void answerOk() throws InterruptedException, JavaLayerException, FileNotFoundException, URISyntaxException {
		Player answer = new Player(new FileInputStream(new File(BgmControlThread.class.getClassLoader().getResource("AnswerOk.mp3").toURI()))); 
		answer.play();
	}
	public static void wrong() throws InterruptedException, JavaLayerException, FileNotFoundException, URISyntaxException {
		Player wrong = new Player(new FileInputStream(new File(BgmControlThread.class.getClassLoader().getResource("Wrong.mp3").toURI()))); 
		wrong.play();
	}
	public static void otherCorrect() throws InterruptedException, JavaLayerException, FileNotFoundException, URISyntaxException {
		Player otherCorrect = new Player(new FileInputStream(new File(BgmControlThread.class.getClassLoader().getResource("OtherCorrect.mp3").toURI()))); 
		otherCorrect.play();
	}
	public static void win() throws InterruptedException, JavaLayerException, FileNotFoundException, URISyntaxException {
		Player win = new Player(new FileInputStream(new File(BgmControlThread.class.getClassLoader().getResource("Win.mp3").toURI()))); 
		win.play();
	}
	public static void lose() throws InterruptedException, JavaLayerException, FileNotFoundException, URISyntaxException {
		Player lose = new Player(new FileInputStream(new File(BgmControlThread.class.getClassLoader().getResource("Lose.mp3").toURI()))); 
		lose.play();
	}
	public static void tenSecond() throws InterruptedException, JavaLayerException, FileNotFoundException, URISyntaxException {
		Player tenSecond = new Player(new FileInputStream(new File(BgmControlThread.class.getClassLoader().getResource("10second2.mp3").toURI()))); 
		tenSecond.play();
	}


}