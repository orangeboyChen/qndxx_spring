package com.orangeboy.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Email {
    private String email;
    private String code;
    private long requestTime;


    public Email(String email){
        this.email = email;
        requestTime = System.currentTimeMillis();
    }


    public Email(String email, String code){
        this.email = email;
        this.code = code;
        requestTime = System.currentTimeMillis();
    }


}
