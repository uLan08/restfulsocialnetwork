@ignore
Feature: List Posts

  Scenario: See all posts
    Given I am logged in
    When I go to the posts page
    Then I should see all the posts