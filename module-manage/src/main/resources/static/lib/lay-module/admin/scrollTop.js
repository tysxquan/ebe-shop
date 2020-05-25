layui.define(['jquery'], function (exports) {
    var $ = layui.jquery;
    var self = {
        elem:null, //盒子
        icon:'<i class="layui-icon layui-icon-up-circle"></i>',
        control:$(".layui-layer-content"),//滚动条产生的那个节点
        config:{

        },
    };
    //初始化元素
    function init(options){
        self.elem = $('<div id="backTop"></div>');
        options.control.prepend(self.elem);
        self.elem.css({
            'position': 'fixed',
            'right': "35px",
            'bottom': '50px',
            'zIndex': 99,
            "cursor": "pointer",
            "display": "none",
        });
        self.elem.prepend(options.icon);
        self.elem.find('i').css({
            'color': '#009688',
            'font-size': '32px',
        });
    }

    self.load = function (options) {
        self = $.extend(self,options);
        init(self);
        // 页面可视区域高度
        const height = self.control.height();
        let scroll_top,control = self.control;
        let rocket = self.elem;

        // 页面滚动显示或隐藏火箭图标
        control.on("scroll",function () {
            scroll_top = control.scrollTop();
            if (scroll_top <= height/4) {
                rocket.css("display","none");
            } else {
                rocket.css("display","block");
            }
        });
        rocket.on("click",function () {
            //带有动态效果的定位
            control.animate({"scrollTop":$(this).scrollTop() - 50},500);
        })
    };

    exports('scrollTop', self);
});