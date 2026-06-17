package com.jonatha.biblioteca.biblioteca_backend.dto.request;

import com.jonatha.biblioteca.biblioteca_backend.model.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(

    @NotBlank(message = "O nome do usuário é obrigatório.")
    @Size(min = 5, max = 100, message = "O nome do usuário deve ter entreo 20 e 100 caracteres.")
    String nome,

    @NotBlank(message = "O CPF do usuário é obrigatório.")
    String cpf,

    @NotBlank(message = "O E-mail do usuário é obrigatório.")
    String email,

    @NotBlank(message = "O Número de celular do usuário é obrigatório.")
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

    public void updateUsuario(Usuario entity) {
        entity.setNome(this.nome);
        entity.setCpf(this.cpf);
        entity.setEmail(this.email);
        entity.setCelular(this.celular);
    }


}
