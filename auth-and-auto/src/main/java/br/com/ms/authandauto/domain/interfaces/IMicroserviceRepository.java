package br.com.ms.authandauto.domain.interfaces;

import br.com.ms.authandauto.domain.model.microsservice.Microservice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMicroserviceRepository extends JpaRepository<Microservice, Long> {
    Optional<Microservice> findByName(String name);
}
