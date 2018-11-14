package gui;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ScorePanel extends JPanel{ 	
	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon(this.getClass().getClassLoader().getResource("ScoreBackGround.png")).getImage(),0,0,null);
		setOpaque(false);
		super.paintComponent(g);
	}
	private JLabel myNickName = new JLabel("");
	private JLabel yourNickName = new JLabel("");
	private int myScoreCount = 0;
	private int yourScoreCount = 0;
	private JLabel myScore = new JLabel(""+myScoreCount);
	private JLabel yourScore = new JLabel(""+yourScoreCount);
	private boolean nickState = false;
	

	public ScorePanel() {
		setLayout(null); //레이아웃 없음
		//패널에 추가
		add(myNickName);
		add(yourNickName);
		add(myScore);
		add(yourScore);
		//레이아웃이 없기에 위치를 각자 지정해 준다.
		myNickName.setBounds(15, 60, 100,20);
		yourNickName.setBounds(299, 60, 130, 30);
		myScore.setBounds(181, 15, 30, 50);
		yourScore.setBounds(250, 15, 30, 50);
		
		//각 라벨들 글씨 크기 및 글씨 색, 라벨 배경색 변경
		myNickName.setFont(new Font("Dialog", Font.BOLD, 20));
		myNickName.setForeground(Color.BLACK); //글씨 색
		myNickName.setBackground(Color.WHITE); //배경 색
		myNickName.setOpaque(true); //라벨 투명도 설정
		myNickName.setHorizontalAlignment(JLabel.CENTER); //닉네임 가운데 정렬

		yourNickName.setFont(new Font("Dialog", Font.BOLD, 20));
		yourNickName.setForeground(Color.BLACK);
		yourNickName.setBackground(Color.WHITE);
		yourNickName.setOpaque(true);
		yourNickName.setHorizontalAlignment(JLabel.CENTER);
		
		myScore.setFont(myScore.getFont().deriveFont(25.0f));
		myScore.setForeground(Color.BLUE);
		yourScore.setFont(yourScore.getFont().deriveFont(25.0f));
		yourScore.setForeground(Color.RED);
		
		//패널 배경 색 변경 및 Score 글자 색 변경
		setBackground(Color.black);

		
	}
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
	
}




