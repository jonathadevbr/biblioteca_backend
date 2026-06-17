package com.jonatha.biblioteca.enums;

public enum StatusEmprestimo {
    ATIVO(1),
    RENOVADO(2),
    EM_ATRASO(3),
    DEVOLVIDO(4);

    private final int valor;

    StatusEmprestimo(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static StatusEmprestimo fromValor(Integer valor) {
        for (StatusEmprestimo tipo : values()) {
            if (tipo.valor == valor) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo inválido: " + valor);
    }


}
