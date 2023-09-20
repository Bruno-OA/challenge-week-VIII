package br.com.ms.scholarb.apresentation.controller;

import br.com.ms.scholarb.application.service.MicroserviceService;
import br.com.ms.scholarb.domain.model.microsservice.MicroserviceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/scholar/")
public class MicroserviceController {

    private final MicroserviceService _microserviceService;

    @Autowired
    public MicroserviceController(MicroserviceService microserviceService) {
        _microserviceService = microserviceService;
    }

    @GetMapping(value = "microservice/{microserviceId}")
    public ResponseEntity<MicroserviceResponse> getMicroserviceByIdAndUsersPermissions(@PathVariable Long microserviceId) {
        MicroserviceResponse microserviceResponse = _microserviceService
                .getMicroserviceByIdAndUsersPermissions(microserviceId);
        return ResponseEntity.ok(microserviceResponse);
    }


}
