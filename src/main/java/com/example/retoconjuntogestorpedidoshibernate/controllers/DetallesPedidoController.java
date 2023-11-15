package com.example.retoconjuntogestorpedidoshibernate.controllers;

import com.example.retoconjuntogestorpedidoshibernate.HelloApplication;
import com.example.retoconjuntogestorpedidoshibernate.Sesion;
import com.example.retoconjuntogestorpedidoshibernate.domain.item.Item;
import com.example.retoconjuntogestorpedidoshibernate.domain.item.ItemDAO;
import com.example.retoconjuntogestorpedidoshibernate.domain.pedido.PedidoDAO;
import com.example.retoconjuntogestorpedidoshibernate.domain.producto.Producto;
import jakarta.persistence.Id;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class DetallesPedidoController implements Initializable {

    @javafx.fxml.FXML
    private TableView<Item> tvItem;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cIdItem;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cCPedido;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cCantidad;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cProducto;
    @javafx.fxml.FXML
    private Label lbPrueba;
    private ObservableList<Item> observableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cIdItem.setCellValueFactory((fila) -> {
            String cIdItem = String.valueOf(fila.getValue().getId());
            return new SimpleStringProperty(cIdItem);
        });

        cCPedido.setCellValueFactory((fila) -> {
            String cCPedido = String.valueOf(fila.getValue().getCodigo_pedido().getCodigo_pedido());
            return new SimpleStringProperty(cCPedido);
        });

        cCantidad.setCellValueFactory((fila) -> {
            String cCantidad = String.valueOf(fila.getValue().getCantidad());
            return new SimpleStringProperty(cCantidad);
        });

        cProducto.setCellValueFactory((fila) -> {
            String cProducto = String.valueOf(fila.getValue().getProducto().getNombre() + "/" + fila.getValue().getProducto().getPrecio() + "€");
            return new SimpleStringProperty(cProducto);
        });

        observableList = FXCollections.observableArrayList();

        Sesion.setPedido((new PedidoDAO()).get(Sesion.getPedido().getId()));
        observableList.setAll(Sesion.getPedido().getItems());

        tvItem.setItems(observableList);

    }

    @Deprecated
    public void logOut(ActionEvent actionEvent) {
        Sesion.setUsuario(null);
        HelloApplication.loadFXMLLogin("login-controller.fxml");
    }
    @Deprecated
    public void mostrarAcercaDe(ActionEvent actionEvent) {
        // Muestra información "Acerca de" en una ventana de diálogo.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de: ");
        alert.setHeaderText("Creado por: ");
        alert.setContentText("Jorge Alarcón Navarro, 2ºDAM");
        alert.showAndWait();
    }
    @Deprecated
    public void volverAtrás(ActionEvent actionEvent) {
        HelloApplication.loadFXMLUsuario("pedidosUsuario-controller.fxml");
    }

    @javafx.fxml.FXML
    public void anhadirItem(ActionEvent actionEvent) {
        var item = new Item();
        Sesion.setItem(item);
        HelloApplication.loadFXMLCrearProducto("anhadirItemController.fxml");
    }

    @javafx.fxml.FXML
    public void eliminarItem(ActionEvent actionEvent) {
    }
}
