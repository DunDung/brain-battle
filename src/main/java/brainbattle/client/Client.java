package brainbattle.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.URISyntaxException;

import brainbattle.gui.IntroFrame;
import brainbattle.gui.MainFrame;
import brainbattle.thread.PreSettingThread;
import brainbattle.thread.ReceiveThread;

public class Client {
    public static MainFrame mainFrame;
    public static IntroFrame introFrame;

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        mainFrame = new MainFrame(); //MainFrame 객체를 생성한다. setVisible(false)이기 때문에 보이지 않는다.
        introFrame = new IntroFrame(mainFrame); //사용자로부터 ip와 닉네임을 입력받기 위한 Frame

        try {
            while (mainFrame.getIp().length() == 0) {
                Thread.sleep(100);
            }

            //InetAddress.getLocalHost().getHostAddress()
            //mainFrame.getIp()
            Socket clientSocket = new Socket(mainFrame.getIp(), 9999);//사용자로부터 입력받은 ip로 서버에서 지정한 포트로 접속한다.
            ReceiveThread reThread = new ReceiveThread(mainFrame, clientSocket); // 상대방으로 부터 받은 메세지를 처리할 쓰레드
            PreSettingThread preSetThread = new PreSettingThread(mainFrame,
                clientSocket);//사전 설정을 하고 프로그램을 연속적으로 이어갈 쓰레드

            preSetThread.start();
            reThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e);
            mainFrame.getChat().getTa().append("System :상대방과의 연결이 끊어졌습니다.\n");
        }
    }

}
