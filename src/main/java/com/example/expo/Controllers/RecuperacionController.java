package com.example.expo.Controllers;
import com.example.expo.Models.CodigosConductuales;
import com.example.expo.Models.Correo;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.MandarCorreo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("MandarCorreo")
public class RecuperacionController {

    private final MandarCorreo emailService;

    public RecuperacionController(MandarCorreo emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/enviarCodigo")
    public CompletableFuture<ResponseEntity<ServiceResponse>> enviarCodigo(@RequestBody Correo correo) throws MessagingException {
        CompletableFuture<Void> emailFuture = emailService.
                enviarCodigoRecuperacionAsync(correo);

        return emailFuture.thenApplyAsync(v -> {
            ServiceResponse serviceResponse = new ServiceResponse();
            serviceResponse.setMessage("Código de recuperación enviado con éxito");
            return ResponseEntity.ok(serviceResponse);
        }).exceptionally(e -> {
            ServiceResponse serviceResponse = new ServiceResponse();
            serviceResponse.setMessage("Error al enviar el código de recuperación");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponse);
        });
    }

}
