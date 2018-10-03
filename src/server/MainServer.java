package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import gui.IntroFrame;
import gui.MainFrame;
import thread.ReceiveThread;
import thread.SendThread;

public class MainServer {

	public static MainFrame mainFrame; //MainFrmae 
	public static IntroFrame introFrame; //introFrmae

	public static void main(String [] args) throws IOException {

		ServerSocket serverSocket = null; 
		Socket clientSocket  =null;
		mainFrame = new MainFrame(); //mainFrmae 객체 생성
		introFrame = new IntroFrame(mainFrame); //introFrame 객체 생성
		//	introFrame.getIpFiedl().setText(" 닉네임만 입력하세요."); //서버컴퓨터인 경우엔 Ip를 입력할 필요가 없으므로 introFrame의 ipField를 따로 설정

		try {
			serverSocket = new ServerSocket(9191); //서버소켓을 포트번호로 받아서 초기화 한다.
			clientSocket = serverSocket.accept();  //클라이언트소켓은 서버소켓에 접근한 소켓으로 초기화한다.

			//introFrame.setSocket(clientSocket);
			while(true) {
				if(mainFrame.getScore().getYourNickName().trim().length() <= 1) {
					DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
					String s =dataIn.readUTF();
					mainFrame.getScore().setYourNickName(s);
					break;
				}
			}
			while(true) {
				if(mainFrame.getNickName() != null) {
					DataOutputStream  dataOut = new DataOutputStream(clientSocket.getOutputStream());
					dataOut.writeUTF(mainFrame.getNickName());
					dataOut.flush();
					break;
				}
			}

			SendThread se_thread = new SendThread(mainFrame);
			ReceiveThread re_thread = new ReceiveThread(mainFrame, se_thread);
			re_thread.setSocket(clientSocket);

			se_thread.setSocket(clientSocket);

			se_thread.start();
			re_thread.start();


		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
