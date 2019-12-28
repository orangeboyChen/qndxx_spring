package com.orangeboy.util;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class mUtil {
    public static String getTimeStr(long l){
        Date date=new Date(l);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
