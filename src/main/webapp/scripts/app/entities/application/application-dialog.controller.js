'use strict';

angular.module('jobvacancyApp').directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]).directive('checkFileSize', function () {
    return {
        restrict: 'A',
        link: function (scope, elem, attr, ctrl) {
            $(elem).bind('change', function () {
                if (this.files[0].size > attr.checkFileSize) {
                    scope.editForm.cv.$invalid = true;
                    /*scope.editForm.cv.$dirty = false;
                     scope.editForm.$invalid = true;
                     scope.editForm.$dirty = true;*/
                } else {
                    scope.editForm.cv.$invalid = false;
                    /* scope.editForm.cv.$dirty = false;
                     scope.editForm.$invalid = false;
                     scope.editForm.$dirty = false;*/
                }
                scope.$apply();
            });
        }
    }
}).service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function (jobApplication, file) {
        $http({
            method: 'POST',
            url: 'api/Application',
            headers: {'Content-Type': undefined},
            transformRequest: function () {
                var formData = new FormData();
                formData.append('jobApplication', new Blob([angular.toJson(jobApplication)], {
                    type: "application/json"
                }));
                formData.append("file", file);
                return formData;
            }
        });
    }
}]).controller('ApplicationDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'JobOffer', 'Application', 'User', 'fileUpload',
        function ($scope, $stateParams, $modalInstance, entity, JobOffer, Application, User, fileUpload) {

            $scope.jobApplication = entity;

            $scope.save = function () {
                var file = $scope.myFile;
                fileUpload.uploadFileToUrl($scope.jobApplication, file);
            };

            $scope.clear = function () {
                $modalInstance.dismiss('cancel');
            };
        }]);
