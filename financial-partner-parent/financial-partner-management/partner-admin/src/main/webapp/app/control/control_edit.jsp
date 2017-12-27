<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>修改控件信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<%@ include file="/common/header.jsp"%>
	<script type="text/javascript">
		//提交
		function submitForm(){
			var controlForm = $("#controlForm");
			controlForm.form('submit',{
				url:'${app}/control/doUpdateCtrl',
				onSubmit:function(){
					if(controlForm.form("validate")){
						openMask();
						return true;
					}else{
						return false;
					}
				},
				success:function(data){
					closeMask();
					var obj = eval("(" + data + ")");
					parent.refreshTab("${app}/control/toCtrlList/${control.pageId}?messageCode=" + obj.messageCode,"控件管理");
					parent.closeTab("修改控件信息");
				}
			});
		}
		
		//取消
		function resetForm(){
			parent.closeTab("修改控件信息");
		}
		
	</script>
  </head>
  
  <body style="background: white;">
  	<form id="controlForm" class="easyui-form" method="post" modelAttribute="control">
  		<input type="hidden" id="controlId" name="controlId" value="${control.controlId}">
		<table class="tableForm" border="0" width="100%">
			<tr>
			    <td class="tdR"><span style="color: red">*</span>控件中文名:</td>
				<td align="left">
					<input id="controlName" name="controlName" class="easyui-textbox" value="${control.controlName}" data-options="required:true,validType:['length[0,30]','chineseRule']" style="width: 175px;height: 24px;"/>&nbsp;&nbsp;
					<span style="color: red;">控件中文名，请输入汉字！</span>
				</td>
			</tr>
			<tr>
			    <td class="tdR"><span style="color: red">*</span>控件唯一标识:</td>
				<td align="left">
					<input id="controlMark" name="controlMark" class="easyui-textbox" value="${control.controlMark}" data-options="required:true,validType:['length[0,30]','letterNumUnderlineRule']" style="width: 175px;height: 24px;"/>&nbsp;&nbsp;
					<span style="color: red;">控件唯一标识必须是由字母、数字、下划线组成的字符串！</span>
				</td>
			</tr>
			<tr>
				<td class="tdR"><span style="color: red">*</span>控件描述:</td>
				<td valign="middle">
					<input id="remark" name="remark" class='easyui-textbox' value="${control.remark}" data-options="required:true,multiline:true,validType:['length[0,100]']" style="width:330px;height:60px"/>					
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
	</form>
  </body>
</html>
