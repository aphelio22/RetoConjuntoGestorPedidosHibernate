package com.example.retoconjuntogestorpedidoshibernate.domain.item;

import com.example.retoconjuntogestorpedidoshibernate.domain.producto.Producto;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "codigo_pedido")
    private String codigo_pedido;
    @Column(name = "cantidad")
    private String cantidad;

    @OneToOne
    @JoinColumn(name = "producto")
    private Producto producto;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", codigo_pedido='" + codigo_pedido + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", producto=" + producto +
                '}';
    }
}
