<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="../css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="../css/font-awesome.css" rel="stylesheet">

    <script src="../js/jquery.min.js" type="text/javascript"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/layui.js"></script>


    <script src="../js/plugins/validate/jquery.validate.min.js"></script>
    <script src="../js/plugins/validate/messages_zh.min.js"></script>
    <script src="../js/layer/layer.min.js"></script>

</head>

<body>

    <div class="container" style="margin-top:40px">
        <div class="row">
            <div class="col-sm-6 col-md-4 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <strong> 注册 </strong>
                    </div>
                    <div class="panel-body">
                        <form  method="POST" id="register_form">
                            <fieldset>
                                <div class="row">
                                    <div class="col-sm-12 col-md-10  col-md-offset-1 ">

                                        <div class="form-group">
                                            <div class="input-group">
                                                <span class="input-group-addon">
                                                    <i class="glyphicon glyphicon-user"></i>
                                                </span>
                                                <input class="form-control required" type="text" placeholder="用户名" name="userName" autofocus="autofocus"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-lock"></i>
                                                    </span>
                                                    <input class="form-control required" type="password" placeholder="密码" id="register_password" name="password"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-lock"></i>
                                                    </span>
                                                    <input class="form-control required" type="password" placeholder="重复密码" name="rpassword"/>

                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group">
                                                 <span class="input-group-addon">
                                                    <i class="fa fa-envelope fa-lg"></i>
                                                 </span>
                                                <input class="form-control eamil" type="text" placeholder="Email" name="email"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="btn btn-lg btn-primary btn-block" id ="register_btn">注册</div>
                                            <div style="margin-top:10px;float:right" >>>&nbsp&nbsp<a href="/noAuth/login">登陆</a></div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </fieldset>
                        </form> <!--- 注册form结束 -->
                    </div>

                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script type="text/javascript">

        $().ready(function() {
//
//            $("#register_form").validate({
//                rules: {
//                    userName:  {
//                        required: true,
//                        minlength: 5
//                    },
//                    password: {
//                        required: true,
//                        minlength: 5
//                    },
//                    rpassword: {
//                        equalTo: "#register_password"
//                    },
//                    email: {
//                        required: true,
//                        email: true
//                    }
//                },
//                messages: {
//                    username: "请输入姓名",
//                    password: {
//                        required: "请输入密码",
//                        minlength: $.validator.format("密码不能小于{0}个字 符")
//                    },
//                    rpassword: {
//                        equalTo: "两次密码不一样"
//                    },
//                    email: {
//                        required: "请输入邮箱",
//                        email: "请输入有效邮箱"
//                    }
//                }
//            });

            $("#register_btn").click(function() {
                //$("#register_form").css("display", "block");
                //提交注册，成功说成功；不成功说不成功

                //提交数据给后台
                $.ajax({
                    type: "POST",
                    url: "../noAuth/register",  //-- 请求注册
                    data: $('#register_form').serialize(),
                    success: function (result) {
                            if (result.code == 0) {
                                var index = layer.load(1, {
                                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                                });
                                parent.location.href = ctx + 'index';
                            } else {
                                layer.msg(r.msg);
                            }
                    },
                    error:function (result) {
                        alert(result);

                    }
                });
            });
        });

    </script>

</body>
</html>