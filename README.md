# Proyecto de Automatización E2E - Sprint 3

> 🎯 **Pruebas E2E con Serenity BDD para InnoSistemas**  
> Integrado con frontend en **modo mock** - Sin necesidad de backend

## 🚀 Inicio Rápido

### Prerequisitos

1. **Frontend corriendo en modo mock**:
```bash
cd ../innova-team-flow
npm install
npm run dev
# Frontend disponible en http://localhost:5173
```

2. **Java y Gradle instalados**

### Ejecutar Pruebas

```bash
# Ejecutar todas las pruebas
./gradlew clean test

# Solo autenticación
./gradlew clean test --tests "co.edu.udea.certificacion.sprint3.runners.AutenticacionRunner"

# Solo notificaciones
./gradlew clean test --tests "co.edu.udea.certificacion.sprint3.runners.NotificacionesRunner"

# Generar reporte
./gradlew aggregate
# Ver en: target/site/serenity/index.html
```

---

## 📁 Estructura del Proyecto

El proyecto sigue el patrón Screenplay de Serenity BDD y está organizado de la siguiente manera:

```
src/
├── main/java/co/edu/udea/certificacion/sprint3/
│   ├── questions/          # Preguntas para validaciones
│   ├── tasks/              # Tareas que los actores pueden realizar
│   └── userinterfaces/     # Elementos de las páginas web
│
└── test/
    ├── java/co/edu/udea/certificacion/sprint3/
    │   ├── runners/        # Runners de Cucumber
    │   └── stepdefinitions/ # Definiciones de pasos
    │
    └── resources/features/ # Archivos .feature con los escenarios
```

## Funcionalidades Implementadas

### 1. Autenticación de Usuarios
**Archivo:** `autenticacion_usuarios.feature`

Valida los mensajes de error en diferentes escenarios de inicio de sesión:
- Credenciales inválidas
- Usuario no registrado
- Campo de correo vacío
- Campo de contraseña vacío

**Runner:** `AutenticacionRunner.java`

### 2. Visualización de Notificaciones
**Archivo:** `visualizacion_notificaciones.feature`

Valida la correcta visualización de notificaciones según el tipo:
- Cambio de rol
- Nuevo miembro en el equipo
- Agregado a un equipo

**Runner:** `NotificacionesRunner.java`

## Comandos para Ejecutar las Pruebas

### Ejecutar todas las pruebas
```bash
./gradlew clean test
```

### Ejecutar solo las pruebas de autenticación
```bash
./gradlew clean test --tests AutenticacionRunner
```

### Ejecutar solo las pruebas de notificaciones
```bash
./gradlew clean test --tests NotificacionesRunner
```

### Ejecutar un escenario específico usando tags (si se agregan)
Si agregas tags como `@login_error` o `@notificaciones` en los archivos .feature, puedes ejecutarlos así:
```bash
./gradlew clean test -Dcucumber.filter.tags="@login_error"
```

### Generar reporte de Serenity
```bash
./gradlew clean test aggregate
```

El reporte se generará en: `target/site/serenity/index.html`

## Configuración Importante

### URLs de la Aplicación
Las URLs están configuradas en los step definitions. Debes actualizar las siguientes URLs con las de tu aplicación real:

**AutenticacionStepDefinitions.java:**
```java
Open.url("https://ejemplo.com/login")
```

**NotificacionesStepDefinitions.java:**
```java
Open.url("https://ejemplo.com/dashboard")
```

### Selectores CSS/XPath
Los selectores de elementos están definidos en las clases de `userinterfaces/`:
- `LoginPage.java` - Elementos de la página de login
- `NotificacionesPage.java` - Elementos de la página de notificaciones

Asegúrate de actualizar los selectores según tu aplicación.

## Dependencias Principales

- **Serenity BDD**: Framework de automatización
- **Cucumber**: Para BDD con Gherkin
- **Selenium WebDriver**: Para interacción con el navegador
- **JUnit**: Para ejecución de tests



## Notas

- Los archivos feature están escritos en español
- Se usa el patrón Screenplay para mayor mantenibilidad
- Los escenarios usan Scenario Outline con Examples para data-driven testing

