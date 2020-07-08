package brainbattle.gui;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class ChattingPanel extends JPanel{
	private	JTextArea ta = new JTextArea(){
		public void paintComponent(Graphics g) { //배경이미지를 그려준다
			g.drawImage(new ImageIcon(this.getClass().getClassLoader().getResource("ChatBackGround.png")).getImage(),0,0,null); //Graphics형 변수 g에 이미지를 그려준다.
			setOpaque(false); // 투명하지 않게 설정함으로써 이미지가 보이게 한다.
			super.paintComponent(g); //컴포넌트에 g를 그린다.
		}
	}; //채팅된 문자열들을 출력할 Area
	private	JScrollPane ts = new JScrollPane(ta); //채팅내용이 많을 경우를 대비한 JScrollPane에 JTextArea변수인 ta추가
	private	JTextField tf = new JTextField(); //채팅창에 입력할 글을 입력하는 Field
	private JButton enter = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("ChatSend.png"))); //enter키를 누르라는 표시

	public ChattingPanel() {
		setLayout(null); //레이아웃 없음
		//패널에 추가
		add(ts);
		add(tf);
		add(enter);
		//레이아웃이 없기에 위치를 각자 지정해 준다.
		ts.setBounds(0,0, 450, 783);
		ts.setBorder(new LineBorder(Color.BLACK)); 
		tf.setBounds(0, 781,350, 50);
		enter.setBounds(349,780,101,50);
		//전송버튼, 채팅창, 채팅입력창 글자크기 변경
		enter.setFont(enter.getFont().deriveFont(15.0f)); 
		tf.setFont(enter.getFont().deriveFont(16.0f));
		ta.setFont(enter.getFont().deriveFont(16.0f));
		//배경 테두리
		enter.setBorder(new LineBorder(Color.BLACK)); 
		tf.setBorder(new LineBorder(Color.BLACK));

	}
	public JTextArea getTa() { //채팅창 getter
		return ta;
	}
	public JTextField getTf() { //입력 필드 getter
		return tf;
	}
	public JButton getEnter() { //전송 버튼 getter
		return enter;
	}

}
