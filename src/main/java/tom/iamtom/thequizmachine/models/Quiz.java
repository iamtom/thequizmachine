package tom.iamtom.thequizmachine.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Set;

@Entity
public class Quiz {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Set<Question> questions;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "score_id")
    private ArrayList<Score> scores;

    public Quiz() {
    }

    public Quiz(String name, Set<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Quiz{" + "id=" + id + ", name=" + name + ", questions=" + questions + '}';
    }

}
