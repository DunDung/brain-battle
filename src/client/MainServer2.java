package client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer2 {
	public static MainFrame mainFrame;
	public static void main(String [] args) {
		try {
			Socket clientSocket =new Socket("1.1.1.154",8999); 
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
