package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			mainFrame.getGame().getWaitGoalScore().setVisible(true);

			mainFrame.getGame().setGoalScore(Integer.parseInt(buf.readLine()));
			mainFrame.getGame().getWaitGoalScore().setVisible(false);


		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}