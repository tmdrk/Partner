<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>员工分配工作组</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@ include file="/common/header.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		
	});
	//选择工作组
	var deptDialog = null;
	function selectDeptGroup(){
		deptDialog = $('#deptDialog').dialog({  
			top:100,
			title: "选择工作组",   
			width: 450,   
			height: 320,  
			closed: false,   
			cache: false,   
			href: "${app}/department/selectDeptGroup/${employee.employeeId}",   
			modal: true,
			buttons:[{
				text:'确定',
				iconCls:'icon-ok',
				handler:function(){
					var nodes = zTreeObj.getCheckedNodes(true);
					var deptIds = "";
					var checkedStatus = "";
					var halfStatus = "";
					for(var i=0;i<nodes.length;i++){
						//console.info(nodes[i].getCheckStatus());//获得半选状态  true 表示半选   false 表示真正选中
						deptIds += nodes[i].dept_id + ",";
						checkedStatus += nodes[i].getCheckStatus().checked + ",";
						halfStatus += nodes[i].getCheckStatus().half + ",";
					}
					deptIds = deptIds.substr(0, deptIds.length-1);
					$("#deptIds").val(deptIds);
					checkedStatus = checkedStatus.substr(0, checkedStatus.length-1);
					$("#checkedStatus").val(checkedStatus);
					halfStatus = halfStatus.substr(0, halfStatus.length-1);
					$("#halfStatus").val(halfStatus);
					deptDialog.dialog('close');
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					deptDialog.dialog('close');
				}
			}]
		}); 
	}
	
	//提交
	function submitForm(){
		var empAuthorizationForm = $("#empAuthorizationForm");
		empAuthorizationForm.form('submit',{
			url:'${app}/workGroup/addWorkGroup',
			onSubmit:function(){
				if(empAuthorizationForm.form("validate")){
					openMask();
					return true;
				}else{
					return false;
				}
			},
			success:function(data){
				closeMask();
				var obj = eval("(" + data + ")");
				parent.refreshTab("${app}/employee/toEmpList?messageCode=" + obj.messageCode,"员工管理");
				parent.closeTab("设置工作组");
			}
		});
	}
	
	//取消
	function resetForm(){
		parent.closeTab("设置工作组");
	}
</script>
</head>

<body style="background: white;">
	<form id="empAuthorizationForm" class="easyui-form" method="post">
		<input type="hidden" id="employeeId" name="employeeId" value="${employee.employeeId}"/>
		<table class="tableForm" border="0" width="100%">
			<tr>
				<td class="tdR" width="15%">部门团队:</td>
				<td width="40%">&nbsp;&nbsp;${levelDeptInfo}</td>
				<td class="tdR" width="15%">姓名:</td>
				<td width="30%">&nbsp;&nbsp;${employee.name}</td>
			</tr>
			<tr>
				<td class="tdR">工作组:</td>
				<td colspan="3">
					<input type="hidden" id="deptIds" name="deptIds" value="${deptIds}"/>
					<input type="hidden" id="checkedStatus" name="checkedStatus" value="${checkedStatus}"/>
					<input type="hidden" id="halfStatus" name="halfStatus" value="${halfStatus}"/>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="selectDeptGroup();">选择工作组</a>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a class="easyui-linkbutton" id="submitButton"  iconCls="icon-ok" onclick="submitForm();">提交</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:resetForm();">取消</a>
				</td>
			</tr>
		</table>
		<div id="deptDialog"></div>
	</form>
</body>
</html>
