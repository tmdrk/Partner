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
    <title>快速注册</title>
    <link rel="stylesheet" href="../css/common/base.css" type="text/css">
    <link rel="stylesheet" href="../css/form.css">
</head>
<body>

<div class="hhrAll">
    <div class="ui-page">
        <div class="ui-view invite-wrap invite-partner">
            <div class="ui-register-form">
                <form>
                    <h4>注册</h4>
                    <ul>
                        <li><input type="text" placeholder="请输入手机号"></li>
                        <li><input type="text" placeholder="请输入图形验证码" id="validCode"><span id="checkCode" class="validate-code code-color">AGTS</span></li>
                        <li><input type="text" placeholder="请输入短信验证码"><span id="get-code" class="get-code">获取验证码</span></li>
                        <li class="password"><input type="password" placeholder="请设置密码，6-18位数字与字母组合"><i></i></li>
                        <li class="iCode">推荐码 <span>30124567890</span></li>
                    </ul>
                    <a href="javasript:;" class="regist-btn">提交</a>
                    <p class="agree">我已阅读并同意<span>《汇友钱隐私政策》</span></p>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="../js/lib/jquery-1.10.1/jquery-1.10.1.min.js"></script>
<script src="../js/form.js"></script>
</body>
</html>