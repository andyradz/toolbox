(function() {
	angular.module('mat').directive('num', function() {
		return {
			scope : {},
			restrict : 'E',
			link : function(scope,elem,attr) {
				scope.a = parseInt(attr.a, 10);
				scope.b = parseInt(attr.b, 10);
			},
			template : '{{a}} + {{b}} = {{a+b}}'
		};
	});
})();