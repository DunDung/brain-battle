package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class GamePanel extends JPanel{ 	
	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon(this.getClass().getClassLoader().getResource("BackGround.png")).getImage(),0,0,null);
		setOpaque(false);
		super.paintComponent(g);
	}
	

	private JTextField tf = new JTextField();
	private JButton ruleButton = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("RuleButton.png")));
	private JLabel rule = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Rule.png")));
	private JButton enter = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("AnswerSend.png")));	
	private JButton ready = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("Ready.png")));
	private JLabel readyOk = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("ReadyOk.png")));
	private JLabel quiz= new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount3.png")));
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
	private boolean turnEnd = false;
	private JLabel timer = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("T60.png")));


	public GamePanel() {
		this.setLayout(null);
		
		add(ruleButton);
		add(ready);
		add(quiz);
		add(readyOk);
		add(waitGoalScore);
		add(rule);
		add(timer);
		
		timer.setBounds(829, 2, 150, 150);
		
		timer.setVisible(false);
		quiz.setVisible(false);
		readyOk.setVisible(false);
		waitGoalScore.setVisible(false);
		rule.setVisible(false);
		
		rule.setBounds(414, 1, 565, 386);
		ruleButton.setBounds(760, 1, 219,87);
		ready.setBounds(410, 400, 250, 210);
		readyOk.setBounds(410, 400, 250, 210);
		quiz.setBounds(0, 0, 980, 885);
		waitGoalScore.setBounds(0, 0, 980, 885);
		
		tf.setFont(enter.getFont().deriveFont(16.0f));

		ruleButton.setBorder(new LineBorder(Color.BLACK));
		enter.setBorder(new LineBorder(Color.BLACK)); //배경 테두리
		tf.setBorder(new LineBorder(Color.BLACK));

		ready.setContentAreaFilled(false); //버튼 내용영역 비우기
		ready.setBorderPainted(false); //버튼 외곽선 지우기
		
		ruleButton.addMouseListener(new MouseCursorEvent());
		ready.addMouseListener(new MouseCursorEvent());
			
		
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
	public JButton getEnter() {
		return enter;
	}
	public JTextField getTf() {
		return tf;
	}
	public JButton getRuleButton() {
		return ruleButton;
	}
	public boolean getTurnEnd() {
		return turnEnd;
	}
	public void setTurnEnd(boolean turn) {
		turnEnd = turn;
	}
	public JLabel getTimer() {
		return timer;
	}
	public void setTfAndEnter() {
		add(tf);
		add(enter);
		tf.setBounds(0, 885, 878, 50);
		enter.setBounds(878, 885, 101, 50);
	}
	
	public void setScoreImg() {
		int y = 0;
		int [] score = {3, 5, 7, 10, 15};
		add(goalLabel);
		goalLabel.setBounds(0,y, 980 ,148);
		for(int i = 0; i<score.length; i++) {
			y+= 148;
			add(scoreImg[i]);
			scoreImg[i].addMouseListener(new MouseCursorEvent());
			scoreImg[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					goalScore = score[scoreImgIndexCount++];
					goalLabel.setVisible(false);
					for(JButton b : scoreImg)
						b.setVisible(false);
				}
				
			});
			scoreImg[i].setBounds(0, y, 980, 148);
		}

	}
	class MouseCursorEvent extends MouseAdapter {
		
		@Override 
		public void mouseEntered(MouseEvent e) { // 마우스를 버튼 위에 올렸을 때 
			setCursor(new Cursor(Cursor.HAND_CURSOR));// 손가락모양 으로 변경
			if(e.getSource().equals(ready))
				ready.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("MouseOnReady.png")));
			if(e.getSource().equals(ruleButton))
				rule.setVisible(true);
		}

		@Override 
		public void mouseExited(MouseEvent e) { // 마우스를 버튼에서 올리지 않았을 때 
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 디폴트값으로 변경
			if(e.getSource().equals(ready))
				ready.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("Ready.png")));
			if(e.getSource().equals(ruleButton))
				rule.setVisible(false);
		}
		
	}
}


