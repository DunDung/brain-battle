package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.IntroFrame;
import gui.MainFrame;

public class MainServer2 {
	public static MainFrame mainFrame;
	public static IntroFrame introFrame;
	public static String ip;
	public static BufferedReader reader;
	public static PrintWriter writer;
	public static void main(String [] args) throws UnknownHostException {
		mainFrame = new MainFrame(); //MainFrame 객체를 생성한다. setVisible(false)이기 때문에 보이지 않는다.
		introFrame = new IntroFrame(mainFrame); //introFrame 객체를 생성하여 사용자로부터 ip와 닉네임을 입력 받는다.

		try {
				Socket clientSocket = new Socket(ip,9876);//사용자로부터 입력받은 ip로 서버에서 지정한 포트로 접속한다.
				reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				writer = new PrintWriter(clientSocket.getOutputStream(),true);
				
				
//				SendThread se_thread = new SendThread(mainFrame);
//				se_thread.setSocket(clientSocket);
//				
//				ReceiveThread re_thread = new ReceiveThread(mainFrame, se_thread);
//				re_thread.setSocket(clientSocket);
//				
//				se_thread.start();
//				re_thread.start();
				
				
			
		}catch(IOException e){
			e.printStackTrace();
		}

		
	}
	public static String data(String str) throws IOException {
		writer.println(str);
		return reader.readLine();
	}


}
