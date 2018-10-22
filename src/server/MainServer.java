package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import client.MainServer2;
import gui.IntroFrame;
import gui.MainFrame;
import thread.ReceiveThread;
import thread.SendThread;

public class MainServer {

	public static MainFrame mainFrame; //MainFrmae 
	public static IntroFrame introFrame; //introFrmae
	public static BufferedReader reader;
	public static PrintWriter writer;

	public static void main(String [] args) throws IOException {
		ServerSocket serverSocket = null; 
		Socket clientSocket  =null;
		try {
			serverSocket = new ServerSocket(9876); //서버소켓을 포트번호로 받아서 초기화 한다.
			clientSocket = serverSocket.accept();  //클라이언트소켓은 서버소켓에 접근한 소켓으로 초기화한다.
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			writer = new PrintWriter(clientSocket.getOutputStream(),true);
//
//			SendThread se_thread = new SendThread(mainFrame);
//			ReceiveThread re_thread = new ReceiveThread(mainFrame, se_thread);
//			re_thread.setSocket(clientSocket);
//
//			se_thread.setSocket(clientSocket);
//			se_thread.start();
//			re_thread.start();

		}catch(IOException e){
			e.printStackTrace();
		}
		mainFrame = new MainFrame(); //mainFrmae 객체 생성
		introFrame = new IntroFrame(mainFrame); //introFrame 객체 생성
		introFrame.getIpField().setText("IP입력 하지마세요.");

		
	}
	
	public static String data(String str) throws IOException {
		writer.println(str);
		return reader.readLine();
	}
}
