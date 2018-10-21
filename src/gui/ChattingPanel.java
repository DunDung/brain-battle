package gui;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ChattingPanel extends JPanel{

	private	JTextArea ta = new JTextArea(); //채팅된 문자열들을 출력할 Area
	private	JScrollPane ts = new JScrollPane(ta); //채팅내용이 많을 경우를 대비한 JScrollPane에 JTextArea변수인 ta추가
	private	JTextField tf = new JTextField(); //채팅창에 입력할 글을 입력하는 Field
	private JButton enter = new JButton("전송"); //enter키를 누르라는 표시

	public ChattingPanel() {
		setLayout(null); //레이아웃 없음
		//패널에 추가
		add(ts);
		add(tf);
		add(enter);
		//레이아웃이 없기에 위치를 각자 지정해 준다.
		ts.setBounds(0,0, 450, 793);
		tf.setBounds(0, 791,350,50);
		enter.setBounds(349,790,100,50);
		//전송버튼, 채팅창, 채팅입력창 글자크기 변경
		enter.setFont(enter.getFont().deriveFont(15.0f)); 
		tf.setFont(enter.getFont().deriveFont(16.0f));
		ta.setFont(enter.getFont().deriveFont(16.0f));
		
		enter.setBackground(Color.YELLOW); //전송 버튼 배경색 변경
		ta.setBackground(Color.LIGHT_GRAY); //채팅창 배경색 변경
		
	
		
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

	public void taAdd(String a) { //채팅화면에 사용자가 입력한 텍스트를 추가하는 메소드
		this.ta.append(a);
	}

	
}




