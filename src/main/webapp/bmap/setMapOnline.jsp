<%@ page language="java" pageEncoding="utf-8" %>
<script type="text/javascript" src="${systemConfig.mapUrl}"></script>
<script>
    var map;//地图声明初始化
    /*** 在线部分****/
    map = new AMap.Map("container", {
        resizeEnable: true,
        zoom:15,
        center: [114.207177,30.563003]
    });
</script>