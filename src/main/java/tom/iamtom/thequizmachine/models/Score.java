package tom.iamtom.thequizmachine.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Score {
    
    @Id
    @GeneratedValue
    private Long id;
    //user id (reference user id) private User user; @JoinColumn(name = "user_id")
    //quiz id (reference quiz id) private Quiz quiz;
    private Integer score;
    private Long userId;
    
    
    
}
