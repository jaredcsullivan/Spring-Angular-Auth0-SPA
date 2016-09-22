(function() {

  'use strict';

  angular
    .module('springdemoApp')
    .run(function($rootScope, $location, authService, authManager, editableOptions) {

    	editableOptions.theme = 'bs3';
    	
        $rootScope.authService = authService;

        authService.registerAuthenticationListener();
        
        authManager.checkAuthOnRefresh();
                
        authManager.redirectWhenUnauthenticated();
        
    })
  
})();
