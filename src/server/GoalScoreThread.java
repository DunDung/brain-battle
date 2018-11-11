package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import gui.MainFrame;

public class GoalScoreThread extends Thread {
	private Socket socket;
	private MainFrame mainFrame;
	public GoalScoreThread(MainFrame mainFrame, Socket socket) {
		this.mainFrame = mainFrame;
		this.socket = socket;
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
					break;
				}
				else
					continue;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
