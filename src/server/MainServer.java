package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
	public static void main(String [] args) throws IOException {
		ServerSocket serverSocket = null; 
		try {
			serverSocket = new ServerSocket(5001);
			Socket clientSocket = serverSocket.accept(); 
			
			ReceiveThread re_thread = new ReceiveThread();
			re_thread.setSocket(clientSocket);
			
			SendThread se_thread = new SendThread();
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
