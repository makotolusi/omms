/** 用于修正在打包为一个整体js文件后kindeditor的plugin等定位的问题 */
function _getPackedBasePath() {
	var els = document.getElementsByTagName('script'), src;
	for (var i = 0, len = els.length; i < len; i++) {
		src = els[i].src || '';
		if (/pack-all[\w\-\.]*js/.test(src)) {
			return src.substring(0, src.lastIndexOf('/')-6) + '/kindeditor/';
		}else if(/wx\.js/.test(src)){
			return src.substring(0, src.lastIndexOf('/')-4) + '/js/kindeditor/';
		}
	}
	return '/wx/js/kindeditor/';
}
KindEditor.basePath = _getPackedBasePath();