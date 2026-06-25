package com.jonatha.biblioteca.biblioteca_backend.dto.request.usuario;

import com.jonatha.biblioteca.biblioteca_backend.model.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioCreateRequestDTO(

    @NotBlank(message = "O nome do usuário é obrigatório.")
    @Size(min = 5, max = 100, message = "O nome do usuário deve ter entre 5 e 100 caracteres.")
    String nome,

    @NotBlank(message = "O CPF do usuário é obrigatório.")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 digitos númericos.")
    String cpf,

    @NotBlank(message = "O E-mail do usuário é obrigatório.")
    @Email(message = "Formato de e-mail inválido.")
    String email,

    @NotBlank(message = "O Número de celular do usuário é obrigatório.")
    @Pattern(regexp = "\\d{10,11}", message = "Celular deve ter 10 ou 11 dígitos.")
    String celular
) {
    public Usuario createUsuario() {
        Usuario entity = new Usuario();
        
        entity.setNome(this.nome);
        entity.setCpf(this.cpf);
        entity.setEmail(this.email);
        entity.setCelular(this.celular);
        
        return entity;
    }
}
