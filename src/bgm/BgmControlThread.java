package bgm;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javazoom.jl.decoder.JavaLayerException;

public class BgmControlThread extends Thread{
	private boolean introOn = true;
	@Override
	public void run() {
		IntroBgm intro = null;
		try {
			intro = new IntroBgm();
		} catch (FileNotFoundException | JavaLayerException | URISyntaxException e) {
			e.printStackTrace();
		}
		while(true) {
			if(introOn) {
				intro.start();
			}
			if(!introOn) {
				intro.getIntro().close();
			}
		}
	}
	
	public boolean getIntroControl() {
		return introOn;
	}
	public void setIntroControl(boolean on) {
		introOn = on;
	}
}
