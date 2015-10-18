'use strict';

angular.module('jobvacancyApp')
    .controller('JobOfferDetailController', function ($scope, $rootScope, $stateParams, entity, JobOffer, User) {
        $scope.jobOffer = entity;
        $scope.load = function (id) {
            JobOffer.get({id: id}, function(result) {
                $scope.jobOffer = result;
            });
        };
        $rootScope.$on('jobvacancyApp:jobOfferUpdate', function(event, result) {
            $scope.jobOffer = result;
        });
    });
