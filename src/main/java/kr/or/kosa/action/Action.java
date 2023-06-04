package kr.or.kosa.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

/*
 *서비스 클래스의 모든 함수는 동일한 리턴타입과 parameter 가지고 사용 ......
 */
public interface Action {
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
