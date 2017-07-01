/**
 * Created by Googler on 2017/5/2.
 */
var windowWidth;//页面宽度
var windowHeight;//页面高度
var rightBoxWidth;
$(document).ready(function () {
    rightBoxWidth = $("#right-list").width();//右侧宽度;//右侧宽度
    kuangjia();
    //点击按钮伸缩
    $("#BMenu-center").click(function () {
        if ($("#bottom-menu").hasClass("active")) {
            $("#bottom-menu").removeClass("active");
        } else {
            $("#bottom-menu").addClass("active");
        }
    });
    //二级菜单的切换
    $("#bottom-menu-02 ul").on("click", "li", function () {
        $(this).siblings().removeClass("active");
        $(this).addClass("active");
        var liNUM = $(this).index();
        $("#BMenu-left-top dl").eq(liNUM).siblings().removeClass("active");
        $("#BMenu-left-top dl").eq(liNUM).addClass("active");
    });
    //弹出右侧  方法：包含 “openList=right-list”
    $("#root-box").on("click", "[openList]", function () {

        var box_number = windowWidth - rightBoxWidth;
        $("#root-box").addClass("list-right");
        $("#right-list").addClass("active");
        $("#open-right").addClass("open-right-shen");
        $("#root-box").width(box_number);
        $("#right-list").css("left", box_number);
        /*			var openList = $(this).attr("openList");
         if(openList == "left-list"){
         $("#root-box").addClass("list-left");
         $("#left-list").addClass("active");
         }if(openList == "right-list"){

         }*/
    });

    $("#open-right").click(function () {
        var box_number = windowWidth - rightBoxWidth;
        if ($("#right-list").hasClass("active")) {
            $("#root-box").removeClass("list-right");
            $("#right-list").removeClass("active");
            $("#open-right").removeClass("open-right-shen");
            $("#root-box").width(windowWidth);
            $("#right-list").css("left", windowWidth);
        } else {//关闭状态
            $("#root-box").addClass("list-right");
            $("#right-list").addClass("active");
            $("#root-box").width(box_number);
            $("#right-list").css("left", box_number);
            $("#open-right").addClass("open-right-shen");
        }
    });
    /*////点击超链接弹出层
     $(document).on('click', 'a',function(){
     if($(this).attr("openMode") == "open_layer"){
     //alert()
     var the_link = $(this).attr("link_URL");
     var the_title = $(this).attr("tab_title");
     open_layer(the_title,the_link,"","");

     }
     });*/

////关闭弹出层
    $("#mask-layer").click(function () {
        // console.log("关闭");
        window.parent.$("#layer-loading").css("display", "block");
        window.parent.$("#layer-iframe").css("display", "none");
        $("#layerbox").animate({
            marginLeft: "-173px",
            marginTop: "-116px"
        }, 300);
        $("#layer-loading").animate({
            width: "300px",
            height: "160px",
        }, 300);
        $("#layer-iframe").animate({
            width: "300px",
            height: "160px",
        }, 300);
        setTimeout(function () {
            window.parent.$("#mask-layer").css("display", "none");
            window.parent.$("#layer-iframe").attr("src", "");
            window.parent.$("#layerbox").css("display", "none");
        }, 300);
    });

    $("#left-menu").on("mouseover", "li", function () {
        var li_length = $("#left-menu").children("li").length;
        var li_index = $(this).index();
        for (a = 0; a < li_length; a++) {
            var Atime = "all 0.5s  " + Math.abs(li_index - a) / 10 + "s";
            $("#left-menu li").eq(a).css("transition", Atime);
        }
    });
});
window.onresize = function () {
    kuangjia();
};
function kuangjia() {
    windowWidth = $(window).width();//页面宽度
    windowHeight = $(window).height();//页面高度
    $("#root-box").height(windowHeight);
    $("#container").height(windowHeight - 40);
    /**20170525 by ayd**/


    rightBoxWidth = $("#right-list").width();//右侧宽度

    if ($("#right-list").hasClass("active")) {
        var box_number = windowWidth - rightBoxWidth;
        $("#root-box").width(box_number);
        $("#right-list").css({"left": box_number, "height": windowHeight,});
    } else {
        $("#root-box").width(windowWidth);
        $("#right-list").css({"left": windowWidth, "height": windowHeight,});
    }
}


////弹出层
function open_layer(link_url, the_layer_title, Custom_width, Custom_height) {
    // console.log("打开");
    $("#mask-layer").css("display", "block");
    $.ajaxSetup({cache: false});
    $("#layer-iframe").attr({"src": link_url});
    $("#layerbox-title").text(the_layer_title);
    var layer_width = $("#layerbox").outerWidth();
    var layer_height = $("#layerbox").outerHeight();
    /*console.log(layer_width);*/
    $("#layerbox").css({
        "margin-top": -layer_height / 2,
        "margin-left": -layer_width / 2,
        "display": "block",
    });
    var layer_width_2;
    var layer_height_2;
    if (Custom_width == "" || Custom_width == null || Custom_width > windowWidth * 0.8) {
        layer_width_2 = windowWidth * 0.8 - 46;
    } else {
        layer_width_2 = Custom_width - 46;
    }
    if (Custom_height == "" || Custom_height == null || Custom_height > windowHeight * 0.8) {
        layer_height_2 = windowHeight * 0.8 - 78;
    } else {
        layer_height_2 = Custom_height - 78;
    }
    $("#layer-iframe").animate({
        width: layer_width_2,
        height: layer_height_2,
    }, 500);
    $("#layerbox").animate({
        marginLeft: -layer_width_2 / 2,
        marginTop: -layer_height_2 / 2
    }, 500);
    $("#layer-loading").animate({
        width: layer_width_2,
        height: layer_height_2,
    }, 500);
    $("#iframe_layer").animate({
        width: layer_width_2,
        height: layer_height_2 - 76,
    }, 500);

    /*	document.getElementById("layer-iframe").onload = function () {
     };*/
    setTimeout(function () {
        /*判断页面加载*/

        $("#layer-loading").css("display", "none");
        $("#layer-iframe").css("display", "block");
    }, 1000);
}


//
//console.log();


