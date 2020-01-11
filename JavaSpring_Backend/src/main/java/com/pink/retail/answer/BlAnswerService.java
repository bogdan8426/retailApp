package com.pink.retail.answer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlAnswerService implements BliAnswerService{

	@Autowired
	AnswerRepository answerRepository;
	
	@Override
	public List<Answer> getAnswersByQuestionId(int questionId) {
		return answerRepository.findByQuestionQuestionId(questionId);
	}

	@Override
	public Answer saveAnswer(Answer data) {
		if(!data.getAnswerMessage().isEmpty()) {
			return answerRepository.save(data);
		}else {
			return null;
		}
		
	}

	@Override
	public Answer updateAnswer(Answer data) {
		return answerRepository.save(data);
	}

	@Override
	public void removeOneAnswer(int answerId) {
		answerRepository.deleteById(answerId);
		
	}
	
	@Override
	public void removeAnswers(int questionId) {
		List<Answer> answers = answerRepository.findByQuestionQuestionId(questionId);
		for(int i=0;i<answers.size();i++) {
			answerRepository.deleteById(answers.get(i).getAnswerId());
		}	
	}

}
