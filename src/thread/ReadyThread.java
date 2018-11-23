package thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import gui.MainFrame;

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
			
			mainFrame.getChat().taAdd("System :연결되었습니다! 먼저 게임설명을 읽어주세요.\nSystem :준비가 완료되면 Ready버튼을 눌러주세요.\n");
			
			while(true) {
				Thread.sleep(100);
				if(mainFrame.getGame().getPlayOk()) {
					writer.println("ready/");
					mainFrame.getChat().taAdd("System :준비완료!\n");
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
