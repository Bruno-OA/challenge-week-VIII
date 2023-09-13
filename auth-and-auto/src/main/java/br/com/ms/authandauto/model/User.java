package br.com.ms.authandauto.model;

import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.*;

@Table(name = "USERS")
@Entity(name = "users")
@Data
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "A senha deve ser informada")
    @Size(min = 6, message = "A senha deve ter no minimo 6 caracteres")
    private String password;
    private boolean admin;

}
