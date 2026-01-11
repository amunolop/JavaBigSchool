package org.example.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {
    @Test
    void testCalculoPrecioBasico() {
        Room room = new Room(101, "Individual", 50.0);
        LocalDate entrada = LocalDate.of(2026, 1, 15);
        LocalDate salida = LocalDate.of(2026, 1, 16);
        Reserva reserva = new Reserva(room, entrada, salida);
        assertEquals(50.0, reserva.getPrecioTotal(), 0.01);
        assertEquals(1, reserva.getNumeroNoches());
    }

    @Test
    void testOferta2x1Con2Noches() {
        Room room = new Room(102, "Doble", 80.0);
        LocalDate entrada = LocalDate.of(2026, 1, 15);
        LocalDate salida = LocalDate.of(2026, 1, 17);
        Reserva reserva = new Reserva(room, entrada, salida);
        assertEquals(80.0, reserva.getPrecioTotal(), 0.01);
        assertEquals(2, reserva.getNumeroNoches());
    }

    @Test
    void testOferta2x1Con3Noches() {
        Room room = new Room(103, "Suite", 100.0);
        LocalDate entrada = LocalDate.of(2026, 1, 15);
        LocalDate salida = LocalDate.of(2026, 1, 18);
        Reserva reserva = new Reserva(room, entrada, salida);
        assertEquals(200.0, reserva.getPrecioTotal(), 0.01);
        assertEquals(3, reserva.getNumeroNoches());
    }

    @Test
    void testOferta2x1Con4Noches() {
        Room room = new Room(103, "Suite", 100.0);
        LocalDate entrada = LocalDate.of(2026, 1, 15);
        LocalDate salida = LocalDate.of(2026, 1, 19);
        Reserva reserva = new Reserva(room, entrada, salida);
        assertEquals(200.0, reserva.getPrecioTotal(), 0.01);
        assertEquals(4, reserva.getNumeroNoches());
    }

    @Test
    void testOferta2x1Con5Noches() {
        Room room = new Room(103, "Suite", 100.0);
        LocalDate entrada = LocalDate.of(2026, 1, 15);
        LocalDate salida = LocalDate.of(2026, 1, 20);
        Reserva reserva = new Reserva(room, entrada, salida);
        assertEquals(300.0, reserva.getPrecioTotal(), 0.01);
        assertEquals(5, reserva.getNumeroNoches());
    }

    @Test
    void testFechaSalidaAnteriorAEntrada() {
        Room room = new Room(101, "Individual", 50.0);
        LocalDate entrada = LocalDate.of(2026, 1, 20);
        LocalDate salida = LocalDate.of(2026, 1, 15);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Reserva(room, entrada, salida);
        });
        assertTrue(exception.getMessage().contains("fecha de salida debe ser posterior"));
    }

    @Test
    void testFechasIguales() {
        Room room = new Room(101, "Individual", 50.0);
        LocalDate fecha = LocalDate.of(2026, 1, 15);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Reserva(room, fecha, fecha);
        });
        assertTrue(exception.getMessage().contains("fecha de salida debe ser posterior"));
    }
}
