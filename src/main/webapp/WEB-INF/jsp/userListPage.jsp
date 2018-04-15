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
              <th>等级</th>
          </tr>
      </thead>

      <c:forEach var="item" items="${users}">
          <tr>
              <th>${item.name}</th>
              <th>${item.birthday}</th>
              <th>${item.address}</th>
              <th>${item.levelName}</th>
          </tr>
      </c:forEach>
  </table>

      <!--用来显示1，2，3的序列-->
      <div class="left">共${userNum}条记录</div>
      <div class="right">
          <c:if test="${currentPage == 1}">
              <span class="disabled"><< 前一页</span>
          </c:if>
          <c:if test="${currentPage != 1}">
              <a href="listUserPage?page=${currentPage-1}"><< 前一页</a>
          </c:if>
          <c:if test="${currentPage == 1}">
              <span class="current">1</span>
          </c:if>
          <c:if test="${currentPage != 1}">
              <a href="listUserPage?page=1">1</a>
          </c:if>
          <%
              int pageTimes = (Integer)request.getAttribute("pageTimes");
              for(int i=1;i<pageTimes;i++)
              {
          %>
                  <c:if test="${currentPage == page}">
                      <span class="current"><%=i+1%></span>
                  </c:if>
                  <c:if test="${currentPage != page}">
                      <a href="listUserPage?page=<%=i+1%>"><%=i+1%></a>
                  </c:if>
          <%  } %>

          <c:if test="${currentPage == pageTimes}">
              <span class="disabled">后一页 >></span>
          </c:if>
          <c:if test="${currentPage != pageTimes}">
              <a href="listUserPage?page=${currentPage+1}">后一页 >></a>
          </c:if>
      </div>

</body>
</html>



