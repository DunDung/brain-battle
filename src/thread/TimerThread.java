package thread;

import javax.swing.ImageIcon;

import gui.MainFrame;

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
			"T4.png", "T3.png", "T2.png", "T1.png"};
	private static boolean timerStop = false;
	private boolean timerEnd = false;
	
	public TimerThread(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void run() {
		try {
			while(!timerEnd) {
			for(int i=0; i<=timer.length; i++) {
				if(timerStop) 
					break;
				if(i==timer.length) {
					mainFrame.getGame().setTurnEnd(true);
					break;
				}
				mainFrame.getGame().getTimer().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(timer[i])));
				Thread.sleep(1000);
			}
			setTimerStop(false);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	public static void setTimerStop(boolean stop) {
		TimerThread.timerStop = stop;
	}
	
	public void killTimer() {
		this.timerEnd = true;
		setTimerStop(true);
	}
}

