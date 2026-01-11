package org.example;

import org.example.model.Hotel;
import org.example.model.Reserva;
import org.example.model.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final Hotel hotel = new Hotel();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        // Datos iniciales
        hotel.agregarHabitacion(new Room(101, "Individual", 50.0));
        hotel.agregarHabitacion(new Room(102, "Doble", 80.0));
        hotel.agregarHabitacion(new Room(103, "Suite", 150.0));
        hotel.agregarHabitacion(new Room(104, "Suite Especial", 200.0));

        boolean salir = false;
        while (!salir) {
            showMenu();
            int opcion = readOption();

            switch (opcion) {
                case 1:
                    listRooms();
                    break;
                case 2:
                    createRoom();
                    break;
                case 3:
                    calculateReservationPrice();
                    break;
                case 4:
                    createReservation();
                    break;
                case 5:
                    listReservations();
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

    private static void showMenu() {
        System.out.println("=== SISTEMA DE RESERVAS DE HOTEL ===");
        System.out.println("1. Listar habitaciones");
        System.out.println("2. Crear habitación");
        System.out.println("3. Calcular precio de reserva");
        System.out.println("4. Crear reserva");
        System.out.println("5. Listar reservas");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int readOption() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void listRooms() {
        System.out.println("\n--- HABITACIONES DISPONIBLES ---");
        if (hotel.getHabitaciones().isEmpty()) {
            System.out.println("No hay habitaciones registradas");
        } else {
            hotel.getHabitaciones().forEach(System.out::println);
        }
    }

    private static void createRoom() {
        System.out.println("\n--- CREAR HABITACIÓN ---");
        try {
            System.out.print("Número de habitación: ");
            int numero = Integer.parseInt(scanner.nextLine());

            System.out.print("Tipo (Individual/Doble/Suite): ");
            String tipo = scanner.nextLine();

            System.out.print("Precio por noche: ");
            double precio = Double.parseDouble(scanner.nextLine());

            Room room = new Room(numero, tipo, precio);
            hotel.agregarHabitacion(room);
            System.out.println("✓ Habitación creada exitosamente");
        } catch (NumberFormatException e) {
            System.out.println("✗ Error: Valores numéricos inválidos");
        }
    }

    private static void calculateReservationPrice() {
        System.out.println("\n--- CALCULAR PRECIO DE RESERVA ---");
        try {
            System.out.print("Número de habitación: ");
            int numero = Integer.parseInt(scanner.nextLine());

            Room room = hotel.buscarHabitacion(numero)
                    .orElseThrow(() -> new IllegalArgumentException("Habitación no encontrada"));

            System.out.print("Fecha entrada (dd/MM/yyyy): ");
            LocalDate fechaEntrada = LocalDate.parse(scanner.nextLine(), dateFormatter);

            System.out.print("Fecha salida (dd/MM/yyyy): ");
            LocalDate fechaSalida = LocalDate.parse(scanner.nextLine(), dateFormatter);

            Reserva reservaTemp = new Reserva(room, fechaEntrada, fechaSalida);
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

    private static void createReservation() {
        System.out.println("\n--- CREAR RESERVA ---");
        try {
            System.out.print("Número de habitación: ");
            int numero = Integer.parseInt(scanner.nextLine());

            Room room = hotel.buscarHabitacion(numero)
                    .orElseThrow(() -> new IllegalArgumentException("Habitación no encontrada"));

            if (!room.isDisponible()) {
                throw new IllegalArgumentException("La habitación no está disponible");
            }

            System.out.print("Fecha entrada (dd/MM/yyyy): ");
            LocalDate fechaEntrada = LocalDate.parse(scanner.nextLine(), dateFormatter);

            System.out.print("Fecha salida (dd/MM/yyyy): ");
            LocalDate fechaSalida = LocalDate.parse(scanner.nextLine(), dateFormatter);

            Reserva reserva = new Reserva(room, fechaEntrada, fechaSalida);
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

    private static void listReservations() {
        System.out.println("\n--- RESERVAS ACTIVAS ---");
        if (hotel.getReservas().isEmpty()) {
            System.out.println("No hay reservas registradas");
        } else {
            hotel.getReservas().forEach(System.out::println);
        }
    }

    public void printUpperCase(String text) {
        if (Objects.isNull(text)) {
            System.out.println("empty");
        }
        if (text != null) {
            System.out.println(text.toUpperCase());
        }
    }
}
