#Authors: Miguel Serna, Camilo Loaiza, Alejandro Orrego
#language: en

Feature: Flight Booking on BlazeDemo
  As a traveler
  I want to search and book a flight
  To receive a confirmation ID

  Actor: User

  @happy_path
  Scenario: Successful flight booking
    Given the user navigates to the BlazeDemo home page
    When he searches for flights from "Boston" to "London"
    And he selects the first available flight
    And he completes the purchase form with the following data
      | name             | Jhon Patiño      |
      | address          | Calle 50 # 32-25 |
      | city             | Chigorodó        |
      | state            | Antioquia        |
      | zipCode          | 10001            |
      | cardType         | Visa             |
      | creditCardNumber | 123456789012     |
      | month            | 11               |
      | year             | 2025             |
      | nameOnCard       | Jhon Patino      |
    Then he should see the message "Thank you for your purchase today!"