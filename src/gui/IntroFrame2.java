package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import client.MainServer2;
import server.MainServer;


public class IntroFrame2 extends JFrame{ 
	private MainFrame mainFrame; //메인프레임 변수
	private Container c = this.getContentPane(); //IntroFrame의 컨텐트판을 얻은 컨테이너
	private JTextField ipField = new JTextField(); //ip를 입력할 JTextField
	private JTextField nickNameField = new JTextField(); //닉네임을 입력할 JTextField
	private JLabel ip = new JLabel("IP :"); //ip를 입력할 필드라는 것을 알려줄 JLabel
	private JLabel nickName = new JLabel("닉네임  :"); //닉네임을 입력할 필드라는 것을 알려줄 JLabel
	private JButton inputOk = new JButton("입력완료"); //닉네임과 ip를 입력 후에 누를 버튼 
	private JButton ipDoNotKnow = new JButton("IP를 모른다면"); //ip를 아는법을 알려줄 버튼

	private Socket socket;

	public IntroFrame2(MainFrame mainFrame) {
		this.mainFrame = mainFrame; //MainFrame변수 mainFrame 초기화

		setTitle("BrainBattle"); //타이틀 설정
		setLocation(new Point(700,350)); //윈도우상의 창의 위치를 잡아준다.
		setPreferredSize(new Dimension(600, 400));//프레임의 창의크기 설정
		pack();//컴포넌트 크기만큼 창의 크기를 잡아준다.
		setLocationRelativeTo(null); //(null)을 넣음으로써 화면중앙에 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임창을 닫을경우 프로세스를 종료

		setLayout(null); //레이아웃 없음
		c.add(ip); //IP : 추가
		ip.setFont(ip.getFont().deriveFont(23.0f)); //글씨크기 변경
		ip.setBounds(50,60,50,50); //위치설정

		c.add(ipField); //ip를 입력할 필드 추가
		ipField.setFont(ipField.getFont().deriveFont(30.0f)); 
		ipField.setBounds(110,50, 430, 80); //필드 위치설정

		c.add(nickName); //닉네임 : 추가
		nickName.setFont(nickName.getFont().deriveFont(18.0f)); //글씨크기 변경
		nickName.setBounds(20,150,100,100); //위치설정

		c.add(nickNameField); //닉네임을 입력할 필드 추가
		nickNameField.setFont(nickNameField.getFont().deriveFont(30.0f));
		nickNameField.setBounds(110,160, 430, 80); //위치설정

		c.add(inputOk); //입력완료 버튼 추가
		inputOk.setFont(nickName.getFont().deriveFont(16.0f)); 
		inputOk.setBounds(240, 260, 150, 70);

		c.add(ipDoNotKnow); //ip를 모른다면 버튼 추가
		ipDoNotKnow.setFont(ipDoNotKnow.getFont().deriveFont(12.0f)); 
		ipDoNotKnow.setBounds(419, 10, 120, 30);

		ipDoNotKnow.addActionListener(new ActionListener() { //ip를 모른다면 버튼을 클릭시 이벤트 추가 및 설정
			public void actionPerformed(ActionEvent e) {
				c.remove(ipDoNotKnow); //ip를 모른다면 버튼을 지우고
				JLabel ipManner= new JLabel("명령 프롬프트에서 ipconfig 검색 후 IPv4 주소를 복사");
				c.add(ipManner); //ip를 얻는 법을 설명한 라벨을 추가
				ipManner.setBounds(210,10,330,30); //위치설정
				ipManner.setFont(ipManner.getFont().deriveFont(13.0f)); //글씨크기 변경
				//	revalidate();
				//	repaint();
			}
		});

		inputOk.addActionListener(new ActionListener() { //입력완료 버튼을 클릭시 이벤트 추가 및 설정
			public void actionPerformed(ActionEvent e) {
				mainFrame.getScore().setMyNickName(nickNameField.getText().toString()); //mainFrame의 nickName필드를 IntroFrame의 nickNameField에 있는 텍스트로 초기화
				MainServer2.ip = ipField.getText().toString();
				try {
					System.out.println(MainServer.data(nickNameField.getText().toString()));
					System.out.println(MainServer2.data(nickNameField.getText().toString()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose(); //introFrame창을 끈다.
				mainFrame.viewTrue(); //안보이게 해두었던 mainFrame을 보이게 한다
				
				}
			
				
		});
		setVisible(true); //introFrame을 보여준다.
	}

	public JTextField getIpField() { //ipField의 getter
		return ipField;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void yourNick(String nick) {
		mainFrame.getScore().setYourNickName(nick);
	}
}

