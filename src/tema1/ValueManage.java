package tema1;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;

/*
 * Resumen rápido: este bloque pide dominar tipos primitivos y sus wrappers,
 * promoción y conversión de tipos, evaluar expresiones aritméticas/booleanas
 * (precedencia, casting), API Math, manejo de texto (String, StringBuilder,
 * text blocks) y la API moderna de fecha/hora (java.time), incluyendo
 * comportamientos con zonas y DST.
 */
public class ValueManage {

    public ValueManage() {
    }

    /*
    * Primitivos en Java (definición y tamaños)
    * byte (8 bits, signed), short (16 bits), int (32 bits), long (64 bits) — enteros.
    * float (32 bits IEEE 754), double (64 bits IEEE 754) — punto flotante.
    * char (16 bits, UTF-16 code unit).
    * boolean (true/false).
    */
    public void primitives(){
        byte byteNum = 1;
        short shortNum = 2;
        int intNum = 3;
        long longNum = 4;
        float floatNum = 5;
        double doubleNum = 6.5;
        char charFormat = 'a';
        boolean bool = true;

        // Casos especiales en double
        System.out.println(10.0/0.0);   // Infinity
        System.out.println(-10.0/0.0);  // -Infinity
        System.out.println(0.0/0.0);    // NaN
    }

    /*
    * Wrappers
    * Clases objeto para cada primitivo: Byte, Short, Integer, Long, Float, Double, Character, Boolean.
    * Autoboxing / unboxing: conversión automática entre primitivo y wrapper (Integer x = 5; int y = x;).
    * Peligros: si un wrapper es null y se hace un unboxing → NullPointerException.
    */
    public void wrappers(){

        //autoboxing de wrappers
        Byte byteNum = 1;
        Short shortNum = 2;
        Integer intNum = 3;
        Long longNum = 4L;
        Float floatNum = 5.5f;
        Double doubleNum = 6.6;
        Character charNum = 'a';
        Boolean boolNum = true;

        //unboxing test
        int testNum = intNum;

        System.out.println(testNum);
    }

    /*
    * Caching de wrappers
    * Implementación estándar (Oracle JVM) cachea algunos valores
    * (p. ej. Integer.valueOf(int) suele cachear -128..127).
    * Por eso Integer a = 100; Integer b = 100; a==b puede ser true,
    * pero para 1000 es false (objetos distintos).
    * new Integer(5) crea siempre una instancia nueva.
    */
    public void wrapperCaching() {
        Integer intNum = 100;
        Integer intNum2 = 100;

        System.out.println("Integer comparación");
        System.out.println("100==100: "+(intNum==intNum2));

        intNum = 1000;
        intNum2 = 1000;

        System.out.println("1000==1000: "+(intNum==intNum2));

        //pero si se usa equals es otro cuento
        System.out.println("1000.equals(1000): "+(intNum.equals(intNum2)));
    }

    /*
    * Promoción numérica y conversiones
    *
    * Widening (ensanchamiento) (implícito): byte -> short -> int -> long -> float -> double. char -> int.
    * Narrowing (estrechamiento) (explícito): requiere cast: int i = (int) longValue;.
    *
    * Promoción binaria: operaciones entre operandos pequeños (byte, short, char)
    * se promueven a int antes de operar. Ej.: byte + byte → int.
    *
    * Asignación compuesta (+=, -= ...) realiza conversión implícita al tipo de la variable
    * objetivo (evita el cast explícito).
    */
    public void numericPromotionAndConversion() {
        short s = 1;
        System.out.println("short s = 1");

        System.out.println("s = s + 1; ERROR: s+1 es int (Auto cast)");
        s += 1;    // OK: compund assignment hace el cast implícito
        System.out.println("s += 1;    // OK: compund assignment hace el cast implícito");
        System.out.println("Casted: "+s);

        //Conserva formato
        int i = 5/2;
        System.out.println("int i = 5/2: = "+i);

        //cast
        //recordar que double (64 bits, 8 bytes,15-16 dígitos decimales) es más preciso que float (32 bits, 4 bytes,7 dígitos decimales)
        double d = (double) i /3;
        System.out.println("double d = (double) i/3: = "+d);
    }

