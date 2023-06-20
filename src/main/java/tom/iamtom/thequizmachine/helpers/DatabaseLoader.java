package tom.iamtom.thequizmachine.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tom.iamtom.thequizmachine.models.Category;
import tom.iamtom.thequizmachine.models.Question;
import tom.iamtom.thequizmachine.models.Quiz;
import tom.iamtom.thequizmachine.repositories.QuestionRepository;
import tom.iamtom.thequizmachine.repositories.QuizRepository;

@Configuration
public class DatabaseLoader {

    private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

    @Bean
    CommandLineRunner initDatabase(QuestionRepository questionRepository, QuizRepository quizRepository) {
        return args -> {
            //data loaded for demonstration purposes
            List<String> wrongAnswers1 = new ArrayList<>();
            wrongAnswers1.add("London");
            wrongAnswers1.add("Berlin");
            wrongAnswers1.add("Stockholm");
            Question question1 = new Question("What is the capital of Norway?", "Oslo", wrongAnswers1, Category.GEOGRAPHY);
            questionRepository.save(question1);

            List<String> wrongAnswers2 = new ArrayList<>();
            wrongAnswers2.add("Ir");
            wrongAnswers2.add("Ni");
            wrongAnswers2.add("U");
            Question question2 = new Question("What is the chemical symbol for iron?", "Fe", wrongAnswers2, Category.SCIENCE);
            questionRepository.save(question2);

            List<String> wrongAnswers3 = new ArrayList<>();
            wrongAnswers3.add("C major");
            wrongAnswers3.add("G major 6");
            wrongAnswers3.add("G major 9");
            Question question3 = new Question("Give the name for a chord with these notes: G B D F#", "G major 7", wrongAnswers3, Category.MUSIC);
            questionRepository.save(question3);

            List<String> wrongAnswers4 = new ArrayList<>();
            wrongAnswers4.add("10 BC");
            wrongAnswers4.add("AD 40");
            wrongAnswers4.add("AD 47");
            Question question4 = new Question("What year was the first Roman invasion of Britain?", "AD 43", wrongAnswers4, Category.HISTORY);
            questionRepository.save(question4);

            System.out.println("Loaded questions into database:");
            questionRepository.findAll().forEach(question -> log.info("Loaded: " + question));

            Set<Question> quiz1questions = new HashSet<Question>();
            quiz1questions.add(question1);
            quiz1questions.add(question2);
            Quiz quiz1 = new Quiz("QUIZ 1", quiz1questions);
            quizRepository.save(quiz1);

            Set<Question> quiz2questions = new HashSet<Question>();
            quiz2questions.add(question3);
            quiz2questions.add(question4);
            Quiz quiz2 = new Quiz("QUIZ 2", quiz2questions);
            quizRepository.save(quiz2);

            System.out.println("Loaded quizes into database:");
            quizRepository.findAll().forEach(quiz -> log.info("Loaded: " + quiz));
        };
    }

}
