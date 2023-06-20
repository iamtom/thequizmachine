package tom.iamtom.thequizmachine.models;

import java.util.Set;

public class NewQuizDTO {
    
    private String name;
    private Set<Long> questionIds;
    
    public NewQuizDTO() {
    }

    public NewQuizDTO(String name, Set<Long> questionIds) {
        this.name = name;
        this.questionIds = questionIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(Set<Long> questionIds) {
        this.questionIds = questionIds;
    }
    
}
