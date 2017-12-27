var const_index = '首页';
/*将form表单元素的值序列化成对象 */
function serializeObject(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

/*清空*/
function clearForm(datagrid) {
	//清空textbox
	$("#searchForm input[class^=easyui-textbox]").each(function(){
		$("#"+$(this).attr("id")).textbox('setValue','');
	});
	
	//清空datebox
	$("#searchForm input[class^=easyui-datebox]").each(function(){
		$("#"+$(this).attr("id")).textbox('setValue','');
	});
	
	//清空numberbox
	$("#searchForm input[class^=easyui-numberbox]").each(function(){
		$("#"+$(this).attr("id")).textbox('setValue','');
	});
	
	//清空datetimebox
	$("#searchForm input[class^=easyui-datetimebox]").each(function(){
		$("#"+$(this).attr("id")).textbox('setValue','');
	});
	datagrid.datagrid('load', {});
	datagrid.datagrid('clearSelections');
	datagrid.datagrid('clearChecked');
}

/**
 * 限制textarea输入字数
 * @memberOf {TypeName} 
 */
function limit_textarea_input() {   
    $("textarea[maxlength]").bind('input propertychange', function() {   
        var maxLength = $(this).attr('maxlength');   
        if(maxLength == ""){
        	maxLength = 100;
        }
        if ($(this).val().length > maxLength) {   
            $(this).val($(this).val().substring(0, maxLength));   
        }else{
        	$(this).parent().find("span").remove();
        	var leave = maxLength-$(this).val().length;
        	$(this).parent().append('<span id="leaves">'+leave+'/'+maxLength+'</span>');
	    }   
    });   
}  

/**
 * 获得当前日期yyyy-mm-dd
 * @return {TypeName} 
 */
function getNowDateFormat(){
	var currentDate = new Date();
	var y = currentDate.getFullYear();
	var m = currentDate.getMonth()+1;
	var d = currentDate.getDate();            
	var date = y + "-";
    if(m < 10){
    	date += "0";
    }
    date += m + "-";
    if(d < 10){
    	date += "0";
    }
    date += d + "";

	return date;
}

/**
 * 两日期相差的天数
 * @param DateOne
 * @param DateTwo
 * @return
 */
function daysBetween(DateOne, DateTwo){   
    var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));  
    var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1);  
    var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));  
  
    var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-'));  
    var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1);  
    var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));  
  
    var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear) - Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);   
    return cha;
}

//easyui 释放iframe内存,覆盖全局的onBeforeDestroy事件。
$.fn.panel.defaults = $.extend({},$.fn.panel.defaults,{onBeforeDestroy:function(){  
    var frame=$('iframe', this);  
    if(frame.length>0){  
       frame[0].contentWindow.document.write('');  
       frame[0].contentWindow.close();  
       frame.remove();  
       if($.browser.msie){  
    	   CollectGarbage();  
       }  
    }  
}}); 

//判断浏览器版本
function getOs(){ 
    if(navigator.userAgent.indexOf("MSIE")>0) { 
         return "MSIE"; 
    } 
    if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){ 
         return "Firefox"; 
    } 
    if(isSafari=navigator.userAgent.indexOf("Safari")>0) { 
         return "Safari"; 
    }  
    if(isCamino=navigator.userAgent.indexOf("Camino")>0){ 
         return "Camino"; 
    } 
    if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){ 
         return "Gecko"; 
    } 
} 

//获得数组的最大值
Array.max = function (array) {
    return Math.max.apply(Math, array);
}

//获得数组的最小值
Array.min = function (array) {
    return Math.min.apply(Math, array);
}

//只能输入英文字母
$.extend($.fn.validatebox.defaults.rules, {
	englishRule : {
	    validator : function(value, param) { 
	        return /^[A-Za-z]+$/.test(value);
	    }, 
	    message : '只能输入英文字母' 
	}
});

//只能输入汉字
$.extend($.fn.validatebox.defaults.rules, {
	chineseRule : {
	    validator : function(value, param) { 
	        return /^[\u4e00-\u9fa5]*$/.test(value);
	    }, 
	    message : '只能输入汉字' 
	}
});

//扩展固定电话验证规则
$.extend($.fn.validatebox.defaults.rules, {   
    telephoneIsValid: {   
        validator: function(value, param){
            return /^0\d{2,3}-\d{7,8}$/.test(value);
        },   
        message: '请输入合法的固定电话'  
    }
}); 

//扩展移动电话(手机)验证规则
$.extend($.fn.validatebox.defaults.rules, {   
    mobileIsValid: {   
        validator: function(value, param){
            return /^[1][3-8]\d{9}$/.test(value);
        },   
        message: '请输入合法的移动电话'  
    }
}); 

//手机号和固话验证规则
$.extend($.fn.validatebox.defaults.rules, {   
    isMobileOrTelephoneValid: {   
        validator: function(value, param){
            return /(^[1][3-8]\d{9}$)|(^0\d{2,3}-\d{7,8}$)/.test(value);
        },   
        message: '请输入合法的手机号或固话'  
    }
}); 

//只能输入数字和字母
$.extend($.fn.validatebox.defaults.rules, {   
    numberAndLetterIsValid: {   
        validator: function(value, param){
            return /^[A-Za-z0-9]+$/.test(value);
        },   
        message: '只能输入数字和字母'  
    }
});

