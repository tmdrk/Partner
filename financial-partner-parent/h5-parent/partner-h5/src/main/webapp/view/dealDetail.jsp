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
    <title>交易详情</title>
    <link rel="stylesheet" href="../css/common/base.css" type="text/css">
</head>
<body>

<div class="ui-page hashf">
    <header class="ui-header ui-linear">
        <a class="lL" href="javascript:;" onClick="javascript:history.back(-1);"><img src="../images/arrowL.png" alt=""></a>
        <h1>交易详情</h1>
    </header>
    <div class="ui-view viewBg" id="scrollPanel">
        <div class="listJJ listJY">

        </div>

        <div class="listJJ listJY hide">

        </div>

        <div id="loading" class="loading">
            <img src="../images/loading.gif" style="margin: 0 auto;padding: 15px 0" alt=""/>
        </div>

    </div><!--ui-view-->
    <footer>
        <div class="navs orderNavs">
            <a href="javascript:void(0)" class="active">本月订单</a>
            <a href="javascript:void(0)">历史订单</a>
        </div>
    </footer>
</div><!--ui-page-->

<script src="../js/lib/jquery-1.10.1/jquery-1.10.1.min.js"></script>
<script src="../js/dropload.js"></script>
<script src="../js/global.js"></script>
<script>
$(function(){
    var listInd = 0  ,timeoutId = null;

    var data = [{
            title:"【普惠出借】24个月",
            status:"预约中",
            uJinE:{
                name:"出借金额(元)",value:"1,000.00"
            },
            uLimit:{
                name:"出借期限(月)",value:"24"
            },
            uHuiKuan:{
                name:"回款日期",value:"-- --"
            },
            uDate:{
                name:"出借时间: ",value:"2017-10-01 23:35:36"
            }
    },{
            title:"【普惠出借】24个月",
            status:"已结清",
            uJinE:{
                name:"出借金额(元)",value:"1,000.00"
            },
            uLimit:{
                name:"出借期限(月)",value:"24"
            },
            uHuiKuan:{
                name:"回款日期",value:"2017-10-01"
            },
            uDate:{
                name:"出借时间: ",value:"2017-10-01 23:35:36"
            }
    },{
            title:"【普惠出借】24个月",
            status:"收益中",
            uJinE:{
                name:"出借金额(元)",value:"1,000.00"
            },
            uLimit:{
                name:"出借期限(月)",value:"24"
            },
            uHuiKuan:{
                name:"回款日期",value:"2017-10-01"
            },
            uDate:{
                name:"出借时间: ",value:"2017-10-01 23:35:36"
            }
    }];

    $(".orderNavs a").click(function(){
        //清空定时器
        clearTimeout(timeoutId);

        listInd = $(this).index();

        $(this).addClass("active").siblings("a").removeClass("active");
        $(".ui-view .listJJ").eq(listInd).removeClass("hide").siblings(".listJJ").addClass("hide");
        $(".ui-view").scrollTop(0);

        //清空
        $(".ui-view .listJJ").eq(listInd).empty();
        $(".loading").show();
        loadData(data);
    });

function domModle(item){
    var classI = '';
    switch(item.status){
        case '预约中': classI = 'orange'; break;
        case '收益中': classI = 'red'; break;
        case '已结清': classI = 'green'; break;
        default: classI = "orange"; break;
    }

    return '<div class="hSegLine"></div><a class="uitem clearfix">'+
                '<h2><label>'+item.title+'</label><span class="btn '+classI+'">'+item.status+'</span></h2>'+
                '<div class="itembd">'+
                    '<p class="imp"><label>出借金额(元)</label>'+item.uJinE.value+'</p>'+
                    '<p class="imp"><label>出借期限(月)</label>'+item.uLimit.value+'</p>'+
                    '<p><label>出借时间</label>'+item.uDate.value+'</p>'+
                    '<p><label>回款日期</label>'+item.uHuiKuan.value+'</p>'+
                '</div>'+
            '</a>';
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