package brainbattle.thread;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;

import brainbattle.gui.MainFrame;
import brainbattle.question.Question;
import brainbattle.userState.UserState;

public class ReceiveThread extends Thread {
    private Socket socket;
    private MainFrame mainFrame; //추가
    private static boolean start = false;
    private BgmControlThread bgm = null;

    public ReceiveThread(MainFrame mainFrame, Socket socket) throws FileNotFoundException, URISyntaxException { //생성자 추가
        this.mainFrame = mainFrame;
        this.socket = socket;
        this.bgm = new BgmControlThread();
    }

    @Override
    public void run() {
        super.run();

        try {
            //클라이언트 소켓의 인풋스트림으로 클라이언트 소켓이 보낸 내용을 받는다.
            bgm.start(); //bgm쓰레드를 시작함으로써 인트로음악을 튼다.

            //연결된 소켓의 inputStream과 outputStream으로 주고받기위한 변수 초기화
            BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Question catchNull = new Question(); ////생성자서 문제이미지와 답을 추가하였기에 static이여도 객체를 생성하지 않으면 nullPoint에러가 발생

            while (true) {
                String receiveString = buf.readLine(); //클라이언트가 보낸 문자열을  읽어서 receiveString에 저장한다.
                String[] receiveArray = receiveString.split("/"); // "/"를 기준으로 receiveString을 나누어서 배열로 저장.

                switch (receiveArray[0]) { //보내온 문자열 중 맨앞 문자열을 기준으로 무엇을 원하는지 판단한다.

                    case "chat": //채팅
                        mainFrame.getChat().getTa().append(receiveArray[1] + "\n"); //내 채팅창에 받아온 메시지를 추가한다.
                        break;

                    case "nickName": //닉네임
                        mainFrame.getScore().setYourNickName(receiveArray[1]); //받아온 닉네임으로 상대 닉네임 초기화
                        break;

                    case "ready": //준비완료
                        if (UserState.isPlayOk()) { //나도 준비완료 되었을 경우
                            writer.println("start/"); //상대에게 start를 보낸다
                            mainFrame.getGame().getReadyOk().setVisible(false); //준비완료 버튼을 사라지게 한다.
                            mainFrame.getGame().setTfAndEnter(); //답안전송할 텍스트필드와 버튼을 추가
                            mainFrame.getGame().getRuleButton().setVisible(false); //게임설명 버튼을 사라지게 한다.
                            mainFrame.getChat().getTa().append("System :게임을 시작합니다.\n");
                            bgm.closeIntro(); //게임이 시작되기 때문에 Intro음악을 꺼준다.

                            SetGoalThread setGoalThread = new SetGoalThread(mainFrame, socket); //한명의 플레이어만 목표점수를 설정한다.
                            setGoalThread.start();
                            break;
                        }
                        break;

                    case "start": //시작
                        //시작신호를 받은 상대방도 똑같은 환경을 만들어준다.
                        mainFrame.getGame().getReadyOk().setVisible(false);
                        mainFrame.getGame().setTfAndEnter();
                        mainFrame.getGame().getRuleButton().setVisible(false);
                        mainFrame.getChat().getTa().append("System :게임을 시작합니다.\n");
                        bgm.closeIntro();

                        //상대방 플레이어가 목표점수를 설정할 동안 기다려 달라는 이미지를 표시한다.
                        mainFrame.getGame().getWaitGoalScore().setVisible(true);
                        break;

                    case "goal"://목표점수
                        mainFrame.getGame().getWaitGoalScore().setVisible(false); //목표점수 설정동안 기다려달라는 이미지를 안보이게 한다.
                        mainFrame.getGame()
                            .setGoalScore(Integer.parseInt(receiveArray[1])); //받아온 목표점수를 Integer로 바꾼후 게임패널에 초기화한다.
                        break;

                    case "questionList"://문제목록, 같은 문제를 보여주기 위해 한쪽에서만 섞고 한쪽은 목록을 받아야한다.
                        String[] questionArray = new String[receiveArray.length - 1];//문제목록을 받아오기 위한 String형 배열
                        for (int i = 1;
                             i < receiveArray.length; i++) { //receiveArray[0]은 questionList라는 키워드기 때문에 i=1이다.
                            questionArray[i - 1] = receiveArray[i]; // questionArray[0]부터 섞인 문제목록으로 채운다.
                        }
                        GameThread gameThread = new GameThread(mainFrame, socket,
                            questionArray); //받아온 문제목록으로 게임쓰레드를 초기화후 실행
                        gameThread.start();
                        break;

                    case "answer": //정답 제출 , 이 정답은 상대방이 보내온 정답이다, 정답이 맞는지 Question클래스의 QuestionMap를 문제번호를 key로 value로 비교한다.
                        receiveArray[2] = receiveArray[2].toUpperCase(); //대문자로 바꿔서 다시 저장
                        if (Question.getQuestionMap().get(receiveArray[1]).equals(receiveArray[2])) { //맞았을 시
                            UserState.setOtherCorrect(true);
                            ; //상대방이 맞았음을 알린다.
                            mainFrame.getScore().addYourScore(); //상대방점수 올린다.
                            mainFrame.getScore().repaint(); //스코어를 다시그린다.
                            TimerThread.setTimerStop(true); //타이머를 멈춘 후 새로 시작할 수 있게 한다.
                            writer.println("answerOk/"); //상대방에게 정답이 맞다고 보낸다.
                            break;
                        } else { //틀렸을 시
                            writer.println("answerWrong/"); // 상대방에게 정답이 아니라고 보낸다.
                            break;
                        }

                    case "answerOk": //내가낸 답이 맞았을 시
                        UserState.setMyCorrect(true); //내가맞았음을 알린다.
                        TimerThread.setTimerStop(true); //타이머를 멈춘 후 새로 시작할 수 있게 한다.
                        mainFrame.getScore().addMyScore(); //나의 점수를 올린다.
                        mainFrame.getScore().repaint(); //스코어를 다시 그린다.
                        break;

                    case "answerWrong": //내가 틀렸을 시
                        UserState.setWrong(true); //내가 틀렸음을 알린다.
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
