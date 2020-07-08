package brainbattle.thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;

import gui.MainFrame;
import question.Question;

public class SetGoalThread extends Thread {
	private Socket socket;
	private MainFrame mainFrame;
	
	public SetGoalThread(MainFrame mainFrame, Socket socket) {
		this.mainFrame = mainFrame;
		this.socket = socket;
	}
	@Override
	public void run() { //랜덤의 한플레이어만 실행되는 쓰레드
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true); //연결된 소켓의  outPutStream으로 초기화
			mainFrame.getGame().setScoreImg(); //게임패널의 목표점수 설정 이미지와 점수 버튼들을 보여주는 메소드
			while(true) { //목표점수 버튼을 눌러서 목표점수가 설정될때 까지 돈다
				Thread.sleep(50);
				if(mainFrame.getGame().getGoalScore() != 0) { //목표점수가 초기화  되어 0이 아니면
					writer.println("goal/"+mainFrame.getGame().getGoalScore()); //설정된 목표점수를 상대방에게 보낸다.
					break;
				}
			}
			Question question = new Question(); //객체생성을 통해 문제를 만든다.
			Collections.shuffle(question.getQuestionList()); //문제들을 섞는다.
			GameThread gameThread = new GameThread(mainFrame, socket,question.toString().split("/")); //섞은 문제들의 구분한 배열을 게임쓰레드 객체생성의 파라미터로 보내며 생성한다. 
			gameThread.start(); //게임시작
			writer.println("questionList/"+question); //섞인 문제들을 ReceiveThread에 보낸다. Receive쓰레드에서 구분하여 초기화한다.
			
		} catch (IOException|InterruptedException e) {
			e.printStackTrace();
		} 
	}
}
