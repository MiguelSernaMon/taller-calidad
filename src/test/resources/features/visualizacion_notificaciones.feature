#Authors: Miguel Serna, Cristina Vergara
#language: en

Feature: Notification Visualization

  Background:
    Given that I have successfully logged in
    And I navigate to the notifications module

  Scenario Outline: Correct visualization according to notification type
    Given that the system has generated an event of type "<type>"
    When I check my inbox
    Then the notification should have the title "<title>"
    And it should display the icon or visual label of "<category>"
    And it should contain a link that directs to "<destination>"

    Examples:
      | type            | title                 | category | destination        |
      | cambio_rol      | Cambio de Rol         | Alerta   | /perfil/roles      |
      | nuevo_miembro   | Nuevo integrante      | Equipo   | /equipo/miembros   |
      | agregado_equipo | Bienvenido al equipo  | Info     | /equipo/general    |

