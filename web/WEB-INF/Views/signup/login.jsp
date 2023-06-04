<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
            <form action="${pageContext.request.contextPath}/loginok.signup" method="post" name="loginForm"
                  id="joinForm">

                <h3 style="text-align: center;">Login Page</h3>
                <div>
                    <table
                            class="table table-hover" style="width: 400px; height: 100px; margin-left: auto; margin-right: auto;">
                        <tr>
                            <th>아이디:</th>
                            <td><input class="form-control form-control-lg" type="text" name="id" id="id"></td>
                        </tr>
                        <tr>
                            <th>비밀번호</th>
                            <td><input class="form-control form-control-lg" type="password" name="pwd" id="pwd"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="submit" value="로그인"> <input
                                    type="reset" value="취소"></td>
                        </tr>
                    </table>

                </div>
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="2"><jsp:include page="/common/Bottom.jsp"></jsp:include></td>
    </tr>
</table>
</body>
</html>