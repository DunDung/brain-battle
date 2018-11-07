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
	
	public ProgressGameThread(MainFrame mainFrame, Socket socket) {
		this.mainFrame = mainFrame;
		this.socket = socket;
	}
	@Override
	public void run() {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			mainFrame.getChat().taAdd("System :준비가 완료되면 Ready버튼을 눌러주세요.\n");
			
			while(true) {
				Thread.sleep(100);
				if(mainFrame.getGame().getPlayOk()) {
					writer.println("ready");
					break;
				}
				else
					continue;
			}
			String ready = buf.readLine();
			if(ready.equals("ready")) {
				mainFrame.getGame().startGame();
				mainFrame.getChat().taAdd("System :채팅기능 활성화\n");
			}
				
			
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
