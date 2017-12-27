<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>奖励单管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript">
		var datagrid;
		$(function(){
        	
			datagrid = $('#datagrid').datagrid({
				url : '${app}/investBonusManage/getInvestBonusManageList',
				title : '',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				toolbar: '#toolbar',
				border : false,
				singleSelect : false,
				idField : 'invest_bonus_id',
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
					field : 'reward_num',
					title : '奖励单号',
					width : 120
				},{
					field : 'contract_no',
					title : '合同编号',
					width : 150,
					formatter : function(value,row,index){
						return "<a href='#' onclick='investDetail(\""+value+"\");' >"+value+"</a>"; 
					}
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
					field : 'user_type',
					title : '用户身份',
					width : 110,
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
					field : 'discount_molecular',
					title : '折标系数',
					width : 110,
					formatter:function(value,rowData,rowIndex){
						if (value==null||rowData.discount_denominator==null) {
							return;
						} else {
							 return value+"/"+rowData.discount_denominator;
						}
                    },
                    styler: function(value,row,index){
					    return 'background-color:#666666;';
					}
				},{
					field : 'discount_amount',
					title : '单笔折标额(元)',
					width : 110,
					formatter:function(value){
						return value = fmoney(value,2);
					},
					styler: function(value,row,index){
					    return 'background-color:#666666;';
					}
				},{
					field : 'user_lend_bonus',
					title : '出借奖金(元)',
					width : 110,
					formatter:function(value){
						if (value==null) {
							return;
						} else {
							return value = fmoney(value,2);
						}
					},
					styler: function(value,row,index){
					    return 'background-color:#666666;';
					}
				},{
					field : 'user_diff_bonus',
					title : '差额奖金(元)',
					width : 110,
					formatter:function(value){
						if (value==null) {
							return;
						} else {
							return value = fmoney(value,2);
						}
					},
					styler: function(value,row,index){
					    return 'background-color:#666666;';
					}
				},{
					field : 'user_total_bonus',
					title : '奖励总额(元)',
					width : 110,
					formatter:function(value){
						if (value==null) {
							return;
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
					field : 'superior_user_type',
					title : '上级合伙人身份',
					width : 110,
					styler: function(value,row,index){
					    return 'background-color:#888888;';
					}
				},{
					field : 'superior_lend_bonus',
					title : '出借奖金(元)',
					width : 110,
					formatter:function(value){
						if (value==null) {
							return;
						} else {
							return value = fmoney(value,2);
						}
					},
					styler: function(value,row,index){
					    return 'background-color:#888888;';
					}
				},{
					field : 'superior_diff_bonus',
					title : '差额奖金(元)',
					width : 110,
					formatter:function(value){
						if (value==null) {
							return;
						} else {
							return value = fmoney(value,2);
						}
					},
					styler: function(value,row,index){
					    return 'background-color:#888888;';
					}
				},{
					field : 'superior_total_bonus',
					title : '奖励总额(元)',
					width : 110,
					formatter:function(value){
						if (value==null) {
							return;
						} else {
							return value = fmoney(value,2);
						}
					},
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
					field : 'on_superior_user_type',
					title : '上上级合伙人身份',
					width : 110,
					styler: function(value,row,index){
					    return 'background-color:#AAAAAA;';
					}
				},{
					field : 'on_superior_lend_bonus',
					title : '出借奖金(元)',
					width : 110,
					formatter:function(value){
						if (value==null) {
							return;
						} else {
							return value = fmoney(value,2);
						}
					},
					styler: function(value,row,index){
					    return 'background-color:#AAAAAA;';
					}
				},{
					field : 'on_superior_total_bonus',
					title : '奖励总额(元)',
					width : 110,
					formatter:function(value){
						if (value==null) {
							return;
						} else {
							return value = fmoney(value,2);
						}
					},
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
					field : 'service_bonus',
					title : '服务奖金(元)',
					width : 110,
					formatter:function(value){
						if (value==null) {
							return;
						} else {
							return value = fmoney(value,2);
						}
					},
					styler: function(value,row,index){
					    return 'background-color:#CCCCCC;';
					}
				},{
					field : 'team_total_bonus',
					title : '奖励总额(元)',
					width : 110,
					formatter:function(value){
						if (value==null) {
							return;
						} else {
							return value = fmoney(value,2);
						}
					},
					styler: function(value,row,index){
					    return 'background-color:#CCCCCC;';
					}
				},{
					field : 'create_time',
					title : '生成时间',
					width : 150
				}]],
			});
		});

		//查看订单详情
		function investDetail(contract_no){
			parent.createTab('${app}/investBonusManage/toInvestDetail/' + contract_no,'查看订单详情');
		}
		//搜索
		function searchFun() {
			datagrid.datagrid('load',serializeObject($("#searchForm")));
			datagrid.datagrid('clearSelections');
			datagrid.datagrid('clearChecked');
		}
		
		//清空
		function clearFromFun(datagrid){
			window.location = "${app}/investBonusManage/toInvestBonusManageList"
		}
		
		
        //奖励单：Excepl导出
		function doExport(){
			$("#searchForm").attr("action", "${app}/investBonusManage/exportInvestBonusList");
			$("#searchForm").attr("method", "POST");
			$("#searchForm").submit();
		}
		
		//弹出加载层
		function load() {  
		    $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");  
		    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });  
		}
		//取消加载层  
		function disLoad() {  
		    $(".datagrid-mask").remove();  
		    $(".datagrid-mask-msg").remove();  
		}
	</script>
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  		<div region="north" border="false" style="height:170px; overflow:hidden;">
  		<form id="searchForm">
	  		<table border="0" class="searchForm datagrid-toolbar" width="100%">
		  		<tr>
					<td width="13%" class="tdR">奖励单号:</td>
					<td width="15%">
						<input id="reward_num" name="reward_num" maxlength="28" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td width="20%" class="tdR">合同编号:</td>
					<td width="24%">
						<input id="contract_no" name="contract_no" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td width="13%" class="tdR">产品名称:</td>
					<td width="15%">
						<input id="product_name" name="product_name" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
				</tr>
		  		<tr>
		  			
					<td class="tdR">用户姓名:</td>
					<td>
						<input id="real_name" name="real_name" maxlength="28" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
		  			<td class="tdR">用户手机号:</td>
					<td>
						<input id="username" name="username" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td class="tdR">用户角色:</td>
					<td>
						<select id="user_role" name="user_role" class="easyui-combobox" required='true' panelHeight="130px" editable="false" style="width: 150px;">
								<option value="">全部</option>
								<c:forEach var="obj" items="${CodeMap['USER_ROLE']}">
									<option value="${obj.key}">${obj.value}</option>
								</c:forEach>
						</select>
					</td>
				</tr>
	  			<tr>
					
					<td class="tdR">上级合伙人姓名:</td>
					<td>
						<input id="superior_real_name" name="superior_real_name" maxlength="28" class='easyui-textbox' style="width:150px;height: 24px;"/>
					</td>
					<td class="tdR">上级合伙人手机号:</td>
					<td >
						<input id="superior_username" name="superior_username" maxlength="28" class='easyui-textbox' style="width:150px;height: 24px;"/>
					</td>
					<td class="tdR">上级合伙人身份:</td>
					<td>
						<select id="superior_user_type" name="superior_user_type" class="easyui-combobox" required='true' panelHeight="130px" editable="false" style="width: 150px;">
								<option value="">全部</option>
								<c:forEach var="obj" items="${CodeMap['USER_TYPE']}">
									<option value="${obj.key}">${obj.value}</option>
								</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					
					<td class="tdR">上上级合伙人姓名:</td>
					<td>
						<input id="on_superior_real_name" name="on_superior_real_name" maxlength="28" class='easyui-textbox' style="width:150px;height: 24px;"/>
					</td>
					<td class="tdR">上上级合伙人手机号:</td>
					<td >
						<input id="on_superior_username" name="on_superior_username" maxlength="28" class='easyui-textbox' style="width:150px;height: 24px;"/>
					</td>
					<td class="tdR">上上级邀请人身份:</td>
					<td>
						<select id="on_superior_user_type" name="on_superior_user_type" class="easyui-combobox" required='true' panelHeight="130px" editable="false" style="width: 150px;">
								<option value="">全部</option>
								<c:forEach var="obj" items="${CodeMap['USER_TYPE']}">
									<option value="${obj.key}">${obj.value}</option>
								</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					
					<td class="tdR">团队管理者姓名:</td>
					<td>
						<input id="team_real_name" name="team_real_name" maxlength="28" class='easyui-textbox' style="width:150px;height: 24px;"/>
					</td>
					<td class="tdR">团队管理者手机号:</td>
					<td >
						<input id="team_username" name="team_username" maxlength="28" class='easyui-textbox' style="width:150px;height: 24px;"/>
					</td>
					<td class="tdR">订单状态:</td>
					<td>
						<select id="invest_status" name="invest_status" class="easyui-combobox" required='true' panelHeight="130px" editable="false" style="width: 150px;">
								<option value="">全部</option>
								<c:forEach var="obj" items="${CodeMap['INVEST_STATUS']}">
									<option value="${obj.key}">${obj.value}</option>
								</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="tdR">生成时间:</td>
	  				<td colspan="3">
						<input id="min_create_time" name="min_create_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 105px;height: 24px;" data-options="required:false" placeholder ="--起始时间--"/>
                       		 至
                        <input id="max_create_time" name="max_create_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 105px;height: 24px;" data-options="required:false" placeholder ="--终止时间--"/>
					</td>
	  				<td  class="tdL" colspan="2">
						<a href="javascript:void(0);" class="easyui-linkbutton" style="min-width:46px;" onclick="searchFun()">搜索</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" style="min-width:46px;" onclick="clearFromFun(datagrid);">清空</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" style="min-width:46px;" onclick="doExport()">导出</a>
					</td>
	  			</tr>
			</table>
		</form>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
  </body>
</html>
