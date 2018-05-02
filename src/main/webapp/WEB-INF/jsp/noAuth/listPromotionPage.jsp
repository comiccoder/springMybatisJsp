<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>


<div class="col-sm-10 col-sm-offset-1">
<nav class="navbar navbar-default">
    <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">comiccoder</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">一个活动 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">当前活动</a></li>
                </ul>
                <!--
                    <form class="navbar-form navbar-right">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="搜索">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <c:if test="${empty user}">
                            <li><a href="/noAuth/login">登陆/注册</a></li>
                        </c:if>
                        <c:if test="${not empty user}">
                            <li><a href="#">你好,${user.userName}</a></li>
                            <li><a href="#"  onclick="loginOut()">注销</a></li>
                        </c:if>
                    </ul>
                -->

                <ul class="nav navbar-nav navbar-right">
                    <c:set var="aaa" value="${sessionScope.user}"/>
                    <c:if test="${empty aaa}">
                        <li><a href="/noAuth/login">登陆/注册</a></li>
                    </c:if>
                    <c:if test="${not empty aaa}">
                        <li><a href="#">你好,${user.userName}</a></li>
                        <li><a href="#"  onclick="loginOut()">注销</a></li>
                    </c:if>
                </ul>

            </div><!-- /.navbar-collapse -->

    </div><!-- /.container-fluid -->
</nav>
</div>

    <div class="container-fluid">
        <c:forEach var="item" items="${promotionVos}">
            <div class="row smallSection"  style="margin-top: 20px">
                <div class="col-sm-10 col-sm-offset-1">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">发布人：</div>
                            <div class="row">活动标题：${item.promotionName}</div>
                            <div class="col-sm-12" style="margin-left:10px">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                            ${item.promotionContent}
                                    </div>
                                </div>
                            </div>

                            <div class="row"></div>
                            <div class="row">
                                <div class="btn btn-primary"   style="float: right; margin-right: 10px" onclick="addSection()">详情</div>
                                <div class="btn btn-primary"   style="float: right; margin-right: 10px" onclick="promotionApply()">
                                    <c:if test="${item.applyState== 0}">
                                        申&nbsp;&nbsp;&nbsp;&nbsp;请
                                    </c:if>
                                    <c:if test="${item.applyState== 1}">
                                        退回申请
                                    </c:if>
                                </div>
                                <input type="hidden" value="${item.applyState}"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--smallSection 结束-->
        </c:forEach>

    </div>

    <div style="text-align: center;">
    <!--用来显示1，2，3的序列-->
        <div >共${promotionNum}条记录</div>
        <ul class="pagination pagination-lg">
            <c:if test="${currentPage == 1}">
                <li class="disabled"><a href="#">前一页</a></li>
            </c:if>
            <c:if test="${currentPage != 1}">
                <li><a href="listUserPage?page=${currentPage-1}">前一页</a></li>
            </c:if>
            <c:if test="${currentPage == 1}">
                <li class="disabled" ><a href="#">1</a></li>
            </c:if>
            <c:if test="${currentPage != 1}">
                <li><a href="listUserPage?page=1">1</a></li>
            </c:if>
            <%
                int pageTimes = (Integer)request.getAttribute("pageTimes");
                for(int i=1;i<pageTimes;i++)
                {
            %>
            <c:if test="${currentPage == page}">
                <li><a href="#"><%=i+1%></a></li>
            </c:if>
            <c:if test="${currentPage != page}">
                <li><a href="listUserPage?page=<%=i+1%>"><%=i+1%></a></li>
            </c:if>
            <%  } %>

            <c:if test="${currentPage == pageTimes}">
                <li class="disabled"><a href="#">后一页</a></li>
            </c:if>
            <c:if test="${currentPage != pageTimes}">
                <li><a href="listUserPage?page=${currentPage+1}">后一页</a></li>
            </c:if>
        </ul>
    </div>
</div>

<script src="../js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
        var loginOut= function() {
                $.ajax({
                    type: "POST",
                    url: "../noAuth/loginOut",      //-- 请求注销
                    data:"",
                    success: function (result) {
                        if (result.code == 1) {  //登陆成功，跳转到成功页
                            location.href = '/noAuth/listPromotionPage';
                        }
                    }
                });
            }
</script>


</body>
</html>



