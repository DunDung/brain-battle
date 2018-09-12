package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import gui.MainFrame;

public class ReceiveThread extends Thread{
	private Socket socket;
	private MainFrame s_Frame;

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
					s_Frame.chat.ta.append("상대방 연결이 끊겼습니다.\n");
					break;
				}
				else
					s_Frame.chat.ta.append("상대방 : "+receiveString);
			}

			buf.close();
		}catch(IOException e) {
		}
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public void setJFrame(MainFrame s_Frame) {
		this.s_Frame = s_Frame;
	}


}
