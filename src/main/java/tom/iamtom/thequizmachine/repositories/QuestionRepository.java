package tom.iamtom.thequizmachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tom.iamtom.thequizmachine.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    
}
