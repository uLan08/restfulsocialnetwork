class UrlMappings {

	static mappings = {
        "/home"(uri: 'index.html')
        "/login"(uri: 'index.html')
        "/profile"(uri: 'index.html')
        "/logout"(uri: 'index.html')
        "/register"(uri: 'index.html')
        "/api/posts"(resources: 'post')
        "/api/users"(resources: 'user')
//        "/$controller/$action?/$id?(.$format)?"{
//            constraints {
//                // apply constraints here
//            }
//        }

//        "/"(view:"/index")
        "500"(view:'/error')
	}
}
