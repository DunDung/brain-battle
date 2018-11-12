package question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question {
	private static Map<String, String> questionMap = new HashMap<>();
	private static List<String> questionList = new ArrayList<>();

	public Question() {
		setQuestion();
	}
	void setQuestion() {
		Question.questionMap.put("Q1.png", "11");
		Question.questionList.add("Q1.png");
		Question.questionMap.put("Q2.png", "À§Çè");
		Question.questionList.add("Q2.png");
		Question.questionMap.put("Q3.png", "1");
		Question.questionList.add("Q3.png");
		Question.questionMap.put("Q4.png", "7");
		Question.questionList.add("Q4.png");
		Collections.shuffle(Question.questionList);
	}
	public static List getQuestionList() {
		return Question.questionList;
	}
	
}



