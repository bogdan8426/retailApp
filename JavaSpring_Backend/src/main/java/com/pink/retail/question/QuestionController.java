package com.pink.retail.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	BliQuestionService questionService;

	
	@RequestMapping(value="/{productId}",method=RequestMethod.GET)
	public List<Question> getQuestionByProdctId(@PathVariable("productId") int productId)
	{
		return questionService.getQuestionsByProductId(productId);
	}
	
	@RequestMapping(value="/removeQuestion/{questionId}", method=RequestMethod.DELETE)
	public void deleteQuestionById(@PathVariable("questionId") int questionId)
	{
		questionService.removeOneQuestion(questionId);
	}
	
	@RequestMapping(value="/addQuestion",method=RequestMethod.PUT)
	public Question addQuestion(@RequestBody Question question)
	{
		return questionService.saveQuestion(question);
	}
	
	@RequestMapping(value="/updateQuestion",method=RequestMethod.POST)
	public Question updateQuestion(@RequestBody Question question)
	{
		return questionService.updateQuestion(question);
	}
}
