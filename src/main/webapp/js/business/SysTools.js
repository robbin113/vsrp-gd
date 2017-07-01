/**
 * Created by nanxiaofeng on 2017/4/27.
 * 一些小玩意
 */
var SysTools = {

    /**
     * 系统复位
     */
    refreshSys: function () {
        location.reload();
    },
    /**
     *全屏显示
     **/
    launchFullScreen: function () {
        if (document.documentElement.requestFullscreen) {
            document.documentElement.requestFullscreen();
        } else if (document.documentElement.mozRequestFullScreen) {
            document.documentElement.mozRequestFullScreen();
        } else if (document.documentElement.webkitRequestFullscreen) {
            document.documentElement.webkitRequestFullscreen();
        } else if (document.documentElement.msRequestFullscreen) {
            document.documentElement.msRequestFullscreen();
        }
    },
    /**
     * 打开右侧菜单
     */
    openRightDiv: function () {
        $("#right-list").empty();
        var box_number = windowWidth - rightBoxWidth;
        $("#root-box").addClass("list-right");
        $("#right-list").addClass("active");
        $("#root-box").width(box_number);
        $("#right-list").css("left", box_number);
        $("#open-right").addClass("open-right-shen");
        // //TODO 右侧DIV折叠方法，暂时放在taglibs，由于右侧右面没有执行完毕状态。
        // $("#right-list").on('click', '.RLB-top', function () {
        //     var ZT_RBL = $(this).next(".RLB-body");
        //     ZT_RBL.toggle();
        // });
    },
    /**
     * 语音播放
     * @param text
     */
    speaker: function (text) {
        try {
            if (userAgent.indexOf("Chrome") > -1) {
                var msg = new SpeechSynthesisUtterance(text);
                msg.lang = 'zh';
                msg.voice = speechSynthesis.getVoices().filter(function (voice) {
                    return voice.name == 'Whisper';
                })[0];
                speechSynthesis.speak(msg);
            }
        } catch (e) {

        }
    },
    //过滤特殊字符
    //需要的话自己改正则表达式
    regScript: function (s) {
        var pattern = new RegExp("`~!@&*()=|';',\\[\\]./~！@#￥……&*（）——|{}【】‘；：”“'。，、？-");
        var rs = "";
        for (var i = 0; i < s.length; i++) {
            rs = rs + s.substr(i, 1).replace(pattern, '');
        }
        rs = rs.replace(new RegExp("\r\n", "gm"), "");//处理回车换行符
        // rs = rs.replace(new RegExp("\"","gm"),"\'");//处理双引号
        return rs;
    },
    /**
     *转换long值为日期字符串
     * @param l long值
     * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss
     * @return 符合要求的日期字符串
     */
    getFormatDateByLong: function (l, pattern) {
        return getFormatDate(new Date(l), pattern);
    },
    /**
     * 添加控件
     */
    showCrtl: function (add) {
        if (add) {
            map.addControl(top_left_navigation);//左上平移工具条
            map.addControl(top_left_control);//左上工具条
            map.addControl(overViewOpen);//鹰眼
            map.addControl(mapType);//2D图，卫星图
            map.addControl(trafctrl);//交通图
            map.addControl(realViewCtrl);//添加全景控件
        } else {
            map.removeControl(top_left_navigation);//左上平移工具条
            map.removeControl(top_left_control);//左上工具条
            map.removeControl(overViewOpen);//鹰眼
            map.removeControl(mapType);//2D图，卫星图
            map.removeControl(trafctrl);//交通图
            map.removeControl(realViewCtrl);//添加全景控件
        }
    },
    /**
     * hideAndShow
     */
    hideAndShowCtrl: function () {
        var act = !top_left_navigation.isVisible();
        SysTools.showCrtl(act);
    }
};

/**
 * 重构数组，去重，保证数组里只有单个对象+业务主键
 * @param layerId 业务主键
 * @param layerType 图层类型
 * @param layer 图层对象
 * @param add 如果是添加行为
 */
Array.prototype.flushOverlays = function (layerId, layerType, layer, local_text, add) {
    var overlay = MapChche.findOverlayById(layerId);//根据ID找到已经存在的 覆盖物
    if (overlay != null) {//如果有结果
        var idx = existOverlays.indexOf(overlay);
        existOverlays.splice(idx, 1);//无条件移除整个元素
    }
    //如果是删除行为则不自行push
    if (add) {//如果是保存或者更新行为，则把新的元素添加进来
        existOverlays.push(
            MapChche.setLayerAndInfo(layerId, layerType, layer, local_text)
        );
    }
};

/**
 * 数组结合
 * @param arrA
 * @param arrB
 * @returns {Array.<T>|string}
 */
Array.prototype.connect = function (arrA, arrB) {
    var arrC = arrA.concat(arrB);
    arrA = null;
    arrB = null;
    return arrC;
};

/**
 * array 排序
 * @param name
 * @returns {Function}
 */
var sortBy = function (name) {
    return function (o, p) {
        var a, b;
        if (typeof o === "object" && typeof p === "object" && o && p) {
            a = o[name];
            b = p[name];
            if (a === b) {
                return 0;
            }
            if (typeof a === typeof b) {
                return a < b ? -1 : 1;
            }
            return typeof a < typeof b ? -1 : 1;
        }
        else {
            throw ("error");
        }
    }
};

/**
 *日期格式转换
 */
//扩展Date的format方法
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};
/**
 *转换日期对象为日期字符串
 * @param date 日期对象
 * @param isFull 是否为完整的日期数据,
 * 为true时, 格式如"2013-12-06 01:05:04"
 * 为false时, 格式如 "2013-12-06"
 * @return 符合要求的日期字符串
 */
function getSmpFormatDate(date, isFull) {
    var pattern = "";
    if (isFull == true || isFull == undefined) {
        pattern = "yyyy-MM-dd hh:mm:ss";
    } else {
        pattern = "yyyy-MM-dd";
    }
    return getFormatDate(date, pattern);
}
/**
 *转换当前日期对象为日期字符串
 * @param date 日期对象
 * @param isFull 是否为完整的日期数据,
 * 为true时, 格式如"2013-12-06 01:05:04"
 * 为false时, 格式如 "2013-12-06"
 * @return 符合要求的日期字符串
 */
function getSmpFormatNowDate(isFull) {
    return getSmpFormatDate(new Date(), isFull);
}
/**
 *转换long值为日期字符串
 * @param l long值
 * @param isFull 是否为完整的日期数据,
 * 为true时, 格式如"2013-12-06 01:05:04"
 * 为false时, 格式如 "2013-12-06"
 * @return 符合要求的日期字符串
 */
function getSmpFormatDateByLong(l, isFull) {
    return getSmpFormatDate(new Date(l), isFull);
}

/**
 *转换日期对象为日期字符串
 * @param l long值
 * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss
 * @return 符合要求的日期字符串
 */
function getFormatDate(date, pattern) {
    if (date == undefined) {
        date = new Date();
    }
    if (pattern == undefined) {
        pattern = "yyyy-MM-dd hh:mm:ss";
    }
    return date.format(pattern);
}