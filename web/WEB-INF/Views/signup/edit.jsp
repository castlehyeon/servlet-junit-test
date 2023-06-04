<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <td colspan="2"><jsp:include page="/common/Top.jsp"></jsp:include>
        </td>
    </tr>
    <tr>
        <td style="width: 200px"><jsp:include page="/common/Left.jsp"></jsp:include>
        </td>
        <td style="width: 700px">
            <form action="/updateok.signup" method="post">

                <h3 style="text-align: center;">회원가입</h3>
                <div>
                    <table
                            style="width: 400px; height: 200px; margin-left: auto; margin-right: auto;">
                        <tr>
                            <td>아이디</td>
                            <td>
                                <input class="form-control form-control-lg" type="text" name="id" value="${signUp.id}" readonly>
                            </td>
                        </tr>
                        <tr>
                            <td>비번</td>
                            <td>${signUp.pwd}</td>
                        </tr>
                        <tr>
                            <td>이름</td>
                            <td>
                                <input class="form-control form-control-lg" type="text" name="name" value="${signUp.name}" style="background-color: yellow">
                            </td>
                        </tr>
                        <tr>
                            <td>나이</td>
                            <td>
                                <input class="form-control form-control-lg" type="text" name="age" value="${signUp.age}" style="background-color: yellow">
                            </td>
                        </tr>
                        <tr>
                            <td>성별</td>
                            <td>
                                [${signUp.gender}]
                                <c:set var="gender" value="${signUp.gender}"/>
                                    <input class="form-check" type="radio" name="gender" id="gender" value="여" ${gender eq '여' ? 'checked' : ''} />여자
                                    <input class="form-check" type="radio" name="gender" id="gender" value="남" ${gender eq '남' ? 'checked' : ''} />남자
                            </td>
                        </tr>
                        <tr>
                            <td>이메일</td>
                            <td>
                                <input class="form-control form-control-lg" type="text" name="email" value="${signUp.email}" style="background-color: yellow">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="수정하기">
                                <a href='/showlist.signup'>리스트이동</a></td>
                    </table>

                </div>
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="2"><jsp:include page="/common/Bottom.jsp"></jsp:include>
        </td>
    </tr>
</table>
</body>
</html>
