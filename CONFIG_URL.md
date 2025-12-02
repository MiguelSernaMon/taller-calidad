# Configuración de URLs para E2E Tests

## Resumen

Todas las URLs utilizadas en los tests E2E están centralizadas en:
1. **Archivo de configuración**: `src/test/resources/serenity.conf` 
2. **Clase Java**: `src/main/java/co/edu/udea/certificacion/sprint3/config/AppConfig.java`

Esto elimina las URLs hardcodeadas en el código y permite cambiar fácilmente entre diferentes entornos.

## 📍 Archivos de Configuración

### 1. `serenity.conf` (Configuración Principal)

```hocon
webdriver {
    driver = chrome
    autodownload = true
    
    # URL base de la aplicación - SE USA EN TODAS LAS PRUEBAS
    base.url = "http://localhost:5173"
}

# Configuración por entornos
environments {
    default {
        webdriver.base.url = "http://localhost:5173"
    }
    
    dev {
        webdriver.base.url = "http://localhost:5173"
    }
    
    qa {
        webdriver.base.url = "http://qa.innosistemas.com"
    }
    
    staging {
        webdriver.base.url = "http://staging.innosistemas.com"
    }
    
    prod {
        webdriver.base.url = "http://innosistemas.com"
    }
}
```

### 2. `AppConfig.java` (Clase de Acceso)

```java
public class AppConfig {
    // Lee la URL desde serenity.conf o system properties
    private static String getConfiguredBaseUrl() {
        String url = System.getProperty("webdriver.base.url");
        if (url == null || url.isEmpty()) {
            url = System.getProperty("base.url");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:5173";
        }
        return url;
    }
}
```

## URLs Disponibles

La clase `AppConfig` proporciona los siguientes métodos para obtener URLs:

- `getLoginUrl()` → Login page
- `getDashboardUrl()` → Dashboard principal
- `getNotificacionesUrl()` → Módulo de notificaciones
- `getPermisosUrl()` → Gestión de permisos
- `getConfiguracionUrl()` → Configuración
- `getBaseUrl()` → URL base
- `getUrl(String path)` → URL personalizada

## Configuración por Defecto

```java
BASE_URL = "http://localhost:5173"
```

## 🔧 Cambiar la URL Base

### Opción 1: Modificar serenity.conf (Recomendado)

Edita `src/test/resources/serenity.conf`:

```hocon
webdriver {
    base.url = "http://tu-nueva-url.com"
}
```

### Opción 2: Variable de Sistema al Ejecutar (CI/CD)

```bash
# Usando webdriver.base.url (lee serenity.conf)
./gradlew clean test -Dwebdriver.base.url=http://qa-server.ejemplo.com:8080

# Usando base.url (alternativa)
./gradlew clean test -Dbase.url=http://qa-server.ejemplo.com:8080
```

### Opción 3: Cambiar de Entorno

```bash
# Entorno QA
./gradlew clean test -Denvironment=qa

# Entorno Staging  
./gradlew clean test -Denvironment=staging

# Entorno Producción
./gradlew clean test -Denvironment=prod
```

## 🔍 Prioridad de Configuración

El sistema busca la URL base en este orden:

1. **System Property** `-Dwebdriver.base.url=http://...` (Mayor prioridad)
2. **System Property** `-Dbase.url=http://...`
3. **Valor por defecto** `http://localhost:5173` (Menor prioridad)

## Uso en Step Definitions

### Ejemplo: AutenticacionStepDefinitions.java

```java
import co.edu.udea.certificacion.sprint3.config.AppConfig;

@Dado("que me encuentro en la página de inicio de sesión")
public void queMeEncuentroEnLaPaginaDeInicioSesion() {
    theActorInTheSpotlight().wasAbleTo(
            Open.url(AppConfig.getLoginUrl())  // ✅ Usando AppConfig
    );
}
```

**Antes (❌ No hacer):**
```java
Open.url("https://ejemplo.com/login")  // URL quemada
```

### Ejemplo: NotificacionesStepDefinitions.java

```java
import co.edu.udea.certificacion.sprint3.config.AppConfig;

@Dado("que he iniciado sesión exitosamente")
public void queHeIniciadoSesionExitosamente() {
    theActorInTheSpotlight().wasAbleTo(
            Open.url(AppConfig.getDashboardUrl())  // ✅ Usando AppConfig
    );
}
```

## Archivos Actualizados

- ✅ `AutenticacionStepDefinitions.java` - Usa `AppConfig.getLoginUrl()`
- ✅ `NotificacionesStepDefinitions.java` - Usa `AppConfig.getDashboardUrl()`
- ✅ Todas las URLs hardcodeadas eliminadas del proyecto

## Ventajas de esta Configuración

1. **Mantenibilidad**: Un solo lugar para cambiar todas las URLs
2. **Flexibilidad**: Fácil cambio entre ambientes (dev, qa, staging, prod)
3. **CI/CD Ready**: Soporte para inyección de URLs mediante system properties
4. **Legibilidad**: Métodos con nombres descriptivos
5. **Escalabilidad**: Fácil agregar nuevas URLs sin modificar step definitions

## Agregar Nuevas URLs

Si necesitas agregar una nueva página:

```java
public class AppConfig {
    // ... código existente ...
    
    public static String getReportesUrl() {
        return BASE_URL + "/reportes";
    }
}
```

Y úsala en tus step definitions:
```java
Open.url(AppConfig.getReportesUrl())
```

## Ejemplo Completo de Ejecución

```bash
# Desarrollo local (usa configuración por defecto)
./gradlew clean test

# QA Server (sobrescribe con system property)
./gradlew clean test -Dwebdriver.base.url=http://192.168.1.100:5173

# Staging (usa entorno configurado)
./gradlew clean test -Denvironment=staging

# Producción (sobrescribe directamente)
./gradlew clean test -Dwebdriver.base.url=https://app.innova-team.com
```

## 🚀 Configuración para CI/CD

### Jenkins

```groovy
stage('Run E2E Tests') {
    steps {
        sh './gradlew clean test -Dwebdriver.base.url=${TEST_ENV_URL}'
    }
}
```

### GitHub Actions

```yaml
- name: Run E2E Tests
  run: ./gradlew clean test -Dwebdriver.base.url=${{ secrets.TEST_URL }}
```

### GitLab CI

```yaml
e2e_tests:
  script:
    - ./gradlew clean test -Dwebdriver.base.url=${TEST_ENV_URL}
```

## ⚠️ Troubleshooting

### Problema: La URL no cambia

**Solución**: Verifica que usas el flag correcto con `-D`:
```bash
# ✅ Correcto
./gradlew test -Dwebdriver.base.url=http://...

# ❌ Incorrecto (falta la 'D')
./gradlew test -webdriver.base.url=http://...
```

### Problema: Necesito usar HTTPS

**Solución**: Usa la URL completa con https en serenity.conf:
```hocon
webdriver.base.url = "https://secure.innosistemas.com"
```

### Problema: Necesito cambiar el puerto

**Solución**: Incluye el puerto en la URL:
```hocon
webdriver.base.url = "http://localhost:8080"
```

---

**Última actualización**: Refactorización de URLs - Sprint 3
