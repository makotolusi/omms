angular.module('resource', ['ngResource'])
			.config(['$resourceProvider','$httpProvider', function ($resourceProvider,$httpProvider) {
				// Don't strip trailing slashes from calculated URLs
			    //$resourceProvider.defaults.stripTrailingSlashes = false;

				/****************************************************************
				 * Fix IE8 ajax caching issue                                   *
				 ****************************************************************/
				// $httpProvider.defaults.headers.get = $httpProvider.defaults.headers.get || {};
				// $httpProvider.defaults.headers.get['If-Modified-Since'] = '0';

			}]).factory('Article', ['$resource', function ($resource, appConfig) {
				return $resource(
									'article',
									null, 
									{
										'getAll' 				:	{ method : 'GET' , isArray : true },
										'getOnePage'	:	{ method : 'GET' , url : 'article/page/:count/:page', params : {SearchTerm : "", Status:"", count : 5, page : 1 } },

										'get'				 	:	{ method : 'GET' , url : 'article/:id', params : { id : 0} },
										'update' 			:	{ method : 'PUT'  },
										'create' 			:	{ method : 'POST' },
										'delete' 				:	{ method : 'DELETE', url : 'article/:id', params : { id : 0}  }

									}
								);


			}]).factory('Qiniu', ['$resource', function ($resource, appConfig) {
				return $resource(
						'qiniu',
						null, 
						{
							'get'				 		:	{ method : 'GET' , url : 'qiniu/:key', params : { key : 0} },
							'delete' 					:	{ method : 'DELETE', url : 'qiniu/:key', params : { key : 0}  },
							'downloadPath'		:	{ method : 'GET' , url:'qiniu/downloadPaht/:key', params : {key : 0}}

						}
					);


}]);