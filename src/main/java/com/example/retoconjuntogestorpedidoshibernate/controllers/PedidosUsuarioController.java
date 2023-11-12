package com.example.retoconjuntogestorpedidoshibernate.controllers;



import com.example.retoconjuntogestorpedidoshibernate.HelloApplication;
import com.example.retoconjuntogestorpedidoshibernate.Sesion;
import com.example.retoconjuntogestorpedidoshibernate.domain.pedido.Pedido;
import com.example.retoconjuntogestorpedidoshibernate.domain.pedido.PedidoDAO;
import com.example.retoconjuntogestorpedidoshibernate.domain.usuario.Usuario;
import com.example.retoconjuntogestorpedidoshibernate.domain.usuario.UsuarioDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * La clase VentanaUsuarioController controla la lógica de la ventana de usuario.
 */
public class PedidosUsuarioController implements Initializable {

    @javafx.fxml.FXML
    private Label lbUsuario;
    @javafx.fxml.FXML
    private TableView<Pedido> tvPedidos;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cIdPedido;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cCPedido;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cFecha;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cUsuario;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cTotal;
    private ObservableList<Pedido> observableList;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cIdPedido.setCellValueFactory((fila) -> {
            String id = String.valueOf(fila.getValue().getId());
            return new SimpleStringProperty(id);
        });
        cCPedido.setCellValueFactory((fila) -> {
            String cPedido = fila.getValue().getCodigo_pedido();
            return new SimpleStringProperty(cPedido);
        });
        cFecha.setCellValueFactory((fila) -> {
            String cFecha = fila.getValue().getFecha();
            return new SimpleStringProperty(cFecha);
        });
        cUsuario.setCellValueFactory((fila) -> {
            String cUsuario = String.valueOf(fila.getValue().getUsuario().getNombre());
            return new SimpleStringProperty(cUsuario);
        });
        cTotal.setCellValueFactory((fila) -> {
            String cTotal = String.valueOf(fila.getValue().getTotal() + "€");
            return new SimpleStringProperty(cTotal);
        });

        observableList = FXCollections.observableArrayList();

        Sesion.setUsuario((new UsuarioDAO()).get(Sesion.getUsuario().getId()));
        observableList.setAll(Sesion.getUsuario().getPedidos());

        tvPedidos.setItems(observableList);

        lbUsuario.setText("Bienvenid@: " + Sesion.getUsuario().getNombre());

        tvPedidos.getSelectionModel().selectedItemProperty().addListener((observableValue, pedido, t1) -> {
            Sesion.setPedido(t1);
            HelloApplication.loadFXMLDetalles("detallesPedido-controller.fxml");
        });


    }
    @javafx.fxml.FXML
    public void logOut(ActionEvent actionEvent) {
        Sesion.setUsuario(null);
        HelloApplication.loadFXMLLogin("login-controller.fxml");
    }

    @javafx.fxml.FXML
    public void mostrarAcercaDe(ActionEvent actionEvent) {
        // Muestra información "Acerca de" en una ventana de diálogo.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de: ");
        alert.setHeaderText("Creado por: ");
        alert.setContentText("Jorge Alarcón Navarro, 2ºDAM");
        alert.showAndWait();
    }
}
