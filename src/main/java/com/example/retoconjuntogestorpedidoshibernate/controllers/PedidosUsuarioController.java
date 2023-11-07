package com.example.retoconjuntogestorpedidoshibernate.controllers;



import com.example.retoconjuntogestorpedidoshibernate.HelloApplication;
import com.example.retoconjuntogestorpedidoshibernate.Sesion;
import com.example.retoconjuntogestorpedidoshibernate.domain.pedido.Pedido;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * La clase VentanaUsuarioController controla la lógica de la ventana de usuario.
 */
public class PedidosUsuarioController implements Initializable {

    @javafx.fxml.FXML
    private Label lbUsuario;
    @javafx.fxml.FXML
    private TableView tvPedidos;
    @javafx.fxml.FXML
    private TableColumn cIdPedido;
    @javafx.fxml.FXML
    private TableColumn cCPedido;
    @javafx.fxml.FXML
    private TableColumn cFecha;
    @javafx.fxml.FXML
    private TableColumn cUsuario;
    @javafx.fxml.FXML
    private TableColumn cTotal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
