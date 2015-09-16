var app = angular.module("restsocnet", ['ngResource', 'ui.router', 'angular-storage', 'angular-jwt'])



app.config(['$stateProvider', '$locationProvider', '$urlRouterProvider', 'jwtInterceptorProvider',
    '$httpProvider', function($stateProvider, $locationProvider, $urlRouterProvider, jwtInterceptorProvider, $httpProvider){
        $locationProvider.html5Mode(true)
        $urlRouterProvider.otherwise('/home')

        jwtInterceptorProvider.tokenGetter = function(store){
            return store.get('jwt')
        }
        $httpProvider.interceptors.push('jwtInterceptor')

        $stateProvider
            .state("posts", {
                url: "/home",
                templateUrl: "partials/posts.html",
                resolve: {
                    Post: 'Post',
                    posts: ['Post', function(Post){
                        return Post.query().$promise
                    }]
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
                url: "/profile",
                templateUrl: 'partials/profile.html',
                data: {
                    requiresLogin: false
                },
                controller: 'ProfileController'
            })


}])

app.run(['$rootScope', '$state', 'store', 'jwtHelper', function($rootScope, $state, store, jwtHelper){
    $rootScope.$on('$stateChangeStart', function(e, to){
        if(to.data.requiresLogin){
            //console.log(to.data)
            //console.log(e)
            //console.log(store.get('jwt'))
            if(!store.get('jwt') || jwtHelper.isTokenExpired(store.get('jwt'))){
                e.preventDefault()
                $state.go('login')
            }
        }
    })
}])



app.factory('Post', ['$resource', function($resource){
    return $resource('api/posts/:postId', {postId: '@id'},
        {
            'update': {method: 'PUT'}
        })
}])

app.factory('Login', ['$resource', function($resource){
    return $resource('api/login')
}])






app.controller('PostController', ['$scope','posts', function($scope, posts){
    //$scope.sum = 10
    //console.log(posts)
    $scope.posts = posts
}])

app.controller('ProfileController', ['$scope', function($scope){
    $scope.temp = 'profile mo ni to!'
}])

app.controller('LogoutController', ['store', '$state', function (store, $state) {
    store.remove('jwt')
    $state.go('login')
    console.log(store.get('jwt'))
}])


app.controller('LoginController', ['$scope', 'Login', 'store', '$state', function($scope, Login, store, $state) {

    $scope.user = {}


    $scope.login = function(){
        Login.save($scope.user,
            function (success) {
                console.log(success)
                store.set('jwt', success.access_token)
                $state.go('posts')
            }, function (error) {
                $scope.user = {}
                console.log(error)
            })

    }


}])

