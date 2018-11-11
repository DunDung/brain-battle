package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.IntroFrame;
import gui.MainFrame;
import thread.NickNameThread;
import thread.ReadyThread;
import thread.ReceiveThread;
import thread.ChatSendThread;
import thread.GameThread;

public class Client {
	public static MainFrame mainFrame;
	public static IntroFrame introFrame;
	public static String ip;

	public static void main(String [] args) throws UnknownHostException, InterruptedException {
		mainFrame = new MainFrame(); //MainFrame 객체를 생성한다. setVisible(false)이기 때문에 보이지 않는다.
		introFrame = new IntroFrame(mainFrame); //introFrame 객체를 생성하여 사용자로부터 ip와 닉네임을 입력 받는다.

		try {
			Socket clientSocket = new Socket(InetAddress.getLocalHost().getHostAddress(),9876);//사용자로부터 입력받은 ip로 서버에서 지정한 포트로 접속한다.
			ChatSendThread chatThread = new ChatSendThread(mainFrame, clientSocket);
			ReceiveThread reThread = new ReceiveThread(mainFrame, clientSocket);
			NickNameThread nickThread = new NickNameThread(mainFrame, clientSocket);
			ReadyThread readyThread = new ReadyThread(mainFrame, clientSocket);
			GameThread gameThread = new GameThread(mainFrame, clientSocket);
			GoalScoreThread setScoreThread = new GoalScoreThread(mainFrame, clientSocket);
			
			nickThread.start();
			nickThread.join();
			
			readyThread.start();
			readyThread.join();
			
			setScoreThread.start();
			setScoreThread.join();
			
			chatThread.start();
			reThread.start();
			
			gameThread.start();
			
			

		}catch(IOException e){
			e.printStackTrace();
		}


	}

}
