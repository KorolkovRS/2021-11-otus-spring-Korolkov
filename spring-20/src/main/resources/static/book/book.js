angular.module('app').controller('bookController', function ($scope, $http, $localStorage) {

    $localStorage.currentBookId = null;

    $scope.showBooks = function () {
        $http({
            url: 'api/v1/books',
            method: 'GET'
        }).then(function (response) {
            $scope.books = response.data;
        });
    };

    $scope.showAuthors = function () {
        $http({
            url: 'api/v1/authors',
            method: 'GET'
        }).then(function (response) {
            $scope.authors = response.data;
        })
    }

    $scope.showGenres = function () {
        $http({
            url: 'api/v1/genres',
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
            url: 'api/v1/books/' + bookId,
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
            url: 'api/v1/books',
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