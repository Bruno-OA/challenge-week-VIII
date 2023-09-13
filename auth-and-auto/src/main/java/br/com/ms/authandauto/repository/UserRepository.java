package br.com.ms.authandauto.repository;

import br.com.ms.authandauto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
