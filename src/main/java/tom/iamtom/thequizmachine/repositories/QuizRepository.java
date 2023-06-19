package tom.iamtom.thequizmachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tom.iamtom.thequizmachine.models.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    
}
