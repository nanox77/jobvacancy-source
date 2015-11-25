'use strict';

angular.module('jobvacancyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('jobOffer', {
                parent: 'entity',
                url: '/jobOffers',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'JobOffers'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/jobOffer/jobOffers.html',
                        controller: 'JobOfferController'
                    }
                },
                resolve: {}
            })
            .state('jobOffer.detail', {
                parent: 'entity',
                url: '/jobOffer/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'JobOffer'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/jobOffer/jobOffer-detail.html',
                        controller: 'JobOfferDetailController'
                    }
                },
                resolve: {
                    duplicate: function () {
                        return false;
                    },
                    ptitle: function () {
                        return "Create a new job offer";
                    },

                    entity: ['$stateParams', 'JobOffer', function ($stateParams, JobOffer) {
                        return JobOffer.get({id: $stateParams.id});
                    }]
                }
            })
            .state('jobOffer.new', {
                parent: 'jobOffer',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function ($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/jobOffer/jobOffer-dialog.html',
                        controller: 'JobOfferDialogController',
                        size: 'lg',
                        resolve: {
                            duplicate: function () {
                                return false;
                            },
                            ptitle: function () {
                                return "Create a new job offer";
                            },
                            entity: function () {
                                return {title: null, location: null, description: null, id: null};
                            }
                        }
                    }).result.then(function (result) {
                            $state.go('jobOffer', null, {reload: true});
                        }, function () {
                            $state.go('jobOffer');
                        })
                }]
            })
            .state('jobOffer.duplicate', {
                parent: 'jobOffer',
                url: '/{id}/duplicate',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function ($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/jobOffer/jobOffer-dialog.html',
                        controller: 'JobOfferDialogController',
                        size: 'lg',
                        resolve: {
                            duplicate: function () {
                                return true;
                            },
                            ptitle: function () {
                                return "Create from this job offer";
                            },
                            entity: ['JobOffer', function (JobOffer) {
                                return JobOffer.get({id: $stateParams.id});
                            }]

                        }
                    }).result.then(function (result) {
                            $state.go('jobOffer', null, {reload: true});
                        }, function () {
                            $state.go('^');
                        })
                }]
            })
            .state('jobOffer.finalize', {
                parent: 'jobOffer',
                url: '/{id}/finalize',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function ($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/jobOffer/jobOffer-finalize.html',
                        controller: 'JobOfferDialogController',
                        size: 'lg',
                        resolve: {
                            duplicate: function () {
                                return false;
                            },
                            ptitle: function () {
                                return "Finalize this job offer";
                            },
                            entity: ['JobOffer', function (JobOffer) {
                                return JobOffer.get({id: $stateParams.id});
                            }]

                        }
                    }).result.then(function (result) {
                            $state.go('jobOffer', null, {reload: true});
                        }, function () {
                            $state.go('^');
                        })
                }]
            })
            .state('jobOffer.edit', {
                parent: 'jobOffer',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function ($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/jobOffer/jobOffer-dialog.html',
                        controller: 'JobOfferDialogController',
                        size: 'lg',
                        resolve: {
                            duplicate: function () {
                                return false;
                            },
                            ptitle: function () {
                                return "Edit this job offer";
                            },
                            entity: ['JobOffer', function (JobOffer) {
                                return JobOffer.get({id: $stateParams.id});
                            }]
                        }
                    }).result.then(function (result) {
                            $state.go('jobOffer', null, {reload: true});
                        }, function () {
                            $state.go('^');
                        })
                }]
            });
    });
