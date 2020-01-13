package com.pink.retail;

import com.pink.retail.answer.Answer;
import com.pink.retail.answer.AnswerRepository;
import com.pink.retail.answer.BlAnswerService;
import com.pink.retail.question.Question;
import com.pink.retail.users.Users;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class BlAnswerServiceTest {

    private BlAnswerService service;
    private AnswerRepository repositoryMock;
    private Answer answer;

    @Before
    public void init() throws NoSuchFieldException {
        //setup
        service = new BlAnswerService();
        repositoryMock = Mockito.mock(AnswerRepository.class);

        FieldSetter.setField(service, service.getClass().getDeclaredField("answerRepository"),
                repositoryMock);

        List<Answer> answerList = new LinkedList<>();

        answer = new Answer();
        answer.setAnswerId(1);
        Question question = new Question();
        question.setQuestionId(1);
        answer.setQuestion(question);
        answer.setAnswerMessage("message");
        answer.setUser(new Users());
        answerList.add(answer);

        answer = new Answer();
        answer.setAnswerId(21);
        Question question1 = new Question();
        question1.setQuestionId(2);
        answer.setQuestion(question1);
        answer.setAnswerMessage("new message");
        answer.setUser(new Users());
        answerList.add(answer);

        Mockito.when(service.getAnswersByQuestionId(1)).thenReturn(find(answerList, 1));
        Mockito.when(service.getAnswersByQuestionId(2)).thenReturn(find(answerList, 2));

        Mockito.when(service.saveAnswer(answer)).thenReturn(answer);
        Mockito.when(service.updateAnswer(answer)).thenReturn(answer);
    }

    private List<Answer> find(List<Answer> answerList, int i) {
        List<Answer> list = new LinkedList<>();
        for(Answer answer: answerList){
            Question question = answer.getQuestion();
            if(question !=null && question.getQuestionId() == i){
                list.add(answer);
            }
        }
        return list;
    }

    @Test
    public void getById(){
        assertNotNull(service.getAnswersByQuestionId(0));
        assertEquals(0, service.getAnswersByQuestionId(-4).size());

        assertEquals(answer, service.getAnswersByQuestionId(2).get(0));
    }

    @Test
    public void saveAnswer(){
        assertEquals(answer, service.saveAnswer(answer));

        Mockito.verify(repositoryMock).save(answer);
    }

    @Test
    public void updateAnswer(){
        assertEquals(answer, service.updateAnswer(answer));

        Mockito.verify(repositoryMock).save(answer);
    }

    @Test
    public void removeOne(){
        service.removeOneAnswer(23);
        Mockito.verify(repositoryMock).deleteById(23);

        service.removeOneAnswer(0);
        Mockito.verify(repositoryMock).deleteById(0);
    }

    @Test
    public void removeAnswers(){
        service.removeAnswers(13);
        Mockito.verify(repositoryMock).findByQuestionQuestionId(13);
        List<Answer> answers = repositoryMock.findByQuestionQuestionId(13);
        Mockito.verify(repositoryMock, Mockito.times(answers.size()))
                .deleteById(13);
    }
}
