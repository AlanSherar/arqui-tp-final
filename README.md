# Proyecto de Arquitectura de Microservicios - Alquiler de Monopatines Eléctricos

Este repositorio contiene el desarrollo de un backend utilizando la **arquitectura de microservicios** para un sistema de **alquiler de monopatines eléctricos** en una ciudad. El proyecto se ha realizado como parte de la materia **Arquitecturas Web** y abarca varios conceptos fundamentales, como la implementación de un **Eureka Server**, un **Gateway** y la **seguridad de endpoints con roles** mediante **Spring Security**.

## Descripción del Proyecto

El proyecto consiste en un sistema backend basado en microservicios para gestionar el alquiler de monopatines eléctricos en una ciudad. Se desarrollaron varios servicios independientes que permiten manejar la información relacionada con el registro de usuarios, alquiler de monopatines, administración de pagos y más.

### Componentes principales

1. **Eureka Server**: Servidor de descubrimiento de servicios que permite que los microservicios se registren y encuentren entre sí sin necesidad de una configuración manual de direcciones.
2. **Gateway**: Un gateway de API que centraliza las solicitudes de los usuarios hacia los microservicios. Este componente también gestiona la autenticación y autorización mediante Spring Security.
3. **Microservicios**: Cada uno de los servicios, como el servicio de usuarios, servicio de monopatines, servicio de pagos, etc., funcionan de manera independiente, permitiendo la escalabilidad y el mantenimiento eficiente del sistema.
4. **Spring Security**: Configuración de seguridad en el Gateway y microservicios para asegurar que los endpoints sean accesibles solo por usuarios con los roles adecuados (por ejemplo, encargado, admin, usuario).

## Conceptos Aprendidos

1. **Arquitectura de Microservicios**: La arquitectura de microservicios implica dividir una aplicación en varios servicios pequeños y autónomos, cada uno con su propia base de datos y lógica de negocio. Esta arquitectura permite escalar, actualizar y mantener los servicios de manera independiente.
2. **Eureka Server**: Se implementó un **Eureka Server** para gestionar el registro y descubrimiento de servicios. Los microservicios pueden registrarse automáticamente en Eureka y descubrir otros servicios sin necesidad de conocer su dirección IP o configuración de red.
3. **API Gateway**: El **API Gateway** se encargó de enrutar las solicitudes hacia los microservicios correspondientes. Además, gestiona la seguridad de las peticiones y proporciona un punto central para aplicar políticas de autenticación y autorización.
4. **Spring Security**: Mediante **Spring Security**, se implementaron medidas de seguridad para proteger los endpoints de los microservicios. Se definieron roles como `ENCARGADO`, `USER` y `ADMIN`, permitiendo que solo los usuarios autenticados y autorizados puedan acceder a ciertos recursos.

## Arquitectura del Sistema

La arquitectura del sistema está compuesta por los siguientes elementos:

- **Eureka Server**: Registra y descubre los microservicios.
- **API Gateway**: Enruta las solicitudes y aplica autenticación y autorización.
- **Microservicios**:
  - **Servicio de Usuarios**: Gestión de registros, inicio de sesión y roles.
  - **Servicio de Monopatines**: Gestión de monopatines disponibles, ubicación y alquiler.
  - **Servicio de Pagos**: Procesamiento de pagos de alquileres.
- **Base de Datos**: Cada microservicio tiene su propia base de datos, garantizando la autonomía de los mismos.

## Tecnologías Utilizadas

- **Spring Boot**: Framework para el desarrollo de aplicaciones Java basadas en microservicios.
- **Spring Cloud**: Para el descubrimiento de servicios con **Eureka** y la configuración del Gateway.
- **Spring Security**: Para la implementación de seguridad en los endpoints.
- **Docker**: Para contenerizar los microservicios y facilitar el despliegue en diferentes entornos.
- **JPA/Hibernate**: Para la gestión de la persistencia de datos en los microservicios.

## Funcionalidades Principales

1. **Registro de Usuarios**: Los usuarios pueden registrarse en el sistema con sus datos personales.
2. **Autenticación y Autorización**: Los usuarios pueden iniciar sesión en el sistema y obtener un token JWT que les permitirá acceder a los recursos protegidos según sus roles.
3. **Alquiler de Monopatines**: Los usuarios pueden consultar la disponibilidad de monopatines en su ubicación, realizar un alquiler y gestionar su alquiler.
4. **Gestión de Monopatines (Encargado y Admin)**: Los roles **ENCARGADO** y **ADMIN** pueden gestionar monopatines, agregarlos, eliminarlos y ver el estado de los mismos.
6. **Gestión de Roles y Permisos**: Los usuarios pueden tener diferentes roles que determinan su nivel de acceso a las funcionalidades del sistema. Los roles son:
    - **USER**: Acceso básico a la consulta y alquiler de monopatines.
    - **ENCARGADO**: Acceso a funcionalidades administrativas limitadas, como la gestión de monopatines y la supervisión de alquileres.
    - **ADMIN**: Acceso completo a todas las funcionalidades, incluida la gestión de usuarios y monopatines.

## Seguridad y Autorización

Se implementó una capa de seguridad mediante **Spring Security** para proteger los servicios. Los endpoints están divididos en tres roles principales:

- **USER**: Los usuarios pueden consultar y alquilar monopatines, pero no tienen acceso a la gestión de los mismos.
- **ENCARGADO**: Los encargados pueden gestionar los monopatines, ver los alquileres activos y realizar tareas administrativas relacionadas con los monopatines.
- **ADMIN**: Los administradores tienen acceso total al sistema, pudiendo gestionar tanto los monopatines como los usuarios y otros recursos del sistema.

Cada servicio está protegido por Spring Security, y la validación de tokens JWT garantiza que solo los usuarios autenticados con los roles adecuados puedan acceder a los recursos.




[DOCUMENTACION](https://app.swaggerhub.com/apis/MALASSISIVALEN/DocumentacionArqui/3.0.0) 
