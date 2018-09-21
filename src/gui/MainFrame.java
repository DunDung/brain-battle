package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class MainFrame extends JFrame{
	private Container c = this.getContentPane(); //추가
	private ChattingPanel chat = new ChattingPanel();
	private String ip = null;
	private String nickName = null;

	public MainFrame() {
		setLocation(new Point(700,350)); //윈도우상의 창의 위치를 잡아준다.
		setPreferredSize(new Dimension(1500, 1000));//프레임의 창의크기 설정
		c.add(chat); //수정
		pack();//컴포넌트 크기만큼 창의 크기를 잡아준다.
		setLocationRelativeTo(null); //(null)을 넣음으로써 화면중앙에 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임창을 닫을경우 프로세스를 종료
		setVisible(false); //프레임을 보여준다.
	}
	
	public ChattingPanel getChat() { //ChattingPanel getter
		return chat;
	}
	public String getIp() { //ip필드 getter
		return ip;
	}
	public void setIp(String ip) { //ip필드 setter
		this.ip = ip;
	}
	public String getNickName() { //nickName필드 getter
		return nickName;
	}
	public void setNickName(String nickName) { //nickName필드 setter
		this.nickName = nickName;
	}
	public void viewTrue() { //호출 시 mainFrame을 화면에 띄울 메소드
		setVisible(true);
	}
	
}


