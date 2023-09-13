package br.com.ms.authandauto.dto;

public record UserRequestDTO(String name, String email, String password, boolean admin) {
}
