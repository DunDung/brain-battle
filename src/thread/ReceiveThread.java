package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import gui.MainFrame;


public class ReceiveThread extends Thread{
	private Socket socket;
	private MainFrame mainFrame; //추가
	private static boolean start = false;
	public ReceiveThread(MainFrame mainFrame, Socket socket) { //생성자 추가
		this.mainFrame = mainFrame;
		this.socket = socket;
	}

	@Override
	public void run() {
		super.run();

		try {
			//클라이언트 소켓의 인풋스트림으로 클라이언트 소켓이 보낸 내용을 받는다.
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			while(true) {
				String receiveString = buf.readLine(); //클라이언트가 보낸 문자열을  읽어서 receiveSring에 저장한다.
				String [] receiveArray = receiveString.split("/");
				
				switch(receiveArray[0]) {
				case "chat":
					mainFrame.getChat().taAdd(receiveArray[1]+"\n"); //JTestArea에 추가해준다.
//				case "ready" :
//					if(mainFrame.getGame().getPlayOk()) {
//						writer.println("go/test");
//						mainFrame.getGame().getReadyOk().setVisible(false);
//					}
//					else
//						readyThread.start();
//				case "go" :
//					mainFrame.getGame().getReadyOk().setVisible(false);
//						
				}
			}

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
