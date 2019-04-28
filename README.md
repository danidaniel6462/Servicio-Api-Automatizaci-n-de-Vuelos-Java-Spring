## Servicio Api para consumir desde un sistema Front End

Este proyecto se ha realizado con Spring Boot 2.0.1, Maven, Java 8, y base PostgreSQL

##  Descargar o copiar el repositorio 

Abrir Eclipse STS si no lo tienes, descargar desde la siguiente ruta Spring https://spring.io/tools3/sts/all 

- Dentro de Eclipse se dará click derecho en el explorador de paquetes `Package Explorer` y seleccionar la opción
`import/General/Existing Projects into WorkSpace` next y seleccionar la ruta donde se  ha descargado el proyecto de Maven

- Una vez importado dar click derecho sobre la carpeta importada y seleccionar la opción `maven/update Project` con esto se 
descargarán las dependencias necesarias para poder ejecutar el proyecto

## Archivo de configuración para conexión a la Base de Datos PostgreSQL

`Disponible en la siguiente ruta` https://github.com/danidaniel6462/Servicio-Api-Automatizaci-n-de-Vuelos-Java-Spring/blob/master/src/main/resources/application.properties

## Pasos necesarios en PostgreSQL para ejecutar una conexión correcta con el servicio

- Abrir postgreSQL y crear una nueva tabla con el nombre vuelos_compania, debe estar creada antes de ejecutar el proyecto en Spring, 
así se crearán las tablas automáticamente en la base de datos, para revisar el esquema de la base, está disponible en https://github.com/danidaniel6462/Servicio-Api-Automatizaci-n-de-Vuelos-Java-Spring/blob/master/src/main/resources/ERDEjercicioVuelo.pdf

## Ejecutar el proyecto Spring Boot

Simple y sencillamente dar click derecho sobre la carpeta del proyecto y seleccionar `Run as\Spring Boot App` para desplegar el servicio

## Felicidades

Para este momento ya debes estar corriendo el servicio, puedes cambiar el server.port dentro de application.properties para ejecutarlo en uno diferente

## api vuelos compañía

El servicio esta habilitado para WebSecurity, es decir la mayoría de las rutas necesitan `Autorización Bearer`

- Existen 3 rutas que son públicas:
  - /login
  - /api/usuario/signIn
  - /api/boleto
- El resto de url necesitan autorización a través de un token generado

es posible crear un usuario con password, al enviar este objeto a la ruta ejemplo: http://localhost:8087/login y por POST enviar
=> 
`{
	"username": "softwareevolutivo",
	"password": "softevo"
`}

o utilizando el siguiente Script sql que se ejecuta dentro de la tabla de Usuario en la base de datos POSTGRESQL

`insert into Usuario values (1, 'usuario', 'software@evolutivo.com', 'apellido', '$2a$10$KkAQqutePhJZIqVUOsVo8efFF4rHEQdjxXwxKaKbqLhT2fDbk0y5G', 'softwareevolutivo')`

Aquel Script ya tiene creado el password encryptado, así se utilizaría un POST con 

=>
`{
	"username": "softwareevolutivo",
	"password": "softevo"
}`

una vez que se reciba el Token en la Cabecera se debe enviar en todas las peticiones posteriores para obtener datos de la base

## Puedes utilizar Postman o cualquier software para hacer peticiones al servicio o puedes crear tu aplicación FronEnd para probar los servicios
si te sirve de guía existe un proyecto realizado con Angular que utiliza este servicio en https://github.com/danidaniel6462/Cliente-Automatizacion-de-vuelos-ANGULAR/blob/master/README.md

## las rutas disponibles son las siguientes:

`/api/boleto/{id}],methods=[PUT]
/api/boleto/actualizarBoletoObtenerLista],methods=[POST]
/api/boleto],methods=[POST]
/api/boleto/{id}],methods=[DELETE]
/api/boleto/{id}],methods=[GET]
/api/boleto],methods=[GET]
/api/perfil/{id}],methods=[PUT]
/api/perfil],methods=[POST]
/api/perfil/{id}],methods=[DELETE]
/api/perfil/{id}],methods=[GET]
/api/perfil],methods=[GET]
/api/perfilUsuario/{id}],methods=[PUT]
/api/perfilUsuario],methods=[POST]
/api/perfilUsuario/{id}],methods=[DELETE]
/api/perfilUsuario/{id}],methods=[GET]
/api/perfilUsuario],methods=[GET]
/api/usuario/{id}],methods=[PUT]
/api/usuario/obtenerPorUsername],methods=[GET]
/api/usuario/signIn],methods=[POST]
/api/usuario/{id}],methods=[DELETE]
/api/usuario/{id}],methods=[GET]
/api/usuario],methods=[GET]
`

