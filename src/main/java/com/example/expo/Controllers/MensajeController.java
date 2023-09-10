package com.example.expo.Controllers;

import com.example.expo.Models.Correo;
import com.example.expo.Models.Telfonono;
import com.example.expo.Services.TelefonoSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    @Autowired
    private TelefonoSMS mensajeService;

    @PostMapping("/enviar-sms")
    public ResponseEntity<String> enviarSMS(@RequestBody Telfonono Tele) {
        // Llama al servicio para enviar un SMS
        mensajeService.enviarSMS(Tele);
        return ResponseEntity.ok("SMS enviado con éxito");
    }


}

