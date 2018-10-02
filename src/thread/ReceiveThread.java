package thread;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import gui.MainFrame;
import gui.ScorePanel;


public class ReceiveThread extends Thread{
	private Socket socket;
	private MainFrame mainFrame; //추가
	private ScorePanel score;

	public ReceiveThread(MainFrame mainFrame) { //생성자 추가
		this.mainFrame = mainFrame;
	}

	@Override
	public void run() {
		super.run();

		try {
			//클라이언트 소켓의 인풋스트림으로 클라이언트 소켓이 보낸 내용을 받는다.
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			DataInputStream dataInput = new DataInputStream(socket.getInputStream());
			String receiveString; //클라이언트가 보낸 문자열을 받아줄 변수
			while(true) {
				if(mainFrame.getNickName() != null) {
					String s = dataInput.readUTF();
					 mainFrame.getScore().setYourNickName(s);
					 break;
				}
			}
			
			while(true) {

				receiveString = buf.readLine(); //클라이언트가 보낸 문자열을  읽어서 receiveSring에 저장한다.
				mainFrame.getChat().taAdd(receiveString+"\n"); //JTestArea에 추가해준다.

			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket socket) { //소켓 초기화
		this.socket = socket;
	}

}
