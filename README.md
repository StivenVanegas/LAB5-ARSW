
### Escuela Colombiana de Ingeniería

### Arquitecturas de Software


## Laboratorio API REST para la gestión de planos

### Dependencias
* [Laboratorio Componentes y conectores Middleware- gestión de planos (Blueprints) Parte 1](https://github.com/ARSW-ECI-beta/REST_API-JAVA-BLUEPRINTS_PART1)

### Descripción
En este ejercicio se va a construír el componente BlueprintsRESTAPI, el cual permita gestionar los planos arquitectónicos de una prestigiosa compañia de diseño. La idea de este API es ofrecer un medio estandarizado e 'independiente de la plataforma' para que las herramientas que se desarrollen a futuro para la compañía puedan gestionar los planos de forma centralizada.
El siguiente, es el diagrama de componentes que corresponde a las decisiones arquitectónicas planteadas al inicio del proyecto:

![](img/CompDiag.png)

Donde se definió que:

* El componente BlueprintsRESTAPI debe resolver los servicios de su interfaz a través de un componente de servicios, el cual -a su vez- estará asociado con un componente que provea el esquema de persistencia. Es decir, se quiere un bajo acoplamiento entre el API, la implementación de los servicios, y el esquema de persistencia usado por los mismos.

Del anterior diagrama de componentes (de alto nivel), se desprendió el siguiente diseño detallado, cuando se decidió que el API estará implementado usando el esquema de inyección de dependencias de Spring (el cual requiere aplicar el principio de Inversión de Dependencias), la extensión SpringMVC para definir los servicios REST, y SpringBoot para la configurar la aplicación:


![](img/ClassDiagram.png)

### Parte I

1. Integre al proyecto base suministrado los Beans desarrollados en el ejercicio anterior. Sólo copie las clases, NO los archivos de configuración. Rectifique que se tenga correctamente configurado el esquema de inyección de dependencias con las anotaciones @Service y @Autowired.

     - se agregaron las dependencias, del lab4
     
      ![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte%201%2C%20item%201.png)
	
     - se implemento la parte requerida
     - ![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte%201%2C%20item%201%20parte%201.png)

2. Modifique el bean de persistecia 'InMemoryBlueprintPersistence' para que por defecto se inicialice con al menos otros tres planos, y con dos asociados a un mismo autor.

     - Se implemento bien la persistencia para que por defecto inicialice con al menos otros tres planos.
     
     ![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte1%2C%20item2.png)

3. Configure su aplicación para que ofrezca el recurso "/blueprints", de manera que cuando se le haga una petición GET, retorne -en formato jSON- el conjunto de todos los planos. Para esto:

	* Modifique la clase BlueprintAPIController teniendo en cuenta el siguiente ejemplo de controlador REST hecho con SpringMVC/SpringBoot:

	```java
	@RestController
	@RequestMapping(value = "/url-raiz-recurso")
	public class XXController {
    
        
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoXX(){
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(data,HttpStatus.ACCEPTED);
        } catch (XXException ex) {
            Logger.getLogger(XXController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
        }        
	}

	```
	* Haga que en esta misma clase se inyecte el bean de tipo BlueprintServices (al cual, a su vez, se le inyectarán sus dependencias de persisntecia y de filtrado de puntos).

	- Implementación de la inyección de la clase en el Bean, así como la inyección en dependencias de persistencia y de filtrado de puntos.
	
	
	![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte%201%2C%20item%203.PNG)

4. Verifique el funcionamiento de a aplicación lanzando la aplicación con maven:

	```bash
	$ mvn compile
	$ mvn spring-boot:run
	
	```
	Y luego enviando una petición GET a: http://localhost:8080/blueprints. Rectifique que, como respuesta, se obtenga un objeto jSON con una lista que contenga el detalle de los planos suministados por defecto, y que se haya aplicado el filtrado de puntos correspondiente.
	
	- obtención de la verificación de un objeto en JSON, en donde se implementa en una lista el detalle de los planes suministrados y la aplicación del filtro.



	![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte1%2C%20item%204.PNG)


5. Modifique el controlador para que ahora, acepte peticiones GET al recurso /blueprints/{author}, el cual retorne usando una representación jSON todos los planos realizados por el autor cuyo nombre sea {author}. Si no existe dicho autor, se debe responder con el código de error HTTP 404. Para esto, revise en [la documentación de Spring](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html), sección 22.3.2, el uso de @PathVariable. De nuevo, verifique que al hacer una petición GET -por ejemplo- a recurso http://localhost:8080/blueprints/juan, se obtenga en formato jSON el conjunto de planos asociados al autor 'juan' (ajuste esto a los nombres de autor usados en el punto 2).

	- Retorno del controlador usando representación de JSON, en donde todos los planes son del autor.


	![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte%201%2C%20item%205-1.PNG)
	
	- Verificación y uso de la petición GET, al hacer una petición en una lista.


	![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte%201%2C%20item%205-2.PNG)
	
	- Retorno del error HTTP 404, Debido a que no existe dicho autor
	
	
	![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte%201%2C%20item%205-3.PNG)

6. Modifique el controlador para que ahora, acepte peticiones GET al recurso /blueprints/{author}/{bpname}, el cual retorne usando una representación jSON sólo UN plano, en este caso el realizado por {author} y cuyo nombre sea {bpname}. De nuevo, si no existe dicho autor, se debe responder con el código de error HTTP 404. 

	- Modificación del controlador para que acepte peticiones GET al recurso.
	
	
	![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte%201%2C%20item%206-1.PNG)
	
	- Verificación de existencia del autor, en este caso si existe y retorna el siguiente mensaje.


	![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte%201%2C%20item%206-2.PNG)




### Parte II

1.  Agregue el manejo de peticiones POST (creación de nuevos planos), de manera que un cliente http pueda registrar una nueva orden haciendo una petición POST al recurso ‘planos’, y enviando como contenido de la petición todo el detalle de dicho recurso a través de un documento jSON. Para esto, tenga en cuenta el siguiente ejemplo, que considera -por consistencia con el protocolo HTTP- el manejo de códigos de estados HTTP (en caso de éxito o error):

	```	java
	@RequestMapping(method = RequestMethod.POST)	
	public ResponseEntity<?> manejadorPostRecursoXX(@RequestBody TipoXX o){
        try {
            //registrar dato
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (XXException ex) {
            Logger.getLogger(XXController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.FORBIDDEN);            
        }        
 	
	}
	```	

	- Se agrega el manejo de peticiones POST, para que el cliente pueda registrar una nueva orden.
	![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte%202%2C%20item%201.PNG)
	

2.  Para probar que el recurso ‘planos’ acepta e interpreta
    correctamente las peticiones POST, use el comando curl de Unix. Este
    comando tiene como parámetro el tipo de contenido manejado (en este
    caso jSON), y el ‘cuerpo del mensaje’ que irá con la petición, lo
    cual en este caso debe ser un documento jSON equivalente a la clase
    Cliente (donde en lugar de {ObjetoJSON}, se usará un objeto jSON correspondiente a una nueva orden:

	```	
	$ curl -i -X POST -HContent-Type:application/json -HAccept:application/json http://URL_del_recurso_ordenes -d '{ObjetoJSON}'
	```	

	Con lo anterior, registre un nuevo plano (para 'diseñar' un objeto jSON, puede usar [esta herramienta](http://www.jsoneditoronline.org/)):
	

	Nota: puede basarse en el formato jSON mostrado en el navegador al consultar una orden con el método GET.
	

	- Se hace uso del código suministrado para ejecutar la petición con JSON, y verificar que la este realizando de forma correcta.
	
	![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte%202%2C%20item%202.PNG)
	


3. Teniendo en cuenta el autor y numbre del plano registrado, verifique que el mismo se pueda obtener mediante una petición GET al recurso '/blueprints/{author}/{bpname}' correspondiente.

	- Se verifica en el browser que la petición realizada con JSON sirva para retornar el recurso correspondiente.
	
	![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte%202%2C%20item%203.PNG)

4. Agregue soporte al verbo PUT para los recursos de la forma '/blueprints/{author}/{bpname}', de manera que sea posible actualizar un plano determinado.

	- Se crea el metodo PUT, con el fin de que pueda solicitar los servicios de la forma: /blueprints/{author}/{bpname}, para 	    que este pueda actualizar el plano.
	
	![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte%202%2C%20item%204-1.PNG)
	
	
	- Se procede a mostrar la ejecucion de dicho metodo mediande el browser y solicitando el plano Stiven.


	![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte%202%2C%20item%204-2.PNG)
	
	- luego hacemos la ejecución del comando JSON para verificar que todo lo realizado anteriormente compila y ejecuta    		   correctamente. 

	![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte%202%2C%20item%204-3.PNG)
	
	- Por último cambiamos los valores de los planos, para verificar que se esta ejecutando bien el JSON y procedemos a                 correrlo en el localHost y verificar que los valores ingresados estén plasmados y correctos.

	
	![texto cualquiera por si no carga la imagen](https://github.com/StivenVanegas/LAB5-ARSW/blob/master/img/parte%202%2C%20item%204-4.PNG)
	


### Parte III

El componente BlueprintsRESTAPI funcionará en un entorno concurrente. Es decir, atederá múltiples peticiones simultáneamente (con el stack de aplicaciones usado, dichas peticiones se atenderán por defecto a través múltiples de hilos). Dado lo anterior, debe hacer una revisión de su API (una vez funcione), e identificar:

* Qué condiciones de carrera se podrían presentar?
* Cuales son las respectivas regiones críticas?

Ajuste el código para suprimir las condiciones de carrera. Tengan en cuenta que simplemente sincronizar el acceso a las operaciones de persistencia/consulta DEGRADARÁ SIGNIFICATIVAMENTE el desempeño de API, por lo cual se deben buscar estrategias alternativas.

Escriba su análisis y la solución aplicada en el archivo ANALISIS_CONCURRENCIA.txt

 ### Desarrollo ###

-  Las condiciones de carrera se presentan cuando se van a realizar la actualización de sobre los planos, ocasionando que cuando varios usuarios al tiempo quieren   	           actualizar el mismo plano, se presenta la condición de carrera.
	
- Las regiones críticas se presentan en dos casos:
    1. Cuando se hace la actualización de los planos, ya que pueden estar varios clientes actualizando el mismo plano, ocasionando una región critica.
    2. Cuando se hacen cambios en MAP que contiene los planos, que es de tipo contenedor, podemos evidenciar que se genera una región critica, pues al actualizar los                    planos, no se vería reglado al mismo tiempo.

