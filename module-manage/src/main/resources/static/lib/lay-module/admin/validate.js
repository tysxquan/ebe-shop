// 定义常用的校验，常用的正则 https://www.open-open.com/code/view/1430625516632
layui.define(['jquery'], function (exports) {
    var $ = layui.jquery;
    exports('validate', {
        username: function (value, item) {
            if (!isEmpty(value)) {
                var result = '';
                $.ajax({
                    url: '/system-user/check/' + value,
                    async: false,
                    data: {
                        "userId": item.getAttribute('id')
                    },
                    type: 'get',
                    success: function (d) {
                        (d) && (result = '该用户名已存在')
                    }
                });
                if (!isEmpty(result)) {
                    return result;
                }
            }
        },
        email: function (value) {
            if (!isEmpty(value)) {
                if (!new RegExp("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$").test(value)) {
                    return '请填写正确的邮箱';
                }
            }
        },
        phone: function (value) {
            if (!isEmpty(value)) {
                if (!new RegExp("^1\\d{10}$").test(value)) {
                    return '请填写正确的手机号码';
                }
            }
        },
        number: function (value) {
            if (!isEmpty(value)) {
                if (!new RegExp("^[0-9]*$").test(value)) {
                    return '只能填写数字';
                }
            }
        },
        selectOption:function(value,item){
            if($(item).parent().parent().is(":visible")){
                if (isEmpty(value)) {
                    return '请选择对应的选项';
                }
            }
        },
        selectIcon:function(value,item){
            if($(item).parent().parent().is(":visible")){
                if (isEmpty(value)) {
                    $(item).parent().find(".layui-iconpicker-item").addClass("admin-form-danger");
                    return '请选择对应的图标';
                }
            }
        },
        requiredHide:function(value,item) {
            if($(item).parent().parent().is(":visible")){
                if (isEmpty(value)) {
                    return '请选择对应的图标';
                }
            }
        },
        requiredAuto: function (value,item) {
            if($(item).parent().parent().is(":visible")) {
                if (isEmpty(value)) {
                    var name = $(item).parent().parent().find("label").text();
                    name = name.substring(0, name.length - 1);
                    return name + '不能为空';
                }
            }
        },
        range: function (value, item) {
            if (!isEmpty(value)) {
                var minlength = item.getAttribute('minlength') ? item.getAttribute('minlength') : -1;
                var maxlength = item.getAttribute('maxlength') ? item.getAttribute('maxlength') : -1;
                var length = value.length;
                if (minlength === -1) {
                    if (length > maxlength) {
                        return '长度不能超过 ' + maxlength + ' 个字符';
                    }
                } else if (maxlength === -1) {
                    if (length < minlength) {
                        return '长度不能少于 ' + minlength + ' 个字符';
                    }
                } else {
                    if (length > maxlength || length < minlength) {
                        return '长度范围 ' + minlength + ' ~ ' + maxlength + ' 个字符';
                    }
                }
            }
        }
    });

    function isEmpty(obj) {
        return typeof obj == 'undefined' || obj == null || obj === '';
    }
});