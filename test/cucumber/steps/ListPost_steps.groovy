import com.patrickjuen.restsocnet.Post

import static cucumber.api.groovy.EN.*
import pages.*

Given(~/^I am logged in$/) { ->
    to LoginPage
    at LoginPage
    page.login("user1", "password")
//    assert page.isLoggedIn("user1") == true


}
When(~/^I am at the home page$/) { ->
    at HomePage
}
Then(~/^I should see all the posts$/) { ->
    assert page.isEmpty() == false
}