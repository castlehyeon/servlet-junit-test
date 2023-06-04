package kr.or.kosa.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.service.*;

import java.io.IOException;

@WebServlet("*.signup")
public class FrontJdbcController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String requestUri = request.getRequestURI(); //전체 url 출력
        String contextPath = request.getContextPath(); //루트 path값
        String urlcommand = requestUri.substring(contextPath.length());

        System.out.println("requestUri : " + requestUri);
        System.out.println("contextPath : " + contextPath);
        System.out.println("urlcommand : " + urlcommand);


        Action action = null;
        ActionForward forward = null;

        if (urlcommand.equals("/main.signup")) {
            //UI제공 (Service객체 필요없어요)
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/Views/signup/main.jsp");
            System.out.println("main을 타고 있슴");

        } else if (urlcommand.equals("/register.signup")) {
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/Views/signup/signup.jsp");
        } else if (urlcommand.equals("/registerok.signup")) {
            action = new RegisterOkServiceAction();
            forward = action.execute(request, response);
        } else if (urlcommand.equals("/login.signup")) {
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/Views/signup/login.jsp");
        } else if (urlcommand.equals("/loginok.signup")) {
            //UI 제공 + 서비스 로직 필요
            action = new LoginOkServiceAction();
            forward = action.execute(request, response);
        }  else if (urlcommand.equals("/showdetail.signup")) {
            action = new ShowDetailServiceAction();
            forward = action.execute(request, response);
        } else if (urlcommand.equals("/showlist.signup")) {
            //UI 제공 + 서비스 로직 필요
            System.out.println("list링크 탔음");
            action = new ShowListServiceAction();
            forward = action.execute(request, response);
            //서버에 요청했던 url 주소 리퀘스트
            //print 해보면...
        } else if (urlcommand.equals("/update.signup")) {
            //UI 제공 + 서비스 로직 필요
            action = new ShowUpdateServiceAction();
            forward = action.execute(request, response);
            //서버에 요청했던 url 주소 리퀘스트
            //print 해보면...
        } else if (urlcommand.equals("/updateok.signup")) {
            //UI 제공 + 서비스 로직 필요
            action = new UpdateOkServiceAction();
            forward = action.execute(request, response);
            //서버에 요청했던 url 주소 리퀘스트
            //print 해보면...
        } else if (urlcommand.equals("/search.signup")) {
            //UI 제공 + 서비스 로직 필요
            action = new SearchServiceAction();
            forward = action.execute(request, response);
            //서버에 요청했던 url 주소 리퀘스트
            //print 해보면...
        } else if (urlcommand.equals("/delete.signup")) {
            //UI 제공 + 서비스 로직 필요
            action = new DeleteServiceAction();
            forward = action.execute(request, response);
            //서버에 요청했던 url 주소 리퀘스트
            //print 해보면...
        }
        if (forward != null) {
            if (forward.isRedirect()) { //true -> location.href=""새로운 페이지 받겠다.
                System.out.println("단순 리다이렉트");
                response.sendRedirect(forward.getPath());
            } else {
                //false (forward) >> 포워드를 해야 객체를 얻어서 출력할 수 있다. view페이지 >> 저장자원출력
                RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
                System.out.println("포워드실행");
                System.out.println(dis);
                System.out.println(forward.getPath());
                dis.forward(request, response);
            }

        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); //기존 session 있으면 리턴 , 없으면 생성
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }
}
