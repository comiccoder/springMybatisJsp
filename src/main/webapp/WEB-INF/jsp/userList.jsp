<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  显示所有信息：
  <table  border="4">
      <thead>
          <tr>
              <th>姓名</th>
              <th>日期</th>
              <th>地址</th>
          </tr>
      </thead>

      <c:forEach var="item" items="${list}">
          <tr>
              <th>${item.name}</th>
              <th>${item.birthday}</th>
              <th>${item.address}</th>
          </tr>
      </c:forEach>

  </table>
</body>
</html>
