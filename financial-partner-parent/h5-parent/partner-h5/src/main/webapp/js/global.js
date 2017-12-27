
//兼容android： 软键盘弹出，获得焦点的输入框在可见区域显示
    if (/android/i.test(navigator.userAgent)){
       window.addEventListener("resize", function() {
          if(document.activeElement.tagName=="INPUT" || document.activeElement.tagName=="TEXTAREA") {
             window.setTimeout(function() {
                document.activeElement.scrollIntoViewIfNeeded();
                $('.btn.abs_b').removeClass('.abs_b')
             },0);
          }
       })
    }


/* 弹出消息 */
$(".msg .close").click(function(){
	$(this).parent('.msg').remove();
});

if($('.orderDetail li span').hasClass('Appointment')){
    $('.msg').show();
}

