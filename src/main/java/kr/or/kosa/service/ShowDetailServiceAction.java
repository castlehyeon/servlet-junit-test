package kr.or.kosa.service;

import jakarta.servlet.RequestDispatcher;
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

public class ShowDetailServiceAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SignUpDao dao = new SignUpDao();
        String id = request.getParameter("id");

        SignUp signUp = dao.getSignUpById(id);
        request.setAttribute("signUp", signUp);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/Views/signup/detail.jsp");
        return forward;
    }
}
