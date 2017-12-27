<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>添加员工</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/common/header.jsp"%>
	<script type="text/javascript">
		$(document).ready(function(){
			limit_textarea_input();//统计文本域输入字数
		});
		
		//选择部门
		var deptDialog = null;
		function selectDept(){
			deptDialog = $('#deptDialog').dialog({  
				top:100,
				title: "选择部门",   
				width: 450,   
				height: 320,  
				closed: false,   
				cache: false,   
				href: "${app}/department/toSelectDept?deptId=" + $("#deptId").val(),   
				modal: true,
				buttons:[{
					text:'确定',
					iconCls:'icon-ok',
					handler:function(){
						$("#deptId").val($("#selectDeptId").val());//选择部门赋值
						deptDialog.dialog('close');
						getLevelDeptInfo();
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
		
		//获得层级部门信息
		function getLevelDeptInfo(){
			$.ajax({
				async:false,
				type:'post',
				url:"${app}/department/getLevelDeptInfo/" + $("#deptId").val(),
				dataType:'html',
				success:function(msg){
					$("#deptName").html(msg);
				}
			});
		}
		
		//提交
		function submitForm(){
			var empAddForm = $("#empAddForm");
			empAddForm.form('submit',{
				url:'${app}/employee/doAddEmp',
				onSubmit:function(){
					if(empAddForm.form("validate")){
						if($("#deptId").val() != ""){
							openMask();
							return true;
						}else {
							$.messager.alert('提示信息','请选择所属部门团队','info');
							return false;
						}
					}else{
						return false;
					}
				},
				success:function(data){
					closeMask();
					var obj = eval("(" + data + ")");
					parent.refreshTab("${app}/employee/toEmpList?messageCode=" + obj.messageCode,"员工管理");
					parent.closeTab("添加员工");
				}
			});
		}
		
		//取消
		function resetForm(){
			parent.closeTab("添加员工");
		}
		
	</script>
  </head>
  
  <body style="background: white;">
  	<form id="empAddForm" class="easyui-form" method="post" modelAttribute="employee">
		<table class="tableForm" border="1" width="100%" >
			<tr>
				<td width="15%" class="tdR"><span style="color: red">*</span>姓名:</td>
				<td width="35%">
					<input type="text" id="name" name="name" class="easyui-textbox" data-options="required:true,validType:['length[0,10]']" style="width: 175px;height: 24px;"/>
				</td>
				<td width="15%" class="tdR"><span style="color: red">*</span>性别:</td>
				<td width="35%">
					<input type="radio" id="sex_1" name="sex" value="0" checked="checked"/><label for="sex_1">男</label>
					<input type="radio" id="sex_2" name="sex" value="1"/><label for="sex_2">女</label>
				</td>
			</tr>
			<tr>
				<td class="tdR"><span style="color: red">*</span>邮箱:</td>
				<td>
					<input type="text" id="email" name="email" class="easyui-textbox" data-options="required:true,validType:['length[0,30]','email']" style="width: 175px;height: 24px;"/>
				</td>
				<td class="tdR"><span style="color: red">*</span>员工编号:</td>
				<td>
					<input type="text" id="employeeNo" name="employeeNo" class='easyui-textbox'  data-options="required:true,validType:['length[0,20]']" style="width: 175px;height: 24px;"/>
				</td>
			</tr>
			<tr>
				<td class="tdR">移动电话:</td>
				<td>
					<input type="text" id="mobile" name="mobile" class="easyui-numberbox" data-options="validType:['length[0,11]','mobileIsValid']" style="width: 175px;height: 24px;"/>
				</td>
				<td class="tdR">固定电话:</td>
				<td>
					<input type="text" id="telephone" name="telephone" class="easyui-textbox" data-options="validType:['length[0,13]','telephoneIsValid']" style="width: 175px;height: 24px;"/>
					<label style="color: red;">格式：010-56408888</label>
				</td>
			</tr>
			<tr>
				<td class="tdR"><span style="color: red">*</span>所属部门团队:</td>
				<td colspan="3">
					<input type="hidden" id="deptId" name="deptId"/>
					<span id="deptName" style="padding-left: 5px;"></span>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="selectDept();">选择部门</a>
				</td>
			</tr>
			<tr>
				<td class="tdR"><span style="color: red">*</span>开启状态:</td>
				<td colspan="3">
					<input type="radio" id="activatedState_1" name="activatedState" value="1" checked="checked"/><label for="activatedState_1">开启</label>
					<input type="radio" id="activatedState_2" name="activatedState" value="0"/><label for="activatedState_2">停用</label>
				</td>
			</tr>
			<tr>
				<td class="tdR">备注:</td>
				<td colspan="3">
					<input id="remark" name="remark" class='easyui-textbox' data-options="multiline:true,validType:['length[0,50]']" style="width:330px;height:60px"/>
					<label style="color: red;" >运营管理部最好写备注，管理哪个城市。</label>
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
