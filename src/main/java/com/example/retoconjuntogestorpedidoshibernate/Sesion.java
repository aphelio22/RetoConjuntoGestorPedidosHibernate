package com.example.retoconjuntogestorpedidoshibernate;

import com.example.retoconjuntogestorpedidoshibernate.domain.item.Item;
import com.example.retoconjuntogestorpedidoshibernate.domain.pedido.Pedido;
import com.example.retoconjuntogestorpedidoshibernate.domain.producto.Producto;
import com.example.retoconjuntogestorpedidoshibernate.domain.usuario.Usuario;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * La clase Sesion almacena información sobre la sesión actual del usuario, incluyendo el pedido actual, la posición
 * del pedido seleccionado en la tabla, el usuario, el listado de pedidos, los productos y los items.
 */
public class Sesion {

    /**
     * Posición del pedido seleccionado en la tabla.
     */
    @Getter
    @Setter
    private static Integer pos = null;

    /**
     * Usuario actual en la sesión.
     */
    @Getter
    @Setter
    private static Usuario usuario;

    /**
     * Pedido en la sesión.
     */
    @Getter
    @Setter
    private static Pedido pedido;

    /**
     * Lista de pedidos en la sesión.
     */
    @Getter
    @Setter
    private static ArrayList<Pedido> pedidos = new ArrayList<>();

    /**
     * Lista de productos en la sesión.
     */
    @Getter
    @Setter
    private static ArrayList<Producto> productos = new ArrayList<>();

    /**
     * Lista de items en la sesión.
     */
    @Getter
    @Setter
    private static ArrayList<Item> items = new ArrayList<>();
}