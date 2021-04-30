<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <c:set  var="message" scope="page" value="${requestScope.result.message}"/>
    <c:set  var="reverts" scope="page" value="${requestScope.result.reverts}"/>
    <head>
        <title>${message.title}</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
        <script>
            function onClickReply(content,id) {
                if (${sessionScope.user == null}) {
                    alert("请先登录")
                    return false;
                }
                document.getElementById("repWin").removeAttribute("hidden");
                if (content != null) {
                    document.getElementById("revert-id").setAttribute("value",id);
                    document.getElementById("repContentInput").setAttribute("value", content);
                    document.getElementById("repForm").setAttribute("action", "/api/revert/modify")
                } else {
                    document.getElementById("revert-id").setAttribute("value","");
                    document.getElementById("repContentInput").setAttribute("value", "");
                    document.getElementById("repForm").setAttribute("action", "/api/revert/reply")
                }
            }
            function onCloseReply() {
                document.getElementById("repWin").setAttribute("hidden", "true");
                return false
            }
        </script>
    </head>
    <body>
        <div>
            <div class="post-header">
                <div class="left">
                    <h1>迷你论坛-贴子</h1>
                    <h2>${message.title}</h2>
                </div>
                <div class="button-group-horizontal">
                    <button>
                        <a href="${pageContext.request.contextPath}/index.jsp">主页</a></button>
                    <button>
                        <a href="${pageContext.request.contextPath}/api/message/query/all">贴吧</a></button>
                    <button onclick="onClickReply()">回贴</button>
                </div>
            </div>
            <div class="post-content floor-border">
                <h4>楼主-<c:out value="${message.writer}"/> <span class="timespan"><fmt:formatDate value="${message.writeDate}" pattern="yyyy-MM-dd"/></span></h4>
                <p><c:out value="${message.content}"/></p>
            </div>
            <c:forEach var="f" items="${reverts}">
                <div class="floor-border floor">
                    <div class="floor-content">
                        <h5><c:out value="${f.writer}"/></h5>
                        <p><c:out value="${f.content}"/></p>
                    </div>
                    <span class="timespan"><fmt:formatDate value="${f.writeDate}" pattern="yyyy-MM-dd"/></span>
                    <c:if test="${f.writer == sessionScope.user.name}">
                        <div class="button-group-horizontal">
                            <button type="button" onclick="onClickReply('${f.content}','${f.revertID}')">修改</button>
                            <button type="button">
                                <a href="${pageContext.request.contextPath}/api/revert/remove?messageID=${message.messageID}&revertID=${f.revertID}">删除</a></button>
                        </div>

                    </c:if>
                </div>
            </c:forEach>
        </div>
        <div hidden class="reply-win" id="repWin">
            <form id="repForm" action="/api/revert/reply" method="post">
                <input id="revert-id" type="hidden" value="" name="revertID">
                <input type="hidden" value="${sessionScope.user.name}" name="writer">
                <input type="hidden" value="${message.messageID}" name="messageID">
                <div class="reply-win-header">
                    <p><c:out value="${sessionScope.user.name}" default="路人甲"/></p>
                    <div class="button-group-horizontal">
                        <button type="button" onclick="onCloseReply()" >取消</button>
                        <button>确定</button>
                    </div>
                </div>
                <div class="reply-win-content floor-border">
                    <input autocomplete="off" id="repContentInput" type="text" name="content" placeholder="写下你的回复">
                </div>
            </form>
        </div>
    </body>
</html>
