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
			//클라이언트 소켓의 인풋스트림으로 클라이언트 소켓이 보낸 내용을 받는다.
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream())); 

			String receiveString; //클라이언트가 보낸 문자열을 받아줄 변수

			while(true) {
				receiveString = buf.readLine(); //클라이언트가 보낸 문자열을  읽어서 receiveSring에 저장한다.
				if(receiveString == null)
				{
					chat.taAdd("상대방 연결이 끊겼습니다.\n"); //실행이 안되는듯 수정요망
					break;
				}
				else
					chat.taAdd("클라이언트 : "+receiveString+"\n"); //JTestArea에 추가해준다.
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
