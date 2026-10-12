package mx.edu.utez.segundoProyecto.controller;


import jakarta.validation.Valid;
import mx.edu.utez.segundoProyecto.dto.envio.EnvioRequestDto;
import mx.edu.utez.segundoProyecto.dto.envio.EnvioResponseDto;
import mx.edu.utez.segundoProyecto.service.EnvioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/segundoProyecto/envio")
@CrossOrigin("*")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @PostMapping
    public ResponseEntity<EnvioResponseDto> cotizar(@RequestBody @Valid EnvioRequestDto payload) {
        return ResponseEntity.status(200).body(envioService.calcularCotizacion(payload));
    }
}