package br.com.ms.scholarb.domain.model.userMicroserviceRole;

import br.com.ms.scholarb.domain.model.microsservice.Microservice;
import br.com.ms.scholarb.domain.model.user.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMicroserviceRole {

    private Long id;

    private User user;

    private Microservice microservice;

    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Microservice getMicroservice() {
        return microservice;
    }

    public void setMicroservice(Microservice microservice) {
        this.microservice = microservice;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
