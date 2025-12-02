# 🔍 Troubleshooting - Botón de Inicio de Sesión

## Problema Actual
El bot no está dando clic en el botón "Iniciar Sesión"

## Soluciones Implementadas

### 1. ✅ Modo Incógnito Desactivado
- El navegador NO usará modo incógnito
- Configurado en `serenity.conf`

### 2. ✅ Múltiples Selectores para el Botón
Se agregaron selectores de respaldo en `LoginPage.java`:

```java
// Busca el botón por:
- ID: #loginButton
- Tipo: button[type='submit']
- Clase: .login-button
- Texto: button que contenga "Iniciar" o "Login"
```

### 3. ✅ Esperas Explícitas Añadidas
En `IntentarIniciarSesion.java` ahora:
- Espera a que el botón sea visible (10 segundos max)
- Espera a que el botón sea clickeable (10 segundos max)
- Agrega un delay de 500ms antes de hacer clic

## 🔧 Cómo Identificar el Selector Correcto

### Opción 1: Inspeccionar Manualmente (Recomendado)

1. Abre tu aplicación en el navegador: `http://localhost:8081`
2. Abre las DevTools (F12 o Cmd+Option+I)
3. Usa el selector de elementos (icono de flecha)
4. Haz clic en el botón "Iniciar Sesión"
5. En las DevTools verás el HTML del botón

**Ejemplos de lo que podrías ver:**

```html
<!-- Si tiene ID -->
<button id="loginButton">Iniciar Sesión</button>

<!-- Si tiene clase -->
<button class="btn-login">Iniciar Sesión</button>

<!-- Si es type submit -->
<button type="submit">Iniciar Sesión</button>

<!-- Si es input -->
<input type="submit" value="Iniciar Sesión">

<!-- Si tiene data attributes -->
<button data-testid="login-btn">Iniciar Sesión</button>
```

### Opción 2: Ver en la Consola del Navegador

Abre la consola (F12) y ejecuta:

```javascript
// Buscar por ID
document.getElementById('loginButton')

// Buscar por tipo submit
document.querySelector('button[type="submit"]')

// Buscar por texto
document.querySelector('button:contains("Iniciar")')

// Buscar por clase
document.querySelector('.login-button')

// Ver TODOS los botones de la página
document.querySelectorAll('button')
```

### Opción 3: Ejecutar Script de Debugging

Cuando las pruebas fallen, agrega esto temporalmente en `IntentarIniciarSesion.java`:

```java
@Override
public <T extends Actor> void performAs(T actor) {
    // DEBUGGING: Imprimir información del botón
    actor.attemptsTo(
        Ensure.that(LoginPage.LOGIN_BUTTON).isDisplayed()
            .orComplainWith(ElementNotFound.class, 
                "No se encontró el botón de login. Verifica el selector.")
    );
    
    // Original
    actor.attemptsTo(
        WaitUntil.the(LoginPage.LOGIN_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
        Click.on(LoginPage.LOGIN_BUTTON)
    );
}
```

## 🛠️ Actualizar el Selector

Una vez que identifiques el selector correcto, actualiza `LoginPage.java`:

### Si el botón tiene un ID diferente:

```java
public static final Target LOGIN_BUTTON = Target.the("Botón de inicio de sesión")
    .located(By.id("TU_ID_AQUI"));  // Cambia por el ID real
```

### Si el botón tiene una clase:

```java
public static final Target LOGIN_BUTTON = Target.the("Botón de inicio de sesión")
    .located(By.className("btn-login"));  // Cambia por la clase real
```

### Si el botón tiene un data-testid:

```java
public static final Target LOGIN_BUTTON = Target.the("Botón de inicio de sesión")
    .located(By.cssSelector("[data-testid='login-btn']"));
```

### Si el botón es un input type="submit":

```java
public static final Target LOGIN_BUTTON = Target.the("Botón de inicio de sesión")
    .located(By.cssSelector("input[type='submit']"));
```

### Si quieres usar XPath por texto:

```java
public static final Target LOGIN_BUTTON = Target.the("Botón de inicio de sesión")
    .located(By.xpath("//button[text()='Iniciar Sesión']"));
```

## 📸 Revisar Screenshots de Serenity

Después de que falle una prueba:

1. Abre el reporte: `target/site/serenity/index.html`
2. Ve al escenario que falló
3. Mira los screenshots capturados
4. Verás exactamente qué estaba en pantalla cuando intentó hacer clic

## 🎯 Selectores Actuales Configurados

```java
// Selector principal (busca múltiples opciones)
.locatedBy("#loginButton, button[type='submit'], .login-button, button:contains('Iniciar'), button:contains('Login')")

// Selector alternativo por XPath
.located(By.xpath("//button[@type='submit'] | //button[contains(text(), 'Iniciar')] | //button[contains(text(), 'Login')] | //input[@type='submit']"))
```

## 🚨 Problemas Comunes

### 1. El botón está en un iframe

Si el formulario de login está dentro de un iframe:

```java
@Override
public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        Switch.toFrame("nombre-del-iframe"),  // Cambiar al iframe
        Click.on(LoginPage.LOGIN_BUTTON),
        Switch.toDefaultContext()  // Volver al contexto principal
    );
}
```

### 2. El botón está oculto por JavaScript

```java
@Override
public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        Scroll.to(LoginPage.LOGIN_BUTTON),  // Hacer scroll al botón
        WaitUntil.the(LoginPage.LOGIN_BUTTON, isVisible()),
        Click.on(LoginPage.LOGIN_BUTTON)
    );
}
```

### 3. El botón necesita JavaScript click

```java
@Override
public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        JavaScriptClick.on(LoginPage.LOGIN_BUTTON)  // Click con JavaScript
    );
}
```

### 4. Hay múltiples botones submit

```java
public static final Target LOGIN_BUTTON = Target.the("Botón de inicio de sesión")
    .located(By.xpath("(//button[@type='submit'])[1]"));  // Toma el primero
```

## 📝 Próximos Pasos

1. **Ejecuta la prueba** y observa qué pasa en el navegador
2. **Captura de pantalla** cuando llegue a la página de login
3. **Inspecciona el botón** con DevTools
4. **Actualiza el selector** en `LoginPage.java` con el correcto
5. **Vuelve a ejecutar** la prueba

## 🔍 Comando para Ver Detalles

```bash
# Ejecutar con logs detallados
./gradlew test --tests "FlujoE2ERunner" --info

# Ver el reporte después
open target/site/serenity/index.html
```

---

**Nota**: Con las esperas explícitas y múltiples selectores configurados, el bot ahora esperará hasta 10 segundos para que el botón aparezca y sea clickeable antes de intentar hacer clic.
