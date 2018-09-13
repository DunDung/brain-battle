package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Chat extends JFrame implements ActionListener, Runnable{
	private Socket socket = null;
	private BufferedReader reader = null;
	private PrintWriter writer = null;
	private JTextField tf;
	private JTextArea ta;
	private JScrollPane sp;
	
	public Chat(String address, int port) {
		sp = new JScrollPane(ta = new JTextArea());
		tf = new JTextField();
		
		ta.setBackground(Color.pink); //배경색 설정
		tf.setBackground(Color.YELLOW);
		
		add(tf,BorderLayout.SOUTH);
		add(sp,BorderLayout.CENTER);
		
		tf.addActionListener(this);
		
		setVisible(true);
		setBounds(300,300,400,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		try {
			s
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
