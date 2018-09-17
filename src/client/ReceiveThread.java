package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;



public class ReceiveThread extends Thread{
	private Socket socket;
	private ChattingPanel chat; //추가
	
	public ReceiveThread(ChattingPanel chat) { //생성자 추가
		this.chat = chat;
	}

	@Override
	public void run() {
		super.run();

		try {
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String receiveString;

			while(true) {
				receiveString = buf.readLine();
				
				chat.taAdd("서버 : "+receiveString+"\n");;
			}

		}catch(IOException e) {
		}
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
