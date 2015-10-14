Feature: logging in

  Scenario: Logging in
    Given I am at the login page
    When I type "user1" in the username field and "password" in the password field and login
    Then I should be able to see "user1" at the home page