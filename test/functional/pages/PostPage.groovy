package pages

import geb.Page

/**
 * Created by Patrick on 9/14/2015.
 */
class PostPage extends Page{

    static url = "http://localhost:8090/restsocnet/"

    static at = {

    }

    static content = {
        body { $("body")}
    }

    def isEmpty(){
        return body == null
    }

}
