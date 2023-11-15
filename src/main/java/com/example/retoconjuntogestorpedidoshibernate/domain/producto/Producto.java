package com.example.retoconjuntogestorpedidoshibernate.domain.producto;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Producto")
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "cantidad_disponible")
    private Integer cantidad_disponible;

    @Override
    public String toString() {
        return nombre;
    }
}
