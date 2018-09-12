package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import gui.MainFrame;

public class MainServer {
	public static ReceiveThread re_thread;
	public static SendThread se_thread;
	public static MainFrame m_frame;
	public static void main(String [] args) throws IOException {
		
		ServerSocket serverSocket = null; 
		Socket clientSocket  =null;
		try {
			serverSocket = new ServerSocket(8999);
			clientSocket = serverSocket.accept(); 
			se_thread = new SendThread();
			re_thread = new ReceiveThread();
			
			re_thread.setSocket(clientSocket);
			se_thread.setSocket(clientSocket);
			se_thread.setJFrame(m_frame);
			re_thread.setJFrame(m_frame);
			
			re_thread.start();
			se_thread.start();
			m_frame = new MainFrame();
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			serverSocket.close();
		}
	}

}
