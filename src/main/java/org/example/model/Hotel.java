package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Hotel {
    private final List<Room> habitaciones;
    private final List<Reserva> reservas;

    public Hotel() {
        this.habitaciones = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public void agregarHabitacion(Room room) {
        habitaciones.add(room);
    }

    public List<Room> getHabitaciones() {
        return new ArrayList<>(habitaciones);
    }

    public Optional<Room> buscarHabitacion(int numero) {
        return habitaciones.stream()
                .filter(h -> h.getNumero() == numero)
                .findFirst();
    }

    public void crearReserva(Reserva reserva) {
        reservas.add(reserva);
        reserva.getHabitacion().setDisponible(false);
    }

    public List<Reserva> getReservas() {
        return new ArrayList<>(reservas);
    }
}

