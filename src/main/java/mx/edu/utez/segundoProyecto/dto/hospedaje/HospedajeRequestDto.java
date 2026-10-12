package mx.edu.utez.segundoProyecto.dto.hospedaje;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HospedajeRequestDto {

    @NotBlank(message = "El nombre del huésped es obligatorio")
    private String nombreHuesped;

    @NotBlank(message = "El tipo de habitación es obligatorio")
    @Pattern(regexp = "^(INDIVIDUAL|DOBLE|SUITE)$", message = "El tipo de habitación debe ser INDIVIDUAL, DOBLE o SUITE")
    private String tipoHabitacion;

    @NotNull(message = "El número de noches es obligatorio")
    @Min(value = 1, message = "El número de noches debe ser al menos 1")
    private Integer numeroNoches;

    @NotNull(message = "El número de huéspedes es obligatorio")
    @Min(value = 1, message = "El número de huéspedes debe ser al menos 1")
    private Integer numeroHuespedes;

    @NotBlank(message = "La temporada es obligatoria")
    @Pattern(regexp = "^(BAJA|REGULAR|ALTA)$", message = "La temporada debe ser BAJA, REGULAR o ALTA")
    private String temporada;

    @NotNull(message = "La indicación de desayuno es obligatoria")
    private Boolean incluyeDesayuno;

    @NotNull(message = "La indicación de estacionamiento es obligatoria")
    private Boolean incluyeEstacionamiento;
}