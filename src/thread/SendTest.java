package thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import gui.MainFrame;


public class SendTest extends Thread {

	private Socket socket;
	private DataOutputStream dataOut;
	private MainFrame mainFrame;


	public SendTest(MainFrame mainFrame) { //생성자 추가
		this.mainFrame = mainFrame;
	}

	@Override
	public void run() {
		super.run();
		try {
			while(true) {
				if(mainFrame.getScore().getYourNickName().equals("") == true && mainFrame.getNickName()!= null) {
					dataOut =  new DataOutputStream(socket.getOutputStream());  //setSocket으로 초기화한 소켓의 아웃풋스트림을 저장한다.
					dataOut.writeUTF(mainFrame.getNickName());
					break;
				}
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket socket) {  //client소켓으로 초기화하는 용도.
		this.socket = socket;
	}
}


