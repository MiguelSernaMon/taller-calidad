#language: es
#Authors: Miguel Serna, Camilo Loaiza, Alejandro Orrego

Característica: Autenticación de Usuarios
  Como usuario del sistema InnovaTeam Flow
  Quiero poder iniciar sesión con mis credenciales
  Para acceder a las funcionalidades del sistema según mi rol

  Antecedentes:
    Dado que me encuentro en la página de inicio de sesión

  Escenario: Inicio de sesión exitoso con credenciales válidas
    Dado que ingreso el correo "usuario@udea.edu.co"
    Y la contraseña "clave123"
    Cuando intento iniciar sesión
    Entonces debería ser redirigido al dashboard
    Y debería ver mi nombre de usuario "Usuario de Prueba"

  Esquema del escenario: Validar mensajes de error en inicio de sesión
    Dado que ingreso el correo "<correo>"
    Y la contraseña "<password>"
    Cuando intento iniciar sesión
    Entonces debería permanecer en la página de login
    Y debería ver el mensaje de error "<mensaje_esperado>"

    Ejemplos:
      | correo                | password    | mensaje_esperado           |
      | usuario@udea.edu.co   | clave_mal   | Credenciales inválidas     |
      | no_existe@udea.edu.co | clave123    | Usuario no registrado      |
      |                       | clave123    | El correo es obligatorio   |
      | usuario@udea.edu.co   |             | La clave es obligatoria    |

  Escenario: Inicio de sesión con rol de administrador
    Dado que ingreso el correo "admin@udea.edu.co"
    Y la contraseña "admin123"
    Cuando intento iniciar sesión
    Entonces debería ser redirigido al dashboard
    Y debería ver mi nombre de usuario "Administrador"
    Y debería ver mi rol como "ADMIN"

  Escenario: Validación de campos vacíos al intentar iniciar sesión
    Cuando intento iniciar sesión sin ingresar credenciales
    Entonces debería permanecer en la página de login
    Y debería ver el mensaje de error "El correo es obligatorio"

  Esquema del escenario: Inicio de sesión exitoso con diferentes roles del sistema
    Dado que ingreso el correo "<correo>"
    Y la contraseña "<password>"
    Cuando intento iniciar sesión
    Entonces debería ser redirigido al dashboard
    Y debería ver mi nombre de usuario "<nombre>"
    Y debería ver mi rol como "<rol>"

    Ejemplos:
      | correo              | password | nombre            | rol            |
      | usuario@udea.edu.co | clave123 | Usuario de Prueba | STUDENT        |
      | admin@udea.edu.co   | admin123 | Administrador     | ADMIN          |
      | po@udea.edu.co      | po123    | Product Owner     | PRODUCT_OWNER  |
      | sm@udea.edu.co      | sm123    | Scrum Master      | SCRUM_MASTER   |
      | tester@udea.edu.co  | test123  | Tester            | TESTER         |
      | dev@udea.edu.co     | dev123   | Developer         | DEVELOPER      |

  Escenario: Validar que campos con espacios en blanco son tratados como vacíos
    Dado que ingreso el correo "   "
    Y la contraseña "   "
    Cuando intento iniciar sesión
    Entonces debería permanecer en la página de login
    Y debería ver el mensaje de error "El correo es obligatorio"

  Escenario: Intentar inicio de sesión con correo válido pero contraseña incorrecta
    Dado que ingreso el correo "admin@udea.edu.co"
    Y la contraseña "contraseña_incorrecta"
    Cuando intento iniciar sesión
    Entonces debería permanecer en la página de login
    Y debería ver el mensaje de error "Credenciales inválidas"

  Escenario: Verificar que el sistema diferencia entre usuario no registrado y credenciales inválidas
    Dado que ingreso el correo "nuevo_usuario@udea.edu.co"
    Y la contraseña "cualquier_password"
    Cuando intento iniciar sesión
    Entonces debería permanecer en la página de login
    Y debería ver el mensaje de error "Usuario no registrado"
