/**
 * Created by xuechuang on 2017/12/14.
 */
;$(function () {
//    显示、隐藏密码
    var flag = true;
    function showHide(objTri, inputType, imgSrc) {
        $(objTri).prevAll('input').attr('type', inputType);
        $(objTri).css({
            'background-image': 'url(' + imgSrc + ')',
            'background-size': 'contain'
        });
        flag = !flag;
    }
    $('.password i').on('click',function () {
        flag ? showHide(this, 'text','../images/show.png'): showHide(this, 'password','../images/hide.png')
    });

    //短信验证码倒计时
    function codeCutdown(sec,id){
        $("#" + id).text(sec + "s");
        var secInterval = setInterval(function(){
            if(sec == 1){
                clearInterval(secInterval);
                $("#" + id).text("重新获取").removeClass("btn-disable");
            } else {
                sec--;
                $("#" + id).text(sec + "s");
            }
        },1000);
    }
//    获取验证码
    $('.get-code').on('click', function () {
        if($(this).hasClass("btn-disable")){
            return;
        }
        $(this).addClass("btn-disable");
        codeCutdown(60,'get-code');
    });

    //验证码
    var vCode;
    function createCode() {
        vCode='';
        var codeLength = 4;//验证码长度
        var checkCode = $('#checkCode');
        var validCode= $('#validCode');
        var selectChar = [0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','d','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
        for(var i=0; i<codeLength; i++){
            var charIndex = Math.floor(Math.random()*62);
            vCode+= selectChar[charIndex];
        }
        if(checkCode){
            checkCode.text(vCode);
            validCode.blur();
        }
        validCode.val('');
    }
    createCode();
    $('#checkCode').on('click', function () {
        createCode();
    });

})