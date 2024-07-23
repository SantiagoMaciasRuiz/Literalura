# Literalura
# Proyecto Desafío Literalura

## Descripción

El proyecto **Desafío Literalura** es una aplicación desarrollada en Java utilizando Spring Boot. La aplicación interactúa con la API de Gutendex para buscar, almacenar y listar libros y autores en una base de datos relacional. Utiliza varios patrones de diseño y protocolos estándar para asegurar un código limpio, eficiente y escalable.

## Funcionalidades

1. **Buscar Libros**: Realiza búsquedas de libros por título utilizando la API de Gutendex.
2. **Listar Libros Buscados**: Muestra todos los libros almacenados en la base de datos.
3. **Listar Libros por Autores**: Muestra autores junto con sus libros.
4. **Listar Autores por Fecha de Nacimiento y Muerte**: Filtra autores vivos en un intervalo de fechas específico.
5. **Mostrar Estadísticas de la Base de Datos**: Proporciona estadísticas sobre los libros almacenados, incluyendo promedio, máximo y mínimo número de descargas.

## Arquitectura del Proyecto

### Patrones de Diseño

- **Repositorio (Repository Pattern)**: Utilizado para encapsular la lógica de acceso a datos. Las interfaces `LibroRepository` y `AutorRepository` extienden `JpaRepository` proporcionando métodos CRUD y consultas personalizadas.
- **Inyección de Dependencias (Dependency Injection)**: Facilitada por Spring Boot, permite inyectar dependencias en tiempo de ejecución, promoviendo un diseño más flexible y fácil de probar.
- **Servicio (Service Layer)**: Define la lógica de negocio en clases de servicio como `ConsumoAPI` y `ConvierteDatos`.
- **Builder Pattern**: Implementado en la clase `Libro` para construir objetos de manera más legible y manejable.

### Protocolo HTTP

- **Cliente HTTP**: La clase `ConsumoAPI` utiliza `HttpClient` de Java 11 para realizar peticiones HTTP a la API de Gutendex.
- **Manejo de Errores**: Se implementan bloques `try-catch` para manejar excepciones de IO e interrupciones durante las peticiones HTTP.
- **Mapeo de JSON**: Uso de Jackson para deserializar las respuestas JSON de la API en objetos Java.

## Estructura del Proyecto

### Paquete `repository`

- **LibroRepository**: Interface que extiende `JpaRepository<Libro, Long>` para realizar operaciones CRUD sobre la entidad `Libro`.
- **AutorRepository**: Interface que extiende `JpaRepository<Autor, Long>`. Incluye métodos personalizados para buscar autores por nombre y por intervalos de fechas.

### Paquete `service`

- **ConsumoAPI**: Clase que encapsula la lógica para realizar peticiones HTTP a la API de Gutendex.
    - `obtenerDatos(String url)`: Realiza una petición GET y devuelve la respuesta en formato JSON.
- **ConvierteDatos**: Implementa la interfaz `IConvierteDatos`.
    - `obtenerDatos(String json, Class<T> clase)`: Convierte el JSON en una instancia de la clase especificada.
    - `convertirDatosLibrosALibro(DatosLibros datosLibros)`: Convierte datos JSON de libros en objetos `Libro`.

### Paquete `principal`

- **Principal**: Clase principal que contiene el menú de la aplicación y coordina las distintas funcionalidades.
    - `mostrarMenu()`: Presenta las opciones del menú al usuario.
    - Métodos privados para cada funcionalidad: `buscarLibroPorLibro()`, `ListarLibrosBuscados()`, `ListarAutorPorLibro()`, `autoresVivosFecha()`, `EstadisticasLibros()`.

### Paquete `model`

- **Libro**: Entidad que representa un libro.
    - Atributos: `Id`, `titulo`, `numeroDescargas`, `autor`, `idiomas`.
    - Relaciones: `@ManyToOne` con `Autor`.
- **Autor**: Entidad que representa un autor.
    - Atributos: `Id`, `nombre`, `fechaDeNacimiento`, `fechaDeMuerte`.
    - Relaciones: `@OneToMany` con `Libro`.
- **DatosLibros**: Clase para mapear los datos JSON de libros obtenidos de la API.
- **DatosAutor**: Clase para mapear los datos JSON de autores obtenidos de la API.
- **Datos**: Clase que encapsula una lista de `DatosLibros`.

## Configuración del Proyecto

### Requisitos

- Java 11 o superior
- Maven
- Spring Boot
- MySQL o cualquier base de datos compatible con JPA

### Instrucciones de Instalación

1. Clonar el repositorio:
    ```bash
    git clone https://github.com/tuusuario/DesafioLiteralura.git
    ```
2. Navegar al directorio del proyecto:
    ```bash
    cd DesafioLiteralura
    ```
3. Configurar las propiedades de la base de datos en `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/literalura
    spring.datasource.username=tuusuario
    spring.datasource.password=tupassword
    spring.jpa.hibernate.ddl-auto=update
    ```
4. Compilar y ejecutar el proyecto:
    ```bash
    mvn spring-boot:run
    ```

### Uso

Al ejecutar la aplicación, se mostrará un menú con las opciones disponibles. Siga las instrucciones en pantalla para buscar libros, listar libros y autores, y generar estadísticas.

## Dependencias

- **Spring Boot Starter Data JPA**: Para operaciones con la base de datos.
- **Spring Boot Starter Web**: Para construir aplicaciones web.
- **Spring Boot Starter Test**: Para pruebas unitarias e integración.
- **Hibernate**: ORM para manejar las entidades JPA.
- **MySQL Connector**: Para conectar con la base de datos MySQL.
- **Jackson Databind**: Para convertir JSON a objetos Java y viceversa.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, envía un pull request con una descripción detallada de los cambios.




