'use strict';

angular.module('jobvacancyApp')
    .controller('JobOfferController', function ($scope, JobOffer, ParseLinks) {
        $scope.jobOffers = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            JobOffer.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.jobOffers = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            JobOffer.get({id: id}, function(result) {
                $scope.jobOffer = result;
                $('#deleteJobOfferConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            JobOffer.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteJobOfferConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.jobOffer = {title: null, location: null, description: null, id: null};
        };
    });
