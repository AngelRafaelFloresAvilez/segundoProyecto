package mx.edu.utez.segundoProyecto.service;

import mx.edu.utez.segundoProyecto.dto.hospedaje.HospedajeRequestDto;
import mx.edu.utez.segundoProyecto.dto.hospedaje.HospedajeResponseDto;
import mx.edu.utez.segundoProyecto.exception.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {

    public HospedajeResponseDto calcularHospedaje(HospedajeRequestDto dto) {
        if (dto.getNumeroNoches() > 30) {
            throw new CustomBadRequestException("No se acepta la reservación: el número de noches supera 30");
        }

        String hab = dto.getTipoHabitacion().toUpperCase();
        int huespedes = dto.getNumeroHuespedes();

        if ("INDIVIDUAL".equals(hab) && huespedes > 1) {
            throw new CustomBadRequestException("Se selecciona habitación individual para más de una persona");
        }
        if ("DOBLE".equals(hab) && huespedes > 2) {
            throw new CustomBadRequestException("Se selecciona habitación doble para más de dos personas");
        }
        if ("SUITE".equals(hab) && huespedes > 4) {
            throw new CustomBadRequestException("Se selecciona una suite para más de cuatro personas");
        }

        double costoNoche = switch (hab) {
            case "INDIVIDUAL" -> 700.0;
            case "DOBLE" -> 1100.0;
            case "SUITE" -> 1800.0;
            default -> 0.0;
        };

        double costoHospedajeBase = costoNoche * dto.getNumeroNoches();

        double ajusteTemporada = switch (dto.getTemporada().toUpperCase()) {
            case "BAJA" -> -0.10 * costoHospedajeBase;
            case "REGULAR" -> 0.0;
            case "ALTA" -> 0.25 * costoHospedajeBase;
            default -> 0.0;
        };

        double costoDesayuno = 0.0;
        if (Boolean.TRUE.equals(dto.getIncluyeDesayuno())) {
            costoDesayuno = dto.getNumeroHuespedes() * dto.getNumeroNoches() * 150.0;
        }

        double costoEstacionamiento = 0.0;
        if (Boolean.TRUE.equals(dto.getIncluyeEstacionamiento())) {
            costoEstacionamiento = dto.getNumeroNoches() * 100.0;
        }

        double descuentoLargaEstancia = 0.0;
        if (dto.getNumeroNoches() >= 7) {
            descuentoLargaEstancia = costoHospedajeBase * 0.08;
        }

        double subtotal = (costoHospedajeBase + ajusteTemporada - descuentoLargaEstancia) + costoDesayuno + costoEstacionamiento;
        double impuestoHospedaje = subtotal * 0.04;

        double total = subtotal + impuestoHospedaje;

        return new HospedajeResponseDto(
                dto.getNombreHuesped(),
                dto.getTipoHabitacion(),
                costoHospedajeBase,
                ajusteTemporada,
                costoDesayuno,
                costoEstacionamiento,
                descuentoLargaEstancia,
                subtotal,
                impuestoHospedaje,
                total
        );
    }
}