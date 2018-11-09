package thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import gui.MainFrame;

public class GameThread extends Thread {
	private MainFrame mainFrame;
	private Socket socket;

	public GameThread(MainFrame mainFrame, Socket socket) {
		this.mainFrame = mainFrame;
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);

			mainFrame.getGame().getQuiz().setVisible(true);
			Thread.sleep(3000);
			mainFrame.getGame().getQuiz().setVisible(false);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
