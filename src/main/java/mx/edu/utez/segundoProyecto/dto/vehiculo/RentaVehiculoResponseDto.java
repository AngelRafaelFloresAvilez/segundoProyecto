package mx.edu.utez.segundoProyecto.dto.vehiculo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentaVehiculoResponseDto {
    private String nombreCliente;
    private String tipoVehiculo;
    private int diasRenta;
    private double costoRentaBase;
    private double descuentoLargaRenta;
    private double cargoKmsAdicionales;
    private double cargoPorEdad;
    private double cargoSeguro;
    private double totalPagar;
}