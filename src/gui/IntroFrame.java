package gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class IntroFrame extends JFrame{ 	

	private MainFrame mainFrame; //메인프레임 변수
	private IntroPanel intro = new IntroPanel();


	public IntroFrame(MainFrame mainFrame)  throws FileNotFoundException, URISyntaxException{
		this.mainFrame = mainFrame; //MainFrame변수 mainFrame 초기화
		setTitle("BrainBattle"); //타이틀 설정
		setLocation(new Point(700,350)); //윈도우상의 창의 위치를 잡아준다.
		setPreferredSize(new Dimension(820, 645));//프레임의 창의크기 설정
		pack();//컴포넌트 크기만큼 창의 크기를 잡아준다.
		setLocationRelativeTo(null); //(null)을 넣음으로써 화면중앙에 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임창을 닫을경우 프로세스를 종료

		setLayout(null); //레이아웃 없음

		add(intro);
		intro.setBounds(0,0,810,630);
		setVisible(true); //introFrame을 보여준다.
		
	}
	public JTextField getIpField() { //ipField의 getter
		return intro.ipField;
	}

	class IntroPanel extends JPanel{
		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon(this.getClass().getClassLoader().getResource("IntroBackGround.png")).getImage(),0,0,null);
			setOpaque(false);
			super.paintComponent(g);
		} 
		
		private JTextField ipField = new JTextField(); //ip를 입력할 JTextField
		private JTextField nickNameField = new JTextField(); //닉네임을 입력할 JTextField
		private URL okImg =this.getClass().getClassLoader().getResource("Ok.png");
		private JButton ok = new JButton(new ImageIcon(okImg));
		public IntroPanel() {
			this.setLayout(null);
			add(ipField);
			add(nickNameField);
			add(ok);

			ipField.setBounds(160,190, 450,70);
			ipField.setFont(new Font("Dialog", Font.BOLD, 34));
			ipField.setText(" ");

			nickNameField.setBounds(160,355,450,70);
			nickNameField.setFont(new Font("Dialog", Font.BOLD, 34));
			nickNameField.setText(" ");
			
			ok.setBounds(282, 500, 237, 74);

			ok.addActionListener(new ActionListener() { //ok 버튼을 클릭시 이벤트 추가 및 설정
				public void actionPerformed(ActionEvent e) {
					mainFrame.setIp(ipField.getText().toString().trim());
					mainFrame.getScore().setMyNickName(nickNameField.getText().toString().trim()); //mainFrame의 nickName필드를 IntroFrame의 nickNameField에 있는 텍스트로 초기화
					mainFrame.getScore().setNcikState();
					dispose(); //introFrame창을 끈다.
					mainFrame.viewTrue(); //안보이게 해두었던 mainFrame을 보이게 한다
				}
			});
			ok.addMouseListener(new MouseAdapter() {
				@Override 
				public void mouseEntered(MouseEvent e) { // 마우스를 버튼 위에 올렸을 때 
					ok.setCursor(new Cursor(Cursor.HAND_CURSOR));// 손가락모양 으로 변경
				}

				@Override 
				public void mouseExited(MouseEvent e) { // 마우스를 버튼에서 올리지 않았을 때 
					ok.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 디폴트값으로 변경 
				}
			});
		}
		
	}
}



