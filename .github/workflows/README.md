# Daily PR Report Workflow

## Descripción

Este workflow de GitHub Actions genera automáticamente un reporte diario de todas las Pull Requests (PRs) en el repositorio que necesitan atención.

## ¿Qué PRs se incluyen en el reporte?

El reporte incluye PRs que cumplan **al menos una** de estas condiciones:

1. **Sin reviewers asignados**: PRs que no tienen ningún revisor asignado
2. **No aprobadas**: PRs que aún no han sido aprobadas por al menos un revisor

## Información incluida en el reporte

Para cada PR, el reporte incluye:

- **Número y título** de la PR con enlace directo
- **Autor** de la PR
- **Fecha de creación** y última actualización
- **Estado**: Si no tiene reviewers o no está aprobada
- **Etiquetas** (labels) de la PR
- **Resumen del contenido**: Primeros 300 caracteres de la descripción de la PR
- **Actividad del revisor**: Detalle de qué han hecho los revisores:
  - ✅ Si aprobaron la PR
  - 🔄 Si solicitaron cambios
  - 💬 Si dejaron comentarios
  - Muestra fragmentos de los comentarios más recientes

## ¿Cuándo se ejecuta?

El workflow se ejecuta automáticamente de dos formas:

1. **Diariamente**: Todos los días a las 9:00 AM UTC (mediante cron schedule)
2. **Manualmente**: Puede ejecutarse en cualquier momento desde la pestaña "Actions" en GitHub usando el botón "Run workflow"

## ¿Dónde se publica el reporte?

El reporte se publica en múltiples lugares:

1. **Issue de GitHub**: Se crea un nuevo issue con la etiqueta `daily-report` y `automated` con el título "Daily PR Report - YYYY-MM-DD"
   - Si ya existe un issue para el día actual, se actualiza en lugar de crear uno nuevo
   
2. **Artifact**: El reporte se guarda como un artifact descargable en la ejecución del workflow
   - Nombre: `pr-report-{run_number}`
   - Retención: 90 días
   
3. **Logs del workflow**: El reporte completo se imprime en los logs del workflow para consulta rápida

## Ejemplo de reporte

```markdown
# 📋 Daily PR Report

**Generated:** 2026-01-11T09:00:00.000Z

**Total Open PRs:** 5
**PRs Needing Attention:** 2

---

## PRs Needing Attention

*These PRs either have no reviewers assigned or have not been approved yet.*

### [#42 - Add new feature](https://github.com/owner/repo/pull/42)

- **Author:** @johndoe
- **Created:** 1/10/2026
- **Last Updated:** 1/11/2026
- **Status:** ⚠️ No reviewers assigned 🔍 Not approved
- **Labels:** feature, needs-review

**Summary:**
This PR adds a new feature to improve user experience...

**Reviewer Activity:** ⚠️ No reviewer activity yet

---

### [#38 - Fix bug in login](https://github.com/owner/repo/pull/38)

- **Author:** @janedoe
- **Created:** 1/8/2026
- **Last Updated:** 1/10/2026
- **Status:** 🔍 Not approved
- **Labels:** bug, high-priority

**Summary:**
Fixed a critical bug in the login flow that caused...

**Reviewer Activity:**
- **reviewer1**: 🔄 Requested changes - "Please add more test coverage for this change..."
- **reviewer2**: 💬 Commented - "Looks good but needs documentation updates"

---
```

## Permisos requeridos

El workflow necesita los siguientes permisos:

- `pull-requests: read` - Para leer información de las PRs
- `contents: read` - Para leer el contenido del repositorio
- `issues: write` - Para crear/actualizar issues con el reporte

## Personalización

Puedes personalizar el workflow editando `.github/workflows/daily-pr-report.yml`:

- **Horario**: Modifica el cron schedule en la línea `cron: '0 9 * * *'`
- **Filtros**: Modifica la lógica de filtrado en el script de JavaScript
- **Formato del reporte**: Personaliza cómo se genera el markdown del reporte
- **Destino**: Cambia dónde se publica el reporte (issue, Slack, email, etc.)

## Solución de problemas

### El workflow no se ejecuta

- Verifica que el repositorio tenga GitHub Actions habilitado
- Comprueba que el horario cron esté correctamente configurado
- Los workflows con cron schedules solo se ejecutan en la rama default del repositorio

### No se crea el issue

- Verifica que el token `GITHUB_TOKEN` tenga los permisos necesarios
- Comprueba los logs del workflow para ver errores específicos

### El reporte está vacío

- Si todas las PRs abiertas tienen reviewers asignados y al menos una aprobación, el reporte indicará que no hay PRs que necesiten atención

## Contribuir

Si encuentras problemas o tienes sugerencias para mejorar el reporte, por favor:

1. Abre un issue describiendo el problema o sugerencia
2. O envía una PR con la mejora propuesta
