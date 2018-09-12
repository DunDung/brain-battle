package client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import gui.MainFrame;
public class MainServer2 {
	public static MainFrame m_frame;
	public static void main(String [] args) {
		try {
			Socket clientSocket =new Socket("192.168.43.190",8999); 
			
			ReceiveThread re_thread = new ReceiveThread();
			re_thread.setSocket(clientSocket);
			
			SendThread se_thread = new SendThread();
			se_thread.setSocket(clientSocket);
			se_thread.setJFrame(m_frame);
			re_thread.setJFrame(m_frame);
			re_thread.start();
			se_thread.start();
			m_frame = new MainFrame();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
