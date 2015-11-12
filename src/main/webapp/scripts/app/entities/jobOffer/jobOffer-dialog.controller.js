'use strict';

angular.module('jobvacancyApp').controller('JobOfferDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'duplicate','entity', 'JobOffer', 'User',
        function($scope, $stateParams, $modalInstance, duplicate, entity, JobOffer, User) {
    	
    	if (duplicate){
    		$scope.PageTitle = "Create from this job offer"
    	}else{
    		$scope.PageTitle = "Create or Edit jobOffer"
    	}
        $scope.jobOffer = entity;
        $scope.users = User.query();
        $scope.load = function(id) {
            JobOffer.get({id : id}, function(result) {
                $scope.jobOffer = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('jobvacancyApp:jobOfferUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {        	
        	if (duplicate){
        		$scope.jobOffer.id = null;
        	}
            if ($scope.jobOffer.id != null) {
            	JobOffer.update($scope.jobOffer, onSaveFinished);
            } else {
                JobOffer.save($scope.jobOffer, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
