package thread; 


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.PrintWriter; 
import java.net.Socket; 
import gui.MainFrame;

public class PreSettingThread extends Thread{ 
	private Socket socket; 
	private MainFrame mainFrame; 
	private PrintWriter writer;
	public PreSettingThread(MainFrame mainFrame, Socket socket) { 
		this.mainFrame = mainFrame; 
		this.socket = socket; 
	} 
	@Override 
	public void run() { 
		try { 
			writer = new PrintWriter(socket.getOutputStream(),true); 

			mainFrame.getChat().getTf().addActionListener(new SendEvent());//채팅을 위한 enter키를 누를 때 이벤트
			mainFrame.getChat().getEnter().addActionListener(new SendEvent()); //채팅을 위한 전송버튼을 누를 때 이벤트

			while(true) { //각자 닉네임 설정
				Thread.sleep(50); 
				if(mainFrame.getScore().getNickState()) { 
					if(mainFrame.getScore().getMyNickName().trim().length() == 0)
						break;
					writer.println("nickName/"+mainFrame.getScore().getMyNickName()); 
					break; 
				} 
			}

			mainFrame.getChat().getTa().append("System :연결되었습니다! 먼저 게임설명을 읽어주세요.\nSystem :준비가 완료되면 Ready버튼을 눌러주세요.\n");

			while(true) { //준비완료 
				Thread.sleep(100);
				if(mainFrame.getGame().getPlayOk()) {
					writer.println("ready/");
					mainFrame.getChat().getTa().append("System :준비완료!\n");
					break;
				}
			}
			
		} catch (IOException  | InterruptedException e) { 
			e.printStackTrace(); 
		} 

	}
	class SendEvent implements ActionListener{ //enter키를 누를 때와 "전송"버튼을 눌렀을 때의 이벤트 클래스
		@Override
		public void actionPerformed(ActionEvent e) { 

			String 	sendString =mainFrame.getChat().getTf().getText(); //택스트 필드에 있는 문자열을 sendString에 저장한다.
			writer.println("chat/"+mainFrame.getScore().getMyNickName() +" :"+ sendString ); //소켓의 아웃풋스트림에 sendString을 보낸다.
			mainFrame.getChat().getTa().append(mainFrame.getScore().getMyNickName() +" :"+ sendString +"\n");  //JTextArea에 sendString을 추가한다.
			mainFrame.getChat().getTf().setText(""); //텍스트 필드를 다시 아무것도 없는 상태로 만든다.
			writer.flush(); //버퍼링되어 아직 기록되지 않은 데이터를 출력 스트림에 모두 출력한다.
			mainFrame.getChat().setVisible(true);  //chat패널을 화면에 보여준다.
		}
	}
}

