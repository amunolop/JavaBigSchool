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
        hotel.agregarHabitacion(room);
        assertEquals(1, hotel.getHabitaciones().size());
    }

    @Test
    void testBuscarHabitacionExistente() {
        Room room = new Room(101, "Individual", 50.0);
        hotel.agregarHabitacion(room);
        assertTrue(hotel.buscarHabitacion(101).isPresent());
        assertEquals(101, hotel.buscarHabitacion(101).get().getNumero());
    }

    @Test
    void testBuscarHabitacionNoExistente() {
        assertFalse(hotel.buscarHabitacion(999).isPresent());
    }

    @Test
    void testCrearReserva() {
        Room room = new Room(101, "Individual", 50.0);
        hotel.agregarHabitacion(room);
        LocalDate entrada = LocalDate.of(2026, 1, 15);
        LocalDate salida = LocalDate.of(2026, 1, 17);
        Reserva reserva = new Reserva(room, entrada, salida);
        hotel.crearReserva(reserva);
        assertEquals(1, hotel.getReservas().size());
        assertFalse(room.isDisponible());
    }

    @Test
    void testMultiplesHabitaciones() {
        hotel.agregarHabitacion(new Room(101, "Individual", 50.0));
        hotel.agregarHabitacion(new Room(102, "Doble", 80.0));
        hotel.agregarHabitacion(new Room(103, "Suite", 150.0));
        assertEquals(3, hotel.getHabitaciones().size());
    }
}
