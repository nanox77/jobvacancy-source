'use strict';

angular.module('jobvacancyApp')
    .controller('OffersController', function ($scope, Offer, ParseLinks) {
        $scope.jobOffers = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            Offer.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.jobOffers = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

    });

/*
angular.module('jobvacancyApp')
    .controller('OffersController', function ($scope, Principal) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });
    });
*/