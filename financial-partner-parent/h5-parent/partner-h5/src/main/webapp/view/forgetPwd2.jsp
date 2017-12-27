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
    <title>忘记密码</title>
    <link rel="stylesheet" href="../css/common/base.css" type="text/css">
    <link rel="stylesheet" href="../css/form.css">
</head>
<body>

<div class="hhrAll">
    <div class="ui-page hashd">
        <header class="ui-header ui-title">
            <h3><a href="javascript:;" class="back" onClick="javascript:history.back(-1);"></a>忘记密码</h3>

        </header>

        <div class="ui-view">
            <div class="ui-form regist-form">
                <form>
                    <fieldset>
                        <div class="ui-form-item code">
                            <input type="text" placeholder="请输入短信验证码"><span class="get-code" id="get-code">发送验证码</span>
                        </div>
                        <div class="ui-form-item password width90">
                            <input type="password" placeholder="请设置新登录密码"><i></i>
                        </div>
                        <div class="ui-form-item2">
                            <button type="button">完成</button>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>

    </div>
</div>

<script src="../js/lib/jquery-1.10.1/jquery-1.10.1.min.js"></script>
<script src="../js/form.js"></script>
</body>
</html>