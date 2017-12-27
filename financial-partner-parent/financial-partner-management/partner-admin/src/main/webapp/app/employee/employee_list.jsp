<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>员工信息列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		var datagrid;
		$(function(){
			
			//一级部门 财富管理部
			$("#firstDept").combobox({
				url:'${app}/department/findFirstDeptInfo',
				valueField:'dept_id',
				textField:'dept_name',
				onChange:function(newValue,oldValue){
					$("#areaId").combobox('clear');
					$("#areaId").combobox('reload',"${app}/department/findSubWorkDeptInfoByDeptId?deptId=" + newValue);
					$("#areaId").combobox('setValue','');
					$("#salesDeptId").combobox('loadData',[{dept_id:'',dept_name:'请选择'}]);
					$("#salesDeptId").combobox('setValue','');
					$("#teamId").combobox('loadData',[{dept_id:'',dept_name:'请选择'}]);
					$("#teamId").combobox('setValue','');
				}
			});
			
			//地区
			$("#areaId").combobox({
				valueField:'dept_id',
				textField:'dept_name',
				onChange:function(newValue,oldValue){
					$("#salesDeptId").combobox('clear');
					$("#salesDeptId").combobox('reload',"${app}/department/findSubWorkDeptInfoByDeptId?deptId=" + newValue);
					$("#teamId").combobox('loadData',[{dept_id:'',dept_name:'请选择'}]);
					$("#teamId").combobox('setValue','');
				}
			});
			
			//营业部
			$("#salesDeptId").combobox({
				valueField:'dept_id',
				textField:'dept_name',
				onChange:function(newValue,oldValue){
					$("#teamId").combobox('clear');
					$("#teamId").combobox('reload',"${app}/department/findSubWorkDeptInfoByDeptId?deptId=" + newValue);
				}
			});
			
			//团队
			$("#teamId").combobox({
				valueField:'dept_id',
				textField:'dept_name'
			});
			
			//初始化
	        $("#areaId").combobox('loadData',[{dept_id:'',dept_name:'请选择'}]);
	        $("#salesDeptId").combobox('loadData',[{dept_id:'',dept_name:'请选择'}]);
	        $("#teamId").combobox('loadData',[{dept_id:'',dept_name:'请选择'}]);
        	
			datagrid = $('#datagrid').datagrid({
				url : '${app}/employee/employeees',
				title : '',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				toolbar:"#toolbar",
				border : false,
				idField : 'employee_id',
				columns : [[{
					field:'cd',
					checkbox:true
				},{
					field : 'name',
					title : '姓名',
					width : 80
				},{
					field : 'sex',
					title : '性别',
					width : 60
				},{
					field : 'employee_no',
					title : '员工编号',
					width : 100
				},{
					field : 'dept_name',
					title : '所属部门团队',
					width : 240
				},{
					field : 'email',
					title : '邮箱',
					width : 160
				},{
					field : 'mobile',
					title : '移动电话',
					width : 120
				},{
					field : 'telephone',
					title : '固定电话',
					width : 140
				},{
					field : 'activated_state_name',
					title : '开启状态',
					width : 70,
					formatter:function(value,rowData,rowIndex){
						var retStr = "";
						if(value == "启用"){
							retStr = "<span style='color:green;'>" + value + "</span>";
						}else{
							retStr = "<span style='color:red;'>" + value + "</span>";
						}
						return retStr;
					}
				},{
					field : 'is_authorization',
					title : '是否授权',
					width : 80,
					formatter:function(value,rowData,rowIndex){
						var retStr = "";
						if(value > 0){
							retStr = "<span style='color:green;'>已授权</span>";
						}else{
							retStr = "<span style='color:red;'>未授权</span>";
						}
						return retStr;
					}
				}/* ,{
					field : 'work_group_count',
					title : '是否分配工作组',
					width : 100,
					formatter:function(value,rowData,rowIndex){
						var retStr = "";
						if(value > 0){
							retStr = "<span style='color:green;'>已分配</span>";
						}else{
							retStr = "<span style='color:red;'>未分配</span>";
						}
						return retStr;
					}
				} */]]
			});
		});
		
		//添加员工
		function toAddEmp(){
			parent.createTab('${app}/employee/toAddEmp','添加员工');
		}
		
		//修改员工
		function toEditEmp(){
			var rows = datagrid.datagrid('getSelections');
			if(rows.length > 0){
				if(rows.length > 1){
					$.messager.show({
						title:'信息提示',
						msg:'该操作只能选择一条记录!',
						timeout:5000,
						showType:'slide'
					});
					return;
				}
				parent.createTab('${app}/employee/toEditEmp/' + rows[0].employee_id,'修改员工');
			}else{
				$.messager.show({
					title:'信息提示',
					msg:'请选择要修改的记录!',
					timeout:5000,
					showType:'slide'
				});
			}
		}
		
		//停用
		function disableFun(){
			var rows = datagrid.datagrid('getSelections');
			if(rows.length > 0){
				if(rows.length > 1){
					$.messager.show({
						title:'信息提示',
						msg:'该操作只能选择一条记录!',
						timeout:5000,
						showType:'slide'
					});
					return;
				}
				if(rows[0].activated_state == <%=Constants.ACTIVATED_STATE_DISABLE%>){
					$.messager.alert("信息提示", "该用户已停用!", "info",function(){});
					return;
				}
				$.messager.confirm("请确认", "您确实要停用该用户吗？", function(b){
					if(b){
						openMask();
						$.ajax({
							async:false,
							type:'post',
							url:"${app}/employee/disableOrEnabled?employeeId=" + rows[0].employee_id + "&activatedState=<%=Constants.ACTIVATED_STATE_DISABLE%>",
							dataType:'json',
							success:function(msg){
								closeMask();
								parent.createTab('${app}/employee/toEmpList?messageCode=' + msg.messageCode,'员工管理');
							}
						});
					}
				});
			}else{
				$.messager.show({
					title:'信息提示',
					msg:'请选择要停用的记录!',
					timeout:5000,
					showType:'slide'
				});
			}
		}
		
		//启用
		function enabledFun(){
			var rows = datagrid.datagrid('getSelections');
			if(rows.length > 0){
				if(rows.length > 1){
					$.messager.show({
						title:'信息提示',
						msg:'该操作只能选择一条记录!',
						timeout:5000,
						showType:'slide'
					});
					return;
				}
				if(rows[0].activated_state == <%=Constants.ACTIVATED_STATE_ENABLED%>){
					$.messager.alert("信息提示", "该用户已启用!", "info",function(){});
					return;
				}
				$.messager.confirm("请确认", "您确实要启用该用户吗？", function(b){
					if(b){
						openMask();
						$.ajax({
							async:false,
							type:'post',
							url:"${app}/employee/disableOrEnabled?employeeId=" + rows[0].employee_id + "&activatedState=<%=Constants.ACTIVATED_STATE_ENABLED%>",
							dataType:'json',
							success:function(msg){
								closeMask();
								parent.createTab('${app}/employee/toEmpList?messageCode=' + msg.messageCode,'员工管理');
							}
						});
					}
				});
			}else{
				$.messager.show({
					title:'信息提示',
					msg:'请选择要启用的记录!',
					timeout:5000,
					showType:'slide'
				});
			}
		}
		
		//密码重置
		function resetPassword(){
			var rows = datagrid.datagrid('getSelections');
			if(rows.length > 0){
				if(rows.length > 1){
					$.messager.show({
						title:'信息提示',
						msg:'该操作只能选择一条记录!',
						timeout:5000,
						showType:'slide'
					});
					return;
				}
				$.messager.confirm("请确认", "您确实要将该用户密码重置吗？", function(b){
					if(b){
						openMask();
						$.ajax({
							async:false,
							type:'post',
							url:"${app}/employee/resetPassword?employeeId=" + rows[0].employee_id,
							dataType:'json',
							success:function(msg){
								if(msg.requestState == "true"){
									closeMask();
									$.messager.show({
										title:'信息提示',
										msg:'密码重置成功!',
										timeout:5000,
										showType:'slide'
									});
								}else{
									closeMask();
									$.messager.show({
										title:'信息提示',
										msg:'密码重置失败,请与管理员联系!',
										timeout:5000,
										showType:'slide'
									});
								}
							}
						});
					}
				});
			}else{
				$.messager.show({
					title:'信息提示',
					msg:'请选择要操作的记录!',
					timeout:5000,
					showType:'slide'
				});
			}
		}
		
		//员工授权
		function toAuthorization(){
			var rows = datagrid.datagrid('getSelections');
			if(rows.length > 0){
				var empIds = "";
	   			for(var i = 0; i < rows.length; i++){
	   				if(rows[i].is_authorization > 0){
						$.messager.show({
							title:'信息提示',
							msg:'操作员工授权时,选中的记录不能包含已授权的员工!',
							timeout:5000,
							showType:'slide'
						});
						return;
					}
	   				empIds += rows[i].employee_id + ",";
	   			}
	   			empIds = empIds.substr(0, empIds.length-1);
				parent.createTab("${app}/employee/toAuthorization/" + empIds,'员工授权');
			}else{
				$.messager.show({
					title:'信息提示',
					msg:'请选择要授权的员工!',
					timeout:5000,
					showType:'slide'
				});
			}
		}
		
		//修改员工授权
		function toEditAuthorization(){
			var rows = datagrid.datagrid('getSelections');
			if(rows.length > 0){
				if(rows.length > 1){
					$.messager.show({
						title:'信息提示',
						msg:'该操作只能选择一条记录!',
						timeout:5000,
						showType:'slide'
					});
					return;
				}
				if(rows[0].is_authorization == 0){
					$.messager.show({
						title:'信息提示',
						msg:'该员工尚未授权，不能进行授权修改!',
						timeout:5000,
						showType:'slide'
					});
					return;
				}
				parent.createTab("${app}/employee/toEditAuthorization?employeeId=" + rows[0].employee_id + "&name=" + encodeURIComponent(encodeURIComponent(rows[0].name)) + "&deptId=" + rows[0].dept_id,'修改员工授权');
			}else{
				$.messager.show({
					title:'信息提示',
					msg:'请选择要修改授权的员工!',
					timeout:5000,
					showType:'slide'
				});
			}
		}
		
		//设置工作组
		function setWorkGroup() {
			var rows = datagrid.datagrid('getSelections');
			if(rows.length > 0){
				if(rows.length > 1){
					$.messager.show({
						title:'信息提示',
						msg:'该操作只能选择一条记录!',
						timeout:5000,
						showType:'slide'
					});
					return;
				}
				parent.createTab("${app}/employee/toSetWorkGroup?employeeId=" + rows[0].employee_id + "&name=" + encodeURIComponent(encodeURIComponent(rows[0].name)) + "&deptId=" + rows[0].dept_id,'设置工作组');
			}else{
				$.messager.show({
					title:'信息提示',
					msg:'请选择一条员工记录!',
					timeout:5000,
					showType:'slide'
				});
			}
		}
		
		//搜索
		function searchFun() {
			datagrid.datagrid('load',serializeObject($("#searchForm")));
			datagrid.datagrid('clearSelections');
			datagrid.datagrid('clearChecked');
		}
		
		//清空
		function clearFromFun(datagrid){
			clearForm(datagrid);
			
			//一级部门
			$("#firstDept").combobox('clear');
			$("#firstDept").combobox('reload');
			
			//区域
			$("#areaId").combobox('clear');
			$("#areaId").combobox('loadData',[{dept_id:'',dept_name:'请选择'}]);
			
			//营业部
			$("#salesDeptId").combobox('clear');
	        $("#salesDeptId").combobox('loadData',[{dept_id:'',dept_name:'请选择'}]);
	        
	        //团队
	        $("#teamId").combobox('clear');
	        $("#teamId").combobox('loadData',[{dept_id:'',dept_name:'请选择'}]);
		}
	</script>
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:35px; overflow:hidden;">
  		<form id="searchForm">
	  		<table border="0" class="searchForm datagrid-toolbar" width="100%">
	  			<tr>
					<td class="tdR" width="7%">姓名:</td>
					<td width="18%">
						<input id="name" name="name" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td class="tdR" width="8%">员工编号:</td>
					<td width="18%">
						<input id="employeeNo" name="employeeNo" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td class="tdR" width="8%">邮箱:</td>
					<td width="18%">
						<input id="email" name="email" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td>
						<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
						<a class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun(datagrid);">清空</a>
					</td>
				</tr>
				<!-- <tr>
	  				<td class="tdR">员工所在团队:</td>
	  				<td colspan="3">
	  					<input type="text" id="firstDept" name="firstDept" class="easyui-combobox" data-options="valueField:'dept_id',textField:'dept_name'" panelHeight="130px" editable="false" style="width: 110px;height: 24px;"/>
	  					<input type="text" id="areaId" name="areaId" class="easyui-combobox" data-options="valueField:'dept_id',textField:'dept_name'" panelHeight="130px" editable="false" style="width: 110px;height: 24px;"/>
	  					<input type="text" id="salesDeptId" name="salesDeptId" class="easyui-combobox" data-options="valueField:'dept_id',textField:'dept_name'"  panelHeight="130px" editable="false" style="width: 110px;height: 24px;"/>
	  					<input type="text" id="teamId" name="teamId" class="easyui-combobox" data-options="valueField:'dept_id',textField:'dept_name'" editable="false" style="width: 110px;height: 24px;"/>
	  				</td>
	  				<td colspan="2" style="padding-left: 60px;">
						<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
						<a class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun(datagrid);">清空</a>
					</td>
	  			</tr> -->
			</table>
		</form>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
 	<div id="toolbar" style="display: none;">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="toAddEmp();">添加</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="toEditEmp();">修改</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" onclick="disableFun();">停用</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-tip',plain:true" onclick="enabledFun();">启用</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="resetPassword();">密码重置</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="toAuthorization();">员工授权</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="toEditAuthorization();">修改员工授权</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
		<%-- <a class="easyui-linkbutton" data-options="iconCls:'icon-filter',plain:true" onclick="setWorkGroup();">设置工作组</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px"> --%>
	</div>
  </body>
</html>
