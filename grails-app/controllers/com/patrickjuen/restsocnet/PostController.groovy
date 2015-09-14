package com.patrickjuen.restsocnet

import grails.converters.JSON

//import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.access.annotation.Secured


@Secured(['ROLE_USER'])
class PostController {

    def index() {
        render Post.list(sort: "dateCreated", order: "desc") as JSON
    }
}
