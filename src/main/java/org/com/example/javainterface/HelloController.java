package org.com.example.javainterface;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    TextField txtUsuario;
    @FXML
    PasswordField passUsuario;
    @FXML
    protected Label txtMessage;

    @FXML
    protected void onHelloButtonClick() {
        txtMessage.setText("Login Feito Com Sucesso");
    }
}
