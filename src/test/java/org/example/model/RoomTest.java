package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    @Test
    void testCrearHabitacion() {
        Room room = new Room(101, "Individual", 50.0);
        assertEquals(101, room.getNumero());
        assertEquals("Individual", room.getTipo());
        assertEquals(50.0, room.getPrecioPorNoche(), 0.01);
        assertTrue(room.isDisponible());
    }

    @Test
    void testCambiarDisponibilidad() {
        Room room = new Room(102, "Doble", 80.0);
        assertTrue(room.isDisponible());
        room.setDisponible(false);
        assertFalse(room.isDisponible());
        room.setDisponible(true);
        assertTrue(room.isDisponible());
    }

    @Test
    void testToString() {
        Room room = new Room(103, "Suite", 150.0);
        String resultado = room.toString();
        assertTrue(resultado.contains("103"));
        assertTrue(resultado.contains("Suite"));
        assertTrue(resultado.contains("150"));
        assertTrue(resultado.contains("Disponible"));
    }
}
