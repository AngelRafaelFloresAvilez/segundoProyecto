package mx.edu.utez.segundoProyecto.dto.hospedaje;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HospedajeResponseDto {
    private String nombreHuesped;
    private String tipoHabitacion;
    private double costoHospedajeBase;
    private double ajusteTemporada;
    private double costoDesayuno;
    private double costoEstacionamiento;
    private double descuentoLargaEstancia;
    private double subtotal;
    private double impuestoHospedaje;
    private double total;
}