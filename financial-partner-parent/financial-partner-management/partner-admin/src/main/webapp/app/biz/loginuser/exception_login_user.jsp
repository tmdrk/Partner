<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>异常用户列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		var datagrid;
		$(function(){
			datagrid = $('#datagrid').datagrid({
				url : '${app}/loginuser/exceptionLoginUserList',
				title : '',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				toolbar:"#toolbar",
				border : false,
				idField : 'user_id',
				columns : [[{
					field:'cd',
					checkbox:true
				},{
					field : 'rowNumbers',
					title : '序号',
					width : 40,
					formatter: function(val,rec,index){  
	                   var op = $('#datagrid').datagrid('options');  
	                   return op.pageSize * (op.pageNumber - 1) + (index + 1);  
	                }  
				},{
					field : 'user_role',
					title : '用户角色',
					width : 80,
					hidden:true
				},{
					field : 'user_id',
					title : '用户id',
					width : 100,
					hidden:true
				},{
					field : 'real_name',
					title : '用户姓名',
					width : 120
				},{
					field : 'username',
					title : '用户手机号',
					width : 180
				},{
					field : 'user_role_name',
					title : '用户角色',
					width : 180
				},{
					field : 'input_invitation_code',
					title : '汇中网邀请码',
					width : 180
				},{
					field : 'partner_time',
					title : '成为合伙人时间',
					width : 180
				},{
					field : 'regist_time',
					title : '汇中网注册时间',
					width : 180
				}]]
			});
		});
		
		 //弹出变更关系框
        function editPartnerRelation(){
        	$("#msg").html("");
        	$("#real_name").html("");//邀请人姓名
            $("#mobile").html("");//邀请人手机号
            $("#user_role").html("");//邀请人角色
            $("#user_type").html("");//邀请人身份
			var rows = datagrid.datagrid('getSelections');
			if(rows.length <= 0){
				$.messager.show({
                    title:'信息提示',
                    msg:"请选择需要变更关系的用户",
                    timeout:3000,
                    showType:'slide'
                });
				return;
			}else{
				for(var i=0;i < rows.length;i++){
					if(rows[i].user_role == <%=Constants.USER_ROLE_CUSTOMER%>){
						$.messager.alert("信息提示", "变更关系用户不能包含客户!", "info",function(){});
						return;
					}
				}
			}
        	$("#partnerRecommendCode").val("");
            $("#updateUserRelationDiv").dialog('open');
        }
		//根据邀请码查找用户信息
        function findLoginUserByInviteCode(){
        	 $("#msg").html("");
        	 $("#real_name").html("");//邀请人姓名
             $("#mobile").html("");//邀请人手机号
             $("#user_role").html("");//邀请人角色
             $("#user_type").html("");//邀请人身份
            var partnerRecommendCode = $("#partnerRecommendCode").val();
            if(validate(partnerRecommendCode)){
	            $.ajax({
	                url : "${app}/loginuser/getUserByPartnerRecommendCodeOrUsername",
	                data : {"partnerRecommendCode":partnerRecommendCode},
	                type : "post",
	                dataType : "json",
	                success : function(data) {
	                    if(data.code=="0000"){//查询用户成功
	                        $("#user_id").val(data.user_id);//邀请人user_id
	                        $("#real_name").html(data.real_name);//邀请人姓名
	                        $("#mobile").html(data.username);//邀请人手机号
	                        $("#user_role").html(data.user_role_name);//邀请人角色
	                        $("#user_type").html(data.user_type_name);//邀请人身份
	                    }
	                    if(data.code=="1111"){//手机号查询用户成功
	                    	$("#user_id").val(data.user_id);//邀请人user_id
	                    	$("#partnerRecommendCode").val(data.partner_recommend_code);//邀请人推荐码
	                        $("#real_name").html(data.real_name);//邀请人姓名
	                        $("#mobile").html(data.username);//邀请人手机号
	                        $("#user_role").html(data.user_role_name);//邀请人角色
	                        $("#user_type").html(data.user_type_name);//邀请人身份
	                    }
	                    if(data.code=="1001"){//1001表示推荐码是手机号查询用户数据不存在
	                    	 /* $.messager.show({
	                            title:'信息提示',
	                            msg:"该手机号对应的用户不是合伙人",
	                            timeout:3000,
	                            showType:'slide'
	                        }); */
	                    	$("#msg").html("该手机号对应的用户不是合伙人");
	        		        $("#msg").css("color","red"); 
	                    }
	                    if(data.code=="1101"){//1101表示推荐码查询用户数据不存在 
	                        /* $.messager.show({
	                            title:'信息提示',
	                            msg:"该邀请人推荐码暂无用户信息",
	                            timeout:3000,
	                            showType:'slide'
	                        }); */
	                    	$("#msg").html("该推荐码暂无用户信息!");
	        		        $("#msg").css("color","red");
	                    }
	                }
	            })
            }
        };
		
     	//变更用户关系提交操作
		function submitForm(){
			var partnerRecommendCode = $("#partnerRecommendCode").val();
     		if(!validate(partnerRecommendCode)){
     			return false;
     		}
			var rows = datagrid.datagrid('getSelections');
			if(rows.length > 0){
				var userIds = "";
	   			for(var i = 0; i < rows.length; i++){
	   				userIds += rows[i].user_id + ",";
	   			}
	   			userIds = userIds.substr(0, userIds.length-1);
	   			$("#user_ids").val(userIds);
				$('#updateUserRelationDiv').dialog('close');
				$("#updataLoginUserForm").ajaxSubmit({
	          		type : 'POST',
	          		url:'${app}/loginuser/updataLoginUserRelation',
	         		dataType:'json',
	          		success : function(msg) {
	          			parent.createTab('${app}/loginuser/toExceptionLoginUser?messageCode=' + msg.messageCode,'异常数据管理');
	          		}
				});
			}
     	 }
		//取消变更关系操作
		function resetForm(){
			$('#updateUserRelationDiv').dialog('close');
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
		}
	
		//导出
		function doExport(){
			$("#searchForm").attr("action", "${app}/employee/exportEmployes");
			$("#searchForm").attr("method", "POST");
			$("#searchForm").submit();
		}
		//输入推荐码校验
		function validate (value){
			$("#msg").html("");
			var validate1 = /^(1[3,4,5,7,8])\d{9}$/; //验证手机号
			var validate2 = /^301\d{8}$/;//验证推荐码
			//var validate3 = /^601\d{8}$/;//验证推荐码
			if(validate2.test(value) || validate1.test(value)){
				return true;
			}else{
				$("#msg").html("推荐码格式不正确!");
		        $("#msg").css("color","red");
				/* $.messager.show({
                    title:'信息提示',
                    msg:"邀请人推荐码格式不正确",
                    timeout:3000,
                    showType:'slide'
                }); */
				return false;
			}
		}
		function inviteNumber(){
			$("#msg").html("");
			var inviteCode = $("#partnerRecommendCode").val();
			var  re = /^\d{11}$/;
			if(re.test(inviteCode)){
				$("#msg").html("");
			}else{
				$("#msg").html("请输入11位的推荐码");
		        $("#msg").css("color","red");
			}
		}
	</script>
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:64px; overflow:hidden;">
  		<form id="searchForm">
	  		<table border="0" class="searchForm datagrid-toolbar" width="100%">
	  			<tr>
					<td class="tdR" width="9%">用户姓名:</td>
					<td width="27%">
						<input id="realName" name="realName" maxlength="30" class='easyui-textbox' style="width: 160px;height: 24px;"/>
					</td>
					<td class="tdR" width="9%">用户手机号:</td>
					<td width="27%">
						<input id="username" name="username" maxlength="30" class='easyui-textbox' style="width: 160px;height: 24px;"/>
					</td>
					<td class="tdR" width="9%">汇中网邀请码:</td>
					<td width="27%">
						<input id="inputInvitationCode" name="inputInvitationCode" maxlength="30" class='easyui-textbox' style="width: 160px;height: 24px;"/>
					</td>
				</tr>
	  			<tr>
	  				<td class="tdR">成为合伙人时间:</td>
					<td>
						<input id="min_change_partner_time" name="min_change_partner_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 140px;height: 24px;" data-options="required:false" placeholder ="--起始时间--"/>
						~
						<input id="max_change_partner_time" name="max_change_partner_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 140px;height: 24px;" data-options="required:false" placeholder ="--终止时间--"/>
					</td>
	  				<td class="tdR" >汇中网注册时间:</td>
					<td>
						<input id="min_regist_time" name="min_regist_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 140px;height: 24px;" data-options="required:false" placeholder ="--起始时间--"/>
						~
						<input id="max_regist_time" name="max_regist_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 140px;height: 24px;" data-options="required:false" placeholder ="--终止时间--"/>
					</td>
					<td colspan="5">
						<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
						<a class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun(datagrid);">清空</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="toolbar" style="display: none;">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="editPartnerRelation();">变更关系</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
  	
  	<!--变更用户关系 -->
	<div id="updateUserRelationDiv" style="display: none; overflow: hidden;width:45%;height:70%;"class="easyui-dialog" title="变更关系" data-options="iconCls:'icon-edit',closed:true" >
		<form id="updataLoginUserForm" style="width:480px;height:300px;margin-left: 20%;" >
			<table>
				<tr style="display:block;margin: 20px">
					<td><span style="font-size: 16px">请填写已选中人员需要变更的关系信息</span></td>
				</tr>
				<tr style="display:block;margin: 3px">
					<td>上级合伙人推荐码 : </td>
					<td>
						<input type="hidden" id="user_id" name="user_id"/>
						<input type="hidden" id="user_ids" name="user_ids"/>
						<input id="partnerRecommendCode" name="partnerRecommendCode"  style="width: 160px;height: 24px;" onblur="findLoginUserByInviteCode();" oninput="inviteNumber();"/>
						<span id="msg"></span>
					</td>
				</tr>
				<tr style="display:block;margin: 3px ">
					<td>上级合伙人姓名 : </td>
					<td><span id="real_name"></span></td>
				</tr>
				<tr style="display:block;margin: 3px ">
					<td>上级合伙人手机号 : </td>
					<td><span id="mobile"></span></td>
				</tr>
				<tr style="display:block;margin: 3px ">
					<td>上级合伙人角色   : </td>
					<td><span id="user_role"></span></td>
				</tr>
				<tr style="display:block;margin: 3px ">
					<td>上级合伙人身份 : </td>
					<td><span id="user_type"></span></td>
				</tr>
				<br><br>
				<tr>
					<td colspan="5" align="center" style="padding-top: 10px">
						<a class="easyui-linkbutton" id="submitButton"  iconCls="icon-ok" onclick="submitForm();">提交</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="resetForm();">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
  </body>
</html>
