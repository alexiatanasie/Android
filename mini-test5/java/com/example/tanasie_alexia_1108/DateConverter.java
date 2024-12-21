package com.example.tanasie_alexia_1108;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {
    private static SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    public static Date fromString(String dateString) throws ParseException {
        return formatter.parse(dateString);
    }
    public static  String fromDate(Date value){
        if(value==null){
            return null;
        }
        return formatter.format(value);
    }
}
