package br.com.ms.scholarb.domain.model.microsservice;

import br.com.ms.scholarb.domain.model.userMicroserviceRole.UserMicroserviceRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Microservice {

    private Long id;

    private String name;

    private List<UserMicroserviceRole> userMicroservices;

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

    public List<UserMicroserviceRole> getUserMicroservices() {
        return userMicroservices;
    }

    public void setUserMicroservices(List<UserMicroserviceRole> userMicroservices) {
        this.userMicroservices = userMicroservices;
    }


}