    /*
     * Overflow / underflow
     * En Java, los tipos primitivos enteros (byte, short, int, long) no lanzan excepción cuando se salen de su rango.
     * Si se excede el máximo, se “da la vuelta” al mínimo (overflow). Si se baja del mínimo, se da la vuelta al máximo (underflow).
     * Enteros overflow no lanzan excepción (envuelven). Usa Math.addExact,
     * Math.multiplyExact para detectar overflow con excepción.
    */
    public void overFlowAndUnderFlow() {
        int max = Integer.MAX_VALUE;   // 2147483647
        System.out.println(max + 1);   // -2147483648 (overflow)

        int min = Integer.MIN_VALUE;   // -2147483648
        System.out.println(min - 1);   // 2147483647 (underflow)


        int a = Integer.MAX_VALUE;
        try {
            int result = Math.addExact(a, 1); // ¡Explota!
        } catch (ArithmeticException e) {
            System.out.println("Overflow detectado");
        }
    }

    /*
    * Operadores booleanos y precedencia
    * En Java, la precedencia de operadores determina el orden en que se evalúan expresiones si no usas paréntesis.
    *
    * Reglas básicas:
    * ! (NOT lógico) tiene más prioridad que && (AND) y || (OR).
    * && tiene más prioridad que ||.
    * Entonces: true || false && false → true || (false && false) → true.
    *
    * Cortocircuito:
    * && → si el primer operando es false, no evalúa el segundo.
    * || → si el primer operando es true, no evalúa el segundo.
    */
    public void operatorsAndPrecedence() {
        int a = 5, b = 10, c = 0;

        // Precedencia aritmética
        System.out.println("a+b*2 = " + (a + b*2));     // 25
        System.out.println("(a+b)*2 = " + ((a+b)*2));   // 30

        // Booleanos + precedencia (! > && > ||)
        boolean cond = true || false && false; // true || (false && false) -> true
        System.out.println("true || false && false = " + cond);

        // Cortocircuito
        boolean safe = (c != 0) && (10/c > 1); // no evalúa segunda
        System.out.println("Cortocircuito evita crash: " + safe);

        // Bitwise
        boolean bitwise = (a < b) & (10 / (c+1) > 5); // evalúa ambas
        System.out.println("Bitwise &: " + bitwise);

        // Ternario
        String msg = (a < b) ? "a < b" : "a >= b";
        System.out.println("Operador ternario: " + msg);

        // Prioridad compleja
        int res = 1 + 2 << 1; // (1+2)<<1 = 3<<1 = 6
        System.out.println("1+2<<1 = " + res);
    }

