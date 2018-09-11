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
	JTextArea ta = new JTextArea();
	JScrollPane text = new JScrollPane(ta);
	JTextField tf = new JTextField(); //채팅창에 입력할 글을 입력하는 Field
	JButton send = new JButton("입력"); //채팅창에 입력한 글을 전송하는 버튼

	public ChattingPanel() {
		setLayout(null);
		add(text);
		add(tf);
		add(send);
		text.setBounds(20,20, 1430, 830);
		tf.setBounds(20,850,1300,60);
		send.setBounds(1320,850,130,60);
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==send){//전송버튼 눌렀을 경우
					//메세지 입력없이 전송버튼만 눌렀을 경우
					if(tf.getText().equals(""))
						return;
					ta.append(""+ tf.getText()+"\n");
					tf.setText("");
				}
			}
		});

		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField)e.getSource();
				ta.append(t.getText() + "\n"); 
				t.setText(""); 
				setVisible(true);  
			}
		});
	}
}






