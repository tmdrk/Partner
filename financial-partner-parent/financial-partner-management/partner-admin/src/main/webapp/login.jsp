<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/header.jsp"%>
<%@ page import="org.springframework.security.web.WebAttributes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合伙人用户登录</title>
	<style type="text/css">
		body {
			background:url(${app}/images/loginBg.jpg) no-repeat;
			background-size: 100% 100%;
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		body,table,td,div {
			font-size: 12px;
			line-height: 24px;
			color: #b8c4ce;
		}
		
		.textfile {background:url(${app}/images/bg_login_textfile.gif) no-repeat left top; padding: 0px 2px; height: 29px; width: 143px; border: 0;line-height: 29px;}
		#chkcode {border:1px double #bfd7f0; height: 27px; width: 60px;line-height: 27px;}
	</style>
	<script type="text/javascript">
		function validate(){
			var flag=true;
			var username=$("#username").val();
			var password=$("#password").val();
			if(username.length==0){
				flag=false;
				showerror("usernamelaber","请输入用户名!");
				return flag;
			}else{
				$("#usernamelaber").html('');
			}
			if(password.length==0){
				flag=false;
				showerror("passwordlaber","请输入密码!");
				return flag;
			}else{
				$("#passwordlaber").html('');
			}
			
			return flag;
		}
	
		function login(){
			if(validate()){
				$("#userlogin").submit();
			}
		}
		
		function resets(){
			$("#username").textbox('setValue','');
			$("#password").textbox('setValue','');
			$("#chkcode").val("");
		}
		//回车登录
		$(document).keydown(function(event){ 
			if(event.keyCode==13){
				login();
			}
		}); 
		
		function changeCheckIMG(){
			$("#loginimg").attr("src","${app }/checkimg.jsp?timestamp=" + new Date());
		}
		
		function showerror(id,info){
			$("#"+id).html("<font color='red'>× "+info+"</font>");
		}
	</script>
	
</head>
<body>
<table width="95" border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td>
   <!--  <img src="${app}/images/sms_login.jpg"  style="margin-top:150px"/> -->
   		<h3 style="color: #fff;font-size: 34px;margin-top: 100px;text-align: center;margin-bottom: 50px;">合伙人管理平台</h3>
    </td>
  </tr>
  <tr>
    <td>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td style="background:#fff;background-size:410px 185px;background-repeat:no-repeat;">
	         <form id="userlogin"  method="post" action="${app }/admin/j_spring_security_check">
		        <table width="500" border="0" align="center" cellpadding="0" cellspacing="0" style="padding-top: 6px;">
		          <tr>
		        	<td>
		        		<h4 style="color: #4acaff;font-size: 18px;background:url(${app}/images/login-line.png) center center no-repeat;text-align: center;margin: 20px 0;">用户登录</h4>
		        	</td>
		          </tr>
		          <tr>
		            <!-- <td align="right"  width="90">用户名：</td> -->
		            <td>
			            <div style="border: 1px solid #e9ecee;height: 36px;width: 278px;margin: 0 auto;line-height: 36px;padding: 0 10px;margin-bottom: 15px;">
				            <label>
			                	用户名<input id="username" name="username" type="text" class="" iconCls='icon-man' value="${username}" style="border: none;outline: none;float: right;width: 230px;margin-top: 5px;padding:5px 0;"/>
			                </label>
		                </div>
	                </td>
	                <!-- <td>&nbsp;</td> -->
		          </tr>
		          <tr>
		           <!--  <td align="right"  width="90">密　码：</td>
		            <td>
			            <label>
			            	<input id="password" name="password" type="password" class="easyui-textbox" iconCls='icon-lock' style="width: 165px;height: 28px;" autocomplete="off" disableautocomplete/>
			            </label>
		            </td>
		            <td><label id="passwordlaber"> </label></td> -->
		            <td>
			            <div style="border: 1px solid #e9ecee;height: 36px;width: 278px;margin: 0 auto;line-height: 36px;padding: 0 10px;margin-bottom: 15px;">
				            <label>
			                	密码<input id="password" name="password" type="password" class="" iconCls='icon-man' value="${username}" style="border: none;outline: none;float: right;width: 230px;margin-top: 5px;padding:5px 0;"/>
			                </label>
		                </div>
	                </td>
		          </tr>
		          <tr height="35px">
		            <!-- <td align="right" width="90">验证码：</td> -->
		            <td>
			            <div  style="border: 1px solid #e9ecee;height: 36px;width: 168px;margin: 0 auto;line-height: 36px;padding: 0 10px;float: left;margin-left: 100px;margin-bottom: 15px;">
			            	<!-- <div style="width:70px;height: 25px;float:left;margin-right: 12px;"> -->
			              	验证码<input name="check_code" style="background:transparent;border: none;outline: none;float: right;width: 120px;margin-top: 5px;padding:5px 0;" id="chkcode" class="" maxlength="4" />
				            <!-- </div> -->
	      					
			            </div>
			            <div style="width:98px;height: 36px;float:left;margin-left: 10px;border: 1px solid #333;">
      						<img id="loginimg" name="loginimg" style="width:98px;height:36px;cursor:pointer;" src="${app}/checkimg.jsp" title='看不清，单击更改'  onclick="javascript:changeCheckIMG();" border="0" />
   						</div>
		            </td>
		            <!-- <td>&nbsp;</td> -->

		          </tr>
		          <tr>
		          	<!-- <td align="right" width="90">&nbsp;</td> -->
		          	<td colspan="2">
		          		<label id="chkcodelaber" style="color: #ff5a70;margin-left: 100px;">
		            		${SPRING_SECURITY_LAST_EXCEPTION.message}
		            	</label> 
		          	</td>
		          </tr>
		          <tr>
		            <!-- <td>&nbsp;</td> -->
		            <td>
			            <label>
			              <input onclick="login()" value="登录" type="button" style="cursor:pointer;width:300px; height:42px;border:0;background:#4acaff;display: block;margin:30px auto;color: #fff;font-size: 16px;"/>
			             <!--  &nbsp; -->
			             <!--  <input onclick="resets()" type="button" style="cursor:pointer;width:64px; height:25px;  border:0;background:url('${app}/images/btn_reset.gif')"/> -->
			            </label>
		            </td>
		            <!-- <td>&nbsp;</td> -->
		          </tr>
		        </table>
	          </form>
	        </td>
	      </tr>
	      <tr><td><br><br><br></td></tr>
	      <!-- <tr align="center"><td>版权所有 ：汇天下（天津）电子商务有限公司</td></tr> -->
    	</table>
    </td>
  </tr>
</table>
</body>
</html>