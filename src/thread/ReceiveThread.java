package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import gui.MainFrame;
import question.Question;


public class ReceiveThread extends Thread{
	private Socket socket;
	private MainFrame mainFrame; //추가
	private static boolean start = false;
	public ReceiveThread(MainFrame mainFrame, Socket socket) { //생성자 추가
		this.mainFrame = mainFrame;
		this.socket = socket;
	}

	@Override
	public void run() {
		super.run();

		try {
			//클라이언트 소켓의 인풋스트림으로 클라이언트 소켓이 보낸 내용을 받는다.
			SetGoalThread setGoalThread = new SetGoalThread(mainFrame, socket);
			GameThread gameThread = new GameThread(mainFrame, socket);
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			while(true) {
				String receiveString = buf.readLine(); //클라이언트가 보낸 문자열을  읽어서 receiveSring에 저장한다.
				String [] receiveArray = receiveString.split("/");

				switch(receiveArray[0]) {
				case "chat":
					mainFrame.getChat().taAdd(receiveArray[1]+"\n");
					break;	
				
				case "ready" :
					if(mainFrame.getGame().getPlayOk()) {
						writer.println("go/");
						mainFrame.getGame().getReadyOk().setVisible(false);
						setGoalThread.start();
						break;
					}
					break;
					
				case "go" :
					mainFrame.getGame().getReadyOk().setVisible(false);
					mainFrame.getGame().getWaitGoalScore().setVisible(true);
					break;
					
				case "goal" :
					mainFrame.getGame().getWaitGoalScore().setVisible(false);
					mainFrame.getGame().setGoalScore(Integer.parseInt(receiveArray[1]));
					gameThread.start();
					break;
					
				case "answer":
					if(Question.getQuestionMap().get(receiveArray[1]).toString().equals(receiveArray[2])) {
						mainFrame.getScore().addYourScore(); //소켓구조상 상대방점수 올린다.
						mainFrame.getScore().repaint();
						writer.println("win/");
						break;
					}
					break;

				case "win":
					mainFrame.getScore().addMyScore();
					mainFrame.getScore().repaint();
					break;
				}
			}

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
