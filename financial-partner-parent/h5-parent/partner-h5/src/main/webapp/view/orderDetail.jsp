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
    <title>订单详情</title>
    <link rel="stylesheet" href="${basePath}/css/common/base.css" type="text/css">
</head>
<body>

<div class="ui-page hashd">
    <header class="ui-header ui-linear">
        <a class="lL" href="javascript:;" onClick="javascript:history.back(-1);"><img src="${basePath}/images/arrowL.png" alt=""></a>
        <h1>订单详情</h1>
    </header>

    <div class="ui-view">
        <!-- 提示信息/预约中提示 只有预约中出现该提示 -->
        <div class="msg"><img src="${basePath}/images/msg-m.png" />T+1个工作日起息<i class="rR close"></i></div>
        <div class="orderDetail">
            <h2>${body.productName}</h2>
            <ul class="list1">
                <li>
                    <label>合同编号</label>
                    <span>${body.investId}</span>
                </li>
                <li>
                    <label>出借金额(元)</label>
                    <span>${body.investAmount}</span>
                </li>
                <li>
                    <label>订单状态</label>
                    <span class="orange Appointment">${body.investStatusStr}</span>
                    <!-- <span class="green">已结清</span>
                    <span class="red">收益中</span>-->
                </li>
                <li>
                    <label>下单时间</label>
                    <span>${body.buyDate}</span>
                </li>
                <li>
                    <label>年均出借回报率</label>
                    <span>${body.annualRate}</span>
                </li>
                <li>
                    <label>出借收益(元)</label>
                    <span>${body.investIncome}</span>
                    <!-- <span>-- -- --</span> -->
                </li>
                <li>
                    <label>回款金额(元)</label>
                    <span>${body.investRepayAmount}</span>
                    <!-- <span>-- -- --</span> -->
                </li>
                <li>
                    <label>回款日期</label>
                    <span>${body.redemptionDate}</span>
                    <!-- <span>-- -- --</span> -->
                </li>
                <li>
                    <label>回款方式</label>
                    <span class="red">${body.payBackTypeStr}</span>
                </li>
            </ul>
        </div>

    </div><!--ui-view-->
</div><!--ui-page-->

<script src="${basePath}/js/lib/jquery-1.10.1/jquery-1.10.1.min.js"></script>
<script src="${basePath}/js/global.js"></script>
</body>
</html>