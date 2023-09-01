package com.example.expo.Controllers;

import com.example.expo.Models.Encargados;
import com.example.expo.Models.RangosHoras;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Services.EncargadosDB;
import com.example.expo.Services.RangosHorasDB;
import com.example.expo.Services.SalonesDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("RangoHoras")
public class RangosHorasController {
    @GetMapping("/list")
    public List<?> obtenerGrupos() {
        CompletableFuture<List<?>> futureEspecialidades = new RangosHorasDB().obtenerRangosHorasAsync();
        List<?> RangosHoras = futureEspecialidades.join();
        return RangosHoras;
    }
    @GetMapping("/Search/{idEstudiante}")
    public List<?> obtenerCodigosConductualesStringPorEstudianteAsync(@PathVariable String idEstudiante) {
        CompletableFuture<List<?>> futureResult = new RangosHorasDB().BuscarCodigoConductualesAsync(idEstudiante);
        List<?> CodigosConductuales = futureResult.join();
        return CodigosConductuales;
    }
    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody RangosHoras RangosHoras) {
        CompletableFuture<Integer> futureResult = RangosHorasDB.insertarRangosHorasAsync(RangosHoras);

        return futureResult.thenApply(result -> {
            ServiceResponse serviceResponse = new ServiceResponse();

            if (result == 1) {
                serviceResponse.setMessage("Item saved with success");
                return ResponseEntity.ok(serviceResponse);
            } else {
                serviceResponse.setMessage("Failed to save item");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponse);
            }
        });
    }
    @DeleteMapping("/delete/{id}")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@PathVariable int id) {
        CompletableFuture<Integer> futureResult = RangosHorasDB.eliminarRangosHorasAsync(id);

        return futureResult.thenApply(result -> {
            ServiceResponse serviceResponse = new ServiceResponse();

            if (result == 1) {
                serviceResponse.setMessage("Item removed with success");
                return ResponseEntity.ok(serviceResponse);
            } else {
                serviceResponse.setMessage("Failed to remove item");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponse);
            }
        });
    }
    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody RangosHoras RangosHoras) {
        CompletableFuture<Integer> futureResult = RangosHorasDB.actualizarRangosHorasAsync(RangosHoras);

        return futureResult.thenApply(result -> {
            ServiceResponse serviceResponse = new ServiceResponse();

            if (result == 1) {
                serviceResponse.setMessage("Item updated with success");
                return ResponseEntity.ok(serviceResponse);
            } else {
                serviceResponse.setMessage("Failed to update item");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponse);
            }
        });
    }

}
