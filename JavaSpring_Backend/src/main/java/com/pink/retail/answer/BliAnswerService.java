package com.pink.retail.answer;

import java.util.List;

public interface BliAnswerService {
	List<Answer> getAnswersByQuestionId(int questionId);
	Answer saveAnswer(Answer data);
	Answer updateAnswer(Answer data);
	void removeOneAnswer(int answerId);
	void removeAnswers(int questionId);
}
