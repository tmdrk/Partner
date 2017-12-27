/*创建tab面板*/
function createTab(src,title){
	if(!centerTabsObj.tabs('exists',title)){
		centerTabsObj.tabs('add', {
			title : title,
			content : '<iframe src="' + src + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			closable : true
		});
		tabClose();
	}else{
		centerTabsObj.tabs('select',title);
		var tab = centerTabsObj.tabs('getSelected');  // get selected panel
		centerTabsObj.tabs('update',{
			tab: tab,
			options: {
				content : '<iframe src="' + src + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
			}
		});
	}
}

//关闭tab面板
function closeTab(title){
	centerTabsObj.tabs('close',title);
}

//刷新父列表页面
function refreshTab(src,title){
	if(!centerTabsObj.tabs('exists',title)){
		centerTabsObj.tabs('add', {
			title : title,
			content : '<iframe src="' + src + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			closable : true
		});
		tabClose();
	}else{
		centerTabsObj.tabs('select',title);
		var tab = centerTabsObj.tabs('getSelected');  // get selected panel
		centerTabsObj.tabs('update',{
			tab: tab,
			options: {
				content : '<iframe src="' + src + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
			}
		});
	}
}

function tabClose() {
	/*双击关闭TAB选项卡*/
	$(".tabs-inner:gt(0)").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#centerTabs').tabs('close',subtitle);
	});
	
	/*为选项卡绑定右键*/
	$(".tabs-inner:gt(0)").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#centerTabs').tabs('select',subtitle);
		return false;
	});
}

//创建Iframe时的src
function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
	return s;
}

//绑定右键菜单事件
function tabCloseEven() {
	//刷新
	$('#mm-tabUpdate').click(function(){
		var currTab = $('#centerTabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		//判断是否包含参数
		if(url.indexOf("?") != -1){
			url += "&refreshTag=1"; //refreshTag代表是不是右键刷新标志
		}else{
			url += "?refreshTag=1";
		}
		if(url != undefined && currTab.panel('options').title != const_index) {
			$('#centerTabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	});
	
	//关闭当前
	$('#mm-tabClose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#centerTabs').tabs('close',currtab_title);
	});
	
	//全部关闭
	$('#mm-tabCloseAll').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t != const_index) {
				$('#centerTabs').tabs('close',t);
			}
		});
	});
	
	//关闭除当前之外的TAB
	$('#mm-tabCloseOther').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		var nextall = $('.tabs-selected').nextAll();		
		if(prevall.length>0){
			prevall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != const_index) {
					$('#centerTabs').tabs('close',t);
				}
			});
		}
		if(nextall.length>0) {
			nextall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != const_index) {
					$('#centerTabs').tabs('close',t);
				}
			});
		}
		return false;
	});

	//关闭当前左侧的TAB
	$('#mm-tabLeftCloseAll').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			if(t != const_index) {
				$('#centerTabs').tabs('close',t);
			}
		});
		return false;
	});
	
	//关闭当前右侧的TAB
	$('#mm-tabRightCloseAll').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#centerTabs').tabs('close',t);
		});
		return false;
	});
}