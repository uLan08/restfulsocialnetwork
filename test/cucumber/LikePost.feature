Feature: Like a post

  Scenario: Like a Post
    Given I am logged in and at the home page
    When I like the post "i am user2 guys hehehe"
    Then I should see "user1 liked this" on the post