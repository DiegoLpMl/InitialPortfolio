package org.com.example.javainterface;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class LoginController {
    @FXML
    TextField txtUsuario;
    @FXML
    TextField passUsuario;
    @FXML
    protected TextField emailUsuario;
    @FXML
    protected Label txtMessage;

    @FXML
    protected void onLoginButtonClick() throws SQLException {
        UserDAO user = new UserDAO();

        String usuarioDigitado = txtUsuario.getText();
        String senhaDigitada = passUsuario.getText();
        String emailDigitado = emailUsuario.getText();

        boolean User_Login = UserDAO.User_Login(usuarioDigitado, senhaDigitada, emailDigitado);

        if(User_Login)
            txtMessage.setText("Login efetuado com sucesso");
        else{
            txtMessage.setText("usuario, Senha ou Email errados");
        }
    }
}
