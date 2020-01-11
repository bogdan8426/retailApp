package com.pink.retail.answer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Integer>{
	List<Answer> findByQuestionQuestionId(int questionId);
}
