# Sistema de Reservas de Hotel

Sistema de gestión de reservas de hotel con interfaz de línea de comandos (CLI).

## Características

### Clases Principales

- **Hotel**: Gestiona la lista de habitaciones y reservas
- **Habitacion**: Representa una habitación con número, tipo y precio por noche
- **Reserva**: Gestiona reservas con fechas de entrada/salida y cálculo automático de precio

### Reglas de Negocio

- **Cálculo de precio**: precio base = noches × precioPorNoche
- **Oferta 2x1**: Por cada 2 noches, pagas solo 1 (se aplica automáticamente)
- **Validación de fechas**: La fecha de salida debe ser posterior a la fecha de entrada

### Menú CLI

El sistema ofrece las siguientes opciones:

1. **Listar habitaciones**: Muestra todas las habitaciones con su disponibilidad
2. **Crear habitación**: Añade una nueva habitación al hotel
3. **Calcular precio de reserva**: Calcula el precio sin confirmar la reserva
4. **Crear reserva**: Confirma y registra una reserva
5. **Listar reservas**: Muestra todas las reservas activas
0. **Salir**: Cierra la aplicación

## Formato de Fechas

Las fechas deben ingresarse en formato: `dd/MM/yyyy`

Ejemplo: `15/01/2026`

## Ejemplos de Uso

### Calcular Precio

- 1 noche a 50€ = 50€
- 2 noches a 80€ = 80€ (oferta 2x1 aplicada)
- 3 noches a 100€ = 200€ (oferta 2x1: pagas 2 de 3)
- 4 noches a 100€ = 200€ (oferta 2x1: pagas 2 de 4)
- 5 noches a 100€ = 300€ (oferta 2x1: pagas 3 de 5)

## Ejecución

### Con Gradle

```bash
./gradlew build
java -cp build/classes/java/main org.example.Main
```

### Desde el IDE

Ejecuta la clase `Main.java` directamente desde IntelliJ IDEA

## Tests

El proyecto incluye tests unitarios completos para:

- **ReservaTest**: Pruebas de cálculo de precio y validación de fechas
- **HotelTest**: Pruebas de gestión de habitaciones y reservas
- **HabitacionTest**: Pruebas de creación y disponibilidad

### Ejecutar Tests

```bash
./gradlew test
```

## Datos Iniciales

El sistema incluye 3 habitaciones precargadas:

- Habitación 101 - Individual - 50€/noche
- Habitación 102 - Doble - 80€/noche
- Habitación 103 - Suite - 150€/noche

## Tecnologías

- Java 25
- JUnit 5 para tests
- Gradle como build tool

