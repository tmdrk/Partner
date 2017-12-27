<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户数据信息列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		var datagrid;
		$(function(){
			//渠道
			$("#organizationChannelId").combobox({
				url:'${app}/partnerOrganization/findSecondOrgan',
				valueField:'organization_id',
				textField:'organization_name',
				onChange:function(newValue,oldValue){
					$("#organizationAreaId").combobox('clear');
					$("#organizationAreaId").combobox('reload',"${app}/partnerOrganization/findCascadingOrgan?organizationId=" + newValue);
				}
			});
			
			//区域
			$("#organizationAreaId").combobox({
				valueField:'organization_id',
				textField:'organization_name'
			});
			
			//初始化
	        $("#organizationChannelId").combobox('loadData',[{organization_id:'',organization_name:'请选择'}]);
	        $("#organizationAreaId").combobox('loadData',[{organization_id:'',organization_name:'请选择'}]);
        	
			datagrid = $('#datagrid').datagrid({
				url : '${app}/loginuser/loginUsers',
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
					field : 'user_id',
					title : '用户id',
					width : 80,
					hidden:true
				},{
					field : 'partner_recommend_code',
					title : '邀请人角色',
					width : 80,
					hidden:true
				},{
					field : 'customer_recommend_code',
					title : '邀请人角色',
					width : 80,
					hidden:true
				},{
					field : 'parent_user_role',
					title : '邀请人角色',
					width : 80,
					hidden:true
				},{
					field : 'user_status',
					title : '用户状态',
					width : 80,
					hidden:true
				},{
					field : 'real_name',
					title : '用户姓名',
					width : 100,
					styler: function(value,row,index){
					    return 'background-color:#666666;';
					}
				},{
					field : 'username',
					title : '用户手机号',
					width : 100,
					styler: function(value,row,index){
					    return 'background-color:#666666;';
					}
				},{
					field : 'user_role_name',
					title : '用户角色',
					width : 100,
					styler: function(value,row,index){
					    return 'background-color:#666666;';
					}
				},{
					field : 'user_type_name',
					title : '用户身份',
					width : 100,
					styler: function(value,row,index){
					    return 'background-color:#666666;';
					}
				},{
					field : 'organization_channel_id_name',
					title : '所属渠道',
					width : 100,
					styler: function(value,row,index){
					    return 'background-color:#666666;';
					}
				},{
					field : 'organization_area_id_name',
					title : '所属区域',
					width : 100,
					styler: function(value,row,index){
					    return 'background-color:#666666;';
					}
				},{
					field : 'parent_name',
					title : '上级合伙人姓名',
					width : 110,
					styler: function(value,row,index){
					    return 'background-color:#949494;';
					}
				},{
					field : 'parent_username',
					title : '上级合伙人手机号',
					width : 110,
					styler: function(value,row,index){
					    return 'background-color:#949494;';
					}
				},{
					field : 'invitation_code',
					title : '上级合伙人推荐码',
					width : 110,
					styler: function(value,row,index){
					    return 'background-color:#949494;';
					}
				},{
					field : 'parent_real_name',
					title : '邀请人姓名',
					width : 100,
					styler: function(value,row,index){
					    return 'background-color:#949494;';
					}
				},{
					field : 'parent_user_role_name',
					title : '邀请人角色',
					width : 100,
					styler: function(value,row,index){
					    return 'background-color:#949494;';
					}
				},{
					field : 'input_invitation_code',
					title : '汇中网邀请码',
					width : 100,
					styler: function(value,row,index){
					    return 'background-color:#949494;';
					}
				},{
					field : 'partner_time',
					title : '合伙人平台注册时间',
					width : 140
				},{
					field : 'regist_time',
					title : '汇中网注册时间',
					width : 140
				},{
					field : 'user_status_name',
					title : '用户状态',
					width : 70
				},{
					field : 'operate',
					title : '操作',
					width : 120,
					formatter : function(value,row,index){
					    var str="";
						if(row.user_role =='10'){
							str = "<a href='#' onclick='findLoginPartnerUser(\""+row.user_id+"\");' >查看下级用户</a>";
						}else{
                            str = "<a href='#' onclick='findLoginUser(\""+row.user_id+"\");' >查看邀请用户</a>";
						}
						return str;
					}
				}]]
			});
		});
		
		
		//查看下级用户
		function findLoginPartnerUser(userId){
			parent.createTab('${app}/loginuser/toFindLoginPartnerUser/' + userId,'查看下级用户');
		}
		//查看邀请用户
		function findLoginUser(userId){
			parent.createTab('${app}/loginuser/toFindLoginUser/' + userId,'查看邀请用户');
		}
		
		//禁用用户
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
				if(rows[0].user_status == <%=Constants.USER_STATE_DISABLE%>){
					$.messager.alert("信息提示", "该用户已禁用!", "info",function(){});
					return;
				}
				$.messager.confirm("请确认", "您确实要停用该用户吗？", function(b){
					if(b){
						openMask();
						$.ajax({
							async:false,
							type:'post',
							url:"${app}/loginuser/disableOrEnabled?userId=" + rows[0].user_id + "&userStatus=<%=Constants.USER_STATE_DISABLE%>",
							dataType:'json',
							success:function(msg){
								closeMask();
								parent.createTab('${app}/loginuser/toLoginUserListPage?messageCode=' + msg.messageCode,'用户数据管理');
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
		
		//启用用户
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
				if(rows[0].user_status == <%=Constants.USER_STATE_ENABLED%>){
					$.messager.alert("信息提示", "该用户已启用!", "info",function(){});
					return;
				}
				$.messager.confirm("请确认", "您确实要启用该用户吗？", function(b){
					if(b){
						openMask();
						$.ajax({
							async:false,
							type:'post',
							url:"${app}/loginuser/disableOrEnabled?userId=" + rows[0].user_id + "&userStatus=<%=Constants.USER_STATE_ENABLED%>",
							dataType:'json',
							success:function(msg){
								closeMask();
								parent.createTab('${app}/loginuser/toLoginUserListPage?messageCode=' + msg.messageCode,'用户数据管理');
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
		
		//修改员工
		function toEditLoginUser(){
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
				parent.createTab('${app}/loginuser/toEditLoginUser/' + rows[0].user_id,'编辑用户');
			}else{
				$.messager.show({
					title:'信息提示',
					msg:'请选择要修改的记录!',
					timeout:5000,
					showType:'slide'
				});
			}
		}
		
		//跳转到异常用户数据列表
		function toExceptionLoginUser(){
			parent.createTab('${app}/loginuser/toExceptionLoginUser','异常数据管理');
		}
		//组织架构归属分配
		function toOrganization(){
			var rows = datagrid.datagrid('getSelections');
			if(rows.length > 0){
				var userIds = "";
	   			for(var i = 0; i < rows.length; i++){
	   				userIds += rows[i].user_id + ",";
	   			}
	   			userIds = userIds.substr(0, userIds.length-1);
	   			$('#organization').dialog('open');
	   			$("#userIds").val(userIds);
	   			//渠道
				$("#organization_channel_id").combobox({
					url:'${app}/partnerOrganization/findSecondOrgan',
					valueField:'organization_id',
					textField:'organization_name',
					onChange:function(newValue,oldValue){
						$("#organization_area_id").combobox('clear');
						$("#organization_area_id").combobox('reload',"${app}/partnerOrganization/findCascadingOrgan?organizationId=" + newValue);
					}
				});
				//区域
				$("#organization_area_id").combobox({
					valueField:'organization_id',
					textField:'organization_name'
				});
				//初始化
		        $("#organization_channel_id").combobox('loadData',[{organization_id:'',organization_name:'请选择'}]);
		        $("#organization_area_id").combobox('loadData',[{organization_id:'',organization_name:'请选择'}]);
			}else{
				$.messager.show({
					title:'信息提示',
					msg:'请选择组织架构分配的用户!',
					timeout:5000,
					showType:'slide'
				});
			}	
		}
		//分配组织架构提交按钮
		function submitOrgForm(){
			$('#organization').dialog('close');
			$("#orgForm").ajaxSubmit({
	          	type : 'POST',
	          	url:'${app}/loginuser/addOrganization',
	         	dataType:'json',
	          	success : function(msg) {
	          		parent.createTab('${app}/loginuser/toLoginUserListPage?messageCode=' + msg.messageCode,'用户数据管理');
	          	}
			});
		}
		//分配组织架构 取消
		function resetOrgForm(){
			$('#organization').dialog('close');
		}
		
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
	          			parent.createTab('${app}/loginuser/toLoginUserListPage?messageCode=' + msg.messageCode,'用户数据管理');
	          		}
				});
			}
     	 }
		//取消变更关系操作
		function resetForm(){
			$('#updateUserRelationDiv').dialog('close');
		}
		
		//输入推荐码校验
		function validate (value){
			var validate1 = /^(1[3,4,5,7,8])\d{9}$/; //验证手机号
			var validate2 = /^301\d{8}$/;//验证推荐码
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
		//推荐码位数校验提示
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
			$("#organizationChannelId").combobox('clear');
			$("#organizationAreaId").combobox('reload');
			$("#parentUserRole").combobox("setValue","");
			$("#userType").combobox("setValue","");
			$("#userRole").combobox("setValue","");
			$("#userStatus").combobox("setValue","");
			$("#position").combobox("setValue",""); 
		}
		//用户数据导出操作
		function doExport(){
			$("#searchForm").attr("action", "${app}/loginuser/exportLoginUsers");
			$("#searchForm").attr("method", "POST");
			$("#searchForm").submit();
		}
		//导入
		function toUpload(){
			$('#upload').dialog('open');
		}
		function resetFileForm(){
			$('#upload').dialog('close');
		}
		//上传
		function upload() {
			var multipart = $("#fileName").val();
			if(multipart==""||multipart==null){
				alert("请先选择文件!");
				return ;
			}
			$('#upload').dialog('close');
	     	$("#fileForm").ajaxSubmit({
	          	type : 'POST',
	          	url:'${app}/loginuser/doUpload',
	         	dataType:'json',
	          	success : function(data) {
					if(data.returnCode == "0000" ){
		          		$.messager.show({
		                    title:'信息提示',
		                    msg:'用户数据导入成功',
		                    timeout:5000,
		                    showType:'slide'
		                });
		          		return false;
		          	}else{
		          		$.messager.show({
		                    title:'信息提示',
		                    msg:'用户数据导入失败',
		                    timeout:5000,
		                    showType:'slide'
		                });
		          		return false;
		          	}
	          	}
	      });
	  }
	</script>
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:130px; overflow:hidden;">
  		<form id="searchForm">
	  		<table border="0" class="searchForm datagrid-toolbar" width="100%">
	  			<tr>
					<td class="tdR" width="7%">用户姓名:</td>
					<td width="7%">
						<input id="realName" name="realName" maxlength="30" class='easyui-textbox' style="width: 160px;height: 24px;"/>
					</td>
					<td class="tdR" width="7%">用户手机号:</td>
					<td width="7%">
						<input id="username" name="username" maxlength="30" class='easyui-textbox' style="width: 160px;height: 24px;"/>
					</td>
					<td class="tdR" width="7%">用户角色:</td>
					<td width="7%">
						<select  id="userRole" name="userRole" class="easyui-combobox" style="width: 160px;height: 24px;">
							<option value=""selected="selected">全部</option>
							<!-- <option value="1">合伙人</option>
							<option value="0">客户</option> -->
							<c:forEach var="obj" items="${CodeMap['USER_ROLE']}">
							<option value="${obj.key}">${obj.value}</option>
							</c:forEach>
						</select>
					</td>
					<td class="tdR" width="7%">用户状态:</td>
					<td width="7%">
						<select  id="userStatus" name="userStatus" class="easyui-combobox" style="width: 160px;height: 24px;">
							<option value=""selected="selected">全部</option>
							<!-- <option value="1">启用</option>
							<option value="0">禁用</option> -->
							
							<c:forEach var="obj" items="${CodeMap['USER_STATUS']}">
							<option value="${obj.key}">${obj.value}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="tdR" width="7%">用户身份:</td>
					<td width="7%">
						<select  id="userType" name="userType" class="easyui-combobox" style="width: 160px;height: 24px;">
							<option value="" selected="selected">全部</option>
							<c:forEach var="obj" items="${CodeMap['USER_TYPE']}">
								<option value="${obj.key}">${obj.value}</option>
							</c:forEach>
						</select>
					</td>
					<td class="tdR" width="7%">所属渠道:</td>
					<td width="7%">
						<input type="text" id="organizationChannelId" name="organizationChannelId" class="easyui-combobox" data-options="valueField:'organization_id',textField:'organization_name'" panelHeight="130px" editable="false" style="width: 160px;height: 24px;"/>
					</td>
					<td class="tdR" width="7%">所属区域:</td>
					<td width="7%">
	  					<input type="text" id="organizationAreaId" name="organizationAreaId" class="easyui-combobox" data-options="valueField:'organization_id',textField:'organization_name'" panelHeight="130px" editable="false" style="width: 160px;height: 24px;"/>
					</td>
					<td class="tdR" width="7%">上级合伙人推荐码:</td>
					<td width="7%">
						<input id="invitationCode" name="invitationCode" maxlength="30" class='easyui-textbox' style="width: 160px;height: 24px;"/>
					</td>
	  			</tr>
	  			<tr>
					<td class="tdR" width="7%">邀请人姓名:</td>
					<td width="7%">
						<input id="parentRealName" name="parentRealName" maxlength="30" class='easyui-textbox' style="width: 160px;height: 24px;"/>
					</td>
					<td class="tdR" width="7%">邀请人角色:</td>
					<td width="7%">
						<select  id="parentUserRole" name="parentUserRole" class="easyui-combobox" style="width: 160px;height: 24px;">
							<option value="" selected="selected">全部</option>
							<c:forEach var="obj" items="${CodeMap['USER_ROLE']}">
								<option value="${obj.key}">${obj.value}</option>
							</c:forEach>
						</select>
					</td>
					<td class="tdR" width="7%">上级合伙人手机号:</td>
					<td width="7%">
						<input id=parentUsername name="parentUsername" maxlength="30" class='easyui-textbox' style="width: 160px;height: 24px;"/>
					</td>
					<td class="tdR" width="7%">上级合伙人姓名:</td>
					<td width="7%">
						<input id="parentName" name="parentName" maxlength="30" class='easyui-textbox' style="width: 160px;height: 24px;"/>
					</td>
				</tr>
	  			<tr>
	  				<td class="tdR"width="9%">合伙人平台注册时间:</td>
					<td colspan="2">
						<input id="min_change_partner_time" name="min_change_partner_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 125px;height: 24px;" data-options="required:false" placeholder ="--起始时间--"/>
						~
						<input id="max_change_partner_time" name="max_change_partner_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 125px;height: 24px;" data-options="required:false" placeholder ="--终止时间--"/>
					</td>
	  				<td class="tdR">汇中网注册时间:</td>
					<td  colspan="2">
						<input id="min_regist_time" name="min_regist_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 125px;height: 24px;" data-options="required:false" placeholder ="--起始时间--"/>
						~
						<input id="max_regist_time" name="max_regist_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 125px;height: 24px;" data-options="required:false" placeholder ="--终止时间--"/>
					</td>
					<td colspan="5">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun(datagrid);">清空</a>
					</td>
				</tr>
				<tr><td style="height: 8px;"></td></tr>
			</table>
		</form>
	</div>
	
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
 	<div id="toolbar" style="display: none;">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="toEditLoginUser();">编辑用户</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" onclick="disableFun();">禁用用户</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-tip',plain:true" onclick="enabledFun();">启用用户</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="toOrganization();">组织架构分配</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="editPartnerRelation();">变更关系</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-filter',plain:true" onclick="toExceptionLoginUser();">异常用户管理</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="doExport();">导出</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
		<%-- <a class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="toUpload();">导入</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px"> --%>
	</div>
	
	<!-- 组织架构分配 -->
	<div id="organization" class="easyui-dialog" title="组织架构分配" data-options="iconCls:'icon-save',closed:true" style="width:450px;height:250px;padding:10px">
	 	<form id="orgForm"  style="text-align: center;padding-top: 10%">
	 		<table border="0"  width="100%">
		 		<tr style="padding-top: 50px">
		 			<td width="7%"> *所属渠道:</td>
					<td width="7%">
						<input type="hidden" id="userIds" name="userIds">
						<input type="text" id="organization_channel_id" name="organization_channel_id" class="easyui-combobox" data-options="valueField:'organization_id',textField:'organization_name'" panelHeight="130px" editable="false" style="width: 175px;height: 24px;"/>
					</td>
				</tr>
				<tr>
					<td width="7%"> *所属区域:</td>
					<td width="7%">
	  					<input type="text" id="organization_area_id" name="organization_area_id" class="easyui-combobox" data-options="valueField:'organization_id',textField:'organization_name'" panelHeight="130px" editable="false" style="width: 175px;height: 24px;"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center" style="padding-top: 10px">
						<a class="easyui-linkbutton" id="submitButton"  iconCls="icon-ok" onclick="submitOrgForm();">提交</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="resetOrgForm();">取消</a>
					</td>
				</tr>
			</table>
  		</form>
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
					<td>上级合伙人机号 : </td>
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
	
	<!-- 导入功能表单 -->
  	<div id="upload" class="easyui-dialog" title="导入" data-options="iconCls:'icon-save',closed:true" style="width:400px;height:250px;padding:10px">
	 	<form id="fileForm" enctype="multipart/form-data" style="text-align: center;padding-top: 10%">
	 		<table border="0"  width="100%">
		 	<tr style="padding-top: 50px;">
				<td align="center"><input type="file" style="width: 40%" name="file" id="fileName"/> </td>
		 	</tr>
		 	<tr></tr>
		 	<tr>
		 	<td  align="center">
				<a class="easyui-linkbutton" id="submitButton"  iconCls="icon-ok" onclick="upload();">上传</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="resetFileForm();">取消</a>
			</td>
<!-- 				<td align="center"><input type="button" style="width: 30%" value="上传" onclick="upload()"/></td> -->
		 	</tr>
			</table>
  		</form>
  	</div>
  </body>
</html>
