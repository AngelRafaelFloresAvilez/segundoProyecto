package mx.edu.utez.segundoProyecto.controller;

import jakarta.validation.Valid;
import mx.edu.utez.segundoProyecto.dto.vehiculo.RentaVehiculoRequestDto;
import mx.edu.utez.segundoProyecto.dto.vehiculo.RentaVehiculoResponseDto;
import mx.edu.utez.segundoProyecto.service.RentaVehiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/segundoProyecto/vehiculo")
@CrossOrigin("*")
public class RentaVehiculoController {

    private final RentaVehiculoService rentaVehiculoService;

    public RentaVehiculoController(RentaVehiculoService rentaVehiculoService) {
        this.rentaVehiculoService = rentaVehiculoService;
    }

    @PostMapping
    public ResponseEntity<RentaVehiculoResponseDto> cotizar(@RequestBody @Valid RentaVehiculoRequestDto payload) {
        return ResponseEntity.status(200).body(rentaVehiculoService.calcularRenta(payload));
    }
}