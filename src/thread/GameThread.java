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
	PrintWriter writer;
	private String[] questionArray;
	private int questionIndex = 0;

	public GameThread(MainFrame mainFrame, Socket socket, String [] questionArray)  {
		this.mainFrame = mainFrame;
		this.socket = socket;
		this.questionArray = questionArray;
	}

	@Override
	public void run() {
		try {
			writer = new PrintWriter(socket.getOutputStream(),true);
			mainFrame.getGame().getEnter().addActionListener(new AnswerSendEvent());
			mainFrame.getGame().getTf().addActionListener(new AnswerSendEvent());


			mainFrame.getGame().getQuiz().setVisible(true);
			Thread.sleep(3500);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount3.png")));
			Thread.sleep(1000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount2.png")));
			Thread.sleep(1000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount1.png")));
			Thread.sleep(1000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("GameStart.png")));
			Thread.sleep(1000);
			while(true) {
				if(mainFrame.getScore().getMyScore() == mainFrame.getGame().getGoalScore()) {
					mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("GameOver.png")));
					break;
				}
				if(mainFrame.getScore().getYourScore() == mainFrame.getGame().getGoalScore()) {
					mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("GameOver.png")));
					break;
				}
				mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(questionArray[questionIndex])));
				if(mainFrame.getGame().getTurnEnd()) {
					questionIndex++;
					mainFrame.getGame().setTurnEnd(false);
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	class AnswerSendEvent implements ActionListener{ //enter키를 누를 때와 "전송"버튼을 눌렀을 때의 이벤트 클래스
		@Override
		public void actionPerformed(ActionEvent e) { 
			String 	answer =mainFrame.getGame().getTf().getText().toString().trim(); //택스트 필드에 있는 문자열을 sendString에 저장한다.
			System.out.println("answer/" + questionArray[questionIndex] + "/" + answer);
			if(answer.length()!=0)
				writer.println("answer/" + questionArray[questionIndex] + "/" + answer); //소켓의 아웃풋스트림에 sendString을 보낸다.
			mainFrame.getGame().getTf().setText(""); //텍스트 필드를 다시 아무것도 없는 상태로 만든다.
			writer.flush(); //버퍼링되어 아직 기록되지 않은 데이터를 출력 스트림에 모두 출력한다.			}
		}
	}
}

