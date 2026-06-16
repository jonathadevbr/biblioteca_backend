package com.jonatha.biblioteca.enums;

public enum StatusEmpretimos {
    ATIVO(1),
    RENOVADO(2),
    EM_ATRASO(3),
    DEVOLVIDO(4);

    private final int valor;

    StatusEmpretimos(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static StatusEmpretimos fromValor(Integer valor) {
        for (StatusEmpretimos tipo : values()) {
            if (tipo.valor == valor) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo inválido: " + valor);
    }


}
