package kr.or.kosa.service;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.SignUpDao;
import kr.or.kosa.dto.SignUp;

import java.io.IOException;
import java.util.List;

public class ShowListServiceAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SignUpDao dao = new SignUpDao();
        try {
            List<SignUp> signUpList = dao.getSignUpList();
            request.setAttribute("signUpList", signUpList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/Views/list.jsp");

        return forward;
    }
}