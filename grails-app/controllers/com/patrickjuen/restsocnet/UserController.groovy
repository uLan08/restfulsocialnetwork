package com.patrickjuen.restsocnet

import grails.converters.JSON
import org.springframework.security.access.annotation.Secured
import com.patrickjuen.restsocnet.Post
import com.patrickjuen.restsocnet.Role
import com.patrickjuen.restsocnet.User
import com.patrickjuen.restsocnet.UserRole
import grails.plugin.gson.converters.GSON





class UserController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    @Secured(['isFullyAuthenticated()'])
    def index() {
        render User.list() as JSON
    }

    @Secured(['permitAll'])
    def save(){
        def newUser = new User(request.JSON)
        newUser.save()
        println newUser
        def role = Role.findByAuthority("ROLE_USER")
        println role
        UserRole.create(newUser, role, true);
        render (["success": true] as JSON)
    }

    @Secured(['isFullyAuthenticated()'])
    def show(){
        render User.get(params.id) as JSON
    }

    @Secured(['isFullyAuthenticated()'])
    def update(){
        def user = User.findById(params.id)
        def currentUser = User.findById(springSecurityService.principal.id)
        currentUser.addToFollowedUsers(user)
        user.addToFollowers(currentUser)
        currentUser.save(flush:true)
        user.save(flush:true)
        return (['success':true] as JSON)
    }
}
