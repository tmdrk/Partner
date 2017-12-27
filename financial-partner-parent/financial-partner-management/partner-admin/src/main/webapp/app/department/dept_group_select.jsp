<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/zTree.jsp"%>
	<script type="text/javascript">
		var zTreeObj = null;
		$(document).ready(function(){
			var setting = {
				check: {
					enable: true
				},
				data: {
					key:{name: 'dept_name'},
					simpleData: {
						enable: true,
						idKey: 'dept_id',
						pIdKey: 'parent_id'
					}
				}
			};
			
			var zTreeNodes = null;
			//查询树节点
			$.ajax({
				url : "${app}/department/listTreeDeptGroup/${employeeId}",
				type : "post",
				dataType : "json",
				async : false,
				success : function(msg){
					if(msg != null && msg.length != 0){
						zTreeNodes = msg;
					}
				},		
				error : function(){
					$.messager.alert("提示信息","系统错误！","info");
				}
			});
			
			zTreeObj = $.fn.zTree.init($("#treeDept"), setting, zTreeNodes);
			zTreeObj.expandAll(true);//全部不展开
		});
	</script>
	
	<ul id="treeDept" class="ztree"></ul>
