package org.example.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private Room room;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private double precioTotal;

    public Reserva(Room room, LocalDate fechaEntrada, LocalDate fechaSalida) {
        if (fechaSalida.isBefore(fechaEntrada) || fechaSalida.isEqual(fechaEntrada)) {
            throw new IllegalArgumentException("La fecha de salida debe ser posterior a la fecha de entrada");
        }
        this.room = room;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.precioTotal = calcularPrecio();
    }

    private double calcularPrecio() {
        long noches = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
        double precioBase = noches * room.getPrecioPorNoche();

        // Oferta 2x1: por cada 2 noches, pagas solo 1
        if (noches >= 2) {
            long nochesGratis = noches / 2;
            precioBase -= nochesGratis * room.getPrecioPorNoche();
        }

        return precioBase;
    }

    public Room getHabitacion() {
        return room;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public long getNumeroNoches() {
        return ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
    }

    @Override
    public String toString() {
        return String.format("Reserva - Habitación %d - %s a %s (%d noches) - Total: %.2f€",
                room.getNumero(), fechaEntrada, fechaSalida, getNumeroNoches(), precioTotal);
    }
}

