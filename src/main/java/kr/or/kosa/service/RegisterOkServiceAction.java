package kr.or.kosa.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.SignUpDao;
import kr.or.kosa.dto.SignUp;

import java.io.IOException;
import java.io.PrintWriter;

public class RegisterOkServiceAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String viewpage = null;

            SignUpDao dao = new SignUpDao();
            String id = request.getParameter("id");
            String pwd = request.getParameter("pwd");
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String gender = request.getParameter("gender");
            String email = request.getParameter("email");
            String ip = request.getRemoteAddr();
            int row = dao.insertSignUp(new SignUp(id, pwd, name, age, gender, email, ip));

            String result = "";
            if (row > 0) {
                result = id + " 회원님 방가방가^^<br>";
            } else {
                result = "가입실패";
            }

            request.setAttribute("result", result);
            viewpage = "/WEB-INF/Views/signup/main.jsp";

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath(viewpage);

        return forward;
    }
}
