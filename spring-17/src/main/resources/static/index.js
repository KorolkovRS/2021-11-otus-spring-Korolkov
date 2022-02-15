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
            .when('/books', {
                templateUrl: 'books/books.html',
                controller: 'booksController'
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