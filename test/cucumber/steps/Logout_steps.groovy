import static cucumber.api.groovy.EN.*
import pages.HomePage

Given(~/^I am at the home page and logged in$/) { ->
    at HomePage
    assert page.isLoggedIn() == true

}
When(~/^I click the logout button$/) { ->
    page.logout()
}
Then(~/^I should not be at the home page for logged in users$/) { ->

    assert page.isLoggedIn() == false

}