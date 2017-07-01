<%@ page language="java" pageEncoding="utf-8" %>
<style>
    #container { width: 1000px; height: 800px; background: #ccc; }
</style>
<script>
    //JQUERY
    $(document).ready(function () {
        //输入框回车
        $("#change-select input").keydown(function (e) {
            if (e.keyCode == 13) {
                var queryId = $("#top-menu-select").val();
                findInfos(queryId);
            }
        });
        //select改变值
        $("#change-select select").change(function () {
            var queryId = $("#top-menu-select").val();
            findInfos(queryId);
        });

    });//end JQUERY

    /**
     * 上部菜单动作
     */
    function findInfos(queryId) {

    }

</script>
<body style="overflow: hidden;">
<div class="root-box" id="root-box">
    <div class="logo-box" id="logo-box">
        <div class="logo-bg">可视化指挥调度系统</div>
        <div class="top-menu" id="top-menu">
            <select type="text" class="new-select-blue" id="top-menu-select">
                <option value="0">选择查询类型</option>
                <option value="1">警情上图</option>
                <option value="2">辖区信息</option>
            </select>
            <ul class="change-select" id="change-select">
                <li>
                    <span>类型</span>
                    <select type="text" class="new-select-blue" name="jjlbmc" id="jjlbmc" style="width: 70px">
                        <option></option>
                        <option value="刑事案件">刑事案件</option>
                        <option value="治安案件">治安案件</option>
                        <option value="群众求助">群众求助</option>
                        <option value="交通类警情">交通类警情</option>
                        <option value="社会联动">社会联动</option>
                        <option value="群体性事件">群体性事件</option>
                        <option value="举报投诉">举报投诉</option>
                        <option value="火灾事故">火灾事故</option>
                        <option value="灾害事故">灾害事故</option>
                        <%--<option value="其他">其他</option>--%>
                    </select>
                    <span>时间段</span>
                    <select type="text" class="new-select-blue" name="scope" id="scope" style="width: 70px">
                        <option></option>
                        <option value="24">24小时</option>
                        <option value="48">48小时</option>
                        <option value="72">72小时</option>
                    </select>
                    <span>接警员</span>
                    <select type="text" class="new-select-blue" name="jjrxm" id="jjrxm" style="width: 70px">
                        <option></option>
                        <option value="${vZbyxxb.xm}">自己</option>
                    </select>
                </li>
                <li class="">
                    <span>辖区:</span>
                    <select type="text" class="new-select-blue" name="areaResource" id="areaResource"
                            style="width: 70px">
                        <option></option>
                        <c:forEach var="areaList" items="${areaList}">
                            <option value="${areaList.id}">${areaList.name}</option>
                        </c:forEach>
                    </select>
                </li>
            </ul>
        </div>
    </div>

    <%--地图容器--%>
    <div id="container" onselectstart="return false;" class="container" style="height: 100%;width:100%;position: relative"></div>
    <div id="message"></div>

    <div class="bottom-menu active" id="bottom-menu">
        <div class="BMenu-left" id="BMenu-left">
            <div class="bottom-menu-02" id="bottom-menu-02">
                <div class="bottom-menu-01" id=""></div>
                <ul>
                    <li onclick="changeCursor()" class="active">
					<span>
						<img src="${ctx}/images/_17.png">
					</span>
                        <p>地图工具</p>
                    </li>
                    <c:if test="${applicationSessionObject.xtsf=='系统管理员'}">
                        <li role="admin">
					<span>
						<img src="${ctx}/images/logojing.png">
					</span>
                            <p>警力资源</p>
                        </li>
                    </c:if>
                    <li>
					<span>
						<img src="${ctx}/images/logojing.png">
					</span>
                        <p>资源应用</p>
                    </li>
                    <li>
					<span>
						<img src="${ctx}/images/_02.png">
					</span>
                        <p>图层控制</p>
                    </li>
                    <%--<li>--%>
                    <%--<span>--%>
                    <%--<img src="${ctx}/images/_05.png">--%>
                    <%--</span>--%>
                    <%--<p>门户登入</p>--%>
                    <%--</li>--%>
                    <c:if test="${applicationSessionObject.xtsf=='系统管理员'}">
                        <li role="admin">
					<span>
						<img src="${ctx}/images/System.png">
					</span>
                            <p>系统工具</p>
                        </li>
                    </c:if>
                </ul>

                <div class="BMenu-left-top" id="BMenu-left-top">
                    <%--地图漫游--%>
                    <dl class="active">
                        <dd onclick="MapAction.openDis()">
                            <span><img src="${ctx}/images/ico/BMenu_01.png"></span>
                            <p>测距</p>
                        </dd>
                        <dd onclick="draw(BMAP_DRAWING_POLYGON)">
                            <span><img src="${ctx}/images/ico/BMenu_02.png"></span>
                            <p>测面积</p>
                        </dd>
                        <dd onclick="draw(BMAP_DRAWING_POLYLINE)">
                            <span><img src="${ctx}/images/ico/BMenu_08.png"></span>
                            <p>画线</p>
                        </dd>
                        <dd onclick="draw(BMAP_DRAWING_CIRCLE)">
                            <span><img src="${ctx}/images/ico/BMenu_06.png"></span>
                            <p>画圆</p>
                        </dd>
                        <dd onclick="draw(BMAP_DRAWING_RECTANGLE)">
                            <span><img src="${ctx}/images/ico/BMenu_07.png"></span>
                            <p>画矩形</p>
                        </dd>
                        <dd onclick="MapAction.clearAllOverLay()">
                            <span><img src="${ctx}/images/ico/BMenu_09.png"></span>
                            <p>清理</p>
                        </dd>
                        <dd onclick="SysTools.launchFullScreen()">
                            <span><img src="${ctx}/images/ico/BMenu_03.png"></span>
                            <p>全屏</p>
                        </dd>
                        <dd onclick="MapAction.panToCenter()">
                            <span><img src="${ctx}/images/ico/BMenu_04.png"></span>
                            <p>中心</p>
                        </dd>
                        <dd onclick="SysTools.refreshSys()">
                            <span><img src="${ctx}/images/ico/BMenu_05.png"></span>
                            <p>重载系统</p>
                        </dd>
                    </dl>
                    <%--数据维护——资源--%>
                    <c:if test="${applicationSessionObject.xtsf=='系统管理员'}">
                        <dl role="admin">
                            <dd onclick="draw(BMAP_DRAWING_MARKER,30)">
                                <span><img src="${ctx}/images/ico/BMenu_10.png"></span>
                                <p>监控</p>
                            </dd>
                            <dd onclick="draw(BMAP_DRAWING_MARKER,70)">
                                <span><img src="${ctx}/images/ico/BMenu_12.png"></span>
                                <p>单位</p>
                            </dd>
                            <dd onclick="draw(BMAP_DRAWING_POLYGON,60)">
                                <span><img src="${ctx}/images/ico/BMenu_13.png"></span>
                                <p>区域</p>
                            </dd>
                            <dd onclick="open_layer('${ctx}/wgisCarInfo.do?method=findWgisCarInfoList'),'车辆管理'">
                                <span><img src="${ctx}/images/ico/BMenu_15.png"></span>
                                <p>车辆</p>
                            </dd>
                            <dd onclick="open_layer('${ctx}/wgisEmtInfo.do?method=findWgisEmtInfoList','设备管理')">
                                <span><img src="${ctx}/images/ico/BMenu_14.png"></span>
                                <p>设备</p>
                            </dd>
                            <dd onclick="draw(BMAP_DRAWING_MARKER,50)">
                                <span><img src="${ctx}/images/ico/BMenu_11.png"></span>
                                <p>热点</p>
                            </dd>
                        </dl>
                    </c:if>
                    <%--资源应用--%>
                    <dl>
                        <dd onclick="draw(BMAP_DRAWING_CIRCLE,30)">
                            <span><img src="${ctx}/images/ico/BMenu_10.png"></span>
                            <p>监控</p>
                        </dd>
                    </dl>
                    <%--图层控制--%>
                    <dl>
                        <dd id="monDD" onclick="MapAction.hideAndShowOverlays(30);">
                            <span><img src="${ctx}/images/ico/BMenu_10.png"></span>
                            <p>监控</p>
                        </dd>
                        <dd id="orgDD" onclick="MapAction.hideAndShowOverlays(70);">
                            <span><img src="${ctx}/images/ico/BMenu_12.png"></span>
                            <p>单位</p>
                        </dd>
                        <dd id="pdaDD" onclick="MapAction.hideAndShowOverlays(90);" title="车辆+PDA">
                            <span><img src="${ctx}/images/ico/BMenu_15.png"></span>
                            <p>车辆-P</p>
                        </dd>
                        <dd id="gpsDD" onclick="MapAction.hideAndShowOverlays(80);" title="车辆+GPS">
                            <span><img src="${ctx}/images/ico/BMenu_15.png"></span>
                            <p>车辆-G</p>
                        </dd>
                        <dd id="areaDD" onclick="MapAction.hideAndShowOverlays(60);">
                            <span><img src="${ctx}/images/ico/BMenu_13.png"></span>
                            <p>辖区</p>
                        </dd>
                        <dd id="tpDD" onclick="MapAction.hideAndShowOverlays(100);">
                            <span><img src="${ctx}/images/ico/BMenu_13.png"></span>
                            <p>交警</p>
                        </dd>
                        <dd id="clDD" onclick="MapAction.hideAndShowOverlays(140);">
                            <span><img src="${ctx}/images/ico/BMenu_13.png"></span>
                            <p>环卫</p>
                        </dd>
                        <dd id="coDD" onclick="MapAction.hideAndShowOverlays(110);">
                            <span><img src="${ctx}/images/ico/BMenu_13.png"></span>
                            <p>县区</p>
                        </dd>
                        <dd onclick="SysTools.hideAndShowCtrl()">
                            <span><img src="${ctx}/images/ico/leftMenuIco08.png"></span>
                            <p>组件</p>
                        </dd>
                    </dl>
                    <%--&lt;%&ndash;SOO门户&ndash;%&gt;--%>
                    <%--<dl>--%>
                    <%--<dd onclick="open_layer('http://www.taobao.com','数据管理')">--%>
                    <%--<span><img src="${ctx}/images/ico/BMenu_01.png"></span>--%>
                    <%--<p>数据管理</p>--%>
                    <%--</dd>--%>
                    <%--<dd onclick="open_layer('http://www.baidu.com','警情研判')">--%>
                    <%--<span><img src="${ctx}/images/ico/BMenu_01.png"></span>--%>
                    <%--<p>警情研判</p>--%>
                    <%--</dd>--%>
                    <%--<dd onclick="open_layer('http://www.baidu.com','短信回访')">--%>
                    <%--<span><img src="${ctx}/images/ico/BMenu_01.png"></span>--%>
                    <%--<p>短信回访</p>--%>
                    <%--</dd>--%>
                    <%--<dd onclick="open_layer('http://www.baidu.com','预案管理')">--%>
                    <%--<span><img src="${ctx}/images/ico/BMenu_01.png"></span>--%>
                    <%--<p>预案管理</p>--%>
                    <%--</dd>--%>
                    <%--<dd onclick="open_layer('http://www.baidu.com','警力报备')">--%>
                    <%--<span><img src="${ctx}/images/ico/BMenu_01.png"></span>--%>
                    <%--<p>警力报备</p>--%>
                    <%--</dd>--%>
                    <%--</dl>--%>
                    <%--数据维护——数据转换--%>
                    <c:if test="${applicationSessionObject.xtsf=='系统管理员'}">
                        <dl role="admin">
                            <dd onclick="MapAction.transMonitors();">
                                <span><img src="${ctx}/images/ico/BMenu_01.png"></span>
                                <p>监控坐标</p>
                            </dd>
                            <dd onclick="MapAction.transMonitors2CNSpell();">
                                <span><img src="${ctx}/images/ico/BMenu_01.png"></span>
                                <p>监控拼音</p>
                            </dd>
                            <dd onclick="MapAction.transPOI();">
                                <span><img src="${ctx}/images/ico/BMenu_01.png"></span>
                                <p>热点坐标</p>
                            </dd>
                            <dd onclick="MapAction.transPOI2CNSpell();">
                                <span><img src="${ctx}/images/ico/BMenu_01.png"></span>
                                <p>热点拼音</p>
                            </dd>
                            <dd onclick="getBoundary();">
                                <span><img src="${ctx}/images/ico/BMenu_01.png"></span>
                                <p>行政边界</p>
                            </dd>
                            <dd onclick="open_layer('${ctx}/dwonload.jsp','资源下载',500,500)">
                                <span><img src="${ctx}/images/ico/BMenu_01.png"></span>
                                <p>相关资源</p>
                            </dd>
                        </dl>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="BMenu-center" id="BMenu-center">
        </div>

        <div class="BMenu-right" id="BMenu-right">
            <dl>
                <dd onclick="MapAction.changeMapStyle('norma')">普通</dd>
                <dd onclick="MapAction.changeMapStyle('dark')">黑色</dd>
                <dd onclick="MapAction.changeMapStyle('light')">蓝单</dd>
                <dd onclick="MapAction.changeMapStyle('midnight')">午夜</dd>
            </dl>
            <div class="BMenu-search" id="BMenu-search">
                <input type="text" value="" placeholder="搜索" id="searchBar">
                <button type="button" id="searchButton"></button>
            </div>
        </div>
    </div>

    <%--右侧窗口--%>
    <div class="open-right" id="open-right">
        <div class="slider_ico">
        </div>
    </div>

    <%--左侧提示--%>
    <%--<ul class="left-menu" id="left-menu">--%>
    <%--<li>--%>
    <%--<div class="left-num"><img src="images/ico/jingbao.png"></div>--%>
    <%--<div class="left-menu-title">警情分类数量</div>--%>
    <%--<div class="TS-num show"></div>--%>
    <%--</li>--%>
    <%--<li>--%>
    <%--<div class="left-num"><img src="images/ico/jinghui.png"></div>--%>
    <%--<div class="left-menu-title">辖区警情数量</div>--%>
    <%--<div class="TS-num show"></div>--%>
    <%--</li>--%>
    <%--<li>--%>
    <%--<div class="left-num"><img src="images/ico/jingche.png"></div>--%>
    <%--<div class="left-menu-title">重大警情提示</div>--%>
    <%--<div class="TS-num"></div>--%>
    <%--</li>--%>
    <%--<li>--%>
    <%--<div class="left-num"><img src="images/ico/jingli.png"></div>--%>
    <%--<div class="left-menu-title">在线警力</div>--%>
    <%--<div class="TS-num show"></div>--%>
    <%--</li>--%>
    <%--<li>--%>
    <%--<div class="left-num"><img src="images/ico/jingzaigang.png"></div>--%>
    <%--<div class="left-menu-title">在岗警力</div>--%>
    <%--<div class="TS-num"></div>--%>
    <%--</li>--%>
    <%--<li>--%>
    <%--<div class="left-num"><img src="images/ico/jingbao.png"></div>--%>
    <%--<div class="left-menu-title">警情分类数量</div>--%>
    <%--<div class="TS-num show"></div>--%>
    <%--</li>--%>
    <%--<li>--%>
    <%--<div class="left-num"><img src="images/ico/jinghui.png"></div>--%>
    <%--<div class="left-menu-title">辖区警情数量</div>--%>
    <%--<div class="TS-num show"></div>--%>
    <%--</li>--%>
    <%--<li>--%>
    <%--<div class="left-num"><img src="images/ico/jingche.png"></div>--%>
    <%--<div class="left-menu-title">重大警情提示</div>--%>
    <%--<div class="TS-num"></div>--%>
    <%--</li>--%>
    <%--</ul>--%>


