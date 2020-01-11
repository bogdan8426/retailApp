package com.pink.retail.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlQuestionService implements BliQuestionService{

	@Autowired
	QuestionRepository questionRepository;
	
	@Override
	public List<Question> getQuestionsByProductId(int productId) {
		return (List<Question>) questionRepository.findByProductProductId(productId);
	}

	@Override
	public Question saveQuestion(Question data) {
		if(!data.getQuestionMessage().isEmpty()) {
			return questionRepository.save(data);
		}else {
			return null;
		}
		
	}

	@Override
	public Question updateQuestion(Question data) {
		return questionRepository.save(data);
	}

	@Override
	public void removeOneQuestion(int questionId) {
		questionRepository.deleteById(questionId);
		
	}

}
