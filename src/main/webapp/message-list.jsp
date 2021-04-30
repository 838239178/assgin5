<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>贴吧</title>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles.css">
    </head>
    <body>
        <h1 align="center">迷你论坛-贴吧</h1>
        <div class="button-group-horizontal" style="margin: 0 auto">
            <button><a href="${pageContext.request.contextPath}/index.jsp">回到主页</a></button>
        </div>
        <div>
            <table class="ta">
            <c:forEach var="m" items="${requestScope.result}">
                <tr>
                    <th><a href="${pageContext.request.contextPath}/api/message/query/post?messageID=${m.messageID}"><c:out value="${m.title}"/></a></th>
                    <td><c:out value="${m.writer}"/></td>
                    <td><fmt:formatDate value="${m.writeDate}" pattern="yyyy-MM-dd"/></td>
                    <c:if test="${sessionScope.user.name == m.writer}">
                        <td><a href="${pageContext.request.contextPath}/api/message/remove/id?messageID=${m.messageID}">删帖</a></td>
                        <td><a href="${pageContext.request.contextPath}/api/message/view/update?messageID=${m.messageID}">修改</a></td>
                    </c:if>
                </tr>
            </c:forEach>
            </table>
        </div>
    </body>
</html>
