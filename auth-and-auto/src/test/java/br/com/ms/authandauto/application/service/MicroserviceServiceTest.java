package br.com.ms.authandauto.application.service;

import br.com.ms.authandauto.application.dtos.MicroserviceDTO;
import br.com.ms.authandauto.domain.interfaces.IMicroserviceRepository;
import br.com.ms.authandauto.domain.model.microsservice.Microservice;
import br.com.ms.authandauto.infra.exceptions.MicroserviceAlreadyExistsExcept;
import br.com.ms.authandauto.infra.exceptions.MicroserviceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MicroserviceServiceTest {

    @InjectMocks
    private MicroserviceService _microserviceService;

    @Mock
    private IMicroserviceRepository _microserviceRepository;
    @Mock
    private ModelMapper _modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Microservice microservice = new Microservice();
        microservice.setId(id);

        when(_microserviceRepository.findById(id)).thenReturn(Optional.of(microservice));

        MicroserviceDTO expectedMicroserviceDTO = new MicroserviceDTO();
        expectedMicroserviceDTO.setId(id);
        when(_modelMapper.map(microservice, MicroserviceDTO.class)).thenReturn(expectedMicroserviceDTO);

        MicroserviceDTO result = _microserviceService.findById(id);

        assertNotNull(result);

        assertEquals(id, result.getId());
    }

    @Test
    public void testFindByIdMicroserviceNotFound() {
        Long id = 1L;
        when(_microserviceRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(MicroserviceNotFoundException.class, () -> {
            _microserviceService.findById(id);
        });
    }

    @Test
    public void testFindAll() {
        Microservice microservice1 = new Microservice();
        Microservice microservice2 = new Microservice();
        List<Microservice> microservices = Arrays.asList(microservice1, microservice2);

        when(_microserviceRepository.findAll()).thenReturn(microservices);

        List<MicroserviceDTO> result = _microserviceService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testCreateMicroservice() {
        MicroserviceDTO microserviceDTO = new MicroserviceDTO();
        microserviceDTO.setName("Gabriel");

        when(_microserviceRepository.findByName("Gabriel")).thenReturn(Optional.empty());

        when(_modelMapper.map(any(), eq(MicroserviceDTO.class))).thenReturn(microserviceDTO);

        MicroserviceDTO result = _microserviceService.createMicroservice(microserviceDTO);

        assertEquals("Gabriel", result.getName());
        verify(_microserviceRepository, times(1)).save(any());
    }

    @Test
    public void testCreateMicroserviceAlreadyExists() {
        MicroserviceDTO microserviceDTO = new MicroserviceDTO();
        microserviceDTO.setName("Microservice C");

        when(_microserviceRepository.findByName("Microservice C")).thenReturn(Optional.of(new Microservice()));

        assertThrows(MicroserviceAlreadyExistsExcept.class, () -> {
            _microserviceService.createMicroservice(microserviceDTO);
        });

        verify(_microserviceRepository, never()).save(any());
    }
}