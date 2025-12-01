#Authors: Miguel Serna, Cristina Vergara
#language: en

Feature: Notification Permissions Management
  As a team administrator
  I want to configure who receives which notifications
  To ensure that each member receives only the information according to their role

  Background:
    Given that I have logged into the system

  Scenario: Administrator successfully updates permissions configuration
    Given that my role is "Administrador"
    And I am on the notifications configuration panel
    When I modify the permissions for a specific role
    And I save the changes made
    Then I should see the confirmation message "Configuración actualizada exitosamente"
    And the new configuration should be saved in the system

  Scenario Outline: Notification visibility according to user role
    Given that my current role is "<my_role>"
    When a notification is generated exclusively for the role "<target_role>"
    Then I <expected_action> view the notification in my inbox

    Examples:
      | my_role       | target_role   | expected_action |
      | Product Owner | Product Owner | should          |
      | Tester        | Product Owner | should not      |
      | Product Owner | Tester        | should not      |
      | Scrum Master  | Scrum Master  | should          |
