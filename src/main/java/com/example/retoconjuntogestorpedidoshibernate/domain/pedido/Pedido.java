package com.example.retoconjuntogestorpedidoshibernate.domain.pedido;

import com.example.retoconjuntogestorpedidoshibernate.domain.item.Item;
import com.example.retoconjuntogestorpedidoshibernate.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Pedido")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "codigo_pedido")
    private String codigo_pedido;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "total")
    private Integer total;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    @Transient
    private List<Item> items = new ArrayList<>();
}
