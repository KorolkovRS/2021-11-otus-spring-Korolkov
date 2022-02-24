(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'home/home.html',
            })
            .when('/info', {
                templateUrl: 'info/info.html',
                controller: 'infoController'
            })
            .when('/book', {
                templateUrl: 'book/book.html',
                controller: 'bookController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {}
})();

angular.module('app').controller('indexController', function ($scope, $http, $localStorage, $location) {
});