
package tom.iamtom.thequizmachine.models;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.Set;

public class User {
    
    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id")
    private Set<Quiz> quizesMadeByUser;
    
    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
