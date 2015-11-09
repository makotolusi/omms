fcms
.directive('upload', function() {
	return {
		restrict: 'E',
		replace: true,
		scope: {
			callbackForSuccessUpload : '=onSuccess',
			callbackForDelete : '=onDelete',
			imgIndex : '=imgIndex'
		},
		controller : ['$scope','$upload','$filter','$http',function($scope,$upload,$filter,$http){

			var callbackSuccess = function(data, status, headers, config) {
				$scope.isUploadingFile = false;
				if (data == null) {
					callbackError("上传失败!");
					return;
				}
				if(data.err != null && data.err != ''){
					callbackError(data.err);
				}else{
					$scope.isUploadingFile = false;
					$scope.imgUrl = data.url;
					$scope.imgKey = data.key;
					$scope.callbackForSuccessUpload($scope.imgIndex, $scope.imgKey, $scope.imgUrl);
				}
			};

			var callbackError = function(errorMsg){
				$scope.isUploadingFile = false;
				$scope.errorInfo = errorMsg;
			};
			
			var validExts = ['jpg','jpeg','png','gif'];
			var isValidFileExtention = function (fileName) {
				for(var index in validExts){
					var re = new RegExp('\\.'+ validExts[index] +"$","i");
					if(re.test(fileName)){
						return true;
					}
				}
				return false;
			};

			$scope.uploadFile = function(files){

				$('.progress-bar').show();
				$scope.isUploadingFile = true;
				$scope.errorInfo = '';


				for (var i = 0; i < files.length; i++) {
					var file = files[i];

					$scope.fileName = file.name;

					if(file.size > 1024*1024*10){
						callbackError("文件大小不能超过10M!");

					}else if(!isValidFileExtention(file.name)){
						callbackError("不支持此类型的文件！");

					}else{
						$upload.upload({
							url: "/wx/qiniu/upload",
							file: file,
							fileFormDataName: 'uploadFile',
						})
						.success(callbackSuccess);
					}
				}
			};
			
			$scope.deleteFile = function() {
				$http({
					method : 'GET',
					url : '/wx/qiniu/delete?key=' + encodeURIComponent($scope.imgKey)
				}).success(function(data) {
					if (data == null || data == '') {
						$scope.imgUrl = null;
						callbackForDelete($scope.imgIndex);
					} else {
						$scope.errInfo = data;
					}
				});
			};
		}],
		templateUrl: './content/directive/view-upload.html'
	};
});