package org.com.example.javainterface;

import org.mindrot.jbcrypt.BCrypt;

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

                    if (BCrypt.checkpw(senha, senhaDoBanco))
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

    public static Usuario buscarEmail(String email) throws SQLException{
        String sql = "SELECT * From User_Sys WHERE email = ?";

        try (Connection conn = DTBConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setToken(rs.getString("token"));
                return usuario;
            }
            //isso aqui e pra caso ele nao encontre o usuario ai ele da um erro de compilacao ja que o User nao foi encontrado
        return null;

        }catch (SQLException e){
            throw new RuntimeException("Erro ao buscar usuario" + e.getMessage(), e);
        }


    }

    public static void salvarToken(String email, String codigoHash) throws SQLException{
        String sql = "UPDATE User_Sys SET token = ? WHERE email = ?";

        try(Connection conn = DTBConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, codigoHash);
            stmt.setString(2, email);
            stmt.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException("Erro ao salvar token");
        }
    }

    public static void atualizarSenha(String email, String NovaSenhaHash) throws SQLException {
        String sql = "UPDATE User_Sys SET senha = ? WHERE email = ?";

        try(Connection conn = DTBConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, NovaSenhaHash);
            stmt.setString(2, email);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Erro ao atualizar senha");
        }


    }
    public static void deletarToken(String email) throws SQLException{
        String sql = "UPDATE User_Sys SET token = null WHERE email = ?";

        try(Connection conn = DTBConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, email);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Erro ao deletar token");
        }
    }
}
