package br.com.ms.authandauto.domain.interfaces;

import br.com.ms.authandauto.domain.model.userMicroserviceRole.UserMicroserviceRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserMicroserviceRoleRepository extends JpaRepository<UserMicroserviceRole, Long> {
    UserMicroserviceRole findByUserIdAndMicroserviceId(Long userId, Long microserviceId);

    UserMicroserviceRole findFirstByMicroserviceId(Long id);
    List<UserMicroserviceRole> findUserByMicroserviceId(Long id);
}
