package thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import gui.MainFrame;

public class SetGoalThread extends Thread {
	private Socket socket;
	private MainFrame mainFrame;
	private GameThread gameThread ;
	public SetGoalThread(MainFrame mainFrame, Socket socket) {
		this.mainFrame = mainFrame;
		this.socket = socket;
		this.gameThread = new GameThread(mainFrame, socket);
	}
	@Override
	public void run() {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			mainFrame.getGame().setScoreImg();
			while(true) {
				Thread.sleep(50);
				if(mainFrame.getGame().getGoalScore() != 0) {
					writer.println("goal/"+mainFrame.getGame().getGoalScore());
					gameThread.start();
					break;
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
