# ForoHub - Challenge Alura 🚀

¡Bienvenido a **ForoHub**! Esta es una API REST robusta desarrollada para el desafío de Alura Latam. El proyecto simula el backend de un foro de discusión, permitiendo gestionar tópicos (preguntas), usuarios y autenticación segura mediante tokens JWT.

## 🛠️ Tecnologías Utilizadas

* **Java 17**: Lenguaje principal.
* **Spring Boot 3**: Framework para el desarrollo ágil de la API.
* **Spring Security**: Gestión de autenticación y autorización.
* **JSON Web Token (JWT)**: Protocolo para la seguridad de los endpoints.
* **Spring Data JPA**: Abstracción para la persistencia de datos.
* **PostgreSQL**: Base de datos relacional para producción.
* **Maven**: Gestor de dependencias.
* **Hibernate**: Motor de mapeo objeto-relacional (ORM).

---

## 🔐 Configuración de Seguridad

La API utiliza un sistema de autenticación **Stateless**. Para interactuar con los recursos protegidos (`/topicos`), es necesario seguir este flujo:



1. **Login**: Se debe enviar un `POST` a `/login` con las credenciales.
2. **Token**: El servidor valida y retorna un `jwTtoken`.
3. **Acceso**: Se debe incluir este token en el Header de cada petición subsiguiente como un `Bearer Token`.

---

## 📑 Endpoints Principales

### 1. Autenticación
* **POST** `/login`: Recibe un JSON con `email` y `contrasena`.

### 2. Tópicos (Requiere Token)
* **GET** `/topicos`: Lista todos los tópicos (incluye paginación y ordenamiento).
* **POST** `/topicos`: Registra un nuevo tópico.
* **GET** `/topicos/{id}`: Detalle de un tópico específico.
* **PUT** `/topicos/{id}`: Actualiza los datos de un tópico.
* **DELETE** `/topicos/{id}`: Elimina un tópico de la base de datos.

---

## ⚙️ Configuración del Proyecto

### Requisitos previos
* JDK 17 o superior.
* PostgreSQL 14 o superior.
* Un IDE (IntelliJ IDEA recomendado).

### Variables de Entorno
Asegúrate de configurar tu archivo `src/main/resources/application.properties` con tus credenciales locales:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/foro_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contrasena

api.security.secret=${JWT_SECRET:tu_clave_secreta_aqui}

```
y ya, seria todo para usar mi programa creo.
