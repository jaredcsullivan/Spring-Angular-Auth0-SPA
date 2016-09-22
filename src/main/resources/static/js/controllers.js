(function() {

  'use strict';

  angular
    .module('springdemoApp')
    .controller('homeController', homeController);

    homeController.$inject = ['$scope', 'authService'];

    function homeController($scope, authService) {

      $scope.authService = authService;
      
    }
    
  angular
    .module('springdemoApp')
    .controller('dashboardController', dashboardController);

  	dashboardController.$inject = ['$scope', '$rootScope', '$location', 'authService', 'leadsFactory', 'guestFactory', '$uibModal'];

    function dashboardController($scope, $rootScope, $location, authService, leadsFactory, guestFactory, $uibModal, $log) {
    	
      var $ctrl = this;	
      $scope.leads = leadsFactory.leads; 
      $scope.guestleads = guestFactory.leads;  
      $scope.authService = authService;
            
	  var mySwiper = new Swiper ('.swiper-container', {
		  direction: 'horizontal',
  	      loop: false,
  	      observer: true,
          onSlideChangeStart: function (swiper) {
        	  $scope.$apply(function () {
        		  $rootScope.slideIndex = mySwiper.activeIndex;
                  $scope.activeSlide = mySwiper.activeIndex + 1;
              });
          },
  	      
  	      pagination: '.swiper-pagination',

  	      nextButton: '.swiper-button-next',
  	      prevButton: '.swiper-button-prev',
  	      
  	      paginationClickable: true
  	      
  	   })

	   $scope.activeSlide = mySwiper.activeIndex + 1;	
      
	   $scope.refreshSlider = function(){
		   $rootScope.slideIndex = mySwiper.activeIndex;
	   }
	   
	   $scope.$on('ngRepeatFinished', function () {  
		  $scope.leads = leadsFactory.leads; 
		  $scope.guestleads = guestFactory.leads; 
		  mySwiper.update();
		  $rootScope.slideIndex = mySwiper.activeIndex;
	   });
	   
	   $scope.$on("updateListDelete",function(e, lead){
		   var index = $scope.leads.indexOf(lead);
			 
		   $scope.leads.splice(index, 1);  
		   mySwiper.removeSlide(mySwiper.activeIndex);
		   mySwiper.update();
		   $rootScope.slideIndex = mySwiper.activeIndex;
		});
	   
	   $scope.$on("updateListCreate",function(e, lead){  
		  $scope.leads.push(lead);
		  mySwiper.update();
		  $rootScope.slideIndex = mySwiper.activeIndex;
		});
	   
	   $scope.$on("updateListDeleteGuest",function(e, lead){
		   var index = $scope.guestleads.indexOf(lead);
			 
		   $scope.guestleads.splice(index, 1);  
		   mySwiper.removeSlide(mySwiper.activeIndex);
		   mySwiper.update();
		   $rootScope.slideIndex = mySwiper.activeIndex;
		});
	   
	   $scope.$on("updateListCreateGuest",function(e, lead){  
		  $scope.guestleads.push(lead);
		  mySwiper.update();
		  $rootScope.slideIndex = mySwiper.activeIndex;
		});
      
	   $scope.updateLead = function(lead){
		   leadsFactory.update(lead);
	   };
	   
	   $scope.openNewLeadModal = function (size) {
  	    var modalInstance = $uibModal.open({
  	      animation: true,
  	      ariaLabelledBy: 'modal-title',
  	      ariaDescribedBy: 'modal-body',
  	      templateUrl: 'newLeadModal.html',
  	      controller: 'ModalInstanceController',
  	      controllerAs: '$ctrl',
  	      size: size,
  	      resolve: {
  	    	leadsList: function () {
  	          return $scope.filteredLeads;
  	        }
  	      }
  	    })};
	   
  	  $scope.openDeleteLeadModal = function (size) {
	      	    var modalInstance = $uibModal.open({
	      	      animation: true,
	      	      ariaLabelledBy: 'modal-title',
	      	      ariaDescribedBy: 'modal-body',
	      	      templateUrl: 'deleteLeadModal.html',
	      	      controller: 'ModalInstanceController',
	      	      controllerAs: '$ctrl',
	      	      size: size,
	      	      resolve: {
	      	    	leadsList: function () {
	      	          return $scope.filteredLeads;
	      	        },
	      	        leadsFactory: function () {
	        	          return leadsFactory;
	        	    }
	      	      }
	    })};
	    
	    $scope.openNewLeadModalGuest = function (size) {
	  	    var modalInstance = $uibModal.open({
	  	      animation: true,
	  	      ariaLabelledBy: 'modal-title',
	  	      ariaDescribedBy: 'modal-body',
	  	      templateUrl: 'newLeadModal.html',
	  	      controller: 'ModalInstanceGuestController',
	  	      controllerAs: '$ctrl',
	  	      size: size,
	  	      resolve: {
	  	    	leadsList: function () {
	  	          return $scope.filteredLeads;
	  	        }
	  	      }
	  	    })};
		   
	  	  $scope.openDeleteLeadModalGuest = function (size) {
		      	    var modalInstance = $uibModal.open({
		      	      animation: true,
		      	      ariaLabelledBy: 'modal-title',
		      	      ariaDescribedBy: 'modal-body',
		      	      templateUrl: 'deleteLeadModal.html',
		      	      controller: 'ModalInstanceGuestController',
		      	      controllerAs: '$ctrl',
		      	      size: size,
		      	      resolve: {
		      	    	leadsList: function () {
		      	          return $scope.filteredLeads;
		      	        },
		      	        leadsFactory: function () {
		        	          return leadsFactory;
		        	    }
		      	      }
		    })};
	      	    
   }
    
    
    angular
    .module('springdemoApp')
    .controller('ModalInstanceController', ModalInstanceController);

    function ModalInstanceController($scope, $rootScope, $uibModalInstance, leadsList, leadsFactory) {
    	
    	  var i;
    	  var $ctrl = this;
    	  
    	  i = $rootScope.slideIndex;
    	  
    	  if(!i){
    		  i = 0;
    	  }
    	  
    	  $ctrl.leadsList = leadsList;
    	  $ctrl.selected = {
    	    lead: $ctrl.leadsList[i]
    	  };
    	  
  		  $scope.createLead = function() {
  			var lead = {
  					"firstName":$scope.firstName,
  					"lastName":$scope.lastName,
  					"companyName":$scope.companyName,
  					"emailAddress":$scope.emailAddress,
  					"jobTitle":$scope.jobTitle,
  					"phoneNumber":$scope.phoneNumber
  				}
  			  	  		  
  	  		  leadsFactory.create(lead);
      		  $rootScope.$broadcast('updateListCreate', lead);

  		  }
  		
    	  $ctrl.create = function () {
    	    $uibModalInstance.close($ctrl.selected.lead);
    	  };
    	  
    	  $ctrl.delete = function () {
    		leadsFactory.delete($ctrl.selected.lead);
    		$rootScope.$broadcast('updateListDelete', $ctrl.selected.lead);
      	    $uibModalInstance.close($ctrl.selected.lead);
      	  };

    	  $ctrl.cancel = function () {
    	    $uibModalInstance.dismiss('cancel');
    	  };
    	  
    }
    
    angular
    .module('springdemoApp')
    .controller('ModalInstanceGuestController', ModalInstanceGuestController);

    function ModalInstanceGuestController($scope, $rootScope, $uibModalInstance, leadsList, guestFactory) {
    	
    	  var i;
    	  var $ctrl = this;
    	  
    	  i = $rootScope.slideIndex;
    	  
    	  if(!i){
    		  i = 0;
    	  }
    	  
    	  $ctrl.leadsList = leadsList;
    	  $ctrl.selected = {
    	    lead: $ctrl.leadsList[i]
    	  };
    	  
  		  $scope.createLead = function() {
  			var lead = {
  					"firstName":$scope.firstName,
  					"lastName":$scope.lastName,
  					"companyName":$scope.companyName,
  					"emailAddress":$scope.emailAddress,
  					"jobTitle":$scope.jobTitle,
  					"phoneNumber":$scope.phoneNumber
  				}
  			  	  		  
  			  guestFactory.create(lead);
      		  $rootScope.$broadcast('updateListCreateGuest', lead);

  		  }
  		
    	  $ctrl.create = function () {
    	    $uibModalInstance.close($ctrl.selected.lead);
    	  };
    	  
    	  $ctrl.delete = function () {
    		guestFactory.delete($ctrl.selected.lead);
    		$rootScope.$broadcast('updateListDeleteGuest', $ctrl.selected.lead);
      	    $uibModalInstance.close($ctrl.selected.lead);
      	  };

    	  $ctrl.cancel = function () {
    	    $uibModalInstance.dismiss('cancel');
    	  };
    	  
    }

})();
