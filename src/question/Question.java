package question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question {
	private static Map<String, String> questionMap = new HashMap<>();
	private static Map<String, String> questionHintMap = new HashMap<>();
	private  List<String> questionList = new ArrayList<>();

	public Question() {
		setQuestion();
	}
	void setQuestion() {
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
	}
	public List<String> getQuestionList() {
		return questionList;
	}
	public static Map<String, String> getQuestionMap() {
		return Question.questionMap;
	}
	public static Map<String, String> getQuestionHintMap() {
		return Question.questionHintMap;
	}

	@Override
	public String toString() {
		StringBuffer BufferList = new StringBuffer(); //ThreadSafe한 StringBuffer를 사용하였다.
		for(int i=0; i<questionList.size(); i++) {
			BufferList.append(questionList.get(i));
			if(questionList.size()-1 != i)
				BufferList.append("/");
		}
		return BufferList.toString();
	}


}



