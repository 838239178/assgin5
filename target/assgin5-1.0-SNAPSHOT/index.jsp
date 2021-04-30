<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles.css">
    </head>
<body>
    <div>
        <h1 align="center">迷你论坛-首页</h1>
        <c:if test="${sessionScope.user != null}" var="isLogin" scope="page">
            <div style="text-align: center;"><p>欢迎<c:out value="${sessionScope.user.name}"/></p></div>
        </c:if>
        <table class="ta">
            <tr>
                <td><a href="${pageContext.request.contextPath}/api/message/query/all">逛贴吧</a></td>
            </tr>
            <c:if test="${isLogin}">
                <tr><td><a href="${pageContext.request.contextPath}/deploy-message.jsp">水一贴</a></td></tr>
                <tr><td><a href="${pageContext.request.contextPath}/api/message/query/user">我的帖子</a></td></tr>
                <tr><td><a href="${pageContext.request.contextPath}/api/auth/logout">退出登录</a></td></tr>
            </c:if>
            <c:if test="${!isLogin}">
                <tr><td><a href="${pageContext.request.contextPath}/login.jsp">登录</a></td></tr>
            </c:if>
        </table>
    </div>
</body>
</html>
