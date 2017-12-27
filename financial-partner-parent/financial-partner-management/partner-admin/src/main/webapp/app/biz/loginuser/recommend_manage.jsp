<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>推荐码管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<script type="text/javascript">
			var datagrid;
			$(function(){
				datagrid = $('#datagrid').datagrid({
					url : '${app}/recommendManage/getRommendHistoryList',
					title : '',
					pagination : true,
					pageSize : <%=Constants.PAGE_SIZE%>,
					pageList : [10,20,30,40,50],
					fit:true,
					toolbar:"#toolbar",
					border:false,
					idField : 'user_id',
					columns : [[{
						field:'rowNumbers',  
					    title: '序号',  
					    align: 'center',  
					    width: 50, 
					    formatter: function(val,rec,index){  
							var op = $('#datagrid').datagrid('options');  
						    return op.pageSize * (op.pageNumber - 1) + (index + 1);  
						}
					},{
						field : 'user_id',
						title : '用户id',
						width : 80,
						hidden:true
					},{
						field : 'real_name',
						title : '用户姓名',
						width : 150
					},{
						field : 'user_role_name',
						title : '用户角色',
						width : 150
					},{
						field : 'username',
						title : '用户手机号',
						width : 150
					},{
						field : 'partner_recommend_code',
						title : '合伙人推荐码',
						width : 150
					},{
						field : 'customer_recommend_code',
						title : '客户推荐码',
						width : 150 
					},{
						field : 'create_time',
						title : '推荐码生成时间',
						width : 150
					}]],
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
			}
			
			//导出所有
			function doExportAll(){
				$("#searchForm").attr("action", "${app}/recommendManage/exportAllRommend");
				$("#searchForm").attr("method", "POST");
				$("#searchForm").submit();
			}
			//导出选中
			function doExportSelect(){
				var rowData = $('#datagrid').datagrid('getSelections');
				var selectIds = "";
				if(rowData.length > 0){
					for(i=0;i<rowData.length;i++){
						selectIds += rowData[i].user_id + "-";
					}
					window.location = "${app}/recommendManage/exportSelectRommend?selectIds="+selectIds;
				}else{
					$.messager.show({
						title:'信息提示',
						msg:'请选择要导出的记录!',
						timeout:5000,
						showType:'slide'
					});
				}
			}
		</script>
	</head>
	<body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">   
	    <div region="north" style="height:67px; overflow:hidden;">
  			<form id="searchForm">
	  			<table border="0" class="searchForm datagrid-toolbar" width="100%">
			  		<tr>
						<td class="tdR">用户姓名:</td>
						<td>
							<input id="realName" name="realName" maxlength="25" class='easyui-textbox' style="width: 120px;height:24px;"/>
						</td>
			  			<td class="tdR">用户手机号:</td>
						<td>
							<input id="mobile" name="mobile" maxlength="25" class='easyui-textbox' style="width: 120px;height: 24px;"/>
						</td>
						<td class="tdR">合伙人推荐码:</td>
						<td>
							<input id="partnerRecommendCode" name="partnerRecommendCode" maxlength="25" class='easyui-textbox' style="width: 120px;height: 24px;"/>
						</td>
					</tr>
					<tr>
						<td class="tdR">客户推荐码:</td>
						<td>
							<input id="customerRecommendCode" name="customerRecommendCode" maxlength="25" class='easyui-textbox' style="width: 120px;height: 24px;"/>
						</td>
						<td class="tdR">推荐码生成时间:</td>
						<td colspan="2">
							<input id="minCreateRecommendTime" name="minCreateRecommendTime" editable='false' maxlength="30" class='easyui-datebox' style="width: 125px;height: 24px;" data-options="required:false" placeholder ="--起始时间--"/>
							-
							<input id="maxCreateRecommendTime" name="maxCreateRecommendTime" editable='false' maxlength="30" class='easyui-datebox' style="width: 125px;height: 24px;" data-options="required:false" placeholder ="--终止时间--"/>
						</td>
		  				<td  class="tdL" style="padding-left:0px;">
							<a href="javascript:void(0);"  class="easyui-linkbutton" style="min-width:46px;" data-options="iconCls:'icon-search'" onclick="searchFun()">搜索</a>
							<a href="javascript:void(0);"  class="easyui-linkbutton" style="min-width:46px;" data-options="iconCls:'icon-cancel'" onclick="clearFromFun(datagrid);">清空</a>
							<a href="javascript:void(0);"  class="easyui-linkbutton" style="min-width:46px;" data-options="iconCls:'icon-ok'" onclick="doExportAll();">导出</a>
							<!-- <a href="javascript:void(0)" id="sb" class="easyui-splitbutton"  data-options="menu:'#mm',iconCls:'icon-ok'" onclick="doExportAll()">导出所有</a>   
							<div id="mm" style="width:100px;"><div data-options="iconCls:'icon-ok'"><a href="javascript:void(0);" style="min-width:100px;" onclick="doExportSelect()">导出选中</a></div></div> -->  
						</td>
		  			</tr>
				</table>
			</form>
	    </div>   
	    <div data-options="region:'center',border:true,title:''" style="padding:5px;background:#eee;">
	    	<table id="datagrid"></table>
	    </div>   
	</body> 
</html>
