package tom.iamtom.thequizmachine.helpers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringGeneratorTest {
    
    public StringGeneratorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testRandomQuizCode_testLengthIsCorrect() {
        System.out.println("randomQuizCode: testing length of result is correct");
        int requiredLength = 4;
        StringGenerator generator = new StringGenerator();
        String result = generator.randomQuizCode(requiredLength);
        System.out.println("Generated string: " + result);
        assertTrue(result.length() == requiredLength);
    }
    
}
