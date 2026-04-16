package org.com.example.javainterface;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.mindrot.jbcrypt.BCrypt;


import java.io.IOException;
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
    protected void onLoginButtonClick() throws SQLException, IOException {


        UserDAO user = new UserDAO();

        String usuarioDigitado = txtUsuario.getText();
        String senhaDigitada = passUsuario.getText();
        String emailDigitado = emailUsuario.getText();


        boolean User_Login = UserDAO.User_Login(usuarioDigitado, senhaDigitada, emailDigitado);

        if(User_Login)
            HelloApplication.trocadorDeTelas("cadastro.fxml");
        else{
            txtMessage.setText("usuario, Senha ou Email errados");
        }
    }

    @FXML
    private Button loginButton;

    @FXML
    public void initialize() {

        loginButton.setOnMousePressed(e -> {
            TranslateTransition tt = new TranslateTransition(Duration.millis(100), loginButton);
            tt.setToY(10);
            tt.play();
        });

        loginButton.setOnMouseReleased(e -> {
            TranslateTransition tt = new TranslateTransition(Duration.millis(100), loginButton);
            tt.setToY(0);
            tt.play();
        });
    }

    @FXML
    protected void onNovoPorAquiClick() throws IOException {

        HelloApplication.trocadorDeTelas("cadastro.fxml");
    }

}

