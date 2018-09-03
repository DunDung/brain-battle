package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SendThread extends Thread {

	private Socket socket;

	@Override
	public void run() {
		super.run();
		try {
			BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); //키보드 입력 대기/입력
			PrintWriter sendWriter = new PrintWriter(socket.getOutputStream()); 
			String sendString;

			while(true) {
				sendString = buf.readLine();
				sendWriter.println(sendString);
				sendWriter.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}


