package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;

import bgm.BgmControlThread;
import bgm.IntroBgm;
import gui.IntroFrame;
import gui.MainFrame;
import thread.ChatSendThread;
import thread.NickNameThread;
import thread.ReadyThread;
import thread.ReceiveThread;

public class Server {

	public static MainFrame mainFrame; 
	public static IntroFrame introFrame;
	private static IntroBgm introBgm = new IntroBgm();
	
	public static void main(String [] args) throws FileNotFoundException, URISyntaxException  {
		mainFrame = new MainFrame(); //MainFrame 객체를 생성한다. setVisible(false)이기 때문에 보이지 않는다.
		introFrame = new IntroFrame(mainFrame); //사용자로부터 ip와 닉네임을 입력받기 위한 Frame
		introBgm.start();
		
		introFrame.getIpField().setText(" 서버는 ip가 필요 없습니다!"); 
		

		ServerSocket serverSocket = null; 
		Socket clientSocket  =null;
		try {

			serverSocket = new ServerSocket(9876); //서버소켓을 포트번호로 받아서 초기화 한다.
			clientSocket = serverSocket.accept();  //클라이언트소켓은 서버소켓에 접근한 소켓으로 초기화한다.
			introBgm.stop();
			ChatSendThread chatThread = new ChatSendThread(mainFrame, clientSocket);
			ReceiveThread reThread = new ReceiveThread(mainFrame, clientSocket);
			NickNameThread  nickThread = new NickNameThread(mainFrame, clientSocket);
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
