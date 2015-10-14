
Feature: Posting new post

  Scenario: New Post
    Given I am logged in and at the home page
    When I write "This is a new post" in the text area and click post
    Then I should see "This is a new post" in the list of posts