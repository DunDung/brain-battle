package client;

import java.io.IOException;
import java.net.Socket;

import gui.IntroFrame;
import gui.MainFrame;
import thread.ReceiveThread;
import thread.SendThread;

public class MainServer2 {
	public static void main(String [] args) {
		MainFrame mainFrame = new MainFrame(); //MainFrame 객체를 생성한다. setVisible(false)이기 때문에 보이지 않는다.
		IntroFrame intro = new IntroFrame(mainFrame); //introFrame 객체를 생성하여 사용자로부터 ip와 닉네임을 입력 받는다.
		try {
				Socket clientSocket =new Socket(mainFrame.getIp(),8999);//사용자로부터 입력받은 ip로 서버에서 지정한 포트로 접속한다.
				ReceiveThread re_thread = new ReceiveThread(mainFrame);
				re_thread.setSocket(clientSocket);
				SendThread se_thread = new SendThread(mainFrame);
				se_thread.setSocket(clientSocket);
				re_thread.start();
				se_thread.start();
			
			
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
