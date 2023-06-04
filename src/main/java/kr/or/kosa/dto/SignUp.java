package kr.or.kosa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUp {
    private String id;
    private String pwd;
    private String name;
    private int age;
    private String gender;
    private String email;
    private String ip;

    public SignUp(String memberId, String password, String name, int age, String gender, String email) {
        this.setId(memberId);
        this.setPwd(password);
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
        this.setEmail(email);
    }

    public SignUp(String id, String mname, String email) {
        this.setId(id);
        this.setName(mname);
        this.setEmail(email);
    }

    public SignUp(String id) {
        this.setId(id);
    }
}
