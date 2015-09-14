@ignore
Feature: Logging out

  Scenario: Log out
    Given I am at the home page and logged in
    When I click the logout button
    Then I should not be at the home page for logged in users