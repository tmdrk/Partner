<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/view/taglibs.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="viewport" content="width=750,target-densitydpi=320, user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta content="telephone=no,email=no" name="format-detection"/>
    <meta name="App-Config" content="fullscreen=yes,useHistoryState=yes,transition=yes"/>
    <title>交易详情</title>
    <link rel="stylesheet" href="${basePath}/css/common/base.css" type="text/css">
</head>
<body>

<div class="ui-page hasTopNavs">
    <header class="ui-header ui-linear">
        <a class="lL" href="javascript:;" onClick="javascript:history.back(-1);"><img src="${basePath}/images/arrowL.png" alt=""></a>
        <h1>交易详情</h1>
        <div class="topNavs">
            <ul class="navs navs1">
                <li class="active">
                    <a class="transaction"><span class="num" id="myLendNum">2</span>我的交易<b></b></a>
                    <ul class="navs navs2">
                        <li class="li0 active">
                            <a class="state">预约中<span class="num investStatusNum_10">2</span></a>
                        </li>
                        <li>
                            <a class="state">收益中<span class="num investStatusNum_20">3</span></a>
                        </li>
                        <li>
                            <a class="state">已结清<span class="num investStatusNum_30">4</span></a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a class="transaction"><span class="num" id="mySubordinateLendNum">2</span>我的下级交易<b></b></a>
                    <ul class="navs navs2">
                        <li class="li0 active">
                            <a class="state">预约中<span class="num investStatusNum_10">2</span></a>
                        </li>
                        <li>
                            <a class="state">收益中<span class="num investStatusNum_20">3</span></a>
                        </li>
                        <li>
                            <a class="state">已结清<span class="num investStatusNum_30">4</span></a>
                        </li>
                    </ul>
                </li>
                <li><!-- 团队管理特有 -->
                    <a class="transaction"><span class="num" id="otherSubordinateLendNum">2</span>其他下级交易<b></b></a>
                    <ul class="navs navs2">
                        <li class="li0 active">
                            <a class="state">预约中<span class="num investStatusNum_10">2</span></a>
                        </li>
                        <li>
                            <a class="state">收益中<span class="num investStatusNum_20">3</span></a>
                        </li>
                        <li>
                            <a class="state">已结清<span class="num investStatusNum_30">4</span></a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </header>

    <div class="ui-view viewBg" id="scrollPanel">


        <!-- 我的 -->
        <div class="listJJ listJY">

        </div>

        <!-- 我的下级 -->
        <div class="listJJ listJY hide">

        </div>

        <!-- 其他下级 -->
        <div class="listJJ listJY hide">

        </div>

        <div id="loading" class="loading">
            <img src="${basePath}/images/loading.gif" style="margin: 0 auto;padding: 15px 0" alt=""/>
        </div>
    </div><!--ui-view-->
</div><!--ui-page-->

