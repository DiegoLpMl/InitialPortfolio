package org.com.example.javainterface;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;


public class EmailService {

    //aqui e pra configurar o acesso ao email do sistema
   private static final String EMAIL_HOST = "initialprojectengentech@gmail.com";
   private static final String EMAIL_PASS = "tjowzponsjuwvenz";

    //aqui eu vou configurar a sessão do SMTP(seja la oq significa iniciar sessao SMTP)
    private static Session startSessao(){

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

    //aq ele faz a autenticacao dos dados passados nas variaveis finais email host e email pass(ta meio complicado mas eu acho que to conseguindo)
        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_HOST, EMAIL_PASS);
            }
        });


    }
    public static void enviaEmail(String destinario, String assunto, String corpo){

        try{ Session sessao = startSessao();

            Message mensagem = new MimeMessage(sessao);
            mensagem.setFrom(new InternetAddress(EMAIL_HOST));
            mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinario));
            mensagem.setSubject(assunto);
            mensagem.setText(corpo);
            //pega toda a configuracao feita acima e envia como mensagem o que for passado no parametro(ta ficando confuso mas e porque eu to fazendo o metodo ainda de fazer as classes controllers)
            Transport.send(mensagem);
            IO.println("Email enviado com sucesso para: " + destinario);


        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar email:"+ e.getMessage(),   e);
        }





    }



}
