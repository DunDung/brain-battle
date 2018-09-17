package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
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

		//	BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); //키보드 입력 대기/입력
			PrintWriter sendWriter = new PrintWriter(socket.getOutputStream());  //setSocket으로 초기화한 소켓의 아웃풋스트림을 저장한다.
			
			//sendString = buf.readLine(); //이 부분에 집착해서 안됐었음
		
				this.chat.tf.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JTextField t = (JTextField)e.getSource();
						String sendString =t.getText(); //택스트 필드에 있는 문자열을 sendString에 저장한다.
						sendWriter.println(sendString); //소켓의 아웃풋스트림에 sendString을 보낸다.
						chat.ta.append("나 : " + sendString +"\n");  //JTextArea에 sendString을 추가한다.
						t.setText(""); //텍스트 필드를 다시 아무것도 없는 상태로 만든다.
						sendWriter.flush(); //버퍼링되어 아직 기록되지 않은 데이터를 출력 스트림에 모두 출력한다.
						chat.setVisible(true);  //chat패널을 화면에 보여준다.
					}
				});
			

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket socket) {  //client소켓으로 초기화하는 용도.
		this.socket = socket;
	}

}