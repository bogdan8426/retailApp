package com.pink.retail;

import com.pink.retail.question.BlQuestionService;
import com.pink.retail.question.Question;
import com.pink.retail.question.QuestionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BlQuestionServiceTest {

    private BlQuestionService service;
    private QuestionRepository questionRepository;
    private Question question;

    @Before
    public void initialize() throws NoSuchFieldException {

        service = new BlQuestionService();
        questionRepository = Mockito.mock(QuestionRepository.class);

        FieldSetter.setField(service, service.getClass().getDeclaredField("questionRepository"),
                questionRepository);

        List<Question> list = new LinkedList<>();
        question = new Question();
        question.setQuestionId(1);
        question.setQuestionMessage("question?");
        list.add(question);

        question = new Question();
        question.setQuestionId(23);
        question.setQuestionMessage("question123?");
        list.add(question);

        Mockito.when(service.getAllQuestion()).thenReturn(list);
        Mockito.when(service.saveQuestion(question)).thenReturn(question);
        Mockito.when(service.updateQuestion(question)).thenReturn(question);
    }

    @Test
    public void getQuestions(){
        assertEquals(2, service.getAllQuestion().size());
        assertEquals(question, service.getAllQuestion().get(1));
    }

    @Test
    public void saveAndUpdateQuestion(){
        //Save
        assertEquals(question, service.saveQuestion(question));
        Mockito.verify(questionRepository).save(question);

        //Update
        assertEquals(question, service.updateQuestion(question));
    }

    @Test
    public void removeOne(){
        service.removeOneQuestion(1);
        Mockito.verify(questionRepository).deleteById(1);

        service.removeOneQuestion(100);
        Mockito.verify(questionRepository).deleteById(100);

    }

}
