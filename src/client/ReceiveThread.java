package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveThread extends Thread{
	private Socket socket;

	@Override
	public void run() {
		super.run();

		try {
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String receiveString;

			while(true) {
				receiveString = buf.readLine();

				System.out.println("»ó´ë¹æ : "+receiveString);
			}
		}catch(IOException e) {
		}
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}


}
