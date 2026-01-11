package org.example.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalPrice;

    public Reserva(Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        if (checkOutDate.isBefore(checkInDate) || checkOutDate.isEqual(checkInDate)) {
            throw new IllegalArgumentException("La fecha de salida debe ser posterior a la fecha de entrada");
        }
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = calculatePrice();
    }

    private double calculatePrice() {
        long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        double basePrice = nights * room.getPricePerNight();

        // Oferta 2x1: por cada 2 noches, pagas solo 1
        if (nights >= 2) {
            long freeNights = nights / 2;
            basePrice -= freeNights * room.getPricePerNight();
        }

        return basePrice;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public long getNumberOfNights() {
        return ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    @Override
    public String toString() {
        return String.format("Reserva - Habitación %d - %s a %s (%d noches) - Total: %.2f€",
                room.getNumber(), checkInDate, checkOutDate, getNumberOfNights(), totalPrice);
    }
}

