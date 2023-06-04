<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원조회</title>
    <jsp:include page="/WEB-INF/Views/include/baseStyle.jsp"/>
</head>
<body>
<table class="table table-striped" style="width: 900px; height: 500px ;margin-left: auto; margin-right: auto;">
    <tr>
        <td colspan="2"><jsp:include page="/common/Top.jsp"></jsp:include>
        </td>
    </tr>
    <tr>
        <td style="width: 200px"><jsp:include page="/common/Left.jsp"></jsp:include>
        </td>
        <td style="width: 700px">
            <!--  데이터 받아서 UI 구성 -->
            <table style="width: 400px;height: 100px;margin-left: auto;margin-right: auto">
                <tr><th colspan="4">회원리스트</th></tr>
                <tr><c:forEach items="${requestScope.signUpList}" var="signUp">
                <tr>

                    <td>${signUp.id}</td>
                    <td>${signUp.name}</td>
                    <td>${signUp.email}</td>
                </tr>
                </c:forEach>
                </tr>
                <tr><td colspan='3'>
                <b>${requestScope.result}</b>
                </td></tr>
            </table>
            <a href="/showlist.signup">회원 목록 페이지</a>
        </td>
    </tr>
    <tr>
        <td colspan="2"><jsp:include page="/common/Bottom.jsp"></jsp:include>
        </td>
    </tr>
</table>
</body>
</html>