package org.example;

import org.example.model.Habitacion;
import org.example.model.Hotel;
import org.example.model.Reserva;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final Hotel hotel = new Hotel();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        // Datos iniciales
        hotel.agregarHabitacion(new Habitacion(101, "Individual", 50.0));
        hotel.agregarHabitacion(new Habitacion(102, "Doble", 80.0));
        hotel.agregarHabitacion(new Habitacion(103, "Suite", 150.0));

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    listarHabitaciones();
                    break;
                case 2:
                    crearHabitacion();
                    break;
                case 3:
                    calcularPrecioReserva();
                    break;
                case 4:
                    crearReserva();
                    break;
                case 5:
                    listarReservas();
                    break;
                case 0:
                    salir = true;
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== SISTEMA DE RESERVAS DE HOTEL ===");
        System.out.println("1. Listar habitaciones");
        System.out.println("2. Crear habitación");
        System.out.println("3. Calcular precio de reserva");
        System.out.println("4. Crear reserva");
        System.out.println("5. Listar reservas");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void listarHabitaciones() {
        System.out.println("\n--- HABITACIONES DISPONIBLES ---");
        if (hotel.getHabitaciones().isEmpty()) {
            System.out.println("No hay habitaciones registradas");
        } else {
            hotel.getHabitaciones().forEach(System.out::println);
        }
    }

    private static void crearHabitacion() {
        System.out.println("\n--- CREAR HABITACIÓN ---");
        try {
            System.out.print("Número de habitación: ");
            int numero = Integer.parseInt(scanner.nextLine());

            System.out.print("Tipo (Individual/Doble/Suite): ");
            String tipo = scanner.nextLine();

            System.out.print("Precio por noche: ");
            double precio = Double.parseDouble(scanner.nextLine());

            Habitacion habitacion = new Habitacion(numero, tipo, precio);
            hotel.agregarHabitacion(habitacion);
            System.out.println("✓ Habitación creada exitosamente");
        } catch (NumberFormatException e) {
            System.out.println("✗ Error: Valores numéricos inválidos");
        }
    }

    private static void calcularPrecioReserva() {
        System.out.println("\n--- CALCULAR PRECIO DE RESERVA ---");
        try {
            System.out.print("Número de habitación: ");
            int numero = Integer.parseInt(scanner.nextLine());

            Habitacion habitacion = hotel.buscarHabitacion(numero)
                    .orElseThrow(() -> new IllegalArgumentException("Habitación no encontrada"));

            System.out.print("Fecha entrada (dd/MM/yyyy): ");
            LocalDate fechaEntrada = LocalDate.parse(scanner.nextLine(), dateFormatter);

            System.out.print("Fecha salida (dd/MM/yyyy): ");
            LocalDate fechaSalida = LocalDate.parse(scanner.nextLine(), dateFormatter);

            Reserva reservaTemp = new Reserva(habitacion, fechaEntrada, fechaSalida);
            System.out.println("\n--- RESUMEN ---");
            System.out.println(reservaTemp);
            System.out.println("(Oferta 2x1 aplicada: cada 2 noches, pagas 1)");
        } catch (NumberFormatException e) {
            System.out.println("✗ Error: Número de habitación inválido");
        } catch (DateTimeParseException e) {
            System.out.println("✗ Error: Formato de fecha inválido (use dd/MM/yyyy)");
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    private static void crearReserva() {
        System.out.println("\n--- CREAR RESERVA ---");
        try {
            System.out.print("Número de habitación: ");
            int numero = Integer.parseInt(scanner.nextLine());

            Habitacion habitacion = hotel.buscarHabitacion(numero)
                    .orElseThrow(() -> new IllegalArgumentException("Habitación no encontrada"));

            if (!habitacion.isDisponible()) {
                throw new IllegalArgumentException("La habitación no está disponible");
            }

            System.out.print("Fecha entrada (dd/MM/yyyy): ");
            LocalDate fechaEntrada = LocalDate.parse(scanner.nextLine(), dateFormatter);

            System.out.print("Fecha salida (dd/MM/yyyy): ");
            LocalDate fechaSalida = LocalDate.parse(scanner.nextLine(), dateFormatter);

            Reserva reserva = new Reserva(habitacion, fechaEntrada, fechaSalida);
            hotel.crearReserva(reserva);

            System.out.println("\n✓ Reserva creada exitosamente");
            System.out.println(reserva);
        } catch (NumberFormatException e) {
            System.out.println("✗ Error: Número de habitación inválido");
        } catch (DateTimeParseException e) {
            System.out.println("✗ Error: Formato de fecha inválido (use dd/MM/yyyy)");
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    private static void listarReservas() {
        System.out.println("\n--- RESERVAS ACTIVAS ---");
        if (hotel.getReservas().isEmpty()) {
            System.out.println("No hay reservas registradas");
        } else {
            hotel.getReservas().forEach(System.out::println);
        }
    }
}
