#Authors: Miguel Serna, Cristina Vergara
#language: en

Feature: User Authentication
  As a user of the InnovaTeam Flow system
  I want to be able to log in with my credentials
  To access the system functionalities according to my role


  Scenario: Successful login with valid credentials
    Given that I enter the email "usuario@udea.edu.co"
    And the password "clave123"
    When I attempt to log in
    Then I should be redirected to the dashboard
    And I should see my username "Usuario de Prueba"

  Scenario Outline: Validate error messages on login
    Given that I enter the email "<email>"
    And the password "<password>"
    When I attempt to log in
    Then I should remain on the login page
    And I should see the error message "<expected_message>"

    Examples:
      | email                 | password    | expected_message           |
      | usuario@udea.edu.co   | clave_mal   | Credenciales inválidas     |
      | no_existe@udea.edu.co | clave123    | Usuario no registrado      |
      |                       | clave123    | El correo es obligatorio   |
      | usuario@udea.edu.co   |             | La clave es obligatoria    |

  Scenario: Login with administrator role
    Given that I enter the email "admin@udea.edu.co"
    And the password "admin123"
    When I attempt to log in
    Then I should be redirected to the dashboard
    And I should see my username "Administrador"
    And I should see my role as "ADMIN"

  Scenario: Validation of empty fields when attempting to log in
    When I attempt to log in without entering credentials
    Then I should remain on the login page
    And I should see the error message "El correo es obligatorio"

  Scenario Outline: Successful login with different system roles
    Given that I enter the email "<email>"
    And the password "<password>"
    When I attempt to log in
    Then I should be redirected to the dashboard
    And I should see my username "<name>"
    And I should see my role as "<role>"

    Examples:
      | email               | password | name              | role           |
      | usuario@udea.edu.co | clave123 | Usuario de Prueba | STUDENT        |
      | admin@udea.edu.co   | admin123 | Administrador     | ADMIN          |
      | po@udea.edu.co      | po123    | Product Owner     | PRODUCT_OWNER  |
      | sm@udea.edu.co      | sm123    | Scrum Master      | SCRUM_MASTER   |
      | tester@udea.edu.co  | test123  | Tester            | TESTER         |
      | dev@udea.edu.co     | dev123   | Developer         | DEVELOPER      |

  Scenario: Validate that fields with blank spaces are treated as empty
    Given that I enter the email "   "
    And the password "   "
    When I attempt to log in
    Then I should remain on the login page
    And I should see the error message "El correo es obligatorio"

  Scenario: Attempt login with valid email but incorrect password
    Given that I enter the email "admin@udea.edu.co"
    And the password "contraseña_incorrecta"
    When I attempt to log in
    Then I should remain on the login page
    And I should see the error message "Credenciales inválidas"

  Scenario: Verify that the system differentiates between unregistered user and invalid credentials
    Given that I enter the email "nuevo_usuario@udea.edu.co"
    And the password "cualquier_password"
    When I attempt to log in
    Then I should remain on the login page
    And I should see the error message "Usuario no registrado"
