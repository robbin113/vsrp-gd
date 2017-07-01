<%@ page language="java" pageEncoding="utf-8" %>
<%--系统配置--%>
<script type="text/javascript" src="${ctx}/systemConfig.js"></script>
<input id="x" value="">
<input id="y" value="">
<%--当前层级--%>
<input id="curlevel" value="">
<input id="curxy" value=""/>
<script>
    /**
     *系统业务常量
     **/
    var station = '${applicationSessionObject.zdxxdm}';//站点信息
    //图层常量
    var existOverlays = [];//
    var lineArray = []; //线集合
    var overlays = [];//覆盖物
    var currLine = ""; //当前画线
    var polyflag = false; //画线开关
    var defaultCursor = null;//
</script>

