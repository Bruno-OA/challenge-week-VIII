package br.com.ms.authandauto.domain.interfaces;


import br.com.ms.authandauto.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
}
