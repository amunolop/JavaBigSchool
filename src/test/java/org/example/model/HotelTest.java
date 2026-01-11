package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {
    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
    }

    @Test
    void testAgregarHabitacion() {
        Room room = new Room(101, "Individual", 50.0);
        hotel.addRoom(room);
        assertEquals(1, hotel.getRooms().size());
    }

    @Test
    void testBuscarHabitacionExistente() {
        Room room = new Room(101, "Individual", 50.0);
        hotel.addRoom(room);
        assertTrue(hotel.findRoom(101).isPresent());
        assertEquals(101, hotel.findRoom(101).get().getNumber());
    }

    @Test
    void testBuscarHabitacionNoExistente() {
        assertFalse(hotel.findRoom(999).isPresent());
    }

    @Test
    void testCrearReserva() {
        Room room = new Room(101, "Individual", 50.0);
        hotel.addRoom(room);
        LocalDate entrada = LocalDate.of(2026, 1, 15);
        LocalDate salida = LocalDate.of(2026, 1, 17);
        Reserva reserva = new Reserva(room, entrada, salida);
        hotel.createReservation(reserva);
        assertEquals(1, hotel.getReservations().size());
        assertFalse(room.isAvailable());
    }

    @Test
    void testMultiplesHabitaciones() {
        hotel.addRoom(new Room(101, "Individual", 50.0));
        hotel.addRoom(new Room(102, "Doble", 80.0));
        hotel.addRoom(new Room(103, "Suite", 150.0));
        assertEquals(3, hotel.getRooms().size());
    }
}
