package mx.edu.utez.segundoProyecto.service;

import mx.edu.utez.segundoProyecto.dto.envio.EnvioRequestDto;
import mx.edu.utez.segundoProyecto.dto.envio.EnvioResponseDto;
import mx.edu.utez.segundoProyecto.exception.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {

    public EnvioResponseDto calcularCotizacion(EnvioRequestDto dto) {
        if (dto.getPesoKg() > 50) {
            throw new CustomBadRequestException("No se aceptan paquetes que pesen más de 50 kg");
        }
        if (dto.getLargoCm() > 150 || dto.getAnchoCm() > 150 || dto.getAltoCm() > 150) {
            throw new CustomBadRequestException("No se aceptan paquetes con alguna dimensión superior a 150 cm");
        }

        double volumen = dto.getLargoCm() * dto.getAnchoCm() * dto.getAltoCm();
        if (volumen > 1000000) {
            throw new CustomBadRequestException("No se aceptan paquetes con un volumen superior a 1,000,000 cm³");
        }

        double costoBase = 80.0;
        double costoPeso = dto.getPesoKg() * 12.0;
        double acumulado = costoBase + costoPeso;

        double recargoVolumen = 0.0;
        if (volumen > 50000) {
            recargoVolumen = 100.0;
            acumulado += recargoVolumen;
        }

        double recargoTipoEnvio = 0.0;
        if ("EXPRESS".equalsIgnoreCase(dto.getTipoEnvio())) {
            recargoTipoEnvio = acumulado * 0.40;
            acumulado += recargoTipoEnvio;
        } else if ("MISMO_DIA".equalsIgnoreCase(dto.getTipoEnvio())) {
            recargoTipoEnvio = acumulado * 0.70;
            acumulado += recargoTipoEnvio;
        }

        double costoSeguro = 0.0;
        if (dto.getValorDeclarado() > 10000) {
            costoSeguro = dto.getValorDeclarado() * 0.02;
        }

        double costoTotal = acumulado + costoSeguro;

        return new EnvioResponseDto(
                dto.getCodigoPostal(),
                dto.getTipoEnvio(),
                volumen,
                costoBase,
                costoPeso,
                recargoVolumen,
                recargoTipoEnvio,
                costoSeguro,
                costoTotal
        );
    }
}