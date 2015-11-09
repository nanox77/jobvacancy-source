'use strict';

angular.module('jobvacancyApp')
    .controller('JobOfferDetailController', function ($scope, $rootScope, $stateParams, entity, JobOffer, User) {
        $scope.jobOffer = entity;
        $scope.postulants = [];      
        $scope.name = "Sebastian";
        $scope.email = "rolsebastian@gmail.com";
        $scope.load = function (id) {
            JobOffer.get({id: id}, function(result) {
                $scope.jobOffer = result;                
            });            
        };        
        $rootScope.$on('jobvacancyApp:jobOfferUpdate', function(event, result) {
            $scope.jobOffer = result;
        });        
        $scope.postulants= $scope.jobOffer.postulants;        
    });

