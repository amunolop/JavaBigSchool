package org.example.model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
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
        Habitacion habitacion = new Habitacion(101, "Individual", 50.0);
        hotel.agregarHabitacion(habitacion);
        assertEquals(1, hotel.getHabitaciones().size());
    }
    @Test
    void testBuscarHabitacionExistente() {
        Habitacion habitacion = new Habitacion(101, "Individual", 50.0);
        hotel.agregarHabitacion(habitacion);
        assertTrue(hotel.buscarHabitacion(101).isPresent());
        assertEquals(101, hotel.buscarHabitacion(101).get().getNumero());
    }
    @Test
    void testBuscarHabitacionNoExistente() {
        assertFalse(hotel.buscarHabitacion(999).isPresent());
    }
    @Test
    void testCrearReserva() {
        Habitacion habitacion = new Habitacion(101, "Individual", 50.0);
        hotel.agregarHabitacion(habitacion);
        LocalDate entrada = LocalDate.of(2026, 1, 15);
        LocalDate salida = LocalDate.of(2026, 1, 17);
        Reserva reserva = new Reserva(habitacion, entrada, salida);
        hotel.crearReserva(reserva);
        assertEquals(1, hotel.getReservas().size());
        assertFalse(habitacion.isDisponible());
    }
    @Test
    void testMultiplesHabitaciones() {
        hotel.agregarHabitacion(new Habitacion(101, "Individual", 50.0));
        hotel.agregarHabitacion(new Habitacion(102, "Doble", 80.0));
        hotel.agregarHabitacion(new Habitacion(103, "Suite", 150.0));
        assertEquals(3, hotel.getHabitaciones().size());
    }
}
