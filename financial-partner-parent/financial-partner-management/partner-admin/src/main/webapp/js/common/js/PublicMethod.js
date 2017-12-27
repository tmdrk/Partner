/* 常用方法封装 */
var NS = NS || {};
(function(){    
    	NS.ajaxPost=function(url,param,callBack){
    		$.ajax({
    			type:'POST',
    			url: url,
    			data:param,
    			cache:false,
    			dateType:'text',
    			success: function (data) {
    				console.info("返回结果："+data);
    		    	var json = JSON.parse(data);//转json对象
    				callBack(json);
    			}
    		})
      },
      NS.alert=function(str){
    	  alert(str);
      },
      NS.herfUrl=function(){
    	  window.location.href = url;
      },
      //加
      NS.accAdd = function(arg1, arg2){
    	    var r1, r2, m, c;
    	    try {
    	        r1 = arg1.toString().split(".")[1].length;
    	    }
    	    catch (e) {
    	        r1 = 0;
    	    }
    	    try {
    	        r2 = arg2.toString().split(".")[1].length;
    	    }
    	    catch (e) {
    	        r2 = 0;
    	    }
    	    c = Math.abs(r1 - r2);
    	    m = Math.pow(10, Math.max(r1, r2));
    	    if (c > 0) {
    	        var cm = Math.pow(10, c);
    	        if (r1 > r2) {
    	            arg1 = Number(arg1.toString().replace(".", ""));
    	            arg2 = Number(arg2.toString().replace(".", "")) * cm;
    	        } else {
    	            arg1 = Number(arg1.toString().replace(".", "")) * cm;
    	            arg2 = Number(arg2.toString().replace(".", ""));
    	        }
    	    } else {
    	        arg1 = Number(arg1.toString().replace(".", ""));
    	        arg2 = Number(arg2.toString().replace(".", ""));
    	    }
    	    return (arg1 + arg2) / m;
    	},
    	//减
    	 NS.accSub = function(arg1, arg2) {
		    var r1, r2, m, n;
		    try {
		        r1 = arg1.toString().split(".")[1].length;
		    }
		    catch (e) {
		        r1 = 0;
		    }
		    try {
		        r2 = arg2.toString().split(".")[1].length;
		    }
		    catch (e) {
		        r2 = 0;
		    }
		    m = Math.pow(10, Math.max(r1, r2)); //last modify by deeka //动态控制精度长度
		    n = (r1 >= r2) ? r1 : r2;
		    return ((arg1 * m - arg2 * m) / m).toFixed(n);
          },
          //乘
           NS.accMul = function(arg1, arg2) {
        	    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
        	    try {
        	        m += s1.split(".")[1].length;
        	    }
        	    catch (e) {
        	    }
        	    try {
        	        m += s2.split(".")[1].length;
        	    }
        	    catch (e) {
        	    }
        	    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
        	},
        	//除,arg1除以arg2的精确结果
        	NS.accDiv = function(arg1, arg2) {
        	    var t1 = 0, t2 = 0, r1, r2;
        	    try {
        	        t1 = arg1.toString().split(".")[1].length;
        	    }
        	    catch (e) {
        	    }
        	    try {
        	        t2 = arg2.toString().split(".")[1].length;
        	    }
        	    catch (e) {
        	    }
        	    with (Math) {
        	        r1 = Number(arg1.toString().replace(".", ""));
        	        r2 = Number(arg2.toString().replace(".", ""));
        	        return (r1 / r2) * pow(10, t2 - t1);
        	    }
        	}
})();
