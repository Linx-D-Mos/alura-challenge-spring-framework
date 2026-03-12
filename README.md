<h1 align="center">Foro Hub API 💬</h1>

<p align="center">
  <i>API REST desarrollada con Spring Boot para la gestión de tópicos en un foro de discusión, protegida mediante autenticación JWT.</i>
</p>

## 📖 Descripción del Proyecto

**Foro Hub API** proporciona un conjunto completo de *endpoints* que permiten a los usuarios crear, leer, actualizar y eliminar tópicos de discusión (CRUD). Esta API está estructurada bajo una **Arquitectura clásica en Capas** (Controlador, Servicio y Repositorio), asegurando así un diseño de software profesional, escalable y mantenible. 

La seguridad ha sido un pilar fundamental en este rediseño, por lo que todos los recursos (exceptuando el inicio de sesión) están protegidos a través del componente **Spring Security**. El proceso de autenticación de clientes (`stateless`) se gestiona mediante tokens **JSON Web Token (JWT)**, validados en cada petición HTTP por un filtro de seguridad personalizado.

## ✨ Características Principales

1.  **Gestión de Tópicos (Módulo CRUD):**
    *   **Creación Segura:** Valida rigurosamente la información contra duplicados antes de insertar nuevos registros (`POST /topicos`).
    *   **Listado Inteligente:** Recupera el listado completo de discusiones habilitando parámetros de paginación y ordenamiento en tiempo real (`GET /topicos`).
    *   **Visualización de Detalle:** Acceso granular al contenido específico de una sola publicación (`GET /topicos/{id}`).
    *   **Limpieza de Contenido:** Eliminación controlada validando existencia previa (`DELETE /topicos/{id}`).
2.  **Módulo de Seguridad (Autenticación JWT):**
    *   Inicio de sesión seguro para usuarios registrados (`POST /login`).
    *   Generación automática de Tokens JWT con expiración temporal configurada.
    *   Interceptación obligatoria de solicitudes (Filter) para asegurar que el *Bearer Token* es legítimo.

## 🛠️ Tecnologías y Herramientas Utilizadas

*   **Java 17:** Lenguaje base.
*   **Spring Boot 3:** Framework robusto para creación de APIs (`@RestController`, inyección de dependencias).
*   **Spring Security:** Control severo del filtro de acceso y encriptación *BCrypt* de contraseñas.
*   **Spring Data JPA (Hibernate):** Interfaz ORM para la capa de persistencia (Base de Datos).
*   **Auth0 JWT:** Biblioteca estandarizada para firma, decodificación y validación de tokens seguros.
*   **Bean Validation:** Uso de anotaciones como `@NotBlank` y `@NotNull` para blindar la entrada de datos.
*   **Jackson:** Manipulación de objetos y adaptaciones estructurales entre los DTOs de JSON (`@JsonAlias`, `@JsonProperty`) y la sintaxis local de Java.
*   **Maven:** Gestor de construcción.

## 🗂️ Estructura del Proyecto Refactorizada

El sistema aplica la separación de responsabilidades:

```text
src/main/java/com/foro/hub/
├── controller
│   ├── ControladorAutenticacion.java   # Interfaz REST de inicio de sesión
│   └── ControladorTopicos.java         # Interfaz REST para el CRUD de tópicos
├── model
│   ├── Topico.java / Usuario.java      # Mapeo a Base de Datos (JPA Entities)
│   └── Datos...java (Records)          # Data Transfer Objects (DTOs)
├── repository
│   ├── TopicoRepository.java           # Lógica DAO hacia Base de Datos
│   └── UsuarioRepository.java          # Lógica DAO de validación de autenticidad de usuarios
├── security
│   ├── ConfiguracionSeguridad.java     # Bean y FilterChain principal para blindaje HttpSecurity
│   └── FiltroAutenticacionJwt.java     # Extensión principal de OncePerRequestFilter 
├── service
│   ├── ServicioAutenticacion.java      # Interfaz funcional hacia proveedor principal (UserDetailsService)
│   ├── ServicioManejoTokens.java       # Emisor de Auth0 y validador de claims HMAC256
│   └── ServicioTopicos.java            # Núcleo duro de la lógica de negocio
└── HubApplication.java                 # Archivo disparador Spring
```

> **Nota Técnica Extra:** Se emplearon anotaciones especiales relacionales en las variables del ORM de Java para preservar completamente intacto el esquema SQL de tu base de datos original. La API mantendrá sus mismos *endpoints* funcionales hacia el frontend.

## 🚀 Despliegue Local

### Prerrequisitos
- **Java 17** habilitado en variables de entorno.
- Una base de datos relacional (MySQL/PostgreSQL) en funcionamiento. Previamente configurada en el archivo `application.properties`.
- Definir la variable de entorno `API_SECURITY_SECRET` o establecer manualmente su propiedad en tu *properties* local para la firma de Tokens.

### Pasos
1. Accede a tu terminal en la carpeta raíz `ChallengeConsumoAPI/`.
2. Verifica dependencias locales y compila el binario:
   ```bash
   ./mvnw clean package
   ```
3. Ejecuta la aplicación mediante Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Podrás conectarte y poner a prueba los *endpoints* mediante clientes como Postman o Insomnia a través de `http://localhost:8080/`.
