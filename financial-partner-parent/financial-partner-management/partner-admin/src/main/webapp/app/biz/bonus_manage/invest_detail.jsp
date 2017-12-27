<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
<title>订单详情</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="invest detail">
<%@ include file="/common/header.jsp"%>
</head>

<body style="background: white;">
	<form id="supplierEditForm" class="easyui-form" method="post" modelAttribute="formBeans">
		<table id="addSupplier" class="tableForm" border="1" width="100%">
			<tr style="font-weight: bold;">
				<td class="tdL" colspan="4" style="padding-left: 18px;">合同信息</td>
			</tr>
			<tr>
				<td class="tdR" width="15%">合同编号:</td>
				<td width="35%" style="padding-left: 5px;">
					${investDetail.contract_no}
				</td>
				<td class="tdR" width="15%">交易金额(元):</td>
				<td width="35%" style="padding-left: 5px;">
					${investDetail.invest_amount}
				</td>
			</tr>
			<tr>
				<td class="tdR">下单时间:</td>
				<td style="padding-left: 5px;">
					${investDetail.buy_date}
				</td>
				<td class="tdR">订单状态:</td>
				<td style="padding-left: 5px;">
					${investDetail.invest_status}
				</td>
			</tr>
			<tr>
				<td class="tdR">起息日期:</td>
				<td style="padding-left: 5px;">
					${investDetail.pay_date}
				</td>
				<td class="tdR">到期日期:</td>
				<td style="padding-left: 5px;">
					${investDetail.due_date}
				</td>
			</tr>
			<tr>
				<td class="tdR">赎回时间:</td>
				<td colspan="3" style="padding-left: 5px;">
					${investDetail.redemption_date}
				</td>
			</tr>
			<tr style="font-weight: bold;">
				<td class="tdL" colspan="4" style="padding-left: 18px;">产品信息</td>
			</tr>
			<tr>
				<td class="tdR" width="15%">产品名称:</td>
				<td width="35%" style="padding-left: 5px;">
					${investDetail.product_name}
				</td>
				<td width="15%" class="tdR">出借期限(月):</td>
				<td width="35%" style="padding-left: 5px;">
					${investDetail.product_term}
				</td>
			</tr>
			<tr>
				<td class="tdR">折标系数:</td>
				<td style="padding-left: 5px;">
					<c:choose>
						<c:when test="${investDetail.discount_molecular==null||investDetail.discount_denominator==null}"></c:when>
						<c:otherwise>
							${investDetail.discount_molecular}/${investDetail.discount_denominator}
						</c:otherwise>
					</c:choose>
				</td>
				<td class="tdR">回款方式:</td>
				<td style="padding-left: 5px;">
					${investDetail.pay_back_type}
				</td>
			</tr>

			<tr style="font-weight: bold;">
				<td class="tdL" colspan="4" style="padding-left: 18px;">用户信息</td>
			</tr>
			<tr>
				<td class="tdR" width="15%">用户ID:</td>
				<td width="35%" style="padding-left: 5px;">
					${investDetail.user_id}
				</td>
				<td width="15%" class="tdR">用户姓名:</td>
				<td width="35%" style="padding-left: 5px;">
					${investDetail.real_name}
				</td>
			</tr>
			<tr>
				<td class="tdR">用户手机号:</td>
				<td width="35%" style="padding-left: 5px;">
					${investDetail.username}
				</td>
				<td class="tdR">用户角色:</td>
				<td width="35%" style="padding-left: 5px;">
					${investDetail.user_role}
				</td>
			</tr>
			<tr>
				<td width="15%" class="tdR">邀请人姓名:</td>
				<td width="35%" style="padding-left: 5px;">
					${investDetail.superior_real_name}
				</td>
				<td width="15%" class="tdR">邀请人角色:</td>
				<td width="35%" style="padding-left: 5px;">
					${investDetail.superior_user_role}
				</td>
			</tr>
			<tr>
				<td class="tdR">邀请人手机号:</td>
				<td width="35%" style="padding-left: 5px;">
					${investDetail.superior_username}
				</td>
				<td class="tdR">邀请人推荐码:</td>
				<td width="35%" style="padding-left: 5px;">
					${investDetail.superior_recommend_code}
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
