package thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;

import gui.MainFrame;
import question.Question;

public class SetGoalThread extends Thread {
	private Socket socket;
	private MainFrame mainFrame;
	
	public SetGoalThread(MainFrame mainFrame, Socket socket) {
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
					break;.
				}
			}
			Question question = new Question();
			Collections.shuffle(question.getQuestionList());
			GameThread gameThread = new GameThread(mainFrame, socket,question.toString().split("/"));
			gameThread.start();
			System.out.println(question);
			writer.println("questionList/"+question);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
