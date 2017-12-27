<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/zTree.jsp"%>
	<script type="text/javascript">
		var zTreeObj = null;
		$(document).ready(function(){
			var setting = {
					data: {
						key:{name: 'dept_name'},
						simpleData: {
							enable: true,
							idKey: 'dept_id',
							pIdKey: 'parent_id'
						}
					},
					view: {
						showTitle: false,
						expandSpeed: 0
					},
					callback: {onClick:zTreeOnClick}
			};
			
			var zTreeNodes = null;
			//查询树节点
			$.ajax({
				url : "${app}/department/departments",
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
			zTreeObj.expandAll(false);//全部不展开
			var node = zTreeObj.getNodeByParam("dept_id", "${deptId}", null);
			zTreeObj.selectNode(node);//选中节点
		});
		
		//节点选中事件
		function zTreeOnClick(event, treeId, treeNode){
			$("#selectDeptId").val(treeNode.dept_id);
		}
		
	</script>
	<input type="hidden" id="selectDeptId" name="selectDeptId" value="${deptId}"/>
	<ul id="treeDept" class="ztree"></ul>
