<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>奖励结算管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript">
		var datagrid;
		$(function(){
        	
			datagrid = $('#datagrid').datagrid({
				url : '${app}/commissionLiquidation/getCommissionLiquidationList',
				title : '',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				toolbar: '#toolbar',
				border : false,
				singleSelect : false,
				idField : 'commission_liquidation_id',
				columns : [[{
					field:'commission_liquidation_id',
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
					field : 'liquidation_num',
					title : '结算单号',
					width : 120
				},{
					field : 'liquidation_month',
					title : '结算年月',
					width : 120
				},{
					field : 'real_name',
					title : '用户姓名',
					width : 120
				},{
					field : 'username',
					title : '用户手机号',
					width : 120 
				},{
					field : 'user_type',
					title : '用户身份',
					width : 120
				},{
					field : 'invest_total_amount',
					title : '交易总额(万元)',
					width : 120,
					formatter:function(value){
						if (value==null) {
							return ;
						} else {
							return value = fmoney(value,2);
						}
					}
				},{
					field : 'discount_total_amount',
					title : '折标总额(万元)',
					width : 120,
					formatter:function(value){
						if (value==null) {
							return ;
						} else {
							return value = fmoney(value,2);
						}
					}
				},{
					field : 'lend_bonus',
					title : '出借奖金(元)',
					width : 110,
					formatter:function(value){
						if (value==null) {
							return ;
						} else {
							return value = fmoney(value,2);
						}
					}
				},{
					field : 'diff_bonus',
					title : '差额奖金(元)',
					width : 110,
					formatter:function(value){
						if (value==null) {
							return ;
						} else {
							return value = fmoney(value,2);
						}
					}
				},{
					field : 'service_bonus',
					title : '服务奖金(元)',
					width : 110,
					formatter:function(value){
						if (value==null) {
							return ;
						} else {
							return value = fmoney(value,2);
						}
					}
				},{
					field : 'total_reward',
					title : '奖励总额(元)',
					width : 110,
					formatter:function(value){
						if (value==null) {
							return ;
						} else {
							return value = fmoney(value,2);
						}
					}
				},{
					field : 'liquidation_status',
					title : '结算状态',
					width : 110 
				},{
					field : 'liquidation_time',
					title : '结算时间',
					width : 130
				},{
					field : 'operate',
					title : '操作',
					width : 110,
					formatter : function(value,row,index){
						if (row.liquidation_status == '已结算') {
							return "<a href='#' onclick='detail(\""+row.commission_liquidation_id+"\",\""+row.liquidation_month+"\");' >查看详情</a>||<a href='#' onclick='pay(\""+row.commission_liquidation_id+"\");' >结算</a>";
						}else{
							return "<a href='#' onclick='detail(\""+row.commission_liquidation_id+"\",\""+row.liquidation_month+"\");' >查看详情</a>"; 
						}
					}
				}]],
			});
		});
		//年月搜索控件
		$(function() {      
		      $('#liquidation_month').datebox({    
		            onShowPanel : function() {// 显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层    
		                span.trigger('click'); // 触发click事件弹出月份层    
		                if (!tds)    
		                    setTimeout(function() {// 延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔    
		                        tds = p.find('div.calendar-menu-month-inner td');    
		                        tds.click(function(e) {    
		                            e.stopPropagation(); // 禁止冒泡执行easyui给月份绑定的事件    
		                            var year = /\d{4}/.exec(span.html())[0]// 得到年份    
		                            , month = parseInt($(this).attr('abbr'), 10) + 1; // 月份    
		                            $('#liquidation_month').datebox('hidePanel')// 隐藏日期对象    
		                            .datebox('setValue', year + '-' + month); // 设置日期的值    
		                        });    
		                    }, 0);    
		            },    
		            parser : function(s) {// 配置parser，返回选择的日期    
		                if (!s)    
		                    return new Date();    
		                var arr = s.split('-');    
		                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);    
		            },    
		            formatter : function(d) {    
		                var month = d.getMonth();  
		                if(month<=10){  
		                    month = "0"+month;  
		                }  
		                if (d.getMonth() == 0) {    
		                    return d.getFullYear()-1 + '-' + 12;    
		                } else {    
		                    return d.getFullYear() + '-' + month;    
		                }    
		            }// 配置formatter，只返回年月    
		        });    
		        var p = $('#liquidation_month').datebox('panel'), // 日期选择对象    
		        tds = false, // 日期选择对象中月份    
		        span = p.find('span.calendar-text'); // 显示月份层的触发控件    
		        
		    });  
		//奖励结算详情页面
		function detail(commissionLiquidationId,liquidationMonth){
			parent.createTab('${app}/commissionLiquidation/toCommissionLiquidationDetail/' + commissionLiquidationId+'/'+liquidationMonth,'奖励结算详情');
		}
		//搜索
		function searchFun() {
			datagrid.datagrid('load',serializeObject($("#searchForm")));
			datagrid.datagrid('clearSelections');
			datagrid.datagrid('clearChecked');
		}
		
		//清空
		function clearFromFun(datagrid){
			window.location = "${app}/commissionLiquidation/toCommissionLiquidationList"
		}
		
        //奖励结算记录：Excepl导出
		function doExport(){
			$("#searchForm").attr("action", "${app}/commissionLiquidation/exportCommissionLiquidationList");
			$("#searchForm").attr("method", "POST");
			$("#searchForm").submit();
		}
		//结算
		function pay(commission_fee_id) {
			$.messager.confirm("结算","你将要对此用户进行奖励/提成结算，确认要进行结算操作吗？",function(r){
				if (r) {
					var commissionFeeId = commission_fee_id;
					$.ajax({
						type:"POST",
						url: '${app}/commissionLiquidation/commissionFeePay',
						data:{
							"commissionFeeId":commissionFeeId
						},
						dataType:'json',
						beforeSend: function () {
							load();
						},
						success:function(msg){
							disLoad();
							datamsg = msg.messageCode;
							if (datamsg == "0001"){
								$.messager.alert('提示',"结算成功！");
								disLoad();
								searchFun();
							}
							if (datamsg == "0002"){
								$.messager.alert('提示',"结算失败！");
								disLoad();
								searchFun();
							}
						},
					});
				}
			});
		}
		
		//批量结算
		function payMany(){
			var rows = $("#datagrid").datagrid('getSelections');
			if (rows.length < 1){
				$.messager.alert("温馨提示", "请选择数据!");
				return;
			}
			var datas="";
		  	for(var i = 0; i < rows.length; i++){
		  		if(rows[i].liquidation_status == '已支付'){
		  			$.messager.alert("温馨提示", "所选数据包含已结算，请重新选择!");
		  			return;
		  		}
		  		if(i == rows.length-1){
					datas += rows[i].commission_liquidation_id
				}else{
					datas += rows[i].commission_liquidation_id + ",";
				}
		    }
		  	$.messager.confirm("批量结算","你将要对选择的用户进行批量奖励/提成结算，确认要进行结算操作吗？",function(r){
				if(r){
					$.ajax({
						type:"POST",
						url: '${app}/commissionLiquidation/commissionFeePayMany',
						data:{
							"body" : datas,
						},
						dataType:'json',
						beforeSend: function () {
							load();
						},
						success:function(msg){
							if (msg == "1"){
								$.messager.alert('提示',"批量结算成功！");
								disLoad();
								searchFun();
							}
						},
					});
				}
			});
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
  		<div region="north" border="false" style="height:90px; overflow:hidden;">
  		<form id="searchForm">
	  		<table border="0" class="searchForm datagrid-toolbar" width="100%">
		  		<tr>
					<td width="13%" class="tdR">结算单号:</td>
					<td width="15%">
						<input id="liquidation_num" name="liquidation_num" maxlength="28" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td width="20%" class="tdR">结算年月:</td>
					<td width="24%">
						<input id="liquidation_month" name="liquidation_month" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td width="13%" class="tdR">结算状态:</td>
					<td width="15%">
						<select id="liquidation_status" name="liquidation_status" class="easyui-combobox" required='true' panelHeight="130px" editable="false" style="width: 150px;">
								<option value="">全部</option>
								<c:forEach var="obj" items="${CodeMap['LIQUIDATION_STATUS']}">
									<option value="${obj.key}">${obj.value}</option>
								</c:forEach>
						</select>
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
					<td class="tdR">用户身份:</td>
					<td>
						<select id="user_type" name="user_type" class="easyui-combobox" required='true' panelHeight="130px" editable="false" style="width: 150px;">
								<option value="">全部</option>
								<c:forEach var="obj" items="${CodeMap['USER_TYPE']}">
									<option value="${obj.key}">${obj.value}</option>
								</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="tdR">结算时间:</td>
	  				<td colspan="3">
						<input id="min_liquidation_time" name="min_liquidation_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 105px;height: 24px;" data-options="required:false" placeholder ="--起始时间--"/>
                       		 至
                        <input id="max_liquidation_time" name="max_liquidation_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 105px;height: 24px;" data-options="required:false" placeholder ="--终止时间--"/>
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
	<div id="toolbar">
  		<table border="0" class="searchForm datagrid-toolbar" width="100%">
  			<tr>
  				<td width="10%" class="tdL">
  					<a class="easyui-linkbutton" style="min-width:100px;" onclick="payMany()" id="payMany">批量结算</a>
  				</td>
  				<td width="8%" class="tdL" style="text-align:left;" id="Commission1">
  				</td>
  				<td width="10%" class="tdR" style="text-align:left;" id="Commission2">
  				</td>
  				<td width="5%" class="tdL"></td>
  			</tr>
  		</table>
		
	</div>
  </body>
</html>
