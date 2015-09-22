package com.patrickjuen.restsocnet

import grails.converters.JSON

//import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.access.annotation.Secured
import com.patrickjuen.restsocnet.*


@Secured(['isFullyAuthenticated()'])
class PostController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def springSecurityService

    def index() {
        render Post.list(sort: "dateCreated", order: "desc") as JSON
    }

    def save(){
        def newPost = new Post(request.JSON)
        if(!newPost.hasErrors()){
            def currentUser = User.get(springSecurityService.principal.id)
            println currentUser
            newPost.user = currentUser
            newPost.save(failOnError: true)

//            currentUser.addToPosts(newPost)
            render (['success': true] as JSON)
        }
    }

    def show(){
        def post = Post.get(params.id)
        render post as JSON
    }
}
