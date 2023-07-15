package tom.iamtom.thequizmachine.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import tom.iamtom.thequizmachine.models.Category;
import tom.iamtom.thequizmachine.models.Question;
import tom.iamtom.thequizmachine.repositories.QuestionRepository;

@ExtendWith(MockitoExtension.class)
public class AnswerCheckerTest {

    @InjectMocks
    private AnswerChecker checker;

    @Mock
    private QuestionRepository questionRepository;

    private Question question;
    private Optional<Question> optional;

    public AnswerCheckerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        //fake Question
        List<String> wrongAnswers = new ArrayList<>();
        wrongAnswers.add("Wrong1");
        wrongAnswers.add("Wrong2");
        wrongAnswers.add("Wrong3");
        question = new Question("What is the answer?", "Correct Answer", wrongAnswers, Category.GEOGRAPHY);
        optional = Optional.of(question);

        Mockito.when(questionRepository.findById(anyLong())).thenReturn(optional);
    }

    @AfterEach
    public void tearDown() {
        question = null;
        optional = null;
        assertNull(question);
        assertNull(optional);
    }

    @Test
    public void testCheckAnswers() {
        System.out.println("Testing checkAnswers");
        HashMap<Long, String> answers = new HashMap<Long, String>();
        answers.put(1L, "Correct Answer");
        answers.put(2L, "Wrong1");
        answers.put(3L, "Wrong2");
        answers.put(4L, "Wrong3");
        
        HashMap<Long, Boolean> expResult = new HashMap<Long, Boolean>();
        expResult.put(1L, true);
        expResult.put(2L, false);
        expResult.put(3L, false);
        expResult.put(4L, false);
        
        HashMap<Long, Boolean> result = checker.checkAnswers(answers);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckAnswer_withCorrectAnswer() {
        System.out.println("Testing checkAnswer with correct answer");

        String userAnswer = "Correct Answer";

        Boolean expResult = true;
        Boolean result = checker.checkAnswer(1L, userAnswer);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckAnswer_withIncorrectAnswer() {
        System.out.println("Testing checkAnswer with incorrect answer");

        String userAnswer = "Wrong Answer";

        Boolean expResult = false;
        Boolean result = checker.checkAnswer(1L, userAnswer);
        assertEquals(expResult, result);
    }

}
