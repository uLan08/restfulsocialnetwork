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
        textArea { $("textarea") }
        likeBtn { $("button") }
        postContainer  { $("div", name: "postContainer") }
        postBtn { $("button", name:"postBtn") }

    }

    def like(){

    }

    def post(String content){
        textArea = content
        postBtn.click()
    }

    def logout(){
        logoutBtn.click()
    }

    def hasPosted(String content){
        return $("div", text: contains(content)) != null
    }

    def isEmpty(){
        return postContainer == null
    }

    def isLoggedIn(String arg){
        return loginTxt.text() == "Welcome, " + arg
    }


}