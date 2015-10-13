@ignore


Feature: Logging out

  Scenario: Log out
    Given I am at the home page and logged in as "user1"
    When I click the logout button
    Then I should be redirected to the login page