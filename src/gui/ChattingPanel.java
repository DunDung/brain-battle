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
		ts.setBounds(20,20, 1440, 830);
		tf.setBounds(20,850,1340,60);
		enter.setBounds(1360,850,100,60);
		
		enter.setFont(enter.getFont().deriveFont(16.0f)); //전송 버튼 글씨크기 변경
		
		enter.setBackground(Color.YELLOW); //전송 버튼 배경색 변경
		ta.setBackground(Color.LIGHT_GRAY); //채팅창 배경색 변경
		
	
		
	}
	public JTextArea getTa() { //채팅창 getter
		return ta;
	}
	public JTextField getTf() { //입력 필드 getter
		return tf;
	}

	public void taAdd(String a) { //추가
		this.ta.append(a);
	}

	
}




