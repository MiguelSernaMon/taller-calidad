#Authors: Miguel Serna, Cristina Vergara
#language: en

Feature: User Authentication
  As a user of the InnovaTeam Flow system
  I want to be able to log in securely
  To access the system functionalities

  # Caso 1: Contraseña incorrecta para un usuario existente
  Scenario: Login with invalid password
    Given that I am on the login page
    When I enter the email "usuario@udea.edu.co"
    And the password "wrong_password"
    When I attempt to log in
    Then I should remain on the login page
    And I should see an error message "Credenciales inválidas"

  # Caso 2: Usuario que no existe en el sistema
  Scenario: Login with non-registered user
    Given that I am on the login page
    When I enter the email "nonexistent@udea.edu.co"
    And the password "any_password"
    When I attempt to log in
    Then I should remain on the login page
    And I should see an error message "Usuario no encontrado"

  # Caso 3: Intentar iniciar sesión dejando campos obligatorios vacíos
  Scenario Outline: Login with empty fields
    Given that I am on the login page
    When I enter the email "<email>"
    And the password "<password>"
    When I attempt to log in
    Then I should remain on the login page
    And I should see an error message indicating required fields

    Examples:
      | email               | password |
      |                     | clave123 |
      | usuario@udea.edu.co |          |
      |                     |          |