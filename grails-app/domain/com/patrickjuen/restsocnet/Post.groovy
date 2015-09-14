package com.patrickjuen.restsocnet

class Post {

    String content
    Date dateCreated
    static belongsTo = [user: User]
    static constraints = {
    }
}
