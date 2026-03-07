# GEMINI Code Companion Instructions

This document provides context and instructions for the Gemini AI assistant to effectively understand and assist with this project.

## Project Overview

This is a command-line interface (CLI) application for a hotel reservation system. The project is written in Java and managed with Gradle.

The core of the application allows users to manage hotel rooms and reservations. The main logic is contained within the `org.example.Main` class, which provides a menu-driven interface for interacting with the system.

The data models are:
- `Hotel`: Manages the lists of rooms and reservations.
- `Room`: Represents a hotel room with properties like room number, type, price, and availability.
- `Reserva`: Represents a reservation, calculating the price and handling booking logic, including a "2 for 1" discount.

## Building and Running

### Build

To build the project, run the following command:

```bash
./gradlew build
```

### Run

To run the application, you can use the provided PowerShell script:

```bash
./run.ps1
```

Alternatively, you can use Gradle directly:

```bash
./gradlew run
```

### Test

To run the unit tests, use the following command:

```bash
./gradlew test
```
The tests are written using JUnit 5 and are located in `src/test/java`.

## Development Conventions

### Code Style

- The code follows standard Java conventions.
- The project uses a simple, object-oriented architecture.
- Models are located in the `org.example.model` package.

### Testing

- Unit tests are written with JUnit 5.
- Tests are located in the `src/test/java/org/example/model` directory, mirroring the main source structure.
- Tests cover the business logic in the model classes.
