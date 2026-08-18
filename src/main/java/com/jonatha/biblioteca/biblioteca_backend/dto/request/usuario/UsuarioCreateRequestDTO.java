package com.jonatha.biblioteca.biblioteca_backend.dto.request.usuario;

import org.hibernate.validator.constraints.br.CPF;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioCreateRequestDTO(

    @NotBlank(message = "O nome do usuário é obrigatório.")
    @Size(max = 100, message = "O nome do usuário deve ter no máximo 100 caracteres.")
    String nome,

    @NotBlank(message = "O CPF do usuário é obrigatório.")
    @CPF
    String cpf,

    @NotBlank(message = "O E-mail do usuário é obrigatório.")
    @Email(message = "Formato de e-mail inválido.")
    String email,

    @NotBlank(message = "O Número de celular do usuário é obrigatório.")
    @Pattern(regexp = "\\d{10,11}", message = "Celular deve ter 10 ou 11 dígitos.")
    String celular
) { }
