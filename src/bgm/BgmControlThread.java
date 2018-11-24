package bgm;

public class BgmControlThread extends Thread{
	private boolean introOn = false;
	@Override
	public void run() {
		IntroBgm intro = new IntroBgm();
		intro.start();
	}
	
	public boolean getIntroControl() {
		return introOn;
	}
	public void setIntroControl(boolean on) {
		introOn = on;
	}
}
