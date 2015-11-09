//对IE定期清理内存
if (navigator.userAgent.indexOf('msie') > 0) setInterval(function() { CollectGarbage(); }, 1000);

/** 对命名空间的支持 */
function namespace(ns){
    if(typeof(ns)!="string")return;
    ns=ns.split(".");
    var o,ni;
    for(var i=0,len=ns.length;i<len,ni=ns[i];i++){
       try{o=(o?(o[ni]=o[ni]||{}):(eval(ni+"="+ni+"||{}")))}catch(e){o=eval(ni+"={}")}
    }
}

function HashMap()
{
    /** Map 大小 **/
    var size = 0;
    /** 对象 **/
    var entry = new Object();
    
    /** 存 **/
    this.put = function (key , value)
    {
        if(!this.containsKey(key))
        {
            size ++ ;
        }
        entry[key] = value;
    }
    
    /** 取 **/
    this.get = function (key)
    {
        return this.containsKey(key) ? entry[key] : null;
    }
    
    /** 删除 **/
    this.remove = function ( key )
    {
        if( this.containsKey(key) && ( delete entry[key] ) )
        {
            size --;
        }
    }
    
    /** 是否包含 Key **/
    this.containsKey = function ( key )
    {
        return (key in entry);
    }
    
    /** 是否包含 Value **/
    this.containsValue = function ( value )
    {
        for(var prop in entry)
        {
            if(entry[prop] == value)
            {
                return true;
            }
        }
        return false;
    }
    
    /** 所有 Value **/
    this.values = function ()
    {
        var values = new Array();
        for(var prop in entry)
        {
            values.push(entry[prop]);
        }
        return values;
    }
    
    /** 所有 Key **/
    this.keys = function ()
    {
        var keys = new Array();
        for(var prop in entry)
        {
            keys.push(prop);
        }
        return keys;
    }
    
    /** Map Size **/
    this.size = function ()
    {
        return size;
    }
    
    /* 清空 */
    this.clear = function ()
    {
        size = 0;
        entry = new Object();
    }
}

/** 字符串在UTF8编码下的字节长度（每个字符长度为1到4字节） */
if(!String.prototype.getBytesLength) String.prototype.getBytesLength = function() {
	var totalLength = 0;
	var charCode;
	for (var i = 0; i < this.length; i++) {
		charCode = this.charCodeAt(i);
		if (charCode < 0x007f) {
			totalLength++;
		} else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) {
			totalLength += 2;
		} else if ((0x0800 <= charCode) && (charCode <= 0xffff)) {
			totalLength += 3;
		} else{
			totalLength += 4;
		}
	}
	return totalLength;
};

/** 判断一个字符串是否为JSON串 */
if(!String.prototype.isJSON) String.prototype.isJSON = function() {
	var str = this;
	if (str == '') return false;
	str = str.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@');
	str = str.replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']');
	str = str.replace(/(?:^|:|,)(?:\s*\[)+/g, '');
	return (/^[\],:{}\s]*$/).test(str);
};

/*
 *  方法:Array.indexOf(val)
 *  功能:根据元素值查找元素索引.
 *  参数:元素值
 *  返回:如果找到则返回元素索引，否则返回-1
 */
if(!Array.prototype.indexOf) Array.prototype.indexOf = function (val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] === val) {
            return i;
        }
    }
    return -1;
};

/*
 *  方法:Array.insert(idx,val)
 *  功能:在给定索引值插入数组元素.
 *  参数:索引值，元素值
 *  返回:在原数组上修改数组
 */
if(!Array.prototype.insert) Array.prototype.insert = function(idx, val) {
	this.splice(idx, 0, val);
};

/*
 *  方法:Array.remove(val)
 *  功能:根据元素值删除数组元素.
 *  参数:元素值
 *  返回:在原数组上修改数组
 */
if(!Array.prototype.remove) Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};

/*
 *  方法:Array.removeAt(idx)
 *  功能:根据元素索引删除数组元素.
 *  参数:元素索引
 *  返回:在原数组上修改数组
 */
if(!Array.prototype.removeAt) Array.prototype.removeAt = function (idx) {
    if (isNaN(idx) || idx > this.length) {
        return false;
    }
    for (var i = 0, n = 0; i < this.length; i++) {
        if (this[i] != this[idx]) {
            this[n++] = this[i];
        }
    }
    this.length -= 1;
};
