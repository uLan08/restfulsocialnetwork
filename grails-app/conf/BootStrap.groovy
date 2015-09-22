import com.patrickjuen.restsocnet.Post
import com.patrickjuen.restsocnet.Role
import com.patrickjuen.restsocnet.User
import com.patrickjuen.restsocnet.UserRole

class BootStrap {

    def init = { servletContext ->
        def role = new Role(authority: "ROLE_USER")
        def user1 = new User(username: "user1", password: "password")
        role.save()
        user1.save()

        def post1 = new Post(content: "new post number 1")
        def post2 = new Post(content: "new post number 2")
        def post3 = new Post(content: "isa pa gd ")
        post1.save()
        post2.save()
        post3.save()

        user1.addToPosts(post1)
        user1.addToPosts(post2)
        user1.addToPosts(post3)

        UserRole.create(user1, role, true)
    }
    def destroy = {
    }
}
