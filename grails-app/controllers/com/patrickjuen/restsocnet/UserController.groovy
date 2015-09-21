package com.patrickjuen.restsocnet

import grails.converters.JSON
import org.springframework.security.access.annotation.Secured
import com.patrickjuen.restsocnet.Post
import com.patrickjuen.restsocnet.Role
import com.patrickjuen.restsocnet.User
import com.patrickjuen.restsocnet.UserRole



class UserController {

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
}
