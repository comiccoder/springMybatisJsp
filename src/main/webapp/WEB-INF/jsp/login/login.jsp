<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
</head>
<body>

    <c:if test="${ok == 2 }">
        查找失败
    </c:if>
    <form action="/user/login" method="post" >
        <div>用户名：
            <input type="text" name="name" />
        </div>
        <div>密码：
            <input type="text" name="pass" />
        </div>

        <input type="submit" value="登陆" />
    </form>
</body>
</html>
