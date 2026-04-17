package org.com.example.javainterface;

import java.security.SecureRandom;
import java.sql.SQLException;

public class resetarSenhaService {

    public static void solicitaReset(String email) throws SQLException {
        Usuario usuario = UserDAO.buscarEmail(email);
        if(usuario == null) {
            throw new RuntimeException("Usuario nao encontrado");
        }
        String codigo = String.format("%08d", new SecureRandom().nextInt(99999999));
        String codigoHash = SenhaUtil.hashSenha(codigo);

        UserDAO.salvarToken(email, codigoHash);

        String corpo = """
                Você solicitou a recuperação de senha.

                Seu código temporário é: %s

                Ele expira em 5 minutos.
                Se não foi você, ignore este email.
                """.formatted(codigo);

        EmailService.enviaEmail(email, "Recuperação de Senha", corpo);


    }
    public static void resetSenha(String novaSenha) throws SQLException {
        String email = Sessao.getEmailAtual();

    if(email == null) {
        throw new RuntimeException("Sessao expirada");
    }

    String novaSenhaHash = SenhaUtil.hashSenha(novaSenha);
    UserDAO.atualizarSenha(email, novaSenhaHash);
    UserDAO.deletarToken(email);
    Sessao.setEmailAtual(null);
    }


}
