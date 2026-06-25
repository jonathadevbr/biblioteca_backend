package com.jonatha.biblioteca.biblioteca_backend.dto.request.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateRequestDTO(
    
    @Size(min = 5, max = 100, message = "O nome do usuário deve ter entre 5 e 100 caracteres.")
    String nome,

    @Pattern(regexp = "\\d{10,11}", message = "Celular deve ter 10 ou 11 dígitos.")
    String celular,

    @Email(message = "Formato de e-mail inválido.")
    String email
) {
 
}