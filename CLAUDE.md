# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build the project
./gradlew build

# Run all tests
./gradlew test

# Run a single test class
./gradlew test --tests "org.example.model.ReservaTest"

# Run a single test method
./gradlew test --tests "org.example.model.ReservaTest.testOferta2x1Con2Noches"

# Run the CLI application
./gradlew run

# Clean and rebuild
./gradlew clean build
```

## Architecture

This is a hotel reservation system with a CLI interface. The codebase uses Gradle (Kotlin DSL) with Java and JUnit 5.

### Domain Model (`src/main/java/org/example/model/`)

- **Hotel**: Manages collections of rooms and reservations. Provides room lookup and reservation creation.
- **Room**: Represents a hotel room with number, type, price per night, and availability status.
- **Reserva**: Represents a reservation with check-in/check-out dates. Calculates total price with a 2x1 offer (for every 2 nights, pay for 1).

### Entry Point

- **Main**: CLI application with menu-driven interface for listing rooms, creating rooms, calculating prices, creating reservations, and listing reservations.

### Business Rules

- **Price calculation**: `nights × pricePerNight` with 2x1 discount (free nights = nights / 2)
- **Date validation**: Check-out date must be after check-in date
- **Date format**: `dd/MM/yyyy`

## Language

The codebase uses a mix of Spanish (UI messages, some class names like `Reserva`) and English (method names, field names). Follow existing conventions when adding code.
