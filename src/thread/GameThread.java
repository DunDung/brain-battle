package thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;

import gui.MainFrame;
import javazoom.jl.decoder.JavaLayerException;
import question.Question;

public class GameThread extends Thread {
	private MainFrame mainFrame; //메인프레임
	private Socket socket; //연결된 소켓
	PrintWriter writer; //상대 ReceiveThread에 보낼 변수
	private String[] questionArray; //문제목록 배열
	private int questionIndex = 0; //문제목록 배열의 현재 인덱스를 나타낼 변수
	private TimerThread timer; //타이머 변수
	Question catchNull = new Question(); //힌트이미지로 바꿀 때 null포인트 에러 방지	

	public GameThread(MainFrame mainFrame, Socket socket, String [] questionArray)  {
		this.mainFrame = mainFrame;
		this.socket = socket;
		this.questionArray = questionArray;
		this.timer = new TimerThread(mainFrame);
	}

	@Override
	public void run() {
		try {
			writer = new PrintWriter(socket.getOutputStream(),true); //writer을 소켓의 outPutStream으로 초기화
			mainFrame.getGame().getEnter().addActionListener(new AnswerSendEvent()); //게임 패널의 답안을 전송할 버튼에 엑션 리스너 추가
			mainFrame.getGame().getTf().addActionListener(new AnswerSendEvent()); //텍스트필드에서 엔터를 치는 것으로도 전송할 수 있게 액션 리스너 추가
			mainFrame.getGame().getQuiz().setVisible(true); //게임패널의 quiz라벨을 보여준다. StratCount3.png로 setIcon되어있다. 3을 보여준다
			Thread.sleep(1000); //1초 쉬고
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount2.png")));//2를 보여준다
			Thread.sleep(1000);//1초쉬고
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount1.png"))); //1을 보여준다
			Thread.sleep(1000); //1초쉬고
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("GameStart.png"))); //게임이 시작한다는 걸 알리는 이미지로 바꾼다.
			Thread.sleep(1000); //1초쉬고
			mainFrame.getGame().getTimer().setVisible(true); //게임패널의 타이머 라벨을 보여주고
			timer.start(); //타이머 쓰레드를 시작 시킨다.
			
