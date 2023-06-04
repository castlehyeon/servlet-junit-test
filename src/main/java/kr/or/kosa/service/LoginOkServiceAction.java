package kr.or.kosa.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.SignUpDao;
import kr.or.kosa.utils.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginOkServiceAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");

        SignUpDao dao = new SignUpDao();
        String viewpage = null;
        viewpage = dao.loginOk(id, pwd, request);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath(viewpage);
        return forward;
    }
}
