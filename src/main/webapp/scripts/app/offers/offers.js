'use strict';

angular.module('jobvacancyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('offers', {
                parent: 'site',
                url: '/offers',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/offers/offers.html',
                        controller: 'OffersController'
                    }
                },
                resolve: {

                }
            })
            .state('application', {
                parent: 'offers',
                url: '/{id}/apply',
                data: {
                    authorities: []
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/application/application-dialog.html',
                        controller: 'ApplicationDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {fullname: null, email: null, offerId: $stateParams.id};
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('offers', null, { reload: true });
                    }, function() {
                        $state.go('offers');
                    })
                }]
            });

    });
