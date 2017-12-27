<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>接口日志列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		
		var datagrid;
		$(function(){
			$('#wd').window('close');
			datagrid = $('#datagrid').datagrid({
				url : '${app}/interfacelog/interfaceLog',
				title : '',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				toolbar:"#toolbar",
				border : false,
				singleSelect : true,
				idField : 'id',
				columns : [[{
					field : 'trade_type',
					title : '接口类型',
					width : 140,
				},{
					field : 'req_data',
					title : '请求报文',
					width : 300,
					formatter : function (value,row){
						var str = "";
						str="<span title='"+ value +"'>"+value+"</span>";
						return str;
					} 
				},{
					field : 'resp_result',
					title : '响应报文',
					width : 300, 
					formatter : function (value,row){
						var str = "";
						str="<span title='"+ value +"'>"+value+"</span>";
						//str= "<a href='javaScript:void(0);' onclick=show('"+row.resp_result+"'); >"+row.resp_result+"</a>"
						return str;
					}
				},{
					field : 'system_docking_name',
					title : '系统对接标识',
					width : 140,
				},{
					field : 'create_time',
					title : '创建时间',
					width : 200,
				}]],
				onDblClickCell: function(index,field,value){
					if(field == "req_data" || field =="resp_result"){
						$("#message").html(value);
						$('#wd').window('open');
					}
				}
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
			$("#tradeType").combobox("setValue",""); 
			$("#systemDocking").combobox("setValue","");
			clearForm(datagrid);
		}
		
	</script>
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:64px; overflow:hidden;">
  		<form id="searchForm">
	  		<table border="0" class="searchForm datagrid-toolbar" width="100%">
	  			<tr>
					<td class="tdR" width="8%">请求报文:</td>
					<td width="20%">
						<input id="reqData" name="reqData" maxlength="30" class='easyui-textbox' style="width: 128px;height: 24px;"/>
					</td>
			  			<td class="tdR" width="8%">接口类型:</td>
						<td width="20%">
							<select id ="tradeType" name ="tradeType" style="width:128px;" editable="false" data-options="panelHeight:112" class="easyui-combobox">
								<option value="" selected="selected">全部</option>
								<!-- <option value="众安-出单接口">众安-出单接口</option>
								<option value="众安-出单验证接口">众安-出单验证接口</option> -->
							</select>
							<!-- <input id="tradeType" name="tradeType" maxlength="30" class='easyui-textbox' style="width: 128px;height: 24px;"/> -->
						</td>
					<td width="8%" class="tdR">记录时间:</td>
					<td width="24%">
						<input id="min_interface_log_time" name="min_interface_log_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 105px;height: 24px;" data-options="required:false" placeholder ="--起始时间--"/>
						至
						<input id="max_interface_log_time" name="max_interface_log_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 105px;height: 24px;" data-options="required:false" placeholder ="--终止时间--"/>
					</td>
				</tr>
				<tr>
					<td class="tdR" width="8%">响应报文:</td>
					<td width="20%">
						<input id="respResult" name="respResult" maxlength="30" class='easyui-textbox' style="width: 128px;height: 24px;"/>
					</td>
					<td class="tdR" width="8%">系统对接标识:</td>
					<td width="20%">
						<select id ="systemDocking" name ="systemDocking" style="width:128px;" editable="false" data-options="panelHeight:112" class="easyui-combobox">
							<option value="" selected="selected">全部</option>
							<c:forEach var="obj" items="${CodeMap['SYSTEM_DOCKING']}">
								<option value="${obj.key}">${obj.value}</option>
							</c:forEach>
						</select>
					</td>
					<td class="tdR" width="8%">
						<td>
						<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>&nbsp;
						<a class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun(datagrid);">清空</a>
						</td>
					</td>
				</tr>
			</table>
		</form>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
  	<div id="wd"  class="easyui-window" data-options="title:'传输数据',collapsible:false,minimizable:false,maximizable:true" style="width:400px;height:150px;padding:4px;z-index:9999;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',border:false" style="padding:10px;background:#fff;border:1px solid #ccc;">
 				<div id="message">
 				</div>
				<br/><br/>
			</div>
		</div>
	</div>
  </body>
</html>
