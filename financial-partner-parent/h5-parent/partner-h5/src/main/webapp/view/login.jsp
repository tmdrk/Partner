<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/view/taglibs.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="viewport" content="target-densitydpi=375,width=750,user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta content="telephone=no,email=no" name="format-detection"/>
    <meta name="App-Config" content="fullscreen=yes,useHistoryState=yes,transition=yes"/>
    <title>登录</title>
    <link rel="stylesheet" href="${basePath}/css/common/base.css" type="text/css">
    <link rel="stylesheet" href="${basePath}/css/form.css">
    <script type="text/javascript" src="${basePath}/js/form.js"></script>
</head>
<body>

<div class="hhrAll">
    <div class="ui-page hashd">
        <header class="ui-header borderNone">
            <a href="javascript:;" class="back" onClick="javascript:history.back(-1);"></a>
        </header>
        <div class="ui-view">
            <div class="ui-logo-box">
                <img src="${basePath}/images/logo.png" alt="">
            </div>
            <div class="ui-form">
                <div class="ui-form-item username">
                    <input type="tel" name="userName" id="userName" maxlength=11 placeholder="请输入手机号"
                           value="15111111111">
                    <input type="hidden" value="${targetUrl}" id="targetUrl">
                </div>
                <div class="ui-form-item password">
                    <input type="password" name="passWord" id="passWord" placeholder="请输入密码"
                           value="test1234"><i></i>
                </div>
                <div class="ui-form-item2">
                    <button onclick="ajaxSubmit();" type="button">登 录</button>
                </div>
                <p class="ui-form-item3"><a href="${basePath}/view/forgetPwd1.jsp">忘记密码</a> <span> | </span> <a
                        href="${basePath}/view/regist.jsp">快速注册</a></p>
            </div>
        </div>

    </div><!--ui-page-->
</div>
</body>
<script type="text/javascript">

    function ajaxSubmit() {
        // todo:lxf-验证输入框
        $.ajax({
            url: "${basePath}/user/doLogin",
            type: "POST",
            data: {
                "userName": $("#userName").val(),
                "passWord": $("#passWord").val(),
                "targetUrl": $("#targetUrl").val()
            },
            dataType: "json",
            success: function (data) {
                // todo:lxf:给了失败页面后，这个就没有用了。会在服务端直接重定向到失败页面
                if (data.errorCode != '200') {
                    alert(data.errorMsg);
                    return;
                }

                if (data.targetUrl) {
                    location.href = data.targetUrl;
                } else {
                    location.href = "${basePath}/view/index.jsp";
                }
            },
            error: function (data) {
                alert(data.errorMsg);
            }
        });
    }

</script>
</html>