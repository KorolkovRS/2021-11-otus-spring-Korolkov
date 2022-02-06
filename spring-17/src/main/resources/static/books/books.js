angular.module('app').controller('booksController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080';

    $scope.showBooks = function() {
        $http({
            url: contextPath + '/api/v1/books',
            method: 'GET',
            params: {
                title: $scope.filter? $scope.filter.title: null,
                authorName: $scope.filter? $scope.filter.authorName: null,
                genreId: $scope.filter? $scope.filter.genreId: null,
            }
        }).then(function (response) {
            $scope.books = response.data;
        });
    };

    $scope.showAuthors = function() {
        $http({
            url: contextPath + '/api/v1/genres',
            method: 'GET'
        }).then(function (response) {
            $scope.genres = response.data;
        })
    }

    $scope.clearGenre = function () {
        $scope.filter.genreId = null;
    };

    $scope.showBooks();
    $scope.showAuthors();
});