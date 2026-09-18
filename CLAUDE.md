# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

This is a Maven/Spring Boot project (Java 25, Spring Boot 4.1.1). Use the Maven wrapper.

- Build: `./mvnw compile` (bash) or `mvnw.cmd compile` (Windows cmd/PowerShell)
- Run all tests: `./mvnw test`
- Run a single test class: `./mvnw test -Dtest=ProyectoFinalApplicationTests`
- Run the app: `./mvnw spring-boot:run`
- Package: `./mvnw package`

The app requires a running MySQL instance matching `src/main/resources/application.properties`
(`spring.datasource.url`, database `mitox`, schema auto-updated via `spring.jpa.hibernate.ddl-auto=update`).
Note: `application.properties` currently commits a plaintext DB password — treat it as a local dev
credential, not something to propagate, and avoid echoing it back in responses.

## Architecture

Package root: `com.academia.proyecto_final`.

- **model/** — JPA entities: `Estudiante`, `Curso`, `Matricula`, `DetalleMatricula`. `Matricula`
  has a `@ManyToOne` to `Estudiante` and a `@OneToMany(cascade = ALL)` to `DetalleMatricula`.
  `DetalleMatricula` is meant to link to `Curso` plus an `aula`, but the `Curso` association is
  currently commented out in `DetalleMatricula.java` pending a join column decision — don't
  assume that link is live. All entities use Lombok `@Data` and restrict `@EqualsAndHashCode` to
  the `@Id` field via `@EqualsAndHashCode(onlyExplicitlyIncluded = true)` + `@EqualsAndHashCode.Include`.
- **repository/** — Spring Data repositories. `IGenericRepository<T, ID>` is a `@NoRepositoryBean`
  base extending `JpaRepository`; entity-specific repositories (`IEstudianteRepository`,
  `ICursoRepository`, `IMatriculaRepository`) extend it directly with no extra query methods.
- **service/** — `IGenericService<T, ID>` defines the generic CRUD contract: `findAll`,
  `findById`, `create`, `update` (throws `Exception`), `delete`. Entity services (e.g.
  `IEstudianteService`, `IMatriculaService`) extend it with no additions.
- **service/impl/** — `CRUDGenericImpl<T, ID>` is the abstract generic implementation of
  `IGenericService`, exposing `protected abstract getRepository()` for subclasses to supply.
  Concrete services (`IEstudianteServiceImpl`, `IMatriculaServiceImpl`) extend this abstract class
  *and* implement the entity-specific service interface, wiring in the concrete repository via
  constructor injection (`@RequiredArgsConstructor`). `IMatriculaServiceImpl` overrides `create()`
  to resolve the nested `Estudiante` by id (throwing `EstudianteNotFoundException` if missing)
  before delegating to `super.create()`; the analogous `Curso` validation on each
  `DetalleMatricula` is written but commented out, consistent with the commented-out entity
  association above.
- **controller/** — REST controllers under `v1/<resource>` (e.g. `EstudianteController` at
  `v1/estudiante`, `MatriculaController` at `v1/matricula`) call into the service layer and return
  `ResponseEntity`. There is no `CursoController` yet even though `Curso`'s model/repository exist.
  Controller endpoint paths are bespoke per action (e.g. `/listarEstudiantes`,
  `/createRegistration`, `/updateStudentRecord/{idStudent}`, `/studentRecordDeletion/{idStudent}`)
  rather than REST-conventional verbs on a shared path — follow this existing naming style when
  adding endpoints rather than switching to plain `GET /`, `POST /`, etc.
- **exception/** — `GlobalExceptionController` (`@RestControllerAdvice`) maps entity-specific
  `RuntimeException`s (`EstudianteNotFoundException`, `CursoNotFoundException`) to a 404
  `ErrorResponseHandler` body, plus a catch-all `Exception` handler returning 500. When adding a
  new entity that needs not-found handling, add a matching `<Entity>NotFoundException` and a
  handler method in `GlobalExceptionController` following the existing pattern.

When adding a new entity (e.g. wiring up `Curso` end-to-end), follow the existing pattern: a
repository interface extending `IGenericRepository`, a service interface extending
`IGenericService`, a service impl extending `CRUDGenericImpl` + implementing the entity-specific
service interface, then a controller under its own `v1/<resource>` path.

Note: `CRUDGenericImpl.update` locates a setter reflectively as `"setId" + <simple class name>`
(e.g. would look for `setIdEstudiante` on `Estudiante`) — keep ID field/setter naming consistent
with this convention if reusing this generic impl for other entities.

Agents are located in: .agents/subagents
Skills are located in: .agents/skills
Workflows are located in: .agents/workflows
