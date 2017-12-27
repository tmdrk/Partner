<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>奖金配置管理页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		var datagrid;
		$(function(){
			datagrid = $('#datagrid').datagrid({
				url : '${app}/productRateConfig/productRateConfigList',
				title : '',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				toolbar:"#toolbar",
				border : false,
				singleSelect:true,
				idField : 'id',
				columns : [[{
					field : 'rowNumbers',
					title : '序号',
					width : 140,
					formatter: function(val,rec,index){  
	                       var op = $('#datagrid').datagrid('options');  
	                       return op.pageSize * (op.pageNumber - 1) + (index + 1);  
	                    }  
				},{
					field : 'productTerm',
					title : '期限(月)',
					width : 140
				},{
					field : 'discountDenominator',
					title : '折算系数',
					width : 240,
					formatter: function (value, row, index) {
						if(row.discountDenominator != null){
					    	return row.discountMolecular +'/'+ row.discountDenominator;
						}
				}
				},{
					field : 'productType',
					title : '产品类型',
					width : 260,
				}]],
			});
		});
		
		//新增折算系数
 		function addProductRateConfig(){
			var url = "${app}/productRateConfig/toProductRateConfig";
			openProductRateConfigDialog(url,"新增折算系数");
		}
 		//弹出新增折算系数的界面
 		var productRateConfigDialog;
		function openProductRateConfigDialog(v_url,v_oper){
			productRateConfigDialog = $('#productRateConfigDialog').dialog({  
				top:10,
				title: v_oper,   
				width: 400,   
				height: 250,  
				closed: false,   
				cache: false,   
				href: v_url,   
				modal: true
			}); 
		}	
		
		//新增折算系数
		function createProduct(){
			var productForm = $("#productForm");
			productForm.form('submit',{
				url:'${app}/productRateConfig/addProductRateConfig',
				onSubmit:function(){
					if(productForm.form("validate")){
				    	openMask();
						return true;
					}else{
						return false;
					}
				},
				success:function(msg){
					closeMask();
					var data = eval("(" + msg + ")");
					parent.createTab('${app}/productRateConfig/toProductRateConfigList?messageCode=' + data.messageCode,'奖励配置管理');
				}
			});
		}
		
		//取消新建
		function cancelCreateProduct(){
			productRateConfigDialog.dialog('close');
		} 
		
		//關閉獎金係數配置頁面
		function closeBonusConfig(){
			editProductRateConfigDialog.dialog('close');
		} 
		
		//搜索
		function searchFun() {
			datagrid.datagrid('load',serializeObject($("#searchForm")));
		}
		
		//清空
		function clearFromFun(datagrid){
			clearForm(datagrid);
			//期限（月）
			$("#productTerm").combobox('clear');
		}
		
		//奖金系数配置
		function editProductRateConfig(){
			var url = "${app}/bonusConfig/selectBonusConfig";
			openProductRateConfig(url,"奖金系数配置");
		}
		
		//奖金系数配置显示框
		var editProductRateConfigDialog;
		function openProductRateConfig(v_url,v_oper){
			editProductRateConfigDialog = $("#editProductRateConfigDialog").dialog({  
				top:15,
				title: v_oper,   
				width: 400,   
				height: 250,
				closed: false,   
				cache: false,   
				href: v_url,   
				modal: true
			}); 
		}
		</script>
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:auto; overflow:hidden;">
  		<form id="searchForm" style="margin-bottom:0;">
  			<table border="0" class="searchForm datagrid-toolbar" width="100%">
	  			<tr>
					<td width="6%" class="tdR">期限（月）:</td>
					<td width="20%">
						<input  type="text" id="productTerm" name="productTerm"  class='easyui-numberbox' precision="0" max="100" min="1" style="width: 150px;height: 24px;"/>
					</td>
					<td >
						<a href="javascript:void(0);" class="easyui-linkbutton" style="min-width:46px;" onclick="searchFun()">搜索</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" style="min-width:46px;" onclick="clearFromFun(datagrid);">清空</a>
					</td>
			</table>
		</form>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
  	<div id="toolbar" style="display: none;">
		<a class="easyui-linkbutton" onclick="addProductRateConfig();">新增结算系数</a>&nbsp;&nbsp;
		<a class="easyui-linkbutton" onclick="editProductRateConfig();">奖金系数配置</a>&nbsp;&nbsp;	
	<div id="productRateConfigDialog"></div>
	<div id="editProductRateConfig"></div>
	<div id="editProductRateConfigDialog"></div>
  </body>
</html>
