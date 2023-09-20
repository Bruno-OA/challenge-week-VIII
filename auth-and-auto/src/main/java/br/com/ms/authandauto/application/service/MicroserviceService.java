package br.com.ms.authandauto.application.service;

import br.com.ms.authandauto.application.dtos.MicroserviceDTO;
import br.com.ms.authandauto.application.interfaces.IMicroserviceService;
import br.com.ms.authandauto.infra.constants.ErrorConstants;
import br.com.ms.authandauto.infra.exceptions.ExceptionResponse;
import br.com.ms.authandauto.infra.exceptions.MicroserviceAlreadyExistsExcept;
import br.com.ms.authandauto.domain.enums.ErrorCodes;
import br.com.ms.authandauto.domain.interfaces.IMicroserviceRepository;
import br.com.ms.authandauto.domain.model.microsservice.Microservice;
import br.com.ms.authandauto.infra.exceptions.MicroserviceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Microservice microservice = _microserviceRepository.findById(id).orElseThrow(() -> new MicroserviceNotFoundException(
                new ExceptionResponse(ErrorCodes.MICROSERVICE_NOT_FOUND, ErrorConstants.MICROSERVICE_NOT_FOUND)));

        return _modelMapper.map(microservice, MicroserviceDTO.class);
    }

    @Override
    public List<MicroserviceDTO> findAll() {
        List<Microservice> microservices = _microserviceRepository.findAll();
        List<MicroserviceDTO> microserviceDTOs = new ArrayList<>();
        for (Microservice microservice : microservices) {
            MicroserviceDTO microserviceDTO = _modelMapper.map(microservice, MicroserviceDTO.class);
            microserviceDTOs.add(microserviceDTO);
        }
        return microserviceDTOs;
    }

    @Override
    public MicroserviceDTO createMicroservice(MicroserviceDTO microserviceDTO) {
        Microservice microservice = _modelMapper.map(microserviceDTO, Microservice.class);
        if(_microserviceRepository.findByName(microserviceDTO.getName()).isPresent()){
            throw new MicroserviceAlreadyExistsExcept(
                    new ExceptionResponse(ErrorCodes.MICROSERVICE_ALREADY_EXISTS,
                            ErrorConstants.MICROSERVICE_ALREADY_EXISTS));
        }
        return _modelMapper.map(_microserviceRepository.save(microservice), MicroserviceDTO.class);
    }
}
