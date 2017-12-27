<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="viewport" content="target-densitydpi=375,width=750,user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta content="telephone=no,email=no" name="format-detection"/>
    <meta name="App-Config" content="fullscreen=yes,useHistoryState=yes,transition=yes"/>
    <title>结算明细</title>
    <link rel="stylesheet" href="../css/common/base.css" type="text/css">
    <link rel="stylesheet" href="../css/form.css">
</head>
<body>

<div class="hhrAll">
    <div class="ui-page hashd">
        <header class="ui-header ui-linear">
            <a class="lL" href="javascript:;" onClick="javascript:history.back(-1);"><img src="../images/arrowL.png" alt=""></a>
            <h1>12月结算明细</h1>
        </header>
        
        <div class="ui-view">
            <div class="set">
                <a href="loanBonus.html">出借奖金（元）<i class="rR"></i></a>
            </div>
            <div class="set">
                <a href="differenceBonus.html">差额奖金（元）<i class="rR"></i></a>
            </div>
            <div class="set">
                <a href="serviceBonus.html">服务奖金（元）<i class="rR"></i></a>
            </div>
        </div>
    </div>
</div>

<script src="../js/lib/jquery-1.10.1/jquery-1.10.1.min.js"></script>
<script src="../js/form.js"></script>
</body>
</html>