layui.use(['form', 'jquery','layer'], function() {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $loginErr = $(".login .err"),
        $loginErrSpan = $loginErr.find("span"),
        $registerErr = $(".register .err"),
        $registerErrSpan = $registerErr.find("span");

    initCode();
    function initCode() {
        $("#code").attr("src","image/captcha?data=" + new Date().getTime());
    }
    $("#code").on("click",function () {
        initCode();
    });
    document.querySelector('.img_btn').addEventListener('click', function() {
        document.querySelector('.content').classList.toggle('s--signup');
    });
    // 进行注册操作
    form.on('submit(register)', function(data) {
        var loading = $(this).find('i');

        data = data.field;

        if(isEmpty(data.username)) {
            tipsRegister("用户名不能为空");
            return false;
        }
        var usr=/^[a-zA-Z]{4,18}$/;
        if (!usr.exec(data.username)) {
            tipsRegister("用户名只能输入4-18个纯字母");
            return false;
        }
        if(isEmpty(data.password)) {
            tipsRegister("密码不能为空");
            return false;
        }
        if(isEmpty(data.passwordEnsure)) {
            tipsRegister("确定密码不能为空");
            return false;
        }
        if (data.password.length < 6) {
            tipsRegister("密码不能小于6位");
            return false;
        }

        var pPattern = /^.*(?=.{6,})(?=.*\d)(?=.*[a-z])(?=.*[!@#$%^&*?.]).*$/;
        if (!pPattern.exec(data.password)) {
            tipsRegister("密码需要包含字母、数字、特殊字符");
            return false;
        }
        if (data.password !== data.passwordEnsure) {
            tipsRegister("两次密码不一致");
            return false;
        }

        loading.show();

        $.post("/register",data,function (res) {
            if (res.code === 200) {
                layer.msg("注册成功，请登录");
                document.querySelector('.content').classList.toggle('s--signup');
                $(".register")[0].reset();
            }else {
                tipsRegister(res.message);
            }
            loading.hide();
        });
        return false;
    });

    // 进行登录操作
    form.on('submit(login)', function(data) {
        var loading = $(this).find('i');
        data = data.field;
        if(isEmpty(data.username)) {
            tipsLogin("用户名不能为空");
            return false;
        }
        if(isEmpty(data.password)) {
            tipsLogin("密码不能为空");
            return false;
        }
        if(isEmpty(data.verifyCode)) {
            tipsLogin("验证码不能为空");
            return false;
        }
        loading.show();
        $.post("/login",data,function (res) {
            if (res.code === 200) {
                window.location = '/index';
            }else {
                tipsLogin(res.message);
                loading.hide();
                initCode();
            }

        });
        return false;
    });
    function tipsLogin(title) {
        $loginErrSpan.html(title);
        $loginErr.fadeIn(300);
    }
    function tipsRegister(title) {
        $registerErrSpan.html(title);
        $registerErr.fadeIn(300);
    }
    function isEmpty(obj) {
        return typeof obj == 'undefined' || obj == null || obj === '';
    }
});
