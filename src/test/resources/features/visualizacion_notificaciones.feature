#Authors: Miguel Serna, Camilo Loaiza, Alejandro Orrego
#language: es

Característica: Visualización de Notificaciones

  Antecedentes:
    Dado que he iniciado sesión exitosamente
    Y navego al módulo de notificaciones

  Esquema del escenario: Visualización correcta según el tipo de notificación
    Dado que el sistema ha generado un evento de tipo "<tipo>"
    Cuando reviso mi bandeja de entrada
    Entonces la notificación debe tener el título "<titulo>"
    Y debe mostrar el icono o etiqueta visual de "<categoria>"
    Y debe contener un enlace que dirige a "<destino>"

    Ejemplos:
      | tipo            | titulo                | categoria | destino            |
      | cambio_rol      | Cambio de Rol         | Alerta    | /perfil/roles      |
      | nuevo_miembro   | Nuevo integrante      | Equipo    | /equipo/miembros   |
      | agregado_equipo | Bienvenido al equipo  | Info      | /equipo/general    |

