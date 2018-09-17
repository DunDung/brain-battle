package client;


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
	JTextArea ta = new JTextArea();
	JScrollPane text = new JScrollPane(ta);
	JTextField tf = new JTextField(); //채팅창에 입력할 글을 입력하는 Field

	public ChattingPanel() {
		setLayout(null);
		add(text);
		add(tf);
		text.setBounds(20,20, 1430, 830);
		tf.setBounds(20,850,1300,60);		
		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField)e.getSource();
				ta.append(t.getText() + "\n"); 
				t.setText(""); 
				setVisible(true); 
			}
		});
	}
	
	public void taAdd(String a) { //추가
		this.ta.append(a);
	}
}







