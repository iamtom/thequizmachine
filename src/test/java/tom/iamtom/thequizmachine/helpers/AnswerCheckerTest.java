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

    /**
     * Test of checkAnswers method, of class AnswerChecker.
     */
    @Test
    public void testCheckAnswers() {
        System.out.println("checkAnswers");
        HashMap<Long, String> answers = null;
        AnswerChecker instance = new AnswerChecker();
        HashMap<Long, Boolean> expResult = null;
        HashMap<Long, Boolean> result = instance.checkAnswers(answers);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkAnswer method, of class AnswerChecker.
     */
    @Test
    public void testCheckAnswer_withCorrectAnswer() {
        System.out.println("Testing checkAnswer with correct answer");

        String userAnswer = "Correct Answer";

        Boolean expResult = true;
        Boolean result = checker.checkAnswer(1L, userAnswer);
        assertEquals(expResult, result);
    }

}
