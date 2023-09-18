package br.com.ms.scholard.controller;

import br.com.ms.scholard.entity.MsScholarDBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/ms-d")
public class ScholarDController {

    @GetMapping("/api/scholar/{userId}/microsservice/{msId}")
    public MsScholarDBean getUserByIDAndMS (@PathVariable Long userId, @PathVariable Long msId) {
        return new MsScholarDBean(1L,1L);
    }


    @GetMapping("/api/scholar/microsservice/{msId}")
    public MsScholarDBean getUsersByMS (@PathVariable Long msId) {
        return new MsScholarDBean(1L,1L);
    }
}
