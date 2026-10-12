package mx.edu.utez.segundoProyecto.dto.vehiculo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentaVehiculoRequestDto {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotNull(message = "La edad del conductor es obligatoria")
    @Min(value = 1, message = "La edad debe ser mayor a 0")
    private Integer edadConductor;

    @NotBlank(message = "El tipo de vehículo es obligatorio")
    @Pattern(regexp = "^(COMPACTO|SEDAN|SUV|CAMIONETA)$", message = "El tipo de vehículo debe ser COMPACTO, SEDAN, SUV o CAMIONETA")
    private String tipoVehiculo;

    @NotNull(message = "El número de días de renta es obligatorio")
    @Min(value = 1, message = "Los días de renta deben ser al menos 1")
    private Integer diasRenta;

    @NotNull(message = "Los kilómetros estimados son obligatorios")
    @PositiveOrZero(message = "Los kilómetros estimados no pueden ser negativos")
    private Double kilometrosEstimados;

    @NotNull(message = "La indicación del seguro es obligatoria")
    private Boolean seguroCompleto;
}