// JavaScript Document
$(function(){
	getMonthList();
	//Initialize Swiper
	 var selindex = $('#swiper1').find('.swiper-slide-active').index();
	  var swiper = new Swiper('#swiper1', {
		slidesPerView: 3,
		spaceBetween: 0,
		observer:true,
		observeParents:true,
		centeredSlides: true,
		pagination: {
		  el: '.swiper-pagination',
		  clickable: true,
		},
		onTap: function(swiper){
			swiper.slideTo(swiper.clickedIndex);
		},
		onSlideChangeEnd: function(swiper){
			var divtext = $('.swiper-slide-active').text();
			var month = divtext.substring(0,4)+'-'+divtext.substring(5,7);
			getUnSettlementList(month);
			//绑定查看明细事件
			bindEventView();
			//调用绑定展开收起函数
			bindEventSlide();
			
		}
	  });

	// 获取待结算月份列表
	function getMonthList() {
		var result = '';
		 $.ajax({
				async:false,
				type:'post',
				url:"getMonthList",
				dataType:'json',
				success:function(json){
					if(json.code == "200"){
						result+='<div class="swiper-wrapper scrolldate">'; 
						var monthList = json.bodys;
						for(var i=0;i<monthList.length;i++){ 
							result+='<div class="swiper-slide">' + monthList[i].substring(0,4)+'年'+monthList[i].substring(5,7) +'月</div>';
						}
						result+='</div><div class="swiper-pagination"></div>';
					}
					$("#swiper1").html(result);
					getUnSettlementList(monthList[0]);
				}
			});
	};	
	
	// 根据月份获取待结算详情
  function getUnSettlementList(month) {
	  $.ajax({
			async:false,
			type:'post',
			url:"getUnSettlementList",
			dataType:'json',
			data: {"settlementMonth":month},  
			success:function(json){
				if(json.code == "200"){
					var bodys = json.bodys;
					var loanBonus = bodys.loanBonus;
					var html = '',
					itempart = $('.itempart');
					html +='<div class="itemlist"><h4> 出借奖金<p data-url="toLoanBonus">';
					html +='查看出借奖金明细</p> <cite class="up"></cite></h4> <ul> <li class="clearfix">';
					html +='<div class="lL">我的月度累计交易金额(万元)</div> <span class="rR">'+loanBonus.monthLoanAmount+'</span></li>';
					html +='<li class="clearfix"><div class="lL">我的月度累计折标金额(万元)</div><span class="rR">'+loanBonus.monthDiscountAmount+'</span></li>';
					html +='<li class="clearfix"><div class="lL">我的下级月度累计交易金额(万元)</div><span class="rR">'+loanBonus.mySubordinateMonthLoanAmount+'</span></li>';
					html +='<li class="clearfix"><div class="lL">我的下级月度累计折标金额(万元)</div><span class="rR">'+loanBonus.mySubordinateMonthDiscountAmount+'</span></li>';
					html +='<li class="clearfix"><div class="lL">出借奖金系数</div><span class="rR">'+loanBonus.loanBonusCoefficient+'</span></li>';
					html +='<li class="clearfix no"><div class="lL">预计获得出借奖金(元)</div><span class="rR">'+loanBonus.expectLoanBonus+'</span></li>';
					html +='</ul></div>';
					if(undefined != bodys.diffBonus){
						var diffBonus = bodys.diffBonus;
						html +='<div class="itemlist"><h4> 差额奖金<p data-url="toDifferenceBonus">查看差额奖金明细</p>';
						html +='<cite></cite> </h4><ul style="display:none"><li class="clearfix">';
						html +='<div class="lL">我的月度累计交易金额(万元)</div><span class="rR">'+diffBonus.monthLoanAmount+'</span></li>';
						html +='<li class="clearfix"><div class="lL">我的月度累计折标金额(万元)</div><span class="rR">'+diffBonus.monthDiscountAmount+'</span></li>';
						html +='<li class="clearfix"><div class="lL">差额奖金系数</div><span class="rR">'+diffBonus.diffBonusCoefficient+'</span></li>';
						html +='<li class="clearfix no"><div class="lL">预计获得差额奖金(元)</div><span class="rR">'+diffBonus.expectDiffBonus+'</span></li>';
						html +='</ul></div>';
					};
					if(undefined != bodys.serviceBonus){
						var serviceBonus = bodys.serviceBonus;
						html +='<div class="itemlist"><h4>服务奖金<p data-url="toServiceBonus">查看服务奖金明细</p>';
						html +='<cite></cite></h4><ul style="display:none"><li class="clearfix">';
						html +='<div class="lL">我的月度累计交易金额(万元)</div><span class="rR">'+serviceBonus.monthLoanAmount+'</span></li>';
						html +='<li class="clearfix"><div class="lL">我的月度累计折标金额(万元)</div><span class="rR">'+serviceBonus.monthDiscountAmount+'</span></li>';
						html +='<li class="clearfix"><div class="lL">我的下级月度累计交易金额(万元)</div><span class="rR">'+serviceBonus.mySubordinateMonthLoanAmount+'</span></li>';
						html +='<li class="clearfix"><div class="lL">我的下级月度累计折标金额(万元)</div><span class="rR">'+serviceBonus.mySubordinateMonthDiscountAmount+'</span></li>';
						html +='<li class="clearfix"><div class="lL">其他下级月度累计交易金额(万元)</div><span class="rR">'+serviceBonus.otherSubordinateMonthLoanAmount+'</span></li>';
						html +='<li class="clearfix"><div class="lL">其他下级月度累计折标金额(万元)</div><span class="rR">'+serviceBonus.otherSubordinateMonthDiscountAmount+'</span></li>';
						html +='<li class="clearfix"><div class="lL">服务奖金系数</div><span class="rR">'+serviceBonus.serviceBonusCoefficient+'</span></li>';
						html +='<li class="clearfix no"><div class="lL">预计获得服务奖金(元)</div><span class="rR">'+serviceBonus.expectServiceBonus+'</span></li>';
						html +='</ul></div>';
					};
				};
				   itempart.html(html);
			}
		});
}
	  
  var swiper2 = new Swiper('#swiper2', {
	slidesPerView: 3,
	spaceBetween: 0,
	centeredSlides: true,
	pagination: {
	  el: '.swiper-pagination',
	  clickable: true,
	},
	onTap: function(swiper2){
		swiper2.slideTo(swiper2.clickedIndex);
	},
	onSlideChangeEnd: function(swiper2){
		var html = '',
		itemfinish = $('.itemfinish');
		var data=[
        {
            "month":"10",
            "jiaoyitotal":"10,000.00",
			"zhebiaototal":"900.00",
			"jianglitotal":"100.00",
			"url":"settle.html"
        },
		{
            "month":"09",
            "jiaoyitotal":"10,000.00",
			"zhebiaototal":"900.00",
			"jianglitotal":"100.00",
			"url":"settle.html"
        },
		{
            "month":"08",
            "jiaoyitotal":"10,000.00",
			"zhebiaototal":"900.00",
			"jianglitotal":"100.00",
			"url":"settle.html"
        },
		{
            "month":"07",
            "jiaoyitotal":"10,000.00",
			"zhebiaototal":"900.00",
			"jianglitotal":"100.00",
			"url":"settle.html"
        }
		
    ];
	    itemfinish.html('');
		itemfinish.html('<div id="loading" class="loading"><img src="../images/loading.gif" style="margin: 0 auto;padding: 15px 0" alt=""></div>');
		loaddata(data);		
	}
  });

  //tab切换
  $('.tabSwitch a').on('click', function(){
    var index = $(this).index(),
		tabcon = $('.tabcon');
	$(this).addClass('active').siblings().removeClass('active');
	tabcon.eq(index).addClass('visi').siblings('.tabcon').removeClass('visi');  
  });
  //列表展开收起事件
  function bindEventSlide(){
	$('.itemlist h4').find('cite').on('click', function(e){
	  e.stopPropagation();
	  e.preventDefault();
	  var that = $(this),
		  contain = that.parents('h4').next(),
		  isHas = that.hasClass('up'); 
	  if(isHas){
		  that.removeClass('up');
		  contain.hide();
	  }else{
		  that.addClass('up');
		  contain.show();
	  }
	});
  }
  //未结算查看明细事件
  function bindEventView(){
  
	$('.itemlist h4').find('p').on('click', function(e){
		e.stopPropagation();
		e.preventDefault();
		
		location.href = $(this).attr("data-url");
	});
  }
  //已结算查看明细事件
  function bindEventView2(){
  
	$('.itemfinish button').on('click', function(e){ console.log('fdaf');
		
		location.href = $(this).attr("data-url");
	});
  }
  function unbindEventView2(){
	$('.itemfinish button').off('click');
	  
  }
  //初始化事件绑定
  bindEventSlide();
  bindEventView();
  
  
//加载数据  
  //已结算年份数据展现
    var data=[
        {
            "month":"10",
            "jiaoyitotal":"10,000.00",
			"zhebiaototal":"900.00",
			"jianglitotal":"100.00",
			"url":"settle.html"
        },
		{
            "month":"09",
            "jiaoyitotal":"10,000.00",
			"zhebiaototal":"900.00",
			"jianglitotal":"100.00",
			"url":"settle.html"
        },
		{
            "month":"08",
            "jiaoyitotal":"10,000.00",
			"zhebiaototal":"900.00",
			"jianglitotal":"100.00",
			"url":"settle.html"
        },
		{
            "month":"07",
            "jiaoyitotal":"10,000.00",
			"zhebiaototal":"900.00",
			"jianglitotal":"100.00",
			"url":"settle.html"
        }
		
    ];
	
function loaddata(data){
    var counter = 0;
    // 每页展示3个
    var num = 3;
    var pageStart = 0,pageEnd = 0;

	$('.scrollWrapNews').css('height', $(window).height() - $('.ui-header').outerHeight()-$('.tabSwitch').outerHeight()-$('#swiper2').outerHeight());
    // dropload
    $('.scrollWrapNews').dropload({
        scrollArea : $('.scrollWrapNews'),
        loadDownFn : function(me){
            var result = '';
            counter++;
            pageEnd = num * counter;
            pageStart = pageEnd - num;

            for(var i=pageStart;i<pageEnd;i++){
                result+=
                    '<div class="itemli"><h4><span>'+ data[i].month +'</span>月</h4><ol><li><span>'+ data[i].jiaoyitotal +'</span><cite>交易总额(万元)</cite></li><li><span>'+ data[i].zhebiaototal +'</span><cite>折标总额(万元)</cite></li><li><span class="orange">'+ data[i].jianglitotal +'</span><cite>奖励总额(元)</cite></li></ol><button class="no" data-url="'+ data[i].url +'"></button></div>';
                if((i + 1) >= data.length){
                    // 锁定
                    me.lock();
                    // 无数据
                    me.noData();
                    break;
                }
            }
            // 为了测试，延迟1秒加载
            setTimeout(function(){
                //$('.scrollWrapNews').append(result);
				if(data.length>0){
				$('.loading').before(result);
				}


                // 每次数据加载完，必须重置
                me.resetload();
				if(data.length<=0){
				  $('.loading').show();	
				}
				
				//移除/绑定事件
				unbindEventView2();
				bindEventView2();

            },1000);
        },
		domDown : {                                                          // 下方DOM
			domClass   : 'dropload-down',
			domRefresh : '<div class="dropload-refresh">下滑加载更多</div>',
			domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
			domNoData  : '<div class="dropload-noData">暂无数据</div>'
		},
        threshold : 42
    });
    $('.dropload-up,.dropload-down').hide();
}
loaddata(data);
///
//重置未结算数据区域高度
$('.itempart').css('height', $(window).height() - $('.ui-header').outerHeight()-$('.tabSwitch').outerHeight()-$('#swiper1').outerHeight());


});