package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import gui.IntroFrame;
import gui.MainFrame;
import thread.ChatSendThread;
import thread.GameThread;
import thread.NickNameThread;
import thread.ReadyThread;
import thread.ReceiveThread;

public class Server {

	public static MainFrame mainFrame; //MainFrmae 
	public static IntroFrame introFrame; //introFrmae

	public static void main(String [] args) throws IOException, InterruptedException {
		mainFrame = new MainFrame(); //mainFrmae 객체 생성
		introFrame = new IntroFrame(mainFrame); //introFrame 객체 생성
		introFrame.getIpField().setText("IP입력 하지마세요.");

		ServerSocket serverSocket = null; 
		Socket clientSocket  =null;
	//	Client.ip = InetAddress.getLocalHost().getHostAddress(); //클라이언트의 ip를 서버pc의 ip로 초기화
		try {

			serverSocket = new ServerSocket(9876); //서버소켓을 포트번호로 받아서 초기화 한다.
			clientSocket = serverSocket.accept();  //클라이언트소켓은 서버소켓에 접근한 소켓으로 초기화한다.
			ChatSendThread chatThread = new ChatSendThread(mainFrame, clientSocket);
			ReceiveThread reThread = new ReceiveThread(mainFrame, clientSocket);
			NickNameThread  nickThread = new NickNameThread(mainFrame, clientSocket);
			ReadyThread readyThread = new ReadyThread(mainFrame, clientSocket);
			GameThread gameThread = new GameThread(mainFrame, clientSocket);
			GoalScoreThread setScoreThread = new GoalScoreThread(mainFrame, clientSocket);

			nickThread.start();
			nickThread.join();			

			readyThread.start();
			readyThread.join();
			
			setScoreThread.start();
			setScoreThread.join();
			
			gameThread.start();
			chatThread.start();
			reThread.start();
		
			
		}catch(IOException e){
			e.printStackTrace();
		}


	}


}
