package tom.iamtom.thequizmachine.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private String correctAnswer;
    private List<String> wrongAnswers;
    private Category category;
    
    public Question() {
        
    }

    public Question(String text, String correctAnswer, List<String> wrongAnswers, Category category) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.wrongAnswers = wrongAnswers;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(List<String> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", text=" + text + ", correctAnswer=" + correctAnswer + ", wrongAnswers=" + wrongAnswers + ", category=" + category + '}';
    }
    
    

}
