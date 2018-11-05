package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import gui.MainFrame;

public class ProgressGameThread extends Thread{
	private boolean myReady = false;
	private boolean yourReady = false;
	private MainFrame mainFrame;
	private Socket socket;
	private final long oneSecond = 1000;
	
	public ProgressGameThread(MainFrame mainFrame, Socket socket) {
		this.mainFrame = mainFrame;
		this.socket = socket;
	}
	@Override
	public void run() {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
//			if(myReady && yourReady) {
//				System.out.println("준비완료");
//			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}
	
	
}
