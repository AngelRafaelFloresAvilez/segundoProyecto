package mx.edu.utez.segundoProyecto.controller;

import jakarta.validation.Valid;
import mx.edu.utez.segundoProyecto.dto.hospedaje.HospedajeRequestDto;
import mx.edu.utez.segundoProyecto.dto.hospedaje.HospedajeResponseDto;
import mx.edu.utez.segundoProyecto.service.HospedajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/segundoProyecto/hospedaje")
@CrossOrigin("*")
public class HospedajeController {

    private final HospedajeService hospedajeService;

    public HospedajeController(HospedajeService hospedajeService) {
        this.hospedajeService = hospedajeService;
    }

    @PostMapping
    public ResponseEntity<HospedajeResponseDto> cotizar(@RequestBody @Valid HospedajeRequestDto payload) {
        return ResponseEntity.status(200).body(hospedajeService.calcularHospedaje(payload));
    }
}