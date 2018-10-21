package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import gui.IntroFrame;
import gui.MainFrame;
import thread.ReceiveThread;
import thread.SendThread;
import java.net.UnknownHostException;

public class MainServer2 {
	public static MainFrame mainFrame;
	public static IntroFrame introFrame;

	
	public static void main(String [] args) throws UnknownHostException {
		mainFrame = new MainFrame(); //MainFrame 객체를 생성한다. setVisible(false)이기 때문에 보이지 않는다.
		introFrame = new IntroFrame(mainFrame); //introFrame 객체를 생성하여 사용자로부터 ip와 닉네임을 입력 받는다.
		
		InetAddress local = InetAddress.getLocalHost();
		
		try {
				Socket clientSocket =new Socket(local.getHostAddress(),9191);//사용자로부터 입력받은 ip로 서버에서 지정한 포트로 접속한다.
				
	
				
				SendThread se_thread = new SendThread(mainFrame);
				se_thread.setSocket(clientSocket);
				
				ReceiveThread re_thread = new ReceiveThread(mainFrame, se_thread);
				re_thread.setSocket(clientSocket);
				
				DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
				dataOut.flush();
				
				se_thread.start();
				re_thread.start();
				
			
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
