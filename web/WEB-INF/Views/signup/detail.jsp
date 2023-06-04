<%@ page language="java" contentType="text/html; charset=UTF-8"
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
            <table class="table table-striped" style="width: 400px;height: 100px;margin-left: auto;margin-right: auto;">
                <tr>
                    <td style="width:100px"><b>아이디</b></td>
                    <td style="width:100px">${signUp.id}</td>
                </tr>
                <tr>
                    <td style="width:100px"><b>비번</b></td>
                    <td style="width:100px">${signUp.pwd}</td>
                </tr>
                <tr>
                    <td style="width:100px"><b>이름</b></td>
                    <td style="width:100px">${signUp.name}</td>
                </tr>
                <tr>
                    <td style="width:100px"><b>나이</b></td>
                    <td style="width:100px">${signUp.age}</td>
                </tr>
                <tr>
                    <td style="width:100px"><b>성별</b></td>
                    <td style="width:100px">${signUp.gender}</td>
                </tr>
                <tr>
                    <td style="width:100px"><b>이메일</b></td>
                    <td style="width:100px">${signUp.email}</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <a href="/showlist.signup">목록가기</a>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2"><jsp:include page="/common/Bottom.jsp"></jsp:include></td>
    </tr>
</table>
</body>
</html>