<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>后台控件信息列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		var datagrid;
		$(function(){
			datagrid = $('#datagrid').datagrid({
				url : '${app}/control/controls/${pageId}',
				title : '',
				pagination : false,
				fit : true,
				toolbar:"#toolbar",
				border : false,
				idField : 'control_id',
				singleSelect:true,
				columns : [[{
					field:'ck',
					checkbox:true
				},{
					field : 'control_name',
					title : '控件中文名',
					width : 160
				},{
					field : 'control_mark',
					title : '控件唯一标识',
					width : 200
				},{
					field : 'remark',
					title : '控件描述',
					width : 210
				},{
					field : 'create_time',
					title : '创建时间',
					width : 140
				}]]
			});
		});
		// 搜索
		function searchFun() {
			datagrid.datagrid('load',serializeObject($("#searchForm")));
			datagrid.datagrid('clearSelections');
			datagrid.datagrid('clearChecked');
		}
		
		// 跳转到添加新控件页面
		function toAddCtrl(){
			parent.createTab('${app}/control/toAddCtrl/${pageId}','添加控件');
		}
		
		// 跳转到修改控件信息页面
		function toUpdateCtrl(){
			var selectRow = datagrid.datagrid('getSelected');
			if(null == selectRow){
				$.messager.show({
					title:'信息提示',
					msg:'请选择一个要修改的控件!',
					timeout:5000,
					showType:'slide'
				});
			}else if(null != selectRow){
				parent.createTab('${app}/control/toUpdateCtrl/'+selectRow.control_id,'修改控件信息');
			}
		}
		
		// 控件授权
		function toAuthorization(){
			var selectRow = datagrid.datagrid('getSelected');
			if(null == selectRow){
				$.messager.show({
					title:'信息提示',
					msg:'请选择要授权的控件!',
					timeout:5000,
					showType:'slide'
				});
			}else{
				parent.createTab('${app}/control/toAuthorization/' + selectRow.control_id,'控件授权');
			}
		}
		
	</script>
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:36px; overflow:hidden;">
  		<form id="searchForm">
	  		<table  border="0" class="searchForm datagrid-toolbar" width="100%">
				<tr>
					<td class="tdR" width="10%">控件中文名:</td>
					<td width="35%">
						<input id="controlName" name="controlName" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td class="tdR" width="10%">控件唯一标识:</td>
					<td width="25%">
						<input id="controlMark" name="controlMark" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td width="*%">
						<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun();">查询</a>
						<a class="easyui-linkbutton" iconCls="icon-clear" onclick="clearForm(datagrid);">清空</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
 	<div id="toolbar" style="display: none;">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="toAddCtrl();">添加</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="toUpdateCtrl();">修改</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-tip',plain:true" onclick="toAuthorization();">控件授权</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
	</div>
  </body>
</html>
