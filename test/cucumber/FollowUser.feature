Feature: Follow a user

  Scenario: Follow user
    Given I am logged in as "user2" and at the home page
    When I click follow "user1"
    Then I should see "user1" in the list of followed users in my profile