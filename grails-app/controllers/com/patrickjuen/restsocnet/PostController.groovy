package com.patrickjuen.restsocnet

import grails.converters.JSON
import org.springframework.security.access.annotation.Secured
import grails.plugin.gson.converters.GSON


@Secured(['isFullyAuthenticated()'])
class PostController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def springSecurityService

    def index() {
//            JSON.use('deep')
        def posts = Post.list(sort: "dateCreated", order: "desc")
        def postList = []

        for(post in posts){
            if(post.user ==  springSecurityService.currentUser){
                postList << post
            }
            for(user in springSecurityService.currentUser.followedUsers){
                if(user == post.user){
                    postList << post
                }
            }
        }
//        for(user in springSecurityService.currentUser.followedUsers){
//            for(post in posts){
//                if(user == post.user || post.user == springSecurityService.currentUser){
//                }
//            }
//        }
        postList.sort{it.dateCreated}
        postList = postList.reverse()
        render postList as JSON


    }

    def save() {
        def newPost = new Post(request.JSON)
        if (!newPost.hasErrors()) {
            def currentUser = User.get(springSecurityService.principal.id)
            println currentUser
            newPost.user = currentUser
            newPost.save(failOnError: true)

//            currentUser.addToPosts(newPost)
            render(['success': true] as JSON)
        }
    }

    def show() {
        def post = Post.get(params.id)
//        JSON.use('deep')
        render post as JSON
    }

    def update() {
        def post = Post.findById(params.id)
        if (!post.hasErrors()) {
            def currentUser = User.get(springSecurityService.principal.id)
            def notif = new Notification(message: currentUser.toString() + " liked your post (" + post.content + ")")
            post.addToLikers(currentUser)
            post.user.addToNotifications(notif)
            post.save(flush: true)
            notif.save()
            render(['success': true] as JSON)
        }
    }
}
