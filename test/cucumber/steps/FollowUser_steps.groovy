import static cucumber.api.groovy.EN.*
import pages.*

Given(~/^I am logged in as "([^"]*)" and at the home page$/) { String arg1 ->
    to LoginPage
    at LoginPage
    page.login(arg1, "password")
    at HomePage
}
When(~/^I click follow "([^"]*)"$/) { String arg1 ->
    page.follow()
    at HomePage
}
Then(~/^I should see "([^"]*)" in the list of followed users in my profile$/) { String arg1 ->
    at HomePage
    page.goToProfile()
    at ProfilePage
    assert page.hasFollowed(arg1) == true
    to LogoutPage
}