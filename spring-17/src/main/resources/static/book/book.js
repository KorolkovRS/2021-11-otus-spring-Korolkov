angular.module('app').controller('bookController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8080';

    $scope.currentBookId = $localStorage.selectedBookId;

    $scope.showBook = function() {
        $http({
            url: contextPath + '/api/v1/books/' + $scope.currentBookId,
            method: 'GET'
        }).then(function (response) {
            $scope.book = response.data;
        });
    };

    $scope.updateBook = function (updateBook) {
        $http({
            url: contextPath + '/api/v1/books',
            method: 'PUT',
            data: {
                id: $scope.book.id,
                title: updateBook.title,
                authorId: updateBook.authorId,
                genreId: updateBook.genreId
            }
        }).then(function (response) {
            $scope.showBook();
            updateBook.title = null;
            updateBook.authorId = null;
            updateBook.genreId = null;
        });
    }

    $scope.showAuthors = function () {
        $http({
            url: contextPath + '/api/v1/authors',
            method: 'GET'
        }).then(function (response) {
            $scope.authors = response.data;
        })
    }

    $scope.showGenres = function () {
        $http({
            url: contextPath + '/api/v1/genres',
            method: 'GET'
        }).then(function (response) {
            $scope.genres = response.data;
        })
    }


    $scope.showBook();
    $scope.showAuthors();
    $scope.showGenres();
});