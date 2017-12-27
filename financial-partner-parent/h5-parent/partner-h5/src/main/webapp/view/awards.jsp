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
    <title>我的奖励</title>
    <link rel="stylesheet" href="${basePath}/css/common/base.css" type="text/css">
</head>
<body>

<div class="ui-page hasft">
    <div class="ui-view award">
        <h1 class="title">我的奖励</h1>
        <!-- 本月概况 -->
        <div class="benyue clearfix">
            <div class="item0">
                <p class="num">${body.myBonusInfo.expectedBonusOfThisMonth}</p>
                <p>本月预计获得奖励(元)</p>
            </div>
            <div class="item item1">
                <p class="num">${body.myBonusInfo.addupBonus}</p>
                <p>累计获得奖励(元)</p>
            </div>
            <span class="sep"></span>
            <div class="item item2">
                <p class="num">${body.myBonusInfo.notSettledAmount}</p>
                <p>待结算(元)</p>
            </div>
            <span class="sep"></span>
            <div class="item item1">
                <p class="num">${body.myBonusInfo.alreadySettledAmount}</p>
                <p>已结算(元)</p>
            </div>
            <a href="awardsDetail.jsp" class="btn">详情</a><!-- 链接到 - 我的奖励详情  -->
        </div>

        <!-- 我的交易概况 -->
        <div class="gaikuang">
            <div class="gkTop">
                <h2>我的交易概况</h2>
                <a class="btn" href="${basePath}/reward/toLend?style=1&userId=${body.userId}">查看详情</a>
            </div>

            <div class="gkXQ clearfix">
                <div class="total"><span class="num">${body.myInvestInfo.investAmountOfThisMonth}</span>本月交易总额(万元)</div>
                <p><span class="num">${body.myInvestInfo.addupInvestAmount}</span>本月我的交易总额(万元)</p>
                <p><span class="num">${body.myInvestInfo.addupInvestNum}</span>本月我的交易笔数(笔)</p>
                <p><span class="num">${body.myInvestInfo.myInvestAmountOfThisMonth}</span>本月客户交易总额(万元)</p>
                <p><span class="num">${body.myInvestInfo.myInvestNumOfThisMonth}</span>本月客户交易笔数(笔)</p>
                <p><span class="num">${body.myInvestInfo.customerInvestAmountOfThisMonth}</span>累计交易总额(万元)</p>
                <p><span class="num">${body.myInvestInfo.customerInvestNumOfThisMonth}</span>累计交易笔数(笔)</p>
            </div>
        </div>

        <!-- 我的下级交易概况 -->
        <div class="gaikuang bdrTop">
            <div class="gkTop">
                <h2>我的下级交易概况</h2>
                <a class="btn" href="${basePath}/reward/toLend?style=2&userId=${body.userId}">查看详情</a>
            </div>
            <div class="gkXQ clearfix">
                <div class="total"><span class="num">${body.mySubordinateInvestInfo.investAmountOfThisMonth}</span>本月交易总额(万元)</div>

                <p><span class="num">${body.mySubordinateInvestInfo.partnerInvestAmountOfThisMonth}</span>本月合伙人交易总额(万元)</p>
                <p><span class="num">${body.mySubordinateInvestInfo.customerInvestAmountOfThisMonth}</span>本月客户交易总额(万元)</p>
            </div>

            <div class="gkXQ clearfix">
                <div class="total"><span class="num font40">${body.mySubordinateInvestInfo.addupInvestAmount}</span>累计交易总额(万元)</div>

                <p><span class="num">${body.mySubordinateInvestInfo.addupPartnerInvestAmount}</span>累计合伙人交易总额(万元)</p>
                <p><span class="num">${body.mySubordinateInvestInfo.addupCustomerInvestAmount}</span>累计客户交易总额(万元)</p>
            </div>
        </div>

        <!-- 其他下级交易概况/团队管理者仅有 -->
        <div class="gaikuang bdrTop" id="teamManagement" style="display:none;">
            <div class="gkTop">
                <h2>其他下级交易概况</h2>
                <a class="btn" href="${basePath}/reward/toLend?style=3&userId=${body.userId}">查看详情</a>
            </div>
            <div class="gkXQ clearfix">
                <div class="total"><span class="num">${body.otherSubordinateInvestInfo.investAmountOfThisMonth}</span>本月交易总额(万元)</div>

                <p><span class="num">${body.otherSubordinateInvestInfo.partnerInvestAmountOfThisMonth}</span>本月合伙人交易总额(万元)</p>
                <p><span class="num">${body.otherSubordinateInvestInfo.customerInvestAmountOfThisMonth}</span>本月客户交易总额(万元)</p>
            </div>

            <div class="gkXQ clearfix">
                <div class="total"><span class="num font40">${body.otherSubordinateInvestInfo.addupInvestAmount}</span>累计交易总额(万元)</div>

                <p><span class="num">${body.otherSubordinateInvestInfo.addupPartnerInvestAmount}</span>累计合伙人交易总额(万元)</p>
                <p><span class="num">${body.otherSubordinateInvestInfo.addupCustomerInvestAmount}</span>累计客户交易总额(万元)</p>
            </div>
        </div>
    </div><!--ui-view-->

    <div class="msg atBottom">
        <img src="${basePath}/images/msg-m.png" />数据同步可能存在网络延时, 请30分钟后再次刷新查看
        <i class="close"></i>
    </div>

    <footer>
        <div class="navs">
            <a href="${basePath}/index"><i class="Ihome"></i><span>首页</span></a>
            <a href="${basePath}/loginUser/user"><i class="Iuser"></i><span>用户</span></a>
            <a href="javascript:;" class="active"><i class="Iaward"></i><span>奖励</span></a>
            <a href="${basePath}/user/userCenter"><i class="Imine"></i><span>我的</span></a>
        </div>
    </footer><!--footer-->
</div><!--ui-page-->

<script src="${basePath}/js/lib/jquery-1.10.1/jquery-1.10.1.min.js"></script>
<script src="${basePath}/js/global.js"></script>
<script>
$(function(){
	//00：普通客户，10：一般合伙人，20：团队管理者
	var div = document.getElementById("teamManagement").style.display;
	if("${body.userType}"==00){
		
	}else if("${body.userType}"==10){
		document.getElementById("hiddenDiv").style.display='none';
	}else if("${body.userType}"==20){
		document.getElementById("hiddenDiv").style.display='block';
	}
});
</script>
</body>
</html>