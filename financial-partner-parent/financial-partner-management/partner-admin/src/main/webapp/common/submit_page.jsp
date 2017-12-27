<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<style>
		/*遮罩层样式*/
		#transparent-div{display:none;position:absolute;top:0;left:0;right:0;width:100%;background-color:#ccc;filter:Alpha(style=6,opacity=60,finishOpacity=60);opacity:0.6;z-index:9998;line-height:0;overflow:hidden}
		/*弹出框样式*/
		#befsigned{border:#A2B7CA 2px solid; width:150px; padding:10px 15px 3px;; margin:60px auto; display:none; background:#fff;position:absolute; left:40%;z-index:9999;}
	</style>
	<script type="text/javascript">
		function openMask(){
			var bgObj = document.getElementById("transparent-div");
			var agrObg = document.getElementById("befsigned");
			//如果屏幕高度大于body高度，设置为屏幕高度，否则设置为body高度
			if((window.screen.height - 342) < document.body.scrollHeight){
				bgObj.style.height = document.body.scrollHeight + "px" ;
			}else{
				bgObj.style.bottom='0';
			}
			agrObg.style.top = 200 + "px";
			bgObj.style.display='block';
			agrObg.style.display='block';
		}
		function closeMask(){
			document.getElementById("transparent-div").style.display='none';
			document.getElementById("befsigned").style.display='none';
		}
	</script>
	<div id="transparent-div"></div>
	<div id="befsigned">
		<img style="padding-bottom: 7px;" src="${app}/images/loading.gif" align="middle"/>
		<span>&nbsp;&nbsp;正在处理，请稍候<img style="padding-bottom: 7px;" src="${app}/images/loading_ellipsis.gif" align="middle"/></span>
	</div>