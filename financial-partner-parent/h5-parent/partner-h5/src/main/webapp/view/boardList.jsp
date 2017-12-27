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
    <title>平台公告</title>
    <link rel="stylesheet" href="../css/common/base.css" type="text/css">
    <link rel="stylesheet" href="../css/board.css" type="text/css">
</head>
<body>

<div class="hhrAll">
    <div class="ui-page hashd" style="background: #F5F5F5;">
        <header class="ui-header ui-title">
            <h3><a href="javascript:;" class="back" onClick="javascript:history.back(-1);"></a>平台公告</h3>
        </header>
        <div class="ui-view">
            <div class="ui-list-box">
                <ul>
                    <li>
                        <p class="board-date"><span>2017.10.7</span> <span>23:25:48</span></p>
                        <div class="board-title-box" data-url="boardDetail.html">
                            <!--已读消息在此处添加类名 info-read -->
                            <em class="lL info"></em>
                            <!--data-url跳转地址-->
                            <p class="rR board-title">汇有钱金融2017年12月28日正式上线…</p>
                            <i class="next rR"></i>
                        </div>
                    </li>
                    <li>
                        <p class="board-date"><span>2017.10.7</span> <span>23:25:48</span></p>
                        <div class="board-title-box" data-url="boardDetail.html">
                            <!--已读消息在此处添加类名 info-read -->
                            <em class="lL info"></em>
                            <!--data-url跳转地址-->
                            <p class="rR board-title">汇有钱金融2017年12月28日正式上线…</p>
                            <i class="next rR"></i>
                        </div>
                    </li>
                    <li>
                        <p class="board-date"><span>2017.10.7</span> <span>23:25:48</span></p>
                        <div class="board-title-box" data-url="boardDetail.html">
                            <!--已读消息在此处添加类名 info-read -->
                            <em class="lL info"></em>
                            <!--data-url跳转地址-->
                            <p class="rR board-title">汇有钱金融于2018年1月9日开通服务看看…</p>
                            <i class="next rR"></i>
                        </div>
                    </li>
                    <li>
                        <p class="board-date"><span>2017.10.7</span> <span>23:25:48</span></p>
                        <div class="board-title-box" data-url="boardDetail.html">
                            <!--已读消息在此处添加类名 info-read -->
                            <em class="lL info info-read"></em>
                            <!--data-url跳转地址-->
                            <p class="rR board-title">汇有钱金融于2018年1月9日开通服务…</p>
                            <i class="next rR"></i>
                        </div>
                    </li>
                    <li>
                        <p class="board-date"><span>2017.10.7</span> <span>23:25:48</span></p>
                        <div class="board-title-box" data-url="boardDetail.html">
                            <!--已读消息在此处添加类名 info-read -->
                            <em class="lL info info-read"></em>
                            <!--data-url跳转地址-->
                            <p class="rR board-title">汇有钱金融于2018年1月9日开通服务…</p>
                            <i class="next rR"></i>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

    </div>
</div>

<script src="../js/lib/jquery-1.10.1/jquery-1.10.1.min.js"></script>
<script>
    $(function () {
        $('.board-title-box').click(function () {
            window.location.href = $(this).data('url');
        })
    })
</script>
</body>
</html>