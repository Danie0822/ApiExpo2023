package com.example.expo.Services;
import com.example.expo.Models.Telfonono;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@Service
public class TelefonoSMS {
    @Value("${twilio.account.sid}") // Configura esto en tu archivo application.properties
    private String accountSid;

    @Value("${twilio.auth.token}") // Configura esto en tu archivo application.properties
    private String authToken;

    public void enviarSMS(Telfonono tele) {
        Twilio.init(accountSid, authToken);

        Message message = Message.creator(
                        new PhoneNumber(tele.getNumero()),  // Número de destino en El Salvador
                        new PhoneNumber("+19493914806"),  // Reemplaza con tu número de Twilio
                        tele.getCode())
                .create();

        System.out.println("Mensaje SID: " + message.getSid());
    }
}
