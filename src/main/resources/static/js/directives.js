(function() {

	'use strict';
	
	  angular
	    .module('springdemoApp')
	    .directive('endRepeat', endRepeat);

	    endRepeat.$inject = ['$timeout'];
	    
	    function endRepeat($timeout) {
		    return {
				restrict: 'A',
				link: function (scope, element, attr) {
					if (scope.$last === true) {
						$timeout(function () {
							scope.$emit('ngRepeatFinished');
						});
					}
				}
		    }
		  }

})();