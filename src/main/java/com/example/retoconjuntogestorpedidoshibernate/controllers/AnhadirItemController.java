package com.example.retoconjuntogestorpedidoshibernate.controllers;

import com.example.retoconjuntogestorpedidoshibernate.HelloApplication;
import com.example.retoconjuntogestorpedidoshibernate.Sesion;
import com.example.retoconjuntogestorpedidoshibernate.domain.item.Item;
import com.example.retoconjuntogestorpedidoshibernate.domain.item.ItemDAO;
import com.example.retoconjuntogestorpedidoshibernate.domain.pedido.Pedido;
import com.example.retoconjuntogestorpedidoshibernate.domain.pedido.PedidoDAO;
import com.example.retoconjuntogestorpedidoshibernate.domain.producto.Producto;
import com.example.retoconjuntogestorpedidoshibernate.domain.producto.ProductoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AnhadirItemController implements Initializable {

    @javafx.fxml.FXML
    private Spinner<Integer> spCantidad;
    @javafx.fxml.FXML
    private ComboBox<Producto> comboProducto;
    private ObservableList<Producto> observableListProductos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableListProductos = FXCollections.observableArrayList();
        ProductoDAO productoDAO = new ProductoDAO();
        observableListProductos.setAll(productoDAO.getAll());
        comboProducto.setItems(observableListProductos);
        spCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1));
    }

    @javafx.fxml.FXML
    public void aceptar(ActionEvent actionEvent) {
        Pedido pedido = Sesion.getPedido();
        if (pedido != null) {
            Item item = new Item();
            item.setCodigo_pedido(pedido);
            item.setCantidad(spCantidad.getValue());
            item.setProducto(comboProducto.getSelectionModel().getSelectedItem());

           Sesion.setItem((new ItemDAO()).save(item));
           Sesion.setItem(item);
           HelloApplication.loadFXMLDetalles("detallesPedido-controller.fxml");
        }
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

    @javafx.fxml.FXML
    public void volverAtrás(ActionEvent actionEvent) {
        HelloApplication.loadFXMLDetalles("detallesPedido-controller.fxml");
    }
}
