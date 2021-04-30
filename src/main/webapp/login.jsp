<%--
  Created by IntelliJ IDEA.
  User: 显卡的香气
  Date: 2021/4/27
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles.css">
    </head>
    <body>
        <div>
            <c:if test="${requestScope.success == false}">
            <p align="center">登录失败: <c:out value="${requestScope.result}"/><p>
            </c:if>
            <form action="/api/auth/login" method="post">
            <table class="ta">
                <tr>
                    <th>账号</th>
                    <td><input type="text" name="username" placeholder="账号"></td>
                </tr>
                <tr>
                    <th>密码</th>
                    <td><input type="password" name="password" placeholder="密码"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="登录"></td>
                </tr>
            </table>
            </form>
        </div>
    </body>
</html>
