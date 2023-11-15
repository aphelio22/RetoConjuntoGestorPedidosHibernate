package com.example.retoconjuntogestorpedidoshibernate.controllers;



import com.example.retoconjuntogestorpedidoshibernate.HelloApplication;
import com.example.retoconjuntogestorpedidoshibernate.Sesion;
import com.example.retoconjuntogestorpedidoshibernate.domain.HibernateUtil;
import com.example.retoconjuntogestorpedidoshibernate.domain.item.Item;
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
import javafx.scene.input.MouseButton;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private PedidoDAO pedidoDAO = new PedidoDAO();



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

        cargarLista();

        lbUsuario.setText("Bienvenid@: " + Sesion.getUsuario().getNombre());

        tvPedidos.getSelectionModel().selectedItemProperty().addListener((observableValue, pedido, t1) -> {
            Sesion.setPedido(t1);
        });

        tvPedidos.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Pedido selectedPedido = tvPedidos.getSelectionModel().getSelectedItem();
                if (selectedPedido != null) {
                    Sesion.setPedido(selectedPedido);
                    HelloApplication.loadFXMLDetalles("detallesPedido-controller.fxml");
                }
            }
        });
    }

    private void cargarLista() {
        observableList.setAll(Sesion.getUsuario().getPedidos());
        for (Pedido pedido : observableList) {
            Double totalPedido = calcularTotalPedido(pedido);
                pedido.setTotal(totalPedido);

        }
        tvPedidos.setItems(observableList);
    }

    private Double calcularTotalPedido(Pedido pedido) {
        Double total  = 0.0;

        for (Item item : pedido.getItems()){
            total += item.getProducto().getPrecio() * item.getCantidad();
        }
        return total;
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

    @javafx.fxml.FXML
    public void anhadir(ActionEvent actionEvent) {
        Pedido nuevoPedido = new Pedido();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Obtener el último código de pedido
            Query<String> query = session.createQuery("select max(p.codigo_pedido) from Pedido p", String.class);
            String ultimoCodigoPedido = query.uniqueResult();

            // Incrementar el último código de pedido
            int ultimoNumero = Integer.parseInt(ultimoCodigoPedido.substring(4));
            int nuevoNumero = ultimoNumero + 1;
            String nuevoCodigoPedido = "PED-" + String.format("%03d", nuevoNumero);

            // Establecer el nuevo código de pedido en el pedido
            nuevoPedido.setCodigo_pedido(nuevoCodigoPedido);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Establecer la fecha actual por defecto
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormat.format(new Date());
        nuevoPedido.setFecha(fechaActual);

        nuevoPedido.setUsuario(Sesion.getUsuario());
        nuevoPedido.setId(0);

        if (nuevoPedido.getItems().isEmpty()) {
            nuevoPedido.setTotal(0.0);
        }

        // Agregar el nuevo pedido a la lista observable
        observableList.add(nuevoPedido);

        // Actualizar la tabla
        tvPedidos.setItems(observableList);
        Sesion.setPedido((new PedidoDAO()).save(nuevoPedido));
        Sesion.setPedido(nuevoPedido);
    }

    @javafx.fxml.FXML
    public void eliminar(ActionEvent actionEvent) {
        Pedido pedidoSeleccionado = tvPedidos.getSelectionModel().getSelectedItem();

        if (pedidoSeleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("¿Deseas borrar el pedido: " + pedidoSeleccionado.getCodigo_pedido() + "?");
            var result = alert.showAndWait().get();

            if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                pedidoDAO.delete(pedidoSeleccionado);
                observableList.remove(pedidoSeleccionado);
            }
        } else {
            // Mostrar un mensaje de error o advertencia al usuario si no se ha seleccionado ningún pedido para eliminar.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Por favor, selecciona un pedido para eliminar.");
            alert.showAndWait();
        }
    }
}
