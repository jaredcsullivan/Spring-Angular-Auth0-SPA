(function() {

  'use strict';

  angular
    .module('springdemoApp')
    .factory('leadsFactory', ['$http', function($http){
    	
    	var o = {
    		leads: []
    	};
    	
    	o.getAll = function() {
    		return $http({
      			method: 'GET',
      			url: '/api/v1/leads/findByUsername'
    		}).then(function successCallback(response) {
    				angular.copy(response.data, o.leads);
    		}, function errorCallback(response) {
    				return response;
    		 });
    	};
    	
    	o.get = function(_id) {
    		return $http({
    			method: 'GET',
    			url: '/api/v1/leads/' + _id
    		}).then(function successCallback(data){
    			return data;
    		}, function errorCallback(response){
    			return response;
    		});
    	};
    	
    	o.create = function(lead) {
    		return $http({
    			method: 'POST',
    			data: lead,
    			url: '/api/v1/leads'
    		}).then(function successCallback(response) {
				console.log(response)
    		}, function errorCallback(response) {
				return response
    		});
    	};
    	
    	o.update = function(lead) {
            return $http({
    			method: 'PUT',
    			data: lead,
    			url: '/api/v1/leads/' + lead.id
    		}).then(function successCallback(data){
    			return data;
    		}, function errorCallback(response){
    			return response;
    		});
    	};
    	
    	o.delete = function(lead) {
            return $http({
    			method: 'DELETE',
    			url: '/api/v1/leads/' + lead.id
    		}).then(function successCallback(data){
    			return data;
    		}, function errorCallback(response){
    			return response;
    		});
    	};
    	
    	return o;
    	
    }])
    
     angular
    .module('springdemoApp')
    .factory('guestFactory', ['$http', function($http){
    	
    	var o = {
    		leads: []
    	};
    	
    	o.getAll = function() {
    		return $http({
      			method: 'GET',
      			url: '/api/v1/leads/guest/findGuest'
    		}).then(function successCallback(response) {
    				angular.copy(response.data, o.leads);
    		}, function errorCallback(response) {
    				return response;
    		 });
    	};
    	
    	o.get = function(_id) {
    		return $http({
    			method: 'GET',
    			url: '/api/v1/leads/guest/' + _id
    		}).then(function successCallback(data){
    			return data;
    		}, function errorCallback(response){
    			return response;
    		});
    	};
    	
    	o.create = function(lead) {
    		return $http({
    			method: 'POST',
    			data: lead,
    			url: '/api/v1/leads/guest'
    		}).then(function successCallback(response) {
				console.log(response)
    		}, function errorCallback(response) {
				return response
    		});
    	};
    	
    	o.update = function(lead) {
            return $http({
    			method: 'PUT',
    			data: lead,
    			url: '/api/v1/leads/guest/' + lead.id
    		}).then(function successCallback(data){
    			return data;
    		}, function errorCallback(response){
    			return response;
    		});
    	};
    	
    	o.delete = function(lead) {
            return $http({
    			method: 'DELETE',
    			url: '/api/v1/leads/guest/' + lead.id
    		}).then(function successCallback(data){
    			return data;
    		}, function errorCallback(response){
    			return response;
    		});
    	};
    	
    	return o;
    	
    }])
    
  
})();