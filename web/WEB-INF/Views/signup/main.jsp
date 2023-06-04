<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/WEB-INF/Views/include/baseStyle.jsp"/>
</head>
<body>
<table class="table table-striped"
       style="width: 900px; height: 500px; margin-left: auto; margin-right: auto;">
    <tr>
        <td colspan="2">
            <jsp:include page="/common/Top.jsp"></jsp:include>
        </td>
    </tr>
    <tr>
        <td style="width: 200px">
            <jsp:include page="/common/Left.jsp"></jsp:include>
        </td>
        <td style="width: 700px">
            <!-- MAIN PAGE CONTENT  -->
            <c:choose>
                <c:when test="${not empty sessionScope.userid}">
                    <!-- 회원 -->
                    ${sessionScope.userid} 회원님 방가방가^^<br>
                    <c:if test="${sessionScope.userid == 'admin'}">
                        <a href='/showlist.signup'>회원관리</a>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <!-- 로그인 하지 않은 사용자 -->
                    <!-- 메인 페이지는 회원만 볼 수 있어요 (강제 링크 추가) -->
                    사이트 방문을 환영합니다 ^^ <br>회원가입 좀 하지 ...
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <jsp:include page="/common/Bottom.jsp"></jsp:include>
        </td>
    </tr>
</table>
</body>
</html>
