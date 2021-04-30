<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>DeployYourMessage</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
        <style>
            .deploy-title {
                width: 25%;
                margin-left: 1%;
                height: fit-content;
            }
            .deploy-title input {
                width: 100%;
                padding-left: 3%;
                padding-top: 1%;
                padding-bottom: 1%;
                background:none;
                outline:none;
                border:none;
                margin: 0;
            }
        </style>
        <script>
            function showErrorMsg(msg) {
                alert(msg);
            }
            function setOldPost(title, content) {
                document.getElementById("title-input").setAttribute("value", title)
                document.getElementById("content-input").setAttribute("value", content)
                document.getElementById("deploy-form").setAttribute("action", "${pageContext.request.contextPath}/api/message/update")
            }
        </script>
    </head>
    <body>
        <c:if test="${requestScope.success != null and not requestScope.success}">
            <script>showErrorMsg('${requestScope.result}')</script>
        </c:if>
        <c:if test="${requestScope.result != null}" var="isUpdate"/>
        <div>
            <div class="post-header">
                <div class="left">
                    <h1>迷你论坛-发帖</h1>
                </div>
                <div class="button-group-horizontal">
                    <button>
                        <a href="${pageContext.request.contextPath}/index.jsp">主页</a>
                    </button>
                    <button>
                        <a href="${pageContext.request.contextPath}/api/message/query/all">贴吧</a>
                    </button>
                </div>
            </div>
            <div class="post-content">
                <form id="deploy-form" action="${pageContext.request.contextPath}/api/message/deploy" method="post">
                    <input type="hidden" value="${sessionScope.user.name}" name="writer">
                    <c:if test="${isUpdate}">
                        <input type="hidden" value="${requestScope.result.messageID}" name="messageID">
                    </c:if>
                    <div class="reply-win-header" style="height: 30%;">
                        <p style="left: 1%; padding-left: 0;">作者:<c:out value="${sessionScope.user.name}" default="路人甲"/></p>
                        <div class="button-group-horizontal">
                            <button>发布</button>
                        </div>
                    </div>
                    <div class="deploy-title floor-border">
                        <input id="title-input" autocomplete="off" type="text" name="title" placeholder="标题...">
                    </div>
                    <div class="reply-win-content floor-border">
                        <input id="content-input" autocomplete="off" id="repContentInput" type="text" name="content" placeholder="正文....">
                    </div>
                </form>
            </div>
        </div>
        <c:if test="${isUpdate}">
            <script>setOldPost('${requestScope.result.title}','${requestScope.result.content}')</script>
        </c:if>
    </body>
</html>
