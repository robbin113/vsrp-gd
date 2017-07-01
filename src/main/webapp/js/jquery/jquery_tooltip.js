/*
 * jQuery Tooltip
 * Copyright 2015 jrack
 * Example
 *      <code>  初始化
 *             $(function () {
 *                $.tooltip.init()
 *            });
 *      </code>
 *      <code>  调用 data ：提示的内容
 *            $.tooltip.tipshow(data);
 *      </code>
 */
(function ($) {
    /* 拓展提示框 */
    $.extend({
        tooltip: {
            init: function () {
                $("body").append('<div id="tooltip" style="display: none; width: auto; text-align: center; position: fixed;'
                    + 'top: 80%; padding: 5px 20px; left: 47%;z-index: 999999;'
                    + 'border-radius: 5px;background-color: black;color: white;">'
                    + '可填写需要的内容'
                    + '</div>');
            },
            tipshow: function (text) {
                $("#tooltip").text(text);
                $("#tooltip").fadeIn(500);
                $("#tooltip").delay(1000).fadeOut(5000);
            }
        }
    });
})(jQuery);