<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
	request.setAttribute("app",request.getContextPath());
%>
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
    <title>奖励详情</title>
    <link rel="stylesheet" href="${app}/css/common/base.css" type="text/css">
    <link rel="stylesheet" href="${app}/js/lib/swiper/swiper.min.css">
    <script src="${app}/js/lib/jquery-1.10.1/jquery-1.10.1.min.js"></script>
	<script src="${app}/js/global.js"></script>
	<script src="${app}/js/lib/swiper/swiper.min.js"></script>
	<script src="${app}/js/dropload.js?v=1.1"></script>
	<script src="${app}/js/jiangli.js?v=1.1"></script>

  <!-- Demo styles -->
  <style>
    
    .swiper-container {
      width: 100%;
      height: 100%;
    }
    .swiper-slide {
      text-align: center;
      font-size: 18px;
      background: #fff;

      /* Center slide text vertically */
      display: -webkit-box;
      display: -ms-flexbox;
      display: -webkit-flex;
      display: flex;
      -webkit-box-pack: center;
      -ms-flex-pack: center;
      -webkit-justify-content: center;
      justify-content: center;
      -webkit-box-align: center;
      -ms-flex-align: center;
      -webkit-align-items: center;
      align-items: center;
    }
  </style>
  
<script type="text/javascript">
	//准备函数
/* 	$(function(){
		getMonthList();
	}); */
	
	
</script>

</head>
<body>

<div class="ui-page hashd">
    <header class="ui-header bg">
        <a class="lL" href="javascript:;" onClick="javascript:history.back(-1);"><img src="${app}/images/arrowL.png" alt=""></a>
        <h1>奖励详情</h1>
        <a href="rule.jsp" class="rR rule">奖励规则</a>
    </header>
    <div class="ui-view">
        <!--tab-->
        <div class="tabSwitch">
            <a href="javascript:;" class="no active">待结算</a><a href="javascript:;" class="no">已结算</a>
        </div>
        <div class="tabarea">
          <!--待结算-->
          <div class="tabcon visi">
            <!-- Swiper -->
            <div class="swiper-container" id="swiper1">
              <div class="swiper-wrapper scrolldate">
                <div class="swiper-slide">2017年10月</div>
                <div class="swiper-slide">2017年11月</div>
                <div class="swiper-slide">2017年12月</div>
                <div class="swiper-slide">2018年01月</div>
              </div>
              <!-- Add Pagination -->
              <div class="swiper-pagination"></div>
            </div>
            
            
            <div class="itempart">
                <div class="itemlist">
                </div>
    
                <div class="itemlist">
                </div>
    
                <div class="itemlist">
                </div>
    
            </div>
          
          </div>
          <!--已结算-->
          <div class="tabcon">
            <!-- Swiper -->
            <div class="swiper-container" id="swiper2">
              <div class="swiper-wrapper years">
                <div class="swiper-slide">2017年</div>
                <div class="swiper-slide">2018年</div>
                <div class="swiper-slide">2019年</div>
              </div>
              <!-- Add Pagination -->
              <div class="swiper-pagination"></div>
            </div>
             <div class="itemfinish scrollWrapNews">
                 <!--<div class="itemli">
                   <h4><span>12</span>月</h4>
                   <ol>
                     <li>
                       <span>90.00</span>
                       <cite>交易总额(万元)</cite>
                     </li>
                     <li>
                       <span>98.00</span>
                       <cite>折标总额(万元)</cite>
                     </li>
                     <li>
                       <span>90.00</span>
                       <cite>奖励总额(元)</cite>
                     </li>
                   </ol>
                   <button class="no"></button>
                   
                 </div>
                 <div class="itemli">
                   <h4><span>10</span>月</h4>
                   <ol>
                     <li>
                       <span>90.00</span>
                       <cite>交易总额(万元)</cite>
                     </li>
                     <li>
                       <span>98.00</span>
                       <cite>折标总额(万元)</cite>
                     </li>
                     <li>
                       <span>90.00</span>
                       <cite>奖励总额(元)</cite>
                     </li>
                   </ol>
                   <button class="no"></button>
                   
                 </div>
                 <div class="itemli">
                   <h4><span>10</span>月</h4>
                   <ol>
                     <li>
                       <span>90.00</span>
                       <cite>交易总额(万元)</cite>
                     </li>
                     <li>
                       <span>98.00</span>
                       <cite>折标总额(万元)</cite>
                     </li>
                     <li>
                       <span>90.00</span>
                       <cite>奖励总额(元)</cite>
                     </li>
                   </ol>
                   <button class="no"></button>
                   
                 </div>-->
                  <div id="loading" class="loading">
                    <img src="${app}/images/loading.gif" style="margin: 0 auto;padding: 15px 0" alt="">
                  </div>
             </div>
          </div>
        </div>
        
    </div><!--ui-view-->
</div><!--ui-page-->
</body>
</html>