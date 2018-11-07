package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import gui.MainFrame;

public class NickNameThread extends Thread{

	private Socket socket;
	private MainFrame mainFrame;
	public NickNameThread(MainFrame mainFrame, Socket socket) {
		this.mainFrame = mainFrame;
		this.socket = socket;
	}
	@Override
	public void run() {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true) {
				Thread.sleep(50);
				if(mainFrame.getScore().getNickState()) {
					writer.println(mainFrame.getScore().getMyNickName());
					break;
				}
				else
					continue;
			}
			mainFrame.getScore().setYourNickName(buf.readLine());
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
