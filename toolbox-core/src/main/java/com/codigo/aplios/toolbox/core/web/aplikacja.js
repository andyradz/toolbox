/**
 * Controller aplikacja
 */

(function() {
	var app = angular.module('aplikacja', [ 'mat', 'myServiceModule' ]);
	app.controller('kontrolerTabeliSkoczkow', [ '$scope', function($scope) {
		$scope.developer = "Andrzej Radziszewski"
		$scope.skoczkowie = {
			"Name" : 1
		};
	} ]);
})();