</div>

</body>


<!--右侧列表-->
<div class="right-list" id="right-list" grid="">

</div>

<!--------------------弹出层页面-------------开始-------->

<div class="mask-layer" id="mask-layer">
    <div class="layer-closed" id="layer-closed"></div>
</div>
<div class="layerbox" id="layerbox">
    <div class="layerbox-top">
        <div class="layerbox-title" id="layerbox-title"></div>
    </div>
    <div class="layerbox-center" id="layerbox-center">
        <div class="layer-loading" id="layer-loading">
            <div class="loading-box" style="">
                <div class="loading-div-a"></div>
                <div class="loading-div-b"></div>
                <div class="loading-div-c"></div>
                <div class="loading-div-d"></div>
            </div>
        </div>
        <iframe src="" id="layer-iframe" class="layer-iframe" name="layer-iframe" style=" display:none;" frameborder="0"
                scrolling="auto"></iframe>
    </div>
    <div class="layerbox-bottom"></div>
</div>


<div class="the_zhezhao" id="the_zhezhao"></div>
<!--带关闭按钮的层 -->
<div id="layer_box" class="layer_box">

    <div class="layer_title_bg" id="layer_title_bg">Title</div>
    <div class="layer-top"></div>
    <div class="layer-boxBG">
        <div class="index_loading_bg" id="index_loading_bg"></div>
        <iframe src="" id="iframe_layer" name="iframe_layer" frameborder="0" width="300" height="160"
                scrolling="auto" style="margin:0px;
		padding:0px;
	z-index:1; background: 0; border: 0;visibility:hidden; overflow: auto;"></iframe>
    </div>
    <div class="layer-bottom"></div>
</div>

<!--------------------弹出 提示信息-------------开始-------->
<div class="prompt" id="prompt">
    <div class="prompt-ico"></div>
    <div class="prompt-message">保存成功</div>
    <div class="prompt-closed"></div>
</div>
