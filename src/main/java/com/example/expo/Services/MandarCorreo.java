package com.example.expo.Services;

import com.example.expo.Models.Correo;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.CompletableFuture;

@Service
public class MandarCorreo {

    private final JavaMailSender mailSender;

    public MandarCorreo(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public CompletableFuture<Void> enviarCodigoRecuperacionAsync(Correo correo) throws MessagingException {
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, "utf-8");
        helper.setTo(correo.getCorreo());
        helper.setSubject("Recuperación de Contraseña");

        // Estilos CSS en línea para el mensaje
        String contenido = "<html><head>"
                + "<style>"
                + "body { font-family: 'Poppins', sans-serif; background-color: #f7f9fc; color: #333; margin: 0; padding: 0; }"
                + ".container { max-width: 600px; margin: 0 auto; padding: 20px; border-radius: 10px; overflow: hidden; background-color: #fff; box-shadow: 0 10px 40px rgba(0, 0, 0, 0.1); animation: fadeIn 1s; }"
                + ".header { background: linear-gradient(to bottom, #2980b9, #3498db); text-align: center; padding: 60px 0; border-top-left-radius: 10px; border-top-right-radius: 10px; }"
                + ".content { padding: 40px; }"
                + ".code { font-size: 60px; color: #e74c3c; font-weight: bold; margin-top: 20px; letter-spacing: 3px; }"
                + ".info { font-size: 18px; color: #777; line-height: 1.5; }"
                + "@keyframes fadeIn { from { opacity: 0; } to { opacity: 1; } }"
                + "</style>"
                + "<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap' rel='stylesheet'>"
                + "</head><body>"
                + "<div class='container'>"
                + "<div class='header'>"
                + "<h1 style='color: white;'>Recuperación de Contraseña</h1>"
                + "<p style='color: white;'>¡Hola! Aquí tienes tu código de recuperación.</p>"
                + "</div>"
                + "<div class='content'>"
                + "<h2 style='color: #2980b9;'>Tu código de recuperación:</h2>"
                + "<p class='code'>" + correo.getCode() + "</p>"
                + "<p class='info'>Utiliza este código para recuperar tu contraseña en nuestro sistema de gestión estudiantil. Si no solicitaste este código, ignóralo.</p>"
                + "</div>"
                + "</div>"
                + "</body></html>";


        helper.setText(contenido, true); // true indica que el contenido es HTML

            mailSender.send(mensaje);
            return CompletableFuture.completedFuture(null);

    }

}
