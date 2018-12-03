package gui;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ScorePanel extends JPanel{ 	
	public void paintComponent(Graphics g) { //배경이미지 넣기
		g.drawImage(new ImageIcon(this.getClass().getClassLoader().getResource("ScoreBackGround.png")).getImage(),0,0,null);
		setOpaque(false);
		super.paintComponent(g);
	}
	private JLabel myNickName = new JLabel(""); //나의 닉네임을 표시할 라벨
	private JLabel yourNickName = new JLabel(""); //상대방의 닉네임을 표시할 라벨
	private int myScoreCount = 0; //나의 점수를 나타낼 변수
	private int yourScoreCount = 0; //상대의 점수를 나타낼 변수
	private JLabel myScore = new JLabel(""+myScoreCount); //나의 점수를 표시할 라벨
	private JLabel yourScore = new JLabel(""+yourScoreCount); //상대의 점수를 표시할 라벨
	private boolean nickState = false; //나의 닉네임이 설정된 상태인지 아닌지 나타낼 변수


	public ScorePanel() {
		setLayout(null); //레이아웃 없음
		//패널에 추가
		add(myNickName);
		add(yourNickName);
		add(myScore);
		add(yourScore);
		
		//레이아웃이 없기에 위치를 각자 지정해 준다.
		myNickName.setBounds(98, 8, 92,22);
		yourNickName.setBounds(262, 8, 92, 22);
		myScore.setBounds(99, 32, 91, 60);
		yourScore.setBounds(262, 32, 91, 60);

		//각 라벨들 글씨 크기 및 글씨 색, 라벨 배경색 변경
		myNickName.setFont(new Font("Dialog", Font.BOLD, 19));
		myNickName.setForeground(Color.BLACK); //글씨 색
		myNickName.setHorizontalAlignment(JLabel.CENTER); //닉네임 가운데 정렬

		yourNickName.setFont(new Font("Dialog", Font.BOLD, 19));
		yourNickName.setForeground(Color.BLACK);
		yourNickName.setHorizontalAlignment(JLabel.CENTER);

		myScore.setFont(new Font("Dialog", Font.BOLD, 50));
		myScore.setForeground(Color.BLUE);
		myScore.setHorizontalAlignment(JLabel.CENTER); 

		yourScore.setFont(new Font("Dialog", Font.BOLD, 50));
		yourScore.setForeground(Color.RED);
		yourScore.setHorizontalAlignment(JLabel.CENTER); 
	}
	
	//멤버변수 getter / setter
	public void setMyNickName(String nickName) {
		this.myNickName.setText(nickName);
	}
	public void setYourNickName(String nickName) {
		this.yourNickName.setText(nickName);

	}
	public String getMyNickName() {
		return this.myNickName.getText().toString().trim();
	}

	public String getYourNickName() {
		return this.yourNickName.getText().toString().trim();
	}

	public int getMyScore() {
		return myScoreCount;
	}

	public void addMyScore() {
		this.myScore.setText(""+ (++myScoreCount));;
	}

	public int getYourScore() {
		return yourScoreCount;
	}

	public void addYourScore() {
		this.yourScore.setText(""+ (++yourScoreCount));;
	}

	public void setNcikState() {  
		this.nickState = true;
	}
	public boolean getNickState() {
		return nickState;
	}
	
	//다시시작 시에 스코어를 초기화할 메소드
	public void scoreReset() {
		//내 점수, 상대점수 0으로 초기화
		this.myScoreCount = 0; 
		this.yourScoreCount = 0;
		//내 점수 라벨, 상대 점수 라벨, 0으로 초기화
		this.myScore.setText(""+ myScoreCount);
		this.yourScore.setText(""+yourScoreCount);
		this.repaint(); //다시 그려준다.
	}
}




