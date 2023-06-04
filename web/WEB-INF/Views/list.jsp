<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <jsp:include page="/WEB-INF/Views/include/baseStyle.jsp"/>
</head>
<body>
<table style="width: 900px; height: 500px; margin-left: auto; margin-right: auto;">
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
            <table class="table table-striped" style="width: 400px;height: 100px;margin-left: auto;margin-right: auto">
                <tr><th colspan="4">회원리스트</th></tr>
                <tr>
                    <c:forEach items="${requestScope.signUpList}" var="signUp">
                <tr>
                <td  width="100px">
                    <a href="${pageContext.request.contextPath}/showdetail.signup?id=${signUp.id}">${signUp.id}</a>
                </td>
                    <td  width="100px">${signUp.ip}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/delete.signup?id=${signUp.id}">[삭제]</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/update.signup?id=${signUp.id}">[수정]</a>
                    </td>
                </tr>
                </c:forEach>
                </tr>
            </table>
            <hr>
            <form action="/search.signup" method="post">
                회원명:<input type="text" name="search">
                <input type="submit" value="이름검색하기">
            </form>
            <hr>

        </td>
    </tr>
    <tr>
        <td colspan="2"><jsp:include page="/common/Bottom.jsp"></jsp:include></td>
    </tr>
</table>
</body>
</html>