<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>后台角色信息列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		var datagrid;
		$(function(){
			datagrid = $('#datagrid').datagrid({
				url : '${app}/role/roles',
				title : '',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				toolbar:"#toolbar",
				border : false,
				idField : 'role_id',
				singleSelect:true,
				columns : [[{
					field:'ck',
					checkbox:true
				},{
					field : 'role_code',
					title : '角色代码',
					width : 280
				},{
					field : 'role_name',
					title : '角色中文名',
					width : 180
				},{
					field : 'remark',
					title : '角色描述',
					width : 480
				},{
					field : 'create_time',
					title : '创建时间',
					width : 125
				}]]
			});
		});
	
		// 搜索
		function searchFun() {
			datagrid.datagrid('load',serializeObject($("#searchForm")));
			datagrid.datagrid('clearSelections');
			datagrid.datagrid('clearChecked');
		}
		
		// 跳转到添加新角色页面
		function toAddRole(){
			parent.createTab('${app}/role/toAddRole','添加角色');
		}
		
		// 跳转到修改角色信息页面
		function toUpdateRole(){
			var selectRow = datagrid.datagrid('getSelected');
			if(null == selectRow){
				$.messager.show({
					title:'信息提示',
					msg:'请选择一个要修改的角色!',
					timeout:5000,
					showType:'slide'
				});
			}else if(null != selectRow){
				parent.createTab('${app}/role/toUpdateRole/'+selectRow.role_id,'修改角色信息');
			}
		}
	</script>
  </head>
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:36px; overflow:hidden;">
  		<form id="searchForm">
	  		<table  border="0" class="searchForm datagrid-toolbar">
				<tr>
					<td class="tdR" width="12%">角色代码:</td>
					<td width="10%">
						<input id="roleCode" name="roleCode" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					
					<td class="tdR" width="12%">角色中文名:</td>
					<td width="10%">
						<input id="roleName" name="roleName" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td  width="15%">
					</td>
					<td width="20%">
						<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
						<a class="easyui-linkbutton" iconCls="icon-clear" onclick="clearForm(datagrid);">清空</a>
					</td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>  
  	</div>
 	<div id="toolbar" style="display: none;">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="toAddRole();">添加</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="toUpdateRole();">修改</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
	</div>
  </body>
</html>
