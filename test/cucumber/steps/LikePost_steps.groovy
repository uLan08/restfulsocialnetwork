import static cucumber.api.groovy.EN.*
import pages.*

//Given(~/^I am logged in and at the home page$/) { ->
//    to LoginPage
//    at LoginPage
//    page.login("user1", "password")
////    assert page.isLoggedIn("user1") == true
//
//}
When(~/^I like the post "([^"]*)"$/) { String arg1 ->
    at HomePage
    page.like()
}
Then(~/^I should see "([^"]*)" on the post$/) { String arg1 ->
    page.hasLiked(arg1)
    to LogoutPage
}