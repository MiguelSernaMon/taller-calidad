# Guía Rápida - Proyecto Sprint 3

## ✅ Resumen de Cambios

Se ha eliminado completamente toda la funcionalidad relacionada con "Flight Booking" y se ha implementado un nuevo proyecto con las siguientes funcionalidades:

### 📋 Funcionalidades Implementadas

#### 1. Autenticación de Usuarios
- **Feature:** `autenticacion_usuarios.feature`
- **Runner:** `AutenticacionRunner.java`
- **Step Definitions:** `AutenticacionStepDefinitions.java`
- **Escenarios:** Validación de mensajes de error usando Scenario Outline con Examples

#### 2. Visualización de Notificaciones
- **Feature:** `visualizacion_notificaciones.feature`
- **Runner:** `NotificacionesRunner.java`
- **Step Definitions:** `NotificacionesStepDefinitions.java`
- **Escenarios:** Validación de notificaciones según tipo usando Scenario Outline con Examples

### 🗂️ Estructura Creada

```
src/main/java/co/edu/udea/certificacion/sprint3/
├── tasks/
│   ├── IngresarCredenciales.java
│   ├── IntentarIniciarSesion.java
│   ├── NavegarAlModuloNotificaciones.java
│   └── RevisarBandejaEntrada.java
├── questions/
│   ├── ElMensajeDeError.java
│   ├── LaPaginaDeLogin.java
│   ├── ElTituloDeNotificacion.java
│   ├── LaCategoriaDeNotificacion.java
│   └── ElEnlaceDeNotificacion.java
└── userinterfaces/
    ├── LoginPage.java
    └── NotificacionesPage.java

src/test/java/co/edu/udea/certificacion/sprint3/
├── runners/
│   ├── AutenticacionRunner.java
│   └── NotificacionesRunner.java
└── stepdefinitions/
    ├── AutenticacionStepDefinitions.java
    └── NotificacionesStepDefinitions.java

src/test/resources/features/
├── autenticacion_usuarios.feature
└── visualizacion_notificaciones.feature
```

### 🗑️ Archivos Eliminados

Todos los archivos relacionados con Flight Booking han sido eliminados:
- `flight_booking.feature`
- `FlightBookingRunner.java`
- `SearchForFlights.java`
- `SelectTheFirstFlight.java`
- `SelectTheMostExpensiveFlight.java`
- `FillOutPurchaseForm.java`
- `HomePage.java`
- `ReservePage.java`
- `PurchasePage.java`
- `ConfirmationPage.java`
- `TheConfirmationMessage.java`
- `StepDefinition.java` (versión antigua)

## 🚀 Cómo Ejecutar las Pruebas

### Compilar el proyecto
```bash
./gradlew clean compileJava compileTestJava
```

### Ejecutar pruebas específicas
```bash
# Solo autenticación
./gradlew clean test --tests AutenticacionRunner

# Solo notificaciones
./gradlew clean test --tests NotificacionesRunner

# Todas las pruebas
./gradlew clean test
```

### Generar reporte
```bash
./gradlew clean test aggregate
```

Luego abre: `target/site/serenity/index.html` en tu navegador

## ⚙️ Configuración Necesaria

### 1. URLs de la Aplicación

Debes actualizar las URLs en los Step Definitions para que apunten a tu aplicación real:

**AutenticacionStepDefinitions.java** (línea ~32):
```java
Open.url("https://ejemplo.com/login") // Cambiar por la URL real
```

**NotificacionesStepDefinitions.java** (línea ~32):
```java
Open.url("https://ejemplo.com/dashboard") // Cambiar por la URL real
```

### 2. Selectores de Elementos

Actualiza los selectores en las clases de `userinterfaces/` según tu aplicación:

**LoginPage.java:**
```java
Target EMAIL_INPUT = Target.the("Campo de correo electrónico")
    .located(By.id("email"));  // Cambiar el selector según tu app
```

**NotificacionesPage.java:**
```java
Target NOTIFICACION_TITULO = Target.the("Título de la notificación")
    .located(By.cssSelector(".notification-title"));  // Cambiar según tu app
```

## 📝 Notas Importantes

1. **Idioma:** Los archivos `.feature` están escritos en español usando las palabras clave de Cucumber en español (Dado, Cuando, Entonces, Y, Esquema del escenario, Ejemplos)

2. **Patrón Screenplay:** El proyecto utiliza el patrón Screenplay de Serenity BDD para mejor mantenibilidad

3. **Data-Driven Testing:** Ambos features usan `Scenario Outline` con `Examples` para pruebas basadas en datos

4. **Actores:** El actor predeterminado se llama "usuario" y se configura en el método `@Before` de cada StepDefinition

## 🔍 Verificación

Para verificar que todo esté correcto:
```bash
# Compilar sin errores
./gradlew clean compileJava compileTestJava

# Ver los features creados
ls -la src/test/resources/features/

# Ver los runners
ls -la src/test/java/co/edu/udea/certificacion/sprint3/runners/
```

## 👥 Autores
- Miguel Serna
- Camilo Loaiza
- Alejandro Orrego

