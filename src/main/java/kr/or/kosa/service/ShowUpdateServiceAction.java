package kr.or.kosa.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.SignUpDao;
import kr.or.kosa.dto.SignUp;
import kr.or.kosa.utils.ConnectionHelper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ShowUpdateServiceAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        SignUpDao dao = new SignUpDao();
        SignUp findSignUp = dao.showUpdateById(id);

        request.setAttribute("signUp", findSignUp);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/Views/signup/edit.jsp");
        return forward;
    }
}
