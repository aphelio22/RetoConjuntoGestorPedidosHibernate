package com.example.retoconjuntogestorpedidoshibernate.exceptions;

/**
 * Esta excepción se lanza cuando se proporciona una contraseña incorrecta.
 */
public class ContrasenhaIncorrecta extends Exception {
    /**
     * Crea una nueva instancia de la excepción con el mensaje de error proporcionado.
     *
     * @param message El mensaje de error que describe la razón de la excepción.
     */
    public ContrasenhaIncorrecta(String message) {
        super(message);
    }
}
