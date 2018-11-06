package thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import gui.MainFrame;


public class SendThread extends Thread {

	private Socket socket;
	private PrintWriter sendWriter;
	private MainFrame mainFrame;


	public SendThread(MainFrame mainFrame, Socket socket) { //생성자 추가
		this.mainFrame = mainFrame;
		this.socket = socket;
	}

	@Override
	public void run() {
		super.run();
		try {
			sendWriter = new PrintWriter(socket.getOutputStream());  //setSocket으로 초기화한 소켓의 아웃풋스트림을 저장한다.
			
			mainFrame.getChat().getTf().addActionListener(new SendEvent());//enter키를 누를 때 이벤트
			mainFrame.getChat().getEnter().addActionListener(new SendEvent()); //전송버튼을 누를 때 이벤트
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}


	class SendEvent implements ActionListener{ //enter키를 누를 때와 "전송"버튼을 눌렀을 때의 이벤트 클래스
		@Override
		public void actionPerformed(ActionEvent e) { 
			
			String 	sendString =mainFrame.getChat().getTf().getText(); //택스트 필드에 있는 문자열을 sendString에 저장한다.
			sendWriter.println("chat/"+mainFrame.getScore().getMyNickName() +" :"+ sendString ); //소켓의 아웃풋스트림에 sendString을 보낸다.
			mainFrame.getChat().getTa().append(mainFrame.getScore().getMyNickName() +" :"+ sendString +"\n");  //JTextArea에 sendString을 추가한다.
			mainFrame.getChat().getTf().setText(""); //텍스트 필드를 다시 아무것도 없는 상태로 만든다.
			sendWriter.flush(); //버퍼링되어 아직 기록되지 않은 데이터를 출력 스트림에 모두 출력한다.
			mainFrame.getChat().setVisible(true);  //chat패널을 화면에 보여준다.
		}
	}
}