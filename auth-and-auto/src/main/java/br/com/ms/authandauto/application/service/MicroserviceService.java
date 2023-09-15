package br.com.ms.authandauto.application.service;

import br.com.ms.authandauto.application.dtos.MicroserviceDTO;
import br.com.ms.authandauto.application.interfaces.IMicroserviceService;
import br.com.ms.authandauto.domain.interfaces.IMicroserviceRepository;
import br.com.ms.authandauto.domain.model.microsservice.Microservice;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicroserviceService implements IMicroserviceService {
    private final IMicroserviceRepository _microserviceRepository;
    private final ModelMapper _modelMapper;


    public MicroserviceService(IMicroserviceRepository microserviceRepository, ModelMapper modelMapper) {
        _microserviceRepository = microserviceRepository;
        _modelMapper = modelMapper;
    }

    @Override
    public MicroserviceDTO findById(Long id) {
        return _modelMapper.map(_microserviceRepository.findById(id).orElse(new Microservice()), MicroserviceDTO.class);
    }

    @Override
    public List<MicroserviceDTO> findAll() {
        return null;
    }

    @Override
    public void save(MicroserviceDTO microserviceDTO) {

    }
}
