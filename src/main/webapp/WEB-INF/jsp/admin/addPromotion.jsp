<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

    <div class="container-fluid">
        <div class="col-sm-10 col-sm-offset-1">
            <form  id="allForm" class="form-horizontal" method="post">  <!--大表单-->
                <div class="form-group" style="margin-top: 50px">
                    <label  class="col-sm-2 control-label">发布人</label>
                    <div class="col-sm-9">
                        <input type="input" name="publicName"  placeholder="发布人" class="form-control" >
                    </div>
                </div>

                <div class="form-group" style="margin-top: 5px">
                    <label  class="col-sm-2 control-label">活动标题</label>
                    <div class="col-sm-9">
                        <input type="input" name="promotionName"   placeholder="活动标题" class="form-control" >
                    </div>
                </div>

                <div class="form-group" style="margin-top: 5px">
                    <label  class="col-sm-2 control-label">活动内容</label>
                    <div class="col-sm-9">
                        <textArea  name="promotionContent"  placeholder="活动内容" class="form-control"  > </textArea>
                    </div>
                </div>
            </form>

            <div class="row">
                <div class="col-sm-10 col-sm-offset-1">
                    <div class="btn btn-primary"   style="float:left; margin-right: 10px" onclick="addPromotion()">增加</div>
                </div>
            </div>

        </div>
    </div>  <!--container-fluid  -->

<script src="../js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
        var loginOut= function() {
            $.ajax({
                type: "POST",
                url: "../noAuth/loginOut",      //-- 请求注销
                data:"",
                success: function (result) {
                    if (result.code == 1) {  //登陆成功，跳转到成功页
                        location.href = '/admin/listPromotionPage';
                    }
                }
            });
        }

        //请求添加活动
        function addPromotion(){
            $.ajax({
                type: "POST",
                url: "../admin/addPromotion",      //-- 请求注销
                data: $('#allForm').serialize(),
                success: function (result) {
                    if (result.code == 1) {      //如果添加活动成功的话
                        window.close();
                        window.opener.location.href = "/admin/listAdminPromotionPage";	//刷新
                    }
                }
            });
        }

</script>


</body>
</html>



