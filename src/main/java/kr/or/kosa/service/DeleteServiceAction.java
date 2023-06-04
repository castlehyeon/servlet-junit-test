package kr.or.kosa.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.SignUpDao;
import kr.or.kosa.utils.ConnectionHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteServiceAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SignUpDao dao = new SignUpDao();
        String id = request.getParameter("id");

        dao.deleteSignUpById(id);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/showlist.signup");
        return forward;
    }
}