<script src="${basePath}/js/lib/jquery-1.10.1/jquery-1.10.1.min.js"></script>
<script src="${basePath}/js/dropload.js"></script>
<script src="${basePath}/js/global.js"></script>
<script>
/*获取地址栏信息*/
function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}
if(GetQueryString('style')){
	if(GetQueryString('style')==1){
        $(".navs1>li:nth-child(1)").addClass("active").siblings("li").removeClass("active");
    }else if(GetQueryString('style')==2){
        $(".navs1>li:nth-child(2)").addClass("active").siblings("li").removeClass("active");
    }else if(GetQueryString('style')==3){
        $(".navs1>li:nth-child(3)").addClass("active").siblings("li").removeClass("active");
    }
}
var userId = GetQueryString('userId');//userId
var lendSize = 0;//页数
var lender = 0;//出借方
var investStatus =0;//出借状态
function itemClick(investId){
	//location.href="${basePath}/reward/toLendOrder?userId="+investId;
	location.href="${basePath}/reward/toLendOrder?investId=13";
};
$(function(){
var listInd = 0, timeoutId = null;
var headerH = $(".ui-header").height();
function viewTopChange(){
    var curActiveDom = $(".navs1 li.active");
    if(curActiveDom.find(".navs2").length>0){
        $(".ui-view").css("top",headerH+110);
    }else {
        $(".ui-view").css("top",headerH);
    }
}
viewTopChange();
function domModle(item){
    var classI = '';
    switch(item.status){
        case '预约中': classI = 'orange'; break;
        case '收益中': classI = 'red'; break;
        case '已结清': classI = 'green'; break;
        default: classI = "orange"; break;
    }
// href="'+item.investId+'"
    return '<div class="hSegLine"></div><a class="item clearfix" onclick="itemClick('+item.investId+');">'+
                '<h2><label>'+item.title+'</label><span class="btn '+classI+'">'+item.status+'</span></h2>'+
                '<div class="itembd">'+
                    '<p class="imp">'+item.uJinE.value+'<label>出借金额(元)</label></p>'+
                    '<p>'+item.uLimit.value+'<label>出借期限(月)</label></p>'+
                    '<p>'+item.uHuiKuan.value+'<label>回款日期</label></p>'+
                    '<p class="p0">出借时间: '+item.uDate.value+'</p>'+
                    '<p class="p1">出借用户: '+item.uName.value+'</p>'+
                '</div>'+
            '</a>';
}
function addHtml(data){
	$("#myLendNum").text(data.myLendNum);
	$("#mySubordinateLendNum").text(data.mySubordinateLendNum);
	$("#otherSubordinateLendNum").text(data.otherSubordinateLendNum);
	$(".investStatusNum_10").text(data.investStatusNum_10);
	$(".investStatusNum_20").text(data.investStatusNum_20);
	$(".investStatusNum_30").text(data.investStatusNum_30);
}
function loadData(data) {
	//封装数据
    var  loanList= {
        title:"【普惠出借】24个月",
        statusId:"11",
        status:"预约中",
        uJinE:{
            name:"出借金额(元)",value:"1,000.00"
        },
        uLimit:{
            name:"出借期限(月)",value:"24"
        },
        uHuiKuan:{
            name:"回款日期",value:"-- --"
        },
        uName:{
            name:"出借用户:", value:"张凤三(合伙人)"
        },
        uDate:{
            name:"出借时间: ",value:"2017-10-01 23:35:36"
        },
        investId:"13"
	}
    if(data.length>0){
        var counter = 0, num = 3, // 每页展示3个
            pageStart = 0, pageEnd = 0;
        // dropload
        $('#scrollPanel').dropload({
            scrollArea : $("#scrollPanel"),
            loadDownFn : function(me){
                var result = '';
                counter++;
                pageEnd = num * counter;
                pageStart = pageEnd - num;
				
                for(var i=pageStart;i<pageEnd;i++){
                	
                	loanList.title=data[i].productName;
                	loanList.statusId=data[i].investStatus;//
                	loanList.status=data[i].investStatusStr;
                	loanList.uJinE.value=data[i].investAmount;
                	loanList.uLimit.value=data[i].productTerm;
                	loanList.uHuiKuan.value=data[i].redemption_date;
                	loanList.uDate.value=data[i].payDate;
                	loanList.uName.value=data[i].realName;
                	loanList.investId=data[i].investId;
                    result+= domModle(loanList);
					
                    if((i + 1) >= data.length){
                        // 锁定
                        me.lock();
                        // 无数据
                        me.noData();
                        break;
                    }
                }
                // 为了测试，延迟1秒加载
                timeoutId = setTimeout(function(){
                    $('.listJJ').eq(listInd).append(result);

                    // 每次数据加载完，必须重置
                    me.resetload();
                },1000);
            },
            threshold : 50
        });
    }else {
    	alert("没有数据");
        $(".loading").hide();
    }

    $('.dropload-up,.dropload-down').hide();
}
	function doClick(){
	//lender 出借方(0我的出借，1我的下级出借，2其他下级出借)
	//investStatus  出借状态（10预约中20收益中30已结清）
	//userId //userId
	var pageNo = lendSize;
	lendSize=lendSize+1;
	$.ajax({
        url: "${basePath}/reward/doLendList",
        type: "POST",
        data: {
            "lender": lender,
            "investStatus": investStatus,
            "userId": userId,
            "pageNo": lendSize,
            "pageSize": 3
        },
        dataType: "json",
        success: function (datad) {
            if (datad.errorCode != '200') {
            	location.href = "${basePath}/user/login";
                return;
            }
            addHtml(datad.body.body);
        	loadData(datad.body.body);
        	//loadData(data);
        },
        error: function (datad) {
            alert(datad.errorMsg);
        }});
	}
	//初始化
	doClick();
	//交易详情
	function doCss(curDom){
		//清空定时器
		clearTimeout(timeoutId);
		
		listInd= $(curDom.parentNode).index();
		$(curDom.parentNode).addClass("active").siblings("li").removeClass("active");
		$(".ui-view .listJJ").eq(listInd).removeClass("hide").siblings(".listJJ").addClass("hide");
		$(".ui-view").scrollTop(0);
		
		viewTopChange();
		
		//清空
		$(".ui-view .listJJ").eq(listInd).empty();
		$(".loading").show();
		//获取交易详情 数据
		doClick();
	}
	$(".transaction").click(function(){
		var curDom = this;
		lender= $(this.parentNode).index();
		doCss(curDom);
	});

	$(".state").click(function(){
		var curDom = this;
		investStatus= $(this.parentNode).index();
		doCss(curDom);
	});
});
</script>
</body>
</html>