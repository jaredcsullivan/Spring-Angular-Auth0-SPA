(function() {

	 'use strict';
	  
	  angular.module('springdemoApp', ['auth0.lock', 'angular-jwt', 'ngRoute', 'xeditable', 'ui.bootstrap', 'ui.mask'])
	    .config(function($routeProvider, $httpProvider, lockProvider, jwtOptionsProvider, jwtInterceptorProvider) {
	      
	    	lockProvider.init({
	    		clientID: 'AUBvv45D1rXJFeffVAKGed6EcIfuUQwt',
            	domain: 'vogollc.auth0.com'
	    	});
	    	
	        jwtOptionsProvider.config({
	            tokenGetter: function() {
	              return localStorage.getItem('id_token');
	            },
	            whiteListedDomains: ['localhost'],
	            unauthenticatedRedirectPath: '/home'
	          });
	  
	        $httpProvider.interceptors.push('jwtInterceptor');
	        
	        $routeProvider
	        .when( '/', {
		          controller: 'homeController',
		          templateUrl: 'components/home.html',
		     })
	        .when( '/dashboard', {
	          controller: 'dashboardController',
	          templateUrl: 'components/dashboard.html',
	          resolve: {
	                leadsFactoryPromise: ['leadsFactory', function(leadsFactory){
	                    return leadsFactory.getAll();
	                }]
	          }
	        })
	        .when( '/guest', {
		          controller: 'dashboardController',
		          templateUrl: 'components/guest.html',
		          resolve: {
		                leadsFactoryPromise: ['guestFactory', function(guestFactory){
		                    return guestFactory.getAll();
		                }]
		          }
		        }); 

    })
   
})();