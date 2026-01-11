package org.example.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class HabitacionTest {
    @Test
    void testCrearHabitacion() {
        Habitacion habitacion = new Habitacion(101, "Individual", 50.0);
        assertEquals(101, habitacion.getNumero());
        assertEquals("Individual", habitacion.getTipo());
        assertEquals(50.0, habitacion.getPrecioPorNoche(), 0.01);
        assertTrue(habitacion.isDisponible());
    }
    @Test
    void testCambiarDisponibilidad() {
        Habitacion habitacion = new Habitacion(102, "Doble", 80.0);
        assertTrue(habitacion.isDisponible());
        habitacion.setDisponible(false);
        assertFalse(habitacion.isDisponible());
        habitacion.setDisponible(true);
        assertTrue(habitacion.isDisponible());
    }
    @Test
    void testToString() {
        Habitacion habitacion = new Habitacion(103, "Suite", 150.0);
        String resultado = habitacion.toString();
        assertTrue(resultado.contains("103"));
        assertTrue(resultado.contains("Suite"));
        assertTrue(resultado.contains("150"));
        assertTrue(resultado.contains("Disponible"));
    }
}
