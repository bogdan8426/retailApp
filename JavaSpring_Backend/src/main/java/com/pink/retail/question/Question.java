package com.pink.retail.question;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.pink.retail.answer.Answer;
import com.pink.retail.product.Product;
import com.pink.retail.users.Users;

@Entity
public class Question {
	@Column(name = "QUESTION_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;
	
	@Column(name="QUESTION_MESSAGE")
	private String questionMessage;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="usersId")
	private Users user;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="productId")
	private Product product;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.MERGE)
	private List<Answer> answers;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionMessage() {
		return questionMessage;
	}

	public void setQuestionMessage(String questionMessage) {
		this.questionMessage = questionMessage;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	
	
}
