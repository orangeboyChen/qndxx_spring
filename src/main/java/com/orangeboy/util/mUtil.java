package com.orangeboy.util;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class mUtil {
    public static String getTimeStr(long l){
        Date date=new Date(l);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static String getRandomStr(int n){
        String string = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] c=new char[n];
        for(int i=0;i<n;i++){
            Random r=new Random();
            int index=r.nextInt(string.length());
            c[i]=string.charAt(index);
        }
        return String.valueOf(c);
    }
}
