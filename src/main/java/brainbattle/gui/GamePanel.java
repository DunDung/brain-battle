package brainbattle.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;

import brainbattle.userState.UserState;
import brainbattle.utils.ImageUtils;

public class GamePanel extends JPanel {
    public void paintComponent(Graphics g) { //배경 넣기
        g.drawImage(ImageUtils.createImage(this, "game/game-background.png"), 0, 0,
            null);//Graphics형 변수 g에 이미지를 그려준다.
        setOpaque(false); //투명하지 않게 함으로써 배경이미지가 보이게 한다.
        super.paintComponent(g); //g를 컴포넌트에 그린다.
    }

    private JTextField tf = new JTextField(); // 답안을 입력할 텍스트 필드
    private JButton enter = new JButton(ImageUtils.createImageIcon(this, "game/send-answer.png")); //답안을 전송할 버튼
    private JButton ruleButton = new JButton(ImageUtils.createImageIcon(this, "game/game-rule-button.png")); //게임설명을 보여줄 버튼
    private JLabel rule = new JLabel(
        ImageUtils.createImageIcon(this, "game/game-rule.png")); //게임설명을 적어둔 라벨
    private JButton ready = new JButton(
        ImageUtils.createImageIcon(this, "game/ready.png")); //레디 버튼
    private JLabel readyOk = new JLabel(
        ImageUtils.createImageIcon(this, "game/complete-ready.png")); //레디 버튼 클릭시 보여줄 라벨
    private JLabel quiz = new JLabel(ImageUtils.createImageIcon(this, "game/3-start-count.png")); //퀴즈를 계속해서 띄워줄 라벨. 처음엔 스타트카운트를 해줄 3으로 초기화
    private JLabel goalLabel = new JLabel(
        ImageUtils.createImageIcon(this, "game/set-target-score.png")); //목표점수 설정시 보여줄 라벨
    private JButton[] scoreImg = {
        new JButton("3", ImageUtils.createImageIcon(this, "game/3-score.png")), //목표점수 설정을 도와줄 3점 버튼
        new JButton("5", ImageUtils.createImageIcon(this, "game/5-score.png")), //목표점수 설정을 도와줄 5점 버튼
        new JButton("7", ImageUtils.createImageIcon(this, "game/7-score.png")),  //목표점수 설정을 도와줄 7점 버튼
        new JButton("10", ImageUtils.createImageIcon(this, "game/10-score.png")),//목표점수 설정을 도와줄 10점 버튼
        new JButton("15", ImageUtils.createImageIcon(this, "game/15-score.png"))}; //목표점수 설정을 도와줄 15점 버튼
    private JLabel waitGoalScore = new JLabel(ImageUtils.createImageIcon(this, "game/setGoal.png")); //한플레이어가 목표점수를 설정할 동안 대기하라는 설명을 적어둔 라벨
    private JLabel timer = new JLabel(
        ImageUtils.createImageIcon(this, "timer/t60.png")); //타이머를 표시할 라벨 첨엔 60으로 설정
    private JButton yes = new JButton(
        ImageUtils.createImageIcon(this, "game/yes.png")); //게임종료 시 다시 시작하겠냐는 물음에 응답하는 Yes버튼
    private JButton no = new JButton(
        ImageUtils.createImageIcon(this, "game/no.png"));//게임종료시 다시 시작하겠냐는 물음에 응답하는 No버튼
    private int goalScore = 0; //버튼을 눌러 목표점수를 설정 시 초기화될 변수
    //	private boolean playOk =false; //준비완료인지 아닌지를 나타내 줄 변수
    //	private boolean turnEnd = false; //턴 종료를 나타낼 변수
    //	private boolean wrong = false; //내가 낸 답이 틀렸음을 나타낼 변수
    //	private boolean otherCorrect = false; //상대방이 맞췄음을 나타낼 변수
    //	private boolean iAmCorrect  = false; //내가 맞췄음을 나타낼 변수

    public GamePanel() { //생성자
        this.setLayout(null); //레이아웃 없음

        //패널에 추가
        add(ruleButton);
        add(ready);
        add(quiz);
        add(readyOk);
        add(waitGoalScore);
        add(rule);
        add(timer);

        //각각의 위치 및 크기 설정
        timer.setBounds(829, 2, 150, 150);
        rule.setBounds(414, 1, 565, 386);
        ruleButton.setBounds(760, 1, 219, 87);
        ready.setBounds(410, 400, 250, 210);
        readyOk.setBounds(410, 400, 250, 210);
        quiz.setBounds(0, 0, 980, 885);
        waitGoalScore.setBounds(0, 0, 980, 885);

        //처음엔 보여주지 않고 나중에 상황에 맞게 보여준다.
        timer.setVisible(false);
        quiz.setVisible(false);
        readyOk.setVisible(false);
        waitGoalScore.setVisible(false);
        rule.setVisible(false);

        //답안을 입력하는 텍스트필드에 삽입될 문자의 크기를 조정
        tf.setFont(enter.getFont().deriveFont(16.0f));

        //룰버튼과 엔트와 텍스트필드에 검은색 배경 테두리를 설정
        ruleButton.setBorder(new LineBorder(Color.BLACK));
        enter.setBorder(new LineBorder(Color.BLACK));
        tf.setBorder(new LineBorder(Color.BLACK));

        ready.setContentAreaFilled(false); //레디 버튼 내용영역 비우기
        ready.setBorderPainted(false); //레디 버튼 외곽선 지우기

        //룰버튼과 레디버튼에 마우스 커서 이벤트 추가
        ruleButton.addMouseListener(new MouseCursorEvent());
        ready.addMouseListener(new MouseCursorEvent());

        ready.addActionListener(new ActionListener() { //레디버튼의 액션리스너 추가
            @Override
            public void actionPerformed(ActionEvent e) { //클릭시
                ready.setVisible(false); //레디버튼을 안보이게하고
                readyOk.setVisible(true); //readyOK라벨을 보이게 한다
                UserState.setPlayOk(true); //준비완료가 됐음을 나타낼 playOk변수를 true로 초기화
            }
        });
    }

