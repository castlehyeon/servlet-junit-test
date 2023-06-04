package kr.or.kosa.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebFilter(
        description = "어노테이션 활용 필터 적용하기",
        urlPatterns = {"/*"},
        initParams = {@WebInitParam(name="session" , value="UTF-8")}
)
public class Session extends HttpFilter implements Filter {
    //추가 코드
    //member field 생성
    private String session;

    //로그인과 회원가입에서는 예외를 건다.
    private static final List<String> EXCLUDED_URLS = Arrays.asList("/login.signup", "/register.signup", "/loginok.signup", "/registerok.signup", "/main.signup");

    public Session() {
        super();
    }

    public void init(FilterConfig fConfig) throws ServletException{
        //최초 요청시 컴파일되고 한번만 실행 (언제 >> 코드 수정, 서버 리부팅)
        //web.xml 설정되어 있는 초기값을 read 해서 FilterConfig 통해서 ....

        this.session = fConfig.getInitParameter("session"); //web.xml 설정된 값 read
        System.out.println("session init 함수 실행 : " + this.session);
    }

    public void destroy(){

    }

    //처리하는 함수
    //request, response 호출
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestUri = ((HttpServletRequest) request).getRequestURI();

        System.out.println(requestUri);
        // 제외할 URL 패턴에 해당하는 경우 필터를 적용하지 않음
        if (EXCLUDED_URLS.contains(requestUri)) {
            System.out.println("예외처리완료");
            chain.doFilter(request, response);
            return;
        } else if (requestUri.endsWith("/logout.signup")) {
            // 로그아웃 요청 처리
            HttpSession session = httpRequest.getSession(false); // 세션이 존재하는지 확인
            if (session != null) {
                session.invalidate(); // 세션 무효화
            }
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.signup");
            return;
        }

        HttpSession session = httpRequest.getSession(false); // 세션이 존재하는지 확인
        if (session == null || session.getAttribute("userid") == null ) {
            // 세션이 존재하지 않거나 userid가 null인 경우
            String requestURI = httpRequest.getRequestURI();
            if (requestURI.endsWith("/login.signup")) {
                // 이미 로그인 페이지로 리다이렉트된 경우
                // 세션 정보가 없는 경우로 간주하여 예외 처리
                chain.doFilter(request, response);
            } else {
                // 로그인 페이지로 리다이렉트
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.signup");
                return;
            }
        }
        chain.doFilter(request, response); // 필터 체인 계속 진행
    }
}
