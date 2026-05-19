package webserviceproject.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private Long password;

    public User(){
    }

    public User(Long id, String email, String name, Long password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getPassword() {
        return password;
    }

    public void setPassword(Long password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
