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
                    <strong> 管理员登陆 </strong>
                </div>
                <div class="panel-body">
                    <form role="form" id="login_form" action="/noAuth/adminLogin" method="POST">
                        <fieldset>
                            <div class="row">
                                <div class="col-sm-12 col-md-10  col-md-offset-1 ">
                                    <div class="form-group">
                                        <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-user"></i>
                                                    </span>
                                            <input class="form-control" placeholder="管理员账号" name="userName" type="text" autofocus>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-lock"></i>
                                                    </span>
                                            <input class="form-control" placeholder="管理员密码" name="password" type="password" value="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div  class="btn btn-lg btn-primary btn-block" id="login_btn">管理员登陆</div>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="../js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {

        $("#login_btn").click(function()
        {
            //$("#register_form").css("display", "block");
            //提交注册，成功说成功；不成功说不成功

            //提交数据给后台
            $.ajax({
                type: "POST",
                url: "../noAuth/adminLogin",           //-- 请求注册
                data: $('#login_form').serialize(),
                success: function (result) {
                    if (result.code == 1) {             //登陆成功，跳转到成功页
                        var index = layer.load(1, {
                            shade: [0.1,'#fff']         //0.1透明度的白色背景
                        });
                        location.href = '/admin/listAdminPromotionPage';
                    } else
                    {    //登陆失败,请核对
                        layer.msg(result.msg);
                    }
                },
                error:function (result) {
                    layer.msg(result.msg);
                }
            });
        });

    });
</script>

</body>
</html>