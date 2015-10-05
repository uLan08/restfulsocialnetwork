var app = angular.module("restsocnet", ['ngResource', 'ui.router', 'angular-storage', 'angular-jwt', 'ui.router.title'])


app.config(['$stateProvider', '$locationProvider', '$urlRouterProvider', 'jwtInterceptorProvider',
    '$httpProvider', function ($stateProvider, $locationProvider, $urlRouterProvider, jwtInterceptorProvider, $httpProvider) {
        $locationProvider.html5Mode(true)
        $urlRouterProvider.otherwise('/home')

        jwtInterceptorProvider.tokenGetter = function (store) {
            return store.get('jwt')
        }
        $httpProvider.interceptors.push('jwtInterceptor')

        $stateProvider
            .state("posts", {
                url: "/home",
                templateUrl: "partials/posts.html",
                resolve: {
                    Post: 'Post',
                    posts: ['Post', function (Post) {
                        return Post.query().$promise
                    }],
                    $title: function() { return 'Home'}
                },
                data: {
                    requiresLogin: true
                },
                controller: 'PostController'
            })
            .state("login", {
                url: "/login",
                templateUrl: "partials/login.html",
                controller: 'LoginController',
                data: {
                    requiresLogin: false
                },
                resolve: {
                    $title: function() {return "Login"}
                }
            })
            .state("logout", {
                url: "/logout",
                controller: 'LogoutController',
                data: {
                    requiresLogin: true
                }
            })
            .state("profile", {
                url: "/profile/:id",
                templateUrl: 'partials/profile.html',
                resolve: {
                    User: 'User',
                    user: ['User', '$stateParams', function(User, $stateParams){
                        return User.get({userId: $stateParams.id}).$promise
                    }],
                    $title: ['user', function(user){
                        return user.username
                    }]
                },
                data: {
                    requiresLogin: true
                },
                controller: 'ProfileController'
            })
            .state("register", {
                url: "/register",
                templateUrl: "partials/register.html",
                controller: "UserController",
                data: {
                    requiresLogin: false
                },
                resolve: {
                    $title: function(){return "Register"}
                }
            })
    }])

app.run(['$rootScope', '$state', 'store', 'jwtHelper', '$stateParams', function ($rootScope, $state, store, jwtHelper, $stateParams) {
    $rootScope.$state = $state
    $rootScope.$stateParams = $stateParams

    $rootScope.$on('$stateChangeStart', function (e, to) {
        if (to.data.requiresLogin) {
            //console.log(to.data)
            //console.log(e)
            //console.log(store.get('jwt'))
            if (!store.get('jwt') || jwtHelper.isTokenExpired(store.get('jwt'))) {
                e.preventDefault()
                $state.go('login')
            }
        }
    })
}])


app.factory('Post', ['$resource', function ($resource) {
    return $resource('api/posts/:postId', {postId: '@id'},
        {
            'update': {method: 'PUT'}
        })
}])

app.factory('Login', ['$resource', function ($resource) {
    return $resource('api/login')
}])

app.factory('User', ['$resource', function ($resource) {
    return $resource('api/users/:userId', {userId: '@id'},
        {
            'update': {method: 'PUT'}
        })
}])

//function reloadPosts(Post){
//    return Post.query()
//}

app.controller('NavbarController', ['$scope', 'store', function($scope, store){
    $scope.isLoggedIn = !!store.get('jwt')
    //console.log($scope.isLoggedIn)
}])

app.controller('ProfileController', ['$scope', '$stateParams', 'user', 'User', 'store', '$state', 'Post', function($scope, $stateParams, user, User, store, $state, Post){
    $scope.newPost = {}
    $scope.user = user
    console.log($scope.user)
    //$scope.userPosts = $scope.user.posts
    //$scope.load()
    if($scope.user.username === store.get('username')){
        $scope.isCurrentUser = true;
    }

    $scope.postStatus = function () {
        console.log($scope.newPost)
        Post.save($scope.newPost,
            function (result) {
                $scope.newPost = {}
                $scope.user = User.get({userId: $stateParams.id})
                //$scope.userPosts = $scope.user.posts
                console.log($scope.user)
                console.log(result.success)
            },
            function (error) {
                console.log(error)
            })

    }


}])

app.controller('PostController', ['$scope', 'posts', 'User', 'Post', '$interval', 'store', function ($scope, posts, User, Post, $interval, store) {
    //$scope.sum = 10
    //console.log(posts)
    $scope.users = User.query()
    $scope.posts = posts
    $scope.newPost = {}
    $scope.currentUser = store.get('username')


    $scope.isCurrentUser = function(username){
        console.log(username)
        if(username === $scope.currentUser){
            return true

        }

    }


    $scope.postStatus = function () {
        Post.save($scope.newPost,
            function (result) {
                $scope.newPost = {}
                $scope.posts = Post.query()
                console.log(result.success)
            },
            function (error) {
                console.log(error)
            })
    }

    $scope.like = function(postid){
        var index;
        for(var i = 0; i < $scope.posts.length; i++ ){
            if($scope.posts[i].id === postid){
                index = i;
            }
        }
        var currentPost = Post.get({postId: postid})
        Post.update({postId: postid}, currentPost, function(response){
            console.log(response)
            $scope.posts = Post.query()
        }, function(response){
            console.log(response)
        })
    }

    $scope.follow = function (userId) {
        var userFollowed = User.get({userId: userId})
        User.update({userId: userId}, userFollowed)
    }
    //
    //$interval(function () {
    //    $scope.posts = Post.query()
    //}, 5000)
}])

app.controller('UserController', ['$scope', 'User', '$state', function ($scope, User, $state) {
    $scope.user = {}

    $scope.register = function () {
        User.save($scope.user, function (result) {
            console.log(result)
            $state.go('login')
        }, function (error) {
            console.log(error)
            $scope.user = {}
        })
    }
}])



app.controller('LogoutController', ['store', '$state', function (store, $state) {
    store.remove('jwt')
    store.remove('username')
    $state.go('login')
    //console.log(store.get('jwt'))
}])


app.controller('LoginController', ['$scope', 'Login', 'store', '$state', function ($scope, Login, store, $state) {

    $scope.user = {}
    $scope.login = function () {
        Login.save($scope.user,
            function (success) {
                console.log(success)
                store.set('jwt', success.access_token)
                store.set('username', success.username)
                $state.go('posts')
            }, function (error) {
                $scope.user = {}
                console.log(error)
            })

    }


}])

