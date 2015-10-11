import com.patrickjuen.restsocnet.Notification
import com.patrickjuen.restsocnet.Post
import com.patrickjuen.restsocnet.Role
import com.patrickjuen.restsocnet.User
import com.patrickjuen.restsocnet.UserRole
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->

        JSON.registerObjectMarshaller(User) {
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['username'] = it.username
            returnArray['posts'] = it.posts
            returnArray['followedUsers'] = it.followedUsers
            returnArray['followers'] = it.followers
            returnArray['notifications'] = it.notifications
            return returnArray
        }
        JSON.registerObjectMarshaller(Post) {
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['content'] = it.content
            returnArray['dateCreated'] = it.dateCreated
            returnArray['user'] = it.user
            returnArray['likers'] = it.likers
            return returnArray
        }
        JSON.registerObjectMarshaller(Notification){
            def returnArray = [:]
            returnArray['message'] = it.message
            returnArray['dateCreated'] = it.dateCreated
            return returnArray
        }


        def role = new Role(authority: "ROLE_USER")
        def user1 = new User(username: "user1", password: "password")
        def user2 = new User(username: "user2", password: "password")
        role.save()
        user1.save()
        user2.save()

        def post1 = new Post(content: "new post number 1")
        def post2 = new Post(content: "new post number 2")
        def post3 = new Post(content: "one more ")
        def post4 = new Post(content: "i am user2 guys hehehe")
        post1.save()
        post2.save()
        post3.save()
        post4.save()

        user1.addToPosts(post1)
        user1.addToPosts(post2)
        user1.addToPosts(post3)
        user2.addToPosts(post4)
        user1.addToFollowedUsers(user2)
        user2.addToFollowers(user1)
        post2.addToLikers(user2)
        post2.addToLikers(user1)
        post1.addToLikers(user1)

        UserRole.create(user1, role, true)
        UserRole.create(user2, role, true)

        def notif1 = new Notification(message: user2.toString() + " liked your post")
        def notif2 = new Notification(message: user1.toString() + " followed you")
        notif1.save()
        notif2.save()

        user1.addToNotifications(notif1)
        user2.addToNotifications(notif2)

    }
    def destroy = {
    }
}
