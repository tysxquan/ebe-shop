layui.define(['jquery', 'form'], function (exports) {
    var modelName = 'selectPlus',
        $ = layui.jquery,
        form = layui.form,
        config = {
            elem:'',
            url:'',
            paramUrl:'',
            selected:'',
            data:{},
            key: {
                name: 'name',
                value: 'id',
            },
            response: {
                successCode:200,
                code:"code",
                msg:"message",
                data:"data"
            }
        };
    //初始化控件
    function initInput(opt) {
        var content = "",option = "";
        var response = opt.response,$elem = $(opt.elem),key = opt.key;
        $.get("/spec/group/form/"+opt.spuId,(res => {
            if (res.code === 200) {
                opt.data = res.data;
                $.each(res.data,function (index,item) {
                    $.ajax({
                        url: "/spec/param/form/"+item.specGroupId,
                        type: 'get',
                        async: false,
                        success: function (res) {
                            if (res.code === 200) {
                                $.each(res.data,function (index,item) {
                                    option += '<option value="' + item.specParamId + '" >' + item.paramName + '</option>\n';
                                })
                            }
                        }
                    });
                    content += '  <div class="layui-form-item">' +
                        '<label class="layui-form-label admin-required">'+item.groupName+"："+'</label>' +
                        '<div class="layui-input-inline">' +
                        '<select lay-verify="requiredAuto" data-group-id="'+item.specGroupId+'">' +
                        '<option value="">选择'+item.groupName+'</option>' + option +
                        '</select>' +
                        '</div>' +
                        '</div>';
                    option="";
                });
                $elem.html(content);
                if (opt.selected !== undefined) {
                    $.each(opt.selected,function (index,item) {
                        $elem.find("[data-group-id="+item.specGroupId+"]").val(item.specParamId)
                    });
                }
                form.render('select');
            }
        }))
    }


    /**
     * 初始化select控件且定义事件处理
     * @param {any} opt
     */
    var selectPlus = {
            render: function (opt) {
                opt = $.extend(true,config,opt);
                initInput(opt);

            },
            getData:function(elem){
                var $elem = $(elem),arr = [],ids=[];
                layui.each($elem.find("[data-group-id]"),function () {
                    var val = $(this).val();
                    ids.push(val);
                    arr.push($(this).attr("data-group-id")+"-"+val);
                });
                return {ids:ids,arr:arr}
            }

        };
    //外部接口
    exports(modelName, selectPlus);
});