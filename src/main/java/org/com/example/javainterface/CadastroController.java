package org.com.example.javainterface;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import java.io.IOException;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;


public class CadastroController {

    @FXML
    TextField txtNome;
    @FXML
    TextField txtSenha;
    @FXML
    TextField txtEmail;
    @FXML
    Label lblMensagem;

    public void onClickCadastrar() throws SQLException,IOException {

        UserDAO user = new UserDAO();

        String nome = txtNome.getText();
        String senha = txtSenha.getText();
        String email = txtEmail.getText();
        String getHash = BCrypt.hashpw(senha, BCrypt.gensalt(12));
        user.User_Create(nome, getHash, email);

        lblMensagem.setText("Usuario Cadastrado com sucesso!");

        if(nome.isEmpty() || senha.isEmpty() || email.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Preencha todos os campos");
            alerta.setHeaderText("Preencha todos os campos");
            alerta.showAndWait();
            TranslateTransition tt = new TranslateTransition(Duration.seconds(4), lblMensagem);
        }
        else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Usuario Cadastrado com sucesso!");
            HelloApplication.trocadorDeTelas("login.fxml");
            alerta.setHeaderText("Usuario Cadastrado com sucesso!");
            txtNome.clear();
            txtSenha.clear();
            txtEmail.clear();
            alerta.showAndWait();
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(2), lblMensagem);
        }



    }

}
