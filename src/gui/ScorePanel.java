package gui;


import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ScorePanel extends JPanel{

	private JLabel score = new JLabel("Score");
	private JLabel myNickName = new JLabel("");
	private JLabel yourNickName = new JLabel("");
	private JLabel vs = new JLabel("VS");
	private JLabel myScore = new JLabel("");
	private JLabel yourScore = new JLabel("");
	private int myScoreCount;
	private int yourScoreCount;
	

	public ScorePanel() {
		setLayout(null); //레이아웃 없음
		//패널에 추가
		add(score);
		add(myNickName);
		add(yourNickName);
		add(vs);
		add(myScore);
		add(yourScore);
		//레이아웃이 없기에 위치를 각자 지정해 준다.
		score.setBounds(185, 10, 100, 45);
		myNickName.setBounds(70, 60, 150,50);
		yourNickName.setBounds(320, 60, 150, 50);
		vs.setBounds(215, 60, 350, 50);
		myScore.setBounds(100, 120, 30, 50);
		yourScore.setBounds(350, 120, 30, 50);
		
		//각 라벨들 글자크기 변경
		score.setFont(score.getFont().deriveFont(30.0f)); 
		myNickName.setFont(myNickName.getFont().deriveFont(23.0f));
		yourNickName.setFont(yourNickName.getFont().deriveFont(23.0f));
		vs.setFont(vs.getFont().deriveFont(19.0f));
		myScore.setFont(myScore.getFont().deriveFont(25.0f));
		yourScore.setFont(yourScore.getFont().deriveFont(25.0f));
		
		//패널 배경 색 변경 및 Score 글자 색 변경
		setBackground(Color.WHITE);
		score.setForeground(Color.RED);

//		
//		enter.setBackground(Color.YELLOW); //전송 버튼 배경색 변경
//		ta.setBackground(Color.LIGHT_GRAY); //채팅창 배경색 변경
		
	}
	public void setMyNickName(String nickName) {
		this.myNickName.setText(nickName);
	}

	public void setYourNickName(String nickName) {
		this.yourNickName.setText(nickName);
	}

	public int getMyScore() {
		return myScoreCount;
	}

	public void setMyScore(int score) {
		this.myScoreCount = score;
	}

	public int getYourScore() {
		return yourScoreCount;
	}

	public void setYourScore(int score) {
		this.yourScoreCount = score;
	}
	
}




