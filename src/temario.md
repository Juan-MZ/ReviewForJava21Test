# Temario Completo Para la Certificación Oracle Java SE 21 (1Z0-830)

---

# 1. Manejo de Valores y Tipos Fundamentales
**Objetivo:** Dominar completamente primitivos, wrappers, conversiones, API Math, Strings y java.time.

## 1.1 Primitivos
- Tamaños, rangos y comportamiento:
  - byte, short, int, long
  - float, double (IEEE 754)
  - char (UTF-16)
  - boolean
- Literales numéricos, binarios, hexadecimales, con guiones bajos.
- Comportamientos especiales en double/float: NaN, Infinity, -Infinity.

## 1.2 Wrappers
- Autoboxing / unboxing.
- Cache interno: Byte/Short/Integer/Long/Character.
- Comparación con `==` vs `.equals`.
- Null en unboxing → `NullPointerException`.

## 1.3 Promoción numérica
- Widening implícito.
- Narrowing con cast explícito.
- Binary numeric promotion.
- Asignación compuesta (`+=`, `-=`, etc.) y conversión implícita.

## 1.4 Overflow / Underflow
- Enteros no lanzan excepción (wrap-around).
- Métodos de Math con verificación: `addExact`, `subtractExact`, `multiplyExact`.

## 1.5 Operadores y precedencia
- Precedencia aritmética y lógica.
- Cortocircuito (`&&`, `||`) vs bitwise (`&`, `|`, `^`).
- Operador ternario.
- Operadores de desplazamiento (`<<`, `>>`, `>>>`).

## 1.6 API Math
- abs, max, min, pow, sqrt, round, floor, ceil.
- Random (Math.random vs Random.next...).

