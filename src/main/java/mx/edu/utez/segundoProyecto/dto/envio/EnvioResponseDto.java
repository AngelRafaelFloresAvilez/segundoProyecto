package mx.edu.utez.segundoProyecto.dto.envio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnvioResponseDto {
    private String codigoPostal;
    private String tipoEnvio;
    private double volumenCm3;
    private double costoBase;
    private double costoPeso;
    private double recargoVolumen;
    private double recargoTipoEnvio;
    private double costoSeguro;
    private double costoTotal;
}