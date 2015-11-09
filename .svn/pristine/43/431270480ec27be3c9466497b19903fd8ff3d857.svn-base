/** 所有kindeditor的容器 */
WX.kindeditor = [];

/** 解析自定义的 css class */
WX.wxparser = function(cxt){
	//解析 kindeditor
	var ke = $('textarea.kindeditor', cxt);
	if(ke.length){
		$.each(ke, function(idx){
			var self = $(this);
			var keid = self.attr('id') || self.attr('name');
			var allowFileManager = self.attr('allowFileManager') || false;
			var uploadJson = ke_uploadJson || base + '/sys/file/keupload';
			var fileManagerJson = ke_fileManagerJson || base + '/sys/file/kebrowse';
			if(WX.kindeditor[keid]){
				var k = WX.kindeditor[keid];
				KindEditor.remove(k);
				k = null;
				delete WX.kindeditor[keid];
				WX.gc();
			}
			WX.kindeditor[keid] = KindEditor.create(self, {
				allowFileManager : allowFileManager,
				uploadJson : uploadJson,
				fileManagerJson : fileManagerJson
			});
		});
	}
};

/** 同步所有的kindeditor */
WX.kesync = function(){
	for(var ke in WX.kindeditor){
		if(WX.kindeditor[ke].sync) WX.kindeditor[ke].sync();
	}
};

/** 清理掉所有的kindeditor */
WX.keclean = function(){
	for(var ke in WX.kindeditor){
		if(WX.kindeditor[ke].sync){
			var k = WX.kindeditor[ke];
			k = null;
			delete WX.kindeditor[ke];
		}
	}
	WX.gc();
};