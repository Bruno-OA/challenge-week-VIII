package br.com.ms.authandauto.domain.interfaces;

import br.com.ms.authandauto.domain.model.microsservice.Microservice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMicroserviceRepository extends JpaRepository<Microservice, Long> {
}
