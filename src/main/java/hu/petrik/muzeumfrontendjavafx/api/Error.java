package hu.petrik.muzeumfrontendjavafx.api;

public class Error {
    String msg;

    public Error(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
