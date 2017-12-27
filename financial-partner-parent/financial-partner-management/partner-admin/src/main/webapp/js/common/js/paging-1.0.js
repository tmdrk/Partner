$(document).ready(function(){
	paging();
});

function pagingFun(page){
	$("#page").val(page);
	$("#form0").submit();
}

function appendPageNum(i){
	if(pageNo == i){
		$("#pageNum").append(
			"<span class='current'>"+i+"</span>"
		);
	}else{
		$("#pageNum").append(
			"<a href='#' onclick='pagingFun("+i+");'>"+i+"</a>"
		);
	}
}

function pageJumpToFun(){
	var pText = $("#pageText");
	//pText.bind("keydown", function(e){
		//if(e.which == 13){
			if(pText.val() != null && !isNaN(pText.val())){
				if(pText.val() < 1){
					pagingFun(1);
				}else if(pText.val() > totalPages){
					pagingFun(totalPages );
				}else{
					pagingFun(pText.val());
				}
			}
		//}
	//});
}

function appendPageAround(){
	if(totalRecords == 0){
		$("#pageHome").attr("class","disabled").html("首页");
		$("#pagePrev").attr("class","disabled").html("上一页");
		$("#pageNext").attr("class","disabled").html("下一页");
		$("#pageEnd").attr("class","disabled").html("尾页");
		$("#pageJumpTo").remove();
	}else{
		if(pageNo == 1){
			$("#pageHome").attr("class","disabled").html("首页");
			$("#pagePrev").attr("class","disabled").html("上一页");
		}else{
			$("#pageHome").append(
				"<a href='#' onclick='pagingFun(1);'>首页</a>"
			);
			$("#pagePrev").append(
				"<a href='#' onclick='pagingFun("+(pageNo-1)+");'>上一页</a>"
			);
		}
		if(totalPages == 1 || pageNo == totalPages){
			$("#pageNext").attr("class","disabled").html("下一页");
			$("#pageEnd").attr("class","disabled").html("尾页");
		}else {
			$("#pageNext").append(
				"<a href='#' onclick='pagingFun("+(pageNo+1)+");'>下一页</a>"
			);
			$("#pageEnd").append(
				"<a href='#' onclick='pagingFun("+totalPages+");'>尾页</a>"
			);
		}
	}
}
//分页
function paging(){
	if(pageNo <= 5){
		appendPageAround();
		if(totalPages > 10){
			for(var i=1; i<=pageSize; i++){
				appendPageNum(i);
			}
		}else{
			for(var i=1; i<=totalPages; i++){
				appendPageNum(i);
			}
		}
	}else{
		appendPageAround();
		if(totalPages - pageNo > 5){
			for(var i=pageNo - 4; i <= pageNo+5; i++){
				appendPageNum(i);
			}
		}else{
			if(totalPages <= 10){
				for(var i=1; i<=totalPages; i++){
					appendPageNum(i);
				}
			}else{
				if(totalPages-pageNo <= 5){
					for(var i=totalPages-10; i<=totalPages; i++){
						appendPageNum(i);
					}
				}else{
					for(var i=pageNo-4; i<=totalPages; i++){
						appendPageNum(i);
					}
				}
			}
		}
	}
}