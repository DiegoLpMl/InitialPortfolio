package org.com.example.javainterface;

import org.mindrot.jbcrypt.BCrypt;

public class SenhaUtil {
    public static String hashSenha(String codigo){
        return BCrypt.hashpw(codigo, BCrypt.gensalt());

    }

    public static boolean verificaSenha(String codigoDigitado, String codigoHash){
        if (codigoDigitado == null || codigoHash == null) return false;
        return BCrypt.checkpw(codigoDigitado, codigoHash);
    }
}
