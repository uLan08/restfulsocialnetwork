package pages

import geb.Page

class HomePage extends Page{
    static url = "http://localhost:8090/restsocnet/"
    static at = {
        title ==~/Welcome to Grails/
    }

    static content = {
        logoutBtn { $("a", text: "grails.plugin.springsecurity.LogoutController")}
        loginTxt { $("div", name: "greet") }
    }

    def logout(){
        logoutBtn.click()
    }

    def isLoggedIn(){
        return loginTxt.text() == "You are Logged in"
    }


}