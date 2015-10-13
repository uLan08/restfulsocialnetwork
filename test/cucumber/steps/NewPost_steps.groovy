import static cucumber.api.groovy.EN.*
import pages.*

Given(~/^I am logged in and at the home page$/) { ->
    to LoginPage
    at LoginPage
    page.login("user1", "password")

}
When(~/^I write "([^"]*)" in the text area and click post$/) { String arg1 ->
    at HomePage
    page.post(arg1)

}
Then(~/^I should see "([^"]*)" in the list of posts$/) { String arg1 ->
    assert page.hasPosted(arg1) == true
    to LogoutPage

}
