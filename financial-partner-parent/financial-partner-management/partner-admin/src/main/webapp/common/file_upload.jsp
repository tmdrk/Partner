<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.huizhongcf.partner.admin.constant.Constants"%>

<%
	request.setAttribute("app",request.getContextPath());
%>

<link href="${app}/js/upload/css/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${app}/js/upload/ajaxupload.js"></script>	
<script type="text/javascript" src="${app}/js/upload/swfupload.js"></script>
<script type="text/javascript" src="${app}/js/upload/swfupload.queue.js"></script>
<script type="text/javascript" src="${app}/js/upload/fileprogress.js"></script>
<script type="text/javascript" src="${app}/js/upload/handlers.js"></script>
 
 <!-- 初始化swfupload 对象-->
 <script type="text/javascript">
		var upload;
		window.onload = function() {
			upload = new SWFUpload({
				// Backend Settings
				upload_url: "${app}/admin/upload/fileUpload_upload.shtml?path=${path}&creator=${creator}",
				file_post_name: "Filedata",
				// File Upload Settings
				file_size_limit : "2048",	// 2048k
				file_types : "<%=Constants.UPLOAD_FILE_TYPE%>",
				file_types_description : "All Files",
				file_upload_limit : "100",  //最多100个附件上传
				file_queue_limit : "0",

				// Event Handler Settings (all my handlers are in the Handler.js file)
				file_dialog_start_handler : fileDialogStart,
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,

				// Button Settings
				button_image_url : "${app}/js/upload/XPButtonUploadText_61x22.png",
				button_placeholder_id : "spanButtonPlaceholder",
				button_window_mode : "Opaque",
				button_width: 61,
				button_height: 22,
				
				// Flash Settings
				flash_url : "${app}/js/upload/swfupload.swf",
				
				custom_settings : {
					progressTarget : "fsUploadProgress",
					cancelButtonId : "btnCancel"
				},
				debug: false
			});
		}
			

		 //隐藏域追加上传的值
	     function attachmentAppend(attachmentId){
	    	 var attachmentIds = $("#attachmentIds").val();
	    	 if("" == attachmentIds){
	    		 attachmentIds += attachmentId;
		     }else{
		    	 attachmentIds += "," + attachmentId;
			 }
			 $("#attachmentIds").val(attachmentIds);
		 }

	     //清除文本域中删除的那个值
		 function attachmentClear(delid){
			 var attachmentIds = $("#attachmentIds").val() + ",";
			 attachmentIds = attachmentIds.replace(delid+",","").substr(0,attachmentIds.replace(delid+",","").length-1);
			 $("#attachmentIds").val(attachmentIds);
		 }
	     
	     function uploadSuccess(file, serverData) {
			try {
				var progress = new FileProgress(file, this.customSettings.progressTarget);
				progress.setComplete();
				if(startWith(serverData,'success')){
					progress.setStatus("<span>上传成功</span>!");
					var res=serverData.split(",");
					var fileAddress = "<li id='li_" + res[2] + "'><a style='text-decoration:none;' target='_blank' href='"+res[1]+"' >" + res[3] + "</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
						"<a style='text-decoration:none;' href='javascript:void(0);' onclick=\"delA('"+res[1]+"','"+res[2]+"');\">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<a style='text-decoration:none;' href='javascript:void(0);' onclick=\"downloadFile('"+res[1]+"','"+res[3]+"');\">下载</a></li>";  
					$("#fsUploadProgress .files").append(fileAddress + "</br>");
					attachmentAppend(res[2]);
				}else{
					progress.setStatus("<span>" + serverData + "</span>!");
				}	
				progress.toggleCancel(false);
			} catch (ex) {
				this.debug(ex);
			}
		}

	    function fileQueueError(file, errorCode, message) {
			try {
				if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
					alert("您已经上传了太多的附件!\n" + (message === 0 ? "已经达到限制了." : "You may select " + (message > 1 ? "up to " + message + " files." : "0 file.")));
					return;
				}
		
				var progress = new FileProgress(file, this.customSettings.progressTarget);
				progress.setError();
				progress.toggleCancel(false);
		
				switch (errorCode) {
				case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
					progress.setStatus("文件太大了 请缩小文件!");
					this.debug("Error Code: 文件太大了 请缩小文件!, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
					break;
				case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
					progress.setStatus("Cannot upload Zero Byte files.");
					this.debug("Error Code: Zero byte file, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
					break;
				case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
					progress.setStatus("错误的文件类型!");
					this.debug("Error Code: Invalid File Type, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
					break;
				case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
					alert("You have selected too many files.  " +  (message > 1 ? "You may only add " +  message + " more files" : "You cannot add any more files."));
					break;
				default:
					if (file !== null) {
						progress.setStatus("Unhandled Error");
					}
					this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
					break;
				}
			} catch (ex) {
		        this.debug(ex);
		    }
		}
</script>