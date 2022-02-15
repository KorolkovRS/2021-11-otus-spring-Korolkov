angular.module('app', []).controller('booksController', function ($scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:8080';

    $localStorage.currentBookId = null;

    $scope.showBooks = function ($location) {
        console.log($location.path())
        $http({
            url: contextPath + '/api/v1/books',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                authorName: $scope.filter ? $scope.filter.authorName : null,
                genreId: $scope.filter ? $scope.filter.genreId : null,
            }
        }).then(function (response) {
            $scope.books = response.data;
        });
    };

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

    $scope.clearGenre = function () {
        $scope.filter.genreId = null;
    };

    $scope.deleteBook = function (bookId) {
        $http({
            url: contextPath + '/api/v1/books/' + bookId,
            method: 'DELETE'
        }).then(function (response) {
            $scope.showBooks();
        });
    }

    $scope.setSelectedBookId = function (bookId) {
        $localStorage.selectedBookId = bookId;
    }

    $scope.saveBook = function (book) {
        $http({
            url: contextPath + '/api/v1/books',
            method: 'POST',
            data: {
                title: book.title,
                authorId: book.authorId,
                genreId: book.genreId
            }
        }).then(function (response) {
            $scope.showBooks();
            book.title = null;
            book.authorId = null;
            book.genreId = null;
        });
    }

    $scope.showBooks();
    $scope.showAuthors();
    $scope.showGenres();
});