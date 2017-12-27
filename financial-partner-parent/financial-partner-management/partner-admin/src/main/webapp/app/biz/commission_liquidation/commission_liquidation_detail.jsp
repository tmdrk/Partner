<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>奖励结算详情</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		var datagrid;
		$(function(){
			datagrid = $('#datagrid').datagrid({
				url : '${app}/investBonusManage/getCommissionLiquidationDetailList',
				title : '',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				toolbar:"#toolbar",
				border : false,
				idField : 'user_id',
				queryParams: {
				    "userId": '${commissionLiquidationDetail.user_id}',
				    "liquidationMonth": '${liquidationMonth}'
				},
				columns : [[{
					field:'invest_bonus_id',
					checkbox:true
				},{
					field:'rowNumbers',  
				    title: '序号',  
				    align: 'center',  
				    width: 50, 
				    formatter: function(val,rec,index){  
						var op = $('#datagrid').datagrid('options');  
					    return op.pageSize * (op.pageNumber - 1) + (index + 1);  
					}
				},{
					field : 'contract_no',
					title : '合同编号',
					width : 150
				},{
					field : 'invest_status',
					title : '订单状态',
					width : 120
				},{
					field : 'product_name',
					title : '产品名称',
					width : 150
				},{
					field : 'real_name',
					title : '用户姓名',
					width : 120,
					styler: function(value,row,index){
					    return 'background-color:#666666;';
					}
				},{
					field : 'username',
					title : '用户手机号',
					width : 80,
					styler: function(value,row,index){
					    return 'background-color:#666666;';
					}
				},{
					field : 'user_role',
					title : '用户角色',
					width : 130,
					styler: function(value,row,index){
					    return 'background-color:#666666;';
					}
				},{
					field : 'invest_amount',
					title : '交易金额(元)',
					width : 110,
					formatter:function(value){
						if (value==null) {
							return ;
						} else {
							return value = fmoney(value,2);
						}
					},
					styler: function(value,row,index){
					    return 'background-color:#666666;';
					}
				},{
					field : 'discount_rate',
					title : '折标系数',
					width : 110,
					styler: function(value,row,index){
					    return 'background-color:#666666;';
					}
				},{
					field : 'discount_amount',
					title : '单笔折标额(元)',
					width : 110,
					formatter:function(value){
						if (value==null) {
							return ;
						} else {
							return value = fmoney(value,2);
						}
					},
					styler: function(value,row,index){
					    return 'background-color:#666666;';
					}
				},{
					field : 'superior_real_name',
					title : '上级合伙人姓名',
					width : 110,
					styler: function(value,row,index){
					    return 'background-color:#888888;';
					}
				},{
					field : 'superior_username',
					title : '上级合伙人手机号',
					width : 110,
					styler: function(value,row,index){
					    return 'background-color:#888888;';
					}
				},{
					field : 'on_superior_real_name',
					title : '上上级合伙人姓名',
					width : 110,
					styler: function(value,row,index){
					    return 'background-color:#AAAAAA;';
					}
				},{
					field : 'on_superior_username',
					title : '上上级合伙人手机号',
					width : 110,
					styler: function(value,row,index){
					    return 'background-color:#AAAAAA;';
					}
				},{
					field : 'team_real_name',
					title : '团队管理者姓名',
					width : 110,
					styler: function(value,row,index){
					    return 'background-color:#CCCCCC;';
					}
				},{
					field : 'team_username',
					title : '团队管理者手机号',
					width : 110,
					styler: function(value,row,index){
					    return 'background-color:#CCCCCC;';
					}
				},{
					field : 'create_time',
					title : '生成时间',
					width : 150
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
	
		//奖励结算详情数据导出操作
		function doExport(){
			$("#searchForm").attr("action", "${app}/investBonusManage/exportCommissionLiquidationDetailList");
			$("#searchForm").attr("method", "POST");
			$("#searchForm").submit();
		}
	</script>
  </head>
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:30px; overflow:hidden;">
  		<form id="searchForm">
	  		<table border="0" class="searchForm datagrid-toolbar" width="100%">
	  			<tr>
					<td>
						<input type="hidden" id="user_id" name="user_id" value="${commissionLiquidationDetail.user_id}"/>
						<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="doExport();">导出</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="toolbar" style="display: none;height: 20px;overflow: hidden;padding-top: 5px;padding-left: 15px;">
		<span> 用户姓名:  ${commissionLiquidationDetail.real_name}</span>
		<span>  &nbsp;&nbsp; 用户手机号:  ${commissionLiquidationDetail.username}</span>&nbsp;&nbsp;
		<span>  &nbsp;&nbsp; 奖励总额(元):  ${commissionLiquidationDetail.total_reward}</span>&nbsp;&nbsp;
		<span>  &nbsp;&nbsp; 出借奖金(元):  ${commissionLiquidationDetail.lend_bonus}</span>&nbsp;&nbsp;
		<span>  &nbsp;&nbsp; 差额奖金(元):  ${commissionLiquidationDetail.diff_bonus}</span>&nbsp;&nbsp;
		<span> 	&nbsp;&nbsp; 服务奖金(元):  ${commissionLiquidationDetail.service_bonus}</span>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
  </body>
</html>
