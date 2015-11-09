//=============================================================================
// easyui datagrid 键盘控制
// Esc   取消选择
// End   选中最后一行
// Home  选中第一行
// Left  循环选中上一行
// Up    循环选中上一行
// Right 循环选中下一行
// Down  循环选中下一行
// 使用： $("#xxxId").datagrid({...}).datagrid("keyCtr");
//=============================================================================
$.extend($.fn.datagrid.methods, {
	keyCtr : function(jq) {
		return jq.each(function() {
			var grid = $(this);
			grid.datagrid('getPanel').panel('panel').attr('tabindex', 1).bind('keydown', function(e) {
				var rows = grid.datagrid('getRows');
				if(rows.length > 0){
					var rowCount = rows.length;
					var selected = grid.datagrid('getSelected');
					switch (e.keyCode) {
					case 27: // Esc
						if (selected) {
							var index = grid.datagrid('getRowIndex', selected);
							grid.datagrid('unselectRow', index);
						}
						break;
					case 35: // End
						grid.datagrid('selectRow', rowCount - 1);
						break;
					case 36: // Home
						grid.datagrid('selectRow', 0);
						break;
					case 37: // Left
					case 38: // Up
						if (selected) {
							var index = grid.datagrid('getRowIndex', selected);
							if(index > 0){
								grid.datagrid('selectRow', index - 1);
							}else{
								grid.datagrid('selectRow', rowCount - 1);
							}
						} else {
							grid.datagrid('selectRow', rowCount - 1);
						}
						break;
					case 39: // Right
					case 40: // Down
						if (selected) {
							var index = grid.datagrid('getRowIndex', selected);
							if(index < rowCount-1){
								grid.datagrid('selectRow', index + 1);
							}else{
								grid.datagrid('selectRow', 0);
							}
						} else {
							grid.datagrid('selectRow', 0);
						}
						break;
					}
				}
			});
		});
	}
});


//=============================================================================
// reset easyui $.fn.combobox.defaults.valueField
//=============================================================================
$.fn.combobox.defaults.valueField = 'id';//default is 'value'

//=============================================================================
// Extend easyui form.methods.
//=============================================================================
$.extend($.fn.form.methods, {
	/** 转换为JSON 对象 */
	toJson : function(jq) {
        var arrayValue = $(jq[0]).serializeArray();
		var json = {};
		$.each(arrayValue, function() {
			var item = this;
			if (json[item["name"]]) {
				json[item["name"]] = json[item["name"]] + "," + item["value"];
			} else {
				json[item["name"]] = item["value"];
			}
		});
		return json;
	},
	/** 转换为JSON 对象的字符串形式 */
	toJsonString : function(jq) {
		JSON.stringify($(jq[0]).toObject());
	}
});

//=============================================================================
// 移除验证和还原验证
// $('#id').validatebox('remove');
// $('#id').validatebox('reduce');
//=============================================================================
$.extend($.fn.validatebox.methods, {
    remove: function(jq, newposition){
        return jq.each(function(){
            $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox');
        });
    },
    reduce: function(jq, newposition){
        return jq.each(function(){
            var opt = $(this).data().validatebox.options;
            $(this).addClass("validatebox-text").validatebox(opt);
        });
    }
});

