package com.jonatha.biblioteca.biblioteca_backend.utils;

public class TextUtils {
    private TextUtils() {
    
    }
    
    /**
     * @param valor
     */

    public static String tratarTexto(String valor) {
        if (valor == null) {
            return null;
        }
        return valor.trim().replaceAll("\\s+", " ").toUpperCase();
    }
}
