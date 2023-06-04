package kr.or.kosa.action;

/*
servlet (client요청)

1. 화면을 주세요 (UI 경로)
2. 처리해 주세요 (SERVIECE)

화면 아니면 로직 처리
*/

public class ActionForward {
    private boolean isRedirect=false; //뷰의 전환 (redirect or forward)
    private String path = null; // 이동경로 (뷰의 주소)

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
