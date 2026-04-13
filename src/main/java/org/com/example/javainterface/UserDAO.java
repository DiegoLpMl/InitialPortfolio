package org.com.example.javainterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {
    public void User_Create(String nome, String senha, String email) throws SQLException {
        String sql = "INSERT INTO User_Sys (nome, senha, email) VALUES (?,?,?)";

        try (Connection conn = DTBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, senha);
            stmt.setString(3, email);
            stmt.executeUpdate();
            IO.println("Usuario criado!");

        } catch (SQLException e) {
            throw new RuntimeException("Sem passagem de informacoes validas");
        }
    }

    public static boolean User_Login(String nomeDigitado, String senha, String emailDigitado) throws SQLException {
        String sql = "SELECT senha FROM User_Sys WHERE nome = ? and email = ?";

        try (Connection conn = DTBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeDigitado);
            stmt.setString(2, emailDigitado);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String senhaDoBanco = rs.getString("senha");

                    if (senha.equals(senhaDoBanco))
                        return true; // retorna o acesso do usuario
                    else {
                        return false; //senha incorretar
                    }
                }


            }catch(SQLException e){
                throw new RuntimeException("usuario nao encontrado");
            }
        }
        return false; // usuario nao econtrado
    }
}
