# cliente-servidor

Este laboratorio fue realizado con fines academicos.
consiste en realizar un servidor web implementado la reflectividad para crear un frameworks artesanal para entender el funcionamiento de estos, ie, como son implementados.

## Despliegue Heroku
[![ProjectDesign](https://www.herokucdn.com/deploy/button.png)](https://arep-server.herokuapp.com/)
## Comenzando 🚀

A continuación se presentarán series de instrucciones para que se pueda tener un funcionamiento en maquina local del proyecto


### Pre-requisitos 📋

Para el correcto funcionamiento en un ambiente de desarrollo se deberán tener instaladas las siguientes tecnologías:  
* [Git](https://git-scm.com/) - Software de control de versiones
* [Java 8](https://www.java.com/es/download/ie_manual.jsp) - Entorno de desarrollo
* [Maven](https://maven.apache.org/) - Manejador de dependencias


### Instalación 🔧

1. **Clonar el proyecto**
    ```
    https://github.com/DamGaFlo/Reflexion
    ```
2. **Compilar el proyecto**
    ```
    mvn package
    ```
3. **Ejecutar la aplicación**  
    * Para entornos Windows
        ```
        java -cp "target/classes;target/dependency/*" co.edu.escuelaing.arep.labHttpServer.HttpServer
        ``` 
    * Para entornos Unix
        ```
        java $JAVA_OPTS -cp target/classes:target/dependency/* co.edu.escuelaing.arep.labHttpServer.HttpServer
        ```
4. **Servicio Web**
Para observar la pagina web se debera
    ```
    localhost:4567
    ```
    Esto redireccionará al archivo index.html del proyecto
    
## Servicios soportados son:

    
    GET /image
    
    
retorna una imagen predefinida

    
    GET /
    
Retorna el index de la pagina tipo html
## Ejecutando las pruebas ⚙️

Para la ejecución de las pruebas
```
mvn test
```

## Arquitectura
Se cuenta con:
* Una clase servidor que funciona implemetando la lectura de las peticiones

* esta clases es un singleton que responde a las peticiones leyendo de disco


## Construido con 🛠️

* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [JUnit](https://junit.org/junit5/) - Herramienta para pruebas unitarias en Java

## Documentación 📖
Para generar la documentación
```
mvn javadoc:javadoc
```
Esta se va a encontrar ```target/site/apidocs/index.html```

## Autores ✒️

* **Johan Damian Garrido Florez**

## Licencia 📄

Este proyecto está bajo la Licencia GNU General Public License mire el archivo [LICENSE.md](LICENSE.md) para detalles
