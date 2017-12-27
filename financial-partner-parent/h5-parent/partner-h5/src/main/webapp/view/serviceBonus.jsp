<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="viewport" content="width=750,target-densitydpi=320, user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta content="telephone=no,email=no" name="format-detection"/>
    <meta name="App-Config" content="fullscreen=yes,useHistoryState=yes,transition=yes"/>
    <title>服务奖金明细</title>
    <link rel="stylesheet" href="../css/common/base.css" type="text/css">
</head>
<body>

<div class="ui-page hasTopNavs">
    <header class="ui-header ui-linear">
        <a class="lL" href="javascript:;" onClick="javascript:history.back(-1);"><img src="../images/arrowL.png" alt=""></a>
        <h1>服务奖金明细</h1>

         <div class="topNavs">
            <ul class="navs navs1">
                <li class="active">
                    <a href="javascript:void(0)"><span class="num">2</span>我的<b></b></a>
                </li>
                <li>
                    <a href="javascript:void(0)"><span class="num">2</span>我的下级<b></b></a>
                </li>
                <li>
                    <a href="javascript:void(0)"><span class="num">4</span>其他下级<b></b></a>
                </li>
            </ul>
        </div>
    </header>
    <div class="ui-view viewBg" id="scrollPanel">
        <!-- 我的 -->
        <div class="listJJ">

        </div>

        <!-- 我的下级 -->
        <div class="listJJ hide">

        </div>

        <!-- 其他下级 -->
        <div class="listJJ hide">

        </div>

        <div id="loading" class="loading">
            <img src="../images/loading.gif" style="margin: 0 auto;padding: 15px 0" alt=""/>
        </div>
    </div><!--ui-view-->
</div><!--ui-page-->

<script src="../js/lib/jquery-1.10.1/jquery-1.10.1.min.js"></script>
<script src="../js/dropload.js"></script>
<script src="../js/global.js"></script>
<script>
$(function(){
    var listInd = 0  ,timeoutId = null;
     var data = [{
        "bianhao":"201712273804099X",
        "list":{
            chuJieJinE:{
                name:"出借金额(元)", value:"10,000.00"
            },
            chuJieQiXian:{
                name:"出借期限(月)",value:"24"
            },
            zheBiaoXiShu:{
                name:"折标系数",value:"1"
            },
            zheBiaoJinE:{
                name:"折标金额(元)",value:"140"
            },
            chaEJiangJinXiShu:{
                name:"服务奖金系数",value:"1.5%"
            },
            chaEJiangJin:{
                name:"服务奖金(元)",value:"30.00"
            },
            chuJieXingMing:{
                name:"出借人姓名",value:"张三"
            },
            chuJieJiaSe:{
                name:"出借人角色",value:"合伙人"
            },
            date:{
                name:"出借时间",value:"2017-10-01 23:23:23"
            }

        }
    },{
        "bianhao":"201712273804099X",
        "list":{
            chuJieJinE:{
                name:"出借金额(元)", value:"10,000.00"
            },
            chuJieQiXian:{
                name:"出借期限(月)",value:"24"
            },
            zheBiaoXiShu:{
                name:"折标系数",value:"1"
            },
            zheBiaoJinE:{
                name:"折标金额(元)",value:"140"
            },
            chaEJiangJinXiShu:{
                name:"服务奖金系数",value:"1.5%"
            },
            chaEJiangJin:{
                name:"服务奖金(元)",value:"30.00"
            },
            chuJieXingMing:{
                name:"出借人姓名",value:"张三"
            },
            chuJieJiaSe:{
                name:"出借人角色",value:"合伙人"
            },
            date:{
                name:"出借时间",value:"2017-10-01 23:23:23"
            }

        }
    },{
        "bianhao":"201712273804099X",
        "list":{
            chuJieJinE:{
                name:"出借金额(元)", value:"10,000.00"
            },
            chuJieQiXian:{
                name:"出借期限(月)",value:"24"
            },
            zheBiaoXiShu:{
                name:"折标系数",value:"1"
            },
            zheBiaoJinE:{
                name:"折标金额(元)",value:"140"
            },
            chaEJiangJinXiShu:{
                name:"服务奖金系数",value:"1.5%"
            },
            chaEJiangJin:{
                name:"服务奖金(元)",value:"30.00"
            },
            chuJieXingMing:{
                name:"出借人姓名",value:"张三"
            },
            chuJieJiaSe:{
                name:"出借人角色",value:"合伙人"
            },
            date:{
                name:"出借时间",value:"2017-10-01 23:23:23"
            }

        }
    }];

    $(".navs li").click(function(){
        //清空定时器
        clearTimeout(timeoutId);


        listInd = $(this).index();

        $(this).addClass("active").siblings("li").removeClass("active");
        $(".ui-view .listJJ").eq(listInd).removeClass("hide").siblings(".listJJ").addClass("hide");
        $(".ui-view").scrollTop(0);

        //清空
        $(".ui-view .listJJ").eq(listInd).empty();
        $(".loading").show();
        loadData(data);
    });

    function domModle(item){
        return '<div class="hSegLine"></div><div class="item clearfix">'
                    +'<h2><label>合同编号</label>'+item.bianhao+'</h2>'
                    +'<p><label>出借金额(元)</label>'+item.list.chuJieJinE.value+'</p>'
                    +'<p><label>出借期限(月)</label>'+item.list.chuJieQiXian.value+'</p>'
                    +'<p><label>折标系数</label>'+item.list.zheBiaoXiShu.value+'</p>'
                    +'<p><label>折标金额(元)</label>'+item.list.zheBiaoJinE.value+'</p>'
                    +'<p><label>服务奖金系数</label>'+item.list.chaEJiangJinXiShu.value+'</p>'
                    +'<p><label>服务奖金(元)</label>'+item.list.chaEJiangJin.value+'</p>'
                    +'<p><label>出借人姓名</label>'+item.list.chuJieXingMing.value+'</p>'
                    +'<p><label>出借人角色</label>'+item.list.chuJieJiaSe.value+'</p>'
                    +'<p class="w"><label>出借时间</label>'+item.list.date.value+'</p>'
                +'</div>';
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