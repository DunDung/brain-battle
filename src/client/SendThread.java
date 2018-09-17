package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JTextField;


public class SendThread extends Thread {

	private Socket socket;
	private ChattingPanel chat; //추가

	public SendThread(ChattingPanel chat) { //생성자 추가
		this.chat = chat;
	}

	@Override
	public void run() {
		super.run();
		try {


			BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); //키보드 입력 대기/입력
			PrintWriter sendWriter = new PrintWriter(socket.getOutputStream()); 
			
			//sendString = buf.readLine(); //지우고싶지만 남겨두기
		
				this.chat.tf.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JTextField t = (JTextField)e.getSource();
						String sendString =t.getText();
						sendWriter.println(sendString);
						chat.ta.append("나 : "+sendString ); 
						t.setText("");
						sendWriter.flush();
						chat.setVisible(true); 
					}
				});
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}


