package br.com.ms.authandauto.apresentation.controller;

import br.com.ms.authandauto.application.dtos.MicroserviceDTO;
import br.com.ms.authandauto.application.interfaces.IMicroserviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api")
public class MicroserviceController {

    private final IMicroserviceService _microserviceService;

    @Autowired
    public MicroserviceController(IMicroserviceService microserviceService) {
        _microserviceService = microserviceService;
    }

    @PostMapping("/microservices")
    public ResponseEntity<MicroserviceDTO> createUser(@RequestBody MicroserviceDTO microserviceDTO)  {
        MicroserviceDTO microservice = _microserviceService.createMicroservice(microserviceDTO);
        return new ResponseEntity<>(microservice, HttpStatus.CREATED);
    }

    @GetMapping("/microservices/{id}")
    public ResponseEntity<MicroserviceDTO> findById(@PathVariable Long id){
        MicroserviceDTO microservice = _microserviceService.findById(id);
        return ResponseEntity.ok(microservice);
    }
}
