package thread;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import gui.MainFrame;
import gui.ScorePanel;


public class ReceiveTest extends Thread{
	private Socket socket;
	private MainFrame mainFrame; //추가
	private ScorePanel score;
	private DataInputStream dataIn;

	public ReceiveTest(MainFrame mainFrame) { //생성자 추가
		this.mainFrame = mainFrame;
	}

	@Override
	public void run() {
		super.run();

		try {
			//클라이언트 소켓의 인풋스트림으로 클라이언트 소켓이 보낸 내용을 받는다.
			
			String s; //클라이언트가 보낸 문자열을 받아줄 변수
			
			
			while(true) {
				if(mainFrame.getScore().getYourNickName().equals("") == true && mainFrame.getNickName()!= null) {
				dataIn = new DataInputStream(socket.getInputStream());
				s = dataIn.readUTF();
				mainFrame.getScore().setYourNickName(s);
				break;
				}

			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket socket) { //소켓 초기화
		this.socket = socket;
	}

}
