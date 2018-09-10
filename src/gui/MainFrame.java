package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import server.MainServer;


public class MainFrame extends JFrame{
	JButton test;
	public MainFrame() {
		super("test");
		setLocation(new Point(700,350)); //윈도우상의 창의 위치를 잡아준다.
		setPreferredSize(new Dimension(1000, 700));//프레임의 창의크기 설정
		setLayout(new GridLayout(0,2,30,30));
		pack();//컴포넌트 크기만큼 창의 크기를 잡아준다.
		setLocationRelativeTo(null); //(null)을 넣음으로써 화면중앙에 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임창을 닫을경우 프로세스를 종료
		add(new JTextField());
		test = new JButton("입력");
		test.addActionListener(new ButtonEvent());
		add(test);
		setVisible(true); //프레임을 보여준다.
		

	}

}
class ButtonEvent implements ActionListener{ 
	
	@Override
	public void actionPerformed(ActionEvent e) { 
		MainServer.re_thread.start();
		MainServer.se_thread.start();
s
	}
}
