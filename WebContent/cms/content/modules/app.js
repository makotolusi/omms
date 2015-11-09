var fcms = angular.module('fcms', ['ngRoute','resource', 'angularFileUpload']
);

fcms.config(['$routeProvider', function ($routeProvider) {
	$routeProvider
	.when('/mine', {
		templateUrl:'content/views/myArticles.html',
		controller: 'myArticlesCtl'
	})
	.when('/edit', {
		templateUrl:'content/views/articleEdit.html',
		controller: 'articleEditCtl'
	})
	.when('/edit/:id', {
		templateUrl:'content/views/articleEdit.html',
		controller: 'articleEditCtl'
	})
	.when('/view/:id', {
		templateUrl:'content/views/articleView.html',
		controller: 'articleViewCtl'
	})
	.when('/apply/:id', {
		templateUrl:'content/views/actApply.html',
		controller: 'actApplyCtl'
	})
	.when('/search', {
		templateUrl:'content/views/articleSearch.html',
		controller: 'articleSearchCtl'
	})
	.otherwise({
        redirectTo: '/edit'
    });
}]);
