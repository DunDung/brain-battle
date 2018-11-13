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
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			Question chathNull = new Question();
			while(true) {
				String receiveString = buf.readLine(); //클라이언트가 보낸 문자열을  읽어서 receiveSring에 저장한다.
				String [] receiveArray = receiveString.split("/");

				switch(receiveArray[0]) {
				case "chat":
					mainFrame.getChat().taAdd(receiveArray[1]+"\n");
					break;	
				
				case "ready" :
					if(mainFrame.getGame().getPlayOk()) {
						writer.println("start/");
						mainFrame.getGame().getReadyOk().setVisible(false);
						setGoalThread.start();
						break;
					}
					break;
					
				case "start" :
					mainFrame.getGame().getReadyOk().setVisible(false);
					mainFrame.getGame().getWaitGoalScore().setVisible(true);
					break;
					
				case "goal" :
					mainFrame.getGame().getWaitGoalScore().setVisible(false);
					mainFrame.getGame().setGoalScore(Integer.parseInt(receiveArray[1]));
					break;
					
				case "questionList" :
					String [] questionArray = new String[receiveArray.length-1];
					for(int i=1; i<receiveArray.length; i++) {
						questionArray[i-1] = receiveArray[i];
					}
					GameThread gameThread = new GameThread(mainFrame, socket, questionArray);
					gameThread.start();
					break;
					
				case "answer":
					if(Question.getQuestionMap().get(receiveArray[1]).equals(receiveArray[2])) {
						mainFrame.getScore().addYourScore(); //소켓구조상 상대방점수 올린다.
						mainFrame.getScore().repaint();
						mainFrame.getGame().setTurnEnd(true);
						writer.println("win/");
						break;
					}
					break;

				case "win":
					mainFrame.getScore().addMyScore();
					mainFrame.getGame().setTurnEnd(true);
					mainFrame.getScore().repaint();
					break;
				}
			}

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
