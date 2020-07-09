package brainbattle.thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import brainbattle.gui.MainFrame;
import brainbattle.userState.UserState;

public class PreSettingThread extends Thread {
    private Socket socket;
    private MainFrame mainFrame;
    private PrintWriter writer;

    public PreSettingThread(MainFrame mainFrame, Socket socket) {
        this.mainFrame = mainFrame;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            writer = new PrintWriter(socket.getOutputStream(), true);

            mainFrame.getChat().getTf().addActionListener(new SendEvent());//채팅을 위한 enter키를 누를 때 이벤트
            mainFrame.getChat().getEnter().addActionListener(new SendEvent()); //채팅을 위한 전송버튼을 누를 때 이벤트

            while (true) { //사용자가 닉네임을 초기화 할때 까지 계속 하여 돈다.
                Thread.sleep(50);
                if (UserState.isNickState()) {  //초기화 됐을 시
                    if (mainFrame.getScore().getMyNickName().trim().length() == 0) //닉네임을 공백으로 입력했을 시
                        break; //while문 빠져나감
                    writer.println("nickName/" + mainFrame.getScore()
                        .getMyNickName()); //공백이 아닐 시 receiveThread에 설정한 닉네임을 키워드와 함께 보낸다.
                    break;  //while문 빠져나감
                }
            }

            mainFrame.getChat().getTa().append("System :연결되었습니다! 먼저 게임설명을 읽어주세요.\nSystem :준비가 완료되면 Ready버튼을 눌러주세요.\n");

            while (true) { //준비완료  버튼을 누를 때 까지 계속 돈다.
                Thread.sleep(100);
                if (UserState.isPlayOk()) { //사용자가 준비완료 버튼을 눌러서 playOk가 true가 됐을 경우
                    writer.println("ready/"); //상대에게 ready 키워드를 보낸다.
                    mainFrame.getChat().getTa().append("System :준비완료!\n"); //채팅창에 준비완료가 처리됐음을 알린다.
                    break; //while문 빠져나감
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    class SendEvent implements ActionListener { //enter키를 누를 때와 "전송"버튼을 눌렀을 때의 이벤트 클래스
        @Override
        public void actionPerformed(ActionEvent e) {
            String sendString = mainFrame.getChat().getTf().getText(); //택스트 필드에 있는 문자열을 sendString에 저장한다.
            writer.println(
                "chat/" + mainFrame.getScore().getMyNickName() + " :" + sendString); //소켓의 아웃풋스트림에 sendString을 보낸다.
            mainFrame.getChat()
                .getTa()
                .append(
                    mainFrame.getScore().getMyNickName() + " :" + sendString + "\n");  //JTextArea에 sendString을 추가한다.
            mainFrame.getChat().getTf().setText(""); //텍스트 필드를 다시 아무것도 없는 상태로 만든다.
            writer.flush(); //버퍼링되어 아직 기록되지 않은 데이터를 출력 스트림에 모두 출력한다.
            mainFrame.getChat().setVisible(true);  //chat패널을 화면에 보여준다.
        }
    }
}

