import com.patrickjuen.restsocnet.Role
import com.patrickjuen.restsocnet.User
import com.patrickjuen.restsocnet.UserRole

class BootStrap {

    def init = { servletContext ->
        def role = new Role(authority: "USER")
        def user1 = new User(username: "user1", password: "password")
        role.save()
        user1.save()

        UserRole.create(user1, role, true)
    }
    def destroy = {
    }
}