    /*
    * API Math (importante en examen)
    * Math.abs, Math.max, Math.min, Math.pow, Math.sqrt, Math.round, Math.ceil, Math.floor, Math.random().
    * Math.addExact, subtractExact, multiplyExact para detectar overflow.
    *
    * Ten en cuenta precisión de float/double y uso de BigDecimal para cálculos monetarios:
    * usar new BigDecimal(String) o BigDecimal.valueOf(double) en vez de new BigDecimal(0.1)
    * para evitar errores de representación.
    */
    public void apiMath() {
        double x = -3.7, y = 2.4;

        System.out.println("Math.abs(-3.7) = " + Math.abs(x));   // 3.7 Obtener valor absoluto
        System.out.println("Math.max(-3.7, 2.4) = " + Math.max(x, y)); // 2.4 Devuelve el mayor de dós números
        System.out.println("Math.min(-3.7, 2.4) = " + Math.min(x, y)); // -3.7 Devuelve el menor de dós números

        System.out.println("Math.pow(2, 3) = " + Math.pow(2, 3)); // 8.0 Potenciación el primer parámetro es la base y el segundo el exponente
        System.out.println("Math.sqrt(16) = " + Math.sqrt(16));   // 4.0 Saca la raíz cuadrada de ún número.

        System.out.println("Math.round(2.5) = " + Math.round(2.5)); // 3 (long) Redondea a entero un valor float o double, ante la duda sube.
        System.out.println("Math.floor(2.9) = " + Math.floor(2.9)); // 2.0 Redondea hacia abajo un valor float o double
        System.out.println("Math.ceil(2.1) = " + Math.ceil(2.1));   // 3.0 Redondea hacia arriba un valor float o double

        // Random entre 0.0 y 1.0
        double random = Math.random();
        System.out.println("Math.random() = " + random);

        // Detectar overflow con addExact
        try {
            int max = Integer.MAX_VALUE;
            Math.addExact(max, 1);
        } catch (ArithmeticException e) {
            System.out.println("Overflow detectado con Math.addExact()");
        }

        //Precisión de float/double y uso de BigDecimal
        //float y double son binarios, no representan todos los decimales exactamente.
        System.out.println(0.1 + 0.2); // 0.30000000000000004
        //Solución -> usar BigDecimal
        BigDecimal a = new BigDecimal("0.1"); // CORRECTO
        BigDecimal b = new BigDecimal("0.2");
        System.out.println(a.add(b)); // 0.3

        BigDecimal wrong = new BigDecimal(0.1); //Error común: Porque toma la representación binaria inexacta de 0.1.
        System.out.println(wrong); // 0.10000000000000000555...

        BigDecimal correct = new BigDecimal("0.1"); //Desde String toma la representación correcta
        System.out.println(correct);
    }

    /*
    * Strings y StringBuilder
    *
    * String es inmutable
    * StringBuilder es mutable
    * Text Blocks (Java 15+)
    */
    public void strings() {
        String s = "Java";
        s.concat("21");
        System.out.println("Inmutable: " + s);

        String sc = s.concat("21");
        System.out.println("Nuevo String: " + sc);

        // Métodos clave
        System.out.println("substring: " + sc.substring(1,3)); // "av"
        System.out.println("replace: " + sc.replace("Java","Kava"));
        System.out.println("equalsIgnoreCase: " + "java".equalsIgnoreCase("JAVA"));

        // StringBuilder
        StringBuilder sb = new StringBuilder("Java");
        //agrega 21 al final, luego ">> " al principio, luego lo invierte
        sb.append("21").insert(0, ">> ").reverse();
        System.out.println("StringBuilder: " + sb); //"12avaJ >>"

        // Text block
        String json = """
                {
                  "name": "Juan",
                  "age": 30
                }
                """;
        System.out.println("TextBlock: " + json);
    }

    /*
    * API de Fecha y Hora (java.time) — conceptos clave
    *
    * Clases principales (inmutables):
    * LocalDate — fecha (yyyy-mm-dd) sin hora ni zona.
    * LocalTime — hora sin fecha ni zona.
    * LocalDateTime — fecha + hora, sin zona.
    * ZonedDateTime — fecha + hora + zona (ZoneId). Incluye offset y reglas DST.
    * OffsetDateTime — fecha + hora + offset (p.ej. +02:00) sin reglas de zona completas.
    * Instant — punto en el tiempo en UTC (nanosegundos desde epoch).
    * Duration — cantidad de tiempo en segundos/nanos (time-based).
    * Period — cantidad en años/meses/días (date-based).
    */
    public void time() {
        LocalDate today = LocalDate.now();
        LocalDate birth = LocalDate.of(1995, 5, 23);
        Period p = Period.between(birth, today);
        System.out.println(p.getYears()); // diferencia en años

        //duración vs período
        Duration d = Duration.ofHours(5); // cantidad de tiempo (horas, minutos)
        Period p2 = Period.ofMonths(6);   // cantidad de tiempo en unidades de calendario

        //zonas horarias
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("America/Bogota"));
        System.out.println(zdt);

        // Formateo
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Formato: " + LocalDateTime.now().format(fmt));

        // Parseo
        LocalDate parsed = LocalDate.parse("2025-10-01");
        System.out.println("Parse: " + parsed);
    }

}
