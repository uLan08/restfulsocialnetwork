import static cucumber.api.groovy.EN.*
import pages.*
//import grails.plugins.springsecurity.SpringSecurityService

//transient springSecurityService


Given(~/^I am at the login page$/) { ->
    to LoginPage
    at LoginPage

}
When(~/^I type "([^"]*)" in the username field and "([^"]*)" in the password field and login$/) { String arg1, String arg2 ->
    page.login(arg1, arg2)
}
Then(~/^I should be able to view the home page for logged in users$/) { ->
//    def springSecurityService = appCtx.getBean("springSecurityService")
//
//    assert springSecurityService.isLoggedIn() == true
    at HomePage
    assert page.isLoggedIn() == true
//    assert  $("div", name: "greet").text() == "You are Logged in"

}