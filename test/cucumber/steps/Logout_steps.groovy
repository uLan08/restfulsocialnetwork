import pages.LoginPage
import pages.LogoutPage

import static cucumber.api.groovy.EN.*
import pages.HomePage

Given(~/^I am at the home page and logged in as "([^"]*)"$/) { String arg1 ->
    at HomePage
    assert page.isLoggedIn(arg1) == true

}
When(~/^I click the logout button$/) { ->
    to LogoutPage
}
Then(~/^I should be redirected to the login page$/) { ->
    at LoginPage

}