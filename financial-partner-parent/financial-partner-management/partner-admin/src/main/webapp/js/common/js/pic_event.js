function hoverfunc(){
	//hover效果
    $(".pic_storage li a.preview_btn").hover(function() {
        $(this).children('div').removeClass('layer_show');
    }, function() {
        $(this).children('div').addClass('layer_show');
    });
    //点击预览
    $(".pic_storage li a.preview_btn").bind('click', function() {
        $(".lagre_image_wrap").fadeIn();
        var pic_src = $(this).css('background-image').substring(5,($(this).css('background-image').length-2));
        $(".preview_lagreimage").children('img').attr('src',pic_src);

    });
    $(".lagre_image_wrap")[0].addEventListener("click", function(){
        $(".lagre_image_wrap").fadeOut();
    },true) 
}