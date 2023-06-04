package kr.or.kosa.dao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.or.kosa.dto.SignUp;
import kr.or.kosa.utils.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SignUpDao {
    //전체조회
    public List<SignUp> getSignUpList() throws SQLException {

        PreparedStatement pstmt = null;
        String sql="select id, pwd, name, age, gender, email, ip from koreaMember";

        Connection conn = ConnectionHelper.getConnection("mariadb");
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        List<SignUp> signUpList = new ArrayList<>();
        while(rs.next()) {
            SignUp s = new SignUp();
            s.setId(rs.getString("id"));
            s.setPwd(rs.getString("pwd"));
            s.setName(rs.getString("name"));
            s.setAge(rs.getInt("age"));
            s.setGender(rs.getString("gender"));
            s.setEmail(rs.getString("email"));
            s.setIp(rs.getString("ip"));

            signUpList.add(s);
        }

        ConnectionHelper.close(rs);
        ConnectionHelper.close(pstmt);

        return signUpList;
    }

    public int insertSignUp(SignUp dto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try{
            conn = ConnectionHelper.getConnection("mariadb");
            String sql = "insert into koreaMember(id,pwd, name, age, gender, email, ip) values(?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, dto.getId());
            pstmt.setString(2, dto.getPwd());
            pstmt.setString(3, dto.getName());
            pstmt.setInt(4, dto.getAge());
            pstmt.setString(5, dto.getGender());
            pstmt.setString(6, dto.getEmail());
            pstmt.setString(7, dto.getIp());

            result = pstmt.executeUpdate();


        }catch (Exception e){
            System.out.println(e.getMessage());

        }finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn); //반환
        }
        return result;
    }
    //조건조회 (where id=? : 데이터 1건 보장 : id컬럼 > unique , primary key)
    public SignUp getSignUpById(String id) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        SignUp signUp = null;
        try{
            conn = ConnectionHelper.getConnection("mariadb");
            String sql = "select id,pwd,name,age,gender,email from koreamember where id=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, id);

            rs = pstmt.executeQuery();
            //rs.next(); 추후에 데이터 1건 경우  (while 없이 )
            while(rs.next()){
                String memberId = rs.getString("id");
                String password = rs.getString("pwd");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                signUp = new SignUp(memberId, password, name, age, gender, email);
            }

        }catch(Exception e){

        }finally{
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
        }

        return signUp;
    }

    public void deleteSignUpById(String id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConnectionHelper.getConnection("mariadb");
            String sql = "delete from koreaMember where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            String message = "삭제되었습니다.";
            String script = "<script>alert('" + message + "');</script>";

            int row = pstmt.executeUpdate();
            if (row > 0) {
                System.out.println("삭제성공");

            } else {
                //필요에 따라 추가
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
        }
    }
    public String loginOk(String id, String pwd, HttpServletRequest request){
        //Class.forName("oracle.jdbc.OracleDriver"); tomcat 6.0 생략 가능
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String viewpage = null;
        try{

            //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","bituser","1004");
            //비기능적 요구사항
            conn = ConnectionHelper.getConnection("mariadb");
            String sql="select id, pwd from koreamember where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();

            HttpSession session = request.getSession(); //기존 session 있으면 리턴, 없으면 생성


            //업무
            while(rs.next()){
                //데이터가 있다 (id가 존재)

                //ID 존재
                if(pwd.equals(rs.getString("pwd"))){
                    session.setAttribute("userid", rs.getString("id"));
                    //이동처리
                    viewpage = "/main.signup";
                }else{
                    viewpage = "/login.signup";
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
        }
        return viewpage;
    }
    public List<Object> searchByName(String name){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String result = null;
        List<SignUp> signUpList = new ArrayList<>();
        List<Object> resultarr = new ArrayList<>();
        try {
            //where ename like '%길동%'
            conn = ConnectionHelper.getConnection("mariadb");
            String sql = "select count(*) from koreaMember where name like ?";
            String sql2 = "select id, name, email from koreamember where name like '%" + name + "%'";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, '%' + name + '%');
            rs = pstmt.executeQuery();
            SignUp signUp =null;
            int rowcount = 0;
            if (rs.next()) {
                rowcount = rs.getInt(1); //조회건수

            }
            if(rowcount > 0) {
                pstmt = conn.prepareStatement(sql2);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String mname = rs.getString(2);
                    String email = rs.getString(3);
                    signUp = new SignUp(id, mname, email);
                    signUpList.add(signUp);
                }
                result = "[" + name + "] 조회결과" + rowcount + "건 조회";
                resultarr.add(name);
                resultarr.add(rowcount);
                resultarr.add(result);
                resultarr.add(signUpList);
            }else {
                result = "조회결과가 없습니다";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
        }
        return resultarr;
    }
    public SignUp showUpdateById(String id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SignUp signUp = null;
        try {
            conn = ConnectionHelper.getConnection("mariadb");
            String sql = "select id,pwd,name,age,gender,email from koreamember where id=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, id);

            rs = pstmt.executeQuery();
            //rs.next(); 추후에 데이터 1건 경우  (while 없이 )
            while(rs.next()){
                String memberId = rs.getString("id");
                String password = rs.getString("pwd");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                signUp = new SignUp(memberId, password, name, age, gender, email);
            }
        } catch (Exception e) {

        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
        }
        return signUp;
    }
    public String updateOk(String id, String name, int age, String email, String gender){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String viewpage = null;
        try{
            conn = ConnectionHelper.getConnection("mariadb");
            String sql = "update koreaMember set name=? , age=? , email=? , gender=? where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, email);
            pstmt.setString(4, gender);
            pstmt.setString(5, id);
            int result = pstmt.executeUpdate();
            if(result > 0){
                viewpage = "/showlist.signup";
                System.out.println("성공");
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionHelper.close(pstmt);

        }
        return viewpage;
    }
}
