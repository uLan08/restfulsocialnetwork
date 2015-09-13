package pages

import geb.Page

class LoggedInHomePage extends Page{
    static url = "/"

    static at = {
        $("div", name: "greet").text() == "You are Logged in"
    }


}