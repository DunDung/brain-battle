package server;

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
				if(receiveString == null)
				{
					chat.taAdd("상대방 연결이 끊겼습니다.\n");
					break;
				}
				else
					chat.taAdd("클라이언트 : "+receiveString+"\n");
			}

			buf.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
