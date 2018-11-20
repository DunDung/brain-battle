package thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;

import gui.MainFrame;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import sun.tools.jar.Main;

public class GameThread extends Thread {
	private MainFrame mainFrame;
	private Socket socket;
	PrintWriter writer;
	private String[] questionArray;
	private int questionIndex = 0;
	private TimerThread timer;
	public GameThread(MainFrame mainFrame, Socket socket, String [] questionArray)  {
		this.mainFrame = mainFrame;
		this.socket = socket;
		this.questionArray = questionArray;
		this.timer = new TimerThread(mainFrame);
	}

	@Override
	public void run() {
		try {
			writer = new PrintWriter(socket.getOutputStream(),true);
			mainFrame.getGame().getEnter().addActionListener(new AnswerSendEvent());
			mainFrame.getGame().getTf().addActionListener(new AnswerSendEvent());
			mainFrame.getGame().getQuiz().setVisible(true);
			Thread.sleep(1000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount2.png")));
			Thread.sleep(1000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount1.png")));
			Thread.sleep(1000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("GameStart.png")));
			Thread.sleep(1000);
			mainFrame.getGame().getTimer().setVisible(true);
			timer.start();
			while(true) {
				if(mainFrame.getGame().getOtherCorrect()|| mainFrame.getGame().getIAmCorrect()) { //상대방이 맞췄을 때, 내가 낸 답이 정답일 때 -> 턴 종료
					questionIndex++;
					if(mainFrame.getGame().getOtherCorrect()) {
						mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("OtherCorrect.png")));
						mainFrame.getGame().setOtherCorrect(false);
					}
					else {
						mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("AnswerOk.png")));
						mainFrame.getGame().setIAmCorrect(false);
					}
						Thread.sleep(1000);
					
				}
				if(mainFrame.getGame().getWrong()) { //내가 낸 답이 정답이 아닐 때
					mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("AnswerWrong.png")));
					Thread.sleep(1000);
					mainFrame.getGame().setWrong(false);
				}
				
				if(mainFrame.getGame().getTurnEnd()) { //시간 종료 , 턴 종료
					Thread.sleep(1000);
					questionIndex++;
					mainFrame.getGame().setTurnEnd(false);
				}
				if(mainFrame.getScore().getMyScore() == mainFrame.getGame().getGoalScore()) { //내가 이겼을 때
					timer.killTimer();
					mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("YouWin.png")));
					mainFrame.getGame().setYesAndNo();
					mainFrame.getGame().getYes().addActionListener(new YesOrNoEvent());
					mainFrame.getGame().getNo().addActionListener(new YesOrNoEvent());
					break;

				}
				if(mainFrame.getScore().getYourScore() == mainFrame.getGame().getGoalScore()) { //상대방이 이겼을 때
					timer.killTimer();
					mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("YouLose.png")));
					mainFrame.getGame().setYesAndNo();
					mainFrame.getGame().getYes().addActionListener(new YesOrNoEvent());
					mainFrame.getGame().getNo().addActionListener(new YesOrNoEvent());
					break;
				}
				
				mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(questionArray[questionIndex])));
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
			String 	answer =mainFrame.getGame().getTf().getText().toString().trim(); //택스트 필드에 있는 문자열을 String변수 answer에 저장한다.
			if(answer.length()!=0)
				writer.println("answer/" + questionArray[questionIndex] + "/" + answer); 
			mainFrame.getGame().getTf().setText(""); //텍스트 필드를 다시 아무것도 없는 상태로 만든다.
			writer.flush(); //버퍼링되어 아직 기록되지 않은 데이터를 출력 스트림에 모두 출력한다.			}
		}
	}
	class YesOrNoEvent implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(mainFrame.getGame().getYes())){
				mainFrame.newGame();
				mainFrame.getScore().scoreReset();
				ReadyThread ready = new ReadyThread(mainFrame, socket);
				ready.start();
			}
			else
				System.exit(0);
		}
	}
}

