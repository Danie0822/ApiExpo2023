package com.example.expo.Services;

import com.example.expo.Models.Correo;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MandarCorreo {

    private final JavaMailSender mailSender;

    public MandarCorreo(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public CompletableFuture<Void> enviarCodigoRecuperacionAsync(Correo correo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(correo.getCorreo());
        mensaje.setSubject("Código de recuperación");
        mensaje.setText("Tu código de recuperación es: " + correo.getCode());

        mailSender.send(mensaje);

        return CompletableFuture.completedFuture(null);
    }
}
