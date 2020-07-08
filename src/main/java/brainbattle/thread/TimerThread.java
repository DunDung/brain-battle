package brainbattle.thread;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;

import gui.MainFrame;
import javazoom.jl.decoder.JavaLayerException;
import userState.UserState;

public class TimerThread extends Thread{
	private MainFrame mainFrame;
	private String [] timer = {"T60.png", "T59.png", "T58.png", "T57.png", "T56.png","T55.png", "T54.png",
			"T53.png", "T52.png", "T51.png", "T50.png", "T49.png", "T48.png", "T47.png",
			"T46.png", "T45.png", "T44.png", "T43.png", "T42.png", "T41.png", "T40.png", 
			"T39.png", "T38.png", "T37.png", "T36.png", "T35.png", "T34.png", "T33.png",
			"T32.png", "T31.png", "T30.png", "T29.png", "T28.png", "T27.png", "T26.png",
			"T25.png", "T24.png", "T23.png", "T22.png", "T21.png", "T20.png", "T19.png",
			"T18.png", "T17.png", "T16.png", "T15.png", "T14.png", "T13.png", "T12.png",
			"T11.png", "T10.png", "T9.png", "T8.png", "T7.png", "T6.png", "T5.png",
			"T4.png", "T3.png", "T2.png", "T1.png", "T0.png"}; //시간을 보여줄 이미지 이름 배열
	private static boolean timerStop = false; //타이머를 멈출지 알려주는 변수
	private boolean timerEnd = false; //타이머쓰레드를 죽일지 말지 결정할 변수
	private boolean hint = false; //30초가 지나 힌트를 보여줄지 알려줄 변수

	public TimerThread(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void run() {
		try {
			while(!timerEnd) { //다른쓰레드가 게임이 종료되어 타이머를 죽이지 않으면 계속 반복 실행
				for(int i=0; i<timer.length; i++) { //60초
					if(timerStop) //문제를 누군가 맞추어 timerStop을 true로 초기화 했을 시
						break; //for문 빠져나간다.
					if(i == 31) { //30초가 지났을 시
						hint = true; //hint변수를 초기화해서 게임쓰레드에선 힌트 이미지를 보여준다.
					}
					if(i<51&&i>0) //10초 남았을 땐 음악으로 1초씩 쉬므로 10초가 남지않으면 1초씩 sleep한다
						Thread.sleep(1000);
					
					if(i >= 51) { //10초가 남았을 땐 효과음을 킨다
						try {
							soundOn();
						} catch (FileNotFoundException | JavaLayerException | URISyntaxException e) {
							e.printStackTrace();
						}
					}
					mainFrame.getGame().getTimer().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(timer[i]))); //이미지를 계속해서 바꿔주며 시간을 표시한다.
				}
				UserState.setTurnEnd(true); //게임쓰레드에서 이번 턴이 끝났다는 걸 알 수 있도록 turnEnd를 true로 초기화.
				setTimerStop(false); //timerStop으로 for문을 끝냈을 경우가 있으므로 for문이 끝난 뒤에 항상 false로 바꿔준다.
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	//멤버변수들의 getter, setter
	public static void setTimerStop(boolean stop) {
		TimerThread.timerStop = stop;
	}
	public boolean isHint() {
		return hint;
	}
	public void setHint(boolean hint) {
		this.hint = hint;
	}
	public void killTimer() { //timerThread를 외부에서 죽이기 위한 메소드
		this.timerEnd = true;
		setTimerStop(true);
	}
	public void soundOn() throws FileNotFoundException, InterruptedException, JavaLayerException, URISyntaxException { //효과음 메소드
		BgmControlThread.playSoundEffect("10second2.mp3");
	}
}

