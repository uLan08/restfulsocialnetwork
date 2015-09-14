import com.patrickjuen.restsocnet.Post

import static cucumber.api.groovy.EN.*
import pages.*

Given(~/^I am logged in$/) { ->
    to LoginPage
    at LoginPage
    page.login("user1", "password")
    to HomePage
    at HomePage
    assert page.isLoggedIn() == true


}
When(~/^I go to the posts page$/) { ->
    to PostPage
}
Then(~/^I should see all the posts$/) { ->
    assert page.isEmpty() == false
}