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
        likeBtn4 { $("button", name:"likeBtn4")}
        followBtn1 {$("button", name:"followBtn1")}
        textArea { $("textarea") }
        postContainer  { $("div", name: "postContainer") }
        postBtn { $("button", name:"postBtn") }
        profileBtn { $("a", name: "profileBtn")}

    }

    def goToProfile(){
        profileBtn.click()
    }

    def like(){
//        $("button", text: content).click()
        waitFor{ likeBtn4 }
        likeBtn4.click()
    }

    def follow(){
        waitFor{ followBtn1 }
        followBtn1.click()
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

    def hasLiked(String content){
        return waitFor { $("div", text: contains(content)) } != null
    }

    def isEmpty(){
        return postContainer == null
    }

    def isLoggedIn(String arg){
        return loginTxt.text() == "Welcome, " + arg
    }


}