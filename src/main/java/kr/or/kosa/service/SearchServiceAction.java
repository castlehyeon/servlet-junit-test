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
import java.util.ArrayList;
import java.util.List;

public class SearchServiceAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name=request.getParameter("search");
        SignUpDao dao = new SignUpDao();
        List<Object> resultarr = dao.searchByName(name);
        //0번째: name, 1번째, rowcount 2번째 result 3번째 signUpList

        request.setAttribute("name", (String)resultarr.get(0));
        request.setAttribute("rowcount", (int)resultarr.get(1));
        request.setAttribute("result", (String)resultarr.get(2));
        request.setAttribute("signUpList", (List<SignUp>)resultarr.get(3));
        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/Views/signup/search.jsp");
        return forward;
    }
}
