package com.patrickjuen.restsocnet

import grails.converters.JSON
import org.springframework.security.access.annotation.Secured


@Secured('isFullyAuthenticated()')
class NotificationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def springSecurityService

    def index() {
        def notifications = Notification.list(sort: "dateCreated", order: "desc")
        def currentUser = springSecurityService.currentUser
        def notifList = []
        for(notification in notifications){
            if(notification.user == currentUser)
                notifList << notification
        }
        notifList.sort{it.dateCreated}
        notifList = notifList.reverse()
        render notifList as JSON

    }
}
