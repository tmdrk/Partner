<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/view/taglibs.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="viewport" content="width=750,target-densitydpi=320, user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta content="telephone=no,email=no" name="format-detection"/>
    <meta name="App-Config" content="fullscreen=yes,useHistoryState=yes,transition=yes"/>
    <title>首页</title>
    <link rel="stylesheet" href="${basePath}/css/common/base.css" type="text/css">
    <link rel="stylesheet" href="${basePath}/js/lib/swiper/swiper.min.css" type="text/css">
    <link rel="stylesheet" href="${basePath}/css/index.css" type="text/css">
</head>
<body>

<div class="ui-page hasft">
    <div class="ui-view">

        <div class="swiper-container">
            <ul class="swiper-wrapper">
                <li class="swiper-slide"><a href="${basePath}/view/active.jsp"><img src="${basePath}/images/banner.png"/></a></li>
            </ul>
            <!-- Add Pagination
            <div class="swiper-pagination"></div> -->
        </div><!-- swiper-container -->

        <!-- 公告 -->
        <section class="section1">
            <i></i>
            <ul>
                <li><a href="${basePath}/view/boardDetail.jsp">汇有钱金融在9月28日正式上线</a></li>
            </ul>
            <a href="${basePath}/view/boardList.jsp" class="rR">更多</a>
        </section><!-- 公告 -->

        <!-- 内容 -->
        <section class="section2">
            <div class="ss0 clearfix">
                <div class="ss01">
                    <p><span class="num">${body.monthLoanAmount}</span></p>
                    <p>本月交易总额（万元）</p>
                </div>
                <div class="ss02">
                    <p class="p1"><span class="num">${body.monthInviteCustomer}</span>人</p>
                    <p>本月邀请客户</p>
                </div>
                <span class="sep"></span>
                <div class="ss02">
                    <p class="p1"><span class="num">${body.monthInvitePartner}</span>人</p>
                    <p>本月邀请合伙人</p>
                </div>
            </div><!-- row -->
            <div class="clearfix">
                <div class="col">
                    <p>累计交易总额</p>
                    <p class="num">${body.totalLoanAmount}万元</p>
                </div>
                <div class="col">
                    <p>累计邀请合伙人</p>
                    <p class="num">${body.totalInvitePartner}人</p>
                </div>
                <div class="col">
                    <p>累计邀请客户</p>
                    <p class="num">${body.totalInviteCustomer}人</p>
                </div>
            </div><!-- row -->
            <div class="clearfix">
                <div class="col">
                    <p>累计交易笔数</p>
                    <p class="num">${body.totalLoanOrder}单</p>
                </div>
                <div class="col">
                    <p>累计交易合伙人</p>
                    <p class="num">${body.partnerLoanOrder}人</p>
                </div>
                <div class="col">
                    <p>累计交易客户</p>
                    <p class="num">${body.customerLoanOrder}人</p>
                </div>
            </div><!-- row -->
        </section><!-- 内容 -->

        <!-- 诚邀合伙人/客户 -->
        <section class="section3">
            <a href="${basePath}/toInvite"></a>
        </section><!-- 诚邀合伙人/客户 -->

        <div class="sepH"></div><!-- sep -->

        <!-- 优质产品推荐
        <section class="section4">
            <h2>优质产品推荐</h2>
            <p class="moreCtx">更多优质产品敬请期待哟！</p>
        </section> --><!-- 优质产品推荐 -->

        <!-- 合伙人攻略 -->
        <section class="section5">
            <h2>合伙人攻略</h2>
            <a href="javascript:;" class="zhiyin">操作指引</a>
            <a href="javascript:;" class="jieshao">合伙人介绍</a>
            <a href="javascript:;" class="xuexi">学习天地</a>
            <a href="javascript:;" class="wenti">常见问题</a>
        </section>

    </div><!--ui-view-->
    <footer>
        <div class="navs">
            <a href="javascript:;" class="active"><i class="Ihome"></i><span>首页</span></a>
            <a href="${basePath}/loginUser/user"><i class="Iuser"></i><span>用户</span></a>
            <a href="${basePath}/reward/toReward"><i class="Iaward"></i><span>奖励</span></a>
            <a href="${basePath}/user/userCenter"><i class="Imine"></i><span>我的</span></a>
        </div>
    </footer><!--footer-->
</div><!--ui-page-->

<script src="${basePath}/js/lib/swiper/swiper.min.js"></script>
<script src="${basePath}/js/global.js"></script>
<script>
    var swiper = new Swiper('.swiper-container', {
        //pagination: '.swiper-pagination',
        //paginationClickable: true,
        loop: false
        //autoplay: 3000,
    });
</script>
</body>
</html>