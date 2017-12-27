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
    <title>我的</title>
    <link rel="stylesheet" href="${basePath}/css/common/base.css" type="text/css">
    <link rel="stylesheet" href="${basePath}/css/common/popBox.css" type="text/css">
    <link rel="stylesheet" href="${basePath}/css/form.css">
</head>
<body>

<div class="hhrAll imgBg">
    <div class="ui-page hashf">
        <header class="ui-header borderNone ui-title" style="background: transparent;">
            <h3>我的 <a href="${basePath}/view/set.jsp" class="setBtn rR"></a></h3>
        </header>

        <div class="ui-view">
            <div class="ui-img-box">
                <div class="img-box">
                    <img src="../images/user.png" alt="头像">
                </div>
                <p class="account">${body.realName}</p>
            </div>
            <div class="real-name-box">
                <div class="ui-form-item realname" id="realName">
                    <em></em>实名信息 <span class="rR" style="color: #BD8F46;">已实名</span><i></i>
                </div>
                <div class="ui-form-item contactUs" id="contactUs">
                    <em></em>联系我们 <span class="rR">工作日9:00-18:00</span><i></i>
                </div>
                <div class="ui-form-item version">
                    <em></em>当前版本 <span class="rR" style="margin-right: 0px;">V1.0.1</span>
                </div>
                <button class="btn sign-out-btn">退出登录</button>
            </div>
        </div>

        <footer>
            <div class="navs">
                <a href="${basePath}/index"><i class="Ihome"></i><span>首页</span></a>
                <a href="${basePath}/loginUser/user"><i class="Iuser"></i><span>用户</span></a>
                <a href="${basePath}/reward/toReward"><i class="Iaward"></i><span>奖励</span></a>
                <a href="javascript:;" class="active"><i class="Imine"></i><span>我的</span></a>
            </div>
        </footer><!--footer-->
    </div><!--ui-page-->
</div>

</body>
<script type="text/javascript">
    $(function () {

        var token = getToken();
        $(".btn.sign-out-btn").click(function () {
            var url = "${basePath}/user/logout";
            $.ajax({
                url: url,
                type: "POST",
                data: {"token": token},
                dataType: "json",
                success: function (json) {
                    if (json.errorCode != '9999' && json.errorCode != '200') {
                        alert(json.errorMsg);
                        return;
                    }

                    location.href = "${basePath}/user/login";

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(XMLHttpRequest);
                    console.log(textStatus);
                    console.log(errorThrown);
                    if (XMLHttpRequest.responseJSON.errorCode == '9999') {
                        location.href = "${basePath}/user/login";
                    }
                }
            });

        });

        $('#realName').click(function () {
            popbox({
                title: '实名信息',
                content: "用户名：${body.realName}<br/>身份证号：${body.idCard}"
            });
        });

        $('#contactUs').click(function () {
            popbox({
                title: '联系我们',
                tel: '400-685-8263',
                val: '呼叫',
                close: 1,
                closetext: '取消'
            })
        })

    })

</script>
</html>