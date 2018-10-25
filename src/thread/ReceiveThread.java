package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import gui.MainFrame;


public class ReceiveThread extends Thread{
	private Socket socket;
	private MainFrame mainFrame; //추가

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
			//			DataInputStream dataInput = new DataInputStream(socket.getInputStream());
			String receiveString; //클라이언트가 보낸 문자열을 받아줄 변수


			while(true) {

				receiveString = buf.readLine(); //클라이언트가 보낸 문자열을  읽어서 receiveSring에 저장한다.
				mainFrame.getChat().taAdd(receiveString+"\n"); //JTestArea에 추가해준다.
			}

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
