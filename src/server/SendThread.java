package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextField;

import gui.MainFrame;

public class SendThread extends Thread {

	private Socket socket;
	private MainFrame s_Frame;

	@Override
	public void run() {
		super.run();
		try {
			BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); //키보드 입력 대기/입력
			PrintWriter sendWriter = new PrintWriter(socket.getOutputStream()); 
			String sendString;

			while(true) {
				sendString = buf.readLine();
				sendWriter.println(sendString);
				sendWriter.flush();
				s_Frame.chat.send.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(e.getSource()==s_Frame.chat.send){//전송버튼 눌렀을 경우
							//메세지 입력없이 전송버튼만 눌렀을 경우
							if(s_Frame.chat.tf.getText().equals(""))
								return;
							
							s_Frame.chat.ta.append(""+ s_Frame.chat.tf.getText()+"\n");
							s_Frame.chat.tf.setText("");
						}
					}
				});

				s_Frame.chat.tf.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JTextField t = (JTextField)e.getSource();
						s_Frame.chat.ta.append(t.getText() + "\n"); 
						t.setText(""); 
					}
				});
				
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public void setJFrame(MainFrame s_Frame) {
		this.s_Frame = s_Frame;
	}
}