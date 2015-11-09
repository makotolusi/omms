fcms.controller('myArticlesCtl', function($scope, $window, Article) {
	Article.getAll(
		{},
		function(result) {
			if (result) {
				$scope.articles = result;
			} else {
				$scope.articles = [
	                     {id : '12',name:'笔记本',addr:'广顺南大街东口民航干部管理学院运动中心',  description:'宅男神器', experience:'便宜！！！'}
	                     ];
			}
		},
		function(err) {
			$window.alert(err);
		}
	);
	
	$scope.encodeURI = function(url) {
		return encodeURI(encodeURI(angular.toJson(url)));
	};

	
});

fcms.controller('articleEditCtl',function($scope, $routeParams, $window, $http, $upload, Article) {
	$scope.pieces = ['日本','韩国','美国','法国'];
	$scope.currencies = ['人民币', '日元','韩元','美元','法郎'];
	$scope.imgs = [{index:1}];
	if ($routeParams.id) {
		$scope.atc = $routeParams.obj;
	} else {
		$scope.atc = {
				piece : '日本',
				currency : '人民币'
		};
	}
	$scope.onSave = function() {
		Article.create(
			{},
			{article : $scope.atc},
			function(result) {
				$window.alert(result);
			},
			function(err) {
				$window.alert(err);
			}
		);
	};
	$scope.onSuccess = function(imgIndex, imgKey, imgUrl) {
		if ($scope.imgs.length<10) {
			$scope.imgs.push({index:$scope.imgs.length});
		}
	};
	$scopeOnDelete = function(imgIndex) {
		$scope.imgs.re
	};
	
});

fcms.controller('articleViewCtl',function($scope, $routeParams) {
    $scope.id = $routeParams.id;
});


fcms.controller('articleSearchCtl',function($scope, $routeParams) {
	$scope.pieces = ['日本','韩国','美国','法国'];
	$scope.onSearch=function(){
		$scope.atcs = [
		                    
		                     ];
	}
});