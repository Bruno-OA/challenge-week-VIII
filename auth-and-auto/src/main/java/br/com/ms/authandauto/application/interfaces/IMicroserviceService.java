package br.com.ms.authandauto.application.interfaces;


import br.com.ms.authandauto.application.dtos.MicroserviceDTO;

import java.util.List;

public interface IMicroserviceService {
    MicroserviceDTO findById(Long id);
    List<MicroserviceDTO> findAll();
    MicroserviceDTO createMicroservice(MicroserviceDTO microserviceDTO);
}
