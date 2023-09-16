package br.com.ms.authandauto.application.interfaces;


import br.com.ms.authandauto.application.dtos.MicroserviceDTO;

import java.util.List;

public interface IMicroserviceService {
    MicroserviceDTO findById(Long id);
    List<MicroserviceDTO> findAll();
    void save(MicroserviceDTO microserviceDTO);
}