## 1.7 String y StringBuilder
- Inmutabilidad del String.
- String pool.
- Métodos esenciales: substring, replace, indexOf, trim, strip, formatted.
- StringBuilder, StringBuffer (diferencias, sincronización).
- Text blocks (""").

## 1.8 BigDecimal / BigInteger
- Problemas de precisión en double/float.
- Uso correcto: `new BigDecimal(String)` y `valueOf`.

## 1.9 API java.time (Muy importante)
- LocalDate, LocalTime, LocalDateTime.
- ZonedDateTime, OffsetDateTime, OffsetTime.
- Instant, Duration, Period.
- Reglas con zonas, horario de verano (DST).
- DateTimeFormatter (format/parse).
- LocalDate.parse y errores comunes.

---

# 2. Programación Orientada a Objetos (OOP) en Java
## 2.1 Clases y objetos
- Constructores, sobrecarga.
- Encapsulamiento, getters/setters modernos (records).
- this, super.

## 2.2 Herencia
- Extends y superclases.
- Reglas de override (firmas, tipos de retorno covariantes).
- Llamada a `super(...)`.

## 2.3 Polimorfismo
- Resolución dinámica.
- Referencias de tipo interfaz/superclase.

## 2.4 Clases anidadas
- Static nested.
- Inner.
- Local.
- Anonymous.
- Reglas de acceso y captura de variables.

---

# 3. Clases Modernas de Java 21
## 3.1 Records
- Propósito, inmutabilidad.
- Compact constructors.
- Implementación de métodos.
- Restrictions y equals/hashCode/toString automáticos.

## 3.2 Sealed Classes
- sealed, non-sealed, final.
- permits.
- Jerarquías cerradas (muy preguntado en el examen).

## 3.3 Pattern Matching (Java 21)
- Pattern matching para `instanceof`.
- Pattern matching en `switch`:
  - Tipo patterns.
  - Guarded patterns.
  - Dominance rules.
- Exhaustividad en switch con sealed types.

---

# 4. Interfaces y Diseño Avanzado
## 4.1 Interfaces
- Métodos abstract, default, static y private.
- Conflictos entre interfaces (diamond problem).

## 4.2 Principios de diseño evaluados en el examen
- Cohesión.
- Acoplamiento.
- Inmutabilidad.
- Encapsulación estricta.

---

# 5. API Fundamental de Java
## 5.1 java.util Collections
- List, Set, Map (Hash*, Linked*, Tree*).
- Métodos clave: of, copyOf, unmodifiableCollections.
- Ordenamiento: Comparable vs Comparator.
- TreeSet/TreeMap: ordering vs equals.

## 5.2 Generics (muy importante)
- Type inference (var).
- Wildcards: ?, ? extends, ? super.
- Bounded generics.
- Erasure (cómo funciona y trampas del examen).

## 5.3 Arrays
- Multidimensionales.
- Arrays util: sort, binarySearch, mismatch.

---

# 6. Excepciones y Manejo de Recursos
## 6.1 Jerarquía de excepciones
- Checked vs unchecked.
- Error vs Exception.

## 6.2 Try-with-resources
- AutoCloseable.
- Orden de cierre.
- Múltiples recursos.
- Suppressed exceptions.

## 6.3 Lanzamiento y propagación
- Throws vs throw.
- Reglas de sobreescritura con excepciones.

---

# 7. Programación Funcional
## 7.1 Lambdas
- Sintaxis.
- Efectivamente final.
- Alcance.

## 7.2 Method references
- Tipo ::instancia
- Clase::instancia
- Clase::estático
- Constructor ::new

## 7.3 Interfaces funcionales (java.util.function)
- Predicate, Function, Supplier, Consumer
- Unary/BinaryOperator
- Composición: andThen, compose, negate.

---

# 8. Streams y Collectors
## 8.1 Streams
- Creación: stream(), of(), iterate(), generate().
- Operaciones intermedias: map, flatMap, filter, distinct, sorted, limit, skip.
- Operaciones terminales: collect, reduce, count, anyMatch/allMatch/noneMatch.

## 8.2 Collectors
- toList, toSet, joining.
- groupingBy, partitioningBy.
- mapping, counting, summing, reducing.

## 8.3 Parallel Streams
- Cuándo conviene o no.
- Problemas comunes (mutable structures).

---

# 9. Concurrencia en Java 21 (Clave en el examen)
## 9.1 Concurrencia clásica
- Thread, Runnable, Callable.
- ExecutorService.
- Future.
- Locks y atomic classes.
- synchronized.

## 9.2 Virtual Threads (Java 21)
- Propósito.
- Creación:
  - `Thread.ofVirtual().start(...)`
  - `Executors.newVirtualThreadPerTaskExecutor()`
- Bloqueo eficiente en virtual threads.

## 9.3 Structured Concurrency (Java 21)
- StructuredTaskScope
  - Shutdown on success
  - Shutdown on failure
- join, getResult.

## 9.4 CompletableFuture
- supplyAsync, thenApply, thenCompose, thenCombine.
- allOf y anyOf.

---

# 10. Módulos (JPMS)
- module-info.java.
- requires, exports, opens.
- modulepath vs classpath.
- Servicios: provides, uses.

---

# 11. Archivos, Entrada/Salida (I/O & NIO.2)
## 11.1 Files y Paths
- Files.exists, isDirectory, isRegularFile, copy, move, delete, walk.
- Path: resolve, relativize, normalize.

## 11.2 Streams de archivos
- Files.lines.
- Files.walk.

## 11.3 Channels y buffers (conceptual).
- ByteBuffer.
- FileChannel.

---

# 12. JVM y Plataforma
## 12.1 Conceptos clave
- Bytecode.
- HotSpot VM.
- Classloaders (bootstrap, extension, application).
- Orden de inicialización de clase.

## 12.2 Garbage Collection (nivel necesario para examen)
- Concepto de heap / stack.
- Referencias fuertes/suaves/débiles/fantasma.

---

# 13. Seguridad, Internacionalización y Misceláneos
- Resource bundles.
- Locale.
- Properties.
- Serialization (conceptual, no detallado).
- Encapsulación fuerte: módulos y accesibilidad.

---

# 14. Práctica de Examen
- Lectura de código minuciosa.
- Preguntas trampas: cast, overflow, equals/hashCode, streams.
- Consejos de tiempo y eliminación de opciones.
