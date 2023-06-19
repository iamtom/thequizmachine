package tom.iamtom.thequizmachine.exceptions;

public class QuizNotFoundException extends RuntimeException {
    
    public QuizNotFoundException(Long id) {
        super("Could not find quiz with ID " + id);
    }
    
}
