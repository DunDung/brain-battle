package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class MainServer {
	public static ReceiveThread re_thread;
	public static SendThread se_thread;
	public static MainFrame mainFrame;
	public static void main(String [] args) throws IOException {

		ServerSocket serverSocket = null; 
		Socket clientSocket  =null;
		try {
			serverSocket = new ServerSocket(8999);
			clientSocket = serverSocket.accept(); 
			
			mainFrame = new MainFrame();
			se_thread = new SendThread(mainFrame.getChat());
			re_thread = new ReceiveThread(mainFrame.getChat()); //파라미터부분 추가

			re_thread.setSocket(clientSocket);
			se_thread.setSocket(clientSocket);

			re_thread.start();
			se_thread.start();
			
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			serverSocket.close();
		}
	}

}
