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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la funcionalidad de añadir ítems a un pedido.
 */
public class AnhadirItemController implements Initializable {

    /**
     * Spinner con la cantidad de un mismo producto que se va añadir al pedido.
     */
    @FXML
    private Spinner<Integer> spCantidad;

    /**
     * Combo Box que contiene todos los productos que se pueden agregar a un item y por consiguiente a un pedido.
     */
    @FXML
    private ComboBox<Producto> comboProducto;

    /**
     * Observable que contiene una lista de todos los productos disponibles, se usa para añadirlos al Combo Box.
     */
    private ObservableList<Producto> observableListProductos;

    /**
     * Inicializa el controlador y establece los valores iniciales.
     *
     * @param url            La URL de inicialización.
     * @param resourceBundle El ResourceBundle utilizado para la inicialización.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Observable que se usará para gestionar una lista de productos disponibles en un Combo Box.
        observableListProductos = FXCollections.observableArrayList();
        //Se crea un nuevo ProductoDAO.
        ProductoDAO productoDAO = new ProductoDAO();
        //Se rellena el Observable con todos los productos.
        observableListProductos.setAll(productoDAO.getAll());
        //Se carga el Combo Box con el Observable.
        comboProducto.setItems(observableListProductos);
        //Se selecciona el primer producto del Combo Box por defecto.
        comboProducto.getSelectionModel().selectFirst();
        //Se establece el Spinner para que solo pueda llegar hasta 100 con paso 1, teniendo como predeterminado el 1.
        spCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1));
    }

    /**
     * Método para agregar un ítem al pedido actual.
     *
     * @param actionEvent El evento de acción que desencadena la operación.
     */
    @FXML
    public void aceptar(ActionEvent actionEvent) {

        //Se crea una instancia de Pedido con el pedido actual de la sesión.
        Pedido pedido = Sesion.getPedido();

        //Si el pedido es distinto de nulo se crea un nuevo item para ese pedido  y se retorna a la ventana de DetallesPedidoController.
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

    /**
     * Método para cerrar la sesión actual y cargar la pantalla de inicio de sesión.
     *
     * @param actionEvent El evento de acción que desencadena la operación.
     */
    @FXML
    public void logOut(ActionEvent actionEvent) {
        //Se settea el usuario actual a null y vuelve al LoginController.
        Sesion.setUsuario(null);
        HelloApplication.loadFXMLLogin("login-controller.fxml");
    }

    /**
     * Método para mostrar información "Acerca de".
     *
     * @param actionEvent El evento de acción que desencadena la operación.
     */
    @FXML
    public void mostrarAcercaDe(ActionEvent actionEvent) {
        // Muestra información "Acerca de" en una ventana de diálogo.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de: ");
        alert.setHeaderText("Creado por: ");
        alert.setContentText("Jorge Alarcón Navarro, 2ºDAM");
        alert.showAndWait();
    }

    /**
     * Método para volver atrás.
     *
     * @param actionEvent El evento de acción que desencadena la operación.
     */
    @FXML
    public void volverAtrás(ActionEvent actionEvent) {
        //Vuelve a la pantalla inmediatamente anterior.
        HelloApplication.loadFXMLDetalles("detallesPedido-controller.fxml");
    }
}
