package com.orangeboy.pojo;

public class Email {
    private String email;
    private String code;
    private long requestTime;

    public Email(){}

    public Email(String email){
        this.email = email;
        requestTime = System.currentTimeMillis();
    }


    public Email(String email, String code){
        this.email = email;
        this.code = code;
        requestTime = System.currentTimeMillis();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }


}
