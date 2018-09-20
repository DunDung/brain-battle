package client;

import java.io.IOException;
import java.net.Socket;

import gui.IntroFrame;
import gui.MainFrame;
import thread.ReceiveThread;
import thread.SendThread;

public class MainServer2 {
	public static MainFrame mainFrame;
	public static void main(String [] args) {
		try {
			String s = "172.30.1.10"; //사용자의 ip를 받을 예정
			Socket clientSocket =new Socket(s,8999); 

			mainFrame  = new MainFrame();
			ReceiveThread re_thread = new ReceiveThread(mainFrame.getChat());
			re_thread.setSocket(clientSocket);

			SendThread se_thread = new SendThread(mainFrame.getChat());
			se_thread.setSocket(clientSocket);
			re_thread.start();
			se_thread.start();

		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
