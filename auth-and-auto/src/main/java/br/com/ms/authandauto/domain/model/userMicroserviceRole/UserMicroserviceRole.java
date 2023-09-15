package br.com.ms.authandauto.domain.model.userMicroserviceRole;

import br.com.ms.authandauto.domain.model.Enum.Role;
import br.com.ms.authandauto.domain.model.microsservice.Microservice;
import br.com.ms.authandauto.domain.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserMicroserviceRole {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
        @ManyToOne
        @JoinColumn(name = "microservice_id")
        private Microservice microservice;
        @Enumerated(EnumType.STRING)
        private Role role;

    }
