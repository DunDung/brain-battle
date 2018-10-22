package gui;


import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ScorePanel extends JPanel{

//	private static JLabel myNickName = new JLabel("");
//	private static JLabel yourNickName = new JLabel("");
	private JLabel vs = new JLabel("VS");
	private JLabel myScore = new JLabel(""+myScoreCount);
	private JLabel yourScore = new JLabel(""+yourScoreCount);
	private static int myScoreCount = 0;
	private static int yourScoreCount = 0;
	private Socket socket;
	

	public ScorePanel() {
		setLayout(null); //레이아웃 없음
		//패널에 추가
//		add(myNickName);
//		add(yourNickName);
		add(vs);
		add(myScore);
		add(yourScore);
		//레이아웃이 없기에 위치를 각자 지정해 준다.
//		myNickName.setBounds(70, 60, 150,50);
//		yourNickName.setBounds(320, 60, 150, 50);
		vs.setBounds(105, 30, 350, 50);
		myScore.setBounds(75, 30, 30, 50);
		yourScore.setBounds(145, 30, 30, 50);
		
		//각 라벨들 글자크기 변경
//		myNickName.setFont(myNickName.getFont().deriveFont(23.0f));
//		yourNickName.setFont(yourNickName.getFont().deriveFont(23.0f));
		vs.setFont(vs.getFont().deriveFont(19.0f));
		vs.setForeground(Color.WHITE);
		myScore.setFont(myScore.getFont().deriveFont(25.0f));
		myScore.setForeground(Color.WHITE);
		yourScore.setFont(yourScore.getFont().deriveFont(25.0f));
		yourScore.setForeground(Color.WHITE);
		
		//패널 배경 색 변경 및 Score 글자 색 변경
		setBackground(Color.black);

//		
//		enter.setBackground(Color.YELLOW); //전송 버튼 배경색 변경
//		ta.setBackground(Color.LIGHT_GRAY); //채팅창 배경색 변경
		
	}
//	public void setMyNickName(String nickName) {
//		this.myNickName.setText(nickName);
//	}
//
//	public void setYourNickName(String nickName) {
//		this.yourNickName.setText(nickName);
//	}
//	
//	public String getMyNickName() {
//		return myNickName.getText().toString();
//	}
//	
//	public String getYourNickName() {
//		return yourNickName.getText().toString();
//	}
//
//	public int getMyScore() {
//		return myScoreCount;
//	}
//
//	public void setMyScore(int score) {
//		this.myScoreCount = score;
//	}

	public int getYourScore() {
		return yourScoreCount;
	}

	public void setYourScore(int score) {
		this.yourScoreCount = score;
	}
	
	public void setSocket(Socket socket) {  //client소켓으로 초기화하는 용도.
		this.socket = socket;
	}
	
}




