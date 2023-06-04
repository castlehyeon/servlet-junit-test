package kr.or.kosa.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(
        description = "어노테이션 활용 필터 적용하기",
        urlPatterns = {"/*"},
        initParams = {@WebInitParam(name="encoding" , value="UTF-8")}
)
public class Encoding extends HttpFilter implements Filter {
    //추가 코드
    //member field 생성
    private String encoding;

    public Encoding() {
        super();
    }

    public void init(FilterConfig fConfig) throws ServletException{
        //최초 요청시 컴파일되고 한번만 실행 (언제 >> 코드 수정, 서버 리부팅)
        //web.xml 설정되어 있는 초기값을 read 해서 FilterConfig 통해서 ....

        this.encoding = fConfig.getInitParameter("encoding"); //web.xml 설정된 값 read
        System.out.println("filter init 함수 실행 : " + this.encoding);
    }

    public void destroy(){

    }

    //처리하는 함수
    //request, response 호출
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 이미 커밋된 응답인지 확인
        if (httpResponse.isCommitted()) {
            chain.doFilter(request, response);
            return;
        }
        // 전처리 작업

        // pass the request along the filter chain

        // request 요청에 대한 필터 실행 코드 영역
        if(request.getCharacterEncoding() == null){
            System.out.println("before : " + request.getCharacterEncoding());
            request.setCharacterEncoding(this.encoding); // web.xml 안에서의 초기값
            System.out.println("after : " + request.getCharacterEncoding());
        }
        chain.doFilter(request, response); // 필터가 존재한다면 ... 다음 필터 doFilter 호출

        //response 응답에 대한 필터 실행 코드 영역
        System.out.println("응답처리 실행 영역");
        // 후처리 작업
    }

}
