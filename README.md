# Persistencia  
## Sección I. Introducción a JDBC  
* Clonamos el proyecto

* Descargamos el archivo ```JDBCExample.java``` en el paquete ```edu.eci.cvds.sampleprj.jdbc.example```
![image](https://user-images.githubusercontent.com/59893804/94978306-6c3d2a80-04e2-11eb-8115-4e0263511617.png)

* En la clase ```JDBCExample``` ajuste los siguientes parámetros
     ```
     URL: jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba
     DRIVER: com.mysql.jdbc.Driver
     USUARIO: bdprueba 
     CONTRASEÑA: prueba2019 
     ```				 
 ![image](https://user-images.githubusercontent.com/59893804/94978280-4a43a800-04e2-11eb-95c3-21e45800f3c4.png)
 
* Hacemos la conexión a la base de datos 
![image](https://user-images.githubusercontent.com/59893804/94978620-d99d8b00-04e3-11eb-9b83-b786971f3bb0.png)
 
* Implementamos las operaciones faltantes 
![image](https://user-images.githubusercontent.com/59893804/94978357-a3abd700-04e2-11eb-827e-d6442dde5305.png)

* Verificamos que la información retornada por el programa coincide con la que se encuentra almacenada en la base de datos.
![image](https://user-images.githubusercontent.com/59893804/94978431-f5546180-04e2-11eb-973f-92a48ecea32f.png)

## Sección II. Introducción a MyBatis
* Revisamos la documentación básica de MyBatis y seguimos las instrucciones que se encuentran en el repositorio 

### Parte I 
* Ubicamos los archivos de configuración para producción de MyBATIS (mybatis-config.xml). Editamos y agregamos _'typeAliases'_
![image](https://user-images.githubusercontent.com/59893804/94978698-38fb9b00-04e4-11eb-93ec-76cde4f0e1d2.png)

* Configuraremos un mapper que permita que el framework reconstruya todos los objetos _Cliente_ con sus detalles.
![image](https://user-images.githubusercontent.com/59893804/94978815-bb845a80-04e4-11eb-92da-787273eb9264.png)

* Ejecutamos una sentencia en un cliente SQL y revisamos el resultado a la consulta propuesta.
![image](https://user-images.githubusercontent.com/59893804/94978825-d060ee00-04e4-11eb-90a4-c1a148fac62b.png)

![image](https://user-images.githubusercontent.com/59893804/94978832-d951bf80-04e4-11eb-8304-79f996af374e.png)

* En el XML ```ClienteMapper.xml``` mapeamos un elemento de tipo ```<select>``` al método _'consultarClientes'_ 
![image](https://user-images.githubusercontent.com/59893804/94978902-5ed56f80-04e5-11eb-8588-76b91782a581.png)

* En el XML agregamos un elemento de tipo ```<resultMap>``` 
y en el elemento ```<collection>``` agregamos una propiedad que indique cual es el _'resultMap'_ 
![image](https://user-images.githubusercontent.com/59893804/94979157-db1c8280-04e6-11eb-805e-e279834a7595.png)

### Parte II 
* Configuramos la operación _consultarCliente(int id)_ del ```ClienteMapper```.En este caso se debe usar en el WHERE de sus correspondiente sentencia SQL

![image](https://user-images.githubusercontent.com/59893804/94979872-191ba580-04eb-11eb-81e7-230b090f0f90.png)

* Configuramos la operacion _insertarItem(Item it)_
![image](https://user-images.githubusercontent.com/59893804/94979941-71eb3e00-04eb-11eb-8edd-f1390db6bd1d.png)

* Configuramos las operaciones _'consultarItem(int it)_ y _'consultarItems()'_
![image](https://user-images.githubusercontent.com/59893804/94979978-b545ac80-04eb-11eb-8917-4e1b7a8eb9a8.png)
 
 * Por ultimo adicionamos el _'resultMap'_ necesario para las operaciones 
 ![image](https://user-images.githubusercontent.com/59893804/94980031-f938b180-04eb-11eb-8b75-2445d619570c.png)
 

