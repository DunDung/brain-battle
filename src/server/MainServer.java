package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import gui.MainFrame;
import thread.ReceiveThread;
import thread.SendThread;

public class MainServer {
	public static ReceiveThread re_thread; //문자열을 받아줄 쓰레드
	public static SendThread se_thread; //문자열을 보낼 쓰레드
	public static MainFrame mainFrame; //GUI
	public static void main(String [] args) throws IOException {

		ServerSocket serverSocket = null; 
		Socket clientSocket  =null;
		try {
			serverSocket = new ServerSocket(8999); //서버소켓을 포트번호로 받아서 초기화 한다.
			clientSocket = serverSocket.accept();  //클라이언트소켓은 서버소켓에 접근한 소켓으로 초기화한다.
			
			mainFrame = new MainFrame(); //메인프레임 생성
			se_thread = new SendThread(mainFrame.getChat()); //SendThread를 ChattingPanel을 파라미터로 생성한다.
			re_thread = new ReceiveThread(mainFrame.getChat());// ReceiveThread를 ChattingPanel을 파라미터로 생성한다.

			re_thread.setSocket(clientSocket); //서버소켓에 접근한 클라이언트 소켓으로 쓰레드들을 초기화
			se_thread.setSocket(clientSocket);

			re_thread.start();
			se_thread.start();
			
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			serverSocket.close(); //서버소켓을 닫는다.
		}
	}

}
