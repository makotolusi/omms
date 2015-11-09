var fcms = angular.module('fcms', ['ngRoute','resource', 'angularFileUpload']
);

fcms.config(['$routeProvider', function ($routeProvider) {
	$routeProvider
	.when('/mine', {
		templateUrl:'../js/angularjs/content/views/myArticles.html',
		controller: 'myArticlesCtl'
	})
	.when('/edit', {
		templateUrl:'../js/angularjs/content/views/articleEdit.html',
		controller: 'articleEditCtl'
	})
	.when('/edit/:id', {
		templateUrl:'../js/angularjs/content/views/articleEdit.html',
		controller: 'articleEditCtl'
	})
	.when('/view/:id', {
		templateUrl:'../js/angularjs/content/views/articleView.html',
		controller: 'articleViewCtl'
	})
	.when('/apply/:id', {
		templateUrl:'../js/angularjs/content/views/actApply.html',
		controller: 'actApplyCtl'
	})
	.when('/search', {
		templateUrl:'../js/angularjs/content/views/articleSearch.html',
		controller: 'articleSearchCtl'
	})
	.otherwise({
        redirectTo: '/edit'
    });
}]);
