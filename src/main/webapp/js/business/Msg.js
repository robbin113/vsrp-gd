/**
 * by nanxiaofeng
 * 业务消息页面处理器
 */
MSG = {
    //消息业务处理
    handleMsg: function (data) {
        console.log(data);
        switch (data.msgId) {
            case "9001"://握手反馈业务处理
                break;
            case "5000"://定位信息业务处理
                break;
            case "5014"://警情上图处理
                break;
            case "5016"://更新警请状态
                break;
            default :
                break;
        }
    }
};