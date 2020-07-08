package brainbattle.userState;

public class UserState {
	private static boolean playOk =false; //준비완료인지 아닌지를 나타내 줄 변수
	private static boolean turnEnd = false; //턴 종료를 나타낼 변수
	private static boolean wrong = false; //내가 낸 답이 틀렸음을 나타낼 변수
	private static boolean otherCorrect = false; //상대방이 맞췄음을 나타낼 변수
	private static boolean myCorrect  = false; //내가 맞췄음을 나타낼 변수
	private static boolean nickState = false; //나의 닉네임이 설정된 상태인지 아닌지 나타낼 변수
	
	
	public static boolean isPlayOk() {
		return playOk;
	}
	public static void setPlayOk(boolean playOk) {
		UserState.playOk = playOk;
	}
	public static boolean isTurnEnd() {
		return turnEnd;
	}
	public static void setTurnEnd(boolean turnEnd) {
		UserState.turnEnd = turnEnd;
	}
	public static boolean isWrong() {
		return wrong;
	}
	public static void setWrong(boolean wrong) {
		UserState.wrong = wrong;
	}
	public static boolean isOtherCorrect() {
		return otherCorrect;
	}
	public static void setOtherCorrect(boolean otherCorrect) {
		UserState.otherCorrect = otherCorrect;
	}
	public static boolean isMyCorrect() {
		return myCorrect;
	}
	public static void setMyCorrect(boolean myCorrect) {
		UserState.myCorrect = myCorrect;
	}
	public static boolean isNickState() {
		return nickState;
	}
	public static void setNickState(boolean nickState) {
		UserState.nickState = nickState;
	}
}
