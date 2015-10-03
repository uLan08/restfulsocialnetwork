package com.patrickjuen.restsocnet

class Post {

    String content
    Date dateCreated
    User user
    static belongsTo = User
    static hasMany = [likers: User]
//    static mappedBy = [likers: "likedPost"]
    static constraints = {
        likers nullable: true
    }
    static mapping = {
        likers fetch: 'join'
        user: lazy: false
    }
}
