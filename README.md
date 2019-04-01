<h1> sistema metro</h1>


Ejecutar la aplicación como spring boot app e ingresar a postman


<h3>para crear un usuario</h3>
metodo post
localhost:3000/usuario/crear

{
    "nombre": "Name",
    "apellido": "Last Name",
    "identificacion": "1011111111",
    "tipoIdentificacion": "Cedula",
    "tipoPoblacion": "poblacion"
    "mail": "amarin@unac.edu.co",
    "edad": "22"
}

<h3>para asignar una civica a un usuario se debe especificar el id del usuario en este caso 1
metodo post</h3>

localhost:3000/usuario/1/asignarCivica

{
	 "saldo": 5000
}


<h3>para obtener usuarios y su civica</h3>
metodo get

localhost:3000/usuario/

<h3>para viajar y generar el trayecto se debe espicificar el id de la civica en este caso 1</h3>

metodo post
localhost:3000/usuario/viajar/1

{
    "estacionInicial": "Niquia",
    "estacionFinal": "Estrella",
    "tipoTransporte": "Metro Plus"  --- esto valida si es metro plus o integrado ver metodo calcularValorViajePorTransporte en
                                         UsuarioManagerImpl, tambien valida que tenga saldo en la civica
}

<h3> Para recargar la civica <h3>
  metodo put
Se espicifica el valor a recargar en este caso 1000, el valor no puede ser menor a 1000  
  
 localhost:3000/usuario/recargarCivica/1/1000
 
 
 <h3>Para Borrar usuario </h3>
 Se espicifica el id en este caso 1
   
 localhost:3000/usuario/1
 
 <h3>Para modificar un usuario</h3>
 solo modifica el nombre apellio y la edad, ya que la cedula es unica, se podria agregar la funcion
 
  localhost:3000/usuario/1
 
 {
    "nombre": "Name",
    "apellido": "Last Name",
    "identificacion": "1011111111",
    "tipoIdentificacion": "Cedula",
    "tipoPoblacion": "poblacion",
    "mail": "amarin@unac.edu.co",
    "edad": "22"
}
 

Para la base de datos en aplication.properties primero ejecutar el proyecto con <h5>spring.jpa.hibernate.ddl-auto=create</h5>
luego cambiar a <h5>spring.jpa.hibernate.ddl-auto=update</h5>
