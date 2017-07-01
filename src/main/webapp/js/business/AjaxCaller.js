/**
 * 和GSS的AJAX协议发送
 * @type {{sayHi2GSS: AJAXCaller.sayHi2GSS, callPhone4Call: AJAXCaller.callPhone4Call, callPhone4Free: AJAXCaller.callPhone4Free, callPhone4Meeting: AJAXCaller.callPhone4Meeting, callPdtMsgSend: AJAXCaller.callPdtMsgSend, callPdtAddMember: AJAXCaller.callPdtAddMember, callPdtAddMemberResult: AJAXCaller.callPdtAddMemberResult, callPdtDelDyMsg: AJAXCaller.callPdtDelDyMsg}}
 */
AJAXCaller = {
    /**
     * 向GSS发送握手
     */
    sayHi2GSS: function () {
        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "callHttp.do?method=sayHi2GSS",
            type: "post",
            success: function (data) {
                try {
                    if (data != null) {
                        var obj = JSON.parse(data);
                    }
                } catch (e) {
                    console.log(e);
                }
            },
            error: function (data, textStatus) {
                alert(textStatus + "错误:" + data.responseText);
            }
        });
    },

    /**
     * 定位反馈
     * @constructor
     */
    PositionWgis2Gss: function (data, l_lng, l_lat, SSZRQ) {
        var PositionWgis2Gss = {
            lsh: data.lsh,
            dwzbX: l_lat,
            dwzbY: l_lng,
            sszrq: SSZRQ
        };
        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "callHttp.do?method=PositionWgis2Gss",
            type: "post",
            data: PositionWgis2Gss,
            success: function (data) {

            },
            error: function (data, textStatus) {
                alert(textStatus + "错误:" + data.responseText);
            }
        });
    },

    /**
     **有线呼叫
     * [9007DHHM:+无线号码/电话号码+*#TH:+台号+*#TLX:+台类型+*#]
     **/
    callPhone4Call: function (dhhm) {
        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "callHttp.do?method=callPhone4Call&dhhm=" + dhhm,
            type: "post",
            success: function (data) {
                console.log(data);//TODO 页面级处理电话呼叫
            },
            error: function (data, textStatus) {
                alert(textStatus + "错误:" + data.responseText);
            }
        });
    },

    /**
     **有线挂机
     **/
    callPhone4Free: function () {
        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "callHttp.do?method=callPhone4Free",
            type: "post",
            success: function (data) {
                console.log(data);//TODO 页面级处理电话挂机
            },
            error: function (data, textStatus) {
                alert(textStatus + "错误:" + data.responseText);
            }
        });
    },

    /**
     **有线启动会议
     **/
    callPhone4Meeting: function () {
        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "callHttp.do?method=callPhone4Meeting",
            type: "post",
            success: function (data) {
                console.log(data);//TODO 页面级处理电话启动会议
            },
            error: function (data, textStatus) {
                alert(textStatus + "错误:" + data.responseText);
            }
        });
    },


    /**
     **向GSS发送PDT短信下行通知
     **/
    callPdtMsgSend: function () {
        var PdtMsgSend = {};//从页面构造PdtMsgSend
        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "callHttp.do?method=callPdtMsgSend",
            type: "post",
            data: PdtMsgSend,
            success: function (data) {
                console.log(data);//TODO 向GSS发送PDT短信下行通知
            },
            error: function (data, textStatus) {
                alert(textStatus + "错误:" + data.responseText);
            }
        });
    },

    /**
     **无线添加动态组成员
     **/
    callPdtAddMember: function () {
        var PdtAddMember = {};//从页面构造PdtAddMember
        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "callHttp.do?method=callPdtAddMember",
            type: "post",
            data: PdtAddMember,
            success: function (data) {
                console.log(data);//TODO 无线添加动态组成员
            },
            error: function (data, textStatus) {
                alert(textStatus + "错误:" + data.responseText);
            }
        });
    },

    /**
     **无线添加动态组成员
     **/
    callPdtAddMemberResult: function () {
        var PdtAddMemberResult = {};//从页面构造PdtAddMemberResult
        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "callHttp.do?method=callPdtAddMemberResult",
            type: "post",
            data: PdtAddMemberResult,
            success: function (data) {
                console.log(data);//TODO 页面级处无线添加动态组成员
            },
            error: function (data, textStatus) {
                alert(textStatus + "错误:" + data.responseText);
            }
        });
    },

    /**
     **无线撤销动态组
     **/
    callPdtDelDyMsg: function () {
        var PdtDelDyMsg = {};//从页面构造PdtDelDyMsg
        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "callHttp.do?method=callPdtDelDyMsg",
            type: "post",
            data: PdtDelDyMsg,
            success: function (data) {
                console.log(data);//TODO 无线撤销动态组
            },
            error: function (data, textStatus) {
                alert(textStatus + "错误:" + data.responseText);
            }
        });
    }

};