//只能输入数字
$.extend($.fn.validatebox.defaults.rules, {   
    isNumber: {   
        validator: function(value, param){
            return /^[0-9]+$/.test(value);
        },   
        message: '只能输入数字'  
    }
});

//必须是以字母、数字、下划线组成的字符串
$.extend($.fn.validatebox.defaults.rules, {   
    letterNumUnderlineRule: {   
        validator: function(value, param){
            return /^[a-zA-Z0-9_]*$/.test(value);
        },   
        message: '必须是以字母、数字、下划线组成的字符串'  
    }
});

//必须是以字母、数字、下划线、横杠组成的字符串
$.extend($.fn.validatebox.defaults.rules, {   
    letterNumUnderlineRuleAndBars: {   
        validator: function(value, param){
            return /^[a-zA-Z0-9_-]*$/.test(value);
        },   
        message: '必须是以字母、数字、下划线、-组成的字符串'  
    }
});

//email 邮箱
$.extend($.fn.validatebox.defaults.rules, {   
    isEmail: {   
        validator: function(value, param){
            return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
        },   
        message: '请正确填写Email'  
    }
});


//角色代码必须是以\"ROLE_\"开头的大写字母、数字、下划线组成的字符串！
$.extend($.fn.validatebox.defaults.rules, {   
    roleRule: {   
        validator: function(value, param){
            return /^ROLE_[A-Z0-9_]*$/.test(value);
        },   
        message: '角色代码必须是以\"ROLE_\"开头的大写字母、数字、下划线组成的字符串！'  
    }
});


//邮政编码
$.extend($.fn.validatebox.defaults.rules, {
	zipCodeRule:{
		validator: function(value){
			return /^\d{6}$/.test(value);
		},
		message: '请输入合法的邮政编码'
	}
});


//只能输入整数
$.extend($.fn.validatebox.defaults.rules, {
	integer:{
		validator: function(value){
			return /^[0-9]*$/.test(value);
		},
		message: '只能输入数字'
	}
});

//验证银行卡号
$.extend($.fn.validatebox.defaults.rules, {   
    isBankCardNo: {   
        validator: function(value, param){
            return value.length == param[0] || value.length == param[1] || value.length == param[2] || value.length == param[3] || value.length == param[4];  
        },   
        message: '银行账户只能是{0}、{1}、{2}、{3}、{4}位'  
    }
}); 

//合同编号
$.extend($.fn.validatebox.defaults.rules, {   
    contractNoRule: {   
        validator: function(value, param){
            return /^[A-Za-z0-9]{8,}$/.test(value);
        },   
        message: '至少8位数字或与字母组合'  
    }
}); 

//三方代扣协议编号
$.extend($.fn.validatebox.defaults.rules, {
	withholdAgreementNoRule:{
		validator: function(value){
			return /^((cj-)|(CJ-))(([\d]{4}-[\d]{4})|([\d]{9}))$/.test(value);
		},
		message: '三方代扣协议编号不合法'
	}
});

//前后两次输入不一致
$.extend($.fn.validatebox.defaults.rules, {
	equalsTo: {
		validator: function(value,param){
			return value == $(param[0]).val();
		},
		message: '前后两次输入不一致!'
	}
});

//千分位转换
function format (num) {
    return (num.toFixed(2) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
}

//身份证验证
function checkIdcard(idcard){
	  idcard = idcard.toUpperCase();
	  var Errors=new Array(
		 "验证通过!",
		 "身份证号码位数不对!",
		 "身份证号码出生日期超出范围或含有非法字符!",
		 "身份证号码校验错误!",
		 "身份证地区非法!"
	  );   
	  var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
	  var Y,JYM;   
	  var S,M;   
	  var idcard_array = idcard.split("");
	  
	  /*地区检验*/  
	  if(area[parseInt(idcard.substr(0,2))]==null) {
		  return Errors[4];   
	  }

	  /*身份号码位数及格式检验*/  
	  switch(idcard.length){   
	   case 15:   
		   if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){   
			   ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性   
		   } else {   
			   ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性   
		   }   
		   if(ereg.test(idcard)){
			   return ""; //15位验证通过   
		   } else { 
			   return Errors[2];   
		   }   
		   break;   
	      
	   case 18:   
		   //18位身份号码检测   
		   //出生日期的合法性检查    
		   //闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))   
		   //平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))   
		   if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){   
			   ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式   
		   } else {   
			   ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式   
		   }   
		   if(ereg.test(idcard)) {//测试出生日期的合法性   
			    //计算校验位   
			    S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7   
				    + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9   
				    + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10   
				    + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5   
				    + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8   
				    + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4   
				    + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2   
				    + parseInt(idcard_array[7]) * 1    
				    + parseInt(idcard_array[8]) * 6   
				    + parseInt(idcard_array[9]) * 3 ;
			    Y = S % 11;   
			    M = "F";   
			    JYM = "10X98765432";   
			    M = JYM.substr(Y,1);/*判断校验位*/  
			    if(M == idcard_array[17]){ 
			    	return ""; /*检测ID的校验位false;*/  
			    } else {   
			    	return Errors[3];   
			    }   
		   } else {   
			   return Errors[2];   
		   }
		   break;
	      
	   default:   
		   return Errors[1];   
	}   
}   
		
$.extend($.fn.validatebox.defaults.rules, {     
    idcard: {     
        validator: function(value,param){    
            return checkIdcard(value).length == 0;    
        },     
        message: '不是有效的身份证号码'    
    }     
});  