			while(true) { //게임 진행
				if(mainFrame.getGame().getOtherCorrect()|| mainFrame.getGame().getIAmCorrect()) { //상대방이 맞췄을 때, 내가 낸 답이 정답일 때 -> 턴 종료
					questionIndex++; //퀴즈 인덱스 +1
					timer.setHint(false); //저번 턴에 Hint를 보여줬을 수 있으므로 false로 설정
					if(mainFrame.getGame().getOtherCorrect()) { //만약 상대방이 맞췄을 시
						mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("OtherCorrect.png"))); //상대방이 맞췄다는 이미지로 바꾼다
						mainFrame.getGame().setOtherCorrect(false); //ReceiveThread에서 바꾼 게임 패널의 OhterCorrect를 false로 다시 초기화
						BgmControlThread.playSoundEffect("OtherCorrect.mp3"); //Thread.sleep대신 1초짜리 효과음을 넣었다. 효과음이 재생되는동안 정지된다.
					}
					else { //내가 맞췄을 시
						mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("AnswerOk.png"))); //맞췄다는 이미지로 바꾼 후
						mainFrame.getGame().setIAmCorrect(false); //ReceiveThread에서 바꾼 게임 패널의 IAmCorrect를 false로 다시 초기화
						BgmControlThread.playSoundEffect("AnswerOk.mp3"); //효과음 재생
					}
				}
				
				if(mainFrame.getGame().getWrong()) { //내가 낸 답이 정답이 아닐 때
					mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("AnswerWrong.png"))); //틀렸다는 이미지를 보여준다.
					BgmControlThread.playSoundEffect("Wrong.mp3"); //효과음 재생
					mainFrame.getGame().setWrong(false); //ReceiveThread에서 바꾼 게임 패널의 wrong을 false로 다시 초기화
				}

				if(mainFrame.getGame().getTurnEnd()) { //시간 종료 , 턴 종료
					questionIndex++;//퀴즈인덱스 +1
					timer.setHint(false); //저번 턴에 Hint를 보여줬을 수 있으므로 false로 설정
					mainFrame.getGame().setTurnEnd(false); //ReceiveThread에서 턴이 끝난 것을 알리는 게임패널의 turnEnd를 다시 false로 초기화
				}
				//내가 이기거나, 상대방이 이겼을 때
				if(mainFrame.getScore().getMyScore() == mainFrame.getGame().getGoalScore() || mainFrame.getScore().getYourScore() == mainFrame.getGame().getGoalScore()) { 
					timer.killTimer(); //타이머 쓰레드를 죽인다.
					mainFrame.getGame().setYesAndNo();//게임패널의 setYesAndNo메소드 실행
					mainFrame.getGame().getYes().addActionListener(new YesOrNoEvent()); //Yes버튼에 액션리스너 추가
					mainFrame.getGame().getNo().addActionListener(new YesOrNoEvent()); //No버튼에 액션리스너 추가
					if(mainFrame.getScore().getYourScore() == mainFrame.getGame().getGoalScore()){ //상대방이 이겼을 시
						mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("YouLose.png"))); //졌다는 이미지로 바꾼 후
						BgmControlThread.playSoundEffect("Lose.mp3"); //효과음 재생
					}else { //내가 이겼을 시
						mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("YouWin.png"))); //이겼다는 이미지로 바꾼 후
						BgmControlThread.playSoundEffect("Win.mp3"); //효과음 재생
					}
					break;
				}
				if(timer.getHint()) //timerThread에서 30초가 지나서 hint변수가 true가 되면
					//그 문제의 문제와 힌트까지 보여주는 이미지로 바꾼다.
					mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(Question.getQuestionHintMap().get(questionArray[questionIndex]))));

				if(!timer.getHint()) //30초 전일 시, hint ==false
					//퀴즈목록배열의 인덱스의 퀴즈 이미지를 보여준다.
					mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(questionArray[questionIndex])));
			}
		} catch (IOException | InterruptedException | JavaLayerException | URISyntaxException e) {
			e.printStackTrace();
		} 
	}
	class AnswerSendEvent implements ActionListener{ //enter키를 누를 때와 "전송"버튼을 눌렀을 때의 이벤트 클래스
		@Override
		public void actionPerformed(ActionEvent e) { 
			String 	answer =mainFrame.getGame().getTf().getText().toString().trim(); //택스트 필드에 있는 문자열을 String변수 answer에 저장한다.
			if(answer.length()!=0) //백지의 답이 아닐 때
				writer.println("answer/" + questionArray[questionIndex] + "/" + answer); //키워드와 문제이름,내가쓴 답을 보낸다. 
			mainFrame.getGame().getTf().setText(""); //텍스트 필드를 다시 아무것도 없는 상태로 만든다.
			writer.flush(); //버퍼링되어 아직 기록되지 않은 데이터를 출력 스트림에 모두 출력한다.			}
		}
	}
	class YesOrNoEvent implements ActionListener{ //게임종료시 Yes나No버튼을 눌렀을 시 이벤트
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(mainFrame.getGame().getYes())){ //Yes버튼을 눌렀을 경우
				mainFrame.newGame(); //메인프레임의 newGame메소드 실행하여 game패널 재 생성
				mainFrame.getScore().scoreReset(); //메인프레임의 스코어패널의 scoreReset()메소드를 실행하여 score판 촉화
				PreSettingThread preSetThread = new PreSettingThread(mainFrame, socket); //사전준비Thread를 다시 실행
				preSetThread.start();
			}
			else //No버튼 눌렀을 경우
				System.exit(0); //프로그램 종료 
		}
	}
}

