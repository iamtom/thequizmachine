package tom.iamtom.thequizmachine.helpers;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tom.iamtom.thequizmachine.models.Category;
import tom.iamtom.thequizmachine.models.Question;
import tom.iamtom.thequizmachine.repositories.QuestionRepository;

@Configuration
public class DatabaseLoader {

    private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

    @Bean
    CommandLineRunner initDatabase(QuestionRepository questionRepository) {
        return args -> {
            //data loaded for demonstration purposes
            List<String> wrongAnswers1 = new ArrayList<>();
            wrongAnswers1.add("London");
            wrongAnswers1.add("Berlin");
            wrongAnswers1.add("Stockholm");
            questionRepository.save(new Question("What is the capital of Norway?", "Oslo", wrongAnswers1, Category.GEOGRAPHY));

            List<String> wrongAnswers2 = new ArrayList<>();
            wrongAnswers2.add("Ir");
            wrongAnswers2.add("Ni");
            wrongAnswers2.add("U");
            questionRepository.save(new Question("What is the chemical symbol for iron?", "Fe", wrongAnswers2, Category.SCIENCE));

            List<String> wrongAnswers3 = new ArrayList<>();
            wrongAnswers3.add("C major");
            wrongAnswers3.add("G major 6");
            wrongAnswers3.add("G major 9");
            questionRepository.save(new Question("Give the name for a chord with these notes: G B D F#", "G major 7", wrongAnswers3, Category.MUSIC));

            List<String> wrongAnswers4 = new ArrayList<>();
            wrongAnswers4.add("10 BC");
            wrongAnswers4.add("AD 40");
            wrongAnswers4.add("AD 47");
            questionRepository.save(new Question("What year was the first Roman invasion of Britain?", "AD 43", wrongAnswers4, Category.HISTORY));

            questionRepository.findAll().forEach(question -> log.info("Loaded: " + question));
        };
    }

}
