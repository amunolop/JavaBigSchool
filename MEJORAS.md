# MEJORAS.md

Posibles mejoras para el sistema de reservas de hotel.

## Funcionalidad

- **Cancelar reservas**: Permitir cancelar reservas y liberar la habitación
- **Modificar reservas**: Cambiar fechas de una reserva existente
- **Buscar disponibilidad por fechas**: Mostrar habitaciones disponibles en un rango de fechas
- **Gestión de clientes**: Asociar reservas a clientes con nombre, email, teléfono
- **Historial de reservas**: Mantener registro de reservas pasadas vs activas
- **Múltiples ofertas**: Soportar diferentes tipos de descuentos (temporada, anticipación, etc.)

## Modelo de datos

- **Solapamiento de reservas**: Actualmente una habitación solo puede tener una reserva. Permitir múltiples reservas en fechas que no se solapen
- **Tipos de habitación como enum**: Usar `RoomType` enum en lugar de String libre
- **Validación de número de habitación único**: Evitar duplicados al crear habitaciones
- **Persistencia**: Guardar datos en base de datos o archivo para no perderlos al cerrar

## Arquitectura

- **Separar lógica de negocio de la UI**: Extraer la lógica del CLI a una capa de servicio
- **Patrón Repository**: Abstraer el acceso a datos de Hotel
- **Inyección de dependencias**: Facilitar testing y extensibilidad

## Código

- **Eliminar método `printUpperCase`**: Método no utilizado en `Main.java:176`
- **Unificar idioma**: Decidir entre español o inglés para todo el código
- **Usar `BigDecimal` para precios**: Evitar problemas de precisión con `double`

## Testing

- **Tests para Hotel**: Añadir tests unitarios para la clase Hotel
- **Tests para Room**: Añadir tests unitarios para la clase Room
- **Tests de integración**: Probar flujos completos del sistema
