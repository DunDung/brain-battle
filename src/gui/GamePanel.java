package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel{ 	
	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon(this.getClass().getClassLoader().getResource("BackGround.png")).getImage(),0,0,null);
		setOpaque(false);
		super.paintComponent(g);
	}
	

	private JTextField tf = new JTextField();
	private JButton enter = new JButton("답안전송");	
	private JButton ready = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("Ready.png")));
	private JLabel readyOk = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("ReadyOk.png")));
	private JLabel quiz= new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Rule.png")));
	private JLabel goalLabel = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Goal.png")));
	private JButton [] scoreImg = {new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("3.png"))),
								   new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("5.png"))),
								   new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("7.png"))),
								   new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("10.png"))),
							   	   new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("15.png")))}; 
	private JLabel waitGoalScore = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("setGoal.png")));
	private int goalScore = 0;
	private int scoreImgIndexCount = 0;
	private boolean playOk =false;
	


	public GamePanel() {
		this.setLayout(null);
		
		add(tf);
		add(enter);
		add(ready);
		add(quiz);
		add(readyOk);
		add(waitGoalScore);
		
		quiz.setVisible(false);
		readyOk.setVisible(false);
		waitGoalScore.setVisible(false);
		
		tf.setBounds(350, 885, 200, 50);
		enter.setBounds(548, 885, 100, 50);
		ready.setBounds(410, 400, 200, 200);
		quiz.setBounds(5, 5, 968, 875);
		readyOk.setBounds(410, 400, 200, 200);
		waitGoalScore.setBounds(5, 5, 968, 875);
		
		enter.setBackground(Color.YELLOW); //전송 버튼 배경색 변경
		enter.setFont(enter.getFont().deriveFont(15.0f));
		tf.setFont(enter.getFont().deriveFont(16.0f));

		ready.setContentAreaFilled(false); //버튼 내용영역 비우기
		ready.setBorderPainted(false); //버튼 외곽선 지우기
		
		
		ready.addMouseListener(new MouseCursorEvent(ready));
			
		
		ready.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) { //버튼의 이미지를 바꿔주고 준비완료 됐음을 알림
				ready.setVisible(false);
				readyOk.setVisible(true);
				playOk = true;
			}
		});
	}

	public boolean getPlayOk() {
		return this.playOk;
	}
	public JLabel getReadyOk() {
		return this.readyOk;
	}
	public JLabel getQuiz() {
		return quiz;
	}
	public int getGoalScore() {
		return this.goalScore;
	}
	public void setGoalScore(int goalScore) {
		this.goalScore = goalScore;
	}
	public JLabel getWaitGoalScore() {
		return waitGoalScore;
	}
	
	public void setScoreImg() {
		int y = 5;
		int [] score = {3, 5, 7, 10, 15};
		add(goalLabel);
		goalLabel.setBounds(5,y, 968 ,148);
		for(int i = 0; i<score.length; i++) {
			y+= 145;
			add(scoreImg[i]);
			scoreImg[i].addMouseListener(new MouseCursorEvent(scoreImg[i]));
			scoreImg[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					goalScore = score[scoreImgIndexCount++];
					goalLabel.setVisible(false);
					for(JButton b : scoreImg)
						b.setVisible(false);
				}
				
			});
			scoreImg[i].setBounds(5, y, 968, 148);
		}
	}
}

class MouseCursorEvent extends MouseAdapter {
	JButton b;
	
	public MouseCursorEvent(JButton b) {
		this.b = b;
	}
	@Override 
	public void mouseEntered(MouseEvent e) { // 마우스를 버튼 위에 올렸을 때 
		b.setCursor(new Cursor(Cursor.HAND_CURSOR));// 손가락모양 으로 변경
	}

	@Override 
	public void mouseExited(MouseEvent e) { // 마우스를 버튼에서 올리지 않았을 때 
		b.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 디폴트값으로 변경 
	}
	
}
