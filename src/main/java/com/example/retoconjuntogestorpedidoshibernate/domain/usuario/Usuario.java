package com.example.retoconjuntogestorpedidoshibernate.domain.usuario;

import com.example.retoconjuntogestorpedidoshibernate.domain.pedido.Pedido;
import jakarta.persistence.*;
import javafx.scene.Scene;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "contrasenha")
    private String contrasenha;
    @Column(name = "email")
    private String email;

    //Cuidado esto tiene que ser paralelo al campo usuario de la tabla.
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Pedido> pedidos = new ArrayList<>(0);
}
