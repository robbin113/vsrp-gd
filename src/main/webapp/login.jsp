<!DOCTYPE html>
<html lang="ZH">
<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ZH">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>ZXIT.VSRP-α-1.0526</title>
</head>
<!-- External CSS -->
<link rel="stylesheet" href="css/login.css">
<body>
<div class="bodyBox" id="bodyBox">
    <div class="box" id="box">
        <div class="login_logo"></div>
        <div class="login_title"></div>
        <form action="/vsrp-gd/login.do?method=login" id="actionform" method="post">
            <div class="login_user">
                <div class="Ainput inputA">站点:<input type="text" id="zdxxdm"></div>
                <div class="Ainput inputA">值班员:<input type="text" id="zby"></div>
            </div>
            <button type="button" class="login_button" id="login">登 录</button>
        </form>
    </div>

    <div class="BQ">深圳市中兴信息技术有限公司</div>

    <!--------------------弹出 提示信息-------------开始-------->
    <div class="prompt" id="prompt">
        <div class="prompt-ico"></div>
        <div class="prompt-message"></div>
        <div class="prompt-closed"></div>
    </div>
</div>


<script src="js/jquery/jquery-3.1.1.min.js"></script>

<script>
    $(document).ready(function () {

        if ('${err}' != "") {
            $("#prompt").addClass("error");
            $("#prompt .prompt-message").text('${err}');
            $("#prompt").animate({
                top: '100px',
                opacity: '1',
            });
            setTimeout(function () {
                $("#prompt").removeClass("error");
                $("#prompt").css({"top": "40px", "opacity": "0",});
            }, 2000);
        }
        $("#prompt .prompt-closed").click(function () {

            $("#prompt").removeClass("error");
            $("#prompt").css({"bottom": "0px", "opacity": "0",});
        });


        $("#login").click(function () {
            login();
        });

        document.onkeydown = function (event) {
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if (e.keyCode == 13) {//监控回车
                loing();
            }
        }
    });

    function login() {
        var zby = $("#zby").val();
        var zdxxdm = $("#zdxxdm").val();
        var formAction = $("#actionform").attr("action");
        var action = formAction + "&zdxxdm="+zdxxdm + "&zby=" + zby;
        $("#actionform").attr("action", action);
        $("#actionform").submit();
    }

</script>
</body>
</html>