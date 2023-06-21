package tom.iamtom.thequizmachine.helpers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tom.iamtom.thequizmachine.models.Question;
import tom.iamtom.thequizmachine.repositories.QuestionRepository;

@Service
public class AnswerChecker {
    
    @Autowired
    private QuestionRepository questionRepository;

    public AnswerChecker() {
    }
        
    public HashMap<Long, Boolean> checkAnswers(HashMap<Long, String> answers) {
        
        HashMap<Long, Boolean> results = new HashMap<Long, Boolean>();
        
        for (Map.Entry<Long, String> entry : answers.entrySet()) {
            
            Boolean result = checkAnswer(entry.getKey(), entry.getValue());
            results.put(entry.getKey(), result);
        }
        
        return results;
    }
    
    public Boolean checkAnswer(Long questionId, String userAnswer) {
        Question question = questionRepository.findById(questionId).get();
        
        if (!question.getCorrectAnswer().equals(userAnswer)) {
            return false;
        }
        if (question.getCorrectAnswer().equals(userAnswer)) {
            return true;
        }
        
        return null;
    }
    
}
