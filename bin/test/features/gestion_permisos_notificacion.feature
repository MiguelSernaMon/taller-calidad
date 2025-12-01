#language: es
#Authors: Miguel Serna, Camilo Loaiza, Alejandro Orrego

Feature: Gestión de Permisos de Notificación
  Como administrador de un equipo
  Quiero configurar quién recibe qué notificaciones
  Para asegurar que cada miembro reciba solo la información de acuerdo a su función

  Background:
    Dado que he iniciado sesión en el sistema

  Scenario: Administrador actualiza configuración de permisos exitosamente
    Dado que mi rol es "Administrador"
    Y estoy en el panel de configuración de notificaciones
    Cuando modifico los permisos para un rol específico
    Y guardo los cambios realizados
    Entonces debería ver el mensaje de confirmación "Configuración actualizada exitosamente"
    Y la nueva configuración debe quedar guardada en el sistema

  Esquema del escenario: Visibilidad de notificaciones según el rol del usuario
    Dado que mi rol actual es "<mi_rol>"
    Cuando se genera una notificación destinada exclusivamente para el rol "<rol_destino>"
    Entonces <accion_esperada> visualizar la notificación en mi bandeja

    Ejemplos:
      | mi_rol        | rol_destino   | accion_esperada |
      | Product Owner | Product Owner | debería         |
      | Tester        | Product Owner | no debería      |
      | Product Owner | Tester        | no debería      |
      | Scrum Master  | Scrum Master  | debería         |
