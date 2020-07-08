package brainbattle.thread;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class BgmControlThread extends Thread{
	private Player intro; //인트로 음악을 틀어줄 변수 , 외부에서 끄기위해 멤버변수로 설정하였다.


	public BgmControlThread() throws FileNotFoundException, JavaLayerException, URISyntaxException {
		InputStream musicPath = this.getClass().getClassLoader().getResourceAsStream("Intro.mp3"); //URL로 하면 오류가 나기에 InputStream에 먼저 처리해 주었다
		intro = new Player(musicPath); //intro초기화
	}
	@Override
	public void run() {
		try {
			while(true) { //프로그램이 꺼지기 전까지 계속하여 음악을 반복 재생한다.
				InputStream musicPath = this.getClass().getClassLoader().getResourceAsStream("GameProgress.mp3");
				Player gameProgress = new Player(musicPath);
				gameProgress.play();
			}
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}

	}
	public void closeIntro() { //인트로 음악을 꺼준다.
		intro.close();
	}
	//각종 효과음이 필요한 순간에 효과음을 틀어줄 메소드
	public static void playSoundEffect(String musicName) throws InterruptedException, JavaLayerException, FileNotFoundException, URISyntaxException {
		InputStream musicPath = BgmControlThread.class.getClassLoader().getResourceAsStream(musicName);
		Player bgm = new Player(musicPath);
		bgm.play();
	}
}