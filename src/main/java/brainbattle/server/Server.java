package brainbattle.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;

import gui.IntroFrame;
import gui.MainFrame;
import thread.PreSettingThread;
import thread.ReceiveThread;

public class Server {

	public static MainFrame mainFrame; 
	public static IntroFrame introFrame;
	
	public static void main(String [] args) throws FileNotFoundException, URISyntaxException  {
		mainFrame = new MainFrame(); //MainFrame 객체를 생성한다. setVisible(false)이기 때문에 보이지 않는다.
		introFrame = new IntroFrame(mainFrame); //사용자로부터 ip와 닉네임을 입력받기 위한 Frame
		
		introFrame.getIpField().setText(" 서버는 ip가 필요 없습니다!"); 
		

		ServerSocket serverSocket = null; 
		Socket clientSocket  =null;
		try {

			serverSocket = new ServerSocket(9999); //서버소켓을 포트번호로 받아서 초기화 한다.
			clientSocket = serverSocket.accept();  //클라이언트소켓은 서버소켓에 접근한 소켓으로 초기화한다.
			ReceiveThread reThread = new ReceiveThread(mainFrame, clientSocket); // 상대방으로 부터 받은 메세지를 처리할 쓰레드
			PreSettingThread preSetThread = new PreSettingThread(mainFrame, clientSocket); //사전 설정을 하고 프로그램을 연속적으로 이어갈 쓰레드

			preSetThread.start();
			reThread.start();
			
		}catch(Exception e) {
			System.out.println(e);
			mainFrame.getChat().getTa().append("System :상대방과의 연결이 끊어졌습니다.\n");
		}
	}
}
