Feature: logging in

  Scenario: Logging in
    Given I am at the url "http://localhost:8090/restsocnet/login/auth"
    When I type "user1" in the username field and "password" in the password field and login
    Then I should be able to view the home page for logged in users