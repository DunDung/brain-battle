package client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URISyntaxException;

import gui.IntroFrame;
import gui.MainFrame;
import thread.NickNameThread;
import thread.ReadyThread;
import thread.ReceiveThread;
import thread.ChatSendThread;

public class Client {
	public static MainFrame mainFrame;
	public static IntroFrame introFrame;
	public static String ip; //사용자로부터 입력받은 ip를 저장할 변수

	public static void main(String [] args) throws FileNotFoundException, URISyntaxException  {
		mainFrame = new MainFrame(); //MainFrame 객체를 생성한다. setVisible(false)이기 때문에 보이지 않는다.
		introFrame = new IntroFrame(mainFrame); //사용자로부터 ip와 닉네임을 입력받기 위한 Frame

		try {
//			while(true) {
//				Thread.sleep(500);
//				if(mainFrame.getIp().length() != 0) { //사용자가 ip를 입력하면
//					ip = mainFrame.getIp(); //멤버변수인 ip를 초기화한다.
//					break; //while(true)탈출
//				}
//			}
			Socket clientSocket = new Socket(InetAddress.getLocalHost().getHostAddress(),9876);//사용자로부터 입력받은 ip로 서버에서 지정한 포트로 접속한다.
			ChatSendThread chatThread = new ChatSendThread(mainFrame, clientSocket); //채팅을 전송할 채팅쓰레드 생성
			ReceiveThread reThread = new ReceiveThread(mainFrame, clientSocket);
			NickNameThread nickThread = new NickNameThread(mainFrame, clientSocket);
			ReadyThread readyThread = new ReadyThread(mainFrame, clientSocket);

			nickThread.start();
			nickThread.join();

			readyThread.start();

			chatThread.start();
			reThread.start();

		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e) {
			mainFrame.getChat().taAdd("System :상대방과의 연결이 끊어졌습니다.\n");
		}


	}

}
