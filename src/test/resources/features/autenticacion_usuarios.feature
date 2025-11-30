#Authors: Miguel Serna, Camilo Loaiza, Alejandro Orrego
      | usuario@udea.edu.co   |             | La clave es obligatoria    |
      |                       | clave123    | El correo es obligatorio   |
      | no_existe@udea.edu.co | clave123    | Usuario no registrado      |
      | usuario@udea.edu.co   | clave_mal   | Credenciales inválidas     |
      | correo                | password    | mensaje_esperado           |
    Ejemplos:

    Y debería ver el mensaje de error "<mensaje_esperado>"
    Entonces debería permanecer en la página de login
    Cuando intento iniciar sesión
    Y la contraseña "<password>"
    Dado que ingreso el correo "<correo>"
  Esquema del escenario: Validar mensajes de error en inicio de sesión

    Dado que me encuentro en la página de inicio de sesión
  Antecedentes:

Característica: Autenticación de Usuarios

#language: es