//=============================================================================
// Extend easyui validate rules.
//=============================================================================
$.extend($.fn.validatebox.defaults.rules, {
	equals : {/** 必须与给定字段值相等 */
		validator: function(value,param){
			return value == $(param[0]).val();
		},
		message: '两个字段的值不一致'
	},
	blength : {/** 字节长度范围 */
		validator : function(value, param) {
			if(WX.isEmpty(value)) return true;
			var len = $.trim(value).getBytesLength();
			return len >= param[0] && len <= param[1];
		},
		message : "有效长度为 {0} 到 {1} 个字节"
	},
	bmin : {/** 最小字节长度 */
		validator : function(value, param) {
			if(WX.isEmpty(value)) return true;
			var len = $.trim(value).getBytesLength();
			return len >= param[0];
		},
		message : "值至少为 {0}个字节"
	},
	bmax : {/** 最大字节长度 */
		validator : function(value, param) {
			if(WX.isEmpty(value)) return true;
			var len = $.trim(value).getBytesLength();
			return len >= param[0];
		},
		message : "值至多为 {0}个字节"
	},
	date : {/** 日期格式 */
		validator : function(value) {
			if(WX.isEmpty(value)) return true;
			return /^\d{4}[\-](0?[1-9]|1[012])[\-](0?[1-9]|[12][0-9]|3[01])$/.test(value);
		},
		message : "有效的日期格式为 2008-08-08 形式"
	},
	datetime : {/** 日期时间格式 */
		validator : function(value) {
			if(WX.isEmpty(value)) return true;
			return /^\d{4}[\-](0?[1-9]|1[012])[\-](0?[1-9]|[12][0-9]|3[01])\s+(0?[0-9]|1[0-9]|2[0-3]){1}:(0?[0-9]|[1-5][0-9]){1}:(0?[0-9]|[1-5][0-9]){1}$/.test(value);
		},
		message : "有效的日期时间格式为 2008-08-08 04:24:34 形式"
	},
	time : {/** 时间格式 */
		validator : function(value) {
			if(WX.isEmpty(value)) return true;
			return /^(0?[0-9]|1[0-9]|2[0-3]){1}:(0?[0-9]|[1-5][0-9]){1}:(0?[0-9]|[1-5][0-9]){1}$/.test(value);
		},
		message : "有效的时间格式为 04:24:34 形式"
	},
	before : {/** 早于给定的日期或字段的值(字段ID必须用#开始) */
		validator : function(value) {
			if(WX.isEmpty(value)) return true;
			var cmp = null;
			return WX.parseDate(value) <= WX.parseDate(param[0]);
		},
		message : "日期必需早于{0}"
	},
	after : {/** 晚于给定的日期或字段的值(字段ID必须用#开始) */
		validator : function(value) {
			if(WX.isEmpty(value)) return true;
			return WX.parseDate(value) >= WX.parseDate(param[0]);
		},
		message : "日期必需晚于{0}"
	},
	past : {/** 早于当前日期 */
		validator : function(value) {
			if(WX.isEmpty(value)) return true;
			return WX.parseDate(value) <= WX.dtoday();
		},
		message : "日期必需早于当前日期"
	},
	future : {/** 晚于当前日期 */
		validator : function(value) {
			if(WX.isEmpty(value)) return true;
			return WX.parseDate(value) >= WX.dtoday();
		},
		message : "日期必需晚于当前日期"
	},
	integer : {/** 整数 */
		validator : function(value) {
			if(WX.isEmpty(value)) return true;
			return /^[\-\+]?\d+$/.test(value);
		},
		message : "只能填写整数"
	},
	number : {/** 数字 */
		validator : function(value) {
			if(WX.isEmpty(value)) return true;
			return /^[\-\+]?(([0-9]+)([\.,]([0-9]+))?|([\.,]([0-9]+))?)$/.test(value);
		},
		message : "只能填写数字"
	},
	min : {/** 数值必须大于等于 */
		validator : function(value, param) {
			if(WX.isEmpty(value)) return true;
			return value >= parseFloat(param[0]);
		},
		message : "数值必须大于等于 {0}"
	},
	max : {/** 数值必须小于等于 */
		validator : function(value, param) {
			if(WX.isEmpty(value)) return true;
			return value <= parseFloat(param[0]);
		},
		message : "数值必须小于等于 {0}"
	},
	range : {/** 数值范围 */
		validator : function(value, param) {
			if(WX.isEmpty(value)) return true;
			return value >= parseFloat(WX.val(param[0])) && value <= parseFloat(WX.val(param[1]));
		},
		message : "数值必须介于 {0} 到 {1} 之间"
	},
	ipv4 : {
		validator : function(value) {
			if(WX.isEmpty(value)) return true;
			return /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/.test(value);
		},
		message : "不是有效的IP地址"
	},
	onlyNumber : {
		validator : function(value) {
			if(WX.isEmpty(value)) return true;
			return /^[0-9]+$/.test(value);
		},
		message : "只能填数字"
	},
	onlyLetter : {
		validator : function(value) {
			if(WX.isEmpty(value)) return true;
			return /^[a-zA-Z]+$/.test(value);
		},
		message : "只能填大小写英文字母"
	},
	onlyLetterNumber : {
		validator : function(value) {
			if(WX.isEmpty(value)) return true;
			return /^[0-9a-zA-Z]+$/.test(value);
		},
		message : "只能填数字或大小写英文字母"
	},
	phone : {
		validator : function(value) {
			if(WX.isEmpty(value)) return true;
			return /^1[0-9]{10}$/.test(value);
		},
		message : "不是有效手机号码"
	},
	idCard : {
		validator : function(value) {
			if(WX.isEmpty(value)) return true;
			var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子
			var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值.10代表X
			
			/**
			 * 判断身份证号码为18位时最后的验证位是否正确
			 * 
			 * @param a_idCard
			 *            身份证号码数组
			 * @return
			 */
			function isTrueValidateCodeBy18IdCard(a_idCard) {
				var sum = 0; // 声明加权求和变量
				if (a_idCard[17].toLowerCase() == 'x') {
					a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作
				}
				for ( var i = 0; i < 17; i++) {
					sum += Wi[i] * a_idCard[i];// 加权求和
				}
				valCodePosition = sum % 11;// 得到验证码所位置
				if (a_idCard[17] == ValideCode[valCodePosition]) {
					return true;
				} else {
					return false;
				}
			}

			/**
			 * 验证18位数身份证号码中的生日是否是有效生日
			 * 
			 * @param idCard
			 *            18位书身份证字符串
			 * @return
			 */
			function isValidityBrithBy18IdCard(idCard18) {
				var year = idCard18.substring(6, 10);
				var month = idCard18.substring(10, 12);
				var day = idCard18.substring(12, 14);
				var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
				// 这里用getFullYear()获取年份，避免千年虫问题
				if (temp_date.getFullYear() != parseFloat(year) || temp_date.getMonth() != parseFloat(month) - 1
						|| temp_date.getDate() != parseFloat(day)) {
					return false;
				} else {
					return true;
				}
			}
			/**
			 * 验证15位数身份证号码中的生日是否是有效生日
			 * 
			 * @param idCard15
			 *            15位书身份证字符串
			 * @return
			 */
			function isValidityBrithBy15IdCard(idCard15) {
				var year = idCard15.substring(6, 8);
				var month = idCard15.substring(8, 10);
				var day = idCard15.substring(10, 12);
				var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
				// 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
				if (temp_date.getYear() != parseFloat(year) || temp_date.getMonth() != parseFloat(month) - 1
						|| temp_date.getDate() != parseFloat(day)) {
					return false;
				} else {
					return true;
				}
			}
			// 去掉字符串头尾空格
			function trim(str) {
				return str.replace(/(^\s*)|(\s*$)/g, "");
			}
			
			var idCard = trim(value.replace(/ /g, ""));
			if (idCard.length == 15) {
				return isValidityBrithBy15IdCard(idCard);
			} else if (idCard.length == 18) {
				var a_idCard = idCard.split("");// 得到身份证数组
				if (isValidityBrithBy18IdCard(idCard) && isTrueValidateCodeBy18IdCard(a_idCard)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
			//return /^[0-9]{6}(19[0-9]{2}|20[0-9]{2})(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9Xx]$/.test(value);
		},
		message : "不是有效省份证号码"
	},
	creditCard : {
		validator : function(value) {
			if(WX.isEmpty(value)) return true;
			var valid = false, cardNumber = value.replace(/ +/g, '').replace(/-+/g, '');
			var numDigits = cardNumber.length;
			if (numDigits >= 14 && numDigits <= 16 && parseInt(cardNumber) > 0) {

				var sum = 0, i = numDigits - 1, pos = 1, digit, luhn = new String();
				do {
					digit = parseInt(cardNumber.charAt(i));
					luhn += (pos++ % 2 == 0) ? digit * 2 : digit;
				} while (--i >= 0)

				for (i = 0; i < luhn.length; i++) {
					sum += parseInt(luhn.charAt(i));
				}
				valid = sum % 10 == 0;
			}
			return valid;
		},
		message : "不是有效省份证号码"
	}
});

