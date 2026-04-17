package org.com.example.javainterface;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class EsqueciSenhaController {
    @FXML
    private PasswordField txtNovaSenha;
    @FXML
    private PasswordField txtConfirmarSenha;

    @FXML
    public void onClickConfirmarSenha() throws SQLException, IOException {
        String novaSenha = txtNovaSenha.getText();
        String confirmarSenha = txtConfirmarSenha.getText();

        if(!novaSenha.equals(confirmarSenha)) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("As senhas devem ser iguais");
            alerta.showAndWait();
            return;
        }
        if(novaSenha == null || novaSenha.isBlank()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Senha vazia");
            alerta.showAndWait();
            return;
        }

        resetarSenhaService.resetSenha(novaSenha);
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Senha alterada");
        alerta.setHeaderText("Senha alterada com sucesso!");
        alerta.showAndWait();
        HelloApplication.trocadorDeTelas("login.fxml");
    }

}


