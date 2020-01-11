package com.pink.retail.question;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository  extends CrudRepository<Question, Integer>{
	List<Question> findByProductProductId(int productId);
}
