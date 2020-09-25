package brainbattle.thread;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.InputStream;

public class BgmControlThread extends Thread {
    private Player intro; //인트로 음악을 틀어줄 변수 , 외부에서 끄기위해 멤버변수로 설정하였다.

    public BgmControlThread() {
        InputStream musicPath = this.getClass()
            .getClassLoader()
                .getResourceAsStream("../../resources/main/static/bgm/Intro.mp3"); //URL로 하면 오류가 나기에 InputStream에 먼저 처리해 주었다
        try {
            intro = new Player(musicPath); //intro초기화
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) { //프로그램이 꺼지기 전까지 계속하여 음악을 반복 재생한다.
                InputStream musicPath = this.getClass().getClassLoader().getResourceAsStream("../../resources/main/static/bgm/GameProgress.mp3");
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
    public static void playSoundEffect(String musicName) {
        InputStream musicPath = BgmControlThread.class.getClassLoader().getResourceAsStream(musicName);
        try {
            new Player(musicPath).play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}