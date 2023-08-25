# 3AC-Generator
Generador de código de tres direcciones basado en una gramática que permite asignaciones a variables. 
## Archivo de Entrada
Dentro del archivo "input.txt" se ingresan las asignaciones sobre las cuales se desea generar el código de tres direcciones. Las asignaciones tienen una sintaxis definida, la cual es la siguiente:
><*id*> = <operación matemática> ;

En donde:

**id**, puede se cualquier palabra que inicia con una letra ente a-z o A-Z seguida de n números entre 0-9 y/o letras entre a-z o A-Z.

**operación matemática**,  puede ser cualquier operación de suma, resta, multiplicacion o division, permitiendo agrupación haciendo uso de paréntesis.

Un ejemplo de una entrada valida es la siguiente:
>x = 12 * (id1 * 12) + 10;

>y = 100 - (-id1 + 12) + 10;

>z = (100 + 2 * (-9 + 1) + id) * id;

>w = -1;

El manejo de errores aun no se encuentra implementado por lo cual solo se permiten entradas válidas.

## Ejecución
Para lanzar la aplicación basta con ubicarse dentro de la carpeta ejecutable y dentro de una consola ingresar el siguiente comando:
>java -jar VarAssignment3ACGenerator.jar

La aplicación va a generar el codigo de tres direcciones correspondiente a las asignaciones que están escritas dentro del archivo "input.txt". Este archivo tiene que estar en la misma carpeta que el ejecutable, de lo contrario lanzará un error la aplicación.
