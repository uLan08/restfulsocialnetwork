package pages

import geb.Page

class ProfilePage extends Page{
    static url = "http://localhost:8090/restsocnet/profile/2"

    static at = {
       waitFor { $("h1").text() == "user2" }
    }

    static content = {
        followedList { $("ul", name:"followedList") }
    }

    def hasFollowed(String user){
//        return followedList.text() == user
        return $("ul", text: contains(user)) != null
    }


}