package server;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ChattingPanel extends JPanel{
	Socket socket = null; //서버와 연결할 Socket클래스 변수
	JTextArea ta = new JTextArea(); //채팅된 문자열들을 출력할 Area
	JScrollPane ts = new JScrollPane(ta); //채팅내용이 많을 경우를 대비한 JScrollPane
	JTextField tf = new JTextField(); //채팅창에 입력할 글을 입력하는 Field

	public ChattingPanel() {
		setLayout(null);
		add(ts);
		add(tf);
		ts.setBounds(20,20, 1430, 830);
		tf.setBounds(20,850,1300,60);			
	}
	
	public void taAdd(String a) { //추가
		this.ta.append(a);
	}
}







