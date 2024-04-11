package pousada.sistemareserva.login;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class emailUtil {

    public static void enviarEmailRedefinicaoSenha(String emailDestino, String token) throws MessagingException {
        // Configurações do servidor de e-mail
        String host = "seu.host.de.email";
        String porta = "587"; // Porta SMTP
        String usuario = "seu.email@gmail.com";
        String senha = "suaSenha";

        // Configurações de propriedades
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", porta);

        // Sessão de e-mail
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @SuppressWarnings("unused")
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, senha);
            }
        });

        try {
            // Criação da mensagem de e-mail
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuario)); // Remetente
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestino)); // Destinatário
            message.setSubject("Redefinição de Senha"); // Assunto
            message.setText("Olá,\n\nVocê solicitou a redefinição de senha. Clique no link abaixo para redefinir sua senha:\n\n"
                    + "http://suaaplicacao.com/redefinir-senha?token=" + token); // Corpo do e-mail

            // Enviar e-mail
            Transport.send(message);

            System.out.println("E-mail de redefinição de senha enviado com sucesso para " + emailDestino);
        } catch (MessagingException e) {
            throw new MessagingException("Erro ao enviar e-mail de redefinição de senha: " + e.getMessage());
        }
    }
}
