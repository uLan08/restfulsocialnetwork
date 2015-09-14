var app = angular.module("restsocnet", ['angular, ng-resourece', 'ui.router'])

app.config(['$stateProvider', '$locationProvider', function($stateProvider, $locationProvider){
    $locationProvider.html5Mode(true)
}])
