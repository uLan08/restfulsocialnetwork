package pages

import geb.Page

class HomePage extends Page{
    static url = "http://localhost:8090/restsocnet/home"
    static at = {
        waitFor {title == "Home"}
    }

    static content = {
        logoutBtn { $("a", text: "Logout")}
        loginTxt { $("div", name: "greeting") }
    }

    def logout(){
        logoutBtn.click()
    }

    def isLoggedIn(String arg){
        return loginTxt.text() == "Welocome, " + arg
    }


}