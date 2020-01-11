package com.pink.retail.answer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pink.retail.question.Question;

@RestController
@RequestMapping("/answer")
public class AnswerController {

	@Autowired
	BliAnswerService answerService;

	
	@RequestMapping(value="/{questionId}",method=RequestMethod.GET)
	public List<Answer> getAnswerById(@PathVariable("questionId") int questionId)
	{
		return answerService.getAnswersByQuestionId(questionId);
	}
	
	@RequestMapping(value="/removeAnswer/{answerId}", method=RequestMethod.DELETE)
	public void deleteAnswerByAnswerId(@PathVariable("answerId") int answerId)
	{
		answerService.removeOneAnswer(answerId);
	}
	
	@RequestMapping(value="/removeAnswers/{questionId}", method=RequestMethod.DELETE)
	public void deleteAnswersByQuestionId(@PathVariable("questionId") int questionId)
	{
		answerService.removeAnswers(questionId);
	}
	
	@RequestMapping(value="/addAnswer/{questionId}",method=RequestMethod.PUT)
	public Answer addAnswer(@RequestBody Answer answer,@PathVariable("questionId") int questionId)
	{		
		Question question= new Question();
		question.setQuestionId(questionId);
		answer.setQuestion(question);
		return answerService.saveAnswer(answer);
	}
	
	@RequestMapping(value="/updateAnswer",method=RequestMethod.POST)
	public Answer updateAnswer(@RequestBody Answer answer)
	{
		return answerService.updateAnswer(answer);
	}
}
