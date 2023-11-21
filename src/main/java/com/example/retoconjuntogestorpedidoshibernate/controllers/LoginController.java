package com.example.retoconjuntogestorpedidoshibernate.controllers;

import com.example.retoconjuntogestorpedidoshibernate.HelloApplication;
import com.example.retoconjuntogestorpedidoshibernate.Sesion;
import com.example.retoconjuntogestorpedidoshibernate.domain.usuario.Usuario;
import com.example.retoconjuntogestorpedidoshibernate.domain.usuario.UsuarioDAO;
import com.example.retoconjuntogestorpedidoshibernate.exceptions.UsuarioInexistente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button btnLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void login(ActionEvent actionEvent) {
        String user = userField.getText();
        String pass = passField.getText();
        Usuario usuario = null;

        try {
            usuario = (new UsuarioDAO()).validateUser(user, pass);
            Sesion.setUsuario(usuario);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("¡Hola!");
            alert.setHeaderText("Inicio correcto");
            alert.setContentText("Bienvenid@, " + usuario.getNombre() + ".");
            alert.showAndWait();
            HelloApplication.loadFXMLUsuario("pedidosUsuario-controller.fxml");
        } catch (UsuarioInexistente e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("¡Algo ha fallado!");
            alert.setContentText("Usuario no encontrado. Puede que el usuario no exista o la contraseña sea incorrecta.");
            alert.showAndWait();
            System.out.println("Usuario no encontrado.");
        }

    }
}

