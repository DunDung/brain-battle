package thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;

import gui.MainFrame;
import question.Question;

public class GameThread extends Thread {
	private MainFrame mainFrame;
	private Socket socket;
	private Question question = new Question();
	PrintWriter writer;
	public GameThread(MainFrame mainFrame, Socket socket) {
		this.mainFrame = mainFrame;
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			writer = new PrintWriter(socket.getOutputStream(),true);
		
			mainFrame.getGame().getQuiz().setVisible(true);
			Thread.sleep(5000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount3.png")));
			Thread.sleep(1000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount2.png")));
			Thread.sleep(1000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount1.png")));
			Thread.sleep(1000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("GameStart.png")));
			Thread.sleep(1000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(Question.getQuestionList().get(0).toString())));
			
			mainFrame.getGame().getEnter().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) { 
					String 	sendString =mainFrame.getGame().getTf().getText(); //택스트 필드에 있는 문자열을 sendString에 저장한다.
					writer.println("answer/"+Question.getQuestionList().get(0)+"/"+sendString); //소켓의 아웃풋스트림에 sendString을 보낸다.
					mainFrame.getGame().getTf().setText(""); //텍스트 필드를 다시 아무것도 없는 상태로 만든다.
					writer.flush(); //버퍼링되어 아직 기록되지 않은 데이터를 출력 스트림에 모두 출력한다.
				}
			});
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
