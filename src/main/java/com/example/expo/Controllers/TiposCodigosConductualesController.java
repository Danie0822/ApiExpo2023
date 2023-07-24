package com.example.expo.Controllers;

import com.example.expo.Models.Encargados;
import com.example.expo.Models.Especialidades;
import com.example.expo.Models.ServiceResponse;
import com.example.expo.Models.TiposCodigosConductuales;
import com.example.expo.Services.EncargadosDB;
import com.example.expo.Services.EspecialidadesDB;
import com.example.expo.Services.TiposCodigosConductualesDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("TiposCodigosConductuales")
public class TiposCodigosConductualesController {
    @GetMapping("/list")
    public List<?> obtenerGrupos() {
        CompletableFuture<List<?>> futureEspecialidades = new TiposCodigosConductualesDB().obtenerTiposCodigosConductualesAsync();
        List<?> TiposCodigosConductuales = futureEspecialidades.join();
        return TiposCodigosConductuales;
    }

    @GetMapping("/get/{id}")
    public String getTipoCodigoConductual(@PathVariable int id){
        CompletableFuture<String> futureTipoCodigoConductual = new TiposCodigosConductualesDB().getTipoCodigoConductualAsync(id);
        String tipoCodigoConductual = futureTipoCodigoConductual.join();
        return tipoCodigoConductual;
    }

    @GetMapping("/getName/{name}")
    public Integer getTipoCodigoConductualName(@PathVariable String name){
        CompletableFuture<Integer> futureIdTipoCodigoConductual = new TiposCodigosConductualesDB().getTipoCodigoConductualNameAsync(name);
        Integer idTipoCodigoConductual = futureIdTipoCodigoConductual.join();
        return idTipoCodigoConductual;
    }
    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<ServiceResponse>> save(@RequestBody TiposCodigosConductuales TiposCodigosConductuales){
        CompletableFuture<Integer> futureResult= TiposCodigosConductualesDB.insertarTiposCodigosConductuales(TiposCodigosConductuales);

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
        CompletableFuture<Integer> futureResult = TiposCodigosConductualesDB.eliminarTiposCodigosConductualesAsync(id);

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
    public CompletableFuture<ResponseEntity<ServiceResponse>> update(@RequestBody TiposCodigosConductuales TiposCodigosConductuales) {
        CompletableFuture<Integer> futureResult = TiposCodigosConductualesDB.actualizarTiposCodigosConductuales(TiposCodigosConductuales);

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
