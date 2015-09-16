class UrlMappings {

	static mappings = {
        "/api/posts"(resources: 'post')
//        "/$controller/$action?/$id?(.$format)?"{
//            constraints {
//                // apply constraints here
//            }
//        }
        "/home"(uri: 'index.html')
        "/login"(uri: 'index.html')
        "/profile"(uri: 'index.html')
        "/logout"(uri: 'index.html')
//        "/"(view:"/index")
        "500"(view:'/error')
	}
}
