<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/font-awesome.css?v=4.4.0" rel="stylesheet">
</head>
<body>

    <c:if test="${ok == 2 }">
        查找失败
    </c:if>
    <form action="/user/login" method="post" >
        <div>用户名：
            <input type="text" name="userName" />
        </div>
        <div>密码：
            <input type="text" name="password" />
        </div>

        <input type="submit" value="登陆" />
    </form>

    <button class="btn btn-primary" data-toggle="button" id="hellow">访问hellow</button>
    <button class="btn btn-primary" data-toggle="button" id="permTest" onclick="location='../admin'">测试一下权限</button>



    <!-- 全局js -->
    <script src="../js/jquery.min.js" type="text/javascript"></script>

    <script type="text/javascript">

        $(document).ready(function () {
            $("#hellow").on('click',
                    function(){
                        hellow();
                    }
            );
        });

        //--- 登陆  ---
        function hellow() {
            $.ajax({
                type: "POST",
                url: "../hellow",
                data: $('#signupForm').serialize(),
                success: function (r) {
                    if (r.code == 0) {
                        parent.location.href = 'hellow';
                    } else {
                        alert(r.msg);
                    }
                }
            });
        }
    </script>

</body>
</html>
