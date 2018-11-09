package thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import gui.MainFrame;

public class GameThread {
	public class ReadyThread extends Thread{
		private MainFrame mainFrame;
		private Socket socket;

		public ReadyThread(MainFrame mainFrame, Socket socket) {
			this.mainFrame = mainFrame;
			this.socket = socket;
		}
		@Override
		public void run() {
			try {
				PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
				
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		}
	}
}
