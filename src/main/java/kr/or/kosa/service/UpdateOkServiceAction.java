package kr.or.kosa.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.SignUpDao;
import kr.or.kosa.utils.ConnectionHelper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateOkServiceAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");

        SignUpDao dao = new SignUpDao();
        String viewpage = dao.updateOk(id, name, age, email, gender);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath(viewpage);
        return forward;
    }
}
