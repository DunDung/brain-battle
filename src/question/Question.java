package question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question {
	private static Map<String, String> questionMap = new HashMap<>(); //문제와 답을 저장할 Map
	private static Map<String, String> questionHintMap = new HashMap<>(); //문제와 그 문제의 힌트를 저장할 Map
	private  List<String> questionList = new ArrayList<>(); //문제 Image의 이름이 저장될 List

	public Question() { //멤버변수들에 문제와 관련된 정보를 삽입
		Question.questionMap.put("Q1.png", "11");
		Question.questionHintMap.put("Q1.png", "Q1Hint.png");
		questionList.add("Q1.png");
		Question.questionMap.put("Q2.png", "위험");
		Question.questionHintMap.put("Q2.png", "Q2Hint.png");
		questionList.add("Q2.png");
		Question.questionMap.put("Q3.png", "87");
		Question.questionHintMap.put("Q3.png", "Q3Hint.png");
		questionList.add("Q3.png");
		Question.questionMap.put("Q4.png", "7");
		Question.questionHintMap.put("Q4.png", "Q4Hint.png");
		questionList.add("Q4.png");
		Question.questionMap.put("Q5.png", "브라질");
		Question.questionHintMap.put("Q5.png", "Q5Hint.png");
		questionList.add("Q5.png");
		Question.questionMap.put("Q6.png", "220");
		Question.questionHintMap.put("Q6.png", "Q6Hint.png");
		questionList.add("Q6.png");
		Question.questionMap.put("Q7.png", "545+5=550");
		Question.questionHintMap.put("Q7.png", "Q7Hint.png");
		questionList.add("Q7.png");
		Question.questionMap.put("Q8.png", "9717");
		Question.questionHintMap.put("Q8.png", "Q8Hint.png");
		questionList.add("Q8.png");
		Question.questionMap.put("Q9.png", "34");
		Question.questionHintMap.put("Q9.png", "Q9Hint.png");
		questionList.add("Q9.png");
		Question.questionMap.put("Q10.png", "35");
		Question.questionHintMap.put("Q10.png", "Q10Hint.png");
		questionList.add("Q10.png");
		Question.questionMap.put("Q11.png", "42");
		Question.questionHintMap.put("Q11.png", "Q11Hint.png");
		questionList.add("Q11.png");
		Question.questionMap.put("Q12.png", "마굿간");
		Question.questionHintMap.put("Q12.png", "Q12Hint.png");
		questionList.add("Q12.png");
		Question.questionMap.put("Q13.png", "회오리");
		Question.questionHintMap.put("Q13.png", "Q13Hint.png");
		questionList.add("Q13.png");
		Question.questionMap.put("Q14.png", "3");
		Question.questionHintMap.put("Q14.png", "Q14Hint.png");
		questionList.add("Q14.png");
		Question.questionMap.put("Q15.png", "21");
		Question.questionHintMap.put("Q15.png", "Q15Hint.png");
		questionList.add("Q15.png");
		Question.questionMap.put("Q16.png", "3");
		Question.questionHintMap.put("Q16.png", "Q16Hint.png");
		questionList.add("Q16.png");
		Question.questionMap.put("Q17.png", "아인슈타인");
		Question.questionHintMap.put("Q17.png", "Q17Hint.png");
		questionList.add("Q17.png");
		Question.questionMap.put("Q18.png", "삼강오륜");
		Question.questionHintMap.put("Q18.png", "Q18Hint.png");
		questionList.add("Q18.png");
		Question.questionMap.put("Q19.png", "흑사병");
		Question.questionHintMap.put("Q19.png", "Q19Hint.png");
		questionList.add("Q19.png");
		Question.questionMap.put("Q20.png", "체게바라");
		Question.questionHintMap.put("Q20.png", "Q20Hint.png");
		questionList.add("Q20.png");
		Question.questionMap.put("Q21.png", "링컨");
		Question.questionHintMap.put("Q21.png", "Q21Hint.png");
		questionList.add("Q21.png");
		Question.questionMap.put("Q22.png", "마크롱");
		Question.questionHintMap.put("Q22.png", "Q22Hint.png");
		questionList.add("Q22.png");
		Question.questionMap.put("Q23.png", "거두절미");
		Question.questionHintMap.put("Q23.png", "Q23Hint.png");
		questionList.add("Q23.png");
		Question.questionMap.put("Q24.png", "고군분투");
		Question.questionHintMap.put("Q24.png", "Q24Hint.png");
		questionList.add("Q24.png");
		Question.questionMap.put("Q25.png", "기상천외");
		Question.questionHintMap.put("Q25.png", "Q25Hint.png");
		questionList.add("Q25.png");
		Question.questionMap.put("Q26.png", "개헌");
		Question.questionHintMap.put("Q26.png", "Q26Hint.png");
		questionList.add("Q26.png");
		Question.questionMap.put("Q27.png", "0");
		Question.questionHintMap.put("Q27.png", "Q27Hint.png");
		questionList.add("Q27.png");
		Question.questionMap.put("Q28.png", "5");
		Question.questionHintMap.put("Q28.png", "Q28Hint.png");
		questionList.add("Q28.png");
		Question.questionMap.put("Q29.png", "6");
		Question.questionHintMap.put("Q29.png", "Q29Hint.png");
		questionList.add("Q29.png");
		Question.questionMap.put("Q30.png", "45");
		Question.questionHintMap.put("Q30.png", "Q30Hint.png");
		questionList.add("Q30.png");
		Question.questionMap.put("Q31.png", "156");
		Question.questionHintMap.put("Q31.png", "Q31Hint.png");
		questionList.add("Q31.png");	
		Question.questionMap.put("Q32.png", "네덜란드");
		Question.questionHintMap.put("Q32.png", "Q32Hint.png");
		questionList.add("Q32.png");
		Question.questionMap.put("Q33.png", "S");
		Question.questionHintMap.put("Q33.png", "Q33Hint.png");
		questionList.add("Q33.png");
		Question.questionMap.put("Q34.png", ".");
		Question.questionHintMap.put("Q34.png", "Q34Hint.png");
		questionList.add("Q34.png");
		Question.questionMap.put("Q35.png", "26");
		Question.questionHintMap.put("Q35.png", "Q35Hint.png");
		questionList.add("Q35.png");
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



