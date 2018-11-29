package thread;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;

import gui.MainFrame;
import javazoom.jl.decoder.JavaLayerException;
import question.Question;


public class ReceiveThread extends Thread {
	private Socket socket;
	private MainFrame mainFrame; //추가
	private static boolean start = false;
	BgmControlThread bgm =null;
	public ReceiveThread(MainFrame mainFrame, Socket socket) throws FileNotFoundException, JavaLayerException, URISyntaxException{ //생성자 추가
		this.mainFrame = mainFrame;
		this.socket = socket;
		this.bgm = new BgmControlThread();
	}

	@Override
	public void run() {
		super.run();

		try {
			//클라이언트 소켓의 인풋스트림으로 클라이언트 소켓이 보낸 내용을 받는다.
			bgm.start();
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			Question catchNull = new Question(); //정답을 비교할 때 null포인트 에러 방지
			
			while(true) {
				String receiveString = buf.readLine(); //클라이언트가 보낸 문자열을  읽어서 receiveString에 저장한다.
				String [] receiveArray = receiveString.split("/"); // "/"를 기준으로 receiveString을 나누어서 배열로 저장.

				switch(receiveArray[0]) { //보내온 문자열 중 맨앞 문자열을 기준으로 무엇을 원하는지 판단한다.
				
				case "chat": //채팅
					mainFrame.getChat().getTa().append(receiveArray[1]+"\n"); //내 채팅창에 받아온 메시지를 추가한다.
					break;	
					
				case "nickName" : //닉네임
					mainFrame.getScore().setYourNickName(receiveArray[1]); //받아온 닉네임으로 상대 닉네임 초기화
					break;
					
				case "ready" : //준비완료 
					if(mainFrame.getGame().getPlayOk()) { //나도 준비완료 되었을 경우
						writer.println("start/"); //상대에게 start를 보낸다
						mainFrame.getGame().getReadyOk().setVisible(false); //준비완료 버튼을 사라지게 한다.
						mainFrame.getGame().setTfAndEnter(); //답안전송할 텍스트필드와 버튼을 추가
						mainFrame.getGame().getRuleButton().setVisible(false); //게임설명 버튼을 사라지게 한다.
						mainFrame.getChat().getTa().append("System :게임을 시작합니다.\n"); 
						bgm.closeIntro(); //게임이 시작되기 때문에 Intro음악을 꺼준다.
						
						SetGoalThread setGoalThread = new SetGoalThread(mainFrame, socket); //한명의 플레이어만 목표점수를 설정한다.
						setGoalThread.start(); 
						break;
					}
					break;
					
				case "start" : //시작
					mainFrame.getGame().getReadyOk().setVisible(false);           
					mainFrame.getGame().setTfAndEnter();						
					mainFrame.getGame().getRuleButton().setVisible(false);			
					mainFrame.getChat().getTa().append("System :게임을 시작합니다.\n");   
					bgm.closeIntro();                                               //시작신호를 받은 상대방도 똑같은 환경을 만들어준다.                                              
					
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
						mainFrame.getGame().setOtherCorrect(true);
						mainFrame.getScore().addYourScore(); //소켓구조상 상대방점수 올린다.
						mainFrame.getScore().repaint();
						TimerThread.setTimerStop(true);
						writer.println("answerOk/");
						break;
					}
					else {
						writer.println("answerWrong/");
						break;
					}

				case "answerOk":
					mainFrame.getGame().setIAmCorrect(true);
					TimerThread.setTimerStop(true);
					mainFrame.getScore().addMyScore();
					mainFrame.getScore().repaint();
					break;
				
				case "answerWrong" :
					mainFrame.getGame().setWrong(true);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
