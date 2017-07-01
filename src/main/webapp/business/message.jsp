<%@ page language="java" pageEncoding="UTF-8" %>
<script type="text/javascript" src="${ctx}/js/common/reconnecting-websocket.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common-format.js"></script>
<script type="text/javascript" src="${ctx}/js/business/Msg.js"></script>
<script>
    var path = '${btx}';
    var zdxxdm = '${applicationSessionObject.zdxxdm}';//站点信息代码
    var websocketPath = "ws://" + path + "/wk/ws.do?zdxxdm=" + zdxxdm;
    var websocket = new ReconnectingWebSocket(websocketPath);

    $(document).ready(function () {
        /**
         **websocket连接
         **/
        websocket.onopen = function (event) {

        };
        /**
         **接受消息
         **/
        websocket.onmessage = function (event) {

        };
        /**
         **websocket发生错误
         **/
        websocket.onerror = function (event) {
            alert("连接错误！ ");
        };
        /**
         **websocket关闭
         **/
        websocket.onclose = function (event) {
            alert("地图集群服务器故障！");
            location.reload();
        };
    });	//JQUERY结束


    /**
     **退出
     **/
    function logout() {
        window.location.href = "${ctx}/login.do?method=logout";
    }
</script>
