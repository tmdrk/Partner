<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查看下级用户列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		var datagrid;
		$(function(){
			datagrid = $('#datagrid').datagrid({
				url : '${app}/loginuser/getLoginPartnerUserList',
				title : '',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				toolbar:"#toolbar",
				border : false,
				idField : 'user_id',
				queryParams: {
				    "userId": '${loginUser.user_id}',
				    "user_type":'${loginUser.user_type}'
				},
				columns : [[{
					field:'cd',
					checkbox:true
				},{
					field : 'rowNumbers',
					title : '序号',
					width : 40,
					formatter: function(val,rec,index){  
	                   var op = $('#datagrid').datagrid('options');  
	                   return op.pageSize * (op.pageNumber - 1) + (index + 1);  
	                }  
				},{
					field : 'user_id',
					title : '用户id',
					width : 100,
					hidden:true
				},{
					field : 'real_name',
					title : '下级用户姓名',
					width : 120
				},{
					field : 'username',
					title : '下级用户手机号',
					width : 120
				},{
					field : 'user_role_name',
					title : '下级用户角色',
					width : 120
				},{
					field : 'invite_type',
					title : '邀请方式',
					width : 120,
					formatter : function(value,row,index){
						if(value ==1){
							return "本人邀请";
						}else{
							return "下级邀请";
						}
					}
				},{
					field : 'parent_real_name',
					title : '合伙人姓名',
					width : 120
				},{
					field : 'parent_username',
					title : '合伙人手机号',
					width : 120
				},{
					field : 'invitation_code',
					title : '合伙人推荐码',
					width : 120
				},{
					field : 'partner_time',
					title : '下级用户注册合伙人平台时间',
					width : 180
				},{
					field : 'user_status_name',
					title : '下级用户状态',
					width : 120
				}]]
			});
		});
		//搜索
		function searchFun() {
			datagrid.datagrid('load',serializeObject($("#searchForm")));
			datagrid.datagrid('clearSelections');
			datagrid.datagrid('clearChecked');
		}
		
		//清空
		function clearFromFun(datagrid){
			clearForm(datagrid);
			$("#userStatus").combobox("setValue","");
			$("#searchForm").submit();
		}
	
		//查看邀请用户数据导出操作
		function doExport(){
			$("#searchForm").attr("action", "${app}/loginuser/exportLoginParterUser");
			$("#searchForm").attr("method", "POST");
			$("#searchForm").submit();
		}
	</script>
  </head>
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:64px; overflow:hidden;">
  		<form id="searchForm">
	  		<table border="0" class="searchForm datagrid-toolbar" width="100%">
	  			<tr>
					<td class="tdR" width="9%">下级用户姓名:</td>
					<td width="17%">
						<input type="hidden" id="user_id" name="user_id" value="${loginUser.user_id}"/>
						<input type="hidden" id="user_type" name="user_type" value="${loginUser.user_type}"/>
						<input id="realName" name="realName" maxlength="30" class='easyui-textbox' style="width: 160px;height: 24px;"/>
					</td>
					<td class="tdR" width="14%">下级用户手机号:</td>
					<td width="17%">
						<input id="username" name="username" maxlength="30" class='easyui-textbox' style="width: 160px;height: 24px;"/>
					</td>
					<td class="tdR" width="7%">下级用户角色:</td>
					<td width="7%">
						<select  id="userRole" name="userRole" class="easyui-combobox" style="width: 160px;height: 24px;">
							<option value="" selected="selected">全部</option>
							<c:forEach var="obj" items="${CodeMap['USER_ROLE']}">
								<option value="${obj.key}">${obj.value}</option>
							</c:forEach>
						</select>
					</td>
					<td class="tdR" width="8%">下级用户状态:</td>
					<td><select  id="userStatus" name="userStatus" class="easyui-combobox" style="width: 160px;height: 24px;">
							<option value=""selected="selected">全部</option>
							<c:forEach var="obj" items="${CodeMap['USER_STATUS']}">
							<option value="${obj.key}">${obj.value}</option>
							</c:forEach>
						</select></td>
				</tr>
	  			<tr>
					<td class="tdR" width="9%">邀请方式:</td>
					<td><select  id="inviteType" name="inviteType" class="easyui-combobox" style="width: 160px;height: 24px;">
							<option value=""selected="selected">全部</option>
							<c:forEach var="obj" items="${CodeMap['INVITE_TYPE']}">
							<option value="${obj.key}">${obj.value}</option>
							</c:forEach>
						</select></td>
	  				<td class="tdR">下级用户注册合伙人平台时间:</td>
					<td colspan="3">
						<input id="min_change_partner_time" name="min_change_partner_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 160px;height: 24px;" data-options="required:false" placeholder ="--起始时间--"/>
						~
						<input id="max_change_partner_time" name="max_change_partner_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 160px;height: 24px;" data-options="required:false" placeholder ="--终止时间--"/>
					</td>
					<td colspan="2">
						<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>&nbsp;&nbsp;
						<a class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun(datagrid);">清空</a>&nbsp;&nbsp;
						<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="doExport();">导出</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="toolbar" style="display: none;height: 20px;overflow: hidden;padding-top: 5px;padding-left: 15px;">
<%-- 		<span id="user_id">  ${loginUser.user_id}</span> --%>
		<span> 合伙人姓名:  ${loginUser.real_name}</span>
		<span>  &nbsp;&nbsp; 合伙人手机号:  ${loginUser.username}</span>&nbsp;&nbsp;
		<%-- <span>  &nbsp;&nbsp; 邀请人角色:  ${loginUser.user_role_name}</span>&nbsp;&nbsp; --%>
		<span>  &nbsp;&nbsp;合伙人推荐码:  ${loginUser.partner_recommend_code} / ${loginUser.customer_recommend_code}</span>&nbsp;&nbsp;
		<span>  &nbsp;&nbsp; 所属渠道:  ${loginUser.organization_channel_id_name}</span>&nbsp;&nbsp;
		<span> 	&nbsp;&nbsp;所属区域:  ${loginUser.organization_area_id_name}</span>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
  </body>
</html>
