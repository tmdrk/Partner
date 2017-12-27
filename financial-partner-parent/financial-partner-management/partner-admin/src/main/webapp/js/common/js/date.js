	/**
	 * 解决IE下new Date()函数不能传参数问题
	 * @param {Object} str
	 * @return {TypeName} 
	 */
	function NewDate(str) { 
		str = str.split('-'); 
		var date = new Date(); 
		date.setUTCFullYear(str[0], str[1] - 1, str[2]); 
		date.setUTCHours(0, 0, 0, 0); 
		return date; 
	} 

	/**
	 * 计算日期间隔，返回*天*小时*分
	 * @param {Object} tmpDate
	 * @return {TypeName} 
	 */
	function millisecondToString(tmpDate){
		if(tmpDate == ''){
			return "";
		}
	    var goDate = NewDate(tmpDate);
		var nowDate = new Date();
		if(goDate <= nowDate){
			return '0天0小时0分';
		}
	    var millisecond = goDate.getTime() - nowDate.getTime();  //差值 单位毫秒
        var str = '';
        var days=Math.floor(millisecond/(24*3600*1000)) + '天'; //计算出相差天数
        //计算出小时数
        var leave1=millisecond%(24*3600*1000);     //计算天数后剩余的毫秒数
        var hours=Math.floor(leave1/(3600*1000)) + '小时';
        //计算相差分钟数
        var leave2=leave1%(3600*1000);        //计算小时数后剩余的毫秒数
        var minutes=Math.floor(leave2/(60*1000)) + '分';
        //计算相差秒数
        var leave3=leave2%(60*1000);          //计算分钟数后剩余的毫秒数
        var seconds=Math.round(leave3/1000);
        str = days + hours + minutes; 
        return str;  
    }
	
	/**
	 * 时间倒计时效果天时：分：秒
	 * @return {TypeName} 
	 */
	function timer(){ 
		if(cutoffTimeString == ''){
			return "";
		}
        var ts = (new Date(cutoffTimeString)) - (new Date());//计算剩余的毫秒数 
        if(ts*1<=0){
        	$('#leftTime_1').html("剩余时间：0天0小时0分0秒");
        	$('#leftTime_2').html("剩余时间：0天0小时0分0秒");
        	clearInterval(si);
        	return false;
        }
        var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);//计算剩余的天数 
        var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数 
        var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数 
        var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数 
        dd = checkTime(dd); 
        hh = checkTime(hh); 
        mm = checkTime(mm); 
        ss = checkTime(ss); 
        $('#leftTime_1').html("剩余时间：" + dd + "天" + hh + "时" + mm + "分" + ss + "秒");
        $('#leftTime_2').html("剩余时间：" + dd + "天" + hh + "时" + mm + "分" + ss + "秒");
    }
	
	/**
	 * 显示 天 时：分：秒
	 * @return {TypeName} 
	 */
	function showTimer(cutoffTimeString){ 
		if(cutoffTimeString == ''){
			return "";
		}
        var ts = (new Date(cutoffTimeString)) - (new Date());//计算剩余的毫秒数 
        if(ts*1<=0){
        	return "0天0小时0分";
        }
        var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);//计算剩余的天数 
        var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数 
        var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数 
        //var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数 
        dd = checkTime(dd); 
        hh = checkTime(hh); 
        mm = checkTime(mm); 
       // ss = checkTime(ss); 
        return dd + "天" + hh + "时" + mm + "分";
    }
	
	/**
	 * 格式化时间 如果为一位前面加0
	 * @param {Object} i
	 * @return {TypeName} 
	 */
    function checkTime(i)   
    {   
       if (i < 10) {   
           i = "0" + i;   
        }   
       return i;   
    }
    
    /**
     * 两时间间距
     * @param {Object} startTime
     * @param {Object} endTime
     * @return {TypeName} 
     */
    function timeInterval(startTime, endTime){
    	if(startTime == '' || endTime == ''){
			return "";
		}
        var ts = (new Date(endTime)) - (new Date(startTime));//计算剩余的毫秒数 
        if(ts*1<=0){
        	return "0天0小时0分0秒";
        }
        var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);//计算剩余的天数 
        var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数 
        var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数 
        var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数 
        dd = checkTime(dd); 
        hh = checkTime(hh); 
        mm = checkTime(mm); 
        ss = checkTime(ss); 
        return dd + "天" + hh + "时" + mm + "分" + ss + "秒";
    }