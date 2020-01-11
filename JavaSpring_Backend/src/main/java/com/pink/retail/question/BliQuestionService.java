package com.pink.retail.question;

import java.util.List;

public interface BliQuestionService {

	List<Question> getQuestionsByProductId(int productId);
	Question saveQuestion(Question data);
	Question updateQuestion(Question data);
	void removeOneQuestion(int questionId);
}
