# Guía de Ejecución de Pruebas E2E

## 🎯 Cambios Realizados

### 1. URL Actualizada
- ✅ **Puerto cambiado a 8081**: `http://localhost:8081`
- Configurado en `serenity.conf`

### 2. Flujo E2E Completo Creado
- ✅ **Nuevo feature**: `flujo_e2e_completo.feature`
- ✅ **Nuevo runner**: `FlujoE2ERunner.java`
- ✅ **Nuevos step definitions**: `FlujoE2EStepDefinitions.java`

### 3. Navegador Visible Configurado
- ✅ **Sin modo headless** - El navegador se abrirá visualmente
- ✅ **Delays añadidos** - 1-2 segundos entre acciones para visualización
- ✅ **Screenshots habilitados** - Captura en cada acción

## 📁 Estructura del Flujo E2E

```
flujo_e2e_completo.feature
├── Scenario 1: Login simple + ver notificaciones
│   ├── Login con usuario@udea.edu.co
│   ├── Verificar dashboard
│   └── Navegar a notificaciones
│
├── Scenario 2: Login admin + configuración
│   ├── Login con admin@udea.edu.co
│   ├── Verificar rol ADMIN
│   └── Acceder a configuración de notificaciones
│
└── Scenario Outline 3: Múltiples roles
    ├── Login con diferentes usuarios
    ├── Verificar roles específicos
    └── Ver notificaciones según rol
```

## 🚀 Cómo Ejecutar

### Opción 1: Ejecutar el Flujo E2E Completo (Recomendado)

```bash
# Ejecutar solo el flujo E2E
./gradlew test --tests "co.edu.udea.certificacion.sprint3.runners.FlujoE2ERunner"
```

### Opción 2: Ejecutar Solo Autenticación

```bash
# Ejecutar solo pruebas de autenticación
./gradlew test --tests "co.edu.udea.certificacion.sprint3.runners.AutenticacionRunner"
```

### Opción 3: Ejecutar Solo Notificaciones

```bash
# Ejecutar solo pruebas de notificaciones
./gradlew test --tests "co.edu.udea.certificacion.sprint3.runners.NotificacionesRunner"
```

### Opción 4: Ejecutar Todas las Pruebas

```bash
# Ejecutar todas las pruebas
./gradlew clean test
```

## ⚙️ Configuración Actual

### serenity.conf
```hocon
webdriver {
    driver = chrome
    base.url = "http://localhost:8081"  # ← Puerto 8081
    
    capabilities {
        "goog:chromeOptions" {
            binary = "/Applications/Brave Browser.app/Contents/MacOS/Brave Browser"
            args = [
                "start-maximized",
                "disable-infobars",
                # SIN headless - navegador visible
            ]
        }
    }
}

serenity {
    take.screenshots = FOR_EACH_ACTION
    step.delay = 1000  # 1 segundo entre pasos
}
```

## 🔍 Delays Configurados

Los delays están añadidos en `FlujoE2EStepDefinitions.java`:

- **Login page**: 1.5 segundos para ver la página
- **Ingresar credenciales**: 1 segundo para ver el ingreso
- **Click login**: 2 segundos para ver el proceso
- **Dashboard**: 1.5 segundos para ver el dashboard
- **Navegación**: 2 segundos para ver la transición
- **Verificaciones**: 1 segundo entre cada verificación

## 📊 Ver los Reportes

Después de ejecutar las pruebas:

```bash
# Abrir el reporte de Serenity
open target/site/serenity/index.html
```

## 🐛 Troubleshooting

### Problema: El navegador no se abre

**Solución 1**: Verificar que NO esté en modo headless
```hocon
# En serenity.conf, NO debe tener:
# args = ["--headless"]
```

**Solución 2**: Verificar la ruta de Brave
```bash
# Verificar que existe
ls -la "/Applications/Brave Browser.app/Contents/MacOS/Brave Browser"
```

**Solución 3**: Usar Chrome en lugar de Brave
```hocon
# Comentar o eliminar la línea binary:
# binary = "/Applications/Brave Browser.app/Contents/MacOS/Brave Browser"
```

### Problema: Las acciones son muy rápidas

**Solución**: Aumentar el delay en `serenity.conf`
```hocon
serenity {
    step.delay = 2000  # Cambiar a 2 segundos
}
```

O modificar los delays individuales en `FlujoE2EStepDefinitions.java`:
```java
theActorInTheSpotlight().attemptsTo(TimeDelay.of(3000)); // 3 segundos
```

### Problema: Puerto 8081 no está disponible

**Verificar**: 
```bash
# Ver si el puerto está en uso
lsof -i :8081

# O iniciar el backend en el puerto correcto
```

**Cambiar puerto temporalmente**:
```bash
./gradlew test -Dwebdriver.base.url=http://localhost:OTRO_PUERTO
```

## 📝 Escenarios del Flujo E2E

### Scenario 1: Login + Notificaciones Básico
```gherkin
Given that I am on the login page
And that I enter the email "usuario@udea.edu.co"
And the password "clave123"
When I attempt to log in
Then I should be redirected to the dashboard
And I should see my username "Usuario de Prueba"
When I navigate to the notifications module
Then I should be able to see the notifications section
```

### Scenario 2: Admin + Configuración
```gherkin
Given that I am on the login page
And that I enter the email "admin@udea.edu.co"
And the password "admin123"
When I attempt to log in
Then I should be redirected to the dashboard
And I should see my username "Administrador"
And I should see my role as "ADMIN"
When I navigate to the notifications module
Then I should be able to access notification settings
```

## ✅ Ventajas del Flujo E2E

1. **Flujo Realista**: Simula el uso real del usuario
2. **Login Primero**: Evita problemas de protección/autenticación
3. **Visible**: Puedes ver cada paso del proceso
4. **Delays Controlados**: Tiempo suficiente para observar
5. **Screenshots**: Captura visual de cada acción
6. **Múltiples Roles**: Prueba diferentes tipos de usuarios

## 🎬 Próximos Pasos

1. Asegúrate de que el backend esté corriendo en **puerto 8081**
2. Ejecuta: `./gradlew test --tests "FlujoE2ERunner"`
3. Observa cómo el navegador se abre y ejecuta cada paso
4. Revisa el reporte en `target/site/serenity/index.html`

---

**Actualizado**: 1 de diciembre de 2025
**Autores**: Miguel Serna, Cristina Vergara
