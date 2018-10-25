package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.IntroFrame;
import gui.MainFrame;
import thread.NickNameThread;
import thread.ReceiveThread;
import thread.SendThread;

public class MainServer2 {
	public static MainFrame mainFrame;
	public static IntroFrame introFrame;
	public static String ip;

	public static void main(String [] args) throws UnknownHostException, InterruptedException {
		mainFrame = new MainFrame(); //MainFrame 객체를 생성한다. setVisible(false)이기 때문에 보이지 않는다.
		introFrame = new IntroFrame(mainFrame); //introFrame 객체를 생성하여 사용자로부터 ip와 닉네임을 입력 받는다.

		try {
			//ip주소 알아내는 코드로 작성해보기 밑에
			Socket clientSocket = new Socket("172.30.1.10",9876);//사용자로부터 입력받은 ip로 서버에서 지정한 포트로 접속한다.

			SendThread se_thread = new SendThread(mainFrame, clientSocket);
			ReceiveThread re_thread = new ReceiveThread(mainFrame, clientSocket);
			NickNameThread nick_thread = new NickNameThread(mainFrame, clientSocket);
			nick_thread.start();
			nick_thread.join();

			se_thread.start();
			re_thread.start();


		}catch(IOException e){
			e.printStackTrace();
		}


	}

}
