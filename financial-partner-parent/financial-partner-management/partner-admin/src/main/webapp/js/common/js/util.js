
//1.去字符串前后空格
String.prototype.Trim = function() { 
	return this.replace(/(^\s*)|(\s*$)/g, ""); 
}
//判断合法邮编
function isZip(zip){
	if(zip.value.length>0) {
		if(isNaN(zip.value) || zip.value.length != 6) {
			alert("邮编格式不正确");
			zip.focus();
	    	return false;
		}
	}
	return true;
}
//判断是否合法email格式
function isEmail(email){
	if(email.value.length>0) {
		var emailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if(!emailReg.test(email.value)) {
			alert("邮箱格式不正确");
			email.focus();
	    	return false;
		}
	}
	return true;
}
//电话是否合法
function isTelephone(telephone){
	if(telephone.value.length>0) {
		var telephoneReg = /^([0-9]|[\-])+$/g;
		if(!telephoneReg.test(telephone.value)) {
			alert("电话格式不正确");
			telephone.focus();
	    	return false;
		}
		
	}
	return true;
}

//判断时间是否合法,格式xxxx-xx-xx,yyyy-mm-dd
function formatTime(str){
	  var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);  
	  if(r==null) return false;
	  var d = new Date(r[1],r[3]-1,r[4]);     
	  return  (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
}

//只能输入金额
function moneyValidate(value){
	var moneyReg = /^\d+(\.\d{0,2})?$/;
	if(!moneyReg.test(value)){
		return false;
	}
	return true;
}


//保留2位小数
function formatFloat(x){
   var f_x = parseFloat(x);
   if (isNaN(f_x)){
     x=0;
   }
   var f_x = Math.round(x*100)/100;
   var s_x = f_x.toString();
   var pos_decimal = s_x.indexOf('.');
   if (pos_decimal < 0){
      pos_decimal = s_x.length;
      s_x += '.';
   }
   while (s_x.length <= pos_decimal + 2){
      s_x += '0';
   }
   return s_x;
}

/**
 * 格式化金额
 * 
 * @param {Object}
 *            s 金额字符串
 * @param {Object}
 *            n 保留小数位数
 * @return {TypeName}
 */
function fmoney(s, n) {
	n = n > 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var ss = s;
	if(s.indexOf("-") == 0){
		ss = s.substring(1, s.length);
	}
	var l = ss.split(".")[0].split("").reverse(), r = ss.split(".")[1];
	t = "";
	for (i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	if(s.indexOf("-") == 0){
		return "-" + t.split("").reverse().join("") + "." + r;
	}
	return t.split("").reverse().join("") + "." + r;
}

//控制输入框只能输入合法的数字,适用于数字运算
function numberText2(obj){   
     	if (navigator.userAgent.indexOf('MSIE') >= 0){ 
           var pos=getCursorPos(obj);//保存原始光标位置
        }
        //先把非数字的都替换掉，除了数字和"."还有"-"   
        obj.value = obj.value.replace(/[^\d.\d-]/g,"");   
        //当第一个字符为"."时,自动换成"-"
        obj.value = obj.value.replace(/^\./g,"-");   
        //把"-."转换成"-"
        obj.value = obj.value.replace(/^\-\./g,"-");  
        //保证只有出现一个.而没有多个.   
        obj.value = obj.value.replace(/\.{2,}/g,".");   
        obj.value =obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
         //保证只有出现一个.而没有多个"-"  
        obj.value = obj.value.replace(/\-{2,}/g,"-");
        obj.value = obj.value.replace("-","$#$").replace(/\-/g,"").replace("$#$","-"); 
        if(obj.value==''){
         	obj.value='0';
        }
        if(obj.value.indexOf('.')!=-1   &&  (obj.value.length - obj.value.lastIndexOf('.')>3))   {   
         	obj.value= obj.value.substring(0,obj.value.length-1);//只能输入两位小数
        }     
        if (navigator.userAgent.indexOf('MSIE') >= 0){ 
         setCursorPos(obj,pos);//设置光标
        } 
        if(obj.value=="0")
       		obj.select();  
 }

//判断字符串s1是否以S2结束
 function  endWith(s1,s2)  {  
	 if(s1.length<s2.length)  
	   return   false;  
	 if(s1==s2)  
	   return   true;  
	 if(s1.substring(s1.length-s2.length)==s2)  
	   return   true;  
	 return   false;  
}

//判断字符串s1是否以S2开始 		
function  startWith(s1,s2)  {  
	if(s1.length<s2.length)  
	     return   false;  
    if(s1==s2)  
	     return   true;  
	if(s1.substring(0,s2.length)==s2)  
	     return   true;  
	return   false;  
}

//创建XMLHttpRequest
function createXhr(){
		if(window.ActiveXObject){
			return new ActiveXObject("Microsoft.XMLHTTP");
		}else if(window.XMLHttpRequest){
			return new XMLHttpRequest();
		}else{
			throw new Error("Does not ajax programming");
		}
}		