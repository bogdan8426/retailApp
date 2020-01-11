package com.pink.retail.answer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pink.retail.question.Question;
import com.pink.retail.users.Users;

@Entity
public class Answer {
	@Column(name = "ANSWER_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int answerId;
	
	@Column(name="ANSWER_MESSAGE")
	private String answerMessage;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="questionId")
	private Question question;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="userId")
	private Users user;

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getAnswerMessage() {
		return answerMessage;
	}

	public void setAnswerMessage(String answerMessage) {
		this.answerMessage = answerMessage;
	}

	@JsonIgnore
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	
	
	
}
