<Goals>
- Reducir rechazos de PR del agente por fallos en CI/validaciones o comportamientos inesperados.
- Minimizar fallos de comandos bash y de build.
- Permitir que el agente complete tareas más rápido, evitando exploración innecesaria (grep/find/code search).
</Goals>

<Limitations>
- Mantener estas instrucciones en un máximo aproximado de 2 páginas.
- No hacerlas específicas de una sola tarea; deben ser generales para el repositorio.
</Limitations>

<WhatToAdd>

<HighLevelDetails>
- Este repositorio contiene una aplicación Java (opcionalmente Spring Boot) con módulos:
  - `server/` (API REST con Spring Boot) y/o `cli/` (aplicación de consola).
- Lenguaje y runtimes:
  - Java 17 (o 21 si el proyecto lo requiere).
  - Gestor de build: Maven (pom.xml) o Gradle (build.gradle[.kts]).
- Tamaño aproximado: ~N archivos, ~N KLOC.
- Estándares:
  - Estilo/estático: Checkstyle/SpotBugs/PMD/Spotless (si aplica).
  - Pruebas: JUnit 5 + Mockito (si aplica).
</HighLevelDetails>

<BuildInstructions>
- **Bootstrap**:
  - Asegura Java 17/21: `java -version` debe coincidir.
  - Exporta `JAVA_HOME` correctamente antes de compilar.
  - Si usas Maven: instala Maven 3.9.x+.
  - Si usas Gradle wrapper: da permisos `chmod +x gradlew` en Unix.

- **Build**:
  - Maven (repo raíz): `mvn -q -U -e clean package` (usa `-DskipTests` si solo quieres empaquetar).
  - Gradle (repo raíz): `./gradlew clean build`.
  - Para multi-módulo, ejecuta en la raíz para compilar todos.

- **Test**:
  - Maven: `mvn test` (añade perfiles si hay ITs: `-P integration-tests`).
  - Gradle: `./gradlew test`.
  - Genera reportes en `target/surefire-reports` (Maven) o `build/reports/tests` (Gradle).

- **Run**:
  - Spring Boot (Maven): `mvn spring-boot:run`.
  - Spring Boot (Gradle): `./gradlew bootRun`.
  - JAR empaquetado: `java -jar server/target/server-*.jar` (ajusta ruta/nombre).
  - CLI: `java -cp cli/target/classes com.example.cli.Main` o empaqueta un fat-jar.

- **Lint/Format** (si está configurado):
  - Maven: `mvn checkstyle:check spotbugs:check pmd:check`.
  - Gradle: `./gradlew checkstyleMain spotbugsMain pmdMain`.
  - Formateo con Spotless (si existe): Maven `mvn spotless:apply` | Gradle `./gradlew spotlessApply`.

- **Workarounds validados**:
  - Si falla por caché Maven: `mvn -U clean` y borrar `~/.m2/repository` de forma selectiva.
  - Si falla por caché Gradle: `./gradlew --stop && ./gradlew clean` y borrar `~/.gradle/caches` si es necesario.
  - En Windows, habilita rutas largas: `git config core.longpaths true`.
  - Verifica variables de entorno requeridas (por ejemplo `SPRING_PROFILES_ACTIVE`, credenciales locales en `.env` o variables del sistema).

- **Versiones** (ejemplo):
  - “Siempre” Java 17 (o 21 si así lo define el proyecto).
  - Maven 3.9.x o Gradle 8.x.
</BuildInstructions>

<ProjectLayout>
- Layout principal:
  - `server/src/main/java` y `server/src/test/java`.
  - `cli/src/main/java` y `cli/src/test/java`.
  - Configuración Spring (si aplica) en `server/src/main/resources/application*.yml`.
- Raíz:
  - `pom.xml` (multi-módulo) o `settings.gradle(.kts)`/`build.gradle(.kts)` (Gradle).
  - CI: `.github/workflows/ci.yml` (build + tests + lint).
  - Estático: `checkstyle.xml`, `spotbugs-exclude.xml`, `pmd.xml`, `.editorconfig` (si existen).
- Checks antes de merge:
  - CI debe compilar, pasar tests y aprobar reglas estáticas.
  - (Opcional) Análisis seguridad: `dependency-check`, `owasp`, `codeql`, etc.
- Dependencias no obvias:
  - Variables de entorno para perfiles (dev/test/prod).
  - Certificados/keystores locales (si aplica).
- Índice rápido (ejemplo):
  - `/server`, `/cli`, `pom.xml` o `build.gradle.kts`, `.github/workflows/*`, `README.md`.
</ProjectLayout>

</WhatToAdd>

<StepsToFollow>
- Revisa `README.md` y `CONTRIBUTING.md` para comandos y convenciones.
- Ejecuta build y tests desde la raíz:
  - Maven: `mvn -q -U -e clean verify`
  - Gradle: `./gradlew clean build`
- Si falla algo:
  - Limpia (`clean`), valida `JAVA_HOME`, versiones y vuelve a ejecutar.
  - Documenta el error y el workaround correspondiente.
- Para ejecutar local:
  - Spring Boot: `mvn spring-boot:run` o `./gradlew bootRun`.
  - CLI: compila y ejecuta `Main` o el JAR generado.
- Confía en estas instrucciones; solo busca en el repo si falta información o detectas error.
</StepsToFollow>
