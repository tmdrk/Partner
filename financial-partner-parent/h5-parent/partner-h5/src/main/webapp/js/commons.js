/**
 *
 * Description:
 *
 * @author LiuXianfa
 * @date 2017/12/21
 */

$(function () {
    // 1、文档加载完成后，给页面的body append上弹框的html代码
    var html = "<!-- 有按钮的弹框(js append html) --><div id='popBox_new' class='fn-hide'><div class='pb-shade'></div><div class='pb-con'><h2><span class='pbtit'></span><div><p class='tel'></p><p class='content'></p></div></h2><div class='pbc-bottom'><div class='pbc-text'></div><div class='pbc-cont'><div class='popagree clearfix'><a href='javascript:void(0)' class='no fn-hide closeBtn'></a><a href='javascript:void(0)' class='no sureBtn'></a></div></div></div></div></div>";
    $("body").append(html);
});

/**
 * 定义弹窗的方法
 * title 弹窗的标题
 * content 弹窗的内容
 * val 确定按钮（是点击此按钮，会执行回调方法）
 * close 是否有关闭按钮;'1'有关闭按钮;'0'无关闭按钮(默认)
 * closetext 关闭按钮显示文字
 * callback 点击确定的回调方法
 * tel 电话号码
 */
function popbox(opt) {
    var st = $(document).scrollTop();
    var height = $(window).height();
    var obj = $("#popBox_new");
    obj.removeClass('fn-hide');
    var opt = opt || {};
    opt.tit = opt.title || null;
    opt.val = opt.val || '确定';
    opt.tel = opt.tel || null;
    opt.name = opt.name || null;
    opt.content = opt.content || null;
    opt.close = opt.close || '0';
    opt.closetext = opt.closetext || "取消";
    opt.callback = opt.callback || function () {
    };
    obj.removeClass('fn-hide');
    obj.find('.pbtit').html(opt.title);
    obj.find('.tel').html(opt.tel);
    if (opt.name) {
        obj.find('.name').html('姓名：' + opt.name);
    } else {
        obj.find('.name').html('');
    }
    if (opt.content) {
        obj.find('.content').html(opt.content);
    } else {
        obj.find('.content').html('');
    }
    obj.find('.sureBtn').text(opt.val);
    var obj1 = obj.find('.pb-con');
    var height1 = obj1.height();
    obj1.css('top', (height - height1) / 2 - 40);
    var agree = obj.find('.popagree');
    var closeBtn = obj.find('.closeBtn');
    if (opt.tel) {
        $('.sureBtn').attr('href', 'tel://' + opt.tel)
    } else {
        $('.sureBtn').attr('href', 'javascript:;')
    }
    if (opt.close == '1') {
        agree.addClass('hasclose');
        closeBtn.text(opt.closetext).removeClass('fn-hide');
    } else {
        agree.removeClass('hasclose');
        closeBtn.addClass('fn-hide');
    }
    closeBtn.click(function () {
        obj.addClass('fn-hide');
        agree.removeClass('hasclose');
        closeBtn.removeClass('fn-hide');
        if (opt.callback && typeof opt.callback == "function") {
            opt.callback = null;//callback执行完以后就移除
        }
        ;
    });
    obj.find('.sureBtn').click(function () {
        obj.addClass('fn-hide');
        if (opt.callback && typeof opt.callback == "function") {
            opt.callback();
            opt.callback = null;
        }
        ;
    });
}

/**
 * 把token传给原生安卓或ios
 */
function setTokenToMobile(token) {

}

/**
 * 从安卓或ios获得token
 */
function getTokenFromMobile() {

}

/**
 * 从安卓或ios删除token
 */
function removeTokenFromMobile() {

}

function setToken(token, cookiePath) {
    var date = new Date();
    date.setDate(date.getDate() + 7);
    $.cookie("ACCESS_TOKEN", token, {"path": cookiePath, "expires": date});

    setTokenToMobile(token)
}

function getToken() {
    var token = $.cookie("ACCESS_TOKEN");
    if (!token) {
        token = getTokenFromMobile();
    }
    return token;
}

function removeToken(cookiePath) {
    $.cookie("ACCESS_TOKEN", null, {"path": cookiePath});
    removeTokenFromMobile();
}


//错误提示框
function Popup(popupData) {
    var txt1 = "<div class='ui-Popup'><div class='PopupBg'></div><div class='PopupCC'>" + popupData + "</div></div>";
    $('body').append(txt1);
    var paL = parseInt($(".PopupCC").css('padding-left'));
    var paT = parseInt($(".PopupCC").css('padding-top'));
    $(".ui-Popup").width($(window).width());
    $(".ui-Popup").height($(window).height());
    $(".PopupBg").width($(window).width());
    $(".PopupBg").height($(window).height());
    $(".PopupCC").css("margin-top", -($(".PopupCC").height() / 2) - paT);
    setTimeout(function () {
        $(".ui-Popup").css("display", "none");
        $(".ui-Popup").remove();
    }, 3000);
}
