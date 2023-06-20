package tom.iamtom.thequizmachine.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tom.iamtom.thequizmachine.exceptions.QuestionNotFoundException;
import tom.iamtom.thequizmachine.models.NewQuizDTO;
import tom.iamtom.thequizmachine.models.Question;
import tom.iamtom.thequizmachine.models.Quiz;
import tom.iamtom.thequizmachine.repositories.QuestionRepository;

@Service
public class QuizService {
    
    @Autowired
    private QuestionRepository questionRepository;
    
    public Quiz mapNewQuizDTOtoQuiz(NewQuizDTO dto) {
        
        System.out.println(dto.toString());
        
        Iterator<Long> idIterator = dto.getQuestionIds().iterator();
        Set<Question> questions = new HashSet<Question>();
        
        while(idIterator.hasNext()) {
            Long id = idIterator.next();
            Question question = questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
            questions.add(question);
        }
        
        Quiz quiz = new Quiz (dto.getName(), questions);
        
        return quiz;
    }
    
}
