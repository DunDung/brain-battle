package question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question {
	private static Map<String, String> questionMap = new HashMap<>();
	private  List<String> questionList = new ArrayList<>();

	public Question() {
		setQuestion();
	}
	void setQuestion() {
		Question.questionMap.put("Q1.png", "11");
		questionList.add("Q1.png");
		Question.questionMap.put("Q2.png", "À§Çè");
		questionList.add("Q2.png");
		Question.questionMap.put("Q3.png", "1");
		questionList.add("Q3.png");
		Question.questionMap.put("Q4.png", "7");
		questionList.add("Q4.png");
	}
	public List<String> getQuestionList() {
		return questionList;
	}
	public static Map<String, String> getQuestionMap() {
		return Question.questionMap;
	}

	@Override
	public String toString() {
		StringBuffer BufferList = new StringBuffer();
		for(int i=0; i<questionList.size(); i++) {
			BufferList.append(questionList.get(i));
			if(questionList.size()-1 != i)
				BufferList.append("/");
		}
		return BufferList.toString();
	}


}



