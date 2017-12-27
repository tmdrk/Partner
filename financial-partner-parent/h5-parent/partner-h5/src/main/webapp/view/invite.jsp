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
    <title>邀请好友</title>
    <link rel="stylesheet" href="${basePath}/css/common/base.css" type="text/css">
    <link rel="stylesheet" href="${basePath}/css/form.css">
</head>
<body>

<div class="hhrAll">
    <div class="ui-page hashd">
        <header class="ui-header ui-title">
            <h3><a href="javascript:;" class="back" onClick="javascript:history.back(-1);"></a>邀请好友</h3>
        </header>
        <div class="ui-view">
            <div class="invite-box">
                <div class="become-partner">
                    <div class="partner-code">
                        <p>我的合伙人推荐码</p>
                        <p class="code-number">${body.partnerRecommend}</p>
                    </div>
                    <div class="step-box">
                        <ul class="step">
                            <li class="share">
                                <div></div>
                                <p><span>1</span>分享链接</p><i></i></li>
                            <li class="infomation">
                                <div></div>
                                <p><span>2</span>好友填写资料</p><i></i></li>
                            <li class="success">
                                <div></div>
                                <p><span>3</span>邀请成功</p></li>
                        </ul>
                    </div>
                    <a href="javascript:;" class="invite-btn" onclick="invite(${body.partnerRecommend});">邀请合伙人</a>
                    <p class="cue">邀请成为您的下级合伙人</p>
                </div>
            </div>
            <div class="invite-box custom">
                <div class="become-partner">
                    <div class="partner-code custom-code">
                        <p>我的客户推荐码</p>
                        <p class="code-number">${body.customerRecommend}</p>
                    </div>
                    <div class="step-box">
                        <ul class="step">
                            <li class="share2">
                                <div></div>
                                <p><span>1</span>分享链接</p><i></i></li>
                            <li class="infomation2">
                                <div></div>
                                <p><span>2</span>好友填写资料</p><i></i></li>
                            <li class="success2">
                                <div></div>
                                <p><span>3</span>邀请成功</p></li>
                        </ul>
                    </div>
                    <a href="javascript:;" class="invite-btn custom-btn" onclick="invite(${body.customerRecommend})">邀请客户</a>
                    <p class="cue">邀请成为您的客户</p>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
<
<script type="text/javascript">
    function invite(code) {

        $.get("${basePath}/invite", {"json": "{'code':'" + code + "'}"}, function (data) {
            console.log(data);
        }, "json");
    }
</script>
</html>