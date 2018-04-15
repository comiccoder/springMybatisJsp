<%--
  Created by IntelliJ IDEA.
  User: AB053735
  Date: 2018/4/13
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form action="/user/addUser" method="post" >
        <div>用户名称：
            <input type="text" name="name" />
        </div>
        <div>用户生日：
            <input type="text" name="birthday" />
        </div>
        <div>用户：
            <input type="text" name="address" />
        </div>

        <input type="submit" value="增加用户" />
    </form>

</body>
</html>