    //멤버변수들 getter/setter
    public JLabel getReadyOk() {
        return this.readyOk;
    }

    public JLabel getQuiz() {
        return quiz;
    }

    public int getGoalScore() {
        return this.goalScore;
    }

    public void setGoalScore(int goalScore) {
        this.goalScore = goalScore;
    }

    public JLabel getWaitGoalScore() {
        return waitGoalScore;
    }

    public JButton getEnter() {
        return enter;
    }

    public JTextField getTf() {
        return tf;
    }

    public JButton getRuleButton() {
        return ruleButton;
    }

    public JLabel getTimer() {
        return timer;
    }

    public JButton getYes() {
        return yes;
    }

    public JButton getNo() {
        return no;
    }

    //게임시작 시 답안전송 버튼과 답안을 입력할 텍스트 필드를 보여줄 메소드
    public void setTfAndEnter() {
        add(tf);
        add(enter);
        tf.setBounds(0, 885, 878, 50);
        enter.setBounds(878, 885, 101, 50);
    }

    //게임종료 시 게임을 다시 시작할 것인지에 대한 물음에 대답하는 yes버튼, no버튼을 추가하고 나타낼 메소드
    public void setYesAndNo() {
        add(yes);
        add(no);
        yes.addMouseListener(new MouseCursorEvent()); //마우스커서 이벤트 추가
        no.addMouseListener(new MouseCursorEvent());  //마우스커서 이벤트 추가
        yes.setBounds(198, 611, 237, 74);
        no.setBounds(543, 611, 237, 74);
    }

    public void setScoreImg() { //목표점수 설정 시 나타낼 컴포넌트들을 설정하는 메소드
        int y = 0; //y좌표값을 0으로 우선 초기화
        add(goalLabel); //목표점수 설정이란 것을 안내할 라벨 추가
        goalLabel.setBounds(0, y, 980, 148);
        for (int i = 0; i < scoreImg.length; i++) {
            y += 148; //y좌표에 148을 더해준다
            add(scoreImg[i]); //점수 버튼 추가
            scoreImg[i].setBounds(0, y, 980, 148); //위치설정
            scoreImg[i].addMouseListener(new MouseCursorEvent()); //점수 버튼에 마우스 커서 이벤트 추가
            scoreImg[i].addActionListener(new ActionListener() { //점수 버튼에 액션리스너 추가
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton temp = (JButton)e.getSource(); //클릭된 버튼으로 temp를 초기화
                    goalScore = Integer.parseInt(temp.getText()); //클릭된 버튼의 미리 설정된 text값으로 목표점수를 나타낼 goalScore초기화
                    //목표점수 설정 시 사용했던 컴포넌트들을 전부 안보이게 한다.
                    goalLabel.setVisible(false);
                    for (JButton b : scoreImg)
                        b.setVisible(false);
                }
            });
        }
    }

    //마우스 커서 이벤트
    class MouseCursorEvent extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent e) { // 마우스를 버튼 위에 올렸을 때
            setCursor(new Cursor(Cursor.HAND_CURSOR));// 커서를 손가락모양 으로 변경
            if (e.getSource().equals(ready)) //레디버튼 일 시
                ready.setIcon(new ImageIcon(
                    this.getClass().getClassLoader().getResource("hover-ready.png"))); //ready의 이미지를 바꿔준다.
            if (e.getSource().equals(ruleButton)) //게임설명 버튼일 시
                rule.setVisible(true); //게임설명을 적어둔 rule 라벨을 보여준다.
        }

        @Override
        public void mouseExited(MouseEvent e) { // 마우스를 버튼에서 올리지 않았을 때
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서를 디폴트값으로 변경
            if (e.getSource().equals(ready))  //레디버튼 일 시
                ready.setIcon(
                    ImageUtils.createImageIcon(this, "game/ready.png"));//다시 원래 이미지로 바꿔준다.
            if (e.getSource().equals(ruleButton))
                rule.setVisible(false); //rule라벨을 사라지게 한다.
        }

    }
}


