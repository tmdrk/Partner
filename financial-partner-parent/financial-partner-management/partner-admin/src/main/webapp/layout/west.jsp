<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/header.jsp"%>
  <body style="margin-left: 0px;margin-top: 3px;">	
  	<style type="text/css">
  		  .l-link-over{ background:#FFEEAC; border:1px;}
  	</style>
  	<link href="${app}/js/JQuery-zTree-v3.5.15/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
	<script src="${app}/js/JQuery-zTree-v3.5.15/js/jquery.ztree.core-3.5.min.js" charset="UTF-8" type="text/javascript"></script>
	<script type="text/javascript" charset="utf-8">
 		var treeObj;
		$(document).ready(function(){
			var setting = {
					data: {
						key:{name: 'name_zh'},
						simpleData: {
							enable: true,
							idKey: 'menu_id',
							pIdKey: 'parent_id'
						}
					},
					view: {
						showTitle: false,
						expandSpeed: 0
					},
					callback: {onClick:clickNode}
			};
	
			var zNodes;
			$.ajax({
				type: "POST",
				async:false,
				url: "${app}/menu/loadMenu",
				dataType: "json",
				cache:false,
				success: function callback(data){
					zNodes = data;
				},
				error: function callback(data){
					$.messager.alert("提示信息", "出错了！！！", "error");
				}
			});
			
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			treeObj.expandAll(true);

			$("span").hover(function (){
            	$(this).addClass("l-link-over");
            }, function (){
            	$(this).removeClass("l-link-over");
            });
		});
		
		function clickNode(event, treeId, treeNode) {
			if(!treeNode.isParent){
				var src = "${app}" + treeNode.menu_url;
				parent.createTab(src,treeNode.name_zh);
			}
		}
	</script>
	<ul id="treeDemo" class="ztree" fit="true"></ul>
  </body>
