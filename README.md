# Conversor de Moneda - Challenge ONE (Java BackEnd)

Aplicacion de consola para convertir montos entre USD, ARS, BRL y COP usando ExchangeRate-API.

## Funcionalidades
- Conversion en tiempo real con ExchangeRate-API.
- Menu interactivo con validacion basica de opcion y monto.
- Arquitectura simple y directa (classes `Currency`, `Option`, `ExchangeRateAPIResponse`).

## Requisitos
- JDK 21+ (usa `record` y `List.getFirst()`). El proyecto esta configurado con JDK 25.
- Gson 2.13+ en el classpath.
- Conexion a internet.
- API key de ExchangeRate-API.

## Configuracion
1. Crea una cuenta en https://www.exchangerate-api.com/ y genera una API key.
2. Reemplaza la constante `API_KEY` en `src/Currency.java`.
3. Agrega Gson al proyecto:
   - IntelliJ: File > Project Structure > Libraries > + Java y selecciona `gson-2.13.2.jar`.
   - CLI: descarga el jar y usa `-cp` al compilar/ejecutar.

## Ejecucion
Este proyecto no usa Maven/Gradle. Puedes ejecutar desde IntelliJ o consola.

Si tu IDE/CLI requiere la firma estandar de entrada, cambia `static void main()` a:
```java
public static void main(String[] args)
```

Ejemplo de ejecucion por consola (tras tener la firma estandar):
```powershell
javac -cp "lib\gson-2.13.2.jar" src\*.java
java -cp "src;lib\gson-2.13.2.jar" CurrenciesConversor
```

## Uso
Al iniciar veras un menu con estas opciones:
```
1) Dolar -> Peso argentino
2) Peso argentino -> Dolar
3) Dolar -> Real brasileno
4) Real brasileno -> Dolar
5) Dolar -> Peso colombiano
6) Peso colombiano -> Dolar
7) Salir
```
Ingresa una opcion y luego el monto a convertir.

## Estructura del proyecto
- `src/CurrenciesConversor.java`: menu, flujo principal y salida por consola.
- `src/Currency.java`: llamada HTTP al API y logica de conversion.
- `src/ExchangeRateAPIResponse.java`: modelo del JSON de respuesta.
- `src/Option.java`: enum con las opciones del menu.

## Notas
- La API key esta en el codigo fuente; evita compartirla o commitearla en repos publicos.
- Si quieres mas monedas, agrega nuevas opciones en `Option` y en la lista de `currencies`.

## Licencia
No especificada.
