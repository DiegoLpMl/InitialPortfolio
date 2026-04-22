package org.com.example.javainterface.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.com.example.javainterface.*;
import org.com.example.javainterface.DTBClasses.UserDAO;
import org.com.example.javainterface.DTBClasses.Usuario;
import org.com.example.javainterface.Services.SenhaService;
import org.com.example.javainterface.Services.SessaoService;
import org.com.example.javainterface.Services.resetarSenhaService;

import java.io.IOException;
import java.sql.SQLException;


public class emailController {
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCodigo;
    @FXML
    private Label lblMensagem1;
    @FXML
    private Label lblMensagem2;

    public void OnconfirmarEmailClick() throws SQLException {
        String email = txtEmail.getText();
        lblMensagem1.getText();

        if (email == null || email.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Email vazio");
            alert.showAndWait();
            return;
        }
        Usuario usuario = UserDAO.buscarEmail(email);

        if (usuario == null || !usuario.getEmail().equals(email)) {
            // Email NÃO encontrado → inválido
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Email inválido ou não cadastrado.");
            alert.showAndWait();
            return;
        }
        else {
            SessaoService.setEmailAtual(email); // salva o email na sessão
            resetarSenhaService.solicitaReset(email);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Email enviado");
            alert.setHeaderText("Email enviado com sucesso!");
            alert.showAndWait();
        }
    }

    public void OnConfirmarCodigoClick() throws SQLException, IOException {
        String codigo = txtCodigo.getText();
        String email = txtEmail.getText();

        if (codigo == null || codigo.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Codigo vazio");
            alert.showAndWait();
            return;
        }
        Usuario usuario = UserDAO.buscarEmail(email);

        if (usuario == null || usuario.getToken() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Codigo invalido");
        }

        boolean verifica = SenhaService.verificaSenha(codigo, usuario.getToken());

        if(verifica) {
            HelloApplication.trocadorDeTelas("esqueciSenha.fxml");
            lblMensagem2.setText("Codigo valido");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Codigo invalido");
            alert.showAndWait();
        }
    }
    }

