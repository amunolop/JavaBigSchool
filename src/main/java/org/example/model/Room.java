package org.example.model;

public class Room {
    private int number;
    private String type;
    private double pricePerNight;
    private boolean available;

    public Room(int number, String type, double pricePerNight) {
        this.number = number;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.available = true;
    }

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return String.format("Habitación %d - %s - %.2f€/noche - %s",
                number, type, pricePerNight, available ? "Disponible" : "Ocupada");
    }
}


