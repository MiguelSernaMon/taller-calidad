#Authors: Miguel Serna, Cristina Vergara
#language: en

Feature: Complete E2E Flow - Authentication and Notifications
  As a user of the InnovaTeam Flow system
  I want to log in and then interact with the notification system
  To verify the complete user journey works correctly

  Scenario: Complete E2E flow - Login and check notifications
    Given that I am on the login page
    And that I enter the email "usuario@udea.edu.co"
    And the password "clave123"
    When I attempt to log in
    Then I should be redirected to the dashboard
    And I should see my username "Usuario de Prueba"
    When I navigate to the notifications module
    Then I should be able to see the notifications section

  Scenario Outline: Complete E2E flow - Different roles login and notification access
    Given that I am on the login page
    And that I enter the email "<email>"
    And the password "<password>"
    When I attempt to log in
    Then I should be redirected to the dashboard
    And I should see my username "<name>"
    And I should see my role as "<role>"
    When I navigate to the notifications module
    Then I should see notifications relevant to my role "<role>"

    Examples:
      | email               | password | name              | role          |
      | usuario@udea.edu.co | clave123 | Usuario de Prueba | STUDENT       |
      | admin@udea.edu.co   | admin123 | Administrador     | ADMIN         |
      | po@udea.edu.co      | po123    | Product Owner     | PRODUCT_OWNER |
      | sm@udea.edu.co      | sm123    | Scrum Master      | SCRUM_MASTER  |

