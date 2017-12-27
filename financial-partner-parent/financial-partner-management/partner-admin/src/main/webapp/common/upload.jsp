<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

StringBuffer uploadUrl = new StringBuffer("http://");
uploadUrl.append(request.getHeader("Host"));
uploadUrl.append(request.getContextPath());
uploadUrl.append("/testStrutsUpload.action");
%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="${app}/js/uploadify/uploadify.css">
        <link rel="stylesheet" type="text/css" href="${app}/js/uploadify/upload_style.css">
        <script type="text/javascript" src="${app}/js/common/js/jquery-1.11.1.min.js"></script> 
        <script type="text/javascript" src="${app}/js/uploadify/jquery.uploadify.min.js"></script>       
    </head>
    <body>
    <!--大图预览 -->
    <div class="lagre_image_wrap">
        <div class="lagre_image_layer">
            <span></span>
           <div class="preview_lagreimage">
               <img src="" alt="" />
           </div> 
        </div>        
    </div>
        <input id="file_upload" name="image" type="file" multiple="true">
        <div class="pic_storage">
                <ul class="pic_list" id="hospic">
                    <li >
                        <div>
                            <b class="upload_cancel" onclick="javascript:image_delete(this);"></b>
                            <a href="javascript:;" id="1"><div class="layer_show">预览</div></a>
                            <span class="pic_srcname">1234.jpg1234.jpg</span>                           
                        </div>

                    </li>  
                     <li id="2">
                        <div>
                            <b class="upload_cancel" onclick="javascript:image_delete(this);"></b>
                            <a href="javascript:;" >
                                
                                <div class="layer_show">é¢è§</div>
                            </a>
                            <span class="pic_srcname" >1234.jpg1234.jpgv</span>                           
                        </div>

                    </li>
                     <li>
                        <div>
                            <b class="upload_cancel" onclick="javascript:image_delete(this);"></b>
                            <a href="javascript:;" id="3" style="background-image:url(small.jpg.gif);"><div class="layer_show">é¢è§</div></a>
                            <span class="pic_srcname">1234.jpg1234.jpgv</span>                           
                        </div>

                    </li>  
                </ul>
                       

        </div>
    </body>
    <script type="text/javascript">
        $(function(){
            //hover效果
            $(".pic_storage li a").hover(function() {
                $(this).children('div').removeClass('layer_show');
            }, function() {
                $(this).children('div').addClass('layer_show');
            });
            //点击预览
            $(".pic_storage li a").bind('click', function() {
                $(".lagre_image_wrap").fadeIn();
                var pic_src = $(this).css('background-image').substring(5,($(this).css('background-image').length-2));
                $(".preview_lagreimage").children('img').attr('src',pic_src);

            });
            $(".lagre_image_wrap")[0].addEventListener("click", function(){
                $(".lagre_image_wrap").fadeOut();
            },true)
        })
        //图片展示
        function loadImage(data){
            for(var i=0;i<data.url.length;i++){
                $("#hospic").append('<li id="piclis"'+data.id+'>'
                        +'<div>'
                            +'<b class="upload_cancel" onclick="javascript:image_delete(this);"></b>'
                            +'<a href="javascript:;" >'
                                +'<div class="layer_show">预览</div>'
                            +'</a>'
                            +'<span class="pic_srcname" >'+data.filename+'</span>'                           
                        +'</div>'
                    +'</li>')
            }
        }
        //图片删除
        function image_delete(obj){
            event.stopPropagation();
            if (confirm("确定删除吗")){
                //删除请求 
                /*jQuery.ajax({
                       url:"", 
                       dataType:"json", 
                       type:"GET",  
                       data:{
                           },
                       error: function(data){

                        },
                       success: function(data) {
                            $(obj).closest('li').remove();
                        } 
                });*/  
                $(obj).closest('li').remove();

                
            }
        }
        //初始化组件
        $(function() {

            $("#file_upload").uploadify({  
                'swf' : 'uploadify/uploadify.swf',//控件flash文件位置   
                'uploader':'uploadify/uploadify.php',   
                'width' : '120',//按钮宽度   
                'height' : '30',//按钮高度
                'fileObjName' : 'image',  
                'buttonText' : '上传',
                'multi' : true,
                'fileSizeLimit':'204800000',
                 onUploadSuccess : function(file, data, response) {  
                     loadImage(data);
                },  
                onUploadError : function(file, errorCode, errorMsg, errorString) { 
                }  
            });  
        });


    </script>    
</html>