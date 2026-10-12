package mx.edu.utez.segundoProyecto.service;


import mx.edu.utez.segundoProyecto.dto.vehiculo.RentaVehiculoRequestDto;
import mx.edu.utez.segundoProyecto.dto.vehiculo.RentaVehiculoResponseDto;
import mx.edu.utez.segundoProyecto.exception.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class RentaVehiculoService {

    public RentaVehiculoResponseDto calcularRenta(RentaVehiculoRequestDto dto) {
        if (dto.getEdadConductor() < 18) {
            throw new CustomBadRequestException("No se acepta la renta: el conductor es menor de 18 años");
        }
        if (dto.getDiasRenta() > 30) {
            throw new CustomBadRequestException("No se acepta la renta: supera los 30 días");
        }
        if (dto.getKilometrosEstimados() > 5000) {
            throw new CustomBadRequestException("No se acepta la renta: los kilómetros estimados superan 5,000 km");
        }
        if ("CAMIONETA".equalsIgnoreCase(dto.getTipoVehiculo()) && dto.getEdadConductor() < 25) {
            throw new CustomBadRequestException("No se acepta la renta: se solicita CAMIONETA y el conductor tiene menos de 25 años");
        }

        double costoDiario = switch (dto.getTipoVehiculo().toUpperCase()) {
            case "COMPACTO" -> 550.0;
            case "SEDAN" -> 700.0;
            case "SUV" -> 950.0;
            case "CAMIONETA" -> 1200.0;
            default -> 0.0;
        };

        double costoRentaBase = costoDiario * dto.getDiasRenta();

        double kmsIncluidos = dto.getDiasRenta() * 100.0;
        double cargoKmsAdicionales = 0.0;
        if (dto.getKilometrosEstimados() > kmsIncluidos) {
            cargoKmsAdicionales = (dto.getKilometrosEstimados() - kmsIncluidos) * 4.0;
        }

        double cargoPorEdad = 0.0;
        if (dto.getEdadConductor() >= 18 && dto.getEdadConductor() <= 24) {
            cargoPorEdad = (costoRentaBase + cargoKmsAdicionales) * 0.15;
        }

        double cargoSeguro = 0.0;
        if (Boolean.TRUE.equals(dto.getSeguroCompleto())) {
            cargoSeguro = 180.0 * dto.getDiasRenta();
        }

        double descuentoLargaRenta = 0.0;
        if (dto.getDiasRenta() >= 7) {
            descuentoLargaRenta = costoRentaBase * 0.10;
        }

        double totalPagar = (costoRentaBase - descuentoLargaRenta) + cargoKmsAdicionales + cargoPorEdad + cargoSeguro;

        return new RentaVehiculoResponseDto(
                dto.getNombreCliente(),
                dto.getTipoVehiculo(),
                dto.getDiasRenta(),
                costoRentaBase,
                descuentoLargaRenta,
                cargoKmsAdicionales,
                cargoPorEdad,
                cargoSeguro,
                totalPagar
        );
    }
}