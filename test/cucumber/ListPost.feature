@ignore
Feature: List Posts

  Scenario: See all posts
    Given I am logged in
    When I am at the home page
    Then I should see all the posts