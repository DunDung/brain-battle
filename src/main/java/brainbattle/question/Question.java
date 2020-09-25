package brainbattle.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question {
	private static Map<String, String> questionMap = new HashMap<>(); //문제와 답을 저장할 Map
	private static Map<String, String> questionHintMap = new HashMap<>(); //문제와 그 문제의 힌트를 저장할 Map
	private  List<String> questionList = new ArrayList<>(); //문제 Image의 이름이 저장될 List

	public Question() { //멤버변수들에 문제와 관련된 정보를 삽입
		Question.questionMap.put("q1.png", "11");
		Question.questionHintMap.put("q1.png", "q1-hint.png");
		questionList.add("q1.png");
		Question.questionMap.put("q2.png", "위험");
		Question.questionHintMap.put("q2.png", "q2-hint.png");
		questionList.add("q2.png");
		Question.questionMap.put("q3.png", "87");
		Question.questionHintMap.put("q3.png", "q3-hint.png");
		questionList.add("q3.png");
		Question.questionMap.put("q4.png", "7");
		Question.questionHintMap.put("q4.png", "q4-hint.png");
		questionList.add("q4.png");
		Question.questionMap.put("q5.png", "브라질");
		Question.questionHintMap.put("q5.png", "q5-hint.png");
		questionList.add("q5.png");
		Question.questionMap.put("q6.png", "220");
		Question.questionHintMap.put("q6.png", "q6-hint.png");
		questionList.add("q6.png");
		Question.questionMap.put("q7.png", "545+5=550");
		Question.questionHintMap.put("q7.png", "q7-hint.png");
		questionList.add("q7.png");
		Question.questionMap.put("q8.png", "9717");
		Question.questionHintMap.put("q8.png", "q8-hint.png");
		questionList.add("q8.png");
		Question.questionMap.put("q9.png", "34");
		Question.questionHintMap.put("q9.png", "q9-hint.png");
		questionList.add("q9.png");
		Question.questionMap.put("q10.png", "35");
		Question.questionHintMap.put("q10.png", "q10-hint.png");
		questionList.add("q10.png");
		Question.questionMap.put("q11.png", "42");
		Question.questionHintMap.put("q11.png", "q11-hint.png");
		questionList.add("q11.png");
		Question.questionMap.put("q12.png", "마굿간");
		Question.questionHintMap.put("q12.png", "q12-hint.png");
		questionList.add("q12.png");
		Question.questionMap.put("q13.png", "회오리");
		Question.questionHintMap.put("q13.png", "q13-hint.png");
		questionList.add("q13.png");
		Question.questionMap.put("q14.png", "3");
		Question.questionHintMap.put("q14.png", "q14-hint.png");
		questionList.add("q14.png");
		Question.questionMap.put("q15.png", "21");
		Question.questionHintMap.put("q15.png", "q15-hint.png");
		questionList.add("q15.png");
		Question.questionMap.put("q16.png", "3");
		Question.questionHintMap.put("q16.png", "q16-hint.png");
		questionList.add("q16.png");
		Question.questionMap.put("q17.png", "아인슈타인");
		Question.questionHintMap.put("q17.png", "q17-hint.png");
		questionList.add("q17.png");
		Question.questionMap.put("q18.png", "삼강오륜");
		Question.questionHintMap.put("q18.png", "q18-hint.png");
		questionList.add("q18.png");
		Question.questionMap.put("q19.png", "흑사병");
		Question.questionHintMap.put("q19.png", "q19-hint.png");
		questionList.add("q19.png");
		Question.questionMap.put("q20.png", "체게바라");
		Question.questionHintMap.put("q20.png", "q20-hint.png");
		questionList.add("q20.png");
		Question.questionMap.put("q21.png", "링컨");
		Question.questionHintMap.put("q21.png", "q21-hint.png");
		questionList.add("q21.png");
		Question.questionMap.put("q22.png", "마크롱");
		Question.questionHintMap.put("q22.png", "q22-hint.png");
		questionList.add("q22.png");
		Question.questionMap.put("q23.png", "거두절미");
		Question.questionHintMap.put("q23.png", "q23-hint.png");
		questionList.add("q23.png");
		Question.questionMap.put("q24.png", "고군분투");
		Question.questionHintMap.put("q24.png", "q24-hint.png");
		questionList.add("q24.png");
		Question.questionMap.put("q25.png", "기상천외");
		Question.questionHintMap.put("q25.png", "q25-hint.png");
		questionList.add("q25.png");
		Question.questionMap.put("q26.png", "개헌");
		Question.questionHintMap.put("q26.png", "q26-hint.png");
		questionList.add("q26.png");
		Question.questionMap.put("q27.png", "0");
		Question.questionHintMap.put("q27.png", "q27-hint.png");
		questionList.add("q27.png");
		Question.questionMap.put("q28.png", "5");
		Question.questionHintMap.put("q28.png", "q28-hint.png");
		questionList.add("q28.png");
		Question.questionMap.put("q29.png", "6");
		Question.questionHintMap.put("q29.png", "q29-hint.png");
		questionList.add("q29.png");
		Question.questionMap.put("q30.png", "45");
		Question.questionHintMap.put("q30.png", "q30-hint.png");
		questionList.add("q30.png");
		Question.questionMap.put("q31.png", "156");
		Question.questionHintMap.put("q31.png", "q31-hint.png");
		questionList.add("q31.png");
		Question.questionMap.put("q32.png", "네덜란드");
		Question.questionHintMap.put("q32.png", "q32-hint.png");
		questionList.add("q32.png");
		Question.questionMap.put("q33.png", "S");
		Question.questionHintMap.put("q33.png", "q33-hint.png");
		questionList.add("q33.png");
		Question.questionMap.put("q34.png", ".");
		Question.questionHintMap.put("q34.png", "q34-hint.png");
		questionList.add("q34.png");
		Question.questionMap.put("q35.png", "26");
		Question.questionHintMap.put("q35.png", "q35-hint.png");
		questionList.add("q35.png");
		Question.questionMap.put("q36.png", "1");
		Question.questionHintMap.put("q36.png", "q36-hint.png");
		questionList.add("q36.png");
		Question.questionMap.put("q37.png", "9");
		Question.questionHintMap.put("q37.png", "q37-hint.png");
		questionList.add("q37.png");
		Question.questionMap.put("q38.png", "CEABD");
		Question.questionHintMap.put("q38.png", "q38-hint.png");
		questionList.add("q38.png");
		Question.questionMap.put("q39.png", "위성도시");
		Question.questionHintMap.put("q39.png", "q39-hint.png");
		questionList.add("q39.png");
		Question.questionMap.put("q40.png", "님비현상");
		Question.questionHintMap.put("q40.png", "q40-hint.png");
		questionList.add("q40.png");
		Question.questionMap.put("q41.png", "뉴델리");
		Question.questionHintMap.put("q41.png", "q41-hint.png");
		questionList.add("q41.png");
		Question.questionMap.put("q42.png", "을지문덕");
		Question.questionHintMap.put("q42.png", "q42-hint.png");
		questionList.add("q42.png");
		Question.questionMap.put("q43.png", "청나라");
		Question.questionHintMap.put("q43.png", "q43-hint.png");
		questionList.add("q43.png");
		Question.questionMap.put("q44.png", "남한산성");
		Question.questionHintMap.put("q44.png", "q44-hint.png");
		questionList.add("q44.png");
		Question.questionMap.put("q45.png", "명량");
		Question.questionHintMap.put("q45.png", "q45-hint.png");
		questionList.add("q45.png");
		Question.questionMap.put("q46.png", "헨델");
		Question.questionHintMap.put("q46.png", "q46-hint.png");
		questionList.add("q46.png");
		Question.questionMap.put("q47.png", "바흐");
		Question.questionHintMap.put("q47.png", "q47-hint.png");
		questionList.add("q47.png");
		Question.questionMap.put("q48.png", "프라하");
		Question.questionHintMap.put("q48.png", "q48-hint.png");
		questionList.add("q48.png");
		Question.questionMap.put("q49.png", "다보탑");
		Question.questionHintMap.put("q49.png", "q49-hint.png");
		questionList.add("q49.png");
		Question.questionMap.put("q50.png", "푸아그라");
		Question.questionHintMap.put("q50.png", "q50-hint.png");
		questionList.add("q50.png");
			
	}
	
	//멤버변수들 getter
	public List<String> getQuestionList() {
		return questionList;
	}
	public static Map<String, String> getQuestionMap() {
		return Question.questionMap;
	}
	public static Map<String, String> getQuestionHintMap() {
		return Question.questionHintMap;
	}

	//상대방에게 문제 목록을 넘겨 줄때 "/"로 목록들을 구분하기 위해  toString을 재정의 하였다.
	@Override
	public String toString() {
		StringBuffer BufferList = new StringBuffer(); //ThreadSafe한 StringBuffer를 사용하였다.
		for(int i=0; i<questionList.size(); i++) {
			BufferList.append(questionList.get(i)); //문제 하나를 추가 
			if(questionList.size()-1 != i) //i가 마지막이 아니라면
				BufferList.append("/"); // "/"를 추가한다.
		}
		return BufferList.toString(); 
	}


}



