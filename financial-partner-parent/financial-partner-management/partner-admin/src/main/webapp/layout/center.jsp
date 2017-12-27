<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
	var centerTabsObj;
	$(function(){
		centerTabsObj = $("#centerTabs");
		centerTabsObj.tabs({   
			border:false, 
			fit:true 
		}); 

		setTimeout(function() {
			var src = '${app}/layout/home.jsp';
			centerTabsObj.tabs('add', {
				title : '首页',
				content : '<iframe src="' + src + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
				closable : false,
				tools:[{
					iconCls : 'icon-mini-refresh',
					handler:function(){
						centerTabsObj.tabs('select','首页');
						var tab = centerTabsObj.tabs('getSelected');
						centerTabsObj.tabs('update',{
							tab:tab,
							options:{
								content:'<iframe src="' + src + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
							}
						});
					}
				}]
			});
		}, 0);
	});
</script>
<div id="centerTabs">
</div>

