package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Hotel {
    private final List<Room> rooms;
    private final List<Reserva> reservations;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    public Optional<Room> findRoom(int number) {
        return rooms.stream()
                .filter(h -> h.getNumber() == number)
                .findFirst();
    }

    public void createReservation(Reserva reservation) {
        reservations.add(reservation);
        reservation.getRoom().setAvailable(false);
    }

    public List<Reserva> getReservations() {
        return new ArrayList<>(reservations);
    }
}

