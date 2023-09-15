package br.com.ms.authandauto.domain.interfaces;

import br.com.ms.authandauto.domain.model.microsservices.Microservice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMicrosservicesServiceRepostitory extends JpaRepository<Microservice, Long> {
}
