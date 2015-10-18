'use strict';

angular.module('jobvacancyApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
