// 濮阳
var citySouthWestY = 114.894824;//市区西南
var citySouthWestX = 35.69455;
var cityNorthEestY = 115.2551;//市区东北
var cityNorthEestX = 35.825145;
var centX = 35.770956;
var centY = 115.04833;


/************系统常量*****************/
var outputPath = 'baidumapv2/tiles/';
var minLevel = 1;
var maxLevel = 19;
var format = '.png';
var pointsStr = centY + "$" + centX + "$公安局指挥中心";
var searchArea = 500;//地图查询范围单位
var fillOpacityFildes = 0.5;//全局覆盖物透明度
//浏览器类型
var userAgent = navigator.userAgent;

/************图标******************/
var ICON = {
    icon_Width: 20,//图标宽
    icon_Height: 20,//图标高
    icon_car_Width: 40,
    icon_car_Height: 40,

    icon_node: "images/marker_red_sprite.png",//热点
    icon_node_del: "images/st-close.png",//删除
    icon_monitor_del: "images/st-close.png",//删除
    icon_center: "images/ico/org-big.png",//中心
    icon_alarm_temponit: "images/point-collection/red-marker-10x13.png", //红色定位图标
    icon_org: "images/ico/org.png", //单位图标
    icon_panTo: "images/pin_red_solid_18px_1129869_easyicon.net.png", //临时定位点
    icon_mon: "images/ico/mon.png",//监控点图标
    icon_cluster: "images/ico/cluster.png",//聚合点样式
    icon_temponit: "images/point-collection/blue-marke-15x16.png", //蓝色定位图标
    icon_car: "images/ico/car_big.png",//大车
    icon_car_offline: "images/ico/car_offline.png",//车离线
    icon_car_online: "images/ico/car_online.png",//车在线
    icon_car_busy: "images/ico/car_busy.png",//在处警
    icon_car_moving: "images/ico/car_moving.png",//车在跑

    icon_emt_big: "images/ico/emt-big.png",
    icon_emt_small: "images/ico/emt.png",
    /*****警情*****/
    icon_alarm_ing: "images/ico/alarm_ing.gif", //正在处警
    icon_alarm_imp: "images/ico/alarm_red.png",//重大案件
    icon_alarm_lit: "images/ico/alarm_yellow.png",//一般案件
    icon_alarm_ed: "images/ico/alarm_green.png", //处警完毕
    icon_alarm_wdc: "images/ico/alarm_little_red.png",//为到场
    icon_alarm_dcj: "images/ico/alarm_little_yellow.png",//待处警
    icon_alarm_wfk: "images/ico/alarm_light_green.png",//未反馈
    iocn_car_gif: "images/ico/jingcar.gif"
};



