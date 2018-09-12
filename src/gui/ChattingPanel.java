package gui;


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
	public JTextArea ta = new JTextArea();
	public JScrollPane text = new JScrollPane(ta);
	public JTextField tf = new JTextField(); //채팅창에 입력할 글을 입력하는 Field
	public JButton send = new JButton("입력"); //채팅창에 입력한 글을 전송하는 버튼

	public ChattingPanel() {
		setLayout(null);
		add(text);
		add(tf);
		add(send);
		text.setBounds(20,20, 1430, 830);
		tf.setBounds(20,850,1300,60);
		send.setBounds(1320,850,130,60);
		
	}
}






