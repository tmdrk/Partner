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
    <title>用户</title>
    <link rel="stylesheet" href="../css/common/base.css" type="text/css">
</head>
<body>

<div class="ui-page hasTopNavsFooter">
    <div class="ui-header ui-linear">
        <div class="topNavs">
            <div class="searchPanel clearfix">
                <div class="flexbox searchWrap">
                    <a href="#" class="lL">搜索</a>
                    <input type="text" class="flexbox-f1 searchInp" placeholder="可通过手机号或姓名查找"/>
                </div>
                <a class="rR" href="invite.jsp"></a>
            </div>
            <ul class="navs navs1">
                <li class="active">
                    <a href="javascript:void(0)"><span class="num">20</span>我的客户<b></b></a>
                </li>
                <li>
                    <a href="javascript:void(0)"><span class="num">38</span>我的下级<b></b></a>
                    <ul class="navs navs2" style="background: #fff;">
                        <li class="active"><a href="javascript:void(0)">合伙人<span class="num">10</span></a></li>
                        <li><a href="javascript:void(0)">合伙人客户<span class="num">10</span></a></li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:void(0)"><span class="num">17</span>其他下级<b></b></a>
                    <ul class="navs navs2" style="background: #fff;">
                        <li class="active"><a href="javascript:void(0)">合伙人<span class="num">10</span></a></li>
                        <li><a href="javascript:void(0)">合伙人客户<span class="num">10</span></a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div class="ui-view viewBg" id="scrollPanel">
        <div class="listJJ">

        </div>

        <div class="listJJ hide">

        </div>

        <div class="listJJ hide">

        </div>

        <div id="loading" class="loading">
            <img src="../images/loading.gif" style="margin: 0 auto;padding: 15px 0" alt=""/>
        </div>
    </div><!--ui-view-->
    <footer>
        <div class="navs">
            <a href="${basePath}/index"><i class="Ihome"></i><span>首页</span></a>
            <a href="javascript:;" class="active"><i class="Iuser"></i><span>用户</span></a>
            <a href="${basePath}/reward/toReward"><i class="Iaward"></i><span>奖励</span></a>
            <a href="${basePath}/user/userCenter"><i class="Imine"></i><span>我的</span></a>
        </div>
    </footer><!--footer-->
</div><!--ui-page-->

<script src="../js/lib/jquery-1.10.1/jquery-1.10.1.min.js"></script>
<script src="../js/dropload.js"></script>
<script src="../js/global.js"></script>
<script>
$(function(){
    var listInd = 0  ,timeoutId = null;
    var data = [{
            uName:{
                name:"姓名", value:"张珊"
            },
            uJiaoYi:{
                name:"交易笔数(笔)",value:"2"
            },
            uTel:{
                name:"手机号",value:"13872837392"
            },
            uTotal:{
                name:"交易总额(万元)",value:"10,000.00"
            },
            uDate:{
                name:"邀请时间",value:"2017-10-01 23:23:23"
            }
    },{
            uName:{
                name:"姓名", value:"张珊"
            },
            uJiaoYi:{
                name:"交易笔数(笔)",value:"2"
            },
            uTel:{
                name:"手机号",value:"13872837392"
            },
            uTotal:{
                name:"交易总额(万元)",value:"10,000.00"
            },
            uDate:{
                name:"邀请时间",value:"2017-10-01 23:23:23"
            }
    },{
            uName:{
                name:"姓名", value:"张珊"
            },
            uJiaoYi:{
                name:"交易笔数(笔)",value:"2"
            },
            uTel:{
                name:"手机号",value:"13872837392"
            },
            uTotal:{
                name:"交易总额(万元)",value:"10,000.00"
            },
            uDate:{
                name:"邀请时间",value:"2017-10-01 23:23:23"
            }
    },{
            uName:{
                name:"姓名", value:"张珊"
            },
            uJiaoYi:{
                name:"交易笔数(笔)",value:"2"
            },
            uTel:{
                name:"手机号",value:"13872837392"
            },
            uTotal:{
                name:"交易总额(万元)",value:"10,000.00"
            },
            uDate:{
                name:"邀请时间",value:"2017-10-01 23:23:23"
            }
    },{
            uName:{
                name:"姓名", value:"张珊"
            },
            uJiaoYi:{
                name:"交易笔数(笔)",value:"2"
            },
            uTel:{
                name:"手机号",value:"13872837392"
            },
            uTotal:{
                name:"交易总额(万元)",value:"10,000.00"
            },
            uDate:{
                name:"邀请时间",value:"2017-10-01 23:23:23"
            }
    },{
            uName:{
                name:"姓名", value:"张珊"
            },
            uJiaoYi:{
                name:"交易笔数(笔)",value:"2"
            },
            uTel:{
                name:"手机号",value:"13872837392"
            },
            uTotal:{
                name:"交易总额(万元)",value:"10,000.00"
            },
            uDate:{
                name:"邀请时间",value:"2017-10-01 23:23:23"
            }
    }];

var headerH = $(".ui-header").height();
function viewTopChange(){
    var curActiveDom = $(".navs1 li.active");
    if(curActiveDom.find(".navs2").length>0){
            $(".ui-view").css("top",headerH+110);
    }else {
        $(".ui-view").css("top",headerH);
    }
}
viewTopChange();

    $(".navs1 li").click(function(){
        //清空定时器
        clearTimeout(timeoutId);

        listInd = $(this).index();

        $(this).addClass("active").siblings("li").removeClass("active");
        $(".ui-view .listJJ").eq(listInd).removeClass("hide").siblings(".listJJ").addClass("hide");
        $(".ui-view").scrollTop(0);

        viewTopChange();

        //清空
        $(".ui-view .listJJ").eq(listInd).empty();
        $(".loading").show();
        loadData(data);
    });

    function domModle(item){
        return '<div class="hSegLine"></div><a class="item uitem clearfix" href="dealDetail.jsp">'+
                    '<p><label>姓名</label>'+item.uName.value+'</p>'+
                    '<p><label>交易笔数(笔)</label>'+item.uJiaoYi.value+'</p>'+
                    '<p><label>手机号</label>'+item.uTel.value+'</p>'+
                    '<p><label>交易总额(万元)</label>'+item.uTotal.value+'</p>'+
                    '<p class="w"><label>邀请时间</label>'+item.uDate.value+'</p>'+
                '</a>'
    }
    function loadData(data) {
        if(data.length>0){
            var counter = 0, num = 3, // 每页展示3个
                pageStart = 0, pageEnd = 0;

            // dropload
            $('#scrollPanel').dropload({
                scrollArea : $("#scrollPanel"),
                loadDownFn : function(me){
                    var result = '';
                    counter++;
                    pageEnd = num * counter;
                    pageStart = pageEnd - num;

                    for(var i=pageStart;i<pageEnd;i++){

                        result+= domModle(data[i]);

                        if((i + 1) >= data.length){
                            // 锁定
                            me.lock();
                            // 无数据
                            me.noData();
                            break;
                        }
                    }
                    // 为了测试，延迟1秒加载
                    timeoutId = setTimeout(function(){
                        $('.listJJ').eq(listInd).append(result);

                        // 每次数据加载完，必须重置
                        me.resetload();
                    },1000);

                },
                threshold : 50
            });
        }else {
            $(".loading").hide();
        }

        $('.dropload-up,.dropload-down').hide();
    }
    loadData(data);
});
</script>
</body>
</html>