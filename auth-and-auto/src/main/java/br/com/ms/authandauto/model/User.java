package br.com.ms.authandauto.model;

import br.com.ms.authandauto.dto.UserRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
    private String name;

//    @Email(message = "Email inválido")
    private String email;

//    @NotEmpty(message = "A senha deve ser informada")
//    @Size(min = 6, message = "A senha deve ter no minimo 6 caracteres")
    private String password;
    private boolean admin;

    public User(UserRequestDTO data){
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.admin = data.admin();
    }

}
