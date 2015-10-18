'use strict';

angular.module('jobvacancyApp').controller('ApplicationDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'JobOffer','Application', 'User',
        function($scope, $stateParams, $modalInstance, entity, JobOffer,Application, User) {

        $scope.jobApplication = entity;


        var onSaveFinished = function (result) {
            $scope.$emit('jobvacancyApp:jobOfferUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            Application.save($scope.jobApplication, onSaveFinished);
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
