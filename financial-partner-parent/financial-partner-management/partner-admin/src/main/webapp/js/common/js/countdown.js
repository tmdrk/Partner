function setcountdown(theyear,themonth,theday){
yr=theyear;mo=themonth;da=theday
}//欢迎来到站长特效网，我们的网址是www.zzjs.net，很好记，zz站长，js就是js特效，本站收集大量高质量js代码，还有许多广告代码下载。
setcountdown(2020,1,12)
var occasion="站长特效网zzjs.net过期时间"
var message_on_occasion="噢，站长特效总算过期了!"
var countdownwidth='480px'
var countdownheight='20px'
var countdownbgcolor='tan'
var opentags='<font face="宋体"><small>'
var closetags='</small></font>'
var montharray=new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec")
var crosscount=''
function start_countdown(){
if (document.layers)
document.countdownnsmain.visibility="show"
else if (document.all||document.getElementById)
crosscount=document.getElementById&&!document.all?document.getElementById("countdownie") : countdownie
countdown()
}
if (document.all||document.getElementById)
document.write('<span id="countdownie" style="width:'+countdownwidth+'; background-color:'+countdownbgcolor+'"></span>')
window.onload=start_countdown
function countdown(){
var today=new Date()
var todayy=today.getYear()
if (todayy < 1000)
todayy+=1900
var todaym=today.getMonth()
var todayd=today.getDate()
var todayh=today.getHours()
var todaymin=today.getMinutes()
var todaysec=today.getSeconds()
var todaystring=montharray[todaym]+" "+todayd+", "+todayy+" "+todayh+":"+todaymin+":"+todaysec
futurestring=montharray[mo-1]+" "+da+", "+yr
dd=Date.parse(futurestring)-Date.parse(todaystring)
dday=Math.floor(dd/(60*60*1000*24)*1)
dhour=Math.floor((dd%(60*60*1000*24))/(60*60*1000)*1)
dmin=Math.floor(((dd%(60*60*1000*24))%(60*60*1000))/(60*1000)*1)
dsec=Math.floor((((dd%(60*60*1000*24))%(60*60*1000))%(60*1000))/1000*1)
if(dday<=0&&dhour<=0&&dmin<=0&&dsec<=1&&todayd==da){
if (document.layers){
document.countdownnsmain.document.countdownnssub.document.write(opentags+message_on_occasion+closetags)
document.countdownnsmain.document.countdownnssub.document.close()
}
else if (document.all||document.getElementById)
crosscount.innerHTML=opentags+message_on_occasion+closetags
return
}//欢迎来到站长特效网，我们的网址是www.zzjs.net，很好记，zz站长，js就是js特效，本站收集大量高质量js代码，还有许多广告代码下载。
else if (dday<=-1){
if (document.layers){
document.countdownnsmain.document.countdownnssub.document.write(opentags+"时间已经过了!"+closetags)
document.countdownnsmain.document.countdownnssub.document.close()
}
else if (document.all||document.getElementById)
crosscount.innerHTML=opentags+"Occasion already passed! "+closetags
return
}
else{
if (document.layers){
document.countdownnsmain.document.countdownnssub.document.write(opentags+dday+ " days, "+dhour+" hours, "+dmin+" minutes, and "+dsec+" seconds left until "+occasion+closetags)
document.countdownnsmain.document.countdownnssub.document.close()
}
else if (document.all||document.getElementById)
crosscount.innerHTML=opentags+"还有 "+dday+ " 天, "+dhour+" 小时, "+dmin+" 分, "+dsec+" 秒 就是 "+occasion+closetags
}
setTimeout("countdown()",1000)
}//欢迎来到站长特效网，我们的网址是www.zzjs.net，很好记，zz站长，js就是js特效，本站收集大量高质量js代码，还有许多广告代码下